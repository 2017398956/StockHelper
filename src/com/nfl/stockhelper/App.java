package com.nfl.stockhelper;

import com.nfl.stockhelper.netservice.GetStocksInfoService2;
import com.nfl.stockhelper.utils.net.CustomCallback;
import com.nfl.stockhelper.utils.net.HttpClient;

import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

/**
 * Created by nfl on 2017/9/20.
 */
public class App {

    // https://weixin.citicsinfo.com/reqxml?action=1230&c.funcno=20009&c.version=1&c.stock_code=000816&c.market=SZ&c.type=day&c.count=&c.cfrom=H5&c.tfrom=PC&c.CHANNEL=
    // https://nufm.dfcfw.com/EM_Finance2014NumericApplication/JS.aspx?type=CT&cmd=C.2&sty=FCOIATA&sortType=C&sortRule=1&page=1&pageSize=20&js=var%20quote_123%3d{rank:[(x)],pages:(pc)}&token=7bc05d0d4c3c22ef9fca8c2a912d779c&jsName=quote_123&_g=0.6862238425171772
    public static void main(String[] args) {



        String strC = "aabbccdd";
//        +
//                "sedrytfgnkhlidtucyvuiuhliddootxdcfvgbnlkjdalfjasihdgfopakfgaieuyrqiyradbvkahfiuuyerffoiqwgkasdgfksalgblsagfiuqwherihksahbglakhsfiueughtioghlksbhgfjlksdhglksdrhgoewtrhlskdhgsjlkdjghslkdghoulerhlgshglksdjhfgkjsldrrrrghksdjf";
        System.out.println(strC.length());
        long startTime = System.currentTimeMillis();

        char[] old = null;
        char[] now = null;
        char[] strChar = null ;
        char zhar = 0;
        StringBuffer sb = null ;

        for (int n = 0; n < 10000000; n++) {
//            old = strC.toCharArray();
//            now = new char[old.length];
//            now[0] = old[0];
//            for (int i = 1; i < old.length; i++) {
//                now[i] = old[i] == old[i - 1] ? 0 : old[i];
//            }

//            removerepeatedchar(sb , strC) ;
            removerepeatedchar(old , now , zhar , strC) ;
        }


        long endTime = System.currentTimeMillis();
        System.out.println("耗时：" + (endTime - startTime) + " "
//                + now.length
        );
//        String temp = new String(now);
//        System.out.println(temp + "的长度为" + temp.length());
        if (true) {
            return;
        }
        String str = "6L+Z5piv5LiA6YOo55S15b2x";
        byte[] base64Byte = Base64.getDecoder().decode(str);
        File file = new File("D:\\360\\base64Image.jpg");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(base64Byte);
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public static String removerepeatedchar(StringBuffer sb , String s) {
        if (s == null)
            return s;
        sb = new StringBuffer();
        int i = 0, len = s.length();
        while (i < len) {
            char c = s.charAt(i);
            sb.append(c);
            i++;
            while (i < len && s.charAt(i) == c) {
                i++;
            }
        }
        return sb.toString();
    }

    public static void removerepeatedchar(char[] chars1 , char[] chars2 , char c ,  String s) {
        if (s == null)
            return ;
        chars1 = s.toCharArray() ;
        chars2 = new char[chars1.length] ;
        int i = 0, len = s.length();
        while (i < len) {
            c = chars1[i];
            chars2[i] = c ;
            i++;
            while (i < len && chars1[i] == c) {
                i++;
            }
        }
    }
}
