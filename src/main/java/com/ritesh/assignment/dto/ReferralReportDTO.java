package com.ritesh.assignment.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReferralReportDTO {
    private String userName;
    private String referralCode;
    private int totalReferrals;
    private int successfulReferrals;
}