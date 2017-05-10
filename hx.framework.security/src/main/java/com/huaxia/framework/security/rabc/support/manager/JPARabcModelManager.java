package com.huaxia.framework.security.rabc.support.manager;

import org.springframework.beans.factory.annotation.Autowired;

import com.huaxia.framework.common.database.repository.ICommonDAO;
import com.huaxia.framework.common.enums.privilege.RabcModelType;
import com.huaxia.framework.security.rabc.manager.IRabcModelManager;

public class JPARabcModelManager implements IRabcModelManager {
	
	@Autowired
	private ICommonDAO commonDao;
	
	@Override
	public void createOrUpdateModel(RabcModelType type, String modelCode, String modelName, long modelCate,
			String modelValue) {
		createOrUpdateParty(modelCode, modelName, modelCate, modelValue);
		
	}

	private void createOrUpdateParty(String modelCode, String modelName, long modelCate,
			String modelValue){
		
	}
}
