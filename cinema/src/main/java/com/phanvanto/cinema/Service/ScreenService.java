package com.phanvanto.cinema.Service;

import java.util.List;

import com.phanvanto.cinema.Entity.Screen;

public interface ScreenService {
	List<Screen> getList();
	void AddorUpdate(Screen screen);
	Screen getScreenById(Long id);
	void deleteById(Long id);
	List<Screen> getListByCinemaId(Long id);
}
