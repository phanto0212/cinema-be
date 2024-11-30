package com.phanvanto.cinema.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phanvanto.cinema.Entity.LineCombo;
import com.phanvanto.cinema.Repository.LineComboRespository;

@Service
public class LineComboServiceImpl implements LineComboService{

	@Autowired
	private LineComboRespository lineComboRespository;
	
	@Override
	public void AddorUpdate(LineCombo line_combo) {
		lineComboRespository.AddorUpdate(line_combo);
		
	}

	@Override
	public List<LineCombo> getListByticketId(Long id) {
		// TODO Auto-generated method stub
		return lineComboRespository.getListByticketId(id);
	}

}
