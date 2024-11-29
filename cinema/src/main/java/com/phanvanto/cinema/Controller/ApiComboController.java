package com.phanvanto.cinema.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phanvanto.cinema.Entity.Combo;
import com.phanvanto.cinema.Service.ComboService;

@RestController
@RequestMapping("/api/combo")
public class ApiComboController {

	@Autowired
	private ComboService comboService;
	
	@PostMapping("/get/all")
	public ResponseEntity<?> getALl(){
		try {
			List<Combo> combos = comboService.getList();
			Map<String, Object> reponse =  new HashMap<>();
			reponse.put("combos", combos);
			return ResponseEntity.ok(reponse);
		}
		catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}
}
