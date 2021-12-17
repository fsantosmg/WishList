package net.felipesantos.wishlist.api.exceptionhandler;

import net.felipesantos.wishlist.common.enums.ProblemType;
import net.felipesantos.wishlist.common.util.Problem;
import net.felipesantos.wishlist.domain.exception.EntityNotFoundException;
import net.felipesantos.wishlist.domain.exception.ProductAlreadyRegisteredException;
import net.felipesantos.wishlist.domain.exception.WishListReachedLimitException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex, WebRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        ProblemType problemType = ProblemType.RESOURCE_NOT_FOUND;
        String detail = ex.getMessage();
        Problem problem = createProblemBuilder(status, problemType, detail).build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(ProductAlreadyRegisteredException.class)
    public ResponseEntity<Object> handleProductAlreadyRegistered(ProductAlreadyRegisteredException ex, WebRequest request) {

        HttpStatus status = HttpStatus.CONFLICT;
        ProblemType problemType = ProblemType.ENTITY_ALREADY_REGISTERED;
        String detail = ex.getMessage();
        Problem problem = createProblemBuilder(status, problemType, detail).build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(WishListReachedLimitException.class)
    public ResponseEntity<Object> handleProductAlreadyRegistered(WishListReachedLimitException ex, WebRequest request) {

        HttpStatus status = HttpStatus.INSUFFICIENT_STORAGE;
        ProblemType problemType = ProblemType.WISHLIST_REACHED_LIMIT;
        String detail = ex.getMessage();
        Problem problem = createProblemBuilder(status, problemType, detail).build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        ProblemType problemType = ProblemType.INVALID_MESSAGE;
        String detail = ex.getMessage();
        Problem problem = createProblemBuilder(status, problemType, detail).build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUncaught(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ProblemType problemType = ProblemType.SYSTEM_ERROR;
        String detail = ex.getMessage();

        ex.printStackTrace();

        Problem problem = createProblemBuilder(status, problemType, detail)
                .build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }


    private Problem.ProblemBuilder createProblemBuilder(HttpStatus status, ProblemType problemType, String detail) {

        return Problem.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .type(problemType.getUri())
                .title(problemType.getTitle())
                .detail(detail);
    }
}
