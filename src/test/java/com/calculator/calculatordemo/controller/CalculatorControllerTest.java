package com.calculator.calculatordemo.controller;

import com.calculator.calculatordemo.Service.DTO.CalculationDataDTO;
import com.calculator.calculatordemo.Service.ERR.*;
import com.calculator.calculatordemo.Service.calculator.Calculator;
import com.calculator.calculatordemo.model.CalculationResult;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.net.URI;
import java.net.URISyntaxException;


import static org.junit.Assert.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Created by Yurii on 6/7/2019.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {CalculatorController.class, InputParameterValidator.class, CacheControl.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration

public class CalculatorControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    private String baseUrl = "http://localhost:";

    @LocalServerPort
    int localPort;

    String jsonString;
    CalculationResult result;
    HttpEntity<String> request;

    @Before
    public void prepareData() throws URISyntaxException {
        jsonString = "{\"firstElement\":2, \"secondElement\":1}";
        request = new HttpEntity<>(jsonString);
        localPort = 8083;
    }

    @Test
    public void addDataWithIncorrectFirstParameter() throws Exception {
        String jsonString = "{\"firstElement\":\"a\", \"secondElement\":1}";
        String error = "{error=Invalid value of firstElement}";
        HttpEntity<String> request = new HttpEntity<>(jsonString);

        ResponseEntity<Object> responseEntity =
                restTemplate.postForEntity(new URI(baseUrl + localPort + "/api/plus/"), request, Object.class);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals(error, responseEntity.getBody().toString());
    }

    @Test
    public void addDataWithIncorrectSecondParameter() throws Exception {
        String jsonString = "{\"firstElement\":2, \"secondElement\":\"a\"}";
        String error = "{error=Invalid value of secondElement}";
        HttpEntity<String> request = new HttpEntity<>(jsonString);

        ResponseEntity<Object> responseEntity =
                restTemplate.postForEntity(new URI(baseUrl + localPort + "/api/plus/"), request, Object.class);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals(error, responseEntity.getBody().toString());
    }

    @Test
    public void addData() throws Exception {

        result = new CalculationResult(3);

        ResponseEntity<CalculationResult> responseEntity =
                restTemplate.postForEntity(new URI(baseUrl + localPort + "/api/plus/"), request, CalculationResult.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(result, responseEntity.getBody());
    }


    @Test
    public void deductData() throws Exception {
        result = new CalculationResult(1);

        ResponseEntity<CalculationResult> responseEntity =
                restTemplate.postForEntity(new URI(baseUrl + localPort + "/api/minus/"), request, CalculationResult.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(result, responseEntity.getBody());
    }

    @Test
    public void multiplyData() throws Exception {
        result = new CalculationResult(2);

        ResponseEntity<CalculationResult> responseEntity =
                restTemplate.postForEntity(new URI(baseUrl + localPort + "/api/multiply/"), request, CalculationResult.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(result, responseEntity.getBody());
    }

    @Test
    public void divideData() throws Exception {
        result = new CalculationResult(2);

        ResponseEntity<CalculationResult> responseEntity =
                restTemplate.postForEntity(new URI(baseUrl + localPort + "/api/divide/"), request, CalculationResult.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(result, responseEntity.getBody());
    }

    @Test
    public void divideDataByZero() throws Exception {
        String jsonString = "{\"firstElement\":2, \"secondElement\":0}";
        String error = "{error=Second parameter coudln't be zero!}";
        HttpEntity<String> request = new HttpEntity<>(jsonString);

        ResponseEntity<Object> responseEntity =
                restTemplate.postForEntity(new URI(baseUrl + localPort + "/api/divide/"), request, Object.class);

        System.out.println(" responce = " + responseEntity.getHeaders());
        System.out.println(" responce = " + responseEntity.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals(error, responseEntity.getBody().toString());
    }
}