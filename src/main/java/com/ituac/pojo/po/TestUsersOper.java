package com.ituac.pojo.po;

import java.util.Date;

import lombok.Data;

/**
 * 【测试实体】用户操作接口历史
 * @author ituac
 */

@Data
public class TestUsersOper {

	/*
	 * 唯一id
	 */
	private String id;
	
	/*
	 * 工号
	 */
	private String jobNumber;
	
	/*
	 * 操作接口
	 */
	private String operationInterfae;
	
	/*
	 * 操作时间
	 */
	private Date operationTime;
	
	/*
	 * 是否操作成功
	 */
	private Boolean operationSuccess;
	
	
}
