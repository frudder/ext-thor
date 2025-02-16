package org.make.ext.plugin;

import org.make.ext.generated.EntityGenerated;
import org.make.ext.generated.MapperGenerated;
import org.make.ext.generated.ThorJavaFactory;
import org.make.ext.generated.ValueGenerated;
import org.make.ext.generated.util.RichJavaClientCompilationUnit;
import org.make.ext.generated.util.RichJavaModelCompilationUnit;
import org.mybatis.generator.api.GeneratedFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.stream.Collectors.toUnmodifiableList;

/**
 *
 */
public final class ThorPlugin extends PluginAdapter {

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    public ThorPlugin() {

    }

    @Override
    public List<GeneratedFile> contextGenerateAdditionalFiles() {
        List<ThorJavaFactory> iterable = newArrayList(
                EntityGenerated.create(context),
                MapperGenerated.create(context));
        return iterable.stream().map(ThorJavaFactory::make).collect(toUnmodifiableList());
    }

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass compilationUnit, IntrospectedTable introspectedTable) {
        compilationUnit.accept(RichJavaModelCompilationUnit.create(context, introspectedTable));
        return true;
    }

    @Override
    public boolean clientGenerated(Interface compilationUnit, IntrospectedTable introspectedTable) {
        compilationUnit.accept(RichJavaClientCompilationUnit.create(context, introspectedTable));
        return true;
    }

    @Override
    public List<GeneratedFile> contextGenerateAdditionalFiles(IntrospectedTable introspectedTable) {
        List<ThorJavaFactory> factories = newArrayList(
                ValueGenerated.create(context, introspectedTable)
        );
        return factories.stream().map(ThorJavaFactory::make).collect(toUnmodifiableList());
    }

}
