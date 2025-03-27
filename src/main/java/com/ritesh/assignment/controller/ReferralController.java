package com.ritesh.assignment.controller;

import com.ritesh.assignment.model.Referral;
import com.ritesh.assignment.service.ReferralService;
import com.ritesh.assignment.service.CSVExportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/referrals")
@RequiredArgsConstructor
public class ReferralController {
    private final ReferralService referralService;
    private final CSVExportService csvExportService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<Referral>> getUserReferrals(@PathVariable Long userId) {
        List<Referral> referrals = referralService.getUserReferrals(userId);
        return ResponseEntity.ok(referrals);
    }

    @GetMapping("/report")
    public ResponseEntity<String> generateReferralReport() {
        String csvReport = csvExportService.generateReferralReport();
        return ResponseEntity.ok()
                .header("Content-Type", "text/csv")
                .header("Content-Disposition", "attachment; filename=referral_report.csv")
                .body(csvReport);
    }
}