package org.make.ext.internal;

import com.google.common.collect.Sets;
import org.make.ext.generated.ThorFactory;
import org.make.ext.generated.lang.DomainGenerated;
import org.make.ext.generated.lang.EntityGenerated;
import org.make.ext.generated.lang.MapperGenerated;
import org.make.ext.generated.lang.RouteGenerated;
import org.make.ext.generated.lang.TraitGenerated;
import org.make.ext.generated.lang.util.ApplicativeGenerated;
import org.make.ext.generated.lang.util.DefaultErrorHandlerGenerated;
import org.make.ext.generated.lang.util.ErrorRedirectGenerated;
import org.make.ext.generated.lang.util.ThorWrapExceptionGenerated;
import org.make.ext.generated.lang.util.ValueObjectWrapGenerated;
import org.mybatis.generator.api.GeneratedFile;
import org.mybatis.generator.config.Context;

import java.util.Collection;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

public class ThorProviderComposite extends ThorProviderFactory {

    private final Set<ThorFactory> factories = Sets.newLinkedHashSetWithExpectedSize(16);

    public ThorProviderComposite() {

    }

    @Override
    public void hook() {
        Properties props = getProperties();
        Context context = getContext();
        this.factories.addAll(Sets.newHashSet(
                EntityGenerated.create(props, context),
                RouteGenerated.create(props, context),
                MapperGenerated.create(props, context),
                TraitGenerated.create(props, context),
                DomainGenerated.create(props, context),
                ApplicativeGenerated.create(props, context),
                ValueObjectWrapGenerated.create(props, context),
                ThorWrapExceptionGenerated.create(props, context),
                ErrorRedirectGenerated.create(props, context),
                DefaultErrorHandlerGenerated.create(props, context)));
    }

    @Override
    public Set<? extends GeneratedFile> make() {
        return factories.stream().map(ThorFactory::make).collect(Collectors.toUnmodifiableSet());
    }

    public void add(ThorFactory factory) {
        factories.add(factory);
    }

    public void addAll(Collection<ThorFactory> factories) {
        this.factories.addAll(factories);
    }

    public void remove(ThorFactory factory) {
        this.factories.remove(factory);
    }
}
