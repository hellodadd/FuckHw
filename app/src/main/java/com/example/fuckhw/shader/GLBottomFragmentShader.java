package com.example.fuckhw.shader;

import android.opengl.GLES20;

import com.example.fuckhw.R;
import com.example.fuckhw.UIConstant;
import com.example.fuckhw.shadervar.GLBottomShaderVar;

import org.rajawali3d.materials.shaders.AShaderBase;
import org.rajawali3d.materials.shaders.FragmentShader;
import org.rajawali3d.util.RawShaderLoader;

import java.util.Arrays;

public class GLBottomFragmentShader extends FragmentShader {
    private float[] fDimen;
    private float[] mColor;
    private int muAlphaHandle;
    private int muColorHandle;
    private int mufDimenHandle;

    public GLBottomFragmentShader() {
        setNeedsBuild(false);
        privateInitialize();
    }

    public void setColor(float[] colors) {
        this.mColor = Arrays.copyOf(colors, colors.length);
    }

    public void setfDimen(float[] fDimen) {
        this.fDimen = Arrays.copyOf(fDimen, fDimen.length);
    }

    private void privateInitialize() {
        this.mShaderString = RawShaderLoader.fetch(R.raw.glbottom_frag);
    }

    public void initialize() {
        privateInitialize();
    }

    public void main() {
    }

    public void setLocations(int programHandle) {
        super.setLocations(programHandle);
        this.muColorHandle = getUniformLocation(programHandle,
                GLBottomShaderVar.U_COLOR);
        this.mufDimenHandle = getUniformLocation(programHandle,
                GLBottomShaderVar.U_FDIMENS);
        this.muAlphaHandle = getUniformLocation(programHandle,
                GLBottomShaderVar.U_ALPHA);
    }

    public void applyParams() {
        super.applyParams();
        GLES20.glUniform4fv(this.muColorHandle, 1, this.mColor, 0);
        GLES20.glUniform4fv(this.mufDimenHandle, 1, this.fDimen, 0);
        int i = this.muAlphaHandle;
        float f = 0.0f;
        if (UIConstant.getAlpha() > 0.0f) {
            f = UIConstant.getAlpha();
        }
        GLES20.glUniform1f(i, f);
    }
}
