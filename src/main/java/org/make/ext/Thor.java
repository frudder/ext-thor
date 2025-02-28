package org.make.ext;


import com.google.common.collect.ImmutableSet;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.api.ProgressCallback;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static java.nio.channels.Channels.newReader;
import static java.nio.channels.FileChannel.open;
import static java.nio.charset.StandardCharsets.UTF_8;

public class Thor {

    private final static Logger logger = LoggerFactory.getLogger(Thor.class);


    public static void main(String[] args) throws Exception {
        final List<String> warnings = newArrayList();
        try (FileChannel ch = open(Paths.get("config.xml"))) {
            ConfigurationParser parser = new ConfigurationParser(warnings);
            Configuration cfg = parser.parseConfiguration(newReader(ch, UTF_8));
            DefaultShellCallback callback = new DefaultShellCallback(true);
            MyBatisGenerator thor = new MyBatisGenerator(cfg, callback, warnings);
            thor.generate(new ProgressCallback() {
                @Override
                public void introspectionStarted(int totalTasks) {
                    ProgressCallback.super.introspectionStarted(totalTasks);
                }

                @Override
                public void generationStarted(int totalTasks) {
                    ProgressCallback.super.generationStarted(totalTasks);
                }

                @Override
                public void saveStarted(int totalTasks) {
                    ProgressCallback.super.saveStarted(totalTasks);
                }

                @Override
                public void startTask(String taskName) {
                    ProgressCallback.super.startTask(taskName);
                }

                @Override
                public void done() {
                    ProgressCallback.super.done();
                }

                @Override
                public void checkCancel() throws InterruptedException {
                    ProgressCallback.super.checkCancel();
                }
            }, ImmutableSet.of("thor"));
        } catch (Exception err) {
            logger.error(err.getMessage(), err);
        }
    }
}