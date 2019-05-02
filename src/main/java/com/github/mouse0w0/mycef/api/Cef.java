package com.github.mouse0w0.mycef.api;

import org.cef.CefApp;
import org.cef.CefClient;
import org.cef.CefSettings;
import org.cef.OS;
import org.cef.browser.CefBrowserOsr;

import java.io.File;
import java.lang.reflect.Field;

public class Cef {

    private static String cefLibraryPath;

    private static boolean initialized;

    private static CefApp cefApp;
    private static CefClient cefClient;

    public static void setLibraryPath(String path) {
        cefLibraryPath = path;
    }

    public static CefBrowserOsr createBrowser(String url) {
        return createBrowser(url, false);
    }

    public static CefBrowserOsr createBrowser(String url, boolean transparent) {
        return (CefBrowserOsr) cefClient.createBrowser(url, true, transparent);
    }

    public static void init() {
        if (initialized) {
            return;
        }
        initialized = true;

        try {
            if (cefLibraryPath != null) {
                injectJavaLibraryPath(cefLibraryPath);
            }

            if (OS.isWindows()) {
                System.loadLibrary("jawt");
                System.loadLibrary("libcef");
            } else if (OS.isLinux()) {
                System.loadLibrary("cef");
            }
            System.loadLibrary("jcef");

            CefSettings cefSettings = new CefSettings();
            cefSettings.windowless_rendering_enabled = true;
            cefSettings.background_color = cefSettings.new ColorType(0, 255, 255, 255);

            if (cefLibraryPath != null) {
                cefSettings.locales_dir_path = cefLibraryPath + "\\locales";
                cefSettings.cache_path = cefLibraryPath + "\\cache";
                cefSettings.browser_subprocess_path = cefLibraryPath + "\\jcef_helper.exe";
            }

            cefApp = CefApp.getInstance(null, cefSettings);
            cefClient = cefApp.createClient();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void shutdown() {
        cefApp.shutdown();
        cefApp.dispose();
    }

    private static void injectJavaLibraryPath(String path) throws Exception {
        Field usrPaths = ClassLoader.class.getDeclaredField("usr_paths");
        usrPaths.setAccessible(true);

        String[] oldPaths = (String[]) usrPaths.get(null);
        String[] newPaths = new String[oldPaths.length + 1];

        System.arraycopy(oldPaths, 0, newPaths, 1, oldPaths.length);
        newPaths[0] = path.replace('/', File.separatorChar);
        usrPaths.set(null, newPaths);
    }
}
