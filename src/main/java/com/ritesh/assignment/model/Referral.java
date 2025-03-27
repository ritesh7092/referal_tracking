package com.ritesh.assignment.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "referrals")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Referral {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "referrer_id")
    private User referrer;

    @ManyToOne
    @JoinColumn(name = "referred_user_id")
    private User referredUser;

    @Enumerated(EnumType.STRING)
    private ReferralStatus status;

    private LocalDateTime createdAt;

    public enum ReferralStatus {
        PENDING, SUCCESSFUL, EXPIRED
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}