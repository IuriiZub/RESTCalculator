package com.calculator.calculatordemo.controller;

import com.calculator.calculatordemo.Service.DTO.CalculationDataDTO;
import com.calculator.calculatordemo.Service.ERR.DivisionException;
import com.calculator.calculatordemo.model.CalculationResult;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.URI;
import java.net.URISyntaxException;


import static org.junit.Assert.*;


/**
 * Created by Yurii on 6/7/2019.
 */


@RunWith(SpringJUnit4ClassRunner.class)
@EnableAutoConfiguration
@SpringBootTest(classes = {CalculatorController.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CalculatorControllerTest {

    private String baseUrl;

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    int localPort;

    CalculationDataDTO inputData;

    @Before
    public void prepareData() throws URISyntaxException {
        baseUrl = "http://localhost:";
        inputData = new CalculationDataDTO(2, 1);
    }

    @Test
    public void addData() throws Exception {
        ResponseEntity<CalculationResult> responseEntity =
                restTemplate.postForEntity(new URI(baseUrl + localPort + "/api/plus/"), inputData, CalculationResult.class);
        CalculationResult result = responseEntity.getBody();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assert.assertEquals(new CalculationResult(3.0), result);
    }


    @Test
    public void deductData() throws Exception {
        ResponseEntity<CalculationResult> responseEntity =
                restTemplate.postForEntity(new URI(baseUrl + localPort + "/api/minus/"), inputData, CalculationResult.class);
        CalculationResult result = responseEntity.getBody();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assert.assertEquals(new CalculationResult(1.0), result);
    }

    @Test
    public void multiplyData() throws Exception {
        ResponseEntity<CalculationResult> responseEntity =
                restTemplate.postForEntity(new URI(baseUrl + localPort + "/api/multiply/"), inputData, CalculationResult.class);
        CalculationResult result = responseEntity.getBody();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assert.assertEquals(new CalculationResult(2.0), result);
    }

    @Test
    public void divideData() throws Exception {
        ResponseEntity<CalculationResult> responseEntity =
                restTemplate.postForEntity(new URI(baseUrl + localPort + "/api/divide/"), inputData, CalculationResult.class);
        CalculationResult result = responseEntity.getBody();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assert.assertEquals(new CalculationResult(2.0), result);
    }

    @Test
    public void divideDataByZero() throws Exception {
        ResponseEntity<DivisionException> responseEntity =
                restTemplate.postForEntity(new URI(baseUrl + localPort + "/api/divide/"), new CalculationDataDTO(2, 0), DivisionException.class);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }


}