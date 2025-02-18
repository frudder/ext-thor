package org.make.ext.generated;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.config.Context;

import java.io.Serializable;
import java.util.Properties;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.mybatis.generator.api.dom.java.JavaVisibility.PRIVATE;
import static org.mybatis.generator.api.dom.java.JavaVisibility.PUBLIC;
import static org.mybatis.generator.internal.util.JavaBeansUtil.getCamelCaseString;

public class ThorController extends ThorFactory {

    private final TopLevelClass compilationUnit;

    private final String name;

    private final Context context;

    private final IntrospectedTable introspectedTable;

    private final Properties properties;

    public static ThorController create(Properties properties, Context context, IntrospectedTable introspectedTable) {
        return new ThorController(properties, context, introspectedTable);
    }

    public ThorController(Properties properties, Context context, IntrospectedTable introspectedTable) {
        this.properties = properties;
        this.context = context;
        FullyQualifiedJavaType domain = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
        this.introspectedTable = introspectedTable;
        String name = ThorAttribute.TARGET_PACKAGE.getProperty(this.properties);
        FullyQualifiedJavaType token = new FullyQualifiedJavaType(String.join(".", name, "controllers"));
        this.name = token.getShortName();
        this.compilationUnit = new TopLevelClass(token + "." + domain.getShortName() + "Controller");
        this.compilationUnit.setVisibility(PUBLIC);
        String resources = introspectedTable.getTableConfiguration().getTableName();
        FullyQualifiedJavaType anyType = new FullyQualifiedJavaType(String.join(".", name, "views", domain.getShortName() + "Value"));
        FullyQualifiedJavaType router = new FullyQualifiedJavaType(String.join(".", name, "lang", "Router"));
        router.addTypeArgument(anyType);
        this.compilationUnit.addSuperInterface(router);
        this.compilationUnit.addAnnotation("@Tags(value = { @Tag(name = \"" + getCamelCaseString(resources, true) + "\")" + "})");
        this.compilationUnit.addAnnotation(GENERATED);
        this.compilationUnit.addAnnotation("@RestController");
        this.compilationUnit.addAnnotation("@RequestMapping(value = \"/" + resources.replace("_", "/") + "\")");
        this.compilationUnit.addAnnotation("@RequiredArgsConstructor(onConstructor = @__(@Autowired))");

        FullyQualifiedJavaType services = new FullyQualifiedJavaType(name + "." + "services" + "." + "I" + domain.getShortName());
        Field f = new Field("domain", services);
        f.setVisibility(PRIVATE);
        f.setFinal(true);
        this.compilationUnit.addField(f);
        this.compilationUnit.addImportedTypes(Sets.newHashSet(
                new FullyQualifiedJavaType("io.swagger.v3.oas.annotations.tags.Tag"),
                new FullyQualifiedJavaType("io.swagger.v3.oas.annotations.tags.Tags"),
                new FullyQualifiedJavaType("jakarta.annotation.Generated"),
                new FullyQualifiedJavaType("lombok.RequiredArgsConstructor"),
                new FullyQualifiedJavaType("org.springframework.beans.factory.annotation.Autowired"),
                new FullyQualifiedJavaType("org.springframework.web.bind.annotation.RequestMapping"),
                new FullyQualifiedJavaType("org.springframework.web.bind.annotation.RestController"),
                new FullyQualifiedJavaType("java.io.Serializable"),
                new FullyQualifiedJavaType("java.util.List"),
                router,
                anyType,
                services
        ));

        Method find = new Method("find");
        find.setVisibility(PUBLIC);
        find.addAnnotation("@Override");
        find.setReturnType(anyType);
        FullyQualifiedJavaType item = new FullyQualifiedJavaType("List");
        item.addTypeArgument(new FullyQualifiedJavaType(Serializable.class.getName()));
        Parameter parameter = new Parameter(item, "item");
        find.addParameter(parameter);
        find.addBodyLine("return null;");
        this.compilationUnit.addMethod(find);

        Method seek = new Method("find");
        seek.setVisibility(PUBLIC);
        seek.addAnnotation("@Override");
        FullyQualifiedJavaType returnType = new FullyQualifiedJavaType("List");
        returnType.addTypeArgument(anyType);
        seek.setReturnType(returnType);
        seek.getParameters().addAll(ImmutableList.of(
                new Parameter(new FullyQualifiedJavaType("Integer"), "index"),
                new Parameter(new FullyQualifiedJavaType("Integer"), "size"),
                new Parameter(new FullyQualifiedJavaType(domain.getShortName() + "Value"), "value")
        ));
        seek.addBodyLine("return List.of();");
        this.compilationUnit.addMethod(seek);

        Method remove = new Method("remove");
        remove.setVisibility(PUBLIC);
        remove.addAnnotation("@Override");
        remove.setReturnType(new FullyQualifiedJavaType("boolean"));
        FullyQualifiedJavaType parameterType = new FullyQualifiedJavaType("List");
        parameterType.addTypeArgument(new FullyQualifiedJavaType(Serializable.class.getName()));
        remove.addParameter(new Parameter(parameterType, "item"));
        remove.addBodyLine("return false;");
        this.compilationUnit.addMethod(remove);

        Method create = new Method("create");
        create.setVisibility(PUBLIC);
        create.addAnnotation("@Override");
        create.setReturnType(anyType);
        create.addParameter(new Parameter(anyType, "body"));
        create.addBodyLine("return null;");
        this.compilationUnit.addMethod(create);

        Method bulk = new Method("create");
        bulk.setVisibility(PUBLIC);
        bulk.addAnnotation("@Override");
        FullyQualifiedJavaType rType = new FullyQualifiedJavaType("List");
        rType.addTypeArgument(anyType);
        bulk.setReturnType(rType);
        FullyQualifiedJavaType pType = new FullyQualifiedJavaType("List");
        pType.addTypeArgument(anyType);
        bulk.addParameter(new Parameter(pType, "body"));
        bulk.addBodyLine("return List.of();");
        this.compilationUnit.addMethod(bulk);
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
