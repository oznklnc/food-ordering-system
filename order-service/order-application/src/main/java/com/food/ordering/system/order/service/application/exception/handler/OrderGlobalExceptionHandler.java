package com.food.ordering.system.order.service.application.exception.handler;

import com.food.ordering.system.application.dto.ErrorDTO;
import com.food.ordering.system.application.handler.GlobalExceptionHandler;
import com.food.ordering.system.order.service.domain.exception.OrderDomainException;
import com.food.ordering.system.order.service.domain.exception.OrderNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class OrderGlobalExceptionHandler extends GlobalExceptionHandler {

    @ExceptionHandler(value = {OrderDomainException.class})
    public ResponseEntity<ErrorDTO> handleException(OrderDomainException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.badRequest().body(
                ErrorDTO.builder()
                        .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
                        .message(e.getMessage())
                        .build());
    }
    @ExceptionHandler(value = {OrderNotFoundException.class})
    public ResponseEntity<ErrorDTO> handleException(OrderNotFoundException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ErrorDTO.builder()
                        .code(HttpStatus.NOT_FOUND.getReasonPhrase())
                        .message(e.getMessage())
                        .build());
    }
}
