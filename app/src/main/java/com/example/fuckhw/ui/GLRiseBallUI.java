package com.example.fuckhw.ui;

import android.util.Log;

import com.example.fuckhw.MyGLUtils;
import com.example.fuckhw.UIConstant;
import com.example.fuckhw.model.GLRiseBallUIAttrs;
import com.example.fuckhw.model.RiseBall;
import com.example.fuckhw.shader.GLRiseBallFragmentShader;
import com.example.fuckhw.shader.GLRiseBallVertexShader;

import org.rajawali3d.BufferInfo;
import org.rajawali3d.Object3D;
import org.rajawali3d.materials.Material;
import org.rajawali3d.scene.Scene;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

public class GLRiseBallUI extends Object3D {
    private float[] color;
    private float mAngle = 0.0f;
    private float mAngleR1;
    private float mAngleR2;
    private float mAngleSpeed;
    private int mBallCount;
    private float[] mBottomBallCentre;
    private float mBottomBallR;
    float[] mCentre;
    FloatBuffer mCentreBuffer;
    private List<Float> mCentreList = new ArrayList();
    private GLRiseBallFragmentShader mGlRiseBallFragmentShader;
    private GLRiseBallVertexShader mGlRiseBallVertexShader;
    private float mHalfScreenCentreY;
    private int[] mIndexArray;
    BufferInfo mIndexBufferInfo;
    private List<Integer> mIndexList = new ArrayList();
    private float mOragnicAngleSpeed;
    public float[] mOrignalTopR;
    /* renamed from: mR */
    float[] f3596mR;
    FloatBuffer mRBuffer;
    private List<Float> mRList = new ArrayList();
    private List<RiseBall> mRiseBalls;
    private List<RiseBall> mTempRiseBallList = new ArrayList();
    private IntBuffer mTmpIndexBuffer;
    private FloatBuffer mTmpVertexBuffer;
    public float[] mTopCentre;
    private List<Integer> mTopIndexlist = new ArrayList();
    public float[] mTopR;
    private float[] mTopRectCentre;
    private float mTopRectoffsetX;
    private List<Float> mToplist = new ArrayList();
    private float[] mVertexArray;
    BufferInfo mVertexBufferInfo;
    private List<Float> mVertexList = new ArrayList();
    private float mdRr1;
    private float mdRr2;
    private int minR;
    private int radomR;

    class C36131 implements Runnable {
        int startTime = 0;

        C36131() {
        }

        public void run() {
            while (this.startTime <= 1000) {
                GLRiseBallUI.this.mTopR[1] = (
                        GLRiseBallUI.this.mOrignalTopR[1] * ((float) this.startTime)) / 1000.0f;
                GLRiseBallUI.this.mTopR[2] = (
                        GLRiseBallUI.this.mOrignalTopR[2] * ((float) this.startTime)) / 1000.0f;
                this.startTime += 16;
                GLRiseBallUI.this.mGlRiseBallFragmentShader.setTopBallAttr(
                        GLRiseBallUI.this.mTopCentre, GLRiseBallUI.this.mTopR);
                try {
                    Thread.sleep(16);
                } catch (InterruptedException e) {

                }
            }
        }
    }

