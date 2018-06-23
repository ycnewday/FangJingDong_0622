package com.example.lenovo.fangjingdong_0622.http;

/**
 * Created by lenovo on 2018/6/23.
 */

public interface OkLoadListener {
    void okLoadSuccess(String json);
    void okLoadError(String error);
}
