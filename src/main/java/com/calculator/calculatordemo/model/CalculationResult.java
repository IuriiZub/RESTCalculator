package com.calculator.calculatordemo.model;

/**
 * Created by Yurii on 6/7/2019.
 */
public class CalculationResult {

    private double result;

    public CalculationResult() {
    }

    public CalculationResult(double result) {
        this.result = result;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CalculationResult)) return false;

        CalculationResult that = (CalculationResult) o;

        return Double.compare(that.getResult(), getResult()) == 0;
    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(getResult());
        return (int) (temp ^ (temp >>> 32));
    }

    @Override
    public String toString() {
        return "CalculationResult{" +
                "result=" + result +
                '}';
    }
}
