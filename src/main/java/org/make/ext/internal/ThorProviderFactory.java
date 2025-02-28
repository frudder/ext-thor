package org.make.ext.internal;

import org.make.ext.internal.spi.ThorProvider;
import org.mybatis.generator.config.Context;

import java.util.Properties;

public abstract class ThorProviderFactory implements ThorProvider {

    private Properties props;

    private Context context;

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
}
