package com.bwie.shouye.utils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by ll on 2018/8/10.
 */

public interface RequestCollBack {
    void onFailure(Call call, IOException e);
    void onResponse(Call call, Response response) throws IOException;
}

