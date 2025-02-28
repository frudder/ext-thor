package org.make.ext.generated.util;

import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.java.TopLevelEnumeration;
import org.mybatis.generator.config.Context;

public abstract class RichTopLevelClassVisitor extends RichCompilationUnitVisitor<TopLevelClass> {

    private final Context context;

    public RichTopLevelClassVisitor(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public TopLevelClass visit(TopLevelClass compilationUnit) {
        return null;
    }

    @Override
    public TopLevelClass visit(TopLevelEnumeration compilationUnit) {
        return null;
    }

    @Override
    public TopLevelClass visit(Interface compilationUnit) {
        return null;
    }

    @Override
    public void accept(TopLevelClass compilationUnit) {
        compilationUnit.accept(this);
    }

    @Override
    public Context getContext() {
        return context;
    }
}
