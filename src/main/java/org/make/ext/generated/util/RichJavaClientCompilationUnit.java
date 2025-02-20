package org.make.ext.generated.util;

import org.make.ext.generated.ThorFactory;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.config.Context;

import java.util.List;
import java.util.Properties;
import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;
import static org.make.ext.generated.ThorFactory.ThorAttribute.THOR_DEFAULT_MAPPER_NAME;
import static org.make.ext.generated.ThorFactory.ThorAttribute.THOR_LANG;
import static org.make.ext.generated.ThorFactory.ThorAttribute.THOR_TARGET_PACKAGE;


public final class RichJavaClientCompilationUnit extends RichInterfaceVisitor {

    private final IntrospectedTable introspectedTable;

    private final String name;

    private final Properties properties;

    public RichJavaClientCompilationUnit(Properties properties, Context context, IntrospectedTable introspectedTable) {
        super(context);
        this.introspectedTable = introspectedTable;
        this.properties = properties;
        this.name = THOR_DEFAULT_MAPPER_NAME;
    }

    public static RichJavaClientCompilationUnit create(Properties properties, Context context, IntrospectedTable introspectedTable) {
        return new RichJavaClientCompilationUnit(properties, context, introspectedTable);
    }

    @Override
    public Interface visit(Interface compilationUnit) {
        String name = String.join(".", ThorFactory.ThorAttribute.getProperty(this.properties, THOR_TARGET_PACKAGE), THOR_LANG, this.name);
        FullyQualifiedJavaType mapper = new FullyQualifiedJavaType(name);
        mapper.addTypeArgument(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()));
        if (!introspectedTable.hasPrimaryKeyColumns())
            throw new IllegalArgumentException();
        List<IntrospectedColumn> columns = introspectedTable.getPrimaryKeyColumns();
        mapper.addTypeArgument(columns.get(0).getFullyQualifiedJavaType());
        compilationUnit.getSuperInterfaceTypes().clear();
        compilationUnit.addSuperInterface(mapper);
        compilationUnit.addImportedType(mapper);
        Set<FullyQualifiedJavaType> remove = newHashSet(
                new FullyQualifiedJavaType("org.mybatis.dynamic.sql.util.mybatis3.CommonCountMapper"),
                new FullyQualifiedJavaType("org.mybatis.dynamic.sql.util.mybatis3.CommonDeleteMapper"),
                new FullyQualifiedJavaType("org.mybatis.dynamic.sql.util.mybatis3.CommonInsertMapper"),
                new FullyQualifiedJavaType("org.mybatis.dynamic.sql.util.mybatis3.CommonUpdateMapper")
        );
        compilationUnit.getImportedTypes().removeIf(remove::contains);
        compilationUnit.getMethods().forEach(it -> {
            if (it.isDefault()) {
                it.addAnnotation("@Override");
            }
        });
        return compilationUnit;
    }
}
