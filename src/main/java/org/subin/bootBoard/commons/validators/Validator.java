package org.subin.bootBoard.commons.validators;

public interface Validator<T> {

    void check(T t);
}