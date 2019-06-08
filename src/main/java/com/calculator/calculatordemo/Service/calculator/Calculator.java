package com.calculator.calculatordemo.Service.calculator;

import com.calculator.calculatordemo.Service.DTO.CalculationDataDTO;
import com.calculator.calculatordemo.Service.ERR.DivisionException;
import com.calculator.calculatordemo.model.CalculationResult;

/**
 * Created by Yurii on 6/7/2019.
 */

public interface Calculator {
    CalculationResult calculate(CalculationDataDTO inputData) throws DivisionException;
}
