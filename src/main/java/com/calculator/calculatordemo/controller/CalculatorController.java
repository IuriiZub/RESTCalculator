package com.calculator.calculatordemo.controller;

import com.calculator.calculatordemo.Service.DTO.CalculationDataDTO;

import com.calculator.calculatordemo.Service.ERR.*;
import com.calculator.calculatordemo.Service.calculator.*;
import com.calculator.calculatordemo.model.CalculationResult;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;


/**
 * Created by Yurii on 6/7/2019.
 */
@RestController
@RequestMapping(value = "/api")
@CrossOrigin
public class CalculatorController {

    private final HttpHeaders httpHeaders = new HttpHeaders();

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ParameterValidator inputValidator;

    CalculatorController() {

    }

    @RequestMapping(value = "/plus", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<CalculationResult> addData(@RequestBody String calculationJsonDTO)
            throws IOException, IncorrectInputDataTypeException {

        CalculationDataDTO inputData = inputValidator.validateParameters(calculationJsonDTO);
        Calculator calculator = applicationContext.getBean(CalculatorAddictor.class);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity(calculator.calculate(inputData), httpHeaders, HttpStatus.OK);
    }

    @RequestMapping(value = "/minus", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<CalculationResult> deductData(@RequestBody String calculationJsonDTO)
            throws IOException, IncorrectInputDataTypeException {

        CalculationDataDTO inputData = inputValidator.validateParameters(calculationJsonDTO);
        Calculator calculator = applicationContext.getBean(CalculatorDeductor.class);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity(calculator.calculate(inputData), httpHeaders, HttpStatus.OK);
    }

    @RequestMapping(value = "/multiply", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<CalculationResult> multiplyData(@RequestBody String calculationJsonDTO)
            throws IOException, IncorrectInputDataTypeException {

        CalculationDataDTO inputData = inputValidator.validateParameters(calculationJsonDTO);
        Calculator calculator = applicationContext.getBean(CalculatorMultipliyer.class);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity(calculator.calculate(inputData), httpHeaders, HttpStatus.OK);
    }

    @RequestMapping(value = "/divide", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<CalculationResult> divideData(@RequestBody String calculationJsonDTO)
            throws IOException, IncorrectInputDataTypeException, DivisionException {

        CalculationDataDTO inputData = inputValidator.validateParameters(calculationJsonDTO);
        Calculator calculator = applicationContext.getBean(CalculatorDivider.class);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity(calculator.calculate(inputData), httpHeaders, HttpStatus.OK);
    }

    @ExceptionHandler
    public final ResponseEntity<ErrorData> handleDivisionException(DivisionException ex) {

        ErrorData errorData = new ErrorData(ex.getMessage());
        return new ResponseEntity<>(errorData, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<ErrorData> handleIncorrectInputDataException(IncorrectInputDataTypeException ex) {

        ErrorData errorData = new ErrorData(ex.getMessage());
        return new ResponseEntity(errorData, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorData> handleIoException(IOException e) {
        ErrorData errorData = new ErrorData("Incorrect input! " + e.getMessage());
        return new ResponseEntity(errorData, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorData> handleException(Exception e) {

        ErrorData errorData = new ErrorData(e.getMessage());
        return new ResponseEntity(errorData, HttpStatus.BAD_REQUEST);
    }
}

