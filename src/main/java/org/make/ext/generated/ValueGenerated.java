package org.make.ext.generated;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.config.Context;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.make.ext.DefaultJavaField.SERIAL_VERSION_UID;

public class ValueGenerated extends MakeFactory {

    private final TopLevelClass compilationUnit;

    private final String name;

    private final Context context;

    private final IntrospectedTable introspectedTable;

    public static ValueGenerated create(Context context, IntrospectedTable introspectedTable) {
        return new ValueGenerated(context, introspectedTable);
    }

    private ValueGenerated(final Context context, final IntrospectedTable introspectedTable) {
        this.name = introspectedTable.getMyBatisDynamicSQLTableObjectName();
        this.context = context;
        this.introspectedTable = introspectedTable;
        FullyQualifiedJavaType token = new FullyQualifiedJavaType(context.getJavaModelGeneratorConfiguration().getTargetPackage() + "." + this.name + "Value");
        this.compilationUnit = new TopLevelClass(token);
        this.compilationUnit.setVisibility(JavaVisibility.PUBLIC);
        this.compilationUnit.addAnnotation(GENERATED);
        this.compilationUnit.addAnnotation("@Data");
        this.compilationUnit.addAnnotation("@EqualsAndHashCode(callSuper = false)");
        this.compilationUnit.addAnnotation("@Schema()");

        FullyQualifiedJavaType value = new FullyQualifiedJavaType("org.make.ext.generated.ValueObject");
        value.addTypeArgument(new FullyQualifiedJavaType(this.name + "Value"));
        value.addTypeArgument(new FullyQualifiedJavaType(this.introspectedTable.getMyBatisDynamicSQLTableObjectName()));
        this.compilationUnit.getSuperInterfaceTypes().addAll(
                Sets.newLinkedHashSet(Lists.newArrayList(
                        value,
                        new FullyQualifiedJavaType(Serializable.class.getName())
                ))
        );

        this.compilationUnit.addImportedTypes(
                Sets.newLinkedHashSet(
                        Lists.newArrayList(
                                new FullyQualifiedJavaType(Serializable.class.getName()),
                                new FullyQualifiedJavaType("org.make.ext.generated.ValueObject"),
                                new FullyQualifiedJavaType(Data.class.getName()),
                                new FullyQualifiedJavaType(EqualsAndHashCode.class.getName()),
                                new FullyQualifiedJavaType(Generated.class.getName()),
                                new FullyQualifiedJavaType(Schema.class.getName()),
                                new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()),
                                token
                        )
                )
        );
        List<IntrospectedColumn> columns = introspectedTable.getAllColumns();
        List<Field> property = columns.stream().map(it -> {
            Field f = new Field(it.getJavaProperty(), it.getFullyQualifiedJavaType());
            f.setVisibility(JavaVisibility.PRIVATE);
            return f;
        }).toList();

        SERIAL_VERSION_UID.apply(compilationUnit);
        this.compilationUnit.getFields().addAll(property);

        Method method = new Method("apply");
        method.setVisibility(JavaVisibility.PUBLIC);
        method.addAnnotation("@Override");
        method.setReturnType(new FullyQualifiedJavaType(introspectedTable.getMyBatisDynamicSQLTableObjectName()));
        method.addParameter(new Parameter(new FullyQualifiedJavaType(this.name + "Value"), "value"));
        method.addBodyLine("return " + this.introspectedTable.getMyBatisDynamicSQLTableObjectName() + "." + "empty();");
        this.compilationUnit.addMethod(method);

    }

    @Override
    public GeneratedJavaFile make() {
        return new GeneratedJavaFile(this.compilationUnit,
                context.getJavaModelGeneratorConfiguration().getTargetProject(),
                StandardCharsets.UTF_8.name(),
                context.getJavaFormatter());
    }

    @Override
    public String getName() {
        return name;
    }
}
