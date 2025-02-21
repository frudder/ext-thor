package org.make.ext.generated.lang.util;

import com.google.common.collect.Sets;
import com.squareup.javapoet.CodeBlock;
import org.make.ext.generated.DefaultJavaField;
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

public class ThorWrapExceptionGenerated extends ThorFactory {

    private final TopLevelClass compilationUnit;

    private final Context context;

    private final Properties properties;

    private final String name;

    public static ThorWrapExceptionGenerated create(Properties properties, Context context) {
        return new ThorWrapExceptionGenerated(properties, context);
    }

    public ThorWrapExceptionGenerated(Properties properties, Context context) {
        this.properties = properties;
        this.context = context;
        this.name = "ThorWrapException";
        FullyQualifiedJavaType javaType = new FullyQualifiedJavaType(String.join(".", ThorAttribute.getProperty(this.properties, THOR_TARGET_PACKAGE), THOR_LANG, this.name));
        this.compilationUnit = new TopLevelClass(javaType);
        this.compilationUnit.setVisibility(JavaVisibility.PUBLIC);
        this.compilationUnit.addAnnotation(GENERATED);
        DefaultJavaField.SERIAL_VERSION_UID.apply(this.compilationUnit);
        this.compilationUnit.setSuperClass(new FullyQualifiedJavaType("RuntimeException"));
        this.compilationUnit.addSuperInterface(new FullyQualifiedJavaType("ErrorHandler"));

        Field f = new Field("error", new FullyQualifiedJavaType("Integer"));
        f.setVisibility(JavaVisibility.PRIVATE);
        f.setFinal(true);
        this.compilationUnit.addField(f);


        Method method = new Method(this.compilationUnit.getType().getShortName());
        method.setConstructor(true);
        method.setVisibility(JavaVisibility.PRIVATE);
        method.addParameter(new Parameter(new FullyQualifiedJavaType("Integer"), "errorValue"));
        method.addParameter(new Parameter(new FullyQualifiedJavaType("String"), "message"));
        method.addParameter(new Parameter(new FullyQualifiedJavaType("Throwable"), "cause"));
        method.addBodyLine(CodeBlock.builder().
                addStatement("super(message, cause)").
                addStatement("this.error = errorValue").
                build().toString());
        this.compilationUnit.addMethod(method);

        method = new Method(this.compilationUnit.getType().getShortName());
        method.setConstructor(true);
        method.setVisibility(JavaVisibility.PRIVATE);
        method.addParameter(new Parameter(new FullyQualifiedJavaType("Integer"), "errorValue"));
        method.addParameter(new Parameter(new FullyQualifiedJavaType("String"), "message"));
        method.addBodyLine(CodeBlock.builder().
                addStatement("this(errorValue, message, null)").
                build().toString());
        this.compilationUnit.addMethod(method);

        method = new Method(this.compilationUnit.getType().getShortName());
        method.setConstructor(true);
        method.setVisibility(JavaVisibility.PUBLIC);
        method.addParameter(new Parameter(new FullyQualifiedJavaType("Integer"), "errorValue"));
        method.addParameter(new Parameter(new FullyQualifiedJavaType("Throwable"), "err"));
        method.addBodyLine(CodeBlock.builder().
                addStatement("this(errorValue, nullToEmpty(err.getMessage()), err)").
                build().toString());
        this.compilationUnit.addMethod(method);

        method = new Method("of");
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setStatic(true);
        method.setReturnType(this.compilationUnit.getType());
        Parameter parameter = new Parameter(new FullyQualifiedJavaType("Integer"), "errorValue");
        parameter.addAnnotation("@Nullable");
        method.addParameter(parameter);
        method.addParameter(new Parameter(new FullyQualifiedJavaType("Throwable"), "err"));
        method.addBodyLine(CodeBlock.builder().
                addStatement("return new ThorWrapException(isEmpty(errorValue) ? INTERNAL_SERVER_ERROR.value() : errorValue, err)").
                build().toString());
        this.compilationUnit.addMethod(method);

        method = new Method("of");
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setStatic(true);
        method.setReturnType(this.compilationUnit.getType());
        method.addParameter(new Parameter(new FullyQualifiedJavaType("Throwable"), "err"));
        method.addBodyLine(CodeBlock.builder().
                addStatement("return of(null, err)").
                build().toString());
        this.compilationUnit.addMethod(method);

        method = new Method("of");
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setStatic(true);
        method.setReturnType(this.compilationUnit.getType());
        method.addParameter(new Parameter(new FullyQualifiedJavaType("Integer"), "errorValue"));
        method.addParameter(new Parameter(new FullyQualifiedJavaType("String"), "message"));
        method.addBodyLine(CodeBlock.builder().
                addStatement("return new ThorWrapException(errorValue, nullToEmpty(message))").
                build().toString());
        this.compilationUnit.addMethod(method);

        method = new Method("handleError");
        method.setVisibility(JavaVisibility.PUBLIC);
        method.addAnnotation("@Override");
        method.setReturnType(new FullyQualifiedJavaType("void"));
        parameter = new Parameter(new FullyQualifiedJavaType("Throwable"), "err");
        parameter.addAnnotation("@Nullable");
        method.addParameter(parameter);
        method.addBodyLine(CodeBlock.builder()
                .beginControlFlow("if (nonNull(err))")
                .addStatement("throw ThorWrapException.of(err)")
                .endControlFlow()
                .build().toString());
        this.compilationUnit.addMethod(method);

        method = new Method("getError");
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(new FullyQualifiedJavaType("Integer"));
        method.addBodyLine(CodeBlock.builder().
                addStatement("return error").
                build().toString());
        this.compilationUnit.addMethod(method);

        this.compilationUnit.addImportedTypes(Sets.newHashSet(
                new FullyQualifiedJavaType("org.springframework.lang.Nullable"),
                new FullyQualifiedJavaType("org.springframework.util.ErrorHandler"),
                new FullyQualifiedJavaType("java.io.Serial"),
                new FullyQualifiedJavaType("jakarta.annotation.Generated")
        ));

        this.compilationUnit.addStaticImports(Sets.newHashSet(
                "com.google.common.base.Strings.nullToEmpty",
                "java.util.Objects.nonNull",
                "org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR",
                "org.springframework.util.ObjectUtils.isEmpty"
        ));
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
