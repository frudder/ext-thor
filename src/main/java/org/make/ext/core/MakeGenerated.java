package org.make.ext.core;

import org.mybatis.generator.api.GeneratedJavaFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class MakeGenerated {

    protected Logger log = LoggerFactory.getLogger(this.getClass());

    public abstract GeneratedJavaFile makeGeneratedFile();

    public abstract String getName();
}
