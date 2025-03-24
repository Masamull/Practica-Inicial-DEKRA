package com.dekra.Inicio.logs.infraestructure.repository;

import com.dekra.Inicio.logs.domain.model.Log;
import com.dekra.Inicio.logs.domain.repository.LogRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class LogRepositoryImp implements LogRepository {

    private final Set<Log> logRepository  =new HashSet<>();
    private Long nextId = 1L; // Contador para IDs

    @Override
    public Log saveLog(Log log) {
        log.setId(nextId++);
         logRepository.add(log);

         return log;
    }

    @Override
    public List<Log> listLogs() {
        return logRepository.stream().toList();
    }


}
