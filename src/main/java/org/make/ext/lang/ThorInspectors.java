package org.make.ext.lang;

import com.google.common.base.MoreObjects;
import com.google.common.primitives.Ints;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;

import java.sql.Connection;
import java.util.Objects;
import java.util.Set;

import static com.google.common.collect.Sets.newLinkedHashSetWithExpectedSize;


@Intercepts(value = {
        @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class}),
})
public abstract class ThorInspectors implements Interceptor, Comparable<ThorInspectors> {

    public abstract void inspect(Invocation invocation) throws Throwable;

    public abstract boolean supports(Invocation invocation);

    private int priority;

    private String name;

    private final Set<? extends ThorInspectors> inspectors = newLinkedHashSetWithExpectedSize(16);

    protected ThorInspectors(int priority, String name) {
        this.priority = priority;
        this.name = name;
    }

    protected ThorInspectors() {
        this(Integer.MAX_VALUE, "default");
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

//        inspectors.stream().filter(it -> it.supports(invocation))
//                .sorted(Comparator.comparingInt(it -> it.priority))
//                .forEachOrdered();

        return invocation.proceed();
    }

    @Override
    public int compareTo(ThorInspectors o) {
        return Ints.compare(priority, o.priority);
    }

    public boolean remove(ThorInspectors inspector) {
        return this.inspectors.remove(inspector);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", this.name).add("priority", this.priority).toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, priority);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ThorInspectors that = (ThorInspectors) o;
        return priority == that.priority && Objects.equals(name, that.name) && Objects.equals(inspectors, that.inspectors);
    }
}
