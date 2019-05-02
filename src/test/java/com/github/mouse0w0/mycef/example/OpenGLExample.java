package com.github.mouse0w0.mycef.example;

import com.github.mouse0w0.mycef.api.Cef;
import org.cef.browser.CefBrowserOsr;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class OpenGLExample {

    private GLFWWindow window = new GLFWWindow();

    private ShaderProgram shader;

    private CefBrowserOsr cefBrowserOsr;

    public void run() {
        try {
            window.init();
            Cef.setLibraryPath("D:\\Workspace\\JavaProjects\\MyCEFOld\\native");
            Cef.init();

            loop();

            Cef.shutdown();
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

        cefBrowserOsr = Cef.createBrowser("https://www.baidu.com", true);
        cefBrowserOsr.resize(1280, 720);

        glViewport(0, 0, 1280, 720);

        while (!window.isShouldClose()) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

            shader.bind();

            shader.setUniform("u_ProjMatrix", new Matrix4f().ortho(0, 1280, 720, 0, 1, -1));
            shader.setUniform("u_ModelMatrix", new Matrix4f());

            cefBrowserOsr.mcefUpdate();
            cefBrowserOsr.draw(0, 0, 1280, 720);

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
