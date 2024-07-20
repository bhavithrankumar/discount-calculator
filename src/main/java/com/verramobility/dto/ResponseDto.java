package com.verramobility.dto;

import lombok.Data;

import java.util.List;

@Data
public class ResponseDto {

    private List<ItemDetailDto> items;
    private double total;
    private double totalSaved;
    private int totalItem;

    @Data
    public static class ItemDetailDto {
        private int count;
        private String product;
        private double price;
    }

}
