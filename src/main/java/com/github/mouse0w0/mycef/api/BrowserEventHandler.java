package com.github.mouse0w0.mycef.api;

public interface BrowserEventHandler {

    void onMouseMove(int x, int y, int mods, boolean left);

    void onMouseButton(int x, int y, int button, int mods, boolean pressed, int clickCount);

    void onMouseWheel(int x, int y, int mods, int amount, int wheelRotation);

    void onKeyTyped(char keyChar, int mods);

    void onKey(char keyChar, int mods, boolean pressed);

    void onKey(int keyCode, int mods, boolean pressed);

    void onFocus(boolean focused);
}
