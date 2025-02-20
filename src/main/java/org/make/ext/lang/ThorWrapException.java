package org.make.ext.lang;

import org.springframework.lang.Nullable;
import org.springframework.util.ErrorHandler;

import java.io.Serial;

import static com.google.common.base.Strings.nullToEmpty;
import static java.util.Objects.nonNull;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.util.ObjectUtils.isEmpty;

public final class ThorWrapException extends RuntimeException implements ErrorHandler {

    @Serial
    private static final long serialVersionUID = 7507410443781119497L;

    private final Integer error;

    private ThorWrapException(final Integer errorValue, String message, Throwable cause) {
        super(message, cause);
        this.error = errorValue;
    }

    private ThorWrapException(final Integer errorValue, final String message) {
        this(errorValue, message, null);
    }

    public ThorWrapException(final Integer errorValue, final Throwable err) {
        this(errorValue, nullToEmpty(err.getMessage()), err);
    }

    public static ThorWrapException of(@Nullable final Integer errorValue, final Throwable err) {
        return new ThorWrapException(
                isEmpty(errorValue) ? INTERNAL_SERVER_ERROR.value() : errorValue, err);
    }

    public static ThorWrapException of(final Throwable err) {
        return of(null, err);
    }

    public static ThorWrapException of(final Integer errorValue, final String message) {
        return new ThorWrapException(errorValue, nullToEmpty(message));
    }

    @Override
    public void handleError(@Nullable Throwable err) {
        if (nonNull(err)) {
            throw ThorWrapException.of(err);
        }
    }

    public Integer getError() {
        return error;
    }
}