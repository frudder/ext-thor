package org.make.ext.generated.util;

import jakarta.annotation.Nullable;
import org.mybatis.generator.api.dom.java.Field;
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
import static com.google.common.collect.Lists.newArrayList;

@Deprecated
/* package */ class GeneratedJavaField implements Consumer<Field>, Iterable<Field>, GeneratedCompilationUnit {

    private static final Logger logger = LoggerFactory.getLogger(GeneratedJavaField.class);
    
    private final TopLevelClass compilationUnit;

    private final List<Field> iterable = newArrayList();

    GeneratedJavaField(final TopLevelClass compilationUnit) {
        this.compilationUnit = checkNotNull(compilationUnit);
    }
    
    static GeneratedJavaField create(final TopLevelClass compilationUnit) {
        return new GeneratedJavaField(compilationUnit);
    }

    public GeneratedJavaField addField(final Field field) {
        iterable.add(checkNotNull(field));
        return this;
    }

    public GeneratedJavaField addField(final Collection<Field> fields) {
        this.iterable.addAll(checkNotNull(fields));
        return this;
    }

    @Override
    public void accept(@Nullable Field field) {
        if (Objects.nonNull(field)) {
            iterable.add(field);
        }
        this.compilationUnit.getFields().addAll(iterable);
    }

    @Override
    public void make() throws Exception {
        accept(null);
    }

    @Override
    @NotNull
    public Iterator<Field> iterator() {
        return iterable.iterator();
    }
}