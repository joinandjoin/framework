/**
 * 
 */
package com.huaxia.framework.security.rabc.manager;

import java.util.List;

import com.huaxia.framework.security.rabc.model.IRabcModel;
import com.huaxia.framework.security.rabc.model.IRabcRelatModel;

/**
 * @author shilei
 *
 */
public interface IPrivilegeManager {

	//----------------------------session 操作----------------------------------------
	
	//----------------------------party 操作-------------------------------------
	
	//----------------------------group 操作------------------------------------------
	
	//----------------------------constraint 操作-------------------------------------
	
	//----------------------------role 操作-------------------------------------------
	
	//----------------------------operation 操作--------------------------------------
	
	//----------------------------resource 操作---------------------------------------
	
	//----------------------------permission 操作-------------------------------------
	
	//----------------------------party-group 操作------------------------------------
	
	//----------------------------role-group 操作-------------------------------------
	
	//----------------------------party-group:role-group 操作-------------------------
	
	//----------------------------party-constraint 操作-------------------------------
	
	//----------------------------role-constraint 操作--------------------------------
	
	//----------------------------role-permission 操作--------------------------------
	
	//-----------------------------------授权操作--------------------------------------
	
	
	/**
	 * 获取授权对象的当前所属Session
	 * @param partyNo
	 * @return
	 */
	IRabcModel getPartySession(long partyNo);
	
	/**
	 * 获取授权对象所属分组
	 * @param partyNo
	 * @return
	 */
	List<IRabcModel> getPartyGroups(long partyNo);
	

	/**
	 * 获取授权对象所属分组的所有角色
	 * @param groupNo
	 * @return
	 */
	List<IRabcModel> getPartyGroupRoles(long groupNo);
	
	/**
	 * 获取授权对象所属分组的所有角色分组
	 * @param groupNo
	 * @return
	 */
	List<IRabcRelatModel> getPartyGroupRoleGroups(long groupNo);
	
	/**
	 * 获取授权对象所有角色
	 * @param partyNo
	 * @return
	 */
	List<IRabcModel> getPartyRoles(long partyNo);
	
	/**
	 * 获取授权对象的所有约束
	 * @param partyNo
	 * @return
	 */
	List<IRabcModel> getPartyConstraints(long partyNo);
	
	/**
	 * 获取角色的所有约束
	 * @param roleNo
	 * @return
	 */
	List<IRabcModel> getRoleConstraints(long roleNo);
	
	/**
	 * 获取授权对象的所有权限
	 * @param partyNo
	 * @return
	 */
	List<IRabcRelatModel> getPartyPermissions(long partyNo);
	
	/**
	 * 创建或者更新授权对象
	 * @param party
	 */
	void createOrUpdateParty(IRabcModel party);
	
	/**
	 * 删除授权对象
	 * @param partyNo
	 */
	void deleteParty(long partyNo);
	
	
	//-----------------------------------------------------
	void createOrUpdateGroup(IRabcModel group);
	
	void deleteGroup(long groupNo);
	
	void createOrUpdatePartyGroup(IRabcRelatModel model);
	
	void deletePartyGroup(long groupNo,long partyNo);
	
	void createOrUpdateRoleGroup(IRabcRelatModel model);
	
	void deleteRoleGroup(long groupNo,long roleNo);
	
	void batchCreateOrUpdateGroup(List<IRabcModel> groups);
	
	void batchCreateOrUpdatePartyGroup(List<IRabcRelatModel> models);
	
	void batchCreateOrUpdateRoleGroup(List<IRabcRelatModel> models);
	
	//-------------------------------------------------------------
	
}
