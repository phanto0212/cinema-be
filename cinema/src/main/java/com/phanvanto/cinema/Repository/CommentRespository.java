package com.phanvanto.cinema.Repository;

import java.util.List;

import com.phanvanto.cinema.Entity.Comments;

public interface CommentRespository {

	List<Comments> getListByMovieId(Long movie_id);
	void AddorUpdate(Comments comment);
	int deleteCommentById(Integer id);
}
