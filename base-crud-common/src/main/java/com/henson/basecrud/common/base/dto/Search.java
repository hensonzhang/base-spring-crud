package com.henson.basecrud.common.base.dto;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.henson.basecrud.common.base.enums.OpEnum;

public class Search implements Serializable {

    private List<Operation> operations;

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    /**
     * 增加操作
     *
     * @param operation
     */
    public void addOperation(Operation operation) {
        if (this.operations == null) {
            this.operations = new ArrayList<>();
        }
        this.operations.add(operation);
    }

    /**
     * 操作对象
     */
    public static class Operation {

        private String fieldName;
        private OpEnum op;
        private Object value;


        public String getFieldName() {
            return fieldName;
        }

        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }

        public OpEnum getOp() {
            return op;
        }

        public void setOp(OpEnum op) {
            this.op = op;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

		@Override
		public String toString() {
			return "Operation [fieldName=" + fieldName + ", op=" + op
					+ ", value=" + value + "]";
		}
        
    }


}
