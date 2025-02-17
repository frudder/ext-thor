package org.make.ext.generated;

import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.config.Context;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.mybatis.generator.api.dom.java.JavaVisibility.PUBLIC;

public class ThorInterface extends ThorJavaFactory {

    private final String prefix = "I";

    private final Interface compilationUnit;

    private final String name;

    private final Context context;

    private final IntrospectedTable introspectedTable;

    public static ThorInterface create(final Context context, final IntrospectedTable introspectedTable) {
        return new ThorInterface(context, introspectedTable);
    }

    public ThorInterface(Context context, IntrospectedTable introspectedTable) {
        this.context = context;
        this.introspectedTable = introspectedTable;
        FullyQualifiedJavaType index = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
        this.name = prefix + index.getShortName();
        this.compilationUnit = new Interface(index.getPackageName() + "." + this.name);
        this.compilationUnit.setVisibility(PUBLIC);
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
