package org.make.ext.generated;

import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.config.Context;

import java.nio.charset.StandardCharsets;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.mybatis.generator.api.dom.java.JavaVisibility.PUBLIC;

public final class RouteGenerated extends MakeFactory {

    private final TopLevelClass compilationUnit;

    private final Context context;

    private RouteGenerated(final Context context) {
        this.context = checkNotNull(context);
        this.compilationUnit = new TopLevelClass(new FullyQualifiedJavaType(context.getJavaModelGeneratorConfiguration().getTargetPackage() + "." + "AbstractEntity"));
        this.compilationUnit.addSuperInterface(new FullyQualifiedJavaType(java.io.Serializable.class.getName()));
        this.compilationUnit.addImportedType(new FullyQualifiedJavaType(java.io.Serializable.class.getName()));
        this.compilationUnit.setVisibility(PUBLIC);
    }

    @Override
    public GeneratedJavaFile make() {
        return new GeneratedJavaFile(compilationUnit,
                context.getJavaModelGeneratorConfiguration().getTargetProject(),
                StandardCharsets.UTF_8.name(),
                context.getJavaFormatter());
    }

    @Override
    public String getName() {
        return "AbstractRoute";
    }
}
