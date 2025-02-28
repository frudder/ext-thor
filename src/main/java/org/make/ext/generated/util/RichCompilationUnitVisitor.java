package org.make.ext.generated.util;

import org.mybatis.generator.api.dom.java.CompilationUnitVisitor;
import org.mybatis.generator.config.Context;

import java.util.function.Consumer;

public abstract class RichCompilationUnitVisitor<T> implements CompilationUnitVisitor<T>, Consumer<T> {

    protected final Context context;

    public RichCompilationUnitVisitor(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }
}
