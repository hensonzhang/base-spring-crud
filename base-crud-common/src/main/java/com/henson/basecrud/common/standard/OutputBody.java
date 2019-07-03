package com.henson.basecrud.common.standard;

import java.io.Serializable;

import com.henson.basecrud.common.Module;

public class OutputBody implements Serializable{

	protected PubResponse pubResponse;
    protected Object body;



	public PubResponse getPubResponse() {
		return pubResponse;
	}

	public void setPubResponse(PubResponse pubResponse) {
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
         *
         */
        public static final String STATUS_FAIL = "fail";

        /**
         *
         */
        public static final String STATUS_SUCCESS = "success";

        /**
         * 模块名
         */
        protected Module module;

        /**
         * 状态
         */
        protected String status;

        /**
         * 消息码
         */
        protected String messageCode;

        /**
         * 消息
         */
        protected String message;
        
        protected String encryType;
        
        protected String version;


        public Module getModule() {
            return module;
        }

        public void setModule(Module module) {
            this.module = module;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getMessageCode() {
            return messageCode;
        }

        public void setMessageCode(String messageCode) {
            this.messageCode = messageCode;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
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
                    "module=" + module +
                    ", status='" + status + '\'' +
                    ", messageCode='" + messageCode + '\'' +
                    ", message='" + message + '\'' +
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
