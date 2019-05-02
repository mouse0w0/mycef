package example;

import com.github.mouse0w0.mycef.api.MyCEF;
import org.cef.browser.CefBrowser;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL;

import java.nio.file.Paths;

import static org.lwjgl.glfw.GLFW.glfwSetErrorCallback;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
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

    private void loop() {
        init();

        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

        GLBrowserRenderer glBrowserRenderer = new GLBrowserRenderer();

        cefBrowser = MyCEF.createBrowser("https://www.baidu.com", false, glBrowserRenderer, null);
        cefBrowser.resize(1280, 720);

        glViewport(0, 0, 1280, 720);

        while (!window.isShouldClose()) {
            glClear(GL_COLOR_BUFFER_BIT);

            shader.bind();

            shader.setUniform("u_ProjMatrix", new Matrix4f().ortho(0, 1280, 720, 0, 1, -1));
            shader.setUniform("u_ModelMatrix", new Matrix4f());

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
        new OpenGLExample().run();
    }
}
