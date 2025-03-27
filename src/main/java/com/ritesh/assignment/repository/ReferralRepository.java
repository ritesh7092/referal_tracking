package com.ritesh.assignment.repository;

import com.ritesh.assignment.model.Referral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;


public interface ReferralRepository extends JpaRepository<Referral, Long> {
    List<Referral> findByReferrerIdAndStatus(Long referrerId, Referral.ReferralStatus status);

    @Query("SELECT r FROM Referral r WHERE r.referrer.id = :referrerId")
    List<Referral> findAllReferralsByReferrerId(Long referrerId);

    List<Referral> findByReferrerId(Long id);
}