package com.example.fuckhw.shader;

import android.opengl.GLES20;

import com.example.fuckhw.R;
import com.example.fuckhw.UIConstant;
import com.example.fuckhw.shadervar.GLBottomShaderVar;
import com.example.fuckhw.shadervar.GLRiseBallShaderVar;

import org.rajawali3d.materials.shaders.FragmentShader;
import org.rajawali3d.util.RawShaderLoader;

import java.util.Arrays;

public class GLOrganicFragmentShader extends FragmentShader {
    private float[] mColor;
    public float[] mTopCentre;
    private int mTopCentreHandler;
    public float[] mTopR;
    private int mTopRHandler;
    private int muAlphaHandle;
    private int muColorHandler;

    public GLOrganicFragmentShader() {
        setNeedsBuild(false);
        privateInitialize();
    }

    public void setTopBallAttr(float[] topCentre, float[] topR) {
        this.mTopCentre = Arrays.copyOf(topCentre, topCentre.length);
        this.mTopR = Arrays.copyOf(topR, topR.length);
    }

    public void setColor(float[] colors) {
        this.mColor = Arrays.copyOf(colors, colors.length);
    }

    private void privateInitialize() {
        this.mShaderString = RawShaderLoader.fetch(R.raw.glorganic_frag);
    }

    public void initialize() {
        privateInitialize();
    }

    public void main() {
    }

    public void setLocations(int programHandle) {
        super.setLocations(programHandle);
        this.muColorHandler = getUniformLocation(programHandle, GLRiseBallShaderVar.U_COLOR);
        this.mTopCentreHandler = getUniformLocation(programHandle, GLRiseBallShaderVar.U_TOPCENTRE);
        this.mTopRHandler = getUniformLocation(programHandle, GLRiseBallShaderVar.U_TOPR);
        this.muAlphaHandle = getUniformLocation(programHandle, GLBottomShaderVar.U_ALPHA);
    }

    public void applyParams() {
        super.applyParams();
        GLES20.glUniform4fv(this.muColorHandler, 1, this.mColor, 0);
        GLES20.glUniform2fv(this.mTopCentreHandler, 3, this.mTopCentre, 0);
        GLES20.glUniform1fv(this.mTopRHandler, 3, this.mTopR, 0);
        GLES20.glUniform1f(this.muAlphaHandle, UIConstant.getAlpha());
    }
}
