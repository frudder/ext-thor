package org.make.ext.generated.util;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.config.Context;

public final class RichJavaClientCompilationUnit extends RichInterfaceVisitor {

    private final IntrospectedTable introspectedTable;

    private final String name;

    public RichJavaClientCompilationUnit(Context context, IntrospectedTable introspectedTable) {
        super(context);
        this.introspectedTable = introspectedTable;
        this.name = "MapperAdapter";
    }

    public static RichJavaClientCompilationUnit create(Context context, IntrospectedTable introspectedTable) {
        return new RichJavaClientCompilationUnit(context, introspectedTable);
    }

    @Override
    public Interface visit(Interface compilationUnit) {
        String name = getContext().getJavaClientGeneratorConfiguration().getTargetPackage() + "." + "MapperAdapter";
        FullyQualifiedJavaType mapper = new FullyQualifiedJavaType(name);
        mapper.addTypeArgument(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()));
        compilationUnit.getSuperInterfaceTypes().clear();
        compilationUnit.addSuperInterface(mapper);
        return compilationUnit;
    }

    public String getName() {
        return name;
    }
}
