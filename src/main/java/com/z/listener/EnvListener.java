package com.z.listener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.z.utils.constant.GenericValues;

public class EnvListener implements ServletContextListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(EnvListener.class);

    private static ServletContext context;

    private static boolean devEnv;

    private static boolean prodEnv;

    private static String releaseVersion;

    private static ResourceBundle systemProps;
    
    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        LOGGER.info("contextDestroyed");
    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        LOGGER.info("contextInitialized");
        ServletContext servletContext = arg0.getServletContext();
        setContext(servletContext);

        String os = System.getProperty("os.name");
        String serverPath = System.getProperty("catalina.home");
        LOGGER.info("os:" + os + "---serverPath:" + serverPath);
        String appRootPath = servletContext.getRealPath("/");
        LOGGER.info("appRootPath:" + appRootPath);

        setDevEnv(false);
        setProdEnv(false);

        try {
            File releaseFile = new File(servletContext.getRealPath("/META-INF/release_version"));
            BufferedReader releaseReader = new BufferedReader(new FileReader(releaseFile));
            setReleaseVersion(releaseReader.readLine() + "_" + System.currentTimeMillis());
            releaseReader.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        
        if (os.contains("Windows")) {
            LOGGER.info("local server");
            setDevEnv(true);
            setSystemProps(ResourceBundle.getBundle("system_dev"));
        } else if (serverPath != null && serverPath.indexOf("tomcat-prod") != -1) {
            LOGGER.info("remote production server");
            setProdEnv(true);
            setSystemProps(ResourceBundle.getBundle("system_prod"));
        }
        context.setAttribute(GenericValues.STATIC_URL, systemProps.getString("system.static-url"));
    }

    public static ServletContext getContext() {
        return context;
    }

    public static void setContext(ServletContext context) {
        EnvListener.context = context;
    }

    public static boolean isDevEnv() {
        return devEnv;
    }

    public static void setDevEnv(boolean devEnv) {
        EnvListener.devEnv = devEnv;
    }

    public static boolean isProdEnv() {
        return prodEnv;
    }

    public static void setProdEnv(boolean inProdEnv) {
        prodEnv = inProdEnv;
    }

    public static String getReleaseVersion() {
        return releaseVersion;
    }

    public static void setReleaseVersion(String releaseVersion) {
        EnvListener.releaseVersion = releaseVersion;
    }

    public static ResourceBundle getSystemProps() {
        return systemProps;
    }

    public static void setSystemProps(ResourceBundle systemProps) {
        EnvListener.systemProps = systemProps;
    }
}
