package com.phanvanto.cinema.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.phanvanto.cinema.Entity.Combo;


public interface ComboRespository {
	List<Combo> getList();
	Combo getCombobyId(Long id);
	void AddorUpdate(Combo combo);
	void deleteComboById(Long id);
}
