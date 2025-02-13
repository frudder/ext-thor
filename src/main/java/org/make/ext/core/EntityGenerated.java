package org.make.ext.core;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.config.Context;

import java.nio.charset.StandardCharsets;
import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.mybatis.generator.api.dom.java.JavaVisibility.PUBLIC;

public class EntityGenerated extends MakeGenerated {

    private final TopLevelClass compilationUnit;

    private final String name;

    private final Context context;

    public static EntityGenerated create(Context context) {
        return new EntityGenerated(context);
    }

    public static EntityGenerated create(String name, Context context) {
        return new EntityGenerated(name, context);
    }

    private EntityGenerated(final String name, final Context context) {
        this.name = Strings.isNullOrEmpty(name) ? "AbstractEntity" : name;
        this.context = checkNotNull(context);

        this.compilationUnit = new TopLevelClass(new FullyQualifiedJavaType(context.getJavaModelGeneratorConfiguration().getTargetPackage() + "." + name));
        this.compilationUnit.addAnnotation("@jakarta.annotation.Generated");
        this.compilationUnit.addSuperInterface(new FullyQualifiedJavaType(java.io.Serializable.class.getName()));
        this.compilationUnit.addImportedTypes(Sets.newHashSet(new FullyQualifiedJavaType(java.io.Serializable.class.getName()), new FullyQualifiedJavaType(java.util.Date.class.getName()), new FullyQualifiedJavaType(jakarta.annotation.Generated.class.getName())));

        this.compilationUnit.addStaticImports(Sets.newHashSet(Preconditions.class.getName() + ".checkNotNull"));

        this.compilationUnit.setVisibility(PUBLIC);

        Set<Field> fields = fields();
        fields.forEach(this.compilationUnit::addField);

        Set<Method> methods = methods();
        methods.forEach(this.compilationUnit::addMethod);
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

    private Field serialVersionUID() {
        Field f = new Field("serialVersionUID", new FullyQualifiedJavaType("long"));
        f.setFinal(true);
        f.setStatic(true);
        f.setVisibility(JavaVisibility.PRIVATE);
        f.setInitializationString("1L");
        return f;
    }

    private Field createdAtField() {
        Field f = new Field("createdAt", new FullyQualifiedJavaType("java.util.Date"));
        f.setVisibility(JavaVisibility.PRIVATE);
        return f;
    }

    private Field updatedAtField() {
        Field f = new Field("updatedAt", new FullyQualifiedJavaType("java.util.Date"));
        f.setVisibility(JavaVisibility.PRIVATE);
        return f;
    }

    private Field createdByField() {
        Field f = new Field("createdBy", new FullyQualifiedJavaType("java.lang.String"));
        f.setVisibility(JavaVisibility.PRIVATE);
        return f;
    }

    private Field lastModifiedBy() {
        Field f = new Field("lastModifiedBy", new FullyQualifiedJavaType("java.lang.String"));
        f.setVisibility(JavaVisibility.PRIVATE);
        return f;
    }


    private ImmutablePair<Method, Method> createdAt() {
        Method set = new Method("setCreatedAt");
        set.setVisibility(JavaVisibility.PUBLIC);
        set.addParameter(new Parameter(new FullyQualifiedJavaType("java.util.Date"), "createdAt"));
        set.addBodyLine("this.createdAt = checkNotNull(createdAt, \"createdAt null\");");

        Method get = new Method("getCreatedAt");
        get.setVisibility(JavaVisibility.PUBLIC);
        set.addBodyLine("return this.createdAt;");
        return ImmutablePair.of(set, get);
    }

    private ImmutablePair<Method, Method> updatedAt() {
        Method set = new Method("setUpdatedAt");
        set.setVisibility(JavaVisibility.PUBLIC);
        set.addParameter(new Parameter(new FullyQualifiedJavaType("java.util.Date"), "updatedAt"));
        set.addBodyLine("this.updatedAt = checkNotNull(updatedAt, \"updatedAt null\");");
        Method get = new Method("getUpdatedAt");
        get.setVisibility(JavaVisibility.PUBLIC);
        set.addBodyLine("return this.updatedAt;");
        return ImmutablePair.of(set, get);
    }

    private Set<Method> methods() {
        return Sets.newHashSet(createdAt().getLeft(), createdAt().getRight(), updatedAt().getLeft(), updatedAt().getRight());
    }

    private Set<Field> fields() {
        return Sets.newHashSet(serialVersionUID(), createdAtField(), createdByField(), updatedAtField(), lastModifiedBy());
    }
}
