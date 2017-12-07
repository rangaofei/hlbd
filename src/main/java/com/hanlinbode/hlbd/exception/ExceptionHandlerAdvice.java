package com.hanlinbode.hlbd.exception;

import com.hanlinbode.hlbd.composbean.BaseBean;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
        result.setBody(e.getBody());
        result.setMessage(e.getMessage());
        return result;
    }

    @ExceptionHandler(ResultAlreadyExistException.class)
    public BaseBean<Object> handleResultAlreadyExistException(ResultAlreadyExistException e) {
        BaseBean<Object> result = new BaseBean<>();
        result.setCode(411);
        result.setBody(e.getBody());
        result.setMessage(e.getMessage());
        return result;
    }

    @ExceptionHandler(ParamIncorrectException.class)
    public BaseBean<Object> handleParamIncorrectException(ParamIncorrectException e) {
        BaseBean<Object> result = new BaseBean<>();
        result.setCode(412);
        result.setBody(e.getBody());
        result.setMessage(e.getMessage());
        return result;
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public BaseBean<Object> handleUserNotFoundException(UsernameNotFoundException e) {
        BaseBean<Object> result = new BaseBean<>();
        result.setCode(415);
        result.setMessage(e.getMessage());
        return result;
    }

    @ExceptionHandler({ExpiredJwtException.class,
            UnsupportedJwtException.class,
            MalformedJwtException.class,
            SignatureException.class,
            IllegalArgumentException.class})
    public BaseBean<Object> handleTokenWrongException(Exception e) {
        BaseBean<Object> result = new BaseBean<>();
        result.setCode(466);
        result.setMessage("refresh_token失效");
        return result;
    }
}
