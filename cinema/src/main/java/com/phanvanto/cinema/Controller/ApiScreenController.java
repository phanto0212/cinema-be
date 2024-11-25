package com.phanvanto.cinema.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phanvanto.cinema.Entity.Screen;
import com.phanvanto.cinema.Service.ScreenService;

@RestController
@RequestMapping("/api/screens")
public class ApiScreenController {
	@Autowired
    private ScreenService screenService;

    @GetMapping("/{cinemaId}")
    public List<Screen> getScreensByCinema(@PathVariable Long cinemaId) {
        return screenService.getListByCinemaId(cinemaId);
    }
}
