package com.calculator.calculatordemo.Service.ERR;

import com.calculator.calculatordemo.Service.DTO.CalculationDataDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service("validator")
public class InputParameterValidator implements ParameterValidator {
    @Override
    public CalculationDataDTO validateParameters(String jsonString) throws IOException, IncorrectInputDataTypeException {
        System.out.println("json string val= " + jsonString);
        String firstElementKey = "firstElement";
        String secondElementKey = "secondElement";

        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readValue(jsonString, JsonNode.class);

        String firstElementString = rootNode.get(firstElementKey).asText();
        String secondElementString = rootNode.get(secondElementKey).asText();

        Double firstElement = validateDoubleStringElemet(firstElementString, firstElementKey);
        Double secondElemet = validateDoubleStringElemet(secondElementString, secondElementKey);

        return new CalculationDataDTO(firstElement, secondElemet);
    }

    private Double validateDoubleStringElemet(String stringElement, String elementName) throws IncorrectInputDataTypeException {
        try {

            return Double.parseDouble(stringElement);

        } catch (Exception e) {

            throw new IncorrectInputDataTypeException(elementName);
        }
    }
}
