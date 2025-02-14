package org.make.ext.plugins;

import org.make.ext.generated.EntityGenerated;
import org.make.ext.generated.MakeGenerated;
import org.make.ext.generated.MapperGenerated;
import org.mybatis.generator.api.GeneratedFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.CompilationUnitVisitor;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.java.TopLevelEnumeration;

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
        List<MakeGenerated> iterable = newArrayList(
                EntityGenerated.create(context),
                MapperGenerated.create(context));
        return iterable.stream().map(MakeGenerated::makeGeneratedFile).collect(Collectors.toList());
    }

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass compilationUnit, IntrospectedTable introspectedTable) {
        compilationUnit = compilationUnit.accept(new CompilationUnitVisitor<TopLevelClass>() {
            @Override
            public TopLevelClass visit(TopLevelClass compilationUnit) {

                String name = context.getJavaModelGeneratorConfiguration().getTargetPackage() + "." + "AbstractEntity";
                compilationUnit.setSuperClass(new FullyQualifiedJavaType(name));
                return compilationUnit;
            }

            @Override
            public TopLevelClass visit(TopLevelEnumeration compilationUnit) {
                return null;
            }

            @Override
            public TopLevelClass visit(Interface compilationUnit) {
                return null;
            }
        });
        return super.modelBaseRecordClassGenerated(compilationUnit, introspectedTable);
    }

    @Override
    public boolean clientGenerated(Interface compilationUnit, IntrospectedTable introspectedTable) {
        compilationUnit = compilationUnit.accept(new CompilationUnitVisitor<>() {
            @Override
            public Interface visit(TopLevelClass topLevelClass) {
                return null;
            }

            @Override
            public Interface visit(TopLevelEnumeration topLevelEnumeration) {
                return null;
            }

            @Override
            public Interface visit(Interface compilationUnit) {
                Interface copy = new Interface(compilationUnit.getType());
                String name = context.getJavaClientGeneratorConfiguration().getTargetPackage() + "." + "MapperAdapter";
                FullyQualifiedJavaType mapper = new FullyQualifiedJavaType(name);
                mapper.addTypeArgument(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()));
                copy.addSuperInterface(mapper);
                return copy;
            }
        });
        return super.clientGenerated(compilationUnit, introspectedTable);
    }

    @Override
    public List<GeneratedFile> contextGenerateAdditionalFiles(IntrospectedTable introspectedTable) {





        return super.contextGenerateAdditionalFiles(introspectedTable);
    }

}
