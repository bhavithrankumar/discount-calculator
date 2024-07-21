package com.verramobility.service;

import com.verramobility.dto.ComponentDto;
import com.verramobility.dto.ResponseDto;

import java.util.List;

public interface DiscountCalculatorService {

    public ResponseDto printReceipt(List<ComponentDto> request);
}
