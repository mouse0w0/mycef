package com.github.mouse0w0.mycef.example;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

public class Tessellator {

    private static final Tessellator INSTANCE = new Tessellator(1048576);
    private BufferBuilder buffer;
    private VertexBufferObject vbo;

    private Tessellator(int bufferSize) {
        vbo = new VertexBufferObject();
        buffer = new BufferBuilder(bufferSize);

//        vertexStatusBufId = GL15.glGenBuffers();
//        GL15.glBindBuffer(GL31.GL_UNIFORM_BUFFER, vertexStatusBufId);
//        GL15.glBufferData(GL31.GL_UNIFORM_BUFFER, 4 * 4, GL15.GL_STATIC_DRAW);
//        GL15.glBindBuffer(GL31.GL_UNIFORM_BUFFER, 0);
    }

    public static Tessellator getInstance() {
        return INSTANCE;
    }

    public BufferBuilder getBuffer() {
        return buffer;
    }

    public void draw() {
        buffer.finish();

//        GL15.glBindBuffer(GL31.GL_UNIFORM_BUFFER, vertexStatusBufId);
//        ByteBuffer bb = ByteBuffer.wrap(new byte[]{(byte) (buffer.isPosEnabled() ? 1 : 0), (byte) (buffer.isColorEnabled() ? 1 : 0), (byte) (buffer.isTexEnabled() ? 1 : 0), (byte) (buffer.isNormalEnabled() ? 1 : 0)});
//        GL15.glBufferSubData(GL31.GL_UNIFORM_BUFFER, 0, bb);
//        GL15.glBindBuffer(GL31.GL_UNIFORM_BUFFER, 0);
        vbo.uploadData(buffer);
        vbo.bind();

        if (buffer.isPosEnabled()) {
            GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, buffer.getOffset(), 0);
            GL20.glEnableVertexAttribArray(0);
        }
        if (buffer.isColorEnabled()) {
            GL20.glVertexAttribPointer(1, 4, GL11.GL_FLOAT, false, buffer.getOffset(), 3 * Float.BYTES);
            GL20.glEnableVertexAttribArray(1);
        }
        if (buffer.isTexEnabled()) {
            GL20.glVertexAttribPointer(2, 2, GL11.GL_FLOAT, false, buffer.getOffset(), 7 * Float.BYTES);
            GL20.glEnableVertexAttribArray(2);
        }
        if (buffer.isNormalEnabled()) {
            GL20.glVertexAttribPointer(3, 3, GL11.GL_FLOAT, false, buffer.getOffset(), 9 * Float.BYTES);
            GL20.glEnableVertexAttribArray(3);
        }

        vbo.drawArrays(buffer.getDrawMode());
        vbo.unbind();
    }
}
