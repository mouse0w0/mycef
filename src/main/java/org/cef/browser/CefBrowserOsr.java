// Copyright (c) 2014 The Chromium Embedded Framework Authors. All rights
// reserved. Use of this source code is governed by a BSD-style license that
// can be found in the LICENSE file.

package org.cef.browser;

import com.github.mouse0w0.mycef.api.BrowserEventHandler;
import com.github.mouse0w0.mycef.api.BrowserRenderer;
import org.cef.CefClient;
import org.cef.DummyComponent;
import org.cef.callback.CefDragData;
import org.cef.handler.CefRenderHandler;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.nio.ByteBuffer;

/**
 * This class represents an off-screen rendered browser.
 * The visibility of this class is "package". To create a new
 * CefBrowser instance, please use CefBrowserFactory.
 */
class CefBrowserOsr extends CefBrowser_N implements CefRenderHandler, BrowserEventHandler {
    // ===== MyCEF begin =====
    private BrowserRenderer renderer_;
    //    private CefRenderer renderer_;
//    private GLCanvas canvas_;
    private DummyComponent component_ = new DummyComponent();
    //    private long window_handle_ = 0;
    // ===== MyCEF end =====
    private Rectangle browser_rect_ = new Rectangle(0, 0, 1, 1); // Work around CEF issue #1437.
    private Point screenPoint_ = new Point(0, 0);
    private boolean isTransparent_;

    CefBrowserOsr(CefClient client, String url, boolean transparent, BrowserRenderer renderer, CefRequestContext context) {
        this(client, url, transparent, renderer, context, null, null);
    }

    private CefBrowserOsr(CefClient client, String url, boolean transparent, BrowserRenderer renderer,
                          CefRequestContext context, CefBrowserOsr parent, Point inspectAt) {
        super(client, url, context, parent, inspectAt);
        isTransparent_ = transparent;
        // ===== MyCEF begin =====
//        renderer_ = new CefRenderer(transparent);
        renderer_ = renderer;
        renderer_.init(this);
        createBrowser(getClient(), 0, getUrl(), true, isTransparent_, null,
                getRequestContext());
//        createGLCanvas();
        // ===== MyCEF end =====
    }

    @Override
    public void createImmediately() {
        // Create the browser immediately.
        createBrowserIfRequired(false);
    }

    @Override
    public Component getUIComponent() {
        // ===== MyCEF begin =====
        return component_;
//        return canvas_;
        // ===== MyCEF end =====
    }

    @Override
    public CefRenderHandler getRenderHandler() {
        return this;
    }

    @Override
    public void resize(int width, int height) {
        browser_rect_.setBounds(0, 0, width, height);
        wasResized(width, height);
    }

    @Override
    public BrowserEventHandler getBrowserEventHandler() {
        return this;
    }

    @Override
    protected CefBrowser_N createDevToolsBrowser(CefClient client, String url,
                                                 CefRequestContext context, CefBrowser_N parent, Point inspectAt) {
        // ===== MyCEF begin =====
        throw new UnsupportedOperationException();
//        return new CefBrowserOsr(
//                client, url, isTransparent_, context, (CefBrowserOsr) this, inspectAt);
        // ===== MyCEF end =====
    }

    // ===== MyCEF begin =====
//    private synchronized long getWindowHandle() {
//        if (window_handle_ == 0) {
//            NativeSurface surface = canvas_.getNativeSurface();
//            if (surface != null) {
//                surface.lockSurface();
//                window_handle_ = getWindowHandle(surface.getSurfaceHandle());
//                surface.unlockSurface();
//                assert (window_handle_ != 0);
//            }
//        }
//        return window_handle_;
//    }
//
//    @SuppressWarnings("serial")
//    private void createGLCanvas() {
//        GLProfile glprofile = GLProfile.getMaxFixedFunc(true);
//        GLCapabilities glcapabilities = new GLCapabilities(glprofile);
//        canvas_ = new GLCanvas(glcapabilities) {
//            @Override
//            public void paint(Graphics g) {
//                createBrowserIfRequired(true);
//                super.paint(g);
//            }
//        };
//
//        canvas_.addGLEventListener(new GLEventListener() {
//            @Override
//            public void reshape(
//                    GLAutoDrawable glautodrawable, int x, int y, int width, int height) {
//                browser_rect_.setBounds(x, y, width, height);
//                screenPoint_ = canvas_.getLocationOnScreen();
//                wasResized(width, height);
//            }
//
//            @Override
//            public void init(GLAutoDrawable glautodrawable) {
//                renderer_.initialize(glautodrawable.getGL().getGL2());
//            }
//
//            @Override
//            public void dispose(GLAutoDrawable glautodrawable) {
//                renderer_.cleanup(glautodrawable.getGL().getGL2());
//            }
//
//            @Override
//            public void display(GLAutoDrawable glautodrawable) {
//                renderer_.render(glautodrawable.getGL().getGL2());
//            }
//        });
//
//        canvas_.addMouseListener(new MouseListener() {
//            @Override
//            public void mousePressed(MouseEvent e) {
//                sendMouseEvent(e);
//            }
//
//            @Override
//            public void mouseReleased(MouseEvent e) {
//                sendMouseEvent(e);
//            }
//
//            @Override
//            public void mouseEntered(MouseEvent e) {
//                sendMouseEvent(e);
//            }
//
//            @Override
//            public void mouseExited(MouseEvent e) {
//                sendMouseEvent(e);
//            }
//
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                sendMouseEvent(e);
//            }
//        });
//
//        canvas_.addMouseMotionListener(new MouseMotionListener() {
//            @Override
//            public void mouseMoved(MouseEvent e) {
//                sendMouseEvent(e);
//            }
//
//            @Override
//            public void mouseDragged(MouseEvent e) {
//                sendMouseEvent(e);
//            }
//        });
//
//        canvas_.addMouseWheelListener(new MouseWheelListener() {
//            @Override
//            public void mouseWheelMoved(MouseWheelEvent e) {
//                sendMouseWheelEvent(e);
//            }
//        });
//
//        canvas_.addKeyListener(new KeyListener() {
//            @Override
//            public void keyTyped(KeyEvent e) {
//                sendKeyEvent(e);
//            }
//
//            @Override
//            public void keyPressed(KeyEvent e) {
//                sendKeyEvent(e);
//            }
//
//            @Override
//            public void keyReleased(KeyEvent e) {
//                sendKeyEvent(e);
//            }
//        });
//
//        canvas_.setFocusable(true);
//        canvas_.addFocusListener(new FocusListener() {
//            @Override
//            public void focusLost(FocusEvent e) {
//                setFocus(false);
//            }
//
//            @Override
//            public void focusGained(FocusEvent e) {
//                // Dismiss any Java menus that are currently displayed.
//                MenuSelectionManager.defaultManager().clearSelectedPath();
//                setFocus(true);
//            }
//        });
//    }
    // ===== MyCEF end =====

