package com.nfl.stockhelper.utils.net;

import com.nfl.stockhelper.netservice.GetStocksInfoService2;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import java.util.Map;

/**
 * Created by nfl on 2017/9/21.
 */
public class HttpClient {

    private static Retrofit retrofit;
    private final static String BASE_URL =
//            "https://api.github.com/" ;
            "https://nufm.dfcfw.com/EM_Finance2014NumericApplication/JS.aspx/";

    private static Retrofit getInstance() {
        if (null == retrofit) {
            synchronized (HttpClient.class) {
                if (null == retrofit) {
                    retrofit = new Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(ScalarsConverterFactory.create())
                            .build();
                }
            }
        }
        return retrofit;
    }

    public static void getDateFromServer(Class<?> serviceClass, Map<String, String> params, CustomCallback customCallback) {
        GetStocksInfoService2 service = getInstance().create(GetStocksInfoService2.class);
        Call<String> call = service.getResultString(params);
        call.enqueue(customCallback);
    }
}
