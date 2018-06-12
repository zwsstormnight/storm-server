package cn.nj.storm.common.pojo;

import com.alibaba.fastjson.annotation.JSONField;

public class BaseResult {
	/**
	 * 返回编码
	 */
	private int result;

	/**
	 * 返回值对象
	 */
	private Object data;

	/**
	 * 错误信息描述
	 */
	private String resultErrMsg;

	public BaseResult() {
	}

	public BaseResult(int result, String resultErrMsg, Object data) {
		this.result = result;
		this.data = data;
		this.resultErrMsg = resultErrMsg;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@JSONField(name = "result_err_msg")
	public String getResultErrMsg() {
		return resultErrMsg;
	}

	@JSONField(name = "result_err_msg")
	public void setResultErrMsg(String resultErrMsg) {
		this.resultErrMsg = resultErrMsg;
	}

}
