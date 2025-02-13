package org.make.ext.plugins;

import org.make.ext.core.EntityGenerated;
import org.make.ext.core.MakeGenerated;
import org.mybatis.generator.api.FullyQualifiedTable;
import org.mybatis.generator.api.GeneratedFile;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;

import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.collect.Lists.newArrayList;

public final class ExtThorPlugin extends PluginAdapter {

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public List<GeneratedFile> contextGenerateAdditionalFiles() {
        List<MakeGenerated> iterable = newArrayList(EntityGenerated.create(context));
        return iterable.stream().map(MakeGenerated::makeGeneratedFile).collect(Collectors.toList());
    }


    @Override
    public List<GeneratedFile> contextGenerateAdditionalFiles(IntrospectedTable introspectedTable) {
        final List<GeneratedFile> item = newArrayList();
        final FullyQualifiedTable v = introspectedTable.getFullyQualifiedTable();
        v.getDomainObjectName();
        FullyQualifiedJavaType type = new FullyQualifiedJavaType("org.ext.example.service." + v.getDomainObjectName() + "Service");
        Interface serviceInterface = new Interface(type);
        serviceInterface.setVisibility(JavaVisibility.PUBLIC);

        if (!introspectedTable.hasPrimaryKeyColumns())
            throw new IllegalArgumentException();

        serviceInterface.addImportedType(new FullyQualifiedJavaType(context.getJavaModelGeneratorConfiguration().getTargetPackage() + "." + v.getDomainObjectName()));

        Method findOne = new Method("findOne");
        findOne.setVisibility(JavaVisibility.PUBLIC);
        findOne.setReturnType(new FullyQualifiedJavaType(v.getDomainObjectName()));
        findOne.setAbstract(true);
        IntrospectedColumn primaryKey = introspectedTable.getPrimaryKeyColumns().get(0);
        findOne.addParameter(new Parameter(primaryKey.getFullyQualifiedJavaType(), primaryKey.getJavaProperty()));


//        findOne.addParameter(new Parameter(type, introspectedTable));
        serviceInterface.addMethod(findOne);
        GeneratedJavaFile gjf = new GeneratedJavaFile(serviceInterface, "src/main/java", context.getJavaFormatter());
        item.add(gjf);
        return item;
    }

}
