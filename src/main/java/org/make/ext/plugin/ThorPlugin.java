package org.make.ext.plugin;

import org.make.ext.generated.ThorDomain;
import org.make.ext.generated.ThorFactory;
import org.make.ext.generated.ThorHandler;
import org.make.ext.generated.ThorTrait;
import org.make.ext.generated.ThorValue;
import org.make.ext.generated.util.RichJavaClientCompilationUnit;
import org.make.ext.generated.util.RichJavaModelCompilationUnit;
import org.make.ext.internal.spi.ThorProvider;
import org.mybatis.generator.api.GeneratedFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.List;
import java.util.ServiceLoader;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Lists.newArrayListWithCapacity;
import static java.util.stream.Collectors.toUnmodifiableList;

public final class ThorPlugin extends PluginAdapter {

    private final ServiceLoader<ThorProvider> SPI;

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    public ThorPlugin() {
        SPI = ServiceLoader.load(ThorProvider.class);
    }

    /**
     * Java SPI
     *
     * @return List
     * @see java.util.ServiceLoader
     */
    @Override
    public List<GeneratedFile> contextGenerateAdditionalFiles() {
        List<GeneratedFile> generatedFiles = newArrayListWithCapacity(16 * 4);
        for (ThorProvider provider : SPI) {
            provider.init(properties, context);
            generatedFiles.addAll(provider.make());
        }
        return generatedFiles;
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
                ThorValue.create(properties, context, introspectedTable),
                ThorHandler.create(properties, context, introspectedTable),
                ThorTrait.create(properties, context, introspectedTable),
                ThorDomain.create(properties, context, introspectedTable)
        );
        return factories.stream().map(ThorFactory::make).collect(toUnmodifiableList());
    }

}
