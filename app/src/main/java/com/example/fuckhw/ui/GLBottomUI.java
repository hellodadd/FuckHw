package com.example.fuckhw.ui;

import com.example.fuckhw.UIConstant;
import com.example.fuckhw.shader.GLBottomFragmentShader;
import com.example.fuckhw.shader.GLBottomVertexShader;

import org.rajawali3d.Object3D;
import org.rajawali3d.materials.Material;
import org.rajawali3d.scene.Scene;

import java.util.Arrays;

public class GLBottomUI extends Object3D {
    private GLBottomFragmentShader mBottomFragmentShader;
    private GLBottomVertexShader mBottomVertexShader;
    private float[] mCentre;
    private float[][] mColorArray = UIConstant.getColorArray();
    private int[] mIndexArray;
    private float mOffsetx;
    private float mOffsety;
    private float[] mVertexArray;

    public GLBottomUI(float[] centre, float offsetX, float offsetY) {
        this.mCentre = Arrays.copyOf(centre, centre.length);
        this.mOffsetx = offsetX;
        this.mOffsety = offsetY;
        setScaleY(-1.0d);
        loadModel();
        loadMaterial();
        //setModelMatrixEnabled(false);
        //setModelViewMatrixEnabled(false);
        //setInverseViewMatrixEnabled(false);
    }

    private void loadModel() {
        setTransparent(true);
        setBlendFunc(1, 771);
        this.mVertexArray = new float[]{this.mCentre[0] -
                this.mOffsetx, this.mCentre[1] - this.mOffsety, this.mCentre[2],
                this.mCentre[0] - this.mOffsetx,
                this.mCentre[1] + this.mOffsety,
                this.mCentre[2], this.mCentre[0] +
                this.mOffsetx, this.mCentre[1] - this.mOffsety, this.mCentre[2],
                this.mCentre[0] + this.mOffsetx, this.mCentre[1] + this.mOffsety, this.mCentre[2]};
        this.mIndexArray = new int[]{0, 1, 2, 2, 1, 3};
        setData(this.mVertexArray, null, null, null, this.mIndexArray, true);
    }

    public void addToScene(Scene scene) {
        scene.addChild(this);
    }

    private void loadMaterial() {
        this.mBottomVertexShader = new GLBottomVertexShader();
        this.mBottomFragmentShader = new GLBottomFragmentShader();
        this.mBottomFragmentShader.setColor(this.mColorArray[3]);
        this.mBottomFragmentShader.setfDimen(new float[]{this.mCentre[0], this.mCentre[1], this.mOffsetx, this.mOffsety});
        this.mMaterial = new Material(this.mBottomVertexShader, this.mBottomFragmentShader);
        setMaterial(this.mMaterial);
    }

    public void updateColor() {
        this.mColorArray = UIConstant.getColorArray();
        this.mBottomFragmentShader.setColor(this.mColorArray[3]);
    }
}
