package org.make.ext.generated.base;

import org.make.ext.generated.ThorFactory;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.java.TypeParameter;
import org.mybatis.generator.config.Context;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Properties;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Strings.isNullOrEmpty;
import static com.google.common.collect.Sets.newHashSet;
import static org.make.ext.DefaultJavaField.CREATED_AT;
import static org.make.ext.DefaultJavaField.CREATED_BY;
import static org.make.ext.DefaultJavaField.LAST_MODIFIED_BY;
import static org.make.ext.DefaultJavaField.PRIMARY_KEY;
import static org.make.ext.DefaultJavaField.SERIAL_VERSION_UID;
import static org.make.ext.DefaultJavaField.UPDATED_AT;
import static org.make.ext.generated.ThorFactory.ThorAttribute.TARGET_PACKAGE;
import static org.mybatis.generator.api.dom.java.JavaVisibility.PUBLIC;

public final class EntityGenerated extends ThorFactory {

    private final TopLevelClass compilationUnit;

    private final String name;

    private final Context context;

    private final Properties properties;

    public static EntityGenerated create(Properties properties, Context context) {
        return create(properties, null, context);
    }

    public static EntityGenerated create(Properties properties, String name, Context context) {
        return new EntityGenerated(properties, name, context);
    }

    private EntityGenerated(final Properties properties, final String name, final Context context) {
        this.properties = properties;
        this.name = isNullOrEmpty(name) ? "AbstractEntity" : name;
        this.context = checkNotNull(context);
        String specs = String.join(".", TARGET_PACKAGE.getProperty(properties), "lang", this.name);
        this.compilationUnit = new TopLevelClass(new FullyQualifiedJavaType(specs));
        this.compilationUnit.setAbstract(true);
        this.compilationUnit.setVisibility(PUBLIC);
        this.compilationUnit.addAnnotation("@Data");
        this.compilationUnit.addAnnotation(GENERATED);
        this.compilationUnit.addTypeParameter(new TypeParameter("T"));
        this.compilationUnit.addSuperInterface(new FullyQualifiedJavaType(Serializable.class.getName()));
        this.compilationUnit.addImportedTypes(newHashSet(new FullyQualifiedJavaType(Serializable.class.getName()),
                new FullyQualifiedJavaType(Date.class.getName()),
                new FullyQualifiedJavaType("lombok.Data"),
                new FullyQualifiedJavaType("jakarta.annotation.Generated")));
        SERIAL_VERSION_UID.apply(compilationUnit);
        PRIMARY_KEY.apply(compilationUnit);
        CREATED_AT.apply(compilationUnit);
        CREATED_BY.apply(compilationUnit);
        UPDATED_AT.apply(compilationUnit);
        LAST_MODIFIED_BY.apply(compilationUnit);
    }

    @Override
    public GeneratedJavaFile make() {
        return new GeneratedJavaFile(this.compilationUnit, context.getJavaModelGeneratorConfiguration().getTargetProject(), StandardCharsets.UTF_8.name(), context.getJavaFormatter());
    }

    @Override
    public String getName() {
        return name;
    }

    public Properties getProperties() {
        return properties;
    }
}
