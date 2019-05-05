package example;

import static java.awt.event.KeyEvent.*;
import static org.lwjgl.glfw.GLFW.*;

public class GLFW2AWTKeyMapping {

    public static int mapToAWT(int glfwKeyCode) {
        if (glfwKeyCode < 128)
            return glfwKeyCode;

        switch (glfwKeyCode) {
            case GLFW_KEY_ESCAPE:
                return VK_ESCAPE;
            case GLFW_KEY_ENTER:
                return VK_ENTER;
            case GLFW_KEY_TAB:
                return VK_TAB;
            case GLFW_KEY_BACKSPACE:
                return VK_BACK_SPACE;
            case GLFW_KEY_INSERT:
                return VK_INSERT;
            case GLFW_KEY_DELETE:
                return VK_DELETE;
            case GLFW_KEY_RIGHT:
                return VK_RIGHT;
            case GLFW_KEY_LEFT:
                return VK_LEFT;
            case GLFW_KEY_DOWN:
                return VK_DOWN;
            case GLFW_KEY_UP:
                return VK_UP;
            case GLFW_KEY_PAGE_UP:
                return VK_PAGE_UP;
            case GLFW_KEY_PAGE_DOWN:
                return VK_PAGE_DOWN;
            case GLFW_KEY_HOME:
                return VK_HOME;
            case GLFW_KEY_END:
                return VK_END;
            case GLFW_KEY_CAPS_LOCK:
                return VK_CAPS_LOCK;
            case GLFW_KEY_SCROLL_LOCK:
                return VK_SCROLL_LOCK;
            case GLFW_KEY_NUM_LOCK:
                return VK_NUM_LOCK;
            case GLFW_KEY_PRINT_SCREEN:
                return VK_PRINTSCREEN;
            case GLFW_KEY_PAUSE:
                return VK_PAUSE;
            case GLFW_KEY_F1:
                return VK_F1;
            case GLFW_KEY_F2:
                return VK_F2;
            case GLFW_KEY_F3:
                return VK_F3;
            case GLFW_KEY_F4:
                return VK_F4;
            case GLFW_KEY_F5:
                return VK_F5;
            case GLFW_KEY_F6:
                return VK_F6;
            case GLFW_KEY_F7:
                return VK_F7;
            case GLFW_KEY_F8:
                return VK_F8;
            case GLFW_KEY_F9:
                return VK_F9;
            case GLFW_KEY_F10:
                return VK_F10;
            case GLFW_KEY_F11:
                return VK_F11;
            case GLFW_KEY_F12:
                return VK_F12;
            case GLFW_KEY_LEFT_SHIFT:
            case GLFW_KEY_RIGHT_SHIFT:
                return VK_SHIFT;
            case GLFW_KEY_LEFT_CONTROL:
            case GLFW_KEY_RIGHT_CONTROL:
                return VK_CONTROL;
            case GLFW_KEY_LEFT_ALT:
            case GLFW_KEY_RIGHT_ALT:
                return VK_ALT;
            case GLFW_KEY_LEFT_SUPER:
            case GLFW_KEY_RIGHT_SUPER:
                return VK_WINDOWS;
            case GLFW_KEY_MENU:
                return VK_CONTEXT_MENU;
            default:
                return VK_UNDEFINED;
        }
    }

    public static char mapToChar(int glfwKeyCode) {
        switch (glfwKeyCode) {
            case GLFW_KEY_ENTER:
                return '\n';
            case GLFW_KEY_BACKSPACE:
                return '\b';
            case GLFW_KEY_TAB:
                return '\t';
            default:
                return '\0';
        }
    }
}