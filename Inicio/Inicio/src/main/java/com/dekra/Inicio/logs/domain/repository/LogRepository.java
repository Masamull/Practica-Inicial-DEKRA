package com.dekra.Inicio.logs.domain.repository;

import com.dekra.Inicio.logs.domain.model.Log;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogRepository {

   Log saveLog (Log log);

   List<Log> listLogs();
}
