package org.make.ext.generated;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.time.Instant;

public abstract class MakeGenerated {

    public static String GENERATED = "@Generated(value = {\" ext-thor \"} , date = \" " + DateFormatUtils.format(Date.from(Instant.now()), "yyyy-MM-dd HH:mm:SS") + " \")";

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    public abstract GeneratedJavaFile makeGeneratedFile();

    public abstract String getName();
}
