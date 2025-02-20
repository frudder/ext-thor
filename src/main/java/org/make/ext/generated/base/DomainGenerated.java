package org.make.ext.generated.base;

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
import static org.mybatis.generator.api.dom.java.JavaVisibility.PRIVATE;
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
                new FullyQualifiedJavaType("jakarta.annotation.Resource")
        ));

        Field f = new Field("mapper", new FullyQualifiedJavaType("M"));
        f.setVisibility(PROTECTED);
        f.addAnnotation("@Resource");
        this.compilationUnit.addField(f);

        Method method = new Method("findAll");
        method.setVisibility(PUBLIC);
        method.addAnnotation("@Override");
        FullyQualifiedJavaType returnType = new FullyQualifiedJavaType("List");
        returnType.addTypeArgument(new FullyQualifiedJavaType("T"));
        method.setReturnType(returnType);
        method.addBodyLine("return List.of();");
        this.compilationUnit.addMethod(method);

        method = new Method("findAll");
        method.setVisibility(PUBLIC);
        method.addAnnotation("@Override");
        method.setReturnType(returnType);
        method.addParameter(new Parameter(new FullyQualifiedJavaType("Integer"), "offset"));
        method.addParameter(new Parameter(new FullyQualifiedJavaType("Integer"), "limit"));
        method.addBodyLine("return List.of();");
        this.compilationUnit.addMethod(method);

        method = new Method("findOne");
        method.setVisibility(PUBLIC);
        method.addAnnotation("@Override");
        returnType = new FullyQualifiedJavaType("Optional");
        returnType.addTypeArgument(new FullyQualifiedJavaType("T"));
        method.setReturnType(returnType);
        method.addParameter(new Parameter(new FullyQualifiedJavaType("Serializable"), "id"));
        method.addBodyLine("return Optional.empty();");
        this.compilationUnit.addMethod(method);

        method = new Method("findOne");
        method.setVisibility(PUBLIC);
        method.addAnnotation("@Override");
        method.setReturnType(returnType);
        method.addBodyLine("return Optional.empty();");
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
