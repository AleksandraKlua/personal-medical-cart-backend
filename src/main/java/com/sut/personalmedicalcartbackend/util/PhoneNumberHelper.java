package com.sut.personalmedicalcartbackend.util;

public class PhoneNumberHelper {
    public static String extractCountryCode(String phoneNumber) {
        return phoneNumber.substring(2);
    }
}
