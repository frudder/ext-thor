package org.make.ext.internal.spi;

import org.mybatis.generator.api.GeneratedFile;
import org.mybatis.generator.config.Context;

import java.util.Properties;
import java.util.Set;

public interface ThorProvider {

    void setContext(Context context);

    void setProperties(Properties properties);

    Set<? extends GeneratedFile> make();
}
