package org.make.ext.generated.base;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.make.ext.generated.ThorJavaFactory;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.config.Context;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Strings.isNullOrEmpty;
import static com.google.common.collect.Sets.newHashSet;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.mybatis.generator.api.dom.java.JavaVisibility.PUBLIC;

public final class RouteGenerated extends ThorJavaFactory {

    private final Interface compilationUnit;

    private final Context context;

    private final String name;

    public static RouteGenerated create(Context context, String name) {
        return new RouteGenerated(context, name);
    }

    public static RouteGenerated create(Context context) {
        return create(context, null);
    }

    private RouteGenerated(final Context context, final String name) {
        this.context = checkNotNull(context);
        this.name = isNullOrEmpty(name) ? "Router" : name;
        FullyQualifiedJavaType token = new FullyQualifiedJavaType(context.getJavaModelGeneratorConfiguration().getTargetPackage() + "." + this.name);
        token.addTypeArgument(new FullyQualifiedJavaType("T"));
        this.compilationUnit = new Interface(token);
        this.compilationUnit.setVisibility(PUBLIC);
        this.compilationUnit.addAnnotation(GENERATED);
        this.compilationUnit.addAnnotation("@RequestMapping(value = \" default \",  consumes = { APPLICATION_JSON_VALUE, TEXT_HTML_VALUE }, produces = { APPLICATION_JSON_VALUE })");
        this.compilationUnit.addAnnotation("@Validated");

        this.compilationUnit.addImportedTypes(newHashSet(
                new FullyQualifiedJavaType("jakarta.annotation.Generated"),
                new FullyQualifiedJavaType("jakarta.validation.constraints.NotEmpty"),
                new FullyQualifiedJavaType("org.springframework.validation.annotation.Validated"),
                new FullyQualifiedJavaType("org.springframework.web.bind.annotation.DeleteMapping"),
                new FullyQualifiedJavaType("org.springframework.web.bind.annotation.GetMapping"),
                new FullyQualifiedJavaType("org.springframework.web.bind.annotation.PathVariable"),
                new FullyQualifiedJavaType("org.springframework.web.bind.annotation.PutMapping"),
                new FullyQualifiedJavaType("org.springframework.web.bind.annotation.RequestBody"),
                new FullyQualifiedJavaType("org.springframework.web.bind.annotation.RequestMapping"),
                new FullyQualifiedJavaType("org.springframework.web.bind.annotation.RequestParam"),
                new FullyQualifiedJavaType("java.io.Serializable"),
                new FullyQualifiedJavaType("java.util.List")
        ));
        this.compilationUnit.addStaticImports(newHashSet(
                "org.springframework.http.MediaType.APPLICATION_JSON_VALUE",
                "org.springframework.http.MediaType.TEXT_HTML_VALUE"
        ));

        Method find = new Method("find");
        find.setAbstract(true);
        find.addAnnotation("@GetMapping(value = \"/{id}\")");
        find.setReturnType(new FullyQualifiedJavaType("T"));
        FullyQualifiedJavaType item = new FullyQualifiedJavaType("List");
        item.addTypeArgument(new FullyQualifiedJavaType("Serializable"));
        Parameter parameter = new Parameter(item, "item");
        parameter.addAnnotation("@PathVariable(value = \"id\")");
        parameter.addAnnotation("@NotEmpty");
        find.getParameters().addAll(ImmutableList.of(parameter));
        this.compilationUnit.addMethod(find);


        Method seek = new Method("find");
        seek.setAbstract(true);
        seek.addAnnotation("@GetMapping");
        FullyQualifiedJavaType returnType = new FullyQualifiedJavaType("List");
        returnType.addTypeArgument(new FullyQualifiedJavaType("T"));
        seek.setReturnType(returnType);
        List<Parameter> parameters = Lists.newArrayListWithCapacity(16);
        parameter = new Parameter(new FullyQualifiedJavaType("Integer"), "index");
        parameter.addAnnotation("@RequestParam(value = \"index\", defaultValue = \"1\")");
        parameters.add(parameter);
        parameter = new Parameter(new FullyQualifiedJavaType("Integer"), "size");
        parameter.addAnnotation("@RequestParam(value = \"size\", defaultValue = \"10\")");
        parameters.add(parameter);
        parameter = new Parameter(new FullyQualifiedJavaType("T"), "value");
        parameter.addAnnotation("@RequestBody");
        parameters.add(parameter);
        seek.getParameters().addAll(parameters);
        this.compilationUnit.addMethod(seek);

        Method remove = new Method("remove");
        remove.setAbstract(true);
        remove.setReturnType(new FullyQualifiedJavaType("boolean"));
        remove.addAnnotation("@DeleteMapping(value = \"/{id}\")");
        item = new FullyQualifiedJavaType("List");
        item.addTypeArgument(new FullyQualifiedJavaType("Serializable"));
        parameter = new Parameter(item, "item");
        parameter.addAnnotation("@PathVariable(value = \"id\")");
        parameter.addAnnotation("@NotEmpty");
        remove.getParameters().addAll(ImmutableList.of(parameter));
        this.compilationUnit.addMethod(remove);

        Method create = new Method("create");
        create.setAbstract(true);
        returnType = new FullyQualifiedJavaType("T");
        create.setReturnType(returnType);
        create.addAnnotation("@PutMapping");
        FullyQualifiedJavaType body = new FullyQualifiedJavaType("T");
        parameter = new Parameter(body, "body");
        parameter.addAnnotation("@RequestBody");
        parameter.addAnnotation("@Validated");
        create.getParameters().addAll(ImmutableList.of(parameter));
        this.compilationUnit.addMethod(create);

        Method bulk = new Method("create");
        bulk.setAbstract(true);
        returnType = new FullyQualifiedJavaType("List");
        returnType.addTypeArgument(new FullyQualifiedJavaType("T"));
        bulk.setReturnType(returnType);
        bulk.addAnnotation("@PutMapping(value = \"/bulk\")");
        body = new FullyQualifiedJavaType("List");
        body.addTypeArgument(new FullyQualifiedJavaType("T"));
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