    public GLRiseBallUI(float halfScreenCentreX, float halfScreenCentreY,
                        List<RiseBall> riseBalls, GLRiseBallUIAttrs attrs) {
        setScaleY(-1.0d);
        this.minR = UIConstant.getMinRadius()[2];
        this.radomR = UIConstant.getRandomRadius()[2];
        this.mHalfScreenCentreY = halfScreenCentreY;
        this.color = attrs.getColor();
        synchronized (this) {
            this.mRiseBalls = riseBalls;
        }
        this.mTopRectCentre = attrs.getTopCircleCentre();
        this.mTopRectoffsetX = attrs.getTopTriangleOffsetX();
        this.mBottomBallCentre = attrs.getBottomBallCentre();
        this.mBottomBallR = attrs.getBottomBallR();
        this.mTopCentre = attrs.getTopCentre();
        this.mOrignalTopR = attrs.getTopR();
        this.mTopR = new float[]{this.mOrignalTopR[0], this.mOrignalTopR[1], this.mOrignalTopR[2]};
        this.mAngle = attrs.getAngle();
        this.mAngleSpeed = attrs.getAngleSpeed();
        this.mOragnicAngleSpeed = this.mAngleSpeed;
        this.mdRr1 = attrs.getdRr1();
        this.mdRr2 = attrs.getdRr2();
        this.mAngleR1 = attrs.getAngleR1();
        this.mAngleR2 = attrs.getAngleR2();
        this.mCentre = new float[(riseBalls.size() * 2)];
        this.f3596mR = new float[riseBalls.size()];
        initVertexTop();
        initcreateBallVertex();
        loadModel();
        loadMaterial();
        initAnimation();
        //setModelMatrixEnabled(false);
        //setModelViewMatrixEnabled(false);
        //setInverseViewMatrixEnabled(false);
    }

    private void initAnimation() {
        new Thread(new C36131()).start();
    }

    private void initVertex() {
        synchronized (this) {
            this.mVertexList.clear();
            this.mIndexList.clear();
            this.mVertexList.addAll(this.mToplist);
            this.mIndexList.addAll(this.mTopIndexlist);
        }
        initRiseBallVertex_ext();
        this.mRBuffer = MyGLUtils.addArrayToFloatBuffer(this.f3596mR);
        this.mCentreBuffer = MyGLUtils.addArrayToFloatBuffer(this.mCentre);
    }

    private void initcreateBallVertex() {
        synchronized (this) {
            this.mVertexList.clear();
            this.mIndexList.clear();

            this.mVertexList.addAll(this.mToplist);
            this.mIndexList.addAll(this.mTopIndexlist);
        }
        //initRiseBallVertex2();
        initRiseBallVertexFinal();
        this.mRBuffer = MyGLUtils.addArrayToFloatBuffer(f3596mR);
        this.mCentreBuffer = MyGLUtils.addArrayToFloatBuffer(mCentre);
    }

    private void initRiseBallVertex_ext() {
        int index2 = this.mTopIndexlist.size();
        float mSize = 0.0f;
        int rCount = 0;
        int cCount = 0;
        synchronized (this) {
            mBallCount = 0;
            for (RiseBall riseBall : mRiseBalls) {
                mBallCount++;

                f3596mR[rCount] = riseBall.getR();
                mCentre[cCount] = riseBall.getCentreX();
                mCentre[cCount + 1] = riseBall.getCentreY();
                cCount += 2;

                mSize = riseBall.getR() * 1.4f;

                this.mVertexList.add(riseBall.getCentreX() - mSize);
                this.mVertexList.add(riseBall.getCentreY() - mSize);
                this.mVertexList.add(this.mTopRectCentre[2]);
                this.mIndexList.add(index2);

                this.mVertexList.add(riseBall.getCentreX() - mSize);
                this.mVertexList.add(riseBall.getCentreY() + mSize);
                this.mVertexList.add(this.mTopRectCentre[2]);
                this.mIndexList.add(index2 + 1);

                this.mVertexList.add(riseBall.getCentreX() + mSize);
                this.mVertexList.add(riseBall.getCentreY() - mSize);
                this.mVertexList.add(this.mTopRectCentre[2]);
                this.mIndexList.add(index2 + 2);

                this.mVertexList.add(riseBall.getCentreX() - mSize);
                this.mVertexList.add(riseBall.getCentreY() + mSize);
                this.mVertexList.add(this.mTopRectCentre[2]);
                this.mIndexList.add(index2 + 3);

                this.mVertexList.add(riseBall.getCentreX() + mSize);
                this.mVertexList.add(riseBall.getCentreY() + mSize);
                this.mVertexList.add(this.mTopRectCentre[2]);
                this.mIndexList.add(index2 + 4);

                this.mVertexList.add(riseBall.getCentreX() + mSize);
                this.mVertexList.add(riseBall.getCentreY() - mSize);
                this.mVertexList.add(this.mTopRectCentre[2]);
                this.mIndexList.add(index2 + 5);

                rCount += 1;
                index2 = index2 + 6;
            }
        }
    }

