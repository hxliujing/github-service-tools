package com.javens.concurrent.test;

public interface ResultHandler<T> {
    public void handle(T result);
}