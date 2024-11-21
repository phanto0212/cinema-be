package com.phanvanto.cinema.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phanvanto.cinema.Entity.Combo;
import com.phanvanto.cinema.Repository.ComboRespository;
@Service
public class ComboServiceImpl implements ComboService {

	@Autowired
	private ComboRespository comboRespository;
	
	@Override
	public List<Combo> getList() {
		return comboRespository.getList();
	}

	@Override
	public Combo getCombobyId(Long id) {
		return comboRespository.getCombobyId(id);
	}

	@Override
	public void AddorUpdate(Combo combo) {
		comboRespository.AddorUpdate(combo);
		
	}

	@Override
	public void deleteComboById(Long id) {
		comboRespository.deleteComboById(id);
		
	}

}