    private void initRiseBallVertex() {
        float mSize = 0.0f;
        int index = 0;
        int rCount = 0;
        int cCount = 0;
        int index2 = this.mTopIndexlist.size();
        synchronized (this) {
            try {
                this.mBallCount = 0;
                for (RiseBall riseBall : this.mRiseBalls) {
                    this.mBallCount++;
                    int rCount2 = rCount + 1;
                    try {
                        this.f3596mR[rCount] = riseBall.getR();
                        int cCount2 = cCount + 1;
                        try {
                            this.mCentre[cCount] = riseBall.getCentreX();
                            cCount = cCount2 + 1;
                            this.mCentre[cCount2] = riseBall.getCentreY();
                            mSize = riseBall.getR() * 1.4f;
                            this.mVertexList.add(riseBall.getCentreX() - mSize);
                            this.mVertexList.add(riseBall.getCentreY() - mSize);
                            this.mVertexList.add(this.mTopRectCentre[2]);
                            index = index2 + 1;
                        } catch (Exception th) {
                            //int i = cCount2;
                        }
                        try {
                            this.mIndexList.add(index2);
                            this.mVertexList.add(riseBall.getCentreX() - mSize);
                            this.mVertexList.add(riseBall.getCentreY() + mSize);
                            this.mVertexList.add(this.mTopRectCentre[2]);
                            int index3 = index + 1;

                            try {
                                this.mIndexList.add(index);
                                this.mVertexList.add(riseBall.getCentreX() + mSize);
                                this.mVertexList.add(riseBall.getCentreY() - mSize);
                                this.mVertexList.add(this.mTopRectCentre[2]);
                                index = index3 + 1;
                                this.mIndexList.add(index3);
                                this.mVertexList.add(riseBall.getCentreX() - mSize);
                                this.mVertexList.add(riseBall.getCentreY() + mSize);
                                this.mVertexList.add(this.mTopRectCentre[2]);
                                index3 = index + 1;
                                this.mIndexList.add(index);
                                this.mVertexList.add(riseBall.getCentreX() + mSize);
                                this.mVertexList.add(riseBall.getCentreY() + mSize);
                                this.mVertexList.add(this.mTopRectCentre[2]);
                                index = index3 + 1;
                                this.mIndexList.add(index3);
                                this.mVertexList.add(riseBall.getCentreX() + mSize);
                                this.mVertexList.add(riseBall.getCentreY() - mSize);
                                this.mVertexList.add(this.mTopRectCentre[2]);
                                index3 = index + 1;
                                this.mIndexList.add(index);
                                index2 = index3;
                                rCount = rCount2;
                            } catch (Exception th2) {
                                //int i2 = index3;
                            }
                        } catch (Exception th3) {
                            //int i3 = index;
                        }
                    } catch (Exception th4) {

                    }
                }
            } catch (Exception th5) {
                //int i4 = rCount;
            }
        }
    }

