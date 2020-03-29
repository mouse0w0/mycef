package com.github.mouse0w0.mycef.api;

import org.cef.browser.CefBrowser;

public interface MyCefBrowser extends CefBrowser {
    void resize(int width, int height);

    BrowserEventHandler getBrowserEventHandler();
}
