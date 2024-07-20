package com.verramobility.dto;

import lombok.Data;

import java.util.Map;

@Data
public class ResponseMessage {
    private String respMsg;
    private String respCode;
    private Map<Object, Object> responseMap;

    public ResponseMessage (String respMsg,String respCode){
        this.respMsg=respMsg;
        this.respCode=respCode;
    }
}
