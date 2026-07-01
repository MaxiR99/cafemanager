package com.cafemanager.cafemanager.api.controller;

import com.cafemanager.cafemanager.api.response.DashboardResponseDTO;
import com.cafemanager.cafemanager.application.service.DashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {


    private final DashboardService dashboardService;


    public DashboardController(
            DashboardService dashboardService
    ) {

        this.dashboardService = dashboardService;
    }



    @GetMapping
    public ResponseEntity<DashboardResponseDTO> obtenerDashboard() {

        return ResponseEntity.ok(
                dashboardService.obtenerDashboard()
        );
    }

}
