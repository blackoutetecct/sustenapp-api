package sustenapp_api.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerException {
    @ExceptionHandler({ExceptionGeneric.class})
    public ResponseEntity<ResponseBody> returnException(ExceptionGeneric exception){
        return ResponseEntity.status(exception.getCorpoResposta().getStatus()).body(exception.getCorpoResposta());
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<ResponseBody> returnException(BadRequestException exception){
        return ResponseEntity.status(exception.getCorpoResposta().getStatus()).body(exception.getCorpoResposta());
    }
}
