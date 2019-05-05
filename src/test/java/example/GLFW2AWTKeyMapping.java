package example;

import static java.awt.event.KeyEvent.*;
import static org.lwjgl.glfw.GLFW.*;

public class GLFW2AWTKeyMapping {

    public static int mapToAWT(int glfwKeyCode) {
        if (glfwKeyCode < 256)
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
            case GLFW_KEY_F5:
                return VK_F5;
            default:
                return VK_UNDEFINED;
        }
    }
}

//        GLFW_KEY_RIGHT         = 262,
//        GLFW_KEY_LEFT          = 263,
//        GLFW_KEY_DOWN          = 264,
//        GLFW_KEY_UP            = 265,
//        GLFW_KEY_PAGE_UP       = 266,
//        GLFW_KEY_PAGE_DOWN     = 267,
//        GLFW_KEY_HOME          = 268,
//        GLFW_KEY_END           = 269,
//        GLFW_KEY_CAPS_LOCK     = 280,
//        GLFW_KEY_SCROLL_LOCK   = 281,
//        GLFW_KEY_NUM_LOCK      = 282,
//        GLFW_KEY_PRINT_SCREEN  = 283,
//        GLFW_KEY_PAUSE         = 284,
//        GLFW_KEY_F1            = 290,
//        GLFW_KEY_F2            = 291,
//        GLFW_KEY_F3            = 292,
//        GLFW_KEY_F4            = 293,
//        GLFW_KEY_F5            = 294,
//        GLFW_KEY_F6            = 295,
//        GLFW_KEY_F7            = 296,
//        GLFW_KEY_F8            = 297,
//        GLFW_KEY_F9            = 298,
//        GLFW_KEY_F10           = 299,
//        GLFW_KEY_F11           = 300,
//        GLFW_KEY_F12           = 301,
//        GLFW_KEY_F13           = 302,
//        GLFW_KEY_F14           = 303,
//        GLFW_KEY_F15           = 304,
//        GLFW_KEY_F16           = 305,
//        GLFW_KEY_F17           = 306,
//        GLFW_KEY_F18           = 307,
//        GLFW_KEY_F19           = 308,
//        GLFW_KEY_F20           = 309,
//        GLFW_KEY_F21           = 310,
//        GLFW_KEY_F22           = 311,
//        GLFW_KEY_F23           = 312,
//        GLFW_KEY_F24           = 313,
//        GLFW_KEY_F25           = 314,
//        GLFW_KEY_KP_0          = 320,
//        GLFW_KEY_KP_1          = 321,
//        GLFW_KEY_KP_2          = 322,
//        GLFW_KEY_KP_3          = 323,
//        GLFW_KEY_KP_4          = 324,
//        GLFW_KEY_KP_5          = 325,
//        GLFW_KEY_KP_6          = 326,
//        GLFW_KEY_KP_7          = 327,
//        GLFW_KEY_KP_8          = 328,
//        GLFW_KEY_KP_9          = 329,
//        GLFW_KEY_KP_DECIMAL    = 330,
//        GLFW_KEY_KP_DIVIDE     = 331,
//        GLFW_KEY_KP_MULTIPLY   = 332,
//        GLFW_KEY_KP_SUBTRACT   = 333,
//        GLFW_KEY_KP_ADD        = 334,
//        GLFW_KEY_KP_ENTER      = 335,
//        GLFW_KEY_KP_EQUAL      = 336,
//        GLFW_KEY_LEFT_SHIFT    = 340,
//        GLFW_KEY_LEFT_CONTROL  = 341,
//        GLFW_KEY_LEFT_ALT      = 342,
//        GLFW_KEY_LEFT_SUPER    = 343,
//        GLFW_KEY_RIGHT_SHIFT   = 344,
//        GLFW_KEY_RIGHT_CONTROL = 345,
//        GLFW_KEY_RIGHT_ALT     = 346,
//        GLFW_KEY_RIGHT_SUPER   = 347,
//        GLFW_KEY_MENU          = 348,
//        GLFW_KEY_LAST          = GLFW_KEY_MENU;
