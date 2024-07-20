package com.verramobility.utils;

import com.verramobility.constant.CommonConstant;
import com.verramobility.dto.ResponseMessage;

import java.util.HashMap;
import java.util.Map;

public class AppResponseUtils {

    public static ResponseMessage successResponse(Object response){
        ResponseMessage message = new ResponseMessage(CommonConstant.SUCCESS_MSG, CommonConstant.SUCCESS_CODE);
        Map<Object,Object> responseMap = new HashMap<>();
        responseMap.put(CommonConstant.RESPONSE,response);
        message.setResponseMap(responseMap);
        return message;
    }

    public static ResponseMessage failureResponse(String errCode, String errMsg, Object response){
        ResponseMessage message = new ResponseMessage(errMsg, errCode);
        Map<Object, Object> responseMap = new HashMap<>();
        responseMap.put(CommonConstant.RESPONSE, response);
        message.setResponseMap(responseMap);
        return message;
    }
}
