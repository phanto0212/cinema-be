package com.phanvanto.cinema.Controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phanvanto.cinema.Configs.JwtTokenUtil;
import com.phanvanto.cinema.DTO.CommentDTO;
import com.phanvanto.cinema.DTO.PostCommentDTO;
import com.phanvanto.cinema.Entity.Comments;
import com.phanvanto.cinema.Entity.User;
import com.phanvanto.cinema.Service.CommentService;
import com.phanvanto.cinema.Service.UserService;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/comment")
public class ApiCommentController {
	
	@Autowired
    private SimpMessagingTemplate messagingTemplate;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@GetMapping("/get/all/comment/{id}")
	public ResponseEntity<?> getAllComment(@PathVariable("id") Long id){
		try {
			List<Comments> comments = commentService.getListByMovieId(id);
			List<CommentDTO> commentDTOs = new ArrayList<>();
			for(Comments comment : comments) {
				User user =  comment.getUser();
				CommentDTO commentDTO = new CommentDTO();
				commentDTO.setAuthor(user.getUsername());
				commentDTO.setContent(comment.getComment_text());
				commentDTO.setId(comment.getId());
				commentDTO.setCreated_at(comment.getCreated_at());
				commentDTOs.add(commentDTO);
			}
			Map<String, Object> reponse =  new HashMap<>();
			reponse.put("comments", commentDTOs);
			return ResponseEntity.ok(reponse);
			
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	@PostMapping("/post/comment")
	public ResponseEntity<?> postComment(@RequestBody PostCommentDTO postCommentDTO, HttpServletRequest request){
		try {
			 String jwt = request.getHeader("Authorization");
		        if (jwt == null || !jwt.startsWith("Bearer ")) {
		            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or missing token");
		        }

		        jwt = jwt.substring(7);
		        Claims claims = jwtTokenUtil.getClaimsFromToken(jwt);
		        java.util.Date expiration = claims.getExpiration();
		        if (expiration.before(new java.util.Date())) {
		            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token expired");
		        }

		        String username = claims.getSubject();
		        if (username == null) {
		            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
		        }

		        // Kiểm tra User
		        User user = userService.getUserByUsername(username);
		        if (user == null) {
		            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
		        }
		        Comments comment = new Comments();
		        comment.setMovie_id(postCommentDTO.getMovie_id());
		        comment.setCreated_at(new Timestamp(System.currentTimeMillis()));
		        comment.setUser(user);
		        comment.setComment_text(postCommentDTO.getContent());
		        commentService.AddorUpdate(comment);
		        messagingTemplate.convertAndSend(
                        "/topic/comment/" + postCommentDTO.getMovie_id(), 
                        "comment successful for movie " + postCommentDTO.getMovie_id()
                    );
		        return ResponseEntity.ok("Bình luận thành công");
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
}
