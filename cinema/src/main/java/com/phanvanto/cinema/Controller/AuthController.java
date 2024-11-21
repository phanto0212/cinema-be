package com.phanvanto.cinema.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phanvanto.cinema.Configs.JwtTokenUtil;
import com.phanvanto.cinema.Entity.User;
import com.phanvanto.cinema.Request.LoginRequest;
import com.phanvanto.cinema.Service.RoleService;
import com.phanvanto.cinema.Service.UserRoleService;
import com.phanvanto.cinema.Service.UserService;
import com.phanvanto.cinema.Util.JwtResponse;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;



@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;
    
    @Autowired
    private RoleService roleService;
    
    @Autowired
    private UserRoleService userRoleService;
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
        	User user = userService.getUserByUsername(loginRequest.getUsername());
        	if(user == null) {
        		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Người dùng không tồn tại!");
        		
        	}
        	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        	String rawPassword = loginRequest.getPassword();
        	String encodedPasswordFromDB = user.getPassword(); // Mật khẩu đã mã hóa từ cơ sở dữ liệu

        	boolean isPasswordMatch = encoder.matches(rawPassword, encodedPasswordFromDB);
        	if(!isPasswordMatch) {
        		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Mật khẩu sai!");
        	}
            // Thực hiện quá trình xác thực (authentication)
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );

            // Lưu trữ đối tượng Authentication vào SecurityContextHolder
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Lấy UserDetails sau khi xác thực thành công
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();
            List<String> roles = userDetails.getAuthorities().stream()
                                            .map(GrantedAuthority::getAuthority)
                                            .collect(Collectors.toList());
            String token = jwtTokenUtil.generateToken(username, roles);
            return ResponseEntity.ok(new JwtResponse(token));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }
    @GetMapping("/get/user")
    public ResponseEntity<?> getUser(HttpServletRequest request){
    	try {
    		 String jwt = request.getHeader("Authorization");
			 
		        if (jwt == null ) {
		            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or missing token" );
		        }
		        if (jwt.startsWith("Bearer ")) {
		            jwt = jwt.substring(7);
		        }
		        Claims claims = jwtTokenUtil.getClaimsFromToken(jwt);
		        java.util.Date expiration = claims.getExpiration();
		        if(expiration.before(new java.util.Date())) {
		        	 return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("token exprired");
		        }
		        String username = claims.getSubject(); // sub
	       
		        if (username == null) {
		            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
		        }
	
		        User user = userService.getUserByUsername(username);
		        if (user == null) {
		            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
		        }
		        Map<String, Object> responseBody = new HashMap<>();
		        user.setPassword("");
	            responseBody.put("user",user);
		        return ResponseEntity.ok(responseBody);
		        
    	}
    	catch(Exception e) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("get user error");
    	}
    }

}
