package com.henson.basecrud.common.standard;

import java.io.Serializable;

public class PubRequest implements Serializable {

	private String token;
	private String encryType;
	private String version;
	private String method; // system.auth.login

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getEncryType() {
		return encryType;
	}

	public void setEncryType(String encryType) {
		this.encryType = encryType;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	@Override
	public String toString() {
		return "PubRequest{" + "token=" + token + ",encryType=" + encryType + ",version=" + version + ",method="
				+ method + "}";
	}

}