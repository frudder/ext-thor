package org.make.ext.generated;

import com.google.common.base.Strings;
import com.google.common.collect.Sets;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.TypeParameter;
import org.mybatis.generator.config.Context;

import java.nio.charset.StandardCharsets;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Sets.newHashSet;
import static org.mybatis.generator.api.dom.java.JavaVisibility.PUBLIC;

public final class MapperGenerated extends MakeFactory {

    private final Interface compilationUnit;

    private final String name;

    private final Context context;

    public static MapperGenerated create(Context context) {
        return create(null, context);
    }

    public static MapperGenerated create(String name, Context context) {
        return new MapperGenerated(name, context);
    }

    private MapperGenerated(final String name, final Context context) {
        this.name = Strings.isNullOrEmpty(name) ? "MapperAdapter" : name;
        this.context = checkNotNull(context);
        this.compilationUnit = new Interface(new FullyQualifiedJavaType(context.getJavaModelGeneratorConfiguration().getTargetPackage() + "." + this.name));
        this.compilationUnit.setVisibility(PUBLIC);
        this.compilationUnit.addAnnotation(GENERATED);
        this.compilationUnit.addTypeParameter(new TypeParameter("T"));


        FullyQualifiedJavaType mapper = new FullyQualifiedJavaType("org.mybatis.dynamic.sql.util.mybatis3.CommonInsertMapper");
        mapper.addTypeArgument(new FullyQualifiedJavaType("T"));
        this.compilationUnit.getSuperInterfaceTypes().addAll(
                Sets.newHashSet(
                        new FullyQualifiedJavaType("org.mybatis.dynamic.sql.util.mybatis3.CommonSelectMapper"),
                        new FullyQualifiedJavaType("org.mybatis.dynamic.sql.util.mybatis3.CommonCountMapper"),
                        new FullyQualifiedJavaType("org.mybatis.dynamic.sql.util.mybatis3.CommonUpdateMapper"),
                        mapper,
                        new FullyQualifiedJavaType("org.mybatis.dynamic.sql.util.mybatis3.CommonDeleteMapper")
                )
        );

        this.compilationUnit.addImportedTypes(newHashSet(
                new FullyQualifiedJavaType("org.mybatis.dynamic.sql.util.mybatis3.CommonDeleteMapper"),
                new FullyQualifiedJavaType("org.mybatis.dynamic.sql.util.mybatis3.CommonInsertMapper"),
                new FullyQualifiedJavaType("org.mybatis.dynamic.sql.util.mybatis3.CommonUpdateMapper"),
                new FullyQualifiedJavaType("org.mybatis.dynamic.sql.util.mybatis3.CommonCountMapper"),
                new FullyQualifiedJavaType("org.mybatis.dynamic.sql.util.mybatis3.CommonSelectMapper"),
                new FullyQualifiedJavaType(jakarta.annotation.Generated.class.getName())));
    }

    @Override
    public GeneratedJavaFile make() {
        return new GeneratedJavaFile(this.compilationUnit, context.getJavaClientGeneratorConfiguration().getTargetProject(), StandardCharsets.UTF_8.name(), context.getJavaFormatter());
    }

    @Override
    public String getName() {
        return name;
    }
}
