package scsm.mapreduce;

import java.io.IOException;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class XfMapper extends TableMapper<Text, Text> {

	private static final byte[] CF = Bytes.toBytes("info");
	private static final byte[] QUALIFIER = Bytes.toBytes("alarmTime");

	@Override
	protected void map(ImmutableBytesWritable key, Result value, Mapper<ImmutableBytesWritable, Result, Text, Text>.Context context) throws IOException, InterruptedException {
		//获取到RowKey
		String rowKey = Bytes.toString(key.get());
		//获取具体数据
		Cell cell = value.getColumnLatestCell(CF, QUALIFIER);
		//获取到对象
		String alarmTime = Bytes.toString(CellUtil.cloneValue(cell));
		//将数据写出
		context.write(new Text(rowKey), new Text(alarmTime));
	}
}