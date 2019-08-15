package com.ecorau.vbn;

public interface PackageSender<T> {
    void send(T msg, RequestContex requestContex);
}
