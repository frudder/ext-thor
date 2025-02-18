package org.make.ext.generated;

import org.make.ext.DefaultProjectSpecs;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.config.Context;

import java.util.Properties;

import static com.google.common.collect.Sets.newHashSet;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.make.ext.generated.ThorFactory.ThorAttribute.TARGET_PACKAGE;
import static org.mybatis.generator.api.dom.java.JavaVisibility.PUBLIC;

public class ThorTrait extends ThorFactory {

    private final String prefix = "I";

    private final Interface compilationUnit;

    private final Context context;

    private final IntrospectedTable introspectedTable;

    private final Properties properties;

    public static ThorTrait create(final Properties properties, final Context context, final IntrospectedTable introspectedTable) {
        return new ThorTrait(properties, context, introspectedTable);
    }

    public ThorTrait(final Properties properties, Context context, IntrospectedTable introspectedTable) {
        this.context = context;
        this.properties = properties;
        this.introspectedTable = introspectedTable;
        String name = String.join(".", TARGET_PACKAGE.getProperty(this.properties), "services");
        FullyQualifiedJavaType index = new FullyQualifiedJavaType(name + "." + prefix + introspectedTable.getFullyQualifiedTable().getDomainObjectName());
        this.compilationUnit = new Interface(index);
        this.compilationUnit.addAnnotation(GENERATED);
        this.compilationUnit.setVisibility(PUBLIC);
        FullyQualifiedJavaType traitType = new FullyQualifiedJavaType(String.join(".", TARGET_PACKAGE.getProperty(this.properties), "lang", DefaultProjectSpecs.DEFAULT_SERVICE_NAME.toString()));
        traitType.addTypeArgument(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()));
        traitType.addTypeArgument(new FullyQualifiedJavaType(introspectedTable.getMyBatis3JavaMapperType()));
        this.compilationUnit.addSuperInterface(traitType);
        this.compilationUnit.addImportedTypes(newHashSet(
                new FullyQualifiedJavaType("jakarta.annotation.Generated"),
                traitType
        ));
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
        return "";
    }
}
