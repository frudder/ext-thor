package org.make.ext;

import com.google.common.collect.Sets;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.io.Serial;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Sets.newHashSet;
import static org.mybatis.generator.api.dom.java.FullyQualifiedJavaType.getDateInstance;
import static org.mybatis.generator.api.dom.java.JavaVisibility.PRIVATE;
import static org.mybatis.generator.api.dom.java.JavaVisibility.PUBLIC;
import static org.mybatis.generator.internal.util.JavaBeansUtil.getGetterMethodName;
import static org.mybatis.generator.internal.util.JavaBeansUtil.getSetterMethodName;

public enum DefaultJavaField {

    SERIAL_VERSION_UID(new Field("serialVersionUID", new FullyQualifiedJavaType("long"))) {
        @Override
        public Field apply() {
            Field value = getValue();
            value.setFinal(true);
            value.setStatic(true);
            value.setVisibility(PRIVATE);
            value.setInitializationString(UUID.randomUUID().getMostSignificantBits() + "L");
            return value;
        }

        @Override
        public void apply(TopLevelClass compilationUnit) {
            Field f = apply();
            Set<String> iterable = newHashSet(f.getAnnotations());
            String annotation = "@" + Serial.class.getName();
            if (!iterable.contains(annotation)) {
                f.addAnnotation(annotation);
            }
            compilationUnit.addField(f);
        }
    },

    PRIMARY_KEY(new Field("id", new FullyQualifiedJavaType("T"))) {
        @Override
        public Field apply() {
            Field value = getValue();
            value.setVisibility(PRIVATE);
            return value;
        }

        @Override
        public void apply(TopLevelClass compilationUnit) {
            compilationUnit.addField(apply());
        }
    },

    CREATED_AT(new Field("createdAt", getDateInstance())) {
        @Override
        Field apply() {
            Field value = getValue();
            value.setVisibility(PRIVATE);
            return value;
        }

        @Override
        public void apply(TopLevelClass compilationUnit) {
            compilationUnit.addField(apply());
        }

    },

    UPDATED_AT(new Field("updatedAt", getDateInstance())) {
        @Override
        Field apply() {
            Field value = getValue();
            value.setVisibility(PRIVATE);
            return value;
        }

        @Override
        public void apply(TopLevelClass compilationUnit) {
            compilationUnit.addField(apply());
        }
    },

    CREATED_BY(new Field("createdBy", new FullyQualifiedJavaType("T"))) {
        @Override
        Field apply() {
            Field value = getValue();
            value.setVisibility(PRIVATE);
            return value;
        }

        @Override
        public void apply(TopLevelClass compilationUnit) {
            compilationUnit.addField(apply());
        }
    },

    LAST_MODIFIED_BY(new Field("lastModifiedBy", new FullyQualifiedJavaType("T"))) {
        @Override
        Field apply() {
            Field value = getValue();
            value.setVisibility(PRIVATE);
            return value;
        }

        @Override
        public void apply(TopLevelClass compilationUnit) {
            compilationUnit.addField(apply());
        }
    };

    private final Field field;

    DefaultJavaField(final Field field) {
        this.field = field;
    }

    abstract Field apply();

    public abstract void apply(final TopLevelClass compilationUnit);

    public Field getValue() {
        return field;
    }

    @Override
    public String toString() {
        return field.getName();
    }

    public void bean(final TopLevelClass compilationUnit) {
        checkNotNull(compilationUnit);
        compilationUnit.addMethod(getJavaBeansSetter());
        compilationUnit.addMethod(getJavaBeansGetter());
    }

    public ImmutablePair<Method, Method> getJavaBeanMethod() {
        return ImmutablePair.of(getJavaBeansSetter(), getJavaBeansGetter());
    }

    Method getJavaBeansGetter() {
        Method method = new Method(getGetterMethodName(getValue().getName(), getValue().getType()));
        method.setVisibility(PUBLIC);
        method.setReturnType(getValue().getType());
        method.addBodyLine("return " + getValue().getName() + ";");
        return method;
    }

    Method getJavaBeansSetter() {
        Method method = new Method(getSetterMethodName(getValue().getName()));
        method.setVisibility(PUBLIC);
        method.setReturnType(new FullyQualifiedJavaType("void"));
        method.addParameter(new Parameter(getValue().getType(), getValue().getName()));
        method.addBodyLine("this." + getValue().getName() + " = " + "checkNotNull(" + getValue().getName() + ")" + ";");
        return method;
    }

    public static List<String> getName() {
        return Arrays.stream(values()).map(DefaultJavaField::toString).toList();
    }

}
