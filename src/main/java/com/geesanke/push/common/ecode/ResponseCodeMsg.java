package com.geesanke.push.common.ecode;

public enum ResponseCodeMsg {
	
	SUCCESS("0000","请求成功"),
	FAILURE("0001","请求失败"),
	SYSTEM_ERROR("1001","系统异常"),
	/*网络异常*/
	INTERNET_ERROR("2001","网络异常"),
	/*文件*/
	FILE_NOT_FOUND("3001","文件不存在"),
	
	/*消息*/
	MESSAGE_SEND_ERROR("6001","消息发送失败"),
	JPMSG_SEND_ERROR("6002","极光推送失败"),
	SMSMSG_SEND_ERROR("6003","短信发送失败"),

	/*线程*/
	RUNNABLE_ERRO("7001","未知任务"),
	RUNNABLE_NOTHING("7002","该对象不是线程"),
	/*参数*/
	PARAMETER_ILLEGAL("8001","参数不合法"),
	PARAMETER_ISNULL("8002","参数为空"),
	PARAMETER_NEED_ISNULL("8003","必要参数为空"),
	
	/*验证码*/
	RADOM_VALIDATE_FAILURE("9001","验证失败"),
	RADOM_SENDING_FAILURE("9002","验证码生成失败"),
	RADOM_PAST("9003","验证码过期"),

	/*参数注入失败*/
	PROPER_INJECT_ERRO("10001","参数注入失败"),
    JPUSH_CLIENT_NOT_FOUND("10002","极光推送客户端生成失败"),
    PUSH_CLIENT_NOT_FOUND("10003","推送客户端查找失败"),
	;
	
	private String code;
	private String msg;
	
	private ResponseCodeMsg(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
	
	
}
