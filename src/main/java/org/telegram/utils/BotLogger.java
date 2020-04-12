package org.telegram.utils;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.*;
import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.core.appender.RollingFileAppender;
import org.apache.logging.log4j.core.appender.rolling.DefaultRolloverStrategy;
import org.apache.logging.log4j.core.appender.rolling.RolloverStrategy;
import org.apache.logging.log4j.core.appender.rolling.SizeBasedTriggeringPolicy;
import org.apache.logging.log4j.core.appender.rolling.TriggeringPolicy;
import org.apache.logging.log4j.core.async.AsyncLoggerConfig;
import org.apache.logging.log4j.core.config.AppenderRef;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.core.config.builder.api.AppenderComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.ComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilderFactory;
import org.apache.logging.log4j.core.config.builder.api.FilterComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.LayoutComponentBuilder;
import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;
import org.apache.logging.log4j.core.filter.ThresholdFilter;
import org.apache.logging.log4j.core.layout.PatternLayout;

/**
 *
 * @author Mehdi DHAKOUANI (c) 2018-2019
 */
public final class BotLogger {

    private final static BotLogger INSTANCE;

    private ConfigurationBuilder<BuiltConfiguration> configurationBuilder = null;
    private Logger _logger = null;
    private final static Level LEVEL = Level.ALL;
    private final static boolean TO_CONSOLE = true;
    private final static boolean TO_FILE = true;
    private final static String COMPRESSION_LEVEL = "0";
    private final static String MAX_FILE_LENGTH = "1048576";
    private final static String MAX_NUMBER_FILES = "10";

    static {
        System.setProperty("log4j2.contextSelector", "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector");
        System.setProperty("log4j2.skipJansi", "false");
        INSTANCE = new BotLogger();
    }
    
    private BotLogger() {
        this.configurationBuilder = ConfigurationBuilderFactory.newConfigurationBuilder();
        this.configurationBuilder.setStatusLevel(LEVEL);
        this.configurationBuilder.setConfigurationName("RollingBuilder");
        // create a layout builder
        LayoutComponentBuilder layoutBuilder = this.configurationBuilder.newLayout("PatternLayout").addAttribute("pattern", "%d{DEFAULT} %-5p %m{ansi} %throwable{full} %n").addAttribute("charset", "UTF-8");
        LayoutComponentBuilder strategyLayoutBuilder = this.configurationBuilder.newLayout("PatternLayout").addAttribute("pattern", "%d{DEFAULT} %m{ansi} %n").addAttribute("charset", "UTF-8");
        LayoutComponentBuilder klineLayoutBuilder = this.configurationBuilder.newLayout("PatternLayout").addAttribute("pattern", "%d{DEFAULT} %m{ansi} %n").addAttribute("charset", "UTF-8");

        // create a console appender
        AppenderComponentBuilder appenderBuilder = this.configurationBuilder
                .newAppender("StdoutAppender", "CONSOLE")
                .addAttribute("target", ConsoleAppender.Target.SYSTEM_OUT);
        appenderBuilder.add(layoutBuilder);
        this.configurationBuilder.add(appenderBuilder);

        // create a rolling file appender
        FilterComponentBuilder rootFilter = this.configurationBuilder.newFilter("ThresholdFilter", Filter.Result.ACCEPT, Filter.Result.DENY).addAttribute("Level", LEVEL);
        ComponentBuilder _triggeringPolicy = this.configurationBuilder
                .newComponent("Policies")
                .addComponent(this.configurationBuilder.newComponent("SizeBasedTriggeringPolicy").addAttribute("size", MAX_FILE_LENGTH));
        String filePattern = "log-%d{yyyyMMdd}.log.%i.gz";
        String fileName = "log-current.log";
        ComponentBuilder rollingOverStrategy = this.configurationBuilder.newComponent("DefaultRolloverStrategy")
            .addAttribute("fileIndex", "min")
            .addAttribute("compressionLevel", COMPRESSION_LEVEL)
            .addAttribute("min", 1)
            .addAttribute("max", MAX_NUMBER_FILES)
            .addAttribute("tempCompressedFilePattern", "log-current.log.tmp");
        appenderBuilder = this.configurationBuilder.newAppender("RollingFileAppender", "RollingFile")
            .addAttribute("fileName", fileName)
            .addAttribute("filePattern", filePattern)
            .add(layoutBuilder)
            .add(rootFilter)
            .addComponent(_triggeringPolicy)
            .addComponent(rollingOverStrategy);
        this.configurationBuilder.add(appenderBuilder);

        if (TO_FILE) { 
                this.configurationBuilder.add(
                    this.configurationBuilder.newAsyncLogger("General", LEVEL)
                            .add(this.configurationBuilder.newAppenderRef("StdoutAppender"))
                            .add(this.configurationBuilder.newAppenderRef("RollingFileAppender"))
                    .addAttribute("Additivity", false)
                );
        } else {
                this.configurationBuilder.add(
                        this.configurationBuilder.newAsyncLogger("General", LEVEL)
                                .add(this.configurationBuilder.newAppenderRef("StdoutAppender"))
                        .addAttribute("additivity", false)
                );
        }

        LoggerContext ctx = Configurator.initialize(this.configurationBuilder.build());
        ctx.updateLoggers();
        ctx.start();
        this._logger = LogManager.getLogger("General");
    }

    public static Logger getMainLogger() {
        return INSTANCE._logger;
    }
    
    public static void trace(String tag, String msg) {
        trace(tag, msg, null);
    }

    public static void trace(String tag, Throwable t) {
        trace(tag, "", t);
    }

    public static void trace(String tag, String msg, Throwable t) {
        getMainLogger().trace(tag + " " + msg, t);
    }

    public static void debug(String tag, String msg) {
        debug(tag, msg, null);
    }

    public static void debug(String tag, Throwable t) {
        debug(tag, "", t);
    }

    public static void debug(String tag, String msg, Throwable t) {
        getMainLogger().debug(tag + " " + msg, t);
    }

    public static void info(String tag, String msg) {
        info(tag, msg, null);
    }

    public static void info(String tag, Throwable t) {
        info(tag, "", t);
    }

    public static void info(String tag, String msg, Throwable t) {
        getMainLogger().info(tag + " " + msg, t);
    }

    public static void severe(String tag, Throwable t) {
        severe(tag, "", t);
    }

    public static void severe(String tag, String msg) {
        severe(tag, msg, null);
    }

    public static void severe(String tag, String msg, Throwable t) {
        getMainLogger().fatal(tag + " " + msg, t);
    }

    public static void error(String tag, Throwable t) {
        error(tag, "", t);
    }

    public static void error(String tag, String msg) {
        error(tag, msg, null);
    }

    public static void error(String tag, String msg, Throwable t) {
        getMainLogger().error(tag + " " + msg, t);
    }

    public static void warning(String tag, String msg) {
        warning(tag, msg, null);
    }

    public static void warning(String tag, Throwable t) {
        warning(tag, "", t);
    }

    public static void warning(String tag, String msg, Throwable t) {
        getMainLogger().warn(tag + " " + msg, t);
    }

}
