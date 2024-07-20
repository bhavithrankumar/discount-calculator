package com.verramobility.daoimpl;

import com.verramobility.constant.CommonConstant;
import com.verramobility.dto.ComponentDto;
import com.verramobility.repository.DiscountCalculatorRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DiscountCalculationService {

    @Autowired
    DiscountCalculatorRepo discountCalculatorRepo;

    public double calculateDiscount(ComponentDto component){
        try {
            double discount = discountCalculatorRepo.findDiscountByProductName(component.getCategory())/CommonConstant.DIVIDE;

            if(component.isClearanceSale()){
                discount += CommonConstant.CLEARANCE_PERCENTAGE;
            }
            return round(component.getPrice() * discount);
        }catch (Exception e){
            log.error(CommonConstant.EXCEPTION_INFO,e);
            throw new RuntimeException(e.getMessage());
        }
    }
    public double round(double value) {
        return Math.round(value * CommonConstant.DIVIDE) / CommonConstant.DIVIDE;
    }

}
