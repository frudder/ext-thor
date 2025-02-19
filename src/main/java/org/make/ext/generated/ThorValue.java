package org.make.ext.generated;

import com.google.common.collect.Sets;
import com.squareup.javapoet.CodeBlock;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.config.Context;

import java.io.Serializable;
import java.util.List;
import java.util.Properties;

import static com.google.common.collect.Lists.newArrayList;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.make.ext.generated.DefaultJavaField.SERIAL_VERSION_UID;
import static org.make.ext.generated.ThorFactory.ThorAttribute.THOR_DEFAULT_POJO_SUFFIX;
import static org.make.ext.generated.ThorFactory.ThorAttribute.THOR_TARGET_PACKAGE;
import static org.mybatis.generator.api.dom.java.JavaVisibility.PRIVATE;
import static org.mybatis.generator.api.dom.java.JavaVisibility.PUBLIC;

public class ThorValue extends ThorFactory {

    private final TopLevelClass compilationUnit;

    private final String name;

    private final Context context;

    private final IntrospectedTable introspectedTable;

    private final Properties properties;

    public static ThorValue create(Properties properties, Context context, IntrospectedTable introspectedTable) {
        return new ThorValue(properties, context, introspectedTable);
    }

    private ThorValue(final Properties properties, final Context context, final IntrospectedTable introspectedTable) {
        this.properties = properties;
        this.introspectedTable = introspectedTable;
        FullyQualifiedJavaType domain = new FullyQualifiedJavaType(this.introspectedTable.getBaseRecordType());
        this.name = domain.getShortName();
        this.context = context;
        String name = String.join(".", ThorAttribute.getProperty(this.properties, THOR_TARGET_PACKAGE), "views");
        FullyQualifiedJavaType token = new FullyQualifiedJavaType(name + "." + this.name + "Value");
        this.compilationUnit = new TopLevelClass(token);
        this.compilationUnit.setVisibility(PUBLIC);
        this.compilationUnit.addAnnotation(GENERATED);
        this.compilationUnit.addAnnotation("@Data");
        this.compilationUnit.addAnnotation("@EqualsAndHashCode(callSuper = false)");
        this.compilationUnit.addAnnotation("@Schema(name=\"" + this.name + "Value\")");

        FullyQualifiedJavaType value = new FullyQualifiedJavaType("org.make.ext.lang.ValueObject");
        value.addTypeArgument(new FullyQualifiedJavaType(this.name + "Value"));
        value.addTypeArgument(domain);
        this.compilationUnit.getSuperInterfaceTypes().addAll(
                Sets.newLinkedHashSet(newArrayList(
                        value,
                        new FullyQualifiedJavaType(Serializable.class.getName())
                ))
        );

        this.compilationUnit.addImportedTypes(
                Sets.newLinkedHashSet(newArrayList(
                                new FullyQualifiedJavaType(Serializable.class.getName()),
                                new FullyQualifiedJavaType("org.make.ext.lang.ValueObject"),
                                new FullyQualifiedJavaType("lombok.Data"),
                                new FullyQualifiedJavaType("lombok.EqualsAndHashCode"),
                                new FullyQualifiedJavaType("jakarta.annotation.Generated"),
                                new FullyQualifiedJavaType("io.swagger.v3.oas.annotations.media.Schema"),
                                new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()),
                                new FullyQualifiedJavaType("jakarta.annotation.Nullable"),
                                new FullyQualifiedJavaType("java.util.Objects"),
                                new FullyQualifiedJavaType("org.springframework.beans.BeanUtils")
                        )
                )
        );
        List<IntrospectedColumn> columns = introspectedTable.getAllColumns();
        List<Field> property = columns.stream().map(it -> {
            Field f = new Field(it.getJavaProperty(), it.getFullyQualifiedJavaType());
            f.setVisibility(PRIVATE);
            f.addAnnotation("@Schema(name = \"" + it.getJavaProperty() + "\", description = \"" + it.getRemarks() + "\", defaultValue = \"" + it.getDefaultValue() + "\")");
            return f;
        }).toList();

        SERIAL_VERSION_UID.apply(compilationUnit);
        this.compilationUnit.getFields().addAll(property);

        Method method = new Method("apply");
        method.setVisibility(PUBLIC);
        method.addAnnotation("@Override");
        method.setReturnType(domain);

        Parameter parameter = new Parameter(new FullyQualifiedJavaType(this.name + THOR_DEFAULT_POJO_SUFFIX), "value");
        parameter.addAnnotation("@Nullable");
        method.addParameter(parameter);
        CodeBlock statements = CodeBlock.builder()
                .beginControlFlow("if (Objects.isNull(value))")
                .addStatement("value = this")
                .endControlFlow()
                .addStatement(this.name + " " + "object = " + this.name + ".empty()")
                .addStatement("BeanUtils.copyProperties(value, object)")
                .addStatement("return object")
                .build();
        method.addBodyLine(statements.toString());

        Method empty = new Method("empty");
        empty.setVisibility(PUBLIC);
        empty.setStatic(true);
        empty.setReturnType(token);
        empty.addBodyLine(CodeBlock.builder()
                .addStatement("return " + "new " + token.getShortName() + "()")
                .build().toString());
        this.compilationUnit.addMethod(empty);
        this.compilationUnit.addMethod(method);
    }

    @Override
    public GeneratedJavaFile make() {
        return new GeneratedJavaFile(this.compilationUnit,
                context.getJavaModelGeneratorConfiguration().getTargetProject(),
                UTF_8.name(),
                context.getJavaFormatter());
    }

    @Override
    public String getName() {
        return name;
    }
}
