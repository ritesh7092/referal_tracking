package com.ritesh.assignment.service;

import com.opencsv.CSVWriter;
import com.ritesh.assignment.model.User;
import com.ritesh.assignment.model.Referral;
import com.ritesh.assignment.repository.UserRepository;
import com.ritesh.assignment.repository.ReferralRepository;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.util.List;

@Service
public class CSVExportService {
    private final UserRepository userRepository;
    private final ReferralRepository referralRepository;

    public CSVExportService(UserRepository userRepository, ReferralRepository referralRepository) {
        this.userRepository = userRepository;
        this.referralRepository = referralRepository;
    }

    public String generateReferralReport() {
        StringWriter writer = new StringWriter();
        try (CSVWriter csvWriter = new CSVWriter(writer)) {
            csvWriter.writeNext(new String[]{
                    "User ID", "Name", "Referral Code",
                    "Total Referrals", "Successful Referrals"
            });

            List<User> users = userRepository.findAll();
            users.forEach(user -> {
                List<Referral> totalReferrals = referralRepository.findByReferrerId(user.getId());
                List<Referral> successfulReferrals = referralRepository
                        .findByReferrerIdAndStatus(user.getId(), Referral.ReferralStatus.SUCCESSFUL);

                csvWriter.writeNext(new String[]{
                        user.getId().toString(),
                        user.getFirstName() + " " + user.getLastName(),
                        user.getReferralCode(),
                        String.valueOf(totalReferrals.size()),
                        String.valueOf(successfulReferrals.size())
                });
            });
        } catch (Exception e) {
            throw new RuntimeException("CSV Export failed", e);
        }
        return writer.toString();
    }
}

