package org.make.ext.generated.lang;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.make.ext.generated.ThorFactory;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.config.Context;

import java.util.List;
import java.util.Properties;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Strings.isNullOrEmpty;
import static com.google.common.collect.Sets.newHashSet;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.make.ext.generated.ThorFactory.ThorAttribute.THOR_DEFAULT_CONTROLLER_NAME;
import static org.make.ext.generated.ThorFactory.ThorAttribute.THOR_LANG;
import static org.make.ext.generated.ThorFactory.ThorAttribute.THOR_TARGET_PACKAGE;
import static org.mybatis.generator.api.dom.java.JavaVisibility.PUBLIC;

public final class RouteGenerated extends ThorFactory {

    private final Interface compilationUnit;

    private final Context context;

    private final String name;

    private final Properties properties;


    public static RouteGenerated create(Properties properties, Context context, String name) {
        return new RouteGenerated(properties, context, name);
    }

    public static RouteGenerated create(Properties properties, Context context) {
        return create(properties, context, null);
    }

    private RouteGenerated(Properties properties, final Context context, final String name) {
        this.properties = checkNotNull(properties);
        this.context = checkNotNull(context);
        this.name = isNullOrEmpty(name) ? THOR_DEFAULT_CONTROLLER_NAME : name;
        FullyQualifiedJavaType token = new FullyQualifiedJavaType(String.join(".", ThorAttribute.getProperty(this.properties, THOR_TARGET_PACKAGE), THOR_LANG, this.name));
        token.addTypeArgument(new FullyQualifiedJavaType("E"));
        token.addTypeArgument(new FullyQualifiedJavaType("T"));
        this.compilationUnit = new Interface(token);
        this.compilationUnit.setVisibility(PUBLIC);
        this.compilationUnit.addAnnotation(GENERATED);
        this.compilationUnit.addAnnotation("@RequestMapping(value = \" default \",  consumes = { APPLICATION_JSON_VALUE, TEXT_HTML_VALUE }, produces = { APPLICATION_JSON_VALUE })");
        this.compilationUnit.addAnnotation("@Validated");

        this.compilationUnit.addImportedTypes(newHashSet(
                new FullyQualifiedJavaType("jakarta.annotation.Generated"),
                new FullyQualifiedJavaType("jakarta.validation.constraints.NotEmpty"),
                new FullyQualifiedJavaType("org.springframework.data.domain.Page"),
                new FullyQualifiedJavaType("org.springframework.validation.annotation.Validated"),
                new FullyQualifiedJavaType("org.springframework.web.bind.annotation.DeleteMapping"),
                new FullyQualifiedJavaType("org.springframework.web.bind.annotation.GetMapping"),
                new FullyQualifiedJavaType("org.springframework.web.bind.annotation.PathVariable"),
                new FullyQualifiedJavaType("org.springframework.web.bind.annotation.PutMapping"),
                new FullyQualifiedJavaType("org.springframework.web.bind.annotation.RequestBody"),
                new FullyQualifiedJavaType("org.springframework.web.bind.annotation.RequestMapping"),
                new FullyQualifiedJavaType("org.springframework.web.bind.annotation.RequestParam"),
                new FullyQualifiedJavaType("java.util.List"),
                new FullyQualifiedJavaType("java.util.function.Supplier"),
                new FullyQualifiedJavaType("java.util.function.BooleanSupplier")
        ));
        this.compilationUnit.addStaticImports(newHashSet(
                "org.springframework.http.MediaType.APPLICATION_JSON_VALUE",
                "org.springframework.http.MediaType.TEXT_HTML_VALUE"
        ));

        Method find = new Method("find");
        find.setAbstract(true);
        find.addAnnotation("@GetMapping(value = \"/{id}\")");
        FullyQualifiedJavaType returnType = new FullyQualifiedJavaType("Supplier");
        returnType.addTypeArgument(new FullyQualifiedJavaType("List<E>"));
        find.setReturnType(returnType);
        FullyQualifiedJavaType item = new FullyQualifiedJavaType("List");
        item.addTypeArgument(new FullyQualifiedJavaType("T"));
        Parameter parameter = new Parameter(item, "item");
        parameter.addAnnotation("@PathVariable(value = \"id\")");
        parameter.addAnnotation("@NotEmpty");
        find.getParameters().addAll(ImmutableList.of(parameter));
        this.compilationUnit.addMethod(find);


        Method seek = new Method("find");
        seek.setAbstract(true);
        seek.addAnnotation("@GetMapping");
        returnType = new FullyQualifiedJavaType("Supplier");
        returnType.addTypeArgument(new FullyQualifiedJavaType("Page<E>"));
        seek.setReturnType(returnType);
        List<Parameter> parameters = Lists.newArrayListWithCapacity(16);
        parameter = new Parameter(new FullyQualifiedJavaType("Integer"), "index");
        parameter.addAnnotation("@RequestParam(value = \"index\", defaultValue = \"1\")");
        parameters.add(parameter);
        parameter = new Parameter(new FullyQualifiedJavaType("Integer"), "size");
        parameter.addAnnotation("@RequestParam(value = \"size\", defaultValue = \"10\")");
        parameters.add(parameter);
        parameter = new Parameter(new FullyQualifiedJavaType("E"), "value");
        parameter.addAnnotation("@RequestParam(required = false)");
        parameters.add(parameter);
        seek.getParameters().addAll(parameters);
        this.compilationUnit.addMethod(seek);

        Method remove = new Method("remove");
        remove.setAbstract(true);
        returnType = new FullyQualifiedJavaType("BooleanSupplier");
        remove.setReturnType(returnType);
        remove.addAnnotation("@DeleteMapping(value = \"/{id}\")");
        item = new FullyQualifiedJavaType("List");
        item.addTypeArgument(new FullyQualifiedJavaType("T"));
        parameter = new Parameter(item, "item");
        parameter.addAnnotation("@PathVariable(value = \"id\")");
        parameter.addAnnotation("@NotEmpty");
        remove.getParameters().addAll(ImmutableList.of(parameter));
        this.compilationUnit.addMethod(remove);

        Method create = new Method("create");
        create.setAbstract(true);
        returnType = new FullyQualifiedJavaType("Supplier");
        returnType.addTypeArgument(new FullyQualifiedJavaType("E"));
        create.setReturnType(returnType);
        create.addAnnotation("@PutMapping");
        FullyQualifiedJavaType body = new FullyQualifiedJavaType("E");
        parameter = new Parameter(body, "body");
        parameter.addAnnotation("@RequestBody");
        parameter.addAnnotation("@Validated");
        create.getParameters().addAll(ImmutableList.of(parameter));
        this.compilationUnit.addMethod(create);

        Method bulk = new Method("create");
        bulk.setAbstract(true);
        returnType = new FullyQualifiedJavaType("Supplier");
        returnType.addTypeArgument(new FullyQualifiedJavaType("List<E>"));
        bulk.setReturnType(returnType);
        bulk.addAnnotation("@PutMapping(value = \"/bulk\")");
        body = new FullyQualifiedJavaType("List");
        body.addTypeArgument(new FullyQualifiedJavaType("E"));
        parameter = new Parameter(body, "body");
        parameter.addAnnotation("@RequestBody");
        parameter.addAnnotation("@Validated");
        bulk.getParameters().addAll(ImmutableList.of(parameter));
        this.compilationUnit.addMethod(bulk);
    }

    @Override
    public GeneratedJavaFile make() {
        return new GeneratedJavaFile(compilationUnit,
                context.getJavaModelGeneratorConfiguration().getTargetProject(),
                UTF_8.name(),
                context.getJavaFormatter());
    }

    @Override
    public String getName() {
        return this.name;
    }
}
