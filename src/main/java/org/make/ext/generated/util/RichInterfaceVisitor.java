package org.make.ext.generated.util;

import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.java.TopLevelEnumeration;
import org.mybatis.generator.config.Context;

public final class RichInterfaceVisitor extends RichCompilationUnitVisitor<Interface> {

    private final Context context;

    public RichInterfaceVisitor(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public Interface visit(TopLevelClass compilationUnit) {
        return null;
    }

    @Override
    public Interface visit(TopLevelEnumeration compilationUnit) {
        return null;
    }

    @Override
    public Interface visit(Interface compilationUnit) {
        return null;
    }

    @Override
    public void accept(Interface compilationUnit) {
        compilationUnit.accept(this);
    }

    @Override
    public Context getContext() {
        return context;
    }
}
