package com.calculator.calculatordemo.Service.calculator;

import com.calculator.calculatordemo.Service.DTO.CalculationDataDTO;
import com.calculator.calculatordemo.Service.ERR.DivisionException;
import com.calculator.calculatordemo.model.CalculationResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CancellationException;

/**
 * Created by Yurii on 6/7/2019.
 */
@Service("divider")
public class CalculatorDivider implements Calculator  {
    @Override
    public CalculationResult calculate(CalculationDataDTO inputData) {

        if (inputData.getSecondElement() == 0) throw new DivisionException();
        return new CalculationResult(inputData.getFirstElement() / inputData.getSecondElement());
    }
}
