package org.make.ext.generated;

import com.google.common.base.Strings;
import jakarta.annotation.Generated;
import lombok.Data;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.java.TypeParameter;
import org.mybatis.generator.config.Context;

import java.io.Serial;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Sets.newHashSet;
import static org.make.ext.DefaultJavaField.CREATED_AT;
import static org.make.ext.DefaultJavaField.CREATED_BY;
import static org.make.ext.DefaultJavaField.LAST_MODIFIED_BY;
import static org.make.ext.DefaultJavaField.PRIMARY_KEY;
import static org.make.ext.DefaultJavaField.SERIAL_VERSION_UID;
import static org.make.ext.DefaultJavaField.UPDATED_AT;
import static org.mybatis.generator.api.dom.java.JavaVisibility.PUBLIC;

public final class EntityGenerated extends MakeGenerated {

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
        this.compilationUnit.setAbstract(true);
        this.compilationUnit.setVisibility(PUBLIC);
        this.compilationUnit.addAnnotation("@Data");
        this.compilationUnit.addAnnotation(GENERATED);
        this.compilationUnit.addTypeParameter(new TypeParameter("T"));
        this.compilationUnit.addSuperInterface(new FullyQualifiedJavaType(Serializable.class.getName()));
        this.compilationUnit.addImportedTypes(newHashSet(new FullyQualifiedJavaType(Serializable.class.getName()),
                new FullyQualifiedJavaType(Date.class.getName()),
                new FullyQualifiedJavaType(Serial.class.getName()),
                new FullyQualifiedJavaType(Data.class.getName()),
                new FullyQualifiedJavaType(Serial.class.getName()),
                new FullyQualifiedJavaType(Generated.class.getName())));

        SERIAL_VERSION_UID.apply(compilationUnit);
        PRIMARY_KEY.apply(compilationUnit);
        CREATED_AT.apply(compilationUnit);
        CREATED_BY.apply(compilationUnit);
        UPDATED_AT.apply(compilationUnit);
        LAST_MODIFIED_BY.apply(compilationUnit);
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
