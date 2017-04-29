package com.huaxia.framework.security.rabc.model;

/**
 * 权限对象抽象结构
 * 
 * @author shilei
 *
 */
public interface IRabcConstraintModel extends IRabcModel {
	String constraint(String constraint);
}
