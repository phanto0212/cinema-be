package com.phanvanto.cinema.Repository;
import java.util.List;

import com.phanvanto.cinema.Entity.LineCombo;
public interface LineComboRespository{
   
	void AddorUpdate(LineCombo line_combo);
	List<LineCombo> getListByticketId(Long id);
}
