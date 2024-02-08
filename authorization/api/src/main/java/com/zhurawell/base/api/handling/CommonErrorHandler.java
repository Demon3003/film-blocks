package com.zhurawell.base.api.handling;

import com.zhurawell.base.api.dto.error.ErrorDto;
import com.zhurawell.blocks.common.utils.exception.BuilderRuntimeException;
import com.zhurawell.blocks.common.utils.exception.CustomExceptionHandler;
import com.zhurawell.blocks.common.utils.exception.ErrorCodes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class CommonErrorHandler {

    private final ErrorDto defaultError = new ErrorDto(ErrorCodes.DEFAULT.getCode(), ErrorCodes.DEFAULT.getMessage());

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleCommonError(Exception ex) {
        log.error("Error occurred", ex);
        BuilderRuntimeException root = CustomExceptionHandler.getBuilderRuntimeException(ex);
        ErrorDto error = root == null ? defaultError : new ErrorDto(root.getCode(), root.getMessage());
        return ResponseEntity.internalServerError().body(error);
    }

    @ExceptionHandler(BuilderRuntimeException.class)
    public ResponseEntity<ErrorDto> handleBadCredentials(BuilderRuntimeException ex) {
        log.error("Error occurred", ex);
        return ResponseEntity.internalServerError().body(new ErrorDto(ex.getCode(), ex.getMessage()));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorDto> handleBadCredentials(BadCredentialsException ex) {
        log.error("Error occurred", ex);
        return ResponseEntity.internalServerError().body(new ErrorDto(ErrorCodes.C_102.getCode(), ErrorCodes.C_102.getMessage()));
    }

}
