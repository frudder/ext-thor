package org.make.ext.generated;

import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.config.Context;

import java.util.Properties;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.mybatis.generator.api.dom.java.JavaVisibility.PUBLIC;

public class ThorDomain extends ThorFactory {

    private final TopLevelClass compilationUnit;

    private final String name;

    private final Context context;

    private final IntrospectedTable introspectedTable;

    private final Properties properties;

    public static ThorDomain create(final Properties properties, final Context context, final IntrospectedTable introspectedTable) {
        return new ThorDomain(properties, context, introspectedTable);
    }

    public ThorDomain(Properties properties, Context context, IntrospectedTable introspectedTable) {
        this.properties = properties;
        this.context = context;
        this.introspectedTable = introspectedTable;
        FullyQualifiedJavaType index = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
        this.name = index.getShortName() + "Domain";
        this.compilationUnit = new TopLevelClass(index.getPackageName() + "." + this.name);
        this.compilationUnit.setVisibility(PUBLIC);
    }

    @Override
    public GeneratedJavaFile make() {
        return new GeneratedJavaFile(this.compilationUnit,
                context.getJavaModelGeneratorConfiguration().getTargetProject(),
                UTF_8.name(),
                context.getJavaFormatter());
    }

    @Override
    public String getName() {
        return this.name;
    }
}
