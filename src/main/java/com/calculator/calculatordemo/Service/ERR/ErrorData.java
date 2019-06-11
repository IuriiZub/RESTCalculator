package com.calculator.calculatordemo.Service.ERR;

/**
 * Created by Yurii on 6/8/2019.
 */
public class ErrorData {

    private String error;

    public ErrorData(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "ErrorData{" +
                "error='" + error + '\'' +
                '}';
    }
}
