package org.make.ext.generated.util;

import com.google.common.collect.Sets;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.config.Context;

import java.util.Properties;
import java.util.Set;

import static org.make.ext.DefaultProjectSpecs.DEFAULT_MAPPER_NAME;
import static org.make.ext.generated.ThorFactory.ThorAttribute.TARGET_PACKAGE;

public final class RichJavaClientCompilationUnit extends RichInterfaceVisitor {

    private final IntrospectedTable introspectedTable;

    private final String name;

    private final Properties properties;

    public RichJavaClientCompilationUnit(Properties properties, Context context, IntrospectedTable introspectedTable) {
        super(context);
        this.introspectedTable = introspectedTable;
        this.properties = properties;
        this.name = DEFAULT_MAPPER_NAME.toString();
    }

    public static RichJavaClientCompilationUnit create(Properties properties, Context context, IntrospectedTable introspectedTable) {
        return new RichJavaClientCompilationUnit(properties, context, introspectedTable);
    }

    @Override
    public Interface visit(Interface compilationUnit) {
        String name = String.join(".", TARGET_PACKAGE.getProperty(this.properties), "lang", this.name);
        FullyQualifiedJavaType mapper = new FullyQualifiedJavaType(name);
        mapper.addTypeArgument(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()));
        compilationUnit.getSuperInterfaceTypes().clear();
        compilationUnit.addSuperInterface(mapper);
        compilationUnit.addImportedType(mapper);
        Set<FullyQualifiedJavaType> remove = Sets.newHashSet(
                new FullyQualifiedJavaType("org.mybatis.dynamic.sql.util.mybatis3.CommonCountMapper"),
                new FullyQualifiedJavaType("org.mybatis.dynamic.sql.util.mybatis3.CommonDeleteMapper"),
                new FullyQualifiedJavaType("org.mybatis.dynamic.sql.util.mybatis3.CommonInsertMapper"),
                new FullyQualifiedJavaType("org.mybatis.dynamic.sql.util.mybatis3.CommonUpdateMapper")
        );
        compilationUnit.getImportedTypes().removeIf(remove::contains);
        return compilationUnit;
    }
}
