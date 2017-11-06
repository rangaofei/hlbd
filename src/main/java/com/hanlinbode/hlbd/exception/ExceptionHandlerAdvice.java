package com.hanlinbode.hlbd.exception;

import com.hanlinbode.hlbd.composbean.BaseBean;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@ControllerAdvice
@ResponseBody
public class ExceptionHandlerAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseBean<Object> handleIllegalException(MethodArgumentNotValidException e) {
        List<ObjectError> errorList = e.getBindingResult().getAllErrors();
        String msg = "参数不合法";
        BaseBean<Object> result = new BaseBean<>();
        result.setCode(412);
        result.setMessage(msg);
        return result;
    }

    @ExceptionHandler(ResultNotFoundException.class)
    public BaseBean<Object> handleResultNotFoundException(ResultNotFoundException e) {
        BaseBean<Object> result = new BaseBean<>();
        result.setCode(410);
        result.setMessage(e.getMessage());
        return result;
    }

    @ExceptionHandler(ResultAlreadyExistException.class)
    public BaseBean<Object> handleResultAlreadyExistException(ResultAlreadyExistException e){
        BaseBean<Object> result = new BaseBean<>();
        result.setCode(411);
        result.setMessage(e.getMessage());
        return result;
    }
}
