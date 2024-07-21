package com.verramobility.service;

import com.verramobility.daoimpl.DiscountCalculationService;
import com.verramobility.dto.ComponentDto;
import com.verramobility.dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DiscountCalculatorServiceImpl implements DiscountCalculatorService {

    @Autowired
    DiscountCalculationService discountCalculationService;

    public ResponseDto printReceipt(List<ComponentDto> request){
        double total = 0.0;
        double totalSaved = 0.0;
        int totalItem = 0;
       List<ResponseDto.ItemDetailDto> itemDetailDto = new ArrayList<>();

        for (ComponentDto item : request){
            double discount = discountCalculationService.calculateDiscount(item);
            double discountedPrice = discountCalculationService.round(item.getPrice() - discount);
            total += discountedPrice;
            totalSaved += discount;
            totalItem += item.getCount();

            ResponseDto.ItemDetailDto itemDetail = new ResponseDto.ItemDetailDto();
            itemDetail.setCount(item.getCount());
            itemDetail.setProduct(item.getName());
            itemDetail.setPrice(discountedPrice);

            itemDetailDto.add(itemDetail);
        }
        ResponseDto responseDto = new ResponseDto();
        responseDto.setItems(itemDetailDto);
        responseDto.setTotal(discountCalculationService.round(total));
        responseDto.setTotalSaved(discountCalculationService.round(totalSaved));
        responseDto.setTotalItem(totalItem);

        return responseDto;
    }
}
