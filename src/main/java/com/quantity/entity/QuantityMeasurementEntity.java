package com.quantity.entity;

public class QuantityMeasurementEntity {

    private String operation;
    private String result;
    private String input1;
    private String input2;
    private boolean error;
    private String errorMessage;

    public QuantityMeasurementEntity(String operation, String result, String input1, String input2) {
        this.operation = operation;
        this.result = result;
        this.input1 = input1;
        this.input2 = input2;
        this.error = false;
    }
    
    public boolean hasError() {
    	return error;
    }

    public String getOperation() {
        return operation;
    }

    public String getResult() {
        return result;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setResult(String result) {
        this.result = result;
    }
    
    public String getInput1() {
    		return input1;
    }
    
    public String getInput2() {
    		return input2;
    }
}