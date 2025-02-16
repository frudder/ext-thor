package org.make.ext.plugins;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import jakarta.annotation.Generated;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.make.ext.DefaultJavaField;
import org.make.ext.generated.EntityGenerated;
import org.make.ext.generated.MakeFactory;
import org.make.ext.generated.MapperGenerated;
import org.make.ext.generated.ValueGenerated;
import org.mybatis.generator.api.GeneratedFile;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.CompilationUnitVisitor;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.java.TopLevelEnumeration;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Lists.newArrayListWithCapacity;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toUnmodifiableList;
import static org.make.ext.generated.MakeFactory.GENERATED;

/**
 *
 */
public final class ExtThorPlugin extends PluginAdapter {

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public List<GeneratedFile> contextGenerateAdditionalFiles() {
        List<MakeFactory> iterable = newArrayList(
                EntityGenerated.create(context),
                MapperGenerated.create(context));
        return iterable.stream().map(MakeFactory::make).collect(toList());
    }

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass compilationUnit, IntrospectedTable introspectedTable) {
        compilationUnit.accept(new CompilationUnitVisitor<TopLevelClass>() {
            @Override
            public TopLevelClass visit(TopLevelClass compilationUnit) {
                FullyQualifiedJavaType base = new FullyQualifiedJavaType(context.getJavaModelGeneratorConfiguration().getTargetPackage() + "." + "AbstractEntity");
                if (!introspectedTable.hasPrimaryKeyColumns()) {
                    throw new IllegalArgumentException();
                }
                List<IntrospectedColumn> columns = introspectedTable.getPrimaryKeyColumns();
                base.addTypeArgument(new FullyQualifiedJavaType(columns.get(0).getFullyQualifiedJavaType().getShortName()));
                compilationUnit.setSuperClass(base);
                compilationUnit.addAnnotation("@Data");
                compilationUnit.addAnnotation("@EqualsAndHashCode(callSuper = true)");
                compilationUnit.addAnnotation(GENERATED);
                compilationUnit.addImportedTypes(Sets.newHashSet(
                        new FullyQualifiedJavaType(Serial.class.getName()),
                        new FullyQualifiedJavaType(Generated.class.getName()),
                        new FullyQualifiedJavaType(Data.class.getName()),
                        new FullyQualifiedJavaType(EqualsAndHashCode.class.getName())
                ));
                DefaultJavaField.SERIAL_VERSION_UID.apply(compilationUnit);
                compilationUnit.addSuperInterface(new FullyQualifiedJavaType(Serializable.class.getName()));
                compilationUnit.getMethods().clear();

                Method method = new Method("empty");
                method.setVisibility(JavaVisibility.PUBLIC);
                method.setStatic(true);
                method.setReturnType(compilationUnit.getType());
                method.addBodyLine("return new " + compilationUnit.getType().getShortName() + "();");
                compilationUnit.addMethod(method);
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
        compilationUnit.accept(new CompilationUnitVisitor<>() {
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
                String name = context.getJavaClientGeneratorConfiguration().getTargetPackage() + "." + "MapperAdapter";
                FullyQualifiedJavaType mapper = new FullyQualifiedJavaType(name);
                mapper.addTypeArgument(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()));
                compilationUnit.getSuperInterfaceTypes().clear();
                compilationUnit.addSuperInterface(mapper);
                return compilationUnit;
            }
        });
        return super.clientGenerated(compilationUnit, introspectedTable);
    }

    @Override
    public List<GeneratedFile> contextGenerateAdditionalFiles(IntrospectedTable introspectedTable) {
        List<MakeFactory> factories = Lists.newArrayList(
                ValueGenerated.create(context, introspectedTable)
        );
        return factories.stream().map(MakeFactory::make).collect(toUnmodifiableList());
    }

}
