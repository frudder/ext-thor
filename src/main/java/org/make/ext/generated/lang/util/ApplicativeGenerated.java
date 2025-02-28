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
import org.mybatis.generator.api.dom.java.TypeParameter;
import org.mybatis.generator.config.Context;

import java.util.Properties;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.make.ext.generated.ThorFactory.ThorAttribute.THOR_LANG;
import static org.make.ext.generated.ThorFactory.ThorAttribute.THOR_TARGET_PACKAGE;

public class ApplicativeGenerated extends ThorFactory {

    private final TopLevelClass compilationUnit;

    private final Context context;

    private final Properties properties;

    private final String name;

    public static ApplicativeGenerated create(Properties properties, Context context) {
        return new ApplicativeGenerated(properties, context);
    }

    public ApplicativeGenerated(Properties properties, Context context) {
        this.properties = properties;
        this.context = context;
        this.name = "Applicative";
        FullyQualifiedJavaType javaType = new FullyQualifiedJavaType(String.join(".", ThorAttribute.getProperty(this.properties, THOR_TARGET_PACKAGE), THOR_LANG, this.name));
        this.compilationUnit = new TopLevelClass(javaType);
        this.compilationUnit.setVisibility(JavaVisibility.PUBLIC);
        this.compilationUnit.setFinal(true);
        this.compilationUnit.addAnnotation("@SuppressWarnings(value = \"unchecked\")");
        this.compilationUnit.addTypeParameter(new TypeParameter("T"));
        FullyQualifiedJavaType value = new FullyQualifiedJavaType(javaType.getPackageName() + "." + "ValueObjectWrap");
        value.addTypeArgument(new FullyQualifiedJavaType("T"));
        this.compilationUnit.addSuperInterface(value);
        this.compilationUnit.addSuperInterface(new FullyQualifiedJavaType("java.io.Serializable"));
        this.compilationUnit.addAnnotation(GENERATED);
        DefaultJavaField.SERIAL_VERSION_UID.apply(this.compilationUnit);

        Field body = new Field("body", new FullyQualifiedJavaType("T"));
        body.setVisibility(JavaVisibility.PRIVATE);
        body.setFinal(true);
        this.compilationUnit.addField(body);

        Field error = new Field("error", new FullyQualifiedJavaType("String"));
        error.setVisibility(JavaVisibility.PRIVATE);
        error.setFinal(true);
        this.compilationUnit.addField(error);

        Field state = new Field("state", new FullyQualifiedJavaType("int"));
        state.setVisibility(JavaVisibility.PRIVATE);
        state.setFinal(true);
        this.compilationUnit.addField(state);

        Method method = new Method(javaType.getShortName());
        method.setConstructor(true);
        method.setVisibility(JavaVisibility.PRIVATE);
        // int state, String error, T body
        method.addParameter(new Parameter(new FullyQualifiedJavaType("int"), "state"));
        method.addParameter(new Parameter(new FullyQualifiedJavaType("String"), "error"));
        method.addParameter(new Parameter(new FullyQualifiedJavaType("T"), "body"));
        method.addBodyLine(CodeBlock.builder()
                .addStatement("this.state = state")
                .addStatement("this.error = error")
                .addStatement("this.body = body")
                .build().toString());
        this.compilationUnit.addMethod(method);

        Method getBody = new Method("getBody");
        getBody.addAnnotation("@Override");
        getBody.setVisibility(JavaVisibility.PUBLIC);
        getBody.setReturnType(new FullyQualifiedJavaType("T"));
        getBody.addBodyLine("return body;");
        this.compilationUnit.addMethod(getBody);

        Method getState = new Method("getState");
        getState.addAnnotation("@Override");
        getState.setVisibility(JavaVisibility.PUBLIC);
        getState.setReturnType(new FullyQualifiedJavaType("int"));
        getState.addBodyLine("return state;");
        this.compilationUnit.addMethod(getState);

        Method getError = new Method("getError");
        getError.addAnnotation("@Override");
        getError.setVisibility(JavaVisibility.PUBLIC);
        getError.setReturnType(new FullyQualifiedJavaType("String"));
        getError.addBodyLine("return error;");
        this.compilationUnit.addMethod(getError);


        Method apply = new Method("apply");
        apply.setVisibility(JavaVisibility.PUBLIC);
        apply.setStatic(true);
        apply.addTypeParameter(new TypeParameter("T"));
        FullyQualifiedJavaType returnType = new FullyQualifiedJavaType(this.name);
        returnType.addTypeArgument(new FullyQualifiedJavaType("T"));
        apply.setReturnType(returnType);
        apply.addParameter(new Parameter(new FullyQualifiedJavaType("int"), "state"));
        apply.addParameter(new Parameter(new FullyQualifiedJavaType("String"), "error"));
        apply.addParameter(new Parameter(new FullyQualifiedJavaType("T"), "body"));
        apply.addBodyLine(CodeBlock.builder().
                addStatement("return new Applicative<>(state, error, body)").
                build().toString());
        this.compilationUnit.addMethod(apply);

        apply = new Method("apply");
        apply.addTypeParameter(new TypeParameter("T"));
        apply.setVisibility(JavaVisibility.PUBLIC);
        apply.setStatic(true);
        apply.setReturnType(returnType);
        FullyQualifiedJavaType function = new FullyQualifiedJavaType("Supplier");
        function.addTypeArgument(new FullyQualifiedJavaType("T"));
        apply.addParameter(new Parameter(function, "function"));
        apply.addBodyLine(CodeBlock.builder().
                addStatement("return apply(null, function)").
                build().toString());
        this.compilationUnit.addMethod(apply);

        apply = new Method("apply");
        apply.addTypeParameter(new TypeParameter("T"));
        apply.setVisibility(JavaVisibility.PUBLIC);
        apply.setStatic(true);
        apply.setReturnType(returnType);
        apply.addParameter(new Parameter(new FullyQualifiedJavaType("Integer"), "errorValue"));
        apply.addParameter(new Parameter(function, "function"));
        apply.addBodyLine(CodeBlock.builder().
                addStatement("return Try(function::get).fold(err -> {throw new ThorWrapException(errorValue, err);}, Applicative::from)")
                .build().toString());
        this.compilationUnit.addMethod(apply);

        Method from = new Method("from");
        from.setVisibility(JavaVisibility.PUBLIC);
        from.setStatic(true);
        from.addTypeParameter(new TypeParameter("T"));
        from.setReturnType(returnType);
        from.addParameter(new Parameter(new FullyQualifiedJavaType("int"), "state"));
        from.addParameter(new Parameter(new FullyQualifiedJavaType("String"), "error"));
        from.addParameter(new Parameter(new FullyQualifiedJavaType("T"), "body"));
        from.addBodyLine(CodeBlock.builder().
                addStatement("return apply(state, error, Objects.isNull(body) ? (T) Map.of() : body)").
                build().toString());
        this.compilationUnit.addMethod(from);

        apply = new Method("from");
        apply.setVisibility(JavaVisibility.PUBLIC);
        apply.setStatic(true);
        apply.addTypeParameter(new TypeParameter("T"));
        apply.setReturnType(returnType);
        apply.addParameter(new Parameter(new FullyQualifiedJavaType("T"), "body"));
        apply.addBodyLine(CodeBlock.builder().
                addStatement("return from(OK.value(), OK.getReasonPhrase(), body)").
                build().toString());
        this.compilationUnit.addMethod(apply);

        from = new Method("from");
        from.addTypeParameter(new TypeParameter("T"));
        from.setVisibility(JavaVisibility.PUBLIC);
        from.setStatic(true);
        from.addParameter(new Parameter(new FullyQualifiedJavaType("int"), "state"));
        from.addParameter(new Parameter(new FullyQualifiedJavaType("String"), "error"));
        from.setReturnType(returnType);
        from.addBodyLine(CodeBlock.builder()
                .addStatement("return from(state, error, null)")
                .build().toString());
        this.compilationUnit.addMethod(from);

        from = new Method("from");
        from.addTypeParameter(new TypeParameter("T"));
        from.setVisibility(JavaVisibility.PUBLIC);
        from.setStatic(true);
        from.setReturnType(returnType);
        from.addParameter(new Parameter(new FullyQualifiedJavaType("HttpStatus"), "http"));
        from.addParameter(new Parameter(new FullyQualifiedJavaType("T"), "body"));
        from.addBodyLine(CodeBlock.builder()
                .addStatement("return from(http.value(), http.getReasonPhrase(), body)")
                .build().toString());
        this.compilationUnit.addMethod(from);

        from = new Method("from");
        from.addTypeParameter(new TypeParameter("T"));
        from.setVisibility(JavaVisibility.PUBLIC);
        from.setStatic(true);
        from.setReturnType(returnType);
        from.addBodyLine(CodeBlock.builder()
                .addStatement("return from(OK.value(), OK.getReasonPhrase(), null)")
                .build().toString());
        this.compilationUnit.addMethod(from);

        from = new Method("from");
        from.addTypeParameter(new TypeParameter("T"));
        from.setVisibility(JavaVisibility.PUBLIC);
        from.setStatic(true);
        from.setReturnType(returnType);
        from.addParameter(new Parameter(new FullyQualifiedJavaType("HttpStatus"), "http"));
        from.addBodyLine(CodeBlock.builder()
                .addStatement("return from(http.value(), http.getReasonPhrase())")
                .build().toString());
        this.compilationUnit.addMethod(from);

        this.compilationUnit.addImportedTypes(Sets.newHashSet(
                new FullyQualifiedJavaType("org.springframework.http.HttpStatus"),
                new FullyQualifiedJavaType("java.io.Serial"),
                new FullyQualifiedJavaType("java.io.Serializable"),
                new FullyQualifiedJavaType("java.util.Map"),
                new FullyQualifiedJavaType("java.util.Objects"),
                new FullyQualifiedJavaType("java.util.function.Supplier"),
                new FullyQualifiedJavaType("jakarta.annotation.Generated")
        ));

        this.compilationUnit.addStaticImports(Sets.newHashSet(
                "io.vavr.API.Try",
                "org.springframework.http.HttpStatus.OK"
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
