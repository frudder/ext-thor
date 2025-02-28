package org.make.ext.generated.util;

import com.squareup.javapoet.CodeBlock;
import org.make.ext.generated.ThorFactory;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.internal.util.JavaBeansUtil;

import java.util.List;
import java.util.Properties;
import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;
import static org.make.ext.generated.ThorFactory.GENERATED;
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
        IntrospectedColumn primaryKey = columns.get(0);
        mapper.addTypeArgument(primaryKey.getFullyQualifiedJavaType());
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

        Method from = new Method("from");
        from.addAnnotation(GENERATED);
        from.addAnnotation("@Override");
        from.setDefault(true);
        FullyQualifiedJavaType returnType = new FullyQualifiedJavaType("AliasableSqlTable");
        returnType.addTypeArgument(new FullyQualifiedJavaType("?"));
        from.setReturnType(returnType);
        from.addBodyLine("return " + JavaBeansUtil.getValidPropertyName(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()).getShortName()) + ";");
        compilationUnit.addMethod(from);
        compilationUnit.addImportedTypes(Set.of(
                new FullyQualifiedJavaType("org.mybatis.dynamic.sql.AliasableSqlTable"),
                new FullyQualifiedJavaType("org.mybatis.dynamic.sql.SqlBuilder")
        ));

        Method deleteAll = new Method("deleteAll");
        deleteAll.addAnnotation(GENERATED);
        deleteAll.addAnnotation("@Override");
        deleteAll.setDefault(true);
        deleteAll.setReturnType(new FullyQualifiedJavaType("int"));
        FullyQualifiedJavaType parameterType = new FullyQualifiedJavaType("List");
        parameterType.addTypeArgument(primaryKey.getFullyQualifiedJavaType());
        deleteAll.addParameter(new Parameter(parameterType, "id_"));
        deleteAll.addBodyLine(CodeBlock.builder()
                .addStatement("return delete(c -> c.where(id, SqlBuilder.isIn(id_)))")
                .build().toString());
        compilationUnit.addMethod(deleteAll);


        Method findAll = new Method("findAll");
        findAll.addAnnotation(GENERATED);
        findAll.addAnnotation("@Override");
        findAll.setDefault(true);
        parameterType = new FullyQualifiedJavaType("List");
        parameterType.addTypeArgument(primaryKey.getFullyQualifiedJavaType());
        findAll.addParameter(new Parameter(parameterType, "id_"));
        returnType = new FullyQualifiedJavaType("List");
        returnType.addTypeArgument(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()));
        findAll.setReturnType(returnType);
        findAll.addBodyLine(CodeBlock.builder()
                .addStatement("return select(it -> it.where(id, SqlBuilder.isIn(id_)))")
                .build().toString());
        compilationUnit.addMethod(findAll);



        Method method = new Method("columns");
        method.addAnnotation(GENERATED);
        method.addAnnotation("@Override");
        method.setDefault(true);
        method.setVisibility(JavaVisibility.DEFAULT);
        returnType = new FullyQualifiedJavaType("BasicColumn[]");
        method.setReturnType(returnType);
        method.addBodyLine(CodeBlock.builder()
                        .addStatement("return selectList")
                .build().toString());
        compilationUnit.addMethod(method);

        return compilationUnit;


    }
}
