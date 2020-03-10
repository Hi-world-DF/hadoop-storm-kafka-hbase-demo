package scsm.data.util;

public class StringUtils {
    /**
     * 补全num位数字
     * 将给定的字符串前面补0，使字符串的长度为num位。
     *
     * @param str
     * @return
     */
    public static String fulfuill(int num,String str) {
        if(str.length() == num) {
            return str;
        } else {
            int fulNum = num-str.length();//5    963
            String tmpStr  =  "";
            for(int i = 0; i < fulNum ; i++){
                tmpStr += "0";
            }
            return tmpStr + str;
        }
    }

    public static String fulfuill(int num,int nn) {
        String str = nn + "";
        return fulfuill(num , str);
    }

}
