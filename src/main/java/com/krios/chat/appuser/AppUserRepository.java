package com.krios.chat.appuser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    @Transactional
    Optional<AppUser> findByUsername(String username);

    @Transactional
    Optional<AppUser> findByEmail(String email);
}
