package scsm.data.util;

public class AlarmData {

    //类型，用于标识心跳还是报警 02（心跳）
    private String type;
    //报警时间 "2019-11-25 11:17:00"
    private String alarmTime;
    //源地址，转码 "00976030025465"
    private String ydz;
    //同一源地址中，探测器唯一编码
    private String bjdz;
    //系统类型
    private String systemType;
    //部件类型
    private String bjType;
    //报警类型，0002（报警）0004（故障）
    private String alarmType;
    //故障类型（火警不判）
    private String faultType;
    //监测类型
    private String monitorType;
    //监测数值
    private String monitorValue;
    //信号
    private String signal;
    //电池电压值
    private String voltage;
    //汉字说明
    private String remarks;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAlarmTime() {
        return alarmTime;
    }

    public void setAlarmTime(String alarmTime) {
        this.alarmTime = alarmTime;
    }

    public String getYdz() {
        return ydz;
    }

    public void setYdz(String ydz) {
        this.ydz = ydz;
    }

    public String getBjdz() {
        return bjdz;
    }

    public void setBjdz(String bjdz) {
        this.bjdz = bjdz;
    }

    public String getSystemType() {
        return systemType;
    }

    public void setSystemType(String systemType) {
        this.systemType = systemType;
    }

    public String getBjType() {
        return bjType;
    }

    public void setBjType(String bjType) {
        this.bjType = bjType;
    }

    public String getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType;
    }

    public String getFaultType() {
        return faultType;
    }

    public void setFaultType(String faultType) {
        this.faultType = faultType;
    }

    public String getMonitorType() {
        return monitorType;
    }

    public void setMonitorType(String monitorType) {
        this.monitorType = monitorType;
    }

    public String getMonitorValue() {
        return monitorValue;
    }

    public void setMonitorValue(String monitorValue) {
        this.monitorValue = monitorValue;
    }

    public String getSignal() {
        return signal;
    }

    public void setSignal(String signal) {
        this.signal = signal;
    }

    public String getVoltage() {
        return voltage;
    }

    public void setVoltage(String voltage) {
        this.voltage = voltage;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "AlarmData{" +
                "type='" + type + '\'' +
                ", alarmTime='" + alarmTime + '\'' +
                ", ydz='" + ydz + '\'' +
                ", bjdz='" + bjdz + '\'' +
                ", systemType='" + systemType + '\'' +
                ", bjType='" + bjType + '\'' +
                ", alarmType='" + alarmType + '\'' +
                ", faultType='" + faultType + '\'' +
                ", monitorType='" + monitorType + '\'' +
                ", monitorValue='" + monitorValue + '\'' +
                ", signal='" + signal + '\'' +
                ", voltage='" + voltage + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
