package com.phanvanto.cinema.DTO;

public class PostCommentDTO {

	private Long movie_id;
	private String content;
	public PostCommentDTO(Long movie_id, String content) {
		super();
		this.movie_id = movie_id;
		this.content = content;
	}
	public PostCommentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getMovie_id() {
		return movie_id;
	}
	public void setMovie_id(Long movie_id) {
		this.movie_id = movie_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
