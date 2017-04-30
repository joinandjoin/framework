/**
 * 
 */
package com.huaxia.framework.security.rabc.manager;

import java.util.List;
import java.util.Map;

import com.huaxia.framework.common.enums.privilege.RabcModelType;
import com.huaxia.framework.security.rabc.model.IRabcModel;
import com.huaxia.framework.security.rabc.model.IRabcRelatModel;

/**
 * @author shilei
 *
 */
public interface IPrivilegeManager {

	
/*	//----------------------------session 操作----------------------------------------
	void createOrUpdateSession(IRabcModel session);
	void deleteSession(long sessionNo);
	IRabcModel findSession(String sessionCode);
	//----------------------------party 操作-------------------------------------
	void createOrUpdateParty(IRabcModel party);
	void deleteParty(long partyNo);
	IRabcModel findPartyById(long partyNo);
	//----------------------------group 操作------------------------------------------
	void createOrUpdateGroup(IRabcModel group);
	void deleteGroup(long groupNo);
	IRabcModel findGroupById(long groupNo);
	//----------------------------role 操作-------------------------------------------
	void createOrUpdateRole(IRabcModel role);
	void deleteRole(long roleNo);
	IRabcModel findRoleById(long roleNo);
	//----------------------------resource 操作---------------------------------------
	void createOrUpdateResource(IRabcModel resource);
	void deleteResource(long resourceNo);
	IRabcModel findResourceById(long resource);
	//----------------------------operation 操作--------------------------------------
	void createOrUpdateOperation(IRabcModel operation);
	void deleteOperation(long operationNo);
	IRabcModel findOperationById(long operationNo);
	//----------------------------constraint 操作-------------------------------------
	void createOrUpdateConstraint(IRabcModel constraint);
	void deleteConstraint(long constraintNo);
	IRabcModel findConstraintById(long constraintNo);*/
	
	//----------------------------session,party,group,,role,resource,operation,constraint操作----------------------------------------
	void createOrUpdateModel(IRabcModel model,RabcModelType type);
	void batchCreateOrUpdateModel(Map<RabcModelType,IRabcModel>  models);
	void batchCreateOrUpdateModel(List<IRabcModel>  models,List<RabcModelType> types);
	void batchCreateOrUpdateModel(List<IRabcModel>  models,RabcModelType type);
	void deleteModel(long modelNo);
	void batchDeleteModel(List<Long> modelNos);
	
	IRabcModel findModelByKey(long modelNo,RabcModelType type);
	List<IRabcModel> findModelByCode(String modelCode,RabcModelType type);
	List<IRabcModel> findModelByName(String modelName,RabcModelType type);
	
	//-party:group,party:role,party-group:role,role:permission,permission|role:group,party-group:role-group,party:constraint,role:constraint 操作-
	void createOrUpdateRelateModel(IRabcRelatModel model);
	void createOrUpdateRelateModel(long leftModelNo,long rightModelNo,RabcModelType relatType);
	void batchCreateOrUpdateRelateModel(List<Long> leftModelNos,List<Long> rightModelNos,List<RabcModelType> relatTypes);
	void batchCreateOrUpdateRelateModel(List<IRabcRelatModel> model);
	
	void deleteRelateModel(long relateModelNo);
	void deleteRelateModel(long leftModelNo, long rigthModelNo);
	void batchDeleteRelateModel(List<Long> relateModelNos);
	void batchDeleteRelateModel(List<Long> leftModelNo, List<Long> rigthModelNo);
	
	
	IRabcRelatModel findRelateModelByKey(long relateModelNo,RabcModelType type);
	List<IRabcRelatModel> findRelateModelByCode(String relateModelCode,RabcModelType type);
	List<IRabcRelatModel> findRelateModelByName(String relateModelName,RabcModelType type);
	List<IRabcRelatModel> findRelateModelByLeftRelatNo(long relatNo,RabcModelType type);
	List<IRabcRelatModel> findRelateModelByRightRelatNo(long relatNo,RabcModelType type);
	List<IRabcRelatModel> findRelateModelByLeftRelatNos(List<Long> relatNos,RabcModelType type);
	List<IRabcRelatModel> findRelateModelByRightRelatNos(List<Long> relatNos,RabcModelType type);
	
	//----------------------------permission 操作-------------------------------------
	
	//----------------------------party-group 操作------------------------------------
	
	//----------------------------role-group 操作-------------------------------------
	
	//----------------------------party-group:role-group 操作-------------------------
	
	//----------------------------party-constraint 操作-------------------------------
	
	//----------------------------role-constraint 操作--------------------------------
	
	//----------------------------role-permission 操作--------------------------------
	
	//----------------------------party-role 操作-------------------------------------
	
	//-----------------------------------授权操作--------------------------------------
	
	
//	/**
//	 * 获取授权对象的当前所属Session
//	 * @param partyNo
//	 * @return
//	 */
//	IRabcModel getPartySession(long partyNo);
//	
//	/**
//	 * 获取授权对象所属分组
//	 * @param partyNo
//	 * @return
//	 */
//	List<IRabcModel> getPartyGroups(long partyNo);
//	
//
//	/**
//	 * 获取授权对象所属分组的所有角色
//	 * @param groupNo
//	 * @return
//	 */
//	List<IRabcModel> getPartyGroupRoles(long groupNo);
//	
//	/**
//	 * 获取授权对象所属分组的所有角色分组
//	 * @param groupNo
//	 * @return
//	 */
//	List<IRabcRelatModel> getPartyGroupRoleGroups(long groupNo);
//	
//	/**
//	 * 获取授权对象所有角色
//	 * @param partyNo
//	 * @return
//	 */
//	List<IRabcModel> getPartyRoles(long partyNo);
//	
//	/**
//	 * 获取授权对象的所有约束
//	 * @param partyNo
//	 * @return
//	 */
//	List<IRabcModel> getPartyConstraints(long partyNo);
//	
//	/**
//	 * 获取角色的所有约束
//	 * @param roleNo
//	 * @return
//	 */
//	List<IRabcModel> getRoleConstraints(long roleNo);
//	
//	/**
//	 * 获取授权对象的所有权限
//	 * @param partyNo
//	 * @return
//	 */
//	List<IRabcRelatModel> getPartyPermissions(long partyNo);
//	
//	/**
//	 * 创建或者更新授权对象
//	 * @param party
//	 */
//	void createOrUpdateParty(IRabcModel party);
//	
//	/**
//	 * 删除授权对象
//	 * @param partyNo
//	 */
//	void deleteParty(long partyNo);
	
	
	//-----------------------------------------------------
//	void createOrUpdateGroup(IRabcModel group);
//	
//	void deleteGroup(long groupNo);
/*	
	void createOrUpdatePartyGroup(IRabcRelatModel model);
	
	void deletePartyGroup(long groupNo,long partyNo);
	
	void createOrUpdateRoleGroup(IRabcRelatModel model);
	
	void deleteRoleGroup(long groupNo,long roleNo);
	
	void batchCreateOrUpdateGroup(List<IRabcModel> groups);
	
	void batchCreateOrUpdatePartyGroup(List<IRabcRelatModel> models);
	
	void batchCreateOrUpdateRoleGroup(List<IRabcRelatModel> models);
	
	//-------------------------------------------------------------
*/	
}
