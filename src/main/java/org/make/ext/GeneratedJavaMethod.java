package org.make.ext;

import com.google.common.collect.Lists;
import jakarta.annotation.Nullable;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

import static com.google.common.base.Preconditions.checkNotNull;

/* package */ class GeneratedJavaMethod implements Consumer<Method>, Iterable<Method> {

    private static final Logger logger = LoggerFactory.getLogger(GeneratedJavaMethod.class);

    private final TopLevelClass compilationUnit;

    private final List<Method> iterable = Lists.newArrayList();

    GeneratedJavaMethod(final TopLevelClass compilationUnit) {
        this.compilationUnit = checkNotNull(compilationUnit);
    }

    static GeneratedJavaMethod create(final TopLevelClass compilationUnit) {
        return new GeneratedJavaMethod(compilationUnit);
    }

    public GeneratedJavaMethod addMethod(final Method field) {
        iterable.add(checkNotNull(field));
        return this;
    }

    public GeneratedJavaMethod addMethod(final Collection<Method> fields) {
        this.iterable.addAll(checkNotNull(fields));
        return this;
    }

    @Override
    public void accept(@Nullable Method field) {
        if (Objects.nonNull(field)) {
            iterable.add(field);
        }
        this.iterable.forEach(this.compilationUnit::addMethod);
    }

    @Override
    @NotNull
    public Iterator<Method> iterator() {
        return iterable.iterator();
    }
}