package scsm.mapreduce;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class XfReducer extends Reducer<Text, Text, Text, Text> {
	@Override
	protected void reduce(Text key, Iterable<Text> values, Reducer<Text, Text, Text, Text>.Context context) throws IOException, InterruptedException {
		//开始遍历
		Iterator<Text> iterator = values.iterator();
		int sum =0;
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy/MM/dd HH:mm:ss" );
		for (Text value : values) {
			try {
				Date start = sdf.parse("2019/09/01 00:00:00");
				Date end = sdf.parse("2019/09/30 23:59:59");
				Date date = sdf.parse(value.toString());
				if (date.after(start)&&date.before(end)){
					sum++;
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		System.out.println("9月火警次数:" + sum);

	}
}
