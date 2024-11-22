package com.phanvanto.cinema.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phanvanto.cinema.Entity.Comments;
import com.phanvanto.cinema.Repository.CommentRespository;

@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	private CommentRespository commentRespository;

	@Override
	public List<Comments> getListByMovieId(Long movie_id) {
		return commentRespository.getListByMovieId(movie_id);
	}

	@Override
	public void AddorUpdate(Comments comment) {
		commentRespository.AddorUpdate(comment);
		
	}

	@Override
	public int deleteCommentById(Integer id) {
		return commentRespository.deleteCommentById(id);
	}
	
	
}
