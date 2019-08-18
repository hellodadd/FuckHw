package com.example.fuckhw.ui;

import android.util.Log;

import com.example.fuckhw.UIConstant;
import com.example.fuckhw.model.GLOrganicUIAttrs;
import com.example.fuckhw.shader.GLOrganicFragmentShader;
import com.example.fuckhw.shader.GLOrganicVertexShader;

import org.rajawali3d.BufferInfo;
import org.rajawali3d.Object3D;
import org.rajawali3d.materials.Material;
import org.rajawali3d.scene.Scene;

import java.util.ArrayList;
import java.util.List;

public class GLOrganicUI extends Object3D {
    private float[] color;
    private float mAngle = 0.0f;
    private float mAngleSpeed;
    private GLOrganicFragmentShader mGlOrganicFragmentShader;
    private GLOrganicVertexShader mGlOrganicVertexShader;
    private int[] mIndexArray;
    BufferInfo mIndexBufferInfo;
    private List<Integer> mIndexList = new ArrayList();
    private int mLayer;
    private float mOragnicAngleSpeed;
    public float[] mOrignalTopR;
    public float[] mTopCentre;
    public float[] mTopR;
    private float[] mTopRectCentre;
    private float mTopRectoffsetX;
    private float[] mVertexArray;
    BufferInfo mVertexBufferInfo;
    private List<Float> mVertexList = new ArrayList();
    private float mdRr;

    /* renamed from: com.huawei.keyguard.view.charge.e60.wired.ui.GLOrganicUI$1 */
    class C36121 implements Runnable {
        int startTime = 0;

        C36121() {
        }

