package com.henson.basecrud.common.standard;

import java.io.Serializable;


public class InputBody implements Serializable {
	private PubRequest pubRequest;
    private Object body;

	public PubRequest getPubRequest() {
		return pubRequest;
	}

	public void setPubRequest(PubRequest pubRequest) {
		this.pubRequest = pubRequest;
	}

	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}

    @Override
    public String toString() {
        return "InputBody{" +
				"pubRequest=" + pubRequest +
                ", body=" + body +
                '}';
    }
}
