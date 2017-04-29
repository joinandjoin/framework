package com.huaxia.framework.security.rabc.manager;

import java.util.List;

import com.huaxia.framework.security.rabc.model.IRabcModel;
import com.huaxia.framework.security.rabc.model.IRabcRelatModel;

public interface IRabcModelManager {

	void saveOrUpdate(IRabcModel model);
	
	void delete(IRabcModel model);
	
	void delete(long modelNo);
	
	IRabcModel findByKey(long modelNo);
	
	IRabcRelatModel findRelatModelByKey(long modelNo);
	
	List<IRabcModel> findByName(String name);
	
	List<IRabcModel> findByCode(String code);
	
}
