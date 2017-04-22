package org.spring.boot.hxframework.autoconfigure.jdbc;

import java.sql.Timestamp;

import com.huaixa.framework.common.database.repository.ICommonDAO;
import com.huaixa.framework.common.idgenerator.ITimeSynchronizer;
import com.huaixa.framework.common.utils.DateUtils;


public class TimeSynchronizerImpl implements ITimeSynchronizer{

	private ICommonDAO commonDao;
	
	@Override
	public long dateTimeFromLocal() {
		return DateUtils.nowDate().getTime();
	}

	@Override
	public long dateTimeFromDataBase() {
		if (commonDao != null){
			try {
				return commonDao.getSysDate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return this.dateTimeFromLocal();
	}

	@Override
	public long dateTimeFormFixStr(String dateTimeChars,String pattern) {
		return DateUtils.getDate(dateTimeChars, pattern).getTime();
	}

	@Override
	public long dateTimeFormFixDate(Timestamp dateTime) {
		return dateTime.getTime();
	}

	public ICommonDAO getCommonDao() {
		return commonDao;
	}

	public void setCommonDao(ICommonDAO commonDao) {
		this.commonDao = commonDao;
	}

	
}
