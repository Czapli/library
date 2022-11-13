package org.czaplinski.library.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.czaplinski.library.model.Log;
import org.czaplinski.library.repository.LogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LogService {
    private final LogRepository logRepository;

    public void saveLog(String logMessage) {
        logRepository.save(new Log(logMessage));
    }
    public List<Log> getAllLog(){
        return logRepository.findAll();
    }
}
