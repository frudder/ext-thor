package org.make.ext.generated.base;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.make.ext.generated.ThorFactory;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.TypeParameter;
import org.mybatis.generator.config.Context;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Sets.newHashSet;
import static com.google.common.collect.Sets.newLinkedHashSet;
import static org.make.ext.generated.ThorFactory.ThorAttribute.TARGET_PACKAGE;
import static org.mybatis.generator.api.dom.java.JavaVisibility.PUBLIC;

public final class MapperGenerated extends ThorFactory {

    private static final String TARGET_RUNTIME = "MyBatis3DynamicSql";

    private final Interface compilationUnit;

    private final String name;

    private final Context context;

    private final Properties properties;

    public static MapperGenerated create(Properties properties, Context context) {
        return create(properties, null, context);
    }

    public static MapperGenerated create(Properties properties, String name, Context context) {
        return new MapperGenerated(properties, name, context);
    }

    private MapperGenerated(final Properties properties, final String name, final Context context) {
        this.properties = properties;
        this.name = Strings.isNullOrEmpty(name) ? "MapperAdapter" : name;
        this.context = checkNotNull(context);
        FullyQualifiedJavaType base = new FullyQualifiedJavaType(String.join(".", TARGET_PACKAGE.getProperty(this.properties), "lang", this.name));
        this.compilationUnit = new Interface(base);
        this.compilationUnit.setVisibility(PUBLIC);
        this.compilationUnit.addAnnotation(GENERATED);
        this.compilationUnit.addTypeParameter(new TypeParameter("T"));
        List<FullyQualifiedJavaType> iterable = Lists.newArrayListWithCapacity(16);
        Set<FullyQualifiedJavaType> imported = newLinkedHashSet();
        if (TARGET_RUNTIME.equalsIgnoreCase(context.getTargetRuntime())) {
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
        imported.add(new FullyQualifiedJavaType("jakarta.annotation.Generated"));
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
