package com.ritesh.assignment.repository;

import com.ritesh.assignment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByReferralCode(String referralCode);
    boolean existsByEmail(String email);
}
