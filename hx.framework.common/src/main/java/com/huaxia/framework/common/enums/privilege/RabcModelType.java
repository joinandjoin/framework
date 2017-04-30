package com.huaxia.framework.common.enums.privilege;

public enum RabcModelType {
	PARTY("授权对象"),
	ROLE("角色"),
	PERMIS("权限"),
	SESSION("会话"),
	RESOURCE("资源"),
	OPERATION("操作"),
	GROUP("分组"),
	CONSTRAINT("约束"),
	PATY_ROLE("授权对象的角色"),
	ROLE_PERMIS("角色的权限"),
	PARTY_GROUP("授权对象分组"),
	ROLE_GROUP("角色分组"),
	PARTY_GROUP_ROLE("分组的角色"),
	PARTY_GROUP_ROLE_GROUP("分组的权限组"),
	PARTY_ROLE_GROUP("授权对象角色组"),
	PARTY_CONSTRAINT("授权对象约束"),
	ROLE_CONSTRAINT("角色约束");
	
	private String text;
	
	private RabcModelType(String text){
		this.text=text;
	}
	
	public String text() {
		return text;
	}
}
