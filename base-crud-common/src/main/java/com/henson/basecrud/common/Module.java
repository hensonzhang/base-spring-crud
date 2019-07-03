package com.henson.basecrud.common;

/**
 * 
 * @ClassName: Module
 * @Description: 每个子系统都有一个对应的系统编码，异常抛出过程中需要携带系统编码
 * @author hensonzhangzs@gmail.com
 * @date 2016年8月10日 下午1:21:56
 *
 */
public enum Module {

	DEMO("demo系统", 9999, "DEMO"), COMMON("common公共", 1001, "DEMO");

	//系统模块名称
	private String mName;
	//系统编码：业务模块1000起，后台管理3000起
	private Integer sysCode;
	//系统模块英文model名称
	private String moduleName;
	
	Module(String name,Integer code,String moduleName){
		this.sysCode=code;
		this.mName=name;
		this.moduleName=moduleName;
	}
	public String getMName(){
		return this.mName;
	}
	public Integer getSysCode(){
		return this.sysCode;
	}
	
	/**
	 * @return the moduleName
	 */
	public String getModuleName() {
		return moduleName;
	}
	//根据系统编码获得模块定义
	public static Module getModuleByCode(int code){
		for (Module module : Module.values()) {
			if(module.getSysCode()==code){
				return module;
			}
		}		
		return null;
	}

	@Override
	public String toString() {
		return "[" +
				"'" + mName +
				"(" + sysCode +
				")' '" + moduleName + '\'' +
				']'+"  ";
	}
}