    /* JADX WARNING: Missing block: B:31:0x0192, code skipped:
            r8.f3596mR = new float[r8.mRList.size()];
     */
    /* JADX WARNING: Missing block: B:33:0x01a0, code skipped:
            if (r1 >= r8.f3596mR.length) goto L_0x01b5;
     */
    /* JADX WARNING: Missing block: B:34:0x01a2, code skipped:
            r8.f3596mR[r1] = ((java.lang.Float) r8.mRList.get(r1)).floatValue();
            r1 = r1 + 1;
     */
    /* JADX WARNING: Missing block: B:35:0x01b5, code skipped:
            r8.mRList.clear();
            r8.mCentre = new float[r8.mCentreList.size()];
            r1 = 0;
            r2 = r8.mCentreList.size();
     */
    /* JADX WARNING: Missing block: B:36:0x01cb, code skipped:
            if (r1 >= r2) goto L_0x01e0;
     */
    /* JADX WARNING: Missing block: B:37:0x01cd, code skipped:
            r8.mCentre[r1] = ((java.lang.Float) r8.mCentreList.get(r1)).floatValue();
            r1 = r1 + 1;
     */
    /* JADX WARNING: Missing block: B:38:0x01e0, code skipped:
            r8.mCentreList.clear();
     */
    /* JADX WARNING: Missing block: B:39:0x01e5, code skipped:
            return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void initRiseBallVertex2() {
        Throwable index;
        int index2 = this.mTopIndexlist.size();
        synchronized (this) {
            int i = 0;
            int index3;
            this.mBallCount = 0;
            mCentreList.clear();
            mRList.clear();
            for (RiseBall riseBall : this.mRiseBalls) {
                this.mBallCount++;

                this.mCentreList.add(riseBall.getCentreX());
                this.mCentreList.add(riseBall.getCentreY());
                this.mRList.add(riseBall.getR());

                float mSize = riseBall.getR() * 1.4f;

                this.mVertexList.add(riseBall.getCentreX() - mSize);
                this.mVertexList.add(riseBall.getCentreY() - mSize);
                this.mVertexList.add(this.mTopRectCentre[2]);
                index3 = index2 + 1;
                this.mIndexList.add(index2);
                this.mVertexList.add(riseBall.getCentreX() - mSize);
                this.mVertexList.add(riseBall.getCentreY() + mSize);
                this.mVertexList.add(this.mTopRectCentre[2]);
                int index4 = index3 + 1;
                this.mIndexList.add(index3);
                this.mVertexList.add(riseBall.getCentreX() + mSize);
                this.mVertexList.add(riseBall.getCentreY() - mSize);
                this.mVertexList.add(this.mTopRectCentre[2]);
                index3 = index4 + 1;
                this.mIndexList.add(index4);
                this.mVertexList.add(riseBall.getCentreX() - mSize);
                this.mVertexList.add(riseBall.getCentreY() + mSize);
                this.mVertexList.add(this.mTopRectCentre[2]);
                index4 = index3 + 1;
                this.mIndexList.add(index3);
                this.mVertexList.add(riseBall.getCentreX() + mSize);
                this.mVertexList.add(riseBall.getCentreY() + mSize);
                this.mVertexList.add(this.mTopRectCentre[2]);
                index3 = index4 + 1;
                this.mIndexList.add(index4);
                this.mVertexList.add(riseBall.getCentreX() + mSize);
                this.mVertexList.add(riseBall.getCentreY() - mSize);
                this.mVertexList.add(this.mTopRectCentre[2]);
                index4 = index3 + 1;
                this.mIndexList.add(index3);
                index2 = index4;
            }
            f3596mR = new float[mRList.size()];
            mCentre = new float[mCentreList.size()];

            try {
                for (int j = 0; i < mRList.size(); j++) {
                    f3596mR[j] = mRList.get(j);
                }
                for (int k = 0; i < mCentreList.size(); k++) {
                    mCentre[k] = mCentreList.get(k);
                }
            } catch (Exception e) {

            }
        }
    }

    private void initRiseBallVertexFinal() {
        int index2 = mTopIndexlist.size();
        synchronized (this) {
            mBallCount = 0;
            mCentreList.clear();
            mRList.clear();
            for (RiseBall riseBall : this.mRiseBalls) {

                mCentreList.add(riseBall.getCentreX());
                mCentreList.add(riseBall.getCentreY());
                mRList.add(riseBall.getR());

                float mSize = riseBall.getR() * 1.4f;

                mVertexList.add(riseBall.getCentreX() - mSize);
                mVertexList.add(riseBall.getCentreY() - mSize);
                mVertexList.add(this.mTopRectCentre[2]);
                mIndexList.add(index2);

                mVertexList.add(riseBall.getCentreX() - mSize);
                mVertexList.add(riseBall.getCentreY() + mSize);
                mVertexList.add(this.mTopRectCentre[2]);
                mIndexList.add(index2 + 1);

                mVertexList.add(riseBall.getCentreX() + mSize);
                mVertexList.add(riseBall.getCentreY() - mSize);
                mVertexList.add(this.mTopRectCentre[2]);
                mIndexList.add(index2 + 2);

                mVertexList.add(riseBall.getCentreX() - mSize);
                mVertexList.add(riseBall.getCentreY() + mSize);
                mVertexList.add(this.mTopRectCentre[2]);
                mIndexList.add(index2 + 3);

                mVertexList.add(riseBall.getCentreX() + mSize);
                mVertexList.add(riseBall.getCentreY() + mSize);
                mVertexList.add(this.mTopRectCentre[2]);
                mIndexList.add(index2 + 4);

                mVertexList.add(riseBall.getCentreX() + mSize);
                mVertexList.add(riseBall.getCentreY() - mSize);
                mVertexList.add(this.mTopRectCentre[2]);
                mIndexList.add(index2 + 5);

                index2 = index2 + 6;
            }

            Log.e("zwb", " mRList.size " + mRList.size());
            Log.e("zwb", " mCentreList.size " + mCentreList.size());
            f3596mR = new float[mRList.size()];
            mCentre = new float[mCentreList.size()];
        }
    }

    private void initVertexTop() {
        int index;
        int index2;
        float angleSpan = (360.0f - 0.0f) / ((float) 90);
        int index3 = 0;
        float angdeg = 0.0f;
        while (angdeg < 360.0f) {
            double angrad = Math.toRadians((double) angdeg);
            this.mToplist.add((float) (((double) this.mTopRectCentre[0]) - (((double) this.mTopRectoffsetX) * Math.sin(angrad))));
            this.mToplist.add((float) (((double) this.mTopRectCentre[1]) + (((double) this.mTopRectoffsetX) * Math.cos(angrad))));
            this.mToplist.add(this.mTopRectCentre[2]);
            index = index3 + 1;
            this.mTopIndexlist.add(index3);
            this.mToplist.add(this.mTopRectCentre[0]);
            this.mToplist.add(this.mTopRectCentre[1]);
            this.mToplist.add(this.mTopRectCentre[2]);
            index2 = index + 1;
            this.mTopIndexlist.add(index);
            angdeg += angleSpan;
            double angrad2 = Math.toRadians((double) angdeg);
            this.mToplist.add((float) (((double) this.mTopRectCentre[0]) - (((double) this.mTopRectoffsetX) * Math.sin(angrad2))));
            this.mToplist.add((float) (((double) this.mTopRectCentre[1]) + (((double) this.mTopRectoffsetX) * Math.cos(angrad2))));
            this.mToplist.add(this.mTopRectCentre[2]);
            int index4 = index2 + 1;
            this.mTopIndexlist.add(index2);
            index3 = index4;
        }
        angdeg = this.mBottomBallR * 4.0f;

        this.mToplist.add(this.mBottomBallCentre[0] - angdeg);
        this.mToplist.add(this.mBottomBallCentre[1] - angdeg);
        this.mToplist.add(this.mTopRectCentre[2]);
        index = index3 + 1;
        this.mTopIndexlist.add(index3);

        this.mToplist.add(this.mBottomBallCentre[0] - angdeg);
        this.mToplist.add(this.mBottomBallCentre[1]);
        this.mToplist.add(this.mTopRectCentre[2]);
        index2 = index + 1;
        this.mTopIndexlist.add(index);

        this.mToplist.add(this.mBottomBallCentre[0] + angdeg);
        this.mToplist.add(this.mBottomBallCentre[1] - angdeg);
        this.mToplist.add(this.mTopRectCentre[2]);
        index = index2 + 1;
        this.mTopIndexlist.add(index2);

        this.mToplist.add(this.mBottomBallCentre[0] - angdeg);
        this.mToplist.add(this.mBottomBallCentre[1]);
        this.mToplist.add(this.mTopRectCentre[2]);
        index2 = index + 1;
        this.mTopIndexlist.add(index);

        this.mToplist.add(this.mBottomBallCentre[0] + angdeg);
        this.mToplist.add(this.mBottomBallCentre[1]);
        this.mToplist.add(this.mTopRectCentre[2]);
        index = index2 + 1;
        this.mTopIndexlist.add(index2);

        this.mToplist.add(this.mBottomBallCentre[0] + angdeg);
        this.mToplist.add(this.mBottomBallCentre[1] - angdeg);
        this.mToplist.add(this.mTopRectCentre[2]);
        index2 = index + 1;
        this.mTopIndexlist.add(index);
    }

    private void loadModel() {
        setTransparent(true);
        setBlendFunc(1, 771);
        setDepthTestEnabled(true);
        setDepthMaskEnabled(true);
        //setDepthTestFunc(513);
        synchronized (this) {
            int i;
            this.mVertexArray = new float[this.mVertexList.size()];
            int size = this.mVertexList.size();
            for (i = 0; i < size; i++) {
                this.mVertexArray[i] = (Float) this.mVertexList.get(i);
            }
            this.mIndexArray = new int[this.mIndexList.size()];
            size = this.mIndexList.size();
            for (i = 0; i < size; i++) {
                this.mIndexArray[i] = (Integer) this.mIndexList.get(i);
            }
        }
        setData(this.mVertexArray, null, null, null, this.mIndexArray, true);
        this.mVertexBufferInfo = getGeometry().getVertexBufferInfo();
        this.mIndexBufferInfo = getGeometry().getIndexBufferInfo();
        getGeometry().setNumIndices(this.mIndexArray.length);
        getGeometry().changeBufferUsage(this.mVertexBufferInfo, 35048);
        getGeometry().changeBufferUsage(this.mIndexBufferInfo, 35048);
        this.mTmpVertexBuffer = ByteBuffer.allocateDirect(this.mVertexArray.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.mTmpIndexBuffer = ByteBuffer.allocateDirect(this.mIndexArray.length * 4).order(ByteOrder.nativeOrder()).asIntBuffer();
    }

    public void addToScene(Scene scene) {
        scene.addChild(this);
    }

    private void loadMaterial() {
        Log.e("zwbbb", " loadMaterial ");
        this.mGlRiseBallVertexShader = new GLRiseBallVertexShader();
        this.mGlRiseBallFragmentShader = new GLRiseBallFragmentShader();
        this.mGlRiseBallFragmentShader.setColor(this.color);
        synchronized (this) {
            this.mGlRiseBallFragmentShader.setBallCount(this.mBallCount);
        }
        this.mGlRiseBallFragmentShader.setRiseBallAttr(this.mCentreBuffer, this.mRBuffer);
        this.mGlRiseBallFragmentShader.setTopBallAttr(this.mTopCentre, this.mTopR);
        this.mGlRiseBallFragmentShader.setBottomBallAttr(this.mBottomBallCentre, this.mBottomBallR);
        this.mMaterial = new Material(this.mGlRiseBallVertexShader, this.mGlRiseBallFragmentShader);
        setMaterial(this.mMaterial);
    }

    public void updateModel(int drawCount) {
        if (((float) drawCount) % UIConstant.getCreateBallSpeed() == 0.0f) {
            synchronized (this) {
                this.mTempRiseBallList.add(
                        RiseBall.createRiseBall(0.0f,
                                this.mHalfScreenCentreY, this.minR, this.radomR));
            }
            updateRiseBallLocation(drawCount);
            initcreateBallVertex();
        } else {
            updateRiseBallLocation(drawCount);
            initVertex();
        }
        synchronized (this) {
            this.mGlRiseBallFragmentShader.setBallCount(this.mBallCount);
        }
        this.mGlRiseBallFragmentShader.setRiseBallAttr(this.mCentreBuffer, this.mRBuffer);
        this.mGlRiseBallFragmentShader.setTopBallAttr(this.mTopCentre, this.mTopR);
        synchronized (this) {
            int i;
            this.mVertexArray = new float[this.mVertexList.size()];
            int size = this.mVertexList.size();
            for (i = 0; i < size; i++) {
                this.mVertexArray[i] = (Float) this.mVertexList.get(i);
            }
            this.mIndexArray = new int[this.mIndexList.size()];
            size = this.mIndexList.size();
            for (i = 0; i < size; i++) {
                this.mIndexArray[i] = (Integer) this.mIndexList.get(i);
            }
        }
        getGeometry().setNumIndices(this.mIndexArray.length);
        this.mTmpVertexBuffer = ByteBuffer.allocateDirect(this.mVertexArray.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.mTmpVertexBuffer.put(this.mVertexArray);
        getGeometry().changeBufferData(this.mVertexBufferInfo, this.mTmpVertexBuffer, 0, true);
        this.mTmpIndexBuffer = ByteBuffer.allocateDirect(this.mIndexArray.length * 4).order(ByteOrder.nativeOrder()).asIntBuffer();
        this.mTmpIndexBuffer.put(this.mIndexArray);
        getGeometry().changeBufferData(this.mIndexBufferInfo, this.mTmpIndexBuffer, 0, true);
    }

    private void updateRiseBallLocation(int drawCount) {
        this.mAngleSpeed = (float) (((double) this.mOragnicAngleSpeed) + Math.sin(Math.toRadians((double) drawCount)));
        this.mAngle += (this.mAngleSpeed * ((UIConstant.getOrganicRotateSpeed() * 1.0f) / 50.0f)) % 360.0f;
        this.mTopCentre[2] = (float) (((double) this.mdRr1) * Math.sin(Math.toRadians((double) (this.mAngle + this.mAngleR1))));
        this.mTopCentre[3] = (float) (((double) this.mdRr1) * Math.cos(Math.toRadians((double) ((this.mAngle + this.mAngleR1) - 180.0f))));
        this.mTopCentre[4] = (float) (((double) this.mdRr2) * Math.sin(Math.toRadians((double) (this.mAngle + this.mAngleR2))));
        this.mTopCentre[5] = (float) (((double) this.mdRr2) * Math.cos(Math.toRadians((double) ((this.mAngle + this.mAngleR2) - 180.0f))));
        synchronized (this) {
            for (RiseBall riseBall : this.mRiseBalls) {
                if (riseBall.centreY >= 0.0f) {
                    riseBall.centreY -= (riseBall.velocityY * 50.0f) * ((UIConstant.getUpSpeed() * 1.0f) / 50.0f);
                    if (riseBall.centreY <= this.mHalfScreenCentreY) {
                        riseBall.centreX += (riseBall.velocityX * 50.0f) * ((UIConstant.getUpSpeed() * 1.0f) / 50.0f);
                    }
                    if (riseBall.f2099R > 0.0f) {
                        if (riseBall.canFuse && riseBall.centreY < UIConstant.getFuseDistance()) {
                            float res = Math.abs((UIConstant.getFuseDistance() * UIConstant.getFuseDistance()) -
                                    (riseBall.centreY * riseBall.centreY));
                            float X1 = ((float) Math.sqrt((double) res)) + 0.0f;
                            float X2 = (-1.0f * ((float) Math.sqrt((double) res))) + 0.0f;
                            if (riseBall.centreX >= 0.0f) {
                                riseBall.centreX = X1;
                            } else {
                                riseBall.centreX = X2;
                            }
                            if (riseBall.f2099R > 0.0f) {
                                riseBall.f2099R -= 5.0f * ((UIConstant.getUpSpeed() * 1.0f) / 50.0f);
                            }
                        }
                        this.mTempRiseBallList.add(riseBall);
                    }
                }
            }
            this.mRiseBalls.clear();
            this.mRiseBalls.addAll(this.mTempRiseBallList);
            this.mTempRiseBallList.clear();
        }
    }

    public static GLRiseBallUI createRiseBallUI(float halfScreenCentreX, float halfScreenCentreY) {
        float f = halfScreenCentreY;
        float R = UIConstant.getTopBallRadius();
        float r1 = 0.5f * R;
        float dRr1 = R - (1.0f * (0.5f * R));
        float r2 = 0.4f * R;
        float dRr2 = R - (1.1f * r2);
        float[] angleArray = UIConstant.getAngleArray();
        float[] angleSpeed = UIConstant.getAngleSpeed();
        int[] minR = UIConstant.getMinRadius();
        int[] randomR = UIConstant.getRandomRadius();
        float[][] colorArray = UIConstant.getColorArray();
        int[] zArray = UIConstant.getZArray();
        List<RiseBall> riseballs = new ArrayList();
        GLRiseBallUIAttrs attrs = new GLRiseBallUIAttrs();
        float[] fArr = new float[3];
        fArr[0] = 0.0f;
        fArr[1] = 0.0f;
        fArr[2] = (float) zArray[2];
        attrs.setTopCircleCentre(fArr);
        attrs.setTopTriangleOffsetX(UIConstant.getTopRectLength());
        attrs.setTopTriangleOffsetY(UIConstant.getTopRectLength());
        attrs.setBottomOffsetX(UIConstant.getBottomOffsetX());
        attrs.setBottomBallCentre(new float[]{0.0f, ((UIConstant.getBottomRadius() * 2.0f) + f) + 10.0f});
        attrs.setBottomBallR(UIConstant.getBottomRadius() * 2.0f);
        fArr = new float[6];
        fArr[0] = 0.0f;
        fArr[1] = 0.0f;
        GLRiseBallUIAttrs attrs2 = attrs;
        float[][] colorArray2 = colorArray;
        fArr[2] = (float) (((double) dRr1) * Math.sin(Math.toRadians((double) (angleArray[2] + 20.0f))));
        float dRr12 = dRr1;
        fArr[3] = (float) (((double) dRr1) * Math.cos(Math.toRadians((double) ((angleArray[2] - 0.024902344f) + 20.0f))));
        fArr[4] = (float) (((double) dRr2) * Math.sin(Math.toRadians((double) (angleArray[2] - 0.03515625f))));
        fArr[5] = (float) (((double) dRr2) * Math.cos(Math.toRadians((double) ((-180.0f + angleArray[2]) - 0.03515625f))));
        GLRiseBallUIAttrs attrs3 = attrs2;
        attrs3.setTopCentre(fArr);
        attrs3.setTopR(new float[]{R, r1, r2});
        attrs3.setColor(colorArray2[2]);
        attrs3.setAngle(angleArray[2]);
        attrs3.setAngleSpeed(angleSpeed[2]);
        attrs3.setRandomR(new int[]{minR[2], randomR[2]});
        attrs3.setdRr1(dRr12);
        attrs3.setdRr2(dRr2);
        attrs3.setAngleR1(20.0f);
        attrs3.setAngleR2(-120.0f);
        return new GLRiseBallUI(halfScreenCentreX, f, riseballs, attrs3);
    }

    public void clearMRiseBalls() {
        synchronized (this) {
            this.mRiseBalls.clear();
        }
    }

    public void updateColor() {
        this.color = UIConstant.getColorArray()[2];
        this.mGlRiseBallFragmentShader.setColor(this.color);
    }
}
