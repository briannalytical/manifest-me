package com.brijay.manifest_me.Repository;

import com.brijay.manifest_me.Model.AppUser;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

  Optional<AppUser> findByEmail(String email);

  boolean existsByEmail(String email);
}
