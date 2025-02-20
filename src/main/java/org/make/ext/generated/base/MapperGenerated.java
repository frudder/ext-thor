package org.make.ext.generated.base;

import com.google.common.base.Strings;
import com.google.common.collect.Sets;
import org.make.ext.generated.ThorFactory;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TypeParameter;
import org.mybatis.generator.config.Context;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Lists.newArrayListWithCapacity;
import static com.google.common.collect.Sets.newHashSet;
import static com.google.common.collect.Sets.newLinkedHashSet;
import static org.make.ext.generated.ThorFactory.ThorAttribute.THOR_DEFAULT_MAPPER_NAME;
import static org.make.ext.generated.ThorFactory.ThorAttribute.THOR_LANG;
import static org.make.ext.generated.ThorFactory.ThorAttribute.THOR_TARGET_PACKAGE;
import static org.mybatis.generator.api.dom.java.JavaVisibility.PUBLIC;

public final class MapperGenerated extends ThorFactory {

    private static final String TARGET_RUNTIME = "MyBatis3DynamicSql";

    private final Interface compilationUnit;

    private final String name;

    private final Context context;

    private final Properties properties;

    public static MapperGenerated create(Properties properties, Context context) {
        return create(properties, null, context);
    }

    public static MapperGenerated create(Properties properties, String name, Context context) {
        return new MapperGenerated(properties, name, context);
    }

