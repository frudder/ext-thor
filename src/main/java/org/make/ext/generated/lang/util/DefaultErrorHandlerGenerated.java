package org.make.ext.generated.lang.util;

import com.google.common.collect.Sets;
import com.squareup.javapoet.CodeBlock;
import org.make.ext.generated.ThorFactory;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.config.Context;

import java.util.Properties;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.make.ext.generated.ThorFactory.ThorAttribute.THOR_LANG;
import static org.make.ext.generated.ThorFactory.ThorAttribute.THOR_TARGET_PACKAGE;

public class DefaultErrorHandlerGenerated extends ThorFactory {

    private final TopLevelClass compilationUnit;

    private final Context context;

    private final Properties properties;

    private final String name;

    public static DefaultErrorHandlerGenerated create(Properties properties, Context context) {
        return new DefaultErrorHandlerGenerated(properties, context);
    }

    public DefaultErrorHandlerGenerated(Properties properties, Context context) {
        this.properties = properties;
        this.context = context;
        this.name = "DefaultErrorHandler";
        FullyQualifiedJavaType javaType = new FullyQualifiedJavaType(String.join(".", ThorAttribute.getProperty(this.properties, THOR_TARGET_PACKAGE), THOR_LANG, this.name));
        this.compilationUnit = new TopLevelClass(javaType);
        this.compilationUnit.setVisibility(JavaVisibility.PUBLIC);
        this.compilationUnit.addAnnotation("@RestControllerAdvice");
        this.compilationUnit.addAnnotation(GENERATED);
        this.compilationUnit.addSuperInterface(new FullyQualifiedJavaType("ErrorHandler"));

        this.compilationUnit.addImportedTypes(Sets.newHashSet(
                new FullyQualifiedJavaType("io.vavr.collection.Stream"),
                new FullyQualifiedJavaType("jakarta.annotation.Generated"),
                new FullyQualifiedJavaType("jakarta.validation.ConstraintViolation"),
                new FullyQualifiedJavaType("jakarta.validation.ConstraintViolationException"),
                new FullyQualifiedJavaType("org.slf4j.Logger"),
                new FullyQualifiedJavaType("org.slf4j.LoggerFactory"),
                new FullyQualifiedJavaType("org.springframework.http.ResponseEntity"),
                new FullyQualifiedJavaType("org.springframework.lang.NonNull"),
                new FullyQualifiedJavaType("org.springframework.util.CollectionUtils"),
                new FullyQualifiedJavaType("org.springframework.util.ErrorHandler"),
                new FullyQualifiedJavaType("org.springframework.web.bind.MethodArgumentNotValidException"),
                new FullyQualifiedJavaType("org.springframework.web.bind.annotation.ExceptionHandler"),
                new FullyQualifiedJavaType("org.springframework.web.bind.annotation.ResponseStatus"),
                new FullyQualifiedJavaType("org.springframework.web.bind.annotation.RestControllerAdvice"),
                new FullyQualifiedJavaType("java.util.Set"),
                new FullyQualifiedJavaType("java.util.concurrent.TimeoutException")
        ));

        this.compilationUnit.addStaticImports(Sets.newHashSet(
                "com.google.common.base.Strings.nullToEmpty",
                "io.vavr.API.Option",
                "org.springframework.http.HttpStatus.BAD_REQUEST",
                "org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR",
                "org.springframework.http.HttpStatus.REQUEST_TIMEOUT",
                "org.springframework.http.ResponseEntity.badRequest",
                "org.springframework.http.ResponseEntity.internalServerError",
                "org.springframework.http.ResponseEntity.status"
        ));


        Field f = new Field("logger", new FullyQualifiedJavaType("Logger"));
        f.setVisibility(JavaVisibility.PRIVATE);
        f.setStatic(true);
        f.setFinal(true);
        f.setInitializationString("LoggerFactory.getLogger(" + this.compilationUnit.getType().getShortName() + ".class" + ")");
        this.compilationUnit.addField(f);

        Method method = new Method("fallback");
        method.setVisibility(JavaVisibility.PUBLIC);
        method.addAnnotation("@ExceptionHandler(value = ThorWrapException.class)");
        method.addAnnotation("@ResponseStatus(value = INTERNAL_SERVER_ERROR)");
        FullyQualifiedJavaType returnType = new FullyQualifiedJavaType("ResponseEntity");
        returnType.addTypeArgument(new FullyQualifiedJavaType("Applicative<String>"));
        method.setReturnType(returnType);
        method.addParameter(new Parameter(new FullyQualifiedJavaType("ThorWrapException"), "err"));
        method.addBodyLine(CodeBlock.builder().
                addStatement("handleError(err)")
                .addStatement("return internalServerError().body(Applicative.from(err.getError(), nullToEmpty(err.getMessage())))").
                build().toString());
        this.compilationUnit.addMethod(method);

        method = new Method("fallback");
        method.setVisibility(JavaVisibility.PUBLIC);
        method.addAnnotation("@ExceptionHandler(value = TimeoutException.class)");
        method.addAnnotation("@ResponseStatus(value = REQUEST_TIMEOUT)");
        method.setReturnType(returnType);
        method.addParameter(new Parameter(new FullyQualifiedJavaType("TimeoutException"), "err"));
        method.addBodyLine(CodeBlock.builder().
                addStatement("handleError(err)")
                .addStatement("return status(REQUEST_TIMEOUT).body(Applicative.from(err.getMessage()))").
                build().toString());
        this.compilationUnit.addMethod(method);

        method = new Method("fallback");
        method.setVisibility(JavaVisibility.PUBLIC);
        method.addAnnotation("@ExceptionHandler(value = MethodArgumentNotValidException.class)");
        method.addAnnotation("@ResponseStatus(value = BAD_REQUEST)");
        method.setReturnType(returnType);
        method.addParameter(new Parameter(new FullyQualifiedJavaType("MethodArgumentNotValidException"), "err"));
        method.addBodyLine(CodeBlock.builder().
                addStatement("handleError(err)")
                .addStatement("String error = err.hasFieldErrors() ? Stream.ofAll(err.getFieldErrors()).map(it -> String.format(\"%s.%s: %s\", Option(err.getTarget()).map(h -> h.getClass().getSimpleName()).getOrElse(it.getObjectName()), it.getField(), it.getDefaultMessage())).mkString(\";\") : nullToEmpty(err.getMessage())")
                .addStatement("return badRequest().body(Applicative.from(BAD_REQUEST.value(), error))").
                build().toString());
        this.compilationUnit.addMethod(method);

        method = new Method("fallback");
        method.setVisibility(JavaVisibility.PUBLIC);
        method.addAnnotation("@ExceptionHandler(value = ConstraintViolationException.class)");
        method.addAnnotation("@ResponseStatus(value = BAD_REQUEST)");
        method.setReturnType(returnType);
        method.addParameter(new Parameter(new FullyQualifiedJavaType("ConstraintViolationException"), "err"));
        method.addBodyLine(CodeBlock.builder().
                addStatement("handleError(err)")
                .addStatement("Set<ConstraintViolation<?>> iter = err.getConstraintViolations()")
                .addStatement("String error = CollectionUtils.isEmpty(iter) ? nullToEmpty(err.getMessage()) : Stream.ofAll(iter).map(it -> String.format(\"%s: %s\", it.getPropertyPath().toString(), it.getMessage())).mkString(\";\")")
                .addStatement("return badRequest().body(Applicative.from(BAD_REQUEST.value(), error))").
                build().toString());
        this.compilationUnit.addMethod(method);

        method = new Method("fallback");
        method.setVisibility(JavaVisibility.PUBLIC);
        method.addAnnotation("@ExceptionHandler(value = RuntimeException.class)");
        method.addAnnotation("@ResponseStatus(value = INTERNAL_SERVER_ERROR)");
        method.setReturnType(returnType);
        method.addParameter(new Parameter(new FullyQualifiedJavaType("RuntimeException"), "err"));
        method.addBodyLine(CodeBlock.builder().
                addStatement("handleError(err)")
                .addStatement("return internalServerError().body(Applicative.from(INTERNAL_SERVER_ERROR.value(), nullToEmpty(err.getMessage())))").
                build().toString());
        this.compilationUnit.addMethod(method);

        method = new Method("fallback");
        method.setVisibility(JavaVisibility.PUBLIC);
        method.addAnnotation("@ExceptionHandler(value = Exception.class)");
        method.addAnnotation("@ResponseStatus(value = INTERNAL_SERVER_ERROR)");
        method.setReturnType(returnType);
        method.addParameter(new Parameter(new FullyQualifiedJavaType("Exception"), "err"));
        method.addBodyLine(CodeBlock.builder().
                addStatement("handleError(err)")
                .addStatement("return internalServerError().body(Applicative.from(INTERNAL_SERVER_ERROR.value(), nullToEmpty(err.getMessage())))").
                build().toString());
        this.compilationUnit.addMethod(method);

        method = new Method("handleError");
        method.addAnnotation("@Override");
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(new FullyQualifiedJavaType("void"));
        Parameter parameter = new Parameter(new FullyQualifiedJavaType("Throwable"), "err");
        parameter.addAnnotation("@NonNull");
        method.addParameter(parameter);
        method.addBodyLine(CodeBlock.builder()
                .beginControlFlow("if (logger.isErrorEnabled())")
                .addStatement("logger.error(\"handleError:\", err)")
                .endControlFlow()
                .build().toString());
        this.compilationUnit.addMethod(method);
    }


    @Override
    public GeneratedJavaFile make() {
        return new GeneratedJavaFile(compilationUnit,
                context.getJavaModelGeneratorConfiguration().getTargetProject(),
                UTF_8.name(),
                context.getJavaFormatter());
    }

    @Override
    public String getName() {
        return this.name;
    }
}
