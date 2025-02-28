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

public class ErrorRedirectGenerated extends ThorFactory {

    private final TopLevelClass compilationUnit;

    private final Context context;

    private final Properties properties;

    private final String name;

    public static ErrorRedirectGenerated create(Properties properties, Context context) {
        return new ErrorRedirectGenerated(properties, context);
    }

    public ErrorRedirectGenerated(Properties properties, Context context) {
        this.properties = properties;
        this.context = context;
        this.name = "HandleErrorRedirectHandler";
        FullyQualifiedJavaType javaType = new FullyQualifiedJavaType(String.join(".", ThorAttribute.getProperty(this.properties, THOR_TARGET_PACKAGE), THOR_LANG, this.name));
        this.compilationUnit = new TopLevelClass(javaType);
        this.compilationUnit.setVisibility(JavaVisibility.PUBLIC);

        this.compilationUnit.addImportedTypes(Sets.newHashSet(
                new FullyQualifiedJavaType("jakarta.servlet.http.HttpServletRequest"),
                new FullyQualifiedJavaType("jakarta.annotation.Generated"),
                new FullyQualifiedJavaType("org.slf4j.Logger"),
                new FullyQualifiedJavaType("org.slf4j.LoggerFactory"),
                new FullyQualifiedJavaType("org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController"),
                new FullyQualifiedJavaType("org.springframework.boot.web.servlet.error.ErrorAttributes"),
                new FullyQualifiedJavaType("org.springframework.http.ResponseEntity"),
                new FullyQualifiedJavaType("org.springframework.lang.NonNull"),
                new FullyQualifiedJavaType("org.springframework.stereotype.Controller"),
                new FullyQualifiedJavaType("org.springframework.web.bind.annotation.GetMapping"),
                new FullyQualifiedJavaType("org.springframework.web.bind.annotation.RequestMapping"),
                new FullyQualifiedJavaType("org.springframework.web.bind.annotation.ResponseBody")

        ));
        this.compilationUnit.addStaticImports(Sets.newHashSet(
                "io.vavr.API.Option",
                "jakarta.servlet.RequestDispatcher.ERROR_MESSAGE",
                "jakarta.servlet.RequestDispatcher.ERROR_STATUS_CODE",
                "org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR",
                "org.springframework.http.MediaType.APPLICATION_JSON_VALUE",
                "org.springframework.http.ResponseEntity.ok"
        ));

        this.compilationUnit.addAnnotation("@Controller");
        this.compilationUnit.addAnnotation("@RequestMapping(\"${server.error.path:${error.path:/error}}\")");
        this.compilationUnit.addAnnotation(GENERATED);
        this.compilationUnit.setSuperClass(new FullyQualifiedJavaType("AbstractErrorController"));

        Field f = new Field("logger", new FullyQualifiedJavaType("Logger"));
        f.setVisibility(JavaVisibility.PRIVATE);
        f.setFinal(true);
        f.setFinal(true);
        f.setInitializationString("LoggerFactory.getLogger(" + this.compilationUnit.getType().getShortName() + ".class" + ")");
        this.compilationUnit.addField(f);

        Method method = new Method(this.compilationUnit.getType().getShortName());
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setConstructor(true);
        method.addParameter(new Parameter(new FullyQualifiedJavaType("ErrorAttributes"), "attributes"));
        method.addBodyLine(CodeBlock.builder().
                addStatement("super(attributes)").
                build().toString());
        this.compilationUnit.addMethod(method);

        method = new Method("error");
        method.setVisibility(JavaVisibility.PUBLIC);
        FullyQualifiedJavaType returnType = new FullyQualifiedJavaType("ResponseEntity");
        returnType.addTypeArgument(new FullyQualifiedJavaType("Applicative<String>"));
        method.setReturnType(returnType);
        method.addAnnotation("@GetMapping(produces = APPLICATION_JSON_VALUE)");
        method.addAnnotation("@ResponseBody");
        Parameter parameter = new Parameter(new FullyQualifiedJavaType("HttpServletRequest"), "r");
        parameter.addAnnotation("@NonNull");
        method.addParameter(parameter);
        method.addBodyLine(CodeBlock.builder()
                .addStatement("Integer statusCode = Option(r.getAttribute(ERROR_STATUS_CODE)).map(it -> Integer.valueOf(String.valueOf(it))).getOrElse(INTERNAL_SERVER_ERROR.value())")
                .addStatement("String message = Option(r.getAttribute(ERROR_MESSAGE)).map(String::valueOf).getOrElse(INTERNAL_SERVER_ERROR.getReasonPhrase())")
                .beginControlFlow("if (logger.isDebugEnabled())")
                .addStatement("logger.debug(\"handle error: {}, {}\", statusCode, message)")
                .endControlFlow()
                .addStatement("return ok(Applicative.from(statusCode, message))")
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
