package example;

import com.github.mouse0w0.mycef.api.BrowserEventHandler;
import com.github.mouse0w0.mycef.api.MyCEF;
import org.cef.browser.CefBrowser;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL;

import java.nio.file.Paths;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class OpenGLExample {

    private GLFWWindow window = new GLFWWindow();

    private ShaderProgram shader;

    private CefBrowser cefBrowser;

    public void run() {
        try {
            window.init();

            MyCEF.setLibraryPath(Paths.get("native").toAbsolutePath().toString());
            MyCEF.init();

            loop();

            MyCEF.shutdown();
            window.dispose();
        } finally {
            glfwTerminate();
            glfwSetErrorCallback(null).free();
        }
    }

    private void init() {
        GL.createCapabilities();

        shader = new ShaderProgram();
        try {
            shader.createVertexShader(Utils.readString("gui.vert"));
            shader.createFragmentShader(Utils.readString("gui.frag"));
            shader.link();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int mouseX, mouseY;

    private void initEvent() {
        BrowserEventHandler handler = cefBrowser.getBrowserEventHandler();
        glfwSetCursorPosCallback(window.getWindow(), (window1, xpos, ypos) -> {
            mouseX = (int) xpos;
            mouseY = (int) ypos;
            handler.onMouseMove(mouseX, mouseY);
        });
        glfwSetMouseButtonCallback(window.getWindow(), (window1, button, action, mods) ->
                handler.onMouseButton(mouseX, mouseY, button, mods, action != GLFW_RELEASE, 1));
        glfwSetScrollCallback(window.getWindow(), (window1, xoffset, yoffset) ->
                handler.onMouseWheel(mouseX, mouseY, 0, 1, (int) yoffset * 100));
        glfwSetKeyCallback(window.getWindow(), (window1, key, scancode, action, mods) ->
                handler.onKey((char) GLFW2AWTKeyMapping.mapToAWT(key), mods, action != GLFW_RELEASE));
        glfwSetCharCallback(window.getWindow(), (window1, codepoint) ->
                handler.onKeyTyped((char) codepoint, 0));
        glfwSetCursorEnterCallback(window.getWindow(), (window1, entered) ->
                handler.onMouseEnter(mouseX, mouseY, entered));
//        glfwSetWindowFocusCallback(window.getWindow(), (window1, focused) ->
//                handler.onFocus(focused));
    }

    private void loop() {
        init();

        GLBrowserRenderer glBrowserRenderer = new GLBrowserRenderer();

        cefBrowser = MyCEF.createBrowser("https://www.bilibili.com", false, glBrowserRenderer, null);
        cefBrowser.resize(1280, 720);
        initEvent();

        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

        glViewport(0, 0, 1280, 720);

        shader.bind();

        shader.setUniform("u_ProjMatrix", new Matrix4f().ortho(0, 1280, 720, 0, 1, -1));
        shader.setUniform("u_ModelMatrix", new Matrix4f());

        while (!window.isShouldClose()) {
            glClear(GL_COLOR_BUFFER_BIT);

            glBrowserRenderer.render();

            window.endFrame();
        }
    }

    public static void main(String[] args) {
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> e.printStackTrace());

        new OpenGLExample().run();
    }
}
