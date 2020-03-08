package com.github.mouse0w0.mycef.api;

import org.cef.CefApp;
import org.cef.CefClient;
import org.cef.CefSettings;
import org.cef.browser.CefBrowser;
import org.cef.browser.CefRequestContext;

import java.io.File;
import java.lang.reflect.Field;

public final class MyCEF {

    private static String cefLibraryPath;

    private static CefSettings cefSettings = new CefSettings();

    private static CefApp cefApp;
    private static CefClient cefClient;

    public static void setLibraryPath(String path) {
        cefLibraryPath = path;
    }

    public static CefBrowser createBrowser(String url, boolean isTransparent, BrowserRenderer renderer, CefRequestContext context) {
        return cefClient.createBrowser(url, isTransparent, renderer, context);
    }

    public static synchronized void init() {
        if (cefApp != null)
            return;

        try {
            if (cefLibraryPath != null) {
                injectJavaLibraryPath(cefLibraryPath);
            }

            cefSettings.windowless_rendering_enabled = true;
            if (cefLibraryPath != null) {
                cefSettings.locales_dir_path = cefLibraryPath + "\\locales";
//                cefSettings.browser_subprocess_path = cefLibraryPath + "\\jcef_helper.exe";
            }
            if(cefSettings.background_color == null) {
                cefSettings.background_color = cefSettings.new ColorType(255, 255, 255, 255);
            }

            cefApp = CefApp.getInstance(null, cefSettings);
            cefClient = cefApp.createClient();
        } catch (Exception e) {
            throw new MyCEFInitializationException("Cannot initialize MyCEF.", e);
        }
    }

    public static CefApp getCefApp() {
        return cefApp;
    }

    public static CefClient getCefClient() {
        return cefClient;
    }

    public static CefSettings getCefSettings() {
        return cefSettings;
    }

    public static synchronized void shutdown() {
        cefApp.dispose();
    }

    private static void injectJavaLibraryPath(String path) throws Exception {
        Field usrPaths = ClassLoader.class.getDeclaredField("usr_paths");
        usrPaths.setAccessible(true);

        String[] oldPaths = (String[]) usrPaths.get(null);
        String[] newPaths;
        if (oldPaths == null) {
            newPaths = new String[1];
        } else {
            newPaths = new String[oldPaths.length + 1];
            System.arraycopy(oldPaths, 0, newPaths, 1, oldPaths.length);
        }

        newPaths[0] = path.replace('/', File.separatorChar);
        usrPaths.set(null, newPaths);

        String pathSeparator = System.getProperty("path.separator");
        System.setProperty("java.library.path", path + pathSeparator + System.getProperty("java.library.path"));
    }

    private MyCEF() {
        throw new AssertionError();
    }
}
