package com.calculator.calculatordemo.Service.calculator;

import com.calculator.calculatordemo.Service.DTO.CalculationDataDTO;
import com.calculator.calculatordemo.model.CalculationResult;
import org.springframework.stereotype.Service;

/**
 * Created by Yurii on 6/7/2019.
 */
@Service("addictor")
public class CalculatorAddictor implements Calculator {
    @Override
    public CalculationResult calculate(CalculationDataDTO inputData) {

        return new CalculationResult(inputData.getFirstElement() + inputData.getSecondElement());
    }
}
