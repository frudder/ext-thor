package org.make.ext.generated;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.checkerframework.checker.index.qual.PolyUpperBound;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.time.Instant;
import java.util.Properties;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * ThorFactory
 */
public abstract class ThorFactory {

    public static String GENERATED = "@Generated(value = {\"ext-thor\"} , date = \"" + DateFormatUtils.format(Date.from(Instant.now()), "yyyy-MM-dd HH:mm:SS") + "\")";

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    public abstract GeneratedJavaFile make();

    public abstract String getName();

    public final static class ThorAttribute {

        private ThorAttribute() {
        }

        public static final String THOR_LANG = "lang";

        public static final String THOR_VIEWS = "views";

        public static final String THOR_SERVICE = "services";

        public static final String THOR_SERVICE_PREFIX = "I";

        public static final String THOR_CONTROLLER = "controller";

        public static final String THOR_TARGET_PACKAGE = "targetPackage";

        public static final String THOR_DEFAULT_CONTROLLER_NAME = "ThorRouter";

        public static final String THOR_DEFAULT_ENTITY_NAME = "ThorEntity";

        public static final String THOR_DEFAULT_SERVICE_NAME = "ThorTrait";

        public static final String THOR_DEFAULT_DOMAIN_NAME = "ThorDomain";

        public static final String THOR_DEFAULT_MAPPER_NAME = "MapperAdapter";

        public static final String THOR_DEFAULT_ENTITY_SUFFIX = "Entity";

        public static final String THOR_DEFAULT_POJO_SUFFIX = "Value";

        public static final String THOR_DEFAULT_CONTROLLER_SUFFIX = "Controller";

        public static final String THOR_DEFAULT_INTERNAL_NAME = "internal";

        public static final String THOR_DEFAULT_SERVICE_SUFFIX = "Domain";

        public static String getProperty(Properties properties, String name) {
            return checkNotNull(properties).getProperty(name);
        }
    }

}
