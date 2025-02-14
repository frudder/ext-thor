package org.make.ext.generated;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.config.Context;

import java.nio.charset.StandardCharsets;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Sets.newHashSet;
import static org.make.ext.DefaultJavaField.CREATED_AT;
import static org.make.ext.DefaultJavaField.CREATED_BY;
import static org.make.ext.DefaultJavaField.LAST_MODIFIED_BY;
import static org.make.ext.DefaultJavaField.PRIMARY_KEY;
import static org.make.ext.DefaultJavaField.SERIAL_VERSION_UID;
import static org.make.ext.DefaultJavaField.UPDATED_AT;
import static org.mybatis.generator.api.dom.java.JavaVisibility.PUBLIC;

public class EntityGenerated extends MakeGenerated {

    private final TopLevelClass compilationUnit;

    private final String name;

    private final Context context;

    public static EntityGenerated create(Context context) {
        return create(null, context);
    }

    public static EntityGenerated create(String name, Context context) {
        return new EntityGenerated(name, context);
    }

    private EntityGenerated(final String name, final Context context) {
        this.name = Strings.isNullOrEmpty(name) ? "AbstractEntity" : name;
        this.context = checkNotNull(context);
        this.compilationUnit = new TopLevelClass(new FullyQualifiedJavaType(context.getJavaModelGeneratorConfiguration().getTargetPackage() + "." + this.name));
        this.compilationUnit.setVisibility(PUBLIC);
        this.compilationUnit.addAnnotation("@Generated(value = {\"ext-thor\"})");
        this.compilationUnit.addSuperInterface(new FullyQualifiedJavaType(java.io.Serializable.class.getName()));
        this.compilationUnit.addImportedTypes(newHashSet(new FullyQualifiedJavaType(java.io.Serializable.class.getName()), new FullyQualifiedJavaType(java.util.Date.class.getName()), new FullyQualifiedJavaType(jakarta.annotation.Generated.class.getName())));
        this.compilationUnit.addStaticImports(newHashSet(Preconditions.class.getName() + ".checkNotNull"));

        SERIAL_VERSION_UID.apply(compilationUnit);
        PRIMARY_KEY.apply(compilationUnit);
        CREATED_AT.apply(compilationUnit);
        CREATED_BY.apply(compilationUnit);
        UPDATED_AT.apply(compilationUnit);
        LAST_MODIFIED_BY.apply(compilationUnit);
    }

    private EntityGenerated(final Context context) {
        this(null, context);
    }

    @Override
    public GeneratedJavaFile makeGeneratedFile() {
        return new GeneratedJavaFile(this.compilationUnit, context.getJavaModelGeneratorConfiguration().getTargetProject(), StandardCharsets.UTF_8.name(), context.getJavaFormatter());
    }

    @Override
    public String getName() {
        return name;
    }
}
