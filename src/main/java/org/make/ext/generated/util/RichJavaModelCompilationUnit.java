package org.make.ext.generated.util;

import org.make.ext.DefaultJavaField;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.config.Context;

import java.io.Serializable;
import java.util.List;
import java.util.Properties;

import static com.google.common.collect.Sets.newHashSet;
import static org.make.ext.generated.ThorFactory.GENERATED;
import static org.make.ext.generated.ThorFactory.ThorAttribute.TARGET_PACKAGE;
import static org.mybatis.generator.api.dom.java.JavaVisibility.PUBLIC;

public final class RichJavaModelCompilationUnit extends RichTopLevelClassVisitor {

    private final Properties properties;

    private final IntrospectedTable introspectedTable;

    private final String name;

    public RichJavaModelCompilationUnit(Properties properties, Context context, IntrospectedTable introspectedTable) {
        super(context);
        this.introspectedTable = introspectedTable;
        this.properties = properties;
        this.name = "AbstractEntity";
    }

    public static RichJavaModelCompilationUnit create(Properties properties, Context context, IntrospectedTable introspectedTable) {
        return new RichJavaModelCompilationUnit(properties, context, introspectedTable);
    }

    @Override
    public TopLevelClass visit(TopLevelClass compilationUnit) {
        String name = String.join(".", TARGET_PACKAGE.getProperty(this.properties), "lang" , this.name);
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
        compilationUnit.addImportedTypes(newHashSet(
                new FullyQualifiedJavaType("jakarta.annotation.Generated"),
                new FullyQualifiedJavaType("lombok.Data"),
                new FullyQualifiedJavaType("lombok.EqualsAndHashCode"),
                base
        ));
        DefaultJavaField.SERIAL_VERSION_UID.apply(compilationUnit);
        compilationUnit.addSuperInterface(new FullyQualifiedJavaType(Serializable.class.getName()));
        compilationUnit.getMethods().clear();
        Method method = new Method("empty");
        method.setVisibility(PUBLIC);
        method.setStatic(true);
        method.setReturnType(compilationUnit.getType());
        method.addBodyLine("return new " + compilationUnit.getType().getShortName() + "();");
        compilationUnit.addMethod(method);
        return compilationUnit;
    }
}
