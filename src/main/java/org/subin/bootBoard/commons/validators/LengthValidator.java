package org.subin.bootBoard.commons.validators;

public interface LengthValidator {

    /**
     * @param str
     * @param min 최소 문자 갯수 --> 0일때는 체크X
     * @param max 최대 문자 갯수 --> 0일때는 체크X
     */
    default void strLengthCheck(String str, int min, int max, RuntimeException e) {
        if (str == null || (min > 0 && str.length() < min) || (max > 0 && str.length() > max)) {
            throw e;
        }
    }

    default void strLengthCheck(String str, int min, RuntimeException e) {
        strLengthCheck(str, min, 0, e);
    }

}