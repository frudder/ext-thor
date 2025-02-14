package org.make.ext;


import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.List;

import static java.nio.channels.Channels.newReader;

public class Main {

    private final static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        final List<String> warnings = Lists.newArrayList();
        try (FileChannel ch = FileChannel.open(Paths.get("config.xml"))) {
            ConfigurationParser parser = new ConfigurationParser(warnings);
            Configuration cfg = parser.parseConfiguration(newReader(ch, StandardCharsets.UTF_8));
            DefaultShellCallback callback = new DefaultShellCallback(true);
            MyBatisGenerator g = new MyBatisGenerator(cfg, callback, warnings);
            g.generate(null, ImmutableSet.of("thor"));
        } catch (Exception err) {
            logger.error(err.getMessage(), err);
        }
    }
}