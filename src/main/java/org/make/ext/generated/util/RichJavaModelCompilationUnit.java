package org.make.ext.generated.util;

import com.squareup.javapoet.CodeBlock;
import org.make.ext.generated.DefaultJavaField;
import org.make.ext.generated.ThorFactory;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.config.Context;

import java.io.Serializable;
import java.util.List;
import java.util.Properties;

import static com.google.common.collect.Sets.newHashSet;
import static org.make.ext.generated.ThorFactory.GENERATED;
import static org.make.ext.generated.ThorFactory.ThorAttribute.THOR_DEFAULT_ENTITY_NAME;
import static org.make.ext.generated.ThorFactory.ThorAttribute.THOR_DEFAULT_POJO_SUFFIX;
import static org.make.ext.generated.ThorFactory.ThorAttribute.THOR_LANG;
import static org.make.ext.generated.ThorFactory.ThorAttribute.THOR_TARGET_PACKAGE;
import static org.make.ext.generated.ThorFactory.ThorAttribute.THOR_VIEWS;
import static org.mybatis.generator.api.dom.java.JavaVisibility.PUBLIC;

public final class RichJavaModelCompilationUnit extends RichTopLevelClassVisitor {

    private final Properties properties;

    private final IntrospectedTable introspectedTable;

    private final String name;

    public RichJavaModelCompilationUnit(Properties properties, Context context, IntrospectedTable introspectedTable) {
        super(context);
        this.introspectedTable = introspectedTable;
        this.properties = properties;
        this.name = THOR_DEFAULT_ENTITY_NAME;
    }

    public static RichJavaModelCompilationUnit create(Properties properties, Context context, IntrospectedTable introspectedTable) {
        return new RichJavaModelCompilationUnit(properties, context, introspectedTable);
    }

    @Override
    public TopLevelClass visit(TopLevelClass compilationUnit) {
        String name = String.join(".", ThorFactory.ThorAttribute.getProperty(this.properties, THOR_TARGET_PACKAGE), THOR_LANG, this.name);
        FullyQualifiedJavaType base = new FullyQualifiedJavaType(name);
        if (!introspectedTable.hasPrimaryKeyColumns()) {
            throw new IllegalArgumentException();
        }
        List<IntrospectedColumn> columns = introspectedTable.getPrimaryKeyColumns();
        base.addTypeArgument(new FullyQualifiedJavaType(columns.get(0).getFullyQualifiedJavaType().getShortName()));
        compilationUnit.setSuperClass(base);
        compilationUnit.addAnnotation("@Data");
        compilationUnit.addAnnotation("@EqualsAndHashCode(callSuper = true)");
        compilationUnit.addAnnotation(GENERATED);

        DefaultJavaField.SERIAL_VERSION_UID.apply(compilationUnit);
        compilationUnit.addSuperInterface(new FullyQualifiedJavaType(Serializable.class.getName()));

        FullyQualifiedJavaType function = new FullyQualifiedJavaType("java.util.function.Function");
        function.addTypeArgument(new FullyQualifiedJavaType(compilationUnit.getType().getShortName()));
        FullyQualifiedJavaType returnType = new FullyQualifiedJavaType(String.join(".", ThorFactory.ThorAttribute.getProperty(properties, THOR_TARGET_PACKAGE), THOR_VIEWS, compilationUnit.getType().getShortName() + THOR_DEFAULT_POJO_SUFFIX));
        function.addTypeArgument(returnType);
        compilationUnit.addSuperInterface(function);
        compilationUnit.getMethods().clear();
        Method method = new Method("empty");
        method.setVisibility(PUBLIC);
        method.setStatic(true);
        method.setReturnType(compilationUnit.getType());
        method.addBodyLine("return new " + compilationUnit.getType().getShortName() + "();");
        compilationUnit.addMethod(method);

        Method apply = new Method("apply");
        apply.addAnnotation("@Override");
        apply.setVisibility(PUBLIC);
        apply.setReturnType(returnType);
        Parameter parameter = new Parameter(new FullyQualifiedJavaType(compilationUnit.getType().getShortName()), "value");
        parameter.addAnnotation("@Nullable");
        apply.addParameter(parameter);
        apply.addBodyLine(CodeBlock.builder()
                        .beginControlFlow("if (Objects.isNull(value))")
                        .addStatement("value = this")
                        .endControlFlow()
                .addStatement(returnType.getShortName() + " " + "object = " + returnType.getShortName() + ".empty()")
                .addStatement("BeanUtils.copyProperties(value, object)")
                .addStatement("return object")
                .build().toString());
        compilationUnit.addMethod(apply);

        compilationUnit.addImportedTypes(newHashSet(
                new FullyQualifiedJavaType("jakarta.annotation.Generated"),
                new FullyQualifiedJavaType("lombok.Data"),
                new FullyQualifiedJavaType("lombok.EqualsAndHashCode"),
                new FullyQualifiedJavaType("java.util.function.Function"),
                new FullyQualifiedJavaType("jakarta.annotation.Nullable"),
                new FullyQualifiedJavaType("java.util.Objects"),
                new FullyQualifiedJavaType("org.springframework.beans.BeanUtils"),
                base,
                returnType
        ));
        return compilationUnit;
    }
}
