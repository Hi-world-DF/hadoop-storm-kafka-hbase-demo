package scsm.data;

import scsm.data.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 方法名：MockData
 * 功能描述：模拟数据
 *
 * */
public class MockData {

    public static void main(String args[]){
//        System.out.println(new MockData().mock());
//        String s = "4040010001000f000f04032800150346271300000000000112000203018000100000000108c800ba631a000000XX2323";
//        String str = "4040000901002E070A190B13009A243BE6360000000000013000020201010081000003650002000000000000000000000000000000000000000000000000000000000000002E070A190B13002323";
//        System.out.println(str);
//        System.out.println(str.length());
        String tmp = "404001000100000000000000000000016AA7000000000001120002030101025C0000002B02FF01000000000000B92323";
        System.out.println(tmp);
        System.out.println(tmp.length());

        System.out.println("====================");
        MockData md = new MockData();
        AnalysData ad = new AnalysData();
        String data = "";
        String type = "";
        while (1==1){
            data = md.mock();
            type = ad.filterData(data);
            System.out.println(type+"//"+data.length());
            System.out.println(data);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public List<String> mock(int i) {
        List<String> dataList = new ArrayList();
        int count = 0;
        while (count++ < i) {
            dataList.add(mock());
        }
        return dataList;
    }

    public String mock(){
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        /**
         * 1,2字节:启动符
         */
        String bt = "40" + "40";
        /**
         * 3,4字节:业务流水号
         *
         * */
        String ls = "01" + "00";
        /**
         * 5,6字节:协议版本号
         * */
        String bb = "01" + "00";
        /**
         * 7-12字节:时间标签
         * */
        String YY = StringUtils.fulfuill(2, Integer.toHexString(random.nextInt(20)));
        String MM = StringUtils.fulfuill(2, Integer.toHexString(random.nextInt(12)));
        String DD = StringUtils.fulfuill(2, Integer.toHexString(random.nextInt(30)));
        String hh = StringUtils.fulfuill(2, Integer.toHexString(random.nextInt(24)));
        String mm = StringUtils.fulfuill(2, Integer.toHexString(random.nextInt(60)));
        String ss = StringUtils.fulfuill(2, Integer.toHexString(random.nextInt(60)));
        String alarmTime = YY + MM + DD + hh + mm + ss;

        /**
         * 13-18字节:IMEI号,源地址
         * */
        String ydz = StringUtils.fulfuill(12, (random.nextInt((int) Math.pow(10, 15))) + "");
        /**
         * 19-24字节:目的地址
         * */
        String bjdz = "00" + "00" + "00" + "00" + "00" + "01";
        /**
         * 25,26字节:应用数据单元长度
         * */
        String lg = "12" + "00";
        /**
         * 27字节:命令字节
         * */
        String cmd = "02";
        /**
         * 28字节:类型标注 type
         *  03:心跳机制
         *  02:火警及故障上传
         * */
        String[] type = {"03","02"};
        /**
         * 29字节:数量
         * */
        String quantity = "01";
        /**
         * 30字节:系统类型 systemType
         * 01H：可燃气体探测器/压力/液位
         * 80H：智慧用电
         * 81H：独立烟感探测器
         */
        String[] systemType = {"01", "80", "81"};
        /**
         * 31字节:系统地址
         * */
        String sysAdd = "00";
        /**
         * 32字节:部件类型 bjType
         * 81H：独立烟感探测器
         * 10H：电气火灾报警器
         * 0CH：可燃气体探测器
         * 5BH：压力
         * 5CH：液位
         * */
        String[] bjType = {"81", "10", "0C", "5B", "5C"};
        /**
         * 33-36字节:部件地址
         * */
        String unitAdd = "00" + "00" + "00" + "01";
        /**
         * 37字节:监测类型值单位
         * 64字节:监测类型值单位
         * */
        String[] monitorType = {"02", "03", "05", "06", "08", "09"};
        /**
         * 38-39字节:监测数值
         * 65-66字节:监测数值
         * */
        String monitorValue = StringUtils.fulfuill(2, Integer.toHexString(random.nextInt(256))) + "00";
        /**
         * 40字节:信号强度
         * 67字节:信号强度
         * */
        String signal = StringUtils.fulfuill(2, Integer.toHexString(random.nextInt(256)));
        /**
         * 41,42字节:电池电压值
         * 68-69字节:电池电压值
         * */
        String voltage = StringUtils.fulfuill(2, Integer.toHexString(random.nextInt(256))) + StringUtils.fulfuill(2, Integer.toHexString(random.nextInt(256)));
        /**
         * 43-45字节:保留字
         * 70-75字节:保留字
         * */
        String keyWord01 = "00" + "00" + "00";
        String keyWord02 = "00" + "00" + "00" + "00" + "00" + "00";
        /**
         * 46字节:校验和
         * 76字节:校验和
         * */
        String verification = "00";
        /**
         * 47-48字节:结束符
         * 77-78字节:结束符
         * */
        String end = "23" + "23";
        /**
         * 37-38字节:事件类型 alarmType
         * 报警0002
         * 故障0004
         * */
        String[] alarmType = {"0002","0004"};
        /**
         * 39字节:故障类型
         * 脱网：01H
         * 欠压：02H
         * 污染：03H
         * 部件损坏：04H
         * 其它故障、非故障事件：00H
         * */
        String[] faultType = {"01","02","03","04","00"};
        /**
         *40-63字节:说明
         **/
        String remarks = "00"+"00"+"00"+"00"+"00"+"00"+"00"+"00"+"00"+"00"+"00"+"00"+"00"+"00"+"00"+"00"+"00"+"00"+
                "00"+"00"+"00"+"00"+"00"+"00";

        String temp = type[random.nextInt(2)];
        sb.append(bt).append(ls).append(bb).append(alarmTime).append(ydz).append(bjdz).append(lg)
                .append(cmd).append(temp).append(quantity).append(systemType[random.nextInt(3)]).append(sysAdd)
                .append(bjType[random.nextInt(5)]).append(unitAdd);
        if (temp.equals("03")) {
            sb.append(monitorType[random.nextInt(6)]).append(monitorValue)
                    .append(signal).append(voltage).append(keyWord01)
                    .append(verification).append(end);
        }else if (temp.equals("02")){
            sb.append(alarmType[random.nextInt(2)]).append(faultType[random.nextInt(5)]).append(remarks)
                    .append(monitorType[random.nextInt(6)]).append(monitorValue)
                    .append(signal).append(voltage).append(keyWord02)
                    .append(verification).append(end);
        }

        String data =sb.toString();
        return data;
    }

}
