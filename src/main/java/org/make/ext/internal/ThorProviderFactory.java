package org.make.ext.internal;

import org.make.ext.internal.spi.ThorProvider;
import org.mybatis.generator.config.Context;

import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class ThorProviderFactory implements ThorProvider {

    private Properties props;

    private Context context;

    private final AtomicBoolean once = new AtomicBoolean(false);

    @Override
    public void setProperties(Properties properties) {
        this.props = properties;
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public Properties getProperties() {
        return props;
    }

    @Override
    public void init(Properties properties, Context context) {
        if (once.compareAndSet(false, true)) {
            setProperties(properties);
            setContext(context);
        }
        hook();
    }

    public abstract void hook();
}
