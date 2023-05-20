package org.subin.bootBoard.commons.validator;

public interface MobileValidator {

    default boolean mobileNumCheck(String mobile) {
        // 1. 형식의 통일화 - 숫자가 아닌 문자를 전부 제거
        mobile = mobile.replaceAll("\\D", "");  // 숫자가 아닌 글자를 ""로 대체

        // 2. 패턴 생성 체크
        String pattern = "^01[016]\\d{3,4}\\d{4}$";


        return mobile.matches(pattern);
    }
}
