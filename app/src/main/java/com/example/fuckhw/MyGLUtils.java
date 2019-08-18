package com.example.fuckhw;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class MyGLUtils {
    public static FloatBuffer addArrayToFloatBuffer(float[] arrays) {
        ByteBuffer vbb = ByteBuffer.allocateDirect(arrays.length * 4);
        vbb.order(ByteOrder.nativeOrder());
        FloatBuffer floatBuffer = vbb.asFloatBuffer();
        floatBuffer.put(arrays);
        floatBuffer.position(0);
        return floatBuffer;
    }
}
