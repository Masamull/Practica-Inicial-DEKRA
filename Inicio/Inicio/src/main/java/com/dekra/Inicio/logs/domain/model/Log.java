package com.dekra.Inicio.logs.domain.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Log {


    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String logType;

    private String description;

    private LocalDateTime timestamp;
}
