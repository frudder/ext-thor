package org.make.ext.generated;

import com.google.common.collect.Sets;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.config.Context;

import java.util.Properties;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.make.ext.generated.ThorFactory.ThorAttribute.THOR_DEFAULT_DOMAIN_NAME;
import static org.make.ext.generated.ThorFactory.ThorAttribute.THOR_LANG;
import static org.make.ext.generated.ThorFactory.ThorAttribute.THOR_TARGET_PACKAGE;
import static org.mybatis.generator.api.dom.java.JavaVisibility.DEFAULT;

public class ThorDomain extends ThorFactory {

    private final TopLevelClass compilationUnit;

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
        String name = String.join(".", ThorAttribute.getProperty(this.properties, THOR_TARGET_PACKAGE), "services");
        FullyQualifiedJavaType domainType = new FullyQualifiedJavaType(this.introspectedTable.getBaseRecordType());
        FullyQualifiedJavaType index = new FullyQualifiedJavaType(name + "." + domainType.getShortName() + "Domain");
        this.compilationUnit = new TopLevelClass(index);
        FullyQualifiedJavaType traitType = new FullyQualifiedJavaType(name + "." + "I" + domainType.getShortName());
        this.compilationUnit.addJavaDocLine("/** package **/");
        this.compilationUnit.setVisibility(DEFAULT);
        this.compilationUnit.addSuperInterface(traitType);
        this.compilationUnit.addAnnotation("@Service");
        this.compilationUnit.addAnnotation("@RequiredArgsConstructor(onConstructor = @__(@Autowired))");
        this.compilationUnit.addAnnotation(GENERATED);
        DefaultJavaField.LOGGER.apply(this.compilationUnit);

        FullyQualifiedJavaType traitDomain = new FullyQualifiedJavaType(String.join(".", ThorAttribute.getProperty(this.properties, THOR_TARGET_PACKAGE), THOR_LANG, THOR_DEFAULT_DOMAIN_NAME));
        FullyQualifiedJavaType T = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
        FullyQualifiedJavaType R = new FullyQualifiedJavaType(introspectedTable.getMyBatis3JavaMapperType());
        traitDomain.addTypeArgument(T);
        traitDomain.addTypeArgument(R);
        this.compilationUnit.setSuperClass(traitDomain);
        String mapper = context.getJavaClientGeneratorConfiguration().getTargetPackage();
        FullyQualifiedJavaType mapperType = new FullyQualifiedJavaType(mapper + "." + domainType.getShortName() + "Mapper");
        Field f = new Field("mapper", mapperType);
        f.setFinal(true);
        f.setVisibility(JavaVisibility.PRIVATE);
        this.compilationUnit.addField(f);
        this.compilationUnit.addImportedTypes(Sets.newHashSet(
                new FullyQualifiedJavaType("lombok.RequiredArgsConstructor"),
                new FullyQualifiedJavaType("org.slf4j.Logger"),
                new FullyQualifiedJavaType("org.slf4j.LoggerFactory"),
                new FullyQualifiedJavaType("org.springframework.beans.factory.annotation.Autowired"),
                new FullyQualifiedJavaType("org.springframework.stereotype.Service"),
                new FullyQualifiedJavaType("jakarta.annotation.Generated"),
                mapperType,
                traitDomain,
                T,
                R
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
