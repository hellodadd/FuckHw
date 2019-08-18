package com.example.fuckhw.shader;

import android.opengl.GLES20;
import android.util.Log;

import com.example.fuckhw.R;
import com.example.fuckhw.UIConstant;
import com.example.fuckhw.shadervar.GLBottomShaderVar;
import com.example.fuckhw.shadervar.GLRiseBallShaderVar;

import org.rajawali3d.materials.shaders.FragmentShader;
import org.rajawali3d.util.RawShaderLoader;

import java.nio.FloatBuffer;
import java.util.Arrays;

public class GLRiseBallFragmentShader extends FragmentShader {
    private int mBallCount;
    private float[] mBottomBallCentre;
    private float mBottomBallR;
    FloatBuffer mCentreBuffer;
    private float[] mColor;
    FloatBuffer mRBuffer;
    public float[] mTopCentre;
    private int mTopCentreHandler;
    public float[] mTopR;
    private int mTopRHandler;
    private int muAlphaHandle;
    private int muCentreHandler;
    private int muColorHandler;
    private int muCountHandler;
    private int muRHandler;
    private int mubottomCentreHandler;
    private int mubottomRHandler;

    public GLRiseBallFragmentShader() {
        setNeedsBuild(false);
        privateInitialize();
    }

    public void setBallCount(int count) {
        this.mBallCount = count;
        //Log.e("zwb", " setBallCount mBallCount =" + mBallCount);
    }

    public void setRiseBallAttr(FloatBuffer mCentreBuffer, FloatBuffer mRBuffer) {
        this.mCentreBuffer = mCentreBuffer;
        this.mRBuffer = mRBuffer;
    }

    public void setTopBallAttr(float[] topCentre, float[] topR) {
        this.mTopCentre = Arrays.copyOf(topCentre, topCentre.length);
        this.mTopR = Arrays.copyOf(topR, topR.length);
    }

    public void setBottomBallAttr(float[] bottomBallCentre, float bottomBallR) {
        this.mBottomBallCentre = Arrays.copyOf(bottomBallCentre, bottomBallCentre.length);
        this.mBottomBallR = bottomBallR;
    }

    public void setColor(float[] colors) {
        this.mColor = Arrays.copyOf(colors, colors.length);
    }

    private void privateInitialize() {
        this.mShaderString = RawShaderLoader.fetch(R.raw.glriseball_frag);
    }

    public void initialize() {
        privateInitialize();
    }

    public void main() {
    }

    public void setLocations(int programHandle) {
        super.setLocations(programHandle);
        this.muColorHandler = getUniformLocation(programHandle, GLRiseBallShaderVar.U_COLOR);
        this.muCountHandler = getUniformLocation(programHandle, GLRiseBallShaderVar.U_COUNT);
        this.muCentreHandler = getUniformLocation(programHandle, GLRiseBallShaderVar.U_CENTRE);
        this.muRHandler = getUniformLocation(programHandle, GLRiseBallShaderVar.U_R);
        this.mubottomCentreHandler = getUniformLocation(programHandle, GLRiseBallShaderVar.U_BOTTOMCENTRE);
        this.mubottomRHandler = getUniformLocation(programHandle, GLRiseBallShaderVar.U_BOTTOMR);
        this.mTopCentreHandler = getUniformLocation(programHandle, GLRiseBallShaderVar.U_TOPCENTRE);
        this.mTopRHandler = getUniformLocation(programHandle, GLRiseBallShaderVar.U_TOPR);
        this.muAlphaHandle = getUniformLocation(programHandle, GLBottomShaderVar.U_ALPHA);
    }

    public void applyParams() {
        super.applyParams();
        //Log.e("zwb", " applyParams mBallCount =" + mBallCount);
        GLES20.glUniform4fv(this.muColorHandler, 1, this.mColor, 0);
        GLES20.glUniform1i(this.muCountHandler, this.mBallCount);
        GLES20.glUniform2fv(this.muCentreHandler, this.mBallCount, this.mCentreBuffer);
        GLES20.glUniform1fv(this.muRHandler, this.mBallCount, this.mRBuffer);
        GLES20.glUniform2fv(this.mubottomCentreHandler, 1, this.mBottomBallCentre, 0);
        GLES20.glUniform1f(this.mubottomRHandler, this.mBottomBallR);
        GLES20.glUniform2fv(this.mTopCentreHandler, 3, this.mTopCentre, 0);
        GLES20.glUniform1fv(this.mTopRHandler, 3, this.mTopR, 0);
        GLES20.glUniform1f(this.muAlphaHandle, UIConstant.getAlpha());
    }
}
