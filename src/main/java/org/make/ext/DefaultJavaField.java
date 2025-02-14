package org.make.ext;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.io.Serializable;
import java.util.Objects;

import static org.mybatis.generator.api.dom.java.FullyQualifiedJavaType.getDateInstance;
import static org.mybatis.generator.api.dom.java.JavaVisibility.PRIVATE;
import static org.mybatis.generator.api.dom.java.JavaVisibility.PUBLIC;
import static org.mybatis.generator.internal.util.JavaBeansUtil.getSetterMethodName;

public enum DefaultJavaField {

    SERIAL_VERSION_UID(new Field("serialVersionUID", new FullyQualifiedJavaType("long"))) {
        @Override
        public Field apply() {
            Field value = getValue();
            value.setFinal(true);
            value.setStatic(true);
            value.setVisibility(PRIVATE);
            value.setInitializationString("1L");
            return value;
        }

        @Override
        public void apply(TopLevelClass compilationUnit) {
            compilationUnit.addField(apply());
        }
    },

    PRIMARY_KEY(new Field("id", new FullyQualifiedJavaType(Serializable.class.getName()))) {
        @Override
        public Field apply() {
            Field value = getValue();
            value.setVisibility(PRIVATE);
            return value;
        }

        @Override
        public void apply(TopLevelClass compilationUnit) {
            compilationUnit.addField(apply());
            Method method = getJavaBeansSetter();
            if (Objects.nonNull(method)) {
                compilationUnit.addMethod(method);
            }
            method = getJavaBeansGetter();
            if (Objects.nonNull(method)) {
                compilationUnit.addMethod(method);
            }
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
            Method method = getJavaBeansSetter();
            if (Objects.nonNull(method)) {
                compilationUnit.addMethod(method);
            }
            method = getJavaBeansGetter();
            if (Objects.nonNull(method)) {
                compilationUnit.addMethod(method);
            }
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
            Method method = getJavaBeansSetter();
            if (Objects.nonNull(method)) {
                compilationUnit.addMethod(method);
            }
            method = getJavaBeansGetter();
            if (Objects.nonNull(method)) {
                compilationUnit.addMethod(method);
            }
        }


    },

    CREATED_BY(new Field("createdBy", new FullyQualifiedJavaType(Serializable.class.getName()))) {
        @Override
        Field apply() {
            Field value = getValue();
            value.setVisibility(PRIVATE);
            return value;
        }

        @Override
        public void apply(TopLevelClass compilationUnit) {
            compilationUnit.addField(apply());
            Method method = getJavaBeansSetter();
            if (Objects.nonNull(method)) {
                compilationUnit.addMethod(method);
            }
            method = getJavaBeansGetter();
            if (Objects.nonNull(method)) {
                compilationUnit.addMethod(method);
            }
        }


    },

    LAST_MODIFIED_BY(new Field("lastModifiedBy", new FullyQualifiedJavaType(Serializable.class.getName()))) {
        @Override
        Field apply() {
            Field value = getValue();
            value.setVisibility(PRIVATE);
            return value;
        }

        @Override
        public void apply(TopLevelClass compilationUnit) {
            compilationUnit.addField(apply());
            Method method = getJavaBeansSetter();
            if (Objects.nonNull(method)) {
                compilationUnit.addMethod(method);
            }
            method = getJavaBeansGetter();
            if (Objects.nonNull(method)) {
                compilationUnit.addMethod(method);
            }
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

    public ImmutablePair<Method, Method> getJavaBeanMethod() {
        return ImmutablePair.of(getJavaBeansSetter(), getJavaBeansGetter());
    }

    Method getJavaBeansGetter() {
        Method method = new Method(getSetterMethodName(getValue().getName()));
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
}
