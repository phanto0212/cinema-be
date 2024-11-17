package com.phanvanto.cinema.Controller;

import java.sql.Timestamp;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.phanvanto.cinema.Entity.User;
import com.phanvanto.cinema.Service.UserService;

@RestController
@RequestMapping("/api")
public class SigninGoogleController {
	
	@Autowired
	private UserService userService;

	@GetMapping("/signingoogle")
	public ResponseEntity<?> currentUser(OAuth2AuthenticationToken oAuth2AuthenticationToken) {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

	    if (authentication instanceof OAuth2AuthenticationToken) {
	        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
	        return ResponseEntity.ok(oauthToken.getPrincipal().getAttributes());
	    } else {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User is not authenticated via Google OAuth2.");
	    }
	}
	@PostMapping("/google-login")
    public ResponseEntity<?> loginWithGoogle(@RequestBody Map<String, String> request) {
        String token = request.get("token");

        // Xác thực token với Google
        String googleTokenInfoUrl = "https://oauth2.googleapis.com/tokeninfo?id_token=" + token;
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> response = restTemplate.getForObject(googleTokenInfoUrl, Map.class);

        if (response != null && response.get("email") != null) {
            // Lấy thông tin từ Google response
            String email = (String) response.get("email");
            String fullname = (String) response.get("name");
            String avatarUrl = (String) response.get("picture");

            // Kiểm tra xem người dùng đã tồn tại chưa
            User user = userService.getUserByEmail(email);
            if (user == null) {
                // Tạo mới người dùng
                user = new User();
                user.setEmail(email);
                user.setFullName(fullname);
                user.setAvatar_url(avatarUrl);
                user.setPassword("google_oauth_dummy_password"); // Mật khẩu giả
                user.setEnabled(true); // Mặc định kích hoạt
                user.setCreated_at(new Timestamp(System.currentTimeMillis()));
                user.setUpdated_at(new Timestamp(System.currentTimeMillis()));
                userService.AddOrUpdate(user);
            } else {
                // Cập nhật thông tin nếu cần
                user.setFullName(fullname);
                user.setAvatar_url(avatarUrl);
                user.setUpdated_at(new Timestamp(System.currentTimeMillis()));
                userService.AddOrUpdate(user);
            }

            return ResponseEntity.ok(user);  // Trả về thông tin người dùng
        }

        return ResponseEntity.status(401).body("Invalid Google token");
    }

}
