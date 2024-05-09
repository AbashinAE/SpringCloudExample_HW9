package ru.gb.SpringAOP.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.gb.SpringAOP.model.Task;
import ru.gb.SpringAOP.model.TaskStatus;


public interface TaskRepository  extends JpaRepository<Task, Long>{
    List<Task> findByStatus(TaskStatus status);
}