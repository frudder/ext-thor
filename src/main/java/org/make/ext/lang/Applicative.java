package org.make.ext.lang;

import org.springframework.http.HttpStatus;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

import static io.vavr.API.Try;
import static org.springframework.http.HttpStatus.OK;

@SuppressWarnings(value = "unchecked")
public final class Applicative<T>
        implements ValueObjectWrap<T>, Serializable {

    @Serial
    private static final long serialVersionUID = 7700066502964553606L;

    private final int state;

    private final String error;

    private final T body;

    private Applicative(int state, String error, T body) {
        this.state = state;
        this.error = error;
        this.body = body;
    }

    public static <T> Applicative<T> apply(final int state, final String error, final T body) {
        return new Applicative<>(state, error, body);
    }

    public static <T> Applicative<T> from(final int state, final String error, final T body) {
        return apply(state, error, Objects.isNull(body) ? (T) Map.of() : body);
    }

    public static <T> Applicative<T> from(final T body) {
        return from(OK.value(), OK.getReasonPhrase(), body);
    }

    public static <T> Applicative<T> from(final int state, final String error) {
        return from(state, error, null);
    }

    public static <T> Applicative<T> from(final HttpStatus http, final T body) {
        return from(http.value(), http.getReasonPhrase(), body);
    }

    public static <T> Applicative<T> from() {
        return from(OK.value(), OK.getReasonPhrase(), null);
    }

    public static <T> Applicative<T> from(final HttpStatus http) {
        return from(http.value(), http.getReasonPhrase());
    }

    public static <T> Applicative<T> apply(final Supplier<T> function) {
        return apply(null, function);
    }

    public static <T> Applicative<T> apply(final Integer errorValue, final Supplier<T> function) {
        return Try(function::get)
                .fold(
                        err -> {
                            throw new ThorWrapException(errorValue, err);
                        },
                        Applicative::from);
    }

    @Override
    public int getState() {
        return state;
    }

    @Override
    public String getError() {
        return error;
    }

    @Override
    public T getBody() {
        return body;
    }
}