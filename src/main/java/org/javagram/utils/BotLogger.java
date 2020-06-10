package org.javagram.utils;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.*;
import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.builder.api.AppenderComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilderFactory;
import org.apache.logging.log4j.core.config.builder.api.LayoutComponentBuilder;
import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;

/**
 *
 * @author Mehdi DHAKOUANI (c) 2018-2019
 */
public final class BotLogger {

    private final static BotLogger INSTANCE;

    private ConfigurationBuilder<BuiltConfiguration> configurationBuilder = null;
    private Logger _logger = null;
    private final static Level LEVEL = Level.ALL;

    static {
        //System.setProperty("log4j2.contextSelector", "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector");
        System.setProperty("log4j2.skipJansi", "false");
        INSTANCE = new BotLogger();
    }
    
    private BotLogger() {
        this.configurationBuilder = ConfigurationBuilderFactory.newConfigurationBuilder();
        this.configurationBuilder.setStatusLevel(LEVEL);
        this.configurationBuilder.setConfigurationName("BotLoggerBuilder");

        // create a layout builder
        LayoutComponentBuilder layoutBuilder = this.configurationBuilder
                .newLayout("PatternLayout")
                .addAttribute("pattern", "%d{DEFAULT} %-5p %m{ansi} %throwable{full} %n")
                .addAttribute("charset", "UTF-8");

        // create a console appender
        AppenderComponentBuilder appenderBuilder = this.configurationBuilder
                .newAppender("StdoutAppender", "CONSOLE")
                .addAttribute("target", ConsoleAppender.Target.SYSTEM_OUT);
        appenderBuilder.add(layoutBuilder);
        this.configurationBuilder.add(appenderBuilder);

        this.configurationBuilder.add(
                this.configurationBuilder.newLogger("BotLogger", LEVEL)
                        .add(this.configurationBuilder.newAppenderRef("StdoutAppender"))
                .addAttribute("additivity", false)
        );

        final LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
        final Configuration config = ctx.getConfiguration();
        config.addLogger("BotLogger", this.configurationBuilder.build().getLogger("BotLogger"));
        ctx.updateLoggers();
        this._logger = LogManager.getLogger("BotLogger");
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