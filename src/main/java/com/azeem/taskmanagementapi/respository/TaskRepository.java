package com.azeem.taskmanagementapi.respository;
import com.azeem.taskmanagementapi.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TaskRepository extends JpaRepository<Task,Long> {
}
