package org.make.ext.generated.util;

import jakarta.annotation.Generated;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.make.ext.DefaultJavaField;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.config.Context;

import java.io.Serializable;
import java.util.List;

import static com.google.common.collect.Sets.newHashSet;
import static org.make.ext.generated.ThorFactory.GENERATED;
import static org.mybatis.generator.api.dom.java.JavaVisibility.PUBLIC;

public final class RichJavaModelCompilationUnit extends RichTopLevelClassVisitor {

    private final IntrospectedTable introspectedTable;

    public RichJavaModelCompilationUnit(Context context, IntrospectedTable introspectedTable) {
        super(context);
        this.introspectedTable = introspectedTable;
    }

    public static RichJavaModelCompilationUnit create(Context context, IntrospectedTable introspectedTable) {
        return new RichJavaModelCompilationUnit(context, introspectedTable);
    }

    @Override
    public TopLevelClass visit(TopLevelClass compilationUnit) {
        FullyQualifiedJavaType base = new FullyQualifiedJavaType(getContext().getJavaModelGeneratorConfiguration().getTargetPackage() + "." + "AbstractEntity");
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
                new FullyQualifiedJavaType(Generated.class.getName()),
                new FullyQualifiedJavaType(Data.class.getName()),
                new FullyQualifiedJavaType(EqualsAndHashCode.class.getName())
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
