// Copyright (c) 2014 The Chromium Embedded Framework Authors. All rights
// reserved. Use of this source code is governed by a BSD-style license that
// can be found in the LICENSE file.

package org.cef.browser;

import com.github.mouse0w0.mycef.api.BrowserRenderer;
import org.cef.CefClient;
import org.cef.DummyComponent;
import org.cef.callback.CefDragData;
import org.cef.handler.CefRenderHandler;

import java.awt.*;
import java.nio.ByteBuffer;

/**
 * This class represents an off-screen rendered browser.
 * The visibility of this class is "package". To create a new
 * CefBrowser instance, please use CefBrowserFactory.
 */
class CefBrowserOsr extends CefBrowser_N implements CefRenderHandler {
    // ===== MyCEF begin =====
    private BrowserRenderer renderer_;
    //    private CefRenderer renderer_;
//    private GLCanvas canvas_;
    private DummyComponent component_ = new DummyComponent();
    //    private long window_handle_ = 0;
    // ===== MyCEF end =====
    private Rectangle browser_rect_ = new Rectangle(0, 0, 1, 1); // Work around CEF issue #1437.
    private Point screenPoint_ = new Point(0, 0);
    // ===== MyCEF begin =====
//    private boolean isTransparent_;
    // ===== MyCEF end =====


    CefBrowserOsr(CefClient client, String url, BrowserRenderer renderer, CefRequestContext context) {
        this(client, url, renderer, context, null, null);
    }

    private CefBrowserOsr(CefClient client, String url, BrowserRenderer renderer,
                          CefRequestContext context, CefBrowserOsr parent, Point inspectAt) {
        super(client, url, context, parent, inspectAt);
        // ===== MyCEF begin =====
//        isTransparent_ = transparent;
//        renderer_ = new CefRenderer(transparent);
        renderer_ = renderer;
        renderer_.init(this);
        createBrowser(getClient(), 0, getUrl(), true, false, null,
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
            // ===== MyCEF begin =====
            if (getParentBrowser() != null) {
//                createDevTools(getParentBrowser(), getClient(), windowHandle, true, isTransparent_,
//                        null, getInspectAt());
                createDevTools(getParentBrowser(), getClient(), windowHandle, true, true,
                        null, getInspectAt());
            } else {
                createBrowser(getClient(), windowHandle, getUrl(), true, true, null,
                        getRequestContext());
//                createBrowser(getClient(), windowHandle, getUrl(), true, isTransparent_, null,
//                        getRequestContext());

            }
            // ===== MyCEF end =====
        } else {
            // OSR windows cannot be reparented after creation.
            setFocus(true);
        }
    }
}
