/**
 * 
 */
package com.huaxia.framework.common.enums.privilege;

import java.math.BigInteger;

/**
 * @author shilei
 *
 */
public enum ResourceOperations {

//	READ(1 << 0,"查看，查询，读取，显示"),
//	UPDATE(1 << 1,"修改，更新，写入"),
//	CREATE(1 << 2,"新建，创建，新增"),
//	DELETE(1 << 3,"删除，去掉，隐藏"),
//	PRINT(1 << 4,"打印"),
//	INPUT(1 << 5,"下载，从外系统读取"),
//	OUTPUT(1 << 6,"上传，向外系统发送"),
//	EXECUTE(1 << 7,"运行，执行"),
//	ADMIN(1<<8,"全部");
	READ(1,"查看","查看，查询，读取，显示"),
	UPDATE(2,"修改","修改，更新，写入"),
	CREATE(4,"新增","新建，创建，新增"),
	DELETE(8,"删除","删除，去掉，隐藏"),
	PRINT(16,"打印","打印"),
	INPUT(32,"下载","下载，从外系统读取"),
	OUTPUT(64,"上传","上传，向外系统发送"),
	EXECUTE(128,"执行","运行，执行"),
	ADMIN(256,"完全","全部");
	
	private int val;
	private String text;
	@SuppressWarnings("unused")
	private String desc;
	
	private ResourceOperations(int val,String text,String desc){
		this.val=val;
		this.text=text;
		this.desc=desc;
	}

	public int val() {
		return val;
	}

	public String text() {
		return text;
	}
	
	public String desc() {
		return text;
	}
	
	public static ResourceOperations valueOfKey(int val){
		for (ResourceOperations op : ResourceOperations.values()){
			if (op.val() == val){
				return op;
			}
		}
		return null;
	}
	public static void main(String[] args) {
		int key = ResourceOperations.UPDATE.val()|ResourceOperations.CREATE.val();
		System.out.println(key);
		System.out.println(key&(ResourceOperations.READ.val()));
		int tokey = key&(ResourceOperations.READ.val());
		System.out.println(tokey==(ResourceOperations.READ.val()));
		System.out.println(ResourceOperations.valueOfKey(64).name());
		BigInteger num = new BigInteger("0"); 
		for (int i=1;i<=195;i++){
			num = num.setBit(i);
		}
		System.out.println(num);
		System.out.println(num.testBit(2));  
        System.out.println(num.testBit(196));  
        System.out.println(num.testBit(3));
		
	}
}
