package com.verramobility.controller;

import com.verramobility.constant.CommonConstant;
import com.verramobility.dto.ComponentDto;
import com.verramobility.dto.ResponseMessage;
import com.verramobility.service.DiscountCalculatorService;
import com.verramobility.utils.AppResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = CommonConstant.MAIN_PATH)
@Slf4j
public class DiscountCalculatorController {

    @Autowired
    DiscountCalculatorService discountCalculatorService;

    @GetMapping(CommonConstant.END_POINT)
    public ResponseEntity<ResponseMessage> fetchDiscount (@RequestBody List<ComponentDto> input){
        log.info(CommonConstant.LOG_INFO,input);
        Object response;
        try {
            response =discountCalculatorService.printReceipt(input);
            return ResponseEntity.status(HttpStatus.OK).body(AppResponseUtils.successResponse(response));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(AppResponseUtils.failureResponse(CommonConstant.ERROR_CODE, CommonConstant.ERROR_MSG, e.getMessage()));
        }
    }

}
