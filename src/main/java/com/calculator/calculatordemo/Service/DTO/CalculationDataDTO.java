package com.calculator.calculatordemo.Service.DTO;

import org.springframework.format.annotation.NumberFormat;
import javax.validation.constraints.NotNull;

/**
 * Created by Yurii on 6/7/2019.
 */
public class CalculationDataDTO {

    @NotNull(message="first element couldn't be null")
    @NumberFormat
    private double firstElement;

    @NotNull(message="second element couldn't be null")
    @NumberFormat
    private double secondElement;

    public CalculationDataDTO() {
    }

    public CalculationDataDTO(double firstElement, double secondElement) {
        this.firstElement = firstElement;
        this.secondElement = secondElement;
    }

    public double getFirstElement() {
        return firstElement;
    }

    public void setFirstElement(double firstElement) {
        this.firstElement = firstElement;
    }

    public double getSecondElement() {
        return secondElement;
    }

    public void setSecondElement(double secondElement) {
        this.secondElement = secondElement;
    }

    @Override
    public String toString() {
        return "CalculationDataDTO{" +
                "firstElement=" + firstElement +
                ", secondElement=" + secondElement +
                '}';
    }
}
