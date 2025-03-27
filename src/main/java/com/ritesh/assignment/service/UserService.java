package com.ritesh.assignment.service;

import com.ritesh.assignment.model.User;
import com.ritesh.assignment.model.Referral;
import com.ritesh.assignment.repository.UserRepository;
import com.ritesh.assignment.repository.ReferralRepository;
import com.ritesh.assignment.utility.ReferralCodeGenerator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ReferralRepository referralRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public User signup(com.ritesh.assignment.dto.@Valid UserSignupRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User newUser = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .referralCode(ReferralCodeGenerator.generateReferralCode())
                .profileCompleted(false)
                .build();

        User savedUser = userRepository.save(newUser);

        if (request.getReferralCode() != null) {
            processReferral(savedUser, request.getReferralCode());
        }

        return savedUser;
    }

    private void processReferral(User referredUser, String referralCode) {
        userRepository.findByReferralCode(referralCode)
                .ifPresent(referrer -> {
                    Referral referral = Referral.builder()
                            .referrer(referrer)
                            .referredUser(referredUser)
                            .status(Referral.ReferralStatus.PENDING)
                            .build();
                    referralRepository.save(referral);
                });
    }

    @Transactional
    public User completeProfile(Long userId, com.ritesh.assignment.dto.@Valid ProfileCompletionRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setProfileCompleted(true);

        updateReferralStatus(user);

        return userRepository.save(user);
    }

    private void updateReferralStatus(User user) {
        referralRepository.findByReferrerIdAndStatus(user.getId(), Referral.ReferralStatus.PENDING)
                .forEach(referral -> {
                    referral.setStatus(Referral.ReferralStatus.SUCCESSFUL);
                    referralRepository.save(referral);
                });
    }
}
