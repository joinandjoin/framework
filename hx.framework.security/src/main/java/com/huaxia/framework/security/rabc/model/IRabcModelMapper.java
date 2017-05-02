package com.huaxia.framework.security.rabc.model;

import com.huaxia.framework.common.enums.privilege.RabcModelType;

public interface IRabcModelMapper<T> {

	void fromRabcModel(IRabcModel oriModel, T target);
	
	IRabcModel toRabcModel(RabcModelType modelType,T source);

}