    private MapperGenerated(final Properties properties, final String name, final Context context) {
        this.properties = properties;
        this.name = Strings.isNullOrEmpty(name) ? THOR_DEFAULT_MAPPER_NAME : name;
        this.context = checkNotNull(context);
        FullyQualifiedJavaType base = new FullyQualifiedJavaType(String.join(".", ThorAttribute.getProperty(this.properties, THOR_TARGET_PACKAGE), THOR_LANG, this.name));
        this.compilationUnit = new Interface(base);
        this.compilationUnit.setVisibility(PUBLIC);
        this.compilationUnit.addAnnotation(GENERATED);
        this.compilationUnit.addTypeParameter(new TypeParameter("T"));
        this.compilationUnit.addTypeParameter(new TypeParameter("ID extends Serializable"));
        List<FullyQualifiedJavaType> iterable = newArrayListWithCapacity(16);
        Set<FullyQualifiedJavaType> imported = newLinkedHashSet();
        if (TARGET_RUNTIME.equalsIgnoreCase(context.getTargetRuntime())) {
            FullyQualifiedJavaType mapper = new FullyQualifiedJavaType("org.mybatis.dynamic.sql.util.mybatis3.CommonInsertMapper");
            mapper.addTypeArgument(new FullyQualifiedJavaType("T"));
            iterable.add(mapper);
            iterable.addAll(Sets.newHashSet(
                    new FullyQualifiedJavaType("org.mybatis.dynamic.sql.util.mybatis3.CommonSelectMapper"),
                    new FullyQualifiedJavaType("org.mybatis.dynamic.sql.util.mybatis3.CommonCountMapper"),
                    new FullyQualifiedJavaType("org.mybatis.dynamic.sql.util.mybatis3.CommonUpdateMapper"),
                    new FullyQualifiedJavaType("org.mybatis.dynamic.sql.util.mybatis3.CommonDeleteMapper")));

            imported.addAll(newHashSet(new FullyQualifiedJavaType("org.mybatis.dynamic.sql.util.mybatis3.CommonDeleteMapper"),
                    new FullyQualifiedJavaType("org.mybatis.dynamic.sql.util.mybatis3.CommonInsertMapper"),
                    new FullyQualifiedJavaType("org.mybatis.dynamic.sql.util.mybatis3.CommonUpdateMapper"),
                    new FullyQualifiedJavaType("org.mybatis.dynamic.sql.util.mybatis3.CommonCountMapper"),
                    new FullyQualifiedJavaType("org.mybatis.dynamic.sql.util.mybatis3.CommonSelectMapper"),
                    new FullyQualifiedJavaType("org.mybatis.dynamic.sql.delete.DeleteDSLCompleter"),
                    new FullyQualifiedJavaType("org.mybatis.dynamic.sql.select.CountDSLCompleter"),
                    new FullyQualifiedJavaType("org.mybatis.dynamic.sql.select.SelectDSLCompleter"),
                    new FullyQualifiedJavaType("org.mybatis.dynamic.sql.update.UpdateDSLCompleter")
            ));
        }
        imported.add(new FullyQualifiedJavaType("jakarta.annotation.Generated"));
        imported.add(new FullyQualifiedJavaType("java.io.Serializable"));
        this.compilationUnit.getSuperInterfaceTypes().addAll(iterable);

        Method count = new Method("count");
        count.addAnnotation(GENERATED);
        count.setAbstract(true);
        count.setVisibility(JavaVisibility.DEFAULT);
        count.setReturnType(new FullyQualifiedJavaType("long"));
        count.addParameter(new Parameter(new FullyQualifiedJavaType("org.mybatis.dynamic.sql.select.CountDSLCompleter"), "completer"));
        count.addBodyLine("return 0L;");
        this.compilationUnit.addMethod(count);

        Method delete = new Method("delete");
        delete.addAnnotation(GENERATED);
        delete.setAbstract(true);
        delete.setVisibility(JavaVisibility.DEFAULT);
        delete.setReturnType(new FullyQualifiedJavaType("int"));
        delete.addParameter(new Parameter(new FullyQualifiedJavaType("org.mybatis.dynamic.sql.delete.DeleteDSLCompleter"), "completer"));
        delete.addBodyLine("return 0;");
        this.compilationUnit.addMethod(delete);

        Method deleteByPrimaryKey = new Method("deleteByPrimaryKey");
        deleteByPrimaryKey.addAnnotation(GENERATED);
        deleteByPrimaryKey.setAbstract(true);
        deleteByPrimaryKey.setVisibility(JavaVisibility.DEFAULT);
        deleteByPrimaryKey.setReturnType(new FullyQualifiedJavaType("int"));
        deleteByPrimaryKey.addParameter(new Parameter(new FullyQualifiedJavaType("ID"), "id_"));
        deleteByPrimaryKey.addBodyLine("return 0;");
        this.compilationUnit.addMethod(deleteByPrimaryKey);

        Method insert = new Method("insert");
        insert.addAnnotation(GENERATED);
        insert.setAbstract(true);
        insert.setVisibility(JavaVisibility.DEFAULT);
        insert.setReturnType(new FullyQualifiedJavaType("int"));
        insert.addParameter(new Parameter(new FullyQualifiedJavaType("T"), "row"));
        insert.addBodyLine("return 0;");
        this.compilationUnit.addMethod(insert);

        Method insertMultiple = new Method("insertMultiple");
        insertMultiple.addAnnotation(GENERATED);
        insertMultiple.setAbstract(true);
        insertMultiple.setVisibility(JavaVisibility.DEFAULT);
        insertMultiple.setReturnType(new FullyQualifiedJavaType("int"));
        FullyQualifiedJavaType parameterType = new FullyQualifiedJavaType("Collection");
        parameterType.addTypeArgument(new FullyQualifiedJavaType("T"));
        insertMultiple.addParameter(new Parameter(parameterType, "records"));
        insertMultiple.addBodyLine("return 0;");
        this.compilationUnit.addMethod(insertMultiple);

        Method insertSelective = new Method("insertSelective");
        insertSelective.addAnnotation(GENERATED);
        insertSelective.setAbstract(true);
        insertSelective.setVisibility(JavaVisibility.DEFAULT);
        insertSelective.setReturnType(new FullyQualifiedJavaType("int"));
        insertSelective.addParameter(new Parameter(new FullyQualifiedJavaType("T"), "row"));
        insertSelective.addBodyLine("return 0;");
        this.compilationUnit.addMethod(insertSelective);

        Method selectOne = new Method("selectOne");
        selectOne.addAnnotation(GENERATED);
        selectOne.setAbstract(true);
        selectOne.setVisibility(JavaVisibility.DEFAULT);
        FullyQualifiedJavaType returnType = new FullyQualifiedJavaType("Optional");
        returnType.addTypeArgument(new FullyQualifiedJavaType("T"));
        selectOne.setReturnType(returnType);
        selectOne.addParameter(new Parameter(new FullyQualifiedJavaType("SelectDSLCompleter"), "completer"));
        selectOne.addBodyLine("return Optional.empty();");
        this.compilationUnit.addMethod(selectOne);

        Method select = new Method("select");
        select.addAnnotation(GENERATED);
        select.setAbstract(true);
        select.setVisibility(JavaVisibility.DEFAULT);
        returnType = new FullyQualifiedJavaType("List");
        returnType.addTypeArgument(new FullyQualifiedJavaType("T"));
        select.setReturnType(returnType);
        select.addParameter(new Parameter(new FullyQualifiedJavaType("SelectDSLCompleter"), "completer"));
        select.addBodyLine("return List.of();");
        this.compilationUnit.addMethod(select);

        Method selectDistinct = new Method("selectDistinct");
        selectDistinct.addAnnotation(GENERATED);
        selectDistinct.setAbstract(true);
        selectDistinct.setVisibility(JavaVisibility.DEFAULT);
        returnType = new FullyQualifiedJavaType("List");
        returnType.addTypeArgument(new FullyQualifiedJavaType("T"));
        selectDistinct.setReturnType(returnType);
        selectDistinct.addParameter(new Parameter(new FullyQualifiedJavaType("SelectDSLCompleter"), "completer"));
        selectDistinct.addBodyLine("return List.of();");
        this.compilationUnit.addMethod(selectDistinct);

        Method selectByPrimaryKey = new Method("selectByPrimaryKey");
        selectByPrimaryKey.addAnnotation(GENERATED);
        selectByPrimaryKey.setAbstract(true);
        selectByPrimaryKey.setVisibility(JavaVisibility.DEFAULT);
        returnType = new FullyQualifiedJavaType("Optional");
        returnType.addTypeArgument(new FullyQualifiedJavaType("T"));
        selectByPrimaryKey.setReturnType(returnType);
        selectByPrimaryKey.addParameter(new Parameter(new FullyQualifiedJavaType("ID"), "id_"));
        selectByPrimaryKey.addBodyLine("return  Optional.empty();");
        this.compilationUnit.addMethod(selectByPrimaryKey);

        Method update = new Method("update");
        update.addAnnotation(GENERATED);
        update.setAbstract(true);
        update.setVisibility(JavaVisibility.DEFAULT);
        returnType = new FullyQualifiedJavaType("int");
        update.setReturnType(returnType);
        update.addParameter(new Parameter(new FullyQualifiedJavaType("UpdateDSLCompleter"), "completer"));
        update.addBodyLine("return  0;");
        this.compilationUnit.addMethod(update);

        Method updateByPrimaryKey = new Method("updateByPrimaryKey");
        updateByPrimaryKey.addAnnotation(GENERATED);
        updateByPrimaryKey.setAbstract(true);
        updateByPrimaryKey.setVisibility(JavaVisibility.DEFAULT);
        returnType = new FullyQualifiedJavaType("int");
        updateByPrimaryKey.setReturnType(returnType);
        updateByPrimaryKey.addParameter(new Parameter(new FullyQualifiedJavaType("T"), "row"));
        updateByPrimaryKey.addBodyLine("return  0;");
        this.compilationUnit.addMethod(updateByPrimaryKey);

        Method updateByPrimaryKeySelective = new Method("updateByPrimaryKeySelective");
        updateByPrimaryKeySelective.addAnnotation(GENERATED);
        updateByPrimaryKeySelective.setAbstract(true);
        updateByPrimaryKeySelective.setVisibility(JavaVisibility.DEFAULT);
        returnType = new FullyQualifiedJavaType("int");
        updateByPrimaryKeySelective.setReturnType(returnType);
        updateByPrimaryKeySelective.addParameter(new Parameter(new FullyQualifiedJavaType("T"), "row"));
        updateByPrimaryKeySelective.addBodyLine("return  0;");
        this.compilationUnit.addMethod(updateByPrimaryKeySelective);

        imported.addAll(Sets.newHashSet(
                new FullyQualifiedJavaType("java.util.Collection"),
                new FullyQualifiedJavaType("java.util.List"),
                new FullyQualifiedJavaType("java.util.Optional")
        ));
        this.compilationUnit.addImportedTypes(imported);
    }

    @Override
    public GeneratedJavaFile make() {
        return new GeneratedJavaFile(this.compilationUnit, context.getJavaClientGeneratorConfiguration().getTargetProject(), StandardCharsets.UTF_8.name(), context.getJavaFormatter());
    }

    @Override
    public String getName() {
        return name;
    }
}
