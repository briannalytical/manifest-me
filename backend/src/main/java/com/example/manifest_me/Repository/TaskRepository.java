package com.example.manifest_me.Repository;

import com.example.manifest_me.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByUserIdAndCompletedAtIsNullOrderByDueDateAsc(Long userId);

    List<Task> findByEntryId(Long entryId);

    Optional<Task> findByIdAndUserId(Long id, Long userId);
}