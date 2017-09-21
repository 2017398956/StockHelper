package com.nfl.stockhelper.utils.net;

import retrofit2.Call;

import java.util.Map;

/**
 * Created by nfl on 2017/9/21.
 */
public interface CommonNetService {
    Call<String> getResultString(String... args);
    Call<String> getResultString(Map<String , String> params);
}