        public void run() {
            while (this.startTime <= 1000) {
                GLOrganicUI.this.mTopR[1] = (GLOrganicUI.this.mOrignalTopR[1] * ((float) this.startTime)) / 1000.0f;
                GLOrganicUI.this.mTopR[2] = (GLOrganicUI.this.mOrignalTopR[2] * ((float) this.startTime)) / 1000.0f;
                this.startTime += 16;
                GLOrganicUI.this.mGlOrganicFragmentShader.setTopBallAttr(GLOrganicUI.this.mTopCentre, GLOrganicUI.this.mTopR);
                try {
                    Thread.sleep(16);
                } catch (InterruptedException e) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("initAnimation fail to sleep ");
                    stringBuilder.append(e.toString());
                    //HwLog.m1901w("GLOrganicUI", stringBuilder.toString());
                }
            }
        }
    }

    public GLOrganicUI(GLOrganicUIAttrs attrs) {
        setScaleY(-1.0d);
        this.color = attrs.getColor();
        this.mTopRectCentre = attrs.getTopCircleCentre();
        this.mTopRectoffsetX = attrs.getTopTriangleOffsetX();
        this.mdRr = attrs.getdRr();
        this.mTopCentre = attrs.getTopCentre();
        this.mOrignalTopR = attrs.getTopR();
        this.mTopR = new float[]{this.mOrignalTopR[0], this.mOrignalTopR[1], this.mOrignalTopR[2]};
        this.mAngle = attrs.getAngle();
        this.mAngleSpeed = attrs.getAngleSpeed();
        this.mOragnicAngleSpeed = this.mAngleSpeed;
        this.mLayer = attrs.getLayer();
        initVertex();
        loadModel();
        loadMaterial();
        initAnimation();
        //setModelMatrixEnabled(false);
        //setModelViewMatrixEnabled(false);
        //setInverseViewMatrixEnabled(false);
    }

    private void initAnimation() {
        new Thread(new C36121()).start();
    }

    private void initVertex() {
        float angleSpan = (360.0f - 0.0f) / ((float) 90);
        int index = 0;
        float angdeg = 0.0f;
        while (angdeg < 360.0f) {
            double angrad = Math.toRadians((double) angdeg);
            this.mVertexList.add((float) (((double) this.mTopRectCentre[0]) - (((double) this.mTopRectoffsetX) * Math.sin(angrad))));
            this.mVertexList.add((float) (((double) this.mTopRectCentre[1]) + (((double) this.mTopRectoffsetX) * Math.cos(angrad))));
            this.mVertexList.add(this.mTopRectCentre[2]);
            int index2 = index + 1;
            this.mIndexList.add(index);
            this.mVertexList.add(this.mTopRectCentre[0]);
            this.mVertexList.add(this.mTopRectCentre[1]);
            this.mVertexList.add(this.mTopRectCentre[2]);
            int index3 = index2 + 1;
            this.mIndexList.add(index2);
            angdeg += angleSpan;
            double angrad2 = Math.toRadians((double) angdeg);
            this.mVertexList.add((float) (((double) this.mTopRectCentre[0]) - (((double) this.mTopRectoffsetX) * Math.sin(angrad2))));
            this.mVertexList.add((float) (((double) this.mTopRectCentre[1]) + (((double) this.mTopRectoffsetX) * Math.cos(angrad2))));
            this.mVertexList.add(this.mTopRectCentre[2]);
            int index4 = index3 + 1;
            this.mIndexList.add(index3);
            index = index4;
        }
    }

    private void loadModel() {
        int i;
        setTransparent(true);
        setBlendFunc(1, 771);
        this.mVertexArray = new float[this.mVertexList.size()];
        int size = this.mVertexList.size();
        for (i = 0; i < size; i++) {
            this.mVertexArray[i] = this.mVertexList.get(i);
        }
        this.mIndexArray = new int[this.mIndexList.size()];
        size = this.mIndexList.size();
        for (i = 0; i < size; i++) {
            this.mIndexArray[i] = this.mIndexList.get(i);
        }
        setData(this.mVertexArray, null, null, null, this.mIndexArray, true);
        this.mVertexBufferInfo = getGeometry().getVertexBufferInfo();
        this.mIndexBufferInfo = getGeometry().getIndexBufferInfo();
        getGeometry().setNumIndices(this.mIndexArray.length);
        getGeometry().changeBufferUsage(this.mVertexBufferInfo, 35048);
        getGeometry().changeBufferUsage(this.mIndexBufferInfo, 35048);
    }

    public void addToScene(Scene scene) {
        scene.addChild(this);
    }

    private void loadMaterial() {
        this.mGlOrganicVertexShader = new GLOrganicVertexShader();
        this.mGlOrganicFragmentShader = new GLOrganicFragmentShader();
        this.mGlOrganicFragmentShader.setColor(this.color);
        this.mGlOrganicFragmentShader.setTopBallAttr(this.mTopCentre, this.mTopR);
        this.mMaterial = new Material(this.mGlOrganicVertexShader, this.mGlOrganicFragmentShader);
        setMaterial(this.mMaterial);
    }

    public void updateModel(int drawCount) {
        //Log.e("zwb", " updateModel dddd - " + drawCount);
        updateOragincLocation(drawCount);
        this.mGlOrganicFragmentShader.setTopBallAttr(this.mTopCentre, this.mTopR);
    }

    private void updateOragincLocation(int drawCount) {
        if (this.mLayer == 0) {
            this.mAngleSpeed = (float) (((double) this.mOragnicAngleSpeed) + Math.sin(Math.toRadians((double) (drawCount - 90))));
        } else {
            this.mAngleSpeed = (float) (((double) this.mOragnicAngleSpeed) + Math.cos(Math.toRadians((double) drawCount)));
        }
        this.mAngle += (this.mAngleSpeed * ((UIConstant.getOrganicRotateSpeed() * 1.0f) / 50.0f)) % 360.0f;
        this.mTopCentre[2] = (float) (((double) this.mdRr) * Math.sin(Math.toRadians((double) (this.mAngle + 0.0f))));
        this.mTopCentre[3] = (float) (((double) this.mdRr) * Math.cos(Math.toRadians((double) (this.mAngle - 180.0f))));
        this.mTopCentre[4] = (float) (((double) this.mdRr) * Math.sin(Math.toRadians((double) (this.mAngle - 180.0f))));
        this.mTopCentre[5] = (float) (((double) this.mdRr) * Math.cos(Math.toRadians((double) ((this.mAngle - 180.0f) - 180.0f))));
    }

    public static List<GLOrganicUI> createOrganicUIs(int count, GLOrganicUIAttrs[] attrsArray) {
        List<GLOrganicUI> list = new ArrayList(count);
        for (int i = 0; i < count - 1; i++) {
            list.add(new GLOrganicUI(attrsArray[i]));
        }
        return list;
    }

    public static List<GLOrganicUI> createOrganicUILayer() {
        float R = UIConstant.getTopBallRadius();
        float r = 0.55f * R;
        float dRr = R - (0.96f * (0.55f * R));
        GLOrganicUIAttrs[] attrsArray = new GLOrganicUIAttrs[3];
        float[] angleArray = UIConstant.getAngleArray();
        float[] angleSpeed = UIConstant.getAngleSpeed();
        int[] minRadius = UIConstant.getMinRadius();
        int[] randomRadius = UIConstant.getRandomRadius();
        float[][] colorArray = UIConstant.getColorArray();
        int[] zArray = UIConstant.getZArray();
        for (int i = 0; i < 2; i++) {
            GLOrganicUIAttrs attrs = new GLOrganicUIAttrs();
            float[] fArr = new float[3];
            fArr[0] = 0.0f;
            fArr[1] = 0.0f;
            fArr[2] = (float) zArray[i];
            attrs.setTopCircleCentre(fArr);
            attrs.setTopTriangleOffsetX(UIConstant.getTopRectLength());
            attrs.setTopTriangleOffsetY(UIConstant.getTopRectLength());
            float[] r3 = new float[6];
            r3[0] = 0.0f;
            r3[1] = 0.0f;
            r3[2] = 0.0f + ((float) (((double) dRr) * Math.sin(Math.toRadians((double) (0.0f + angleArray[i])))));
            r3[3] = 0.0f + ((float) (((double) dRr) * Math.cos(Math.toRadians((double) (angleArray[i] - 0.024902344f)))));
            r3[4] = 0.0f + ((float) (((double) dRr) * Math.sin(Math.toRadians((double) (angleArray[i] - 0.024902344f)))));
            r3[5] = 0.0f + ((float) (((double) dRr) * Math.cos(Math.toRadians((double) (-360.0f + angleArray[i])))));
            attrs.setTopCentre(r3);
            attrs.setTopR(new float[]{R, r, r});
            attrs.setColor(colorArray[i]);
            attrs.setAngle(angleArray[i]);
            attrs.setAngleSpeed(angleSpeed[i]);
            attrs.setRandomR(new int[]{minRadius[i], randomRadius[i]});
            attrs.setdRr(dRr);
            attrs.setLayer(i);
            attrsArray[i] = attrs;
        }
        return GLOrganicUI.createOrganicUIs(3, attrsArray);
    }

    public void updateColor(int index) {
        this.color = UIConstant.getColorArray()[index];
        this.mGlOrganicFragmentShader.setColor(this.color);
    }
}
