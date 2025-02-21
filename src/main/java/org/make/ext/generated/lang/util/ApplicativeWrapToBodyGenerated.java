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
import java.util.Set;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.make.ext.generated.ThorFactory.ThorAttribute.THOR_LANG;
import static org.make.ext.generated.ThorFactory.ThorAttribute.THOR_TARGET_PACKAGE;

public class ApplicativeWrapToBodyGenerated extends ThorFactory {

    private final TopLevelClass compilationUnit;

    private final Context context;

    private final Properties properties;

    private final String name;

    public static ApplicativeWrapToBodyGenerated create(Properties properties, Context context) {
        return new ApplicativeWrapToBodyGenerated(properties, context);
    }

    public ApplicativeWrapToBodyGenerated(Properties properties, Context context) {
        this.properties = properties;
        this.context = context;
        this.name = "ApplicativeWrapToBody";
        FullyQualifiedJavaType javaType = new FullyQualifiedJavaType(String.join(".", ThorAttribute.getProperty(this.properties, THOR_TARGET_PACKAGE), THOR_LANG, this.name));
        this.compilationUnit = new TopLevelClass(javaType);
        this.compilationUnit.setVisibility(JavaVisibility.PUBLIC);
        this.compilationUnit.addAnnotation(GENERATED);
        this.compilationUnit.addAnnotation("@RestControllerAdvice");
        FullyQualifiedJavaType trait = new FullyQualifiedJavaType("ResponseBodyAdvice");
        trait.addTypeArgument(FullyQualifiedJavaType.getObjectInstance());
        this.compilationUnit.addSuperInterface(trait);

        Field f = new Field("logger", new FullyQualifiedJavaType("Logger"));
        f.setFinal(true);
        f.setVisibility(JavaVisibility.PRIVATE);
        f.setStatic(true);
        f.setInitializationString("LoggerFactory.getLogger(" + this.name + ".class" + ")");
        this.compilationUnit.addField(f);

        FullyQualifiedJavaType t = new FullyQualifiedJavaType("Set");
        t.addTypeArgument(new FullyQualifiedJavaType("Class<?>"));
        f = new Field("skipping", t);
        f.setVisibility(JavaVisibility.PRIVATE);
        f.setFinal(true);
        f.setStatic(true);
        f.setInitializationString("""
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
                                          ValueObjectWrap.class,
                                          byte[].class)
                """);
        this.compilationUnit.addField(f);
        this.compilationUnit.addImportedTypes(Sets.newHashSet(
                new FullyQualifiedJavaType("org.slf4j.Logger"),
                new FullyQualifiedJavaType("org.slf4j.LoggerFactory"),
                new FullyQualifiedJavaType("org.springframework.core.MethodParameter"),
                new FullyQualifiedJavaType("org.springframework.core.io.Resource"),
                new FullyQualifiedJavaType("org.springframework.http.MediaType"),
                new FullyQualifiedJavaType("org.springframework.http.ResponseEntity"),
                new FullyQualifiedJavaType("org.springframework.http.converter.HttpMessageConverter"),
                new FullyQualifiedJavaType("org.springframework.http.server.ServerHttpRequest"),
                new FullyQualifiedJavaType("org.springframework.http.server.ServerHttpResponse"),
                new FullyQualifiedJavaType("org.springframework.lang.NonNull"),
                new FullyQualifiedJavaType("org.springframework.web.bind.annotation.RestControllerAdvice"),
                new FullyQualifiedJavaType("org.springframework.web.context.request.async.DeferredResult"),
                new FullyQualifiedJavaType("org.springframework.web.context.request.async.WebAsyncTask"),
                new FullyQualifiedJavaType("org.springframework.web.servlet.function.ServerResponse"),
                new FullyQualifiedJavaType("org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice"),
                new FullyQualifiedJavaType("org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter"),
                new FullyQualifiedJavaType("org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody"),
                new FullyQualifiedJavaType("java.io.OutputStream"),
                new FullyQualifiedJavaType("java.util.Set"),
                new FullyQualifiedJavaType("java.util.function.BooleanSupplier"),
                new FullyQualifiedJavaType("java.util.function.Supplier"),
                new FullyQualifiedJavaType("jakarta.annotation.Generated")));

        this.compilationUnit.addStaticImports(Set.of("org.springframework.http.MediaType.APPLICATION_JSON"));

        Method supports = new Method("supports");
        supports.setVisibility(JavaVisibility.PUBLIC);
        supports.addAnnotation("@Override");
        supports.setReturnType(new FullyQualifiedJavaType("boolean"));
        Parameter parameter = new Parameter(new FullyQualifiedJavaType("MethodParameter"), "returnType");
        parameter.addAnnotation("@NonNull");
        supports.addParameter(parameter);
        // Class<? extends HttpMessageConverter<?>> converterType
        FullyQualifiedJavaType parameterType = new FullyQualifiedJavaType("Class");
        parameterType.addTypeArgument(new FullyQualifiedJavaType("? extends HttpMessageConverter<?>"));
        parameter = new Parameter(parameterType, "converterType");
        parameter.addAnnotation("@NonNull");
        supports.addParameter(parameter);
        supports.addBodyLine(CodeBlock.builder()
                .addStatement("final Class<?> target = returnType.getParameterType()")
                .addStatement("return skipping.stream().noneMatch(it -> it.isAssignableFrom(target))")
                .build().toString());
        this.compilationUnit.addMethod(supports);

        Method beforeBodyWrite = new Method("beforeBodyWrite");
        beforeBodyWrite.setVisibility(JavaVisibility.PUBLIC);
        beforeBodyWrite.addAnnotation("@Override");
        beforeBodyWrite.setReturnType(FullyQualifiedJavaType.getObjectInstance());
        beforeBodyWrite.addParameter(new Parameter(FullyQualifiedJavaType.getObjectInstance(), "body"));
        parameter = new Parameter(new FullyQualifiedJavaType("MethodParameter"), "returnType");
        parameter.addAnnotation("@NonNull");
        beforeBodyWrite.addParameter(parameter);
        parameter = new Parameter(new FullyQualifiedJavaType("MediaType"), "selectedContentType");
        parameter.addAnnotation("@NonNull");
        beforeBodyWrite.addParameter(parameter);

        parameter = new Parameter(parameterType, "selectedConverterType");
        parameter.addAnnotation("@NonNull");
        beforeBodyWrite.addParameter(parameter);
        parameter = new Parameter(new FullyQualifiedJavaType("ServerHttpRequest"), "request");
        parameter.addAnnotation("@NonNull");
        beforeBodyWrite.addParameter(parameter);
        parameter = new Parameter(new FullyQualifiedJavaType("ServerHttpResponse"), "response");
        parameter.addAnnotation("@NonNull");
        beforeBodyWrite.addParameter(parameter);
        beforeBodyWrite.addBodyLine(CodeBlock.builder()
                .beginControlFlow("if (logger.isDebugEnabled())")
                .addStatement("logger.debug(\"ContentType: {}\", selectedContentType)")
                .endControlFlow()
                .beginControlFlow("if (APPLICATION_JSON.isCompatibleWith(selectedContentType))")
                .beginControlFlow("if (Supplier.class.isAssignableFrom(body.getClass()))")
                .addStatement("return Applicative.apply((Supplier<?>) body)")
                .endControlFlow()
                .beginControlFlow("if (BooleanSupplier.class.isAssignableFrom(body.getClass()))")
                .addStatement("return Applicative.from(((BooleanSupplier) body).getAsBoolean())")
                .endControlFlow()
                .addStatement("return Applicative.from(body)")
                .endControlFlow()
                .addStatement("return body")
                .build().toString());
        this.compilationUnit.addMethod(beforeBodyWrite);
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
