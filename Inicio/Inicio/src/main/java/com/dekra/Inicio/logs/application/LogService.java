package com.dekra.Inicio.logs.application;

import com.dekra.Inicio.logs.domain.model.Log;
import com.dekra.Inicio.logs.domain.repository.LogRepository;
import com.dekra.Inicio.logs.infraestructure.repository.LogRepositoryImp;
import com.dekra.Inicio.team.application.TeamObserver;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LogService implements TeamObserver {

    private final LogRepository logRepository;

   public LogService(LogRepository logRepository) {

       this.logRepository = logRepository;


   }

    @Override
    public void update(Long teamId, Long userId, String rolName) {
        String message = String.format("User %d assigned to Team %d with role: %s", userId, teamId, rolName);
        this.createLog("ASSIGNATION", message);
    }



    public Log createLog(String logType, String description) {
        Log log = new Log();
        log.setLogType(logType);
        log.setDescription(description);
        log.setTimestamp(LocalDateTime.now());
         logRepository.saveLog(log);

         return log;
    }


    public List<Log> listLogs() {

        return logRepository.listLogs();


    }


}
