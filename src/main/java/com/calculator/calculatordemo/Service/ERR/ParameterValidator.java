package com.calculator.calculatordemo.Service.ERR;

import com.calculator.calculatordemo.Service.DTO.CalculationDataDTO;

import java.io.IOException;

public interface ParameterValidator {
    CalculationDataDTO validateParameters(String jsonString) throws IOException, IncorrectInputDataTypeException;
}

