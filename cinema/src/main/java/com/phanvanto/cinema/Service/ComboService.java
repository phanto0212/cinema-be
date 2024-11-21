package com.phanvanto.cinema.Service;

import java.util.List;

import com.phanvanto.cinema.Entity.Combo;

public interface ComboService {

	List<Combo> getList();
	Combo getCombobyId(Long id);
	void AddorUpdate(Combo combo);
	void deleteComboById(Long id);
	
}
