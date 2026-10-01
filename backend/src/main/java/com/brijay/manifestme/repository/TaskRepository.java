package com.brijay.manifestme.repository;

import com.brijay.manifestme.model.Task;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

  List<Task> findByUserIdAndCompletedAtIsNullOrderByDueDateAsc(Long userId);

  List<Task> findByEntryId(Long entryId);

  Optional<Task> findByIdAndUserId(Long id, Long userId);
}
