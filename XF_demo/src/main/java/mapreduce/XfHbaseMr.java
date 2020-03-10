package scsm.mapreduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import scsm.storm.Constants;

public class XfHbaseMr {
	public static void main(String[] args) throws Exception {
		//获取配置文件
		Configuration configuration = new Configuration(true);
		//本地模式运行
		configuration.set("mapreduce.framework.name", "local");
		//设置HBase的管理集群
		configuration.set("hbase.zookeeper.quorum", Constants.HBASE_ZOOKEEPER_LIST);
		//创建JOB
		Job job = Job.getInstance(configuration);
		//开始设置Job的参数
		job.setJarByClass(XfHbaseMr.class);
		job.setJobName("hbaseToHdfs");
		//设置和Mapper相关的参数
		String table = "t_alarm";
		Scan scan = new Scan();
		scan.setCaching(4);
		TableMapReduceUtil.initTableMapperJob(table, scan, XfMapper.class, Text.class, Text.class, job);
		//设置和Reducer相关的参数
		FileOutputFormat.setOutputPath(job, new Path("/user/root/weather" + System.currentTimeMillis()));
		job.setReducerClass(XfReducer.class);

		//提交任务等待完成
		job.waitForCompletion(true);

	}
}
