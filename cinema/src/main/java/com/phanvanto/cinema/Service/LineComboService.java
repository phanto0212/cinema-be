package com.phanvanto.cinema.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.phanvanto.cinema.Entity.LineCombo;
@Service
public interface LineComboService {

	List<LineCombo> getListByticketId(Long id);
	void AddorUpdate(LineCombo line_combo);
}
