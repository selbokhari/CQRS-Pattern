package com.food.ordering.system.customer.service.handler;

import com.food.ordering.system.application.handler.ErrorDTO;
import com.food.ordering.system.application.handler.GlobalExceptionHandler;
import com.food.ordering.system.customer.service.domain.exception.CustomerDomainException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Slf4j
@ControllerAdvice
public class CustomerGlobalExceptionHandler extends GlobalExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {CustomerDomainException.class})
    public ErrorDTO handleCustomerDomainException(CustomerDomainException exception) {
        log.error(exception.getMessage(), exception);
        return new ErrorDTO(HttpStatus.BAD_REQUEST.getReasonPhrase(), exception.getMessage());
    }
}
