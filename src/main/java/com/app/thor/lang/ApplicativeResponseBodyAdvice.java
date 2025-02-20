package com.app.thor.lang;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.WebAsyncTask;
import org.springframework.web.servlet.function.ServerResponse;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.OutputStream;
import java.util.Set;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@RestControllerAdvice
public class ApplicativeResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    private static final Logger logger = LoggerFactory.getLogger(ApplicativeResponseBodyAdvice.class);

    private static final Set<Class<?>> skipping =
            Set.of(
                    Void.class,
                    Resource.class,
                    StreamingResponseBody.class,
                    OutputStream.class,
                    DeferredResult.class,
                    WebAsyncTask.class,
                    ResponseBodyEmitter.class,
                    ServerResponse.class,
                    ResponseEntity.class,
                    Applicative.class,
                    String.class,
                    byte[].class);

    @Override
    public boolean supports(
            @NonNull final MethodParameter returnType,
            @NonNull final Class<? extends HttpMessageConverter<?>> converterType) {
        final Class<?> target = returnType.getParameterType();
        return skipping.stream().noneMatch(it -> it.isAssignableFrom(target));
    }

    @Override
    public Object beforeBodyWrite(
            final Object body,
            @NonNull final MethodParameter returnType,
            @NonNull final MediaType selectedContentType,
            @NonNull final Class<? extends HttpMessageConverter<?>> selectedConverterType,
            @NonNull final ServerHttpRequest request,
            @NonNull final ServerHttpResponse response) {
        if (logger.isDebugEnabled()) {
            logger.debug("ContentType: {}", selectedContentType);
        }
        if (APPLICATION_JSON.isCompatibleWith(selectedContentType)) {
            if (Supplier.class.isAssignableFrom(body.getClass())) {
                return Applicative.apply((Supplier<?>) body);
            }
            if (BooleanSupplier.class.isAssignableFrom(body.getClass())) {
                BooleanSupplier fn = (BooleanSupplier) body;
                return Applicative.from(fn.getAsBoolean());
            }
            return Applicative.from(body);
        }
        return body;
    }
}