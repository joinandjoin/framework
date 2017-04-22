package com.huaixa.framework.common.idgenerator;

import java.sql.Timestamp;
/**
 * 序列值的生成，依赖时间戳，时间戳错乱
 * 会导致序列重复，因此需要有时间同步
 * 的操作，此接口封装了时间同步的途径
 * 1、本地方式同步时间
 * 2、通过数据库时间同步系统时间
 * 3、指定一个时间的String表示，来同步时间
 * 4、指定一个时间的Timestamp表示，来同步时间
 * @author shilei
 *
 */
public interface ITimeSynchronizer {

	long dateTimeFromLocal();
	
	long dateTimeFromDataBase();
	
	long dateTimeFormFixStr(String dateTimeChars,String pattern);
	
	long dateTimeFormFixDate(Timestamp dateTime);
}
