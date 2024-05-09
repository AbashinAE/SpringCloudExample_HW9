package ru.gb.SpringAOP.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import ru.gb.SpringAOP.aspect.TrackUserAction;
import ru.gb.SpringAOP.model.Task;
import ru.gb.SpringAOP.model.TaskStatus;
import ru.gb.SpringAOP.repositories.TaskRepository;

/**
 * Сервис инкапсулирующий логику работы с базой данных
 */
@Service

public class DataProcessingService {
    private TaskRepository taskRepository;

    public DataProcessingService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @TrackUserAction
    public List<Task> getListTasks() {
        return taskRepository.findAll();
    }

    @TrackUserAction
    public Task addTask(Task task) {
        return taskRepository.save(task);
    }

    @TrackUserAction
    public List<Task> getListTasksByStatus(TaskStatus status) {
        return taskRepository.findByStatus(status);
    }

    @TrackUserAction
    public Task updateTaskStatusWithId(Long id, TaskStatus status) {
        Task updatedTask = taskRepository.getReferenceById(id);
        updatedTask.setStatus(status);
        updatedTask.setLastUpdateTime(LocalDateTime.now());
        return taskRepository.saveAndFlush(updatedTask);
    }

    @TrackUserAction
    public void deleteTaskWithId(Long id) {
        taskRepository.delete(taskRepository.getReferenceById(id));
    }
}
