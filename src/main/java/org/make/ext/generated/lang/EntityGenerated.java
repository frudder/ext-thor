package org.make.ext.generated.lang;

import com.squareup.javapoet.CodeBlock;
import org.make.ext.generated.ThorFactory;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Method;
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
import static org.make.ext.generated.DefaultJavaField.CREATED_AT;
import static org.make.ext.generated.DefaultJavaField.CREATED_BY;
import static org.make.ext.generated.DefaultJavaField.LAST_MODIFIED_BY;
import static org.make.ext.generated.DefaultJavaField.PRIMARY_KEY;
import static org.make.ext.generated.DefaultJavaField.SERIAL_VERSION_UID;
import static org.make.ext.generated.DefaultJavaField.UPDATED_AT;
import static org.make.ext.generated.ThorFactory.ThorAttribute.THOR_DEFAULT_ENTITY_NAME;
import static org.make.ext.generated.ThorFactory.ThorAttribute.THOR_LANG;
import static org.make.ext.generated.ThorFactory.ThorAttribute.THOR_TARGET_PACKAGE;
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
        this.name = isNullOrEmpty(name) ? THOR_DEFAULT_ENTITY_NAME : name;
        this.context = checkNotNull(context);
        String specs = String.join(".", ThorAttribute.getProperty(this.properties, THOR_TARGET_PACKAGE), THOR_LANG, this.name);
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
                new FullyQualifiedJavaType("com.google.common.base.Strings"),
                new FullyQualifiedJavaType("com.fasterxml.jackson.annotation.JsonIgnore"),
                new FullyQualifiedJavaType("jakarta.annotation.Generated")));
        SERIAL_VERSION_UID.apply(compilationUnit);
        PRIMARY_KEY.apply(compilationUnit);
        CREATED_AT.apply(compilationUnit);
        CREATED_BY.apply(compilationUnit);
        UPDATED_AT.apply(compilationUnit);
        LAST_MODIFIED_BY.apply(compilationUnit);
        Method method = new Method("isEmpty");
        method.addAnnotation(GENERATED);
        method.setVisibility(PUBLIC);
        method.setReturnType(new FullyQualifiedJavaType("boolean"));
        method.addBodyLine(CodeBlock.builder()
                        .beginControlFlow("if (id == null)")
                        .addStatement("return true")
                        .endControlFlow()
                        .beginControlFlow("if (id instanceof String)")
                        .addStatement("return Strings.isNullOrEmpty((String) id)")
                        .endControlFlow()
                        .beginControlFlow("if (id instanceof Long)")
                        .addStatement("return ((Long) id) == 0L")
                        .endControlFlow()
                        .beginControlFlow("if (id instanceof Integer)")
                        .addStatement("return ((Integer) id) == 0")
                        .endControlFlow()
                        .addStatement("return false")
                .build().toString());
        this.compilationUnit.addMethod(method);

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
