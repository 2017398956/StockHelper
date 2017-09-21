package com.nfl.stockhelper;

import com.nfl.stockhelper.netservice.GetStocksInfoService2;
import com.nfl.stockhelper.utils.net.CustomCallback;
import com.nfl.stockhelper.utils.net.HttpClient;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nfl on 2017/9/20.
 */
public class App {

    // https://nufm.dfcfw.com/EM_Finance2014NumericApplication/JS.aspx?type=CT&cmd=C.2&sty=FCOIATA&sortType=C&sortRule=1&page=1&pageSize=20&js=var%20quote_123%3d{rank:[(x)],pages:(pc)}&token=7bc05d0d4c3c22ef9fca8c2a912d779c&jsName=quote_123&_g=0.6862238425171772
    public static void main(String[] args) {


        Map<String, String> params = new HashMap<>();
        params.put("type", "CT");
        params.put("cmd ", "C.2");
        params.put("sty ", "FCOIATA");
        params.put("sortType ", "C");
        params.put("sortRule ", "1");
        params.put("page ", "1");
        params.put("pageSize ", "20");
        params.put("js ", "var%20quote_123%3d{rank:[(x)],pages:(pc)}");
        params.put("token ", "7bc05d0d4c3c22ef9fca8c2a912d779c");
        params.put("jsName ", "quote_123");
        params.put("_g ", "0.8876621183908553");
        CustomCallback<String> customCallback = new CustomCallback<String>() {
        };
        HttpClient.getDateFromServer(GetStocksInfoService2.class, params, customCallback);
    }
}
