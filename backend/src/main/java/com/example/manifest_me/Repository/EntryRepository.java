package com.example.manifest_me.Repository;

import com.example.manifest_me.Model.Entry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EntryRepository extends JpaRepository<Entry, Long> {

    List<Entry> findByUserIdOrderByDateAppliedDesc(Long userId);

    Optional<Entry> findByIdAndUserId(Long id, Long userId);

    List<Entry> findByUserIdAndStatus(Long userId, Entry.Status status);
}