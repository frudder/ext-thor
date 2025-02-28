package org.make.ext.generated.lang;

import org.make.ext.generated.ThorFactory;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.config.Context;

import java.util.Properties;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Sets.newHashSet;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.make.ext.generated.ThorFactory.ThorAttribute.THOR_DEFAULT_SERVICE_NAME;
import static org.make.ext.generated.ThorFactory.ThorAttribute.THOR_TARGET_PACKAGE;
import static org.mybatis.generator.api.dom.java.JavaVisibility.PUBLIC;

public final class TraitGenerated extends ThorFactory {

    private final Interface compilationUnit;

    private final Context context;

    private final Properties properties;

    private final String name;

    public static TraitGenerated create(Properties properties, Context context) {
        return new TraitGenerated(properties, context);
    }

    private TraitGenerated(Properties properties, Context context) {
        this.properties = properties;
        this.context = checkNotNull(context);
        String name = ThorAttribute.getProperty(this.properties, THOR_TARGET_PACKAGE);
        this.name = String.join(".", name, "lang", THOR_DEFAULT_SERVICE_NAME);
        FullyQualifiedJavaType traitType = new FullyQualifiedJavaType(this.name);
        // ThorTrait<T extends ThorEntity<? extends Serializable>, D extends Serializable, M extends MapperAdapter<T, D>>
        traitType.addTypeArgument(new FullyQualifiedJavaType("T extends ThorEntity < ? extends Serializable>"));
        traitType.addTypeArgument(new FullyQualifiedJavaType("D extends Serializable"));
        traitType.addTypeArgument(new FullyQualifiedJavaType("M extends MapperAdapter<T,D>"));
        this.compilationUnit = new Interface(traitType);
        this.compilationUnit.setVisibility(PUBLIC);
        this.compilationUnit.addAnnotation(GENERATED);
        this.compilationUnit.addImportedTypes(newHashSet(

                new FullyQualifiedJavaType("org.mybatis.dynamic.sql.delete.DeleteDSLCompleter"),
                new FullyQualifiedJavaType("org.mybatis.dynamic.sql.select.CountDSLCompleter"),
                new FullyQualifiedJavaType("org.mybatis.dynamic.sql.select.SelectDSLCompleter"),
                new FullyQualifiedJavaType("org.mybatis.dynamic.sql.update.UpdateDSLCompleter"),
                new FullyQualifiedJavaType("org.springframework.data.domain.Page"),
                new FullyQualifiedJavaType("org.springframework.data.domain.Pageable"),

                new FullyQualifiedJavaType("jakarta.annotation.Generated"),
                new FullyQualifiedJavaType("java.io.Serializable"),
                new FullyQualifiedJavaType("java.util.Optional"),
                new FullyQualifiedJavaType("java.util.List")
        ));

        Method method = new Method("findAll");
        method.setAbstract(true);
        method.setReturnType(new FullyQualifiedJavaType("List<T>"));
        this.compilationUnit.addMethod(method);

        method = new Method("findAll");
        method.setAbstract(true);
        method.setReturnType(new FullyQualifiedJavaType("List<T>"));
        method.addParameter(new Parameter(new FullyQualifiedJavaType("List<D>"), "id_"));
        this.compilationUnit.addMethod(method);

        method = new Method("findAll");
        method.setAbstract(true);
        method.setReturnType(new FullyQualifiedJavaType("List<T>"));
        method.addParameter(new Parameter(new FullyQualifiedJavaType("SelectDSLCompleter"), "function"));
        this.compilationUnit.addMethod(method);

        method = new Method("findAll");
        method.setAbstract(true);
        method.setReturnType(new FullyQualifiedJavaType("Page<T>"));
        method.addParameter(new Parameter(new FullyQualifiedJavaType("Pageable"), "peek"));
        method.addParameter(new Parameter(new FullyQualifiedJavaType("SelectDSLCompleter"), "function"));
        this.compilationUnit.addMethod(method);

        method = new Method("findOne");
        method.setAbstract(true);
        method.setReturnType(new FullyQualifiedJavaType("Optional<T>"));
        method.addParameter(new Parameter(new FullyQualifiedJavaType("SelectDSLCompleter"), "function"));
        this.compilationUnit.addMethod(method);

        method = new Method("findOne");
        method.setAbstract(true);
        method.setReturnType(new FullyQualifiedJavaType("Optional<T>"));
        method.addParameter(new Parameter(new FullyQualifiedJavaType("D"), "_id"));
        this.compilationUnit.addMethod(method);

        method = new Method("count");
        method.setAbstract(true);
        method.setReturnType(new FullyQualifiedJavaType("Long"));
        method.addParameter(new Parameter(new FullyQualifiedJavaType("CountDSLCompleter"), "function"));
        this.compilationUnit.addMethod(method);

        method = new Method("create");
        method.setAbstract(true);
        method.setReturnType(new FullyQualifiedJavaType("T"));
        method.addParameter(new Parameter(new FullyQualifiedJavaType("T"), "entity"));
        this.compilationUnit.addMethod(method);

        method = new Method("create");
        method.setAbstract(true);
        method.setReturnType(new FullyQualifiedJavaType("List<T>"));
        method.addParameter(new Parameter(new FullyQualifiedJavaType("List<T>"), "entities"));
        this.compilationUnit.addMethod(method);

        method = new Method("delete");
        method.setAbstract(true);
        method.setReturnType(new FullyQualifiedJavaType("boolean"));
        method.addParameter(new Parameter(new FullyQualifiedJavaType("D"), "id_"));
        this.compilationUnit.addMethod(method);

        method = new Method("delete");
        method.setAbstract(true);
        method.setReturnType(new FullyQualifiedJavaType("boolean"));
        method.addParameter(new Parameter(new FullyQualifiedJavaType("List<D>"), "id_"));
        this.compilationUnit.addMethod(method);

        method = new Method("delete");
        method.setAbstract(true);
        method.setReturnType(new FullyQualifiedJavaType("boolean"));
        method.addParameter(new Parameter(new FullyQualifiedJavaType("DeleteDSLCompleter"), "function"));
        this.compilationUnit.addMethod(method);

        method = new Method("save");
        method.setAbstract(true);
        method.setReturnType(new FullyQualifiedJavaType("T"));
        method.addParameter(new Parameter(new FullyQualifiedJavaType("T"), "entity"));
        this.compilationUnit.addMethod(method);

        method = new Method("save");
        method.setAbstract(true);
        method.setReturnType(new FullyQualifiedJavaType("List<T>"));
        method.addParameter(new Parameter(new FullyQualifiedJavaType("List<T>"), "entities"));
        this.compilationUnit.addMethod(method);

        method = new Method("save");
        method.setAbstract(true);
        method.setReturnType(new FullyQualifiedJavaType("boolean"));
        method.addParameter(new Parameter(new FullyQualifiedJavaType("UpdateDSLCompleter"), "function"));
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
