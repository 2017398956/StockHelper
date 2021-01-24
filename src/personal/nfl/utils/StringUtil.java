package personal.nfl.utils;

public class StringUtil {

    public static boolean isEmpty(String string) {
        if (null == string || "null".equals(string) || string.length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isNotEmpty(String string){
        return !isEmpty(string);
    }
}
