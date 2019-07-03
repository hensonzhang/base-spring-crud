package com.henson.basecrud.common.standard;

import java.io.Serializable;


public class OutputBody2 implements Serializable{


	protected PubResponse pubResponse;
    protected Object body;

	public PubResponse getPubResponse() {
		return pubResponse;
    }

	public void setPubresponse(PubResponse pubResponse) {
		this.pubResponse = pubResponse;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public static class PubResponse implements Serializable{

        /**
		 * 成功状态
		 */
		public static final String SUCCESS_CODE = "0000";

        /**
         * 消息码
         */
		protected String code;

        /**
         * 消息
         */
		protected String msg;
        
        protected String encryType;
        
        protected String version;

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

		public String getVersion() {
			return version;
		}

		public void setVersion(String version) {
			this.version = version;
		}
		
		public String getEncryType() {
			return encryType;
		}

		public void setEncryType(String encryType) {
			this.encryType = encryType;
		}

		@Override
        public String toString() {
            return "PubResponse{" +
					", code='" + code + '\'' + ", msg='" + msg + '\'' +
                    ", version='" + version + '\'' +
                    ", encryType='" + encryType + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "OutputBody{" +
				"pubResponse=" + pubResponse +
                ", body=" + body +
                '}';
    }
}
