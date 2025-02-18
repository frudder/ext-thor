package org.make.ext.plugin;

import org.make.ext.generated.ThorController;
import org.make.ext.generated.ThorDomain;
import org.make.ext.generated.ThorFactory;
import org.make.ext.generated.ThorInterface;
import org.make.ext.generated.ValueGenerated;
import org.make.ext.generated.base.EntityGenerated;
import org.make.ext.generated.base.MapperGenerated;
import org.make.ext.generated.base.RouteGenerated;
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
        List<ThorFactory> iterable = newArrayList(
                EntityGenerated.create(properties, context),
                RouteGenerated.create(properties, context),
                MapperGenerated.create(properties, context));
        return iterable.stream().map(ThorFactory::make).collect(toUnmodifiableList());
    }

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass compilationUnit, IntrospectedTable introspectedTable) {
        compilationUnit.accept(RichJavaModelCompilationUnit.create(properties, context, introspectedTable));
        return true;
    }

    @Override
    public boolean clientGenerated(Interface compilationUnit, IntrospectedTable introspectedTable) {
        compilationUnit.accept(RichJavaClientCompilationUnit.create(properties, context, introspectedTable));
        return true;
    }

    @Override
    public List<GeneratedFile> contextGenerateAdditionalFiles(IntrospectedTable introspectedTable) {
        List<ThorFactory> factories = newArrayList(
                ValueGenerated.create(properties, context, introspectedTable),
                ThorController.create(properties, context, introspectedTable),
                ThorInterface.create(properties, context, introspectedTable),
                ThorDomain.create(properties, context, introspectedTable)
        );
        return factories.stream().map(ThorFactory::make).collect(toUnmodifiableList());
    }

}
