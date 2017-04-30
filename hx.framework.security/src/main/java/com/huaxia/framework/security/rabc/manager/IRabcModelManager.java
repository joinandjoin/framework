package com.huaxia.framework.security.rabc.manager;

import java.util.List;

import com.huaxia.framework.security.rabc.model.IRabcModel;
import com.huaxia.framework.security.rabc.model.IRabcRelatModel;

public interface IRabcModelManager {

	void batchSaveOrUpdate(List<IRabcModel> model);
	
	void saveOrUpdate(IRabcModel model);
	
	void batchDelete(List<Long> modelNos);
	
	void delete(long modelNo);
	
	IRabcModel findByKey(long modelNo);
	
	IRabcRelatModel findRelatModelByKey(long modelNo);
	
	List<IRabcModel> findByName(String name);
	
	List<IRabcModel> findByCode(String code);
	
}
