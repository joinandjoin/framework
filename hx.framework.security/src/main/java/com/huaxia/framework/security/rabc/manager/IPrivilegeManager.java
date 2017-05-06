/**
 * 
 */
package com.huaxia.framework.security.rabc.manager;

import java.util.List;

import com.huaxia.framework.common.enums.privilege.RabcModelType;
import com.huaxia.framework.security.rabc.model.IRabcModel;

/**
 * @author shilei
 *
 */
public interface IPrivilegeManager {
	
	/**
	 * 
	 * @param type 仅限于 PARTY("授权对象"),ROLE("角色"),RESOURCE("资源"),OPERATION("操作"),GROUP("分组"),CONSTRAINT("约束"),
	 * @param modelCode
	 * @param modelName
	 * @param modelType
	 * @param modelValue
	 */
	void createOrUpdateModel(RabcModelType type,String modelCode, String modelName, long modelType, 
			String modelValue);
	void deleteModel(long modelNo);
	IRabcModel findModelById(long modelNo);
	IRabcModel findModelByCode(String modelCode);
	List<IRabcModel> findModelsByName(String modelName);
	List<IRabcModel> findModelsByType(long modelType);
	
	//----------------------------session 操作----------------------------------------
	void createOrUpdateSession(long partyNo,String sessionCode);
	void deleteSession(long sessionNo);
	IRabcModel findSession(String sessionCode);
	
/*	//----------------------------party 操作-------------------------------------
	void createOrUpdateParty(String paryCode, String partyName, long partyType);
	void deleteParty(long partyNo);
	IRabcModel findPartyById(long partyNo);
	IRabcModel findPartyByCode(String paryCode);
	List<IRabcModel> findPartysByName(String partyName);
	List<IRabcModel> findPartysByType(long partyType);
	//----------------------------role 操作-------------------------------------------
	void createOrUpdateRole(String roleCode,String roleName);
	void deleteRole(long roleNo);
	IRabcModel findRoleById(long roleNo);
	IRabcModel findRoleByCode(String roleCode);
	List<IRabcModel> findRolesByName(String roleName);
	//----------------------------operation 操作--------------------------------------
	void createOrUpdateOperation(String operationCode, String operationName);
	void deleteOperation(long operationNo);
	IRabcModel findOperationById(long operationNo);
	IRabcModel findOperationByCode(String operationCode);
	List<IRabcModel> findOperationsByName(String operationName);
	//----------------------------resource 操作---------------------------------------
	void createOrUpdateResource(String resourceCode, String resourceName, long resourceType);
	void deleteResource(long resourceNo);
	IRabcModel findResourceById(long resourceNo);
	IRabcModel findResourceByCode(String resourceCode);
	List<IRabcModel> findResourcesByName(String resourceName);
	List<IRabcModel> findResourcesByType(long resourceType);
	//----------------------------group 操作------------------------------------------
	void createOrUpdateGroup(String groupCode, String groupName, long groupType);
	void deleteGroup(long groupNo);
	IRabcModel findGroupById(long groupNo);
	IRabcModel findGroupByCode(String groupCode);
	List<IRabcModel> findGroupsByName(String groupName);
	List<IRabcModel> findGroupsByType(long groupType);
	//----------------------------constraint 操作-------------------------------------
	void createOrUpdateConstraint(String constraintCode, String constraintName, long constraintType, 
			String constraintValue);
	void deleteConstraint(long constraintNo);
	IRabcModel findConstraintById(long constraintNo);
	IRabcModel findConstraintByCode(String constraintCode);
	List<IRabcModel> findConstraintsByName(String constraintName);
	List<IRabcModel> findConstraintsByType(long constraintType);*/

	/*//----------------------------session,party,group,,role,resource,operation,constraint操作----------------------------------------
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
	*/
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
