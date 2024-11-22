package com.phanvanto.cinema.Service;

import java.util.List;

import com.phanvanto.cinema.Entity.Comments;

public interface CommentService {
	List<Comments> getListByMovieId(Long movie_id);
	void AddorUpdate(Comments comment);
	int deleteCommentById(Integer id);
}
