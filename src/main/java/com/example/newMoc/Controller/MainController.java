package com.example.newMoc.Controller;

import com.example.newMoc.Model.RequestDTO;
import com.example.newMoc.Model.ResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.RoundingMode;
import java.util.Random;

import java.math.BigDecimal;


@RestController
public class MainController {

    private Logger log = LoggerFactory.getLogger(MainController.class);
    ObjectMapper mapper = new ObjectMapper();

    @PostMapping(
            value = "/info/postBalances",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public Object postBalances(@RequestBody RequestDTO requestDTO) {
        try {
            String clientId = requestDTO.getClientId();
            char firstDigit = clientId.charAt(0);
            BigDecimal maxLimit;
            String currency;
            String rqUID = requestDTO.getRqUID();
            BigDecimal balance;
            Random random = new Random();


            if (firstDigit == '8') {
                maxLimit = new BigDecimal(2000.00);
                currency = new String("US");
                balance = (maxLimit.multiply(new BigDecimal(random.nextDouble()))).setScale(2, RoundingMode.HALF_UP);
            } else if (firstDigit == '9') {
                maxLimit = new BigDecimal(1000.00);
                currency = new String("EU");
                balance = (maxLimit.multiply(new BigDecimal(random.nextDouble()))).setScale(2, RoundingMode.HALF_UP);
            }
            else {
                maxLimit = new BigDecimal(10000.00);
                currency = new String("RU");
                balance = (maxLimit.multiply(new BigDecimal(random.nextDouble()))).setScale(2, RoundingMode.HALF_UP);
            }

            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setRqUID(rqUID);
            responseDTO.setClientId(clientId);
            responseDTO.setAccount(requestDTO.getAccount());
            responseDTO.setCurrency("RU");
            responseDTO.setBalance(balance);
            responseDTO.setMaxLimit(maxLimit);
            responseDTO.setCurrency(currency);


            log.error("********** RequestDTO **********" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(requestDTO));
            log.error("********** ResponseDTO **********" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseDTO));

            return responseDTO;


        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

}
