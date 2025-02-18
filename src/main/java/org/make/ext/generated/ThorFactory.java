package org.make.ext.generated;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.time.Instant;
import java.util.Properties;

/**
 * MakeFactory
 */
public abstract class ThorFactory {

    public static String GENERATED = "@Generated(value = {\"ext-thor\"} , date = \"" + DateFormatUtils.format(Date.from(Instant.now()), "yyyy-MM-dd HH:mm:SS") + "\")";

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    public abstract GeneratedJavaFile make();

    public abstract String getName();

    public enum ThorAttribute {

        TARGET_PACKAGE("targetPackage") {
            @Override
            public String getProperty(Properties props) {
                return props.getProperty(toString());
            }
        },
        ;
        private final String attribute;

        ThorAttribute(String attribute) {
            this.attribute = attribute;
        }

        @Override
        public String toString() {
            return attribute;
        }

        public abstract String getProperty(Properties props);
    }

}
