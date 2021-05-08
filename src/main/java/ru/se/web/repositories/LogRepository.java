package ru.se.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.se.web.model.Log;

public interface LogRepository extends JpaRepository<Log,Long> {
    default void addLog(Log log){
        save(log);
    }
}