    @Override
    public Rectangle getViewRect(CefBrowser browser) {
        return browser_rect_;
    }

    @Override
    public Point getScreenPoint(CefBrowser browser, Point viewPoint) {
        Point screenPoint = new Point(screenPoint_);
        screenPoint.translate(viewPoint.x, viewPoint.y);
        return screenPoint;
    }

    @Override
    public void onPopupShow(CefBrowser browser, boolean show) {
        if (!show) {
            renderer_.clearPopupRects();
            invalidate();
        }
    }

    @Override
    public void onPopupSize(CefBrowser browser, Rectangle size) {
        renderer_.onPopupSize(size);
    }

    @Override
    public void onPaint(CefBrowser browser, boolean popup, Rectangle[] dirtyRects,
                        ByteBuffer buffer, int width, int height) {
        // ===== MyCEF begin =====
        renderer_.onPaint(browser, popup, dirtyRects, buffer, width, height);
//        canvas_.getContext().makeCurrent();
//        renderer_.onPaint(canvas_.getGL().getGL2(), popup, dirtyRects, buffer, width, height);
//        canvas_.getContext().release();
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                canvas_.display();
//            }
//        });
        // ===== MyCEF end =====
    }

    @Override
    public void onCursorChange(CefBrowser browser, final int cursorType) {
        // ===== MyCEF begin =====
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                canvas_.setCursor(new Cursor(cursorType));
//            }
//        });
        // ===== MyCEF end =====
    }

    @Override
    public boolean startDragging(CefBrowser browser, CefDragData dragData, int mask, int x, int y) {
        // TODO(JCEF) Prepared for DnD support using OSR mode.
        return false;
    }

    @Override
    public void updateDragCursor(CefBrowser browser, int operation) {
        // TODO(JCEF) Prepared for DnD support using OSR mode.
    }


    private void createBrowserIfRequired(boolean hasParent) {
        long windowHandle = 0;
        if (hasParent) {
            // ===== MyCEF begin =====
//            windowHandle = getWindowHandle();
            // ===== MyCEF end =====
        }

        if (getNativeRef("CefBrowser") == 0) {
            if (getParentBrowser() != null) {
                createDevTools(getParentBrowser(), getClient(), windowHandle, true, isTransparent_,
                        null, getInspectAt());
            } else {
                createBrowser(getClient(), windowHandle, getUrl(), true, isTransparent_, null,
                        getRequestContext());

            }
        } else {
            // OSR windows cannot be reparented after creation.
            setFocus(true);
        }
    }

    @Override
    public void onMouseMove(int x, int y, int mods, boolean left) {
        sendMouseEvent(new MouseEvent(component_, left ? MouseEvent.MOUSE_EXITED : MouseEvent.MOUSE_MOVED, 0, mods, x, y, 0, false));
    }

    @Override
    public void onMouseButton(int x, int y, int mods, int button, boolean pressed, int clickCount) {
        sendMouseEvent(new MouseEvent(component_, pressed ? MouseEvent.MOUSE_PRESSED : MouseEvent.MOUSE_RELEASED, 0, mods, x, y, clickCount, false, button));
    }

    @Override
    public void onKeyTyped(char keyChar, int mods) {
        sendKeyEvent(new KeyEvent(component_, KeyEvent.KEY_TYPED, 0, mods, 0, keyChar));
    }

    @Override
    public void onKey(char keyChar, int mods, boolean pressed) {
        sendKeyEvent(new KeyEvent(component_, pressed ? KeyEvent.KEY_PRESSED : KeyEvent.KEY_RELEASED, 0, mods, 0, keyChar));
    }

    @Override
    public void onKey(int keyCode, int mods, boolean pressed) {
        sendKeyEvent(new KeyEvent(component_, pressed ? KeyEvent.KEY_PRESSED : KeyEvent.KEY_RELEASED, 0, mods, keyCode));
    }

    @Override
    public void onMouseWheel(int x, int y, int mods, int amount, int wheelRotation) {
        sendMouseWheelEvent(new MouseWheelEvent(component_, MouseEvent.MOUSE_WHEEL, 0, mods, x, y, 0, false, MouseWheelEvent.WHEEL_UNIT_SCROLL, amount, wheelRotation));
    }

    @Override
    public void onFocus(boolean focused) {
        setFocus(focused);
    }
}
