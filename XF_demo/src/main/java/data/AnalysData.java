package scsm.data;

import scsm.data.util.AlarmData;
import scsm.data.util.HeartbeatData;
import scsm.data.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
* 按照协议进行
* 数据解析
*  */

public class AnalysData {

    public static void main(String[] args) {
        //模拟数据
        MockData md = new MockData();
        AnalysData ad = new AnalysData();
        // String str = md.mock();
        String str = "404001000100130b13142917000241588450000000000001120002020181001000000001000204000000000000000000000000000000000000000000000000037500760a32000000000000XX2323";
        System.out.println(str);
        System.out.println(str.length());
        //过滤数据，过滤出报警类型
        String type = ad.filterData(str);
        System.out.println(type);
        if (type == "heartbeat" || type.equals("heartbeat")){
            System.out.println(ad.analyHeartbeatData(str));
        }else if (type == "alarm" || type.equals("alarmType")
                || type == "fault" || type.equals("faultType")){
            System.out.println(ad.analyAlarmData(str));
        }
    }

    /**
    * 数据过滤：类型(heartbeat:心跳/alarm：报警/fault：故障)
    * 先根据长度，再根据type
     * @param str：数据字符串
     * @return "type"
    * */
    public String filterData(String str){
        if (str.length()==96){
            return "heartbeat";
        }else if (str.length()==156){
            String type = getData(str,37,2,0);
            if (type == "0002" || type.equals("0002")){
                return "alarm";
            }else if (type == "0004" || type.equals("0004")){
                return "fault";
            }
        }
        return null;
    }

    /**
     * 函数名：analyHeartbeatData()
     * 功能描述：心跳解码
     * @param str：数据字符串
     * @return data
     */
    public HeartbeatData analyHeartbeatData(String str){
        //初始化心跳数据
        HeartbeatData data = new HeartbeatData();
        //类型，标识心跳/报警
        data.setType(dataUnit(str,28,0));
        //心跳时间
        data.setAlarmTime(getDate(str));
        //源地址
        data.setYdz(getYdz(str));
        //部件地址
        data.setBjdz(getData(str,19,6,0));
        //系统类型
        data.setSystemType(dataUnit(str,30,0));
        //部件类型
        data.setBjType(dataUnit(str,32,0));
        //监测类型 02H：高度（CM）03H：温度（摄氏度） 05H：压力（千帕）
        //06H：浓度（%LEL） 08H：电压（伏特） 09H：电流（毫安）
        data.setMonitorType(dataUnit(str,37,0));
        //监测数值
        data.setMonitorValue(getMonitoringValue(str,0));
        //信号
        data.setSignal(dataUnit(str,40,2));
        //电池电压值
        data.setVoltage(getvoltage(str,0));

        return data;
    }

    /**
     * 函数名：analyAlarmData()
     * 功能描述：报警解码
     * @param str：数据字符串
     * @return data
     */
    public AlarmData analyAlarmData(String str){
        //初始化报警数据
        AlarmData data = new AlarmData();
        //类型，标识心跳/报警
        data.setType(dataUnit(str,28,0));
        //报警时间
        data.setAlarmTime(getDate(str));
        //源地址
        data.setYdz(getYdz(str));
        //部件地址
        data.setBjdz(getData(str,19,6,0));
        //系统类型
        data.setSystemType(dataUnit(str,30,0));
        //部件类型
        data.setBjType(dataUnit(str,32,0));
        //报警类型
        data.setAlarmType(getData(str,37,2,0));
        //故障类型
        data.setFaultType(dataUnit(str,39,0));
        //监测类型
        data.setMonitorType(dataUnit(str,64,0));
        //监测数值
        data.setMonitorValue(getMonitoringValue(str,1));
        //信号
        data.setSignal(dataUnit(str,67,2));
        //电池电压值
        data.setVoltage(getvoltage(str,1));
        //备注说明
        data.setRemarks(getData(str,40,24,0));

        return data;
    }

    /**
     *方法名：dataUnit
     *功能描述：数据的进制的转换
     * @param str：数据字符串
     * @param num：第几个字节
     * @param type: 数据类型 0:标识符,补全不转化, 1:时间及IEMI号等,补全转换 2:数值,转换不补全
     * @return temp
     */
    public String dataUnit(String str,int num,int type){
        String temp ="";
        switch (type){
            case 0 :
                temp = str.substring((num-1)*2,num*2);
                break;
            case 1 :
                temp = StringUtils.fulfuill(2,Integer.parseInt(str.substring((num-1)*2,num*2),16));
                break;
            case 2 :
                temp = Integer.parseInt(str.substring((num-1)*2,num*2),16) + "";
        }
        return temp;
    }

    /**
     * 方法名称：getData
     * 功能描述：获取各个字段的数据
     * @param str：数据字符
     * @param startNum：开始字节
     * @param length：长度
     * @return temp
     */
    public String getData (String str,int startNum,int length,int type){
        String temp = "";
        for (int i=0;i<length;i++){
            temp += dataUnit(str,startNum+i,type);
        }
        return temp;
    }

    /**
     * 方法名：getDate
     * 功能描述：获取时间,且格式化时间
     * @param str：数据字符
     * @return date
     * */
    public String getDate(String str){

        SimpleDateFormat sdf=new SimpleDateFormat("yyMMddHHmmss");
        SimpleDateFormat sdff = new SimpleDateFormat( "yyyy/MM/dd HH:mm:ss" );

        String date = null;
        try {
            date = sdff.format(sdf.parse( getData(str,7,6,1)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 方法名：getYdz
     * 功能描述：获取源地址
     * @param str：数据字符
     * @return getData()
     * */
    public String getYdz(String str) {
        return getData(str,13,6,0);
    }

    /**
     * 方法名：getMonitoringValue
     * 功能描述：获取检测值
     * @param str：数据字符
     * @param num：字节数
     * @return temp
     * */
    public  String getMonitoringValue(String str,int num){
        String temp = "";
        if(num ==0 ){
            temp =  dataUnit(str,39,1) + "." + dataUnit(str,38,1);
        }else if(num == 1){
            temp = dataUnit(str,66,1) + "." + dataUnit(str,65,1);
        }
        return temp;
    }

    /**
     * 方法名：getMonitoringValue
     * 功能描述：获取电池电压值
     * @param str：数据字符
     * @param num：字节数
     * @return temp
     * */
    public  String getvoltage(String str,int num){
        String temp = "";
        if(num ==0 ){
            temp =  dataUnit(str,41,1) + "." + dataUnit(str,42,1);
        }else if(num == 1){
            temp = dataUnit(str,68,1) + "." + dataUnit(str,69,1);
        }
        return temp ;
    }

}
