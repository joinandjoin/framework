package com.huaxia.framework.security.rabc.manager;

import com.huaxia.framework.common.enums.privilege.RabcModelType;

public interface IRabcModelManager {
	
	void createOrUpdateModel(RabcModelType type,String modelCode, String modelName, long modelCate, 
			String modelValue);
	
	//----------------------------session,party,group,,role,resource,operation,constraint操作----------------------------------------
//		void createOrUpdateModel(IRabcModel model,RabcModelType type);
//		void batchCreateOrUpdateModel(Map<RabcModelType,IRabcModel>  models);
//		void batchCreateOrUpdateModel(List<IRabcModel>  models,List<RabcModelType> types);
//		void batchCreateOrUpdateModel(List<IRabcModel>  models,RabcModelType type);
//		void deleteModel(long modelNo);
//		void batchDeleteModel(List<Long> modelNos);
//		
//		IRabcModel findModelByKey(long modelNo,RabcModelType type);
//		List<IRabcModel> findModelByCode(String modelCode,RabcModelType type);
//		List<IRabcModel> findModelByName(String modelName,RabcModelType type);
//		
//		//-party:group,party:role,party-group:role,role:permission,permission|role:group,party-group:role-group,party:constraint,role:constraint 操作-
//		void createOrUpdateRelateModel(IRabcRelatModel model);
//		void createOrUpdateRelateModel(long leftModelNo,long rightModelNo,RabcModelType relatType);
//		void batchCreateOrUpdateRelateModel(List<Long> leftModelNos,List<Long> rightModelNos,List<RabcModelType> relatTypes);
//		void batchCreateOrUpdateRelateModel(List<IRabcRelatModel> model,List<RabcModelType> relatTypes);
//		
//		void deleteRelateModel(long relateModelNo);
//		void deleteRelateModel(long leftModelNo, long rigthModelNo);
//		void batchDeleteRelateModel(List<Long> relateModelNos);
//		void batchDeleteRelateModel(List<Long> leftModelNo, List<Long> rigthModelNo);
//		
//		IRabcRelatModel findRelateModelByKey(long relateModelNo,RabcModelType type);
//		List<IRabcRelatModel> findRelateModelByCode(String relateModelCode,RabcModelType type);
//		List<IRabcRelatModel> findRelateModelByName(String relateModelName,RabcModelType type);
//		List<IRabcRelatModel> findRelateModelByLeftRelatNo(long relatNo,RabcModelType type);
//		List<IRabcRelatModel> findRelateModelByRightRelatNo(long relatNo,RabcModelType type);
//		List<IRabcRelatModel> findRelateModelByLeftRelatNos(List<Long> relatNos,RabcModelType type);
//		List<IRabcRelatModel> findRelateModelByRightRelatNos(List<Long> relatNos,RabcModelType type);

}
