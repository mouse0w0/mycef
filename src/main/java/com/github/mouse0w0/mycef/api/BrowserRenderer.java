package com.github.mouse0w0.mycef.api;

import org.cef.browser.CefBrowser;

import java.awt.*;
import java.nio.ByteBuffer;

public interface BrowserRenderer {

    void init(CefBrowser browser);

    void onPaint(CefBrowser browser, boolean popup, Rectangle[] dirtyRects, ByteBuffer buffer, int width, int height);

    void dispose();

    void clearPopupRects();

    void onPopupSize(Rectangle size);
}
