package org.make.ext.generated.base;

import org.make.ext.generated.ThorFactory;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.config.Context;

import java.util.Properties;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Sets.newHashSet;
import static java.nio.charset.StandardCharsets.UTF_8;
//import static org.make.ext.DefaultProjectSpecs.DEFAULT_DOMAIN_NAME;
//import static org.make.ext.DefaultProjectSpecs.DEFAULT_SERVICE_NAME;
//import static org.make.ext.generated.ThorFactory.ThorAttribute.TARGET_PACKAGE;
import static org.make.ext.generated.ThorFactory.ThorAttribute.THOR_DEFAULT_DOMAIN_NAME;
import static org.make.ext.generated.ThorFactory.ThorAttribute.THOR_DEFAULT_SERVICE_NAME;
import static org.make.ext.generated.ThorFactory.ThorAttribute.THOR_LANG;
import static org.make.ext.generated.ThorFactory.ThorAttribute.THOR_TARGET_PACKAGE;
import static org.mybatis.generator.api.dom.java.JavaVisibility.PUBLIC;

public final class DomainGenerated extends ThorFactory {

    private final TopLevelClass compilationUnit;

    private final Context context;

    private final Properties properties;

    public static DomainGenerated create(Properties properties, Context context) {
        return new DomainGenerated(properties, context);
    }

    private DomainGenerated(Properties properties, Context context) {
        this.properties = properties;
        this.context = checkNotNull(context);
        String name = ThorAttribute.getProperty(this.properties, THOR_TARGET_PACKAGE);
        name = String.join(".", name, THOR_LANG, THOR_DEFAULT_DOMAIN_NAME);
        FullyQualifiedJavaType domainType = new FullyQualifiedJavaType(name);
        // // T extends ThorEntity<? extends Serializable>, R extends MapperAdapter<T>
        domainType.addTypeArgument(new FullyQualifiedJavaType("T extends ThorEntity <? extends Serializable> "));
        domainType.addTypeArgument(new FullyQualifiedJavaType("R extends MapperAdapter<T>"));
        FullyQualifiedJavaType traitType = new FullyQualifiedJavaType(THOR_DEFAULT_SERVICE_NAME);
        traitType.addTypeArgument(new FullyQualifiedJavaType("T"));
        traitType.addTypeArgument(new FullyQualifiedJavaType("R"));
        this.compilationUnit = new TopLevelClass(domainType);
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
