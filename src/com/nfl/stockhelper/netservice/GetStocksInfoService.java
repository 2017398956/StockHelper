package com.nfl.stockhelper.netservice;

import com.nfl.stockhelper.utils.net.CommonNetService;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

import java.util.Map;

/**
 * Created by nfl on 2017/9/21.
 */
public interface GetStocksInfoService extends CommonNetService {
    @GET("/")
    Call<String> getResultString(@QueryMap Map<String, String> params);
}

// https://nufm.dfcfw.com/EM_Finance2014NumericApplication/JS.aspx?type=CT&cmd=C.2&sty=FCOIATA&sortType=C&sortRule=1&page=1&pageSize=20&js=var%20quote_123%3d{rank:[(x)],pages:(pc)}&token=7bc05d0d4c3c22ef9fca8c2a912d779c&jsName=quote_123&_g=0.6862238425171772
