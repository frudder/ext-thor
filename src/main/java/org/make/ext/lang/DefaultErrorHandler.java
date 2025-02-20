package org.make.ext.lang;

import io.vavr.collection.Stream;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ErrorHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Set;
import java.util.concurrent.TimeoutException;

import static com.google.common.base.Strings.nullToEmpty;
import static io.vavr.API.Option;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.REQUEST_TIMEOUT;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.internalServerError;
import static org.springframework.http.ResponseEntity.status;

@RestControllerAdvice
public class DefaultErrorHandler implements ErrorHandler {

    private static final Logger logger = LoggerFactory.getLogger(DefaultErrorHandler.class);

    @ExceptionHandler(value = ThorWrapException.class)
    @ResponseStatus(value = INTERNAL_SERVER_ERROR)
    public ResponseEntity<Applicative<String>> fallback(final ThorWrapException err) {
        handleError(err);
        return internalServerError()
                .body(Applicative.from(err.getError(), nullToEmpty(err.getMessage())));
    }

//  @ExceptionHandler(value = RequestNotPermitted.class)
//  @ResponseStatus(value = TOO_MANY_REQUESTS)
//  public ResponseEntity<Applicative<String>> fallback(final RequestNotPermitted err) {
//    handleError(err);
//    return status(TOO_MANY_REQUESTS).body(Applicative.from(err.getMessage()));
//  }
//
//  @ExceptionHandler(value = BulkheadFullException.class)
//  @ResponseStatus(value = BANDWIDTH_LIMIT_EXCEEDED)
//  public ResponseEntity<Applicative<String>> fallback(final BulkheadFullException err) {
//    handleError(err);
//    return status(BANDWIDTH_LIMIT_EXCEEDED).body(Applicative.from(err.getMessage()));
//  }
//
//  @ExceptionHandler(value = CallNotPermittedException.class)
//  @ResponseStatus(value = SERVICE_UNAVAILABLE)
//  public ResponseEntity<Applicative<String>> fallback(final CallNotPermittedException err) {
//    handleError(err);
//    return status(SERVICE_UNAVAILABLE).body(Applicative.from(err.getMessage()));
//  }

    @ExceptionHandler(value = TimeoutException.class)
    @ResponseStatus(value = REQUEST_TIMEOUT)
    public ResponseEntity<Applicative<String>> fallback(final TimeoutException err) {
        handleError(err);
        return status(REQUEST_TIMEOUT).body(Applicative.from(err.getMessage()));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(value = BAD_REQUEST)
    public ResponseEntity<Applicative<String>> fallback(final MethodArgumentNotValidException err) {
        handleError(err);
        // fmt:off
        final String error =
                err.hasFieldErrors()
                        ? Stream.ofAll(err.getFieldErrors())
                        .map(it -> String.format(
                                "%s.%s: %s",
                                Option(err.getTarget())
                                        .map(h -> h.getClass().getSimpleName())
                                        .getOrElse(it.getObjectName()),
                                it.getField(),
                                it.getDefaultMessage()))
                        .mkString(";")
                        : nullToEmpty(err.getMessage());
        // fmt:on
        return badRequest().body(Applicative.from(BAD_REQUEST.value(), error));
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<Applicative<String>> fallback(final ConstraintViolationException err) {
        handleError(err);
        final Set<ConstraintViolation<?>> iter = err.getConstraintViolations();
        final String error =
                CollectionUtils.isEmpty(iter)
                        ? nullToEmpty(err.getMessage())
                        : Stream.ofAll(iter)
                        .map(
                                it -> String.format("%s: %s", it.getPropertyPath().toString(), it.getMessage()))
                        .mkString(";");
        return badRequest().body(Applicative.from(BAD_REQUEST.value(), error));
    }

    @ExceptionHandler(value = RuntimeException.class)
    @ResponseStatus(value = INTERNAL_SERVER_ERROR)
    public ResponseEntity<Applicative<String>> fallback(final RuntimeException err) {
        handleError(err);
        return internalServerError()
                .body(Applicative.from(INTERNAL_SERVER_ERROR.value(), nullToEmpty(err.getMessage())));
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(value = INTERNAL_SERVER_ERROR)
    public ResponseEntity<Applicative<String>> fallback(final Exception err) {
        handleError(err);
        return internalServerError()
                .body(Applicative.from(INTERNAL_SERVER_ERROR.value(), nullToEmpty(err.getMessage())));
    }

    @Override
    public void handleError(@NonNull final Throwable t) {
        if (logger.isErrorEnabled()) {
            logger.error("handleError:", t);
        }
    }
}