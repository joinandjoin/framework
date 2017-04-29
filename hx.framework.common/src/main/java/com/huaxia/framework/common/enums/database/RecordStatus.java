/**
 * 
 */
package com.huaxia.framework.common.enums.database;

/**
 * @author shilei
 *
 */
public enum RecordStatus {
	VALID("001"),
	INVALID("000");
	
	private String code;
	
	private RecordStatus(String code){
		this.code = code;
	}

	public String code() {
		return code;
	}
}
