package org.make.ext.generated.lang;

import com.squareup.javapoet.CodeBlock;
import org.make.ext.generated.ThorFactory;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.config.Context;

import java.util.Properties;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Sets.newHashSet;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.make.ext.generated.ThorFactory.ThorAttribute.THOR_DEFAULT_DOMAIN_NAME;
import static org.make.ext.generated.ThorFactory.ThorAttribute.THOR_DEFAULT_SERVICE_NAME;
import static org.make.ext.generated.ThorFactory.ThorAttribute.THOR_LANG;
import static org.make.ext.generated.ThorFactory.ThorAttribute.THOR_TARGET_PACKAGE;
import static org.mybatis.generator.api.dom.java.JavaVisibility.PROTECTED;
import static org.mybatis.generator.api.dom.java.JavaVisibility.PUBLIC;

public final class DomainGenerated extends ThorFactory {

    private final TopLevelClass compilationUnit;

    private final Context context;

    private final Properties properties;

    private final String name;

    public static DomainGenerated create(Properties properties, Context context) {
        return new DomainGenerated(properties, context);
    }

    private DomainGenerated(Properties properties, Context context) {
        this.properties = properties;
        this.context = checkNotNull(context);
        String name = ThorAttribute.getProperty(this.properties, THOR_TARGET_PACKAGE);
        this.name = String.join(".", name, THOR_LANG, THOR_DEFAULT_DOMAIN_NAME);
        FullyQualifiedJavaType domainType = new FullyQualifiedJavaType(this.name);
        // T extends ThorEntity<? extends Serializable>, D extends Serializable, M extends MapperAdapter<T,D>
        domainType.addTypeArgument(new FullyQualifiedJavaType("T extends ThorEntity <? extends Serializable> "));
        domainType.addTypeArgument(new FullyQualifiedJavaType("D extends Serializable "));
        domainType.addTypeArgument(new FullyQualifiedJavaType("M extends MapperAdapter<T,D>"));
        FullyQualifiedJavaType traitType = new FullyQualifiedJavaType(THOR_DEFAULT_SERVICE_NAME);
        traitType.addTypeArgument(new FullyQualifiedJavaType("T"));
        traitType.addTypeArgument(new FullyQualifiedJavaType("D"));
        traitType.addTypeArgument(new FullyQualifiedJavaType("M"));
        FullyQualifiedJavaType t = new FullyQualifiedJavaType(String.join(".", ThorAttribute.getProperty(this.properties, THOR_TARGET_PACKAGE), THOR_LANG, THOR_DEFAULT_SERVICE_NAME));
        t.addTypeArgument(new FullyQualifiedJavaType("T"));
        t.addTypeArgument(new FullyQualifiedJavaType("D"));
        t.addTypeArgument(new FullyQualifiedJavaType("M"));
        this.compilationUnit = new TopLevelClass(domainType);
        this.compilationUnit.addSuperInterface(t);
        this.compilationUnit.setVisibility(PUBLIC);
        this.compilationUnit.setAbstract(true);
        this.compilationUnit.addAnnotation(GENERATED);
        this.compilationUnit.addImportedTypes(newHashSet(
                new FullyQualifiedJavaType("jakarta.annotation.Generated"),
                new FullyQualifiedJavaType("java.io.Serializable"),
                new FullyQualifiedJavaType("java.util.Optional"),
                new FullyQualifiedJavaType("java.util.List"),
                new FullyQualifiedJavaType("jakarta.annotation.Resource"),
                new FullyQualifiedJavaType("org.mybatis.dynamic.sql.delete.DeleteDSLCompleter"),
                new FullyQualifiedJavaType("org.mybatis.dynamic.sql.select.CountDSLCompleter"),
                new FullyQualifiedJavaType("org.mybatis.dynamic.sql.select.SelectDSLCompleter"),
                new FullyQualifiedJavaType("org.mybatis.dynamic.sql.update.UpdateDSLCompleter"),
                new FullyQualifiedJavaType("org.springframework.data.domain.Page"),
                new FullyQualifiedJavaType("org.springframework.data.domain.Pageable"),
                new FullyQualifiedJavaType("org.springframework.transaction.annotation.Transactional"),
                new FullyQualifiedJavaType("javax.annotation.CheckReturnValue")
        ));

        this.compilationUnit.addStaticImports(newHashSet(
                "org.springframework.util.CollectionUtils.isEmpty"
        ));

        Field f = new Field("mapper", new FullyQualifiedJavaType("M"));
        f.setVisibility(PROTECTED);
        f.addAnnotation("@Resource");
        this.compilationUnit.addField(f);


        Method method = new Method("findAll");
        method.addAnnotation("@Override");
        method.addAnnotation("@Transactional(readOnly = true)");
        method.setVisibility(PUBLIC);
        method.setReturnType(new FullyQualifiedJavaType("List<T>"));
        method.addBodyLine(CodeBlock.builder()
                .addStatement("return findAll(it -> it)")
                .build().toString());
        this.compilationUnit.addMethod(method);

        method = new Method("findAll");
        method.addAnnotation("@Override");
        method.addAnnotation("@Transactional(readOnly = true)");
        method.setVisibility(PUBLIC);
        method.setReturnType(new FullyQualifiedJavaType("List<T>"));
        method.addParameter(new Parameter(new FullyQualifiedJavaType("List<D>"), "id_"));
        method.addBodyLine(CodeBlock.builder()
                .addStatement("return isEmpty(id_) ? List.of() : mapper.findAll(id_)")
                .build()
                .toString());
        this.compilationUnit.addMethod(method);

        method = new Method("findAll");
        method.addAnnotation("@Override");
        method.addAnnotation("@Transactional(readOnly = true)");
        method.setVisibility(PUBLIC);
        method.setReturnType(new FullyQualifiedJavaType("List<T>"));
        method.addParameter(new Parameter(new FullyQualifiedJavaType("SelectDSLCompleter"), "function"));
        method.addBodyLine(CodeBlock.builder()
                .addStatement("return mapper.select(function)")
                .build().toString());
        this.compilationUnit.addMethod(method);

        method = new Method("findAll");
        method.addAnnotation("@Override");
        method.addAnnotation("@Transactional(readOnly = true)");
        method.setVisibility(PUBLIC);
        method.setReturnType(new FullyQualifiedJavaType("Page<T>"));
        method.addParameter(new Parameter(new FullyQualifiedJavaType("Pageable"), "peek"));
        method.addParameter(new Parameter(new FullyQualifiedJavaType("SelectDSLCompleter"), "function"));
        method.addBodyLine(CodeBlock.builder().addStatement("throw new UnsupportedOperationException()").build().toString());
        this.compilationUnit.addMethod(method);

        method = new Method("findOne");
        method.addAnnotation("@Override");
        method.addAnnotation("@Transactional(readOnly = true)");
        method.setVisibility(PUBLIC);
        method.setReturnType(new FullyQualifiedJavaType("Optional<T>"));
        method.addParameter(new Parameter(new FullyQualifiedJavaType("SelectDSLCompleter"), "function"));
        method.addBodyLine(CodeBlock.builder().addStatement("return mapper.selectOne(function)").build().toString());
        this.compilationUnit.addMethod(method);

        method = new Method("findOne");
        method.addAnnotation("@Override");
        method.addAnnotation("@Transactional(readOnly = true)");
        method.setVisibility(PUBLIC);
        method.setReturnType(new FullyQualifiedJavaType("Optional<T>"));
        method.addParameter(new Parameter(new FullyQualifiedJavaType("D"), "_id"));
        method.addBodyLine(CodeBlock.builder().addStatement("return mapper.selectByPrimaryKey(_id)").build().toString());
        this.compilationUnit.addMethod(method);

        method = new Method("count");
        method.addAnnotation("@Override");
        method.addAnnotation("@Transactional(readOnly = true)");
        method.setVisibility(PUBLIC);
        method.setReturnType(new FullyQualifiedJavaType("Long"));
        method.addParameter(new Parameter(new FullyQualifiedJavaType("CountDSLCompleter"), "function"));
        method.addBodyLine(CodeBlock.builder().addStatement("return mapper.count(function)").build().toString());
        this.compilationUnit.addMethod(method);


        method = new Method("create");
        method.setVisibility(PUBLIC);
        method.addAnnotation("@Override");
        method.addAnnotation("@Transactional(rollbackFor = Exception.class)");
        method.addAnnotation("@CheckReturnValue");
        method.setReturnType(new FullyQualifiedJavaType("T"));
        method.addParameter(new Parameter(new FullyQualifiedJavaType("T"), "entity"));
        method.addBodyLine(CodeBlock.builder()
                .beginControlFlow("if (mapper.insert(entity) > 0)")
                .addStatement("return entity")
                .endControlFlow()
                .addStatement("return null")
                .build().toString());
        this.compilationUnit.addMethod(method);

        method = new Method("create");
        method.setVisibility(PUBLIC);
        method.addAnnotation("@Override");
        method.addAnnotation("@Transactional(rollbackFor = Exception.class)");
        method.addAnnotation("@CheckReturnValue");
        method.setReturnType(new FullyQualifiedJavaType("List<T>"));
        method.addParameter(new Parameter(new FullyQualifiedJavaType("List<T>"), "entities"));
        method.addBodyLine(CodeBlock.builder()
                .beginControlFlow("if (isEmpty(entities))")
                .addStatement("return List.of()")
                .endControlFlow()
                .beginControlFlow("if (mapper.insertMultiple(entities) > 0)")
                .addStatement(" return entities")
                .endControlFlow()
                .addStatement("return List.of()")
                .build().toString());
        this.compilationUnit.addMethod(method);

        method = new Method("delete");
        method.setVisibility(PUBLIC);
        method.addAnnotation("@Override");
        method.addAnnotation("@Transactional(rollbackFor = Exception.class)");
        method.setReturnType(new FullyQualifiedJavaType("boolean"));
        method.addParameter(new Parameter(new FullyQualifiedJavaType("D"), "id_"));
        method.addBodyLine(CodeBlock.builder().addStatement("return mapper.deleteByPrimaryKey(id_) > 0").build().toString());
        this.compilationUnit.addMethod(method);

        method = new Method("delete");
        method.setVisibility(PUBLIC);
        method.addAnnotation("@Override");
        method.addAnnotation("@Transactional(rollbackFor = Exception.class)");
        method.setReturnType(new FullyQualifiedJavaType("boolean"));
        method.addParameter(new Parameter(new FullyQualifiedJavaType("List<D>"), "id_"));
        method.addBodyLine(CodeBlock.builder().addStatement("return mapper.deleteAll(id_) > 0").build().toString());
        this.compilationUnit.addMethod(method);

        method = new Method("delete");
        method.setVisibility(PUBLIC);
        method.addAnnotation("@Override");
        method.addAnnotation("@Transactional(rollbackFor = Exception.class)");
        method.setReturnType(new FullyQualifiedJavaType("boolean"));
        method.addParameter(new Parameter(new FullyQualifiedJavaType("DeleteDSLCompleter"), "function"));
        method.addBodyLine(CodeBlock.builder().addStatement("return mapper.delete(function) > 0").build().toString());
        this.compilationUnit.addMethod(method);

        method = new Method("save");
        method.setVisibility(PUBLIC);
        method.addAnnotation("@Override");
        method.addAnnotation("@CheckReturnValue");
        method.addAnnotation("@Transactional(rollbackFor = Exception.class)");
        method.setReturnType(new FullyQualifiedJavaType("T"));
        method.addParameter(new Parameter(new FullyQualifiedJavaType("T"), "entity"));
        method.addBodyLine(CodeBlock.builder().
                beginControlFlow("if (mapper.updateByPrimaryKey(entity) > 0)")
                .addStatement("return entity")
                .endControlFlow().
                addStatement("return null").build().toString());
        this.compilationUnit.addMethod(method);

        method = new Method("save");
        method.setVisibility(PUBLIC);
        method.addAnnotation("@Override");
        method.addAnnotation("@CheckReturnValue");
        method.addAnnotation("@Transactional(rollbackFor = Exception.class)");
        method.setReturnType(new FullyQualifiedJavaType("List<T>"));
        method.addParameter(new Parameter(new FullyQualifiedJavaType("List<T>"), "entities"));
        method.addBodyLine(CodeBlock.builder().addStatement("throw new UnsupportedOperationException()").build().toString());
        this.compilationUnit.addMethod(method);

        method = new Method("save");
        method.setVisibility(PUBLIC);
        method.addAnnotation("@Override");
        method.addAnnotation("@CheckReturnValue");
        method.addAnnotation("@Transactional(rollbackFor = Exception.class)");
        method.setReturnType(new FullyQualifiedJavaType("boolean"));
        method.addParameter(new Parameter(new FullyQualifiedJavaType("UpdateDSLCompleter"), "function"));
        method.addBodyLine(CodeBlock.builder().addStatement("return mapper.update(function) > 0").build().toString());
        this.compilationUnit.addMethod(method);
    }

    @Override
    public GeneratedJavaFile make() {
        return new GeneratedJavaFile(compilationUnit,
                context.getJavaModelGeneratorConfiguration().getTargetProject(),
                UTF_8.name(),
                context.getJavaFormatter());
    }

    @Override
    public String getName() {
        return this.name;
    }

    public Properties getProperties() {
        return properties;
    }
}
