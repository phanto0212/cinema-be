package com.phanvanto.cinema.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phanvanto.cinema.Entity.Screen;
import com.phanvanto.cinema.Repository.ScreenRespository;
@Service
public class ScreenServiceImpl implements ScreenService {

	@Autowired
	private ScreenRespository screenRespository;
	
	@Override
	public List<Screen> getList() {
		return screenRespository.getList();
	}

	@Override
	public void AddorUpdate(Screen screen) {
		screenRespository.AddorUpdate(screen);
		
	}

	@Override
	public Screen getScreenById(Long id) {
		return screenRespository.getScreenById(id);
	}

	@Override
	public void deleteById(Long id) {
		screenRespository.deleteById(id);
		
	}

}
