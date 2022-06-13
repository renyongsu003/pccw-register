package com.pccw.register.adapter.filter;

import com.pccw.register.adapter.dto.ResultCodeEnum;
import com.pccw.register.adapter.dto.ResultDTO;
import com.pccw.register.domain.exceptions.BussinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class RegisterExceptionHandler {

    @ExceptionHandler(Exception.class)
    protected ResultDTO handleException(Exception ex) {
        if (ex instanceof BussinessException) {
            return handleBussinessException((BussinessException) ex);
        } else if(ex instanceof BindException){
            return handleBindExceptionException((BindException)ex);
        }else {
            return otherException(ex);
        }

    }


    private ResultDTO handleBindExceptionException(BindException ex) {
        String message = message(ex.getBindingResult());
        log.error("入参错误,错误信息:{}", message, ex);
        return new ResultDTO(ResultCodeEnum.ERROR_PARAM_ILLEGAL.getCode(),message);
    }

    private String message(BindingResult result) {
        if (result == null) {
            return "参数错误";
        }

        StringBuilder sb = new StringBuilder();
        result.getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError)error).getField();
            String errorMessage = error.getDefaultMessage();
            sb.append(fieldName).append(errorMessage).append(". ");
        });

        return sb.toString();
    }

    private ResultDTO handleBussinessException(BussinessException ex) {
        if (ex.getCause() != null) {
            log.error("BussinessException异常信息", ex);
        } else {
            log.warn("BussinessException业务异常信息", ex);
        }
        return new ResultDTO(ResultCodeEnum.ERROR_BUSINESS.getCode(), ex.getMessage());
    }

    private ResultDTO otherException(Exception ex) {
        log.error("其他系统错误", ex);
        return new ResultDTO().fail(ResultCodeEnum.ERROR_SERVER);
    }
}
