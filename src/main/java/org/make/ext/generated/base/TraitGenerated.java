package org.make.ext.generated.base;

import org.make.ext.generated.ThorFactory;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
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

    public static TraitGenerated create(Properties properties, Context context) {
        return new TraitGenerated(properties, context);
    }

    private TraitGenerated(Properties properties, Context context) {
        this.properties = properties;
        this.context = checkNotNull(context);
        String name = ThorAttribute.getProperty(this.properties, THOR_TARGET_PACKAGE);
        name = String.join(".", name, "lang", THOR_DEFAULT_SERVICE_NAME);
        FullyQualifiedJavaType traitType = new FullyQualifiedJavaType(name);
        // T extends ThorEntity<? extends Serializable>, R extends MapperAdapter<T>
        traitType.addTypeArgument(new FullyQualifiedJavaType("T extends ThorEntity < ? extends Serializable>"));
        traitType.addTypeArgument(new FullyQualifiedJavaType("R extends MapperAdapter<T> "));
        this.compilationUnit = new Interface(traitType);
        this.compilationUnit.setVisibility(PUBLIC);
        this.compilationUnit.addAnnotation(GENERATED);
        this.compilationUnit.addImportedTypes(newHashSet(
                new FullyQualifiedJavaType("jakarta.annotation.Generated"),
                new FullyQualifiedJavaType("java.io.Serializable")
        ));
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
        return "";
    }

    public Properties getProperties() {
        return properties;
    }
}
