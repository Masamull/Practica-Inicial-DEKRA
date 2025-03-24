package com.dekra.Inicio.logs.controller;


import com.dekra.Inicio.logs.application.LogService;
import com.dekra.Inicio.logs.domain.model.Log;
import com.dekra.Inicio.logs.domain.repository.LogRepository;
import com.dekra.Inicio.project.api.dto.ProjectDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/logs")
public class LogController {

private final LogService logService;

public LogController (LogService logService) {

    this.logService = logService;
}

    @GetMapping
    public ResponseEntity<List<Log>> listLogs() {

        return  ResponseEntity.ok(logService.listLogs());

    }
}
