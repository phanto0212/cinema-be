package com.phanvanto.cinema.DTO;

import java.sql.Timestamp;

public class CommentDTO {
 
	private Integer id;
	private String author;
	private String content;
	private Timestamp  created_at;
	public CommentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CommentDTO(Integer id, String author, String content, Timestamp created_at) {
		super();
		this.id = id;
		this.author = author;
		this.content = content;
		this.created_at = created_at;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}
	
}
