package com.ritesh.assignment.utility;

import java.security.SecureRandom;
import java.util.Base64;

public class ReferralCodeGenerator {
    private static final int CODE_LENGTH = 8;
    private static final SecureRandom secureRandom = new SecureRandom();

    public static String generateReferralCode() {
        byte[] randomBytes = new byte[CODE_LENGTH];
        secureRandom.nextBytes(randomBytes);
        return Base64.getUrlEncoder().withoutPadding()
                .encodeToString(randomBytes)
                .substring(0, CODE_LENGTH)
                .toUpperCase();
    }
}
