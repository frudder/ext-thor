package org.make.ext.generated.base;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.make.ext.generated.ThorJavaFactory;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.TypeParameter;
import org.mybatis.generator.config.Context;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Sets.newHashSet;
import static com.google.common.collect.Sets.newLinkedHashSet;
import static org.mybatis.generator.api.dom.java.JavaVisibility.PUBLIC;

public final class MapperGenerated extends ThorJavaFactory {

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
        List<FullyQualifiedJavaType> iterable = Lists.newArrayListWithCapacity(16);
        Set<FullyQualifiedJavaType> imported = newLinkedHashSet();
        if ("MyBatis3DynamicSql".equalsIgnoreCase(context.getTargetRuntime())) {
            FullyQualifiedJavaType mapper = new FullyQualifiedJavaType("org.mybatis.dynamic.sql.util.mybatis3.CommonInsertMapper");
            mapper.addTypeArgument(new FullyQualifiedJavaType("T"));
            iterable.add(mapper);
            iterable.addAll(Sets.newHashSet(
                    new FullyQualifiedJavaType("org.mybatis.dynamic.sql.util.mybatis3.CommonSelectMapper"),
                    new FullyQualifiedJavaType("org.mybatis.dynamic.sql.util.mybatis3.CommonCountMapper"),
                    new FullyQualifiedJavaType("org.mybatis.dynamic.sql.util.mybatis3.CommonUpdateMapper"),
                    new FullyQualifiedJavaType("org.mybatis.dynamic.sql.util.mybatis3.CommonDeleteMapper")));

            imported.addAll(newHashSet(new FullyQualifiedJavaType("org.mybatis.dynamic.sql.util.mybatis3.CommonDeleteMapper"),
                    new FullyQualifiedJavaType("org.mybatis.dynamic.sql.util.mybatis3.CommonInsertMapper"),
                    new FullyQualifiedJavaType("org.mybatis.dynamic.sql.util.mybatis3.CommonUpdateMapper"),
                    new FullyQualifiedJavaType("org.mybatis.dynamic.sql.util.mybatis3.CommonCountMapper"),
                    new FullyQualifiedJavaType("org.mybatis.dynamic.sql.util.mybatis3.CommonSelectMapper")));
        }
        imported.add(new FullyQualifiedJavaType(jakarta.annotation.Generated.class.getName()));
        this.compilationUnit.getSuperInterfaceTypes().addAll(iterable);
        this.compilationUnit.addImportedTypes(imported);
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
