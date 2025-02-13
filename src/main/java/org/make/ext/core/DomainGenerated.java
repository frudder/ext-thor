package org.make.ext.core;

import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.config.Context;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.mybatis.generator.api.dom.java.JavaVisibility.PUBLIC;

public class DomainGenerated extends MakeGenerated {

    private final TopLevelClass compilationUnit;

    private final Context context;

    public static DomainGenerated create(Context context) {
        return new DomainGenerated(context);
    }

    private DomainGenerated(Context context) {
        this.context = checkNotNull(context);
        this.compilationUnit = new TopLevelClass(new FullyQualifiedJavaType(context.getJavaModelGeneratorConfiguration().getTargetPackage() + "." + "AbstractDomain"));
        this.compilationUnit.setVisibility(PUBLIC);
    }

    @Override
    public GeneratedJavaFile makeGeneratedFile() {
        return new GeneratedJavaFile(compilationUnit,
                context.getJavaModelGeneratorConfiguration().getTargetProject(),
                UTF_8.name(),
                context.getJavaFormatter());
    }

    @Override
    public String getName() {
        return "";
    }
}
