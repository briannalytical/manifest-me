package com.brijay.manifest_me.Repository;

import com.brijay.manifest_me.Model.Task;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

  List<Task> findByUserIdAndCompletedAtIsNullOrderByDueDateAsc(Long userId);

  List<Task> findByEntryId(Long entryId);

  Optional<Task> findByIdAndUserId(Long id, Long userId);
}
