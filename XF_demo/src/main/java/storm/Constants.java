package scsm.storm;

/**
 * 端口配置
 */
public class Constants {

    //本机(测试环境hosts已配置:192.168.120.64 scnode01....)
    public static final String HBASE_ZOOKEEPER_LIST = "scnode01:2181,scnode02:2181,scnode03:2181";

    public static final String KAFKA_ZOOKEEPER_LIST = "scnode01:2181,scnode02:2181,scnode03:2181";

    public static final String BROKER_LIST = "scnode01:9092,scnode02:9092,scnode03:9092";

    public static final String ZOOKEEPERS = "scnode01,scnode02,scnode03";

    //测试环境
//    public static final String HBASE_ZOOKEEPER_LIST = "192.168.120.64:2181,192.168.120.65:2181,192.168.120.66:2181";
//
//    public static final String KAFKA_ZOOKEEPER_LIST = "192.168.120.64:2181,192.168.120.65:2181,192.168.120.66:2181";
//
//    public static final String BROKER_LIST = "192.168.120.64:9092,192.168.120.65:9092,192.168.120.66:9092";
//
//    public static final String ZOOKEEPERS = "192.168.120.64,192.168.120.65,192.168.120.66";

    public static final String ServicePost ="8585";

}
