package com.ritesh.assignment.service;


import com.ritesh.assignment.model.Referral;
import com.ritesh.assignment.repository.UserRepository;
import com.ritesh.assignment.repository.ReferralRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
@RequiredArgsConstructor
public class ReferralService {
    private final ReferralRepository referralRepository;
    private final UserRepository userRepository;

    public List<Referral> getUserReferrals(Long userId) {
        return referralRepository.findAllReferralsByReferrerId(userId);
    }
}
