package org.make.ext.generated.lang.util;

import org.make.ext.generated.ThorFactory;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TypeParameter;
import org.mybatis.generator.config.Context;

import java.util.Properties;
import java.util.Set;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.make.ext.generated.ThorFactory.ThorAttribute.THOR_LANG;
import static org.make.ext.generated.ThorFactory.ThorAttribute.THOR_TARGET_PACKAGE;

public class ValueObjectWrapGenerated extends ThorFactory {

    private final Interface compilationUnit;

    private final Context context;

    private final Properties properties;

    private final String name;

    public static ValueObjectWrapGenerated create(Properties properties, Context context) {
        return new ValueObjectWrapGenerated(properties, context);
    }

    public ValueObjectWrapGenerated(Properties properties, Context context) {
        this.properties = properties;
        this.context = context;
        this.name = "ValueObjectWrap";
        FullyQualifiedJavaType javaType = new FullyQualifiedJavaType(String.join(".", ThorAttribute.getProperty(this.properties, THOR_TARGET_PACKAGE), THOR_LANG, this.name));
        this.compilationUnit = new Interface(javaType);
        this.compilationUnit.setVisibility(JavaVisibility.PUBLIC);
        this.compilationUnit.addAnnotation(GENERATED);
        this.compilationUnit.addTypeParameter(new TypeParameter("T"));

        Method method = new Method("getState");
        method.setAbstract(true);
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(new FullyQualifiedJavaType("int"));
        this.compilationUnit.addMethod(method);

        method = new Method("getError");
        method.setAbstract(true);
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(new FullyQualifiedJavaType("String"));
        this.compilationUnit.addMethod(method);

        method = new Method("getBody");
        method.setAbstract(true);
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(new FullyQualifiedJavaType("T"));
        this.compilationUnit.addMethod(method);

        this.compilationUnit.addImportedTypes(Set.of(
                new FullyQualifiedJavaType("jakarta.annotation.Generated")
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
