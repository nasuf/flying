package com.flying.common.service;

public interface Function<E, T> {

    public T callback(E e);

}
