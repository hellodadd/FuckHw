package com.example.fuckhw;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;

import com.example.fuckhw.ui.GLBottomUI;
import com.example.fuckhw.ui.GLOrganicUI;
import com.example.fuckhw.ui.GLRiseBallUI;

import org.rajawali3d.cameras.Camera;
import org.rajawali3d.math.vector.Vector3;
import org.rajawali3d.renderer.Renderer;

import java.util.List;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MainRenderer extends Renderer {
    private int mDrawCount = 0;
    private List<GLOrganicUI> mGLOrganicList;
    private GLBottomUI mGlBottomUI;
    private GLRiseBallUI mGlRiseBallUI;
    private int mScreenHeight;
    private int mScreenWidth;

    public MainRenderer(Context context) {
        super(context);
    }

    /* Access modifiers changed, original: protected */
    public void initScene() {
        if (UIConstant.getChangeEntranceLocation() == 0 || UIConstant.getChangeEntranceLocation() == 2) {
            if (UIConstant.getRotateAngle() == -90.0f || UIConstant.getRotateAngle() == -270.0f) {
                this.mScreenWidth = getViewportHeight();
                this.mScreenHeight = getViewportWidth();
            } else {
                this.mScreenWidth = getViewportWidth();
                this.mScreenHeight = getViewportHeight();
            }
        } else if (UIConstant.getChangeEntranceLocation() == 3 || UIConstant.getChangeEntranceLocation() == 1) {
            if (UIConstant.getRotateAngle() == -90.0f || UIConstant.getRotateAngle() == -270.0f) {
                this.mScreenWidth = getViewportWidth();
                this.mScreenHeight = getViewportHeight();
            } else {
                this.mScreenWidth = getViewportHeight();
                this.mScreenHeight = getViewportWidth();
            }
        }
        Log.e("zwb", " initScene " + mScreenWidth + " --- " + mScreenHeight);
        //UIConstant.updateConstant(((float)
               // max(this.mScreenWidth, this.mScreenHeight)) / 1920.0f);
        UIConstant.updateConstant(1.0f);
        int viewWidth = getViewportWidth();
        int viewHeight = getViewportHeight();
        float distance = (float) (((double) (((float) viewHeight) / 2.0f)) / Math.tan(Math.toRadians(12.5d)));
        Camera camera = getCurrentCamera();
        camera.setProjectionMatrix(25.0d, viewWidth, viewHeight);
        camera.setNearPlane(((double) distance) * 0.5d);
        camera.setFarPlane(((double) distance) * 1.5d);
        camera.enableLookAt();
        camera.setLookAt(0.0d, 0.0d, -1.0d);
        camera.setUpAxis(Vector3.Y);
        camera.setPosition(0.0d, 0.0d, (double) distance);
        initGLOrganicUI();
        initGLRiseBallUI();
        initGLBottomUI();
    }

    private void initGLOrganicUI() {
        this.mGLOrganicList = GLOrganicUI.createOrganicUILayer();
        for (GLOrganicUI ui : this.mGLOrganicList) {
            ui.rotate(Vector3.Axis.Z, (double) UIConstant.getRotateAngle());
            ui.rotate(Vector3.Axis.Z, (double) UIConstant.getChargeEnatranceAngle());
            ui.addToScene(getCurrentScene());
        }
    }

    public void removeAllView() {
        getCurrentScene().removeChild(this.mGlBottomUI);
        if (this.mGlRiseBallUI != null) {
            getCurrentScene().removeChild(this.mGlRiseBallUI);
            this.mGlRiseBallUI.clearMRiseBalls();
        }
        if (this.mGLOrganicList != null) {
            for (GLOrganicUI ui : this.mGLOrganicList) {
                getCurrentScene().removeChild(ui);
            }
        }
    }

    private void initGLRiseBallUI() {
        this.mGlRiseBallUI = GLRiseBallUI.createRiseBallUI(
                ((float) this.mScreenWidth) / 2.0f, ((float) this.mScreenHeight) / 2.0f);
        this.mGlRiseBallUI.rotate(Vector3.Axis.Z, (double) UIConstant.getRotateAngle());
        this.mGlRiseBallUI.rotate(Vector3.Axis.Z, (double) UIConstant.getChargeEnatranceAngle());
        this.mGlRiseBallUI.addToScene(getCurrentScene());
    }

    private void initGLBottomUI() {
        this.mGlBottomUI = new GLBottomUI(new float[]{0.0f, (((float) this.mScreenHeight) / 2.0f) -
                UIConstant.getBottomRadius(), 4.0f}, UIConstant.getBottomOffsetX(), UIConstant.getBottomRadius());
        this.mGlBottomUI.rotate(Vector3.Axis.Z, (double) UIConstant.getRotateAngle());
        this.mGlBottomUI.rotate(Vector3.Axis.Z, (double) UIConstant.getChargeEnatranceAngle());
        this.mGlBottomUI.addToScene(getCurrentScene());
    }

    /* Access modifiers changed, original: protected */
    public void onRender(long ellapsedRealtime, double deltaTime) {
        super.onRender(ellapsedRealtime, deltaTime);
        this.mDrawCount++;
        this.mGlRiseBallUI.updateModel(this.mDrawCount);
        if (this.mGLOrganicList != null) {
            for (GLOrganicUI ui : this.mGLOrganicList) {
                ui.updateModel(this.mDrawCount);
            }
        }
    }

    public void onRenderSurfaceCreated(EGLConfig config, GL10 gl, int width, int height) {
        super.onRenderSurfaceCreated(config, gl, width, height);
    }

    public void onOffsetsChanged(float v, float v1, float v2, float v3, int i, int i1) {
    }

    public void onTouchEvent(MotionEvent motionEvent) {
    }

    /* Access modifiers changed, original: protected */
    public void updateColor() {
        if (this.mGlBottomUI != null) {
            this.mGlBottomUI.updateColor();
        }
        if (this.mGlRiseBallUI != null) {
            this.mGlRiseBallUI.updateColor();
        }
        if (this.mGLOrganicList != null && this.mGLOrganicList.size() > 0) {
            int size = this.mGLOrganicList.size();
            for (int i = 0; i < size; i++) {
                ((GLOrganicUI) this.mGLOrganicList.get(i)).updateColor(i);
            }
        }
    }

    public long max(long item, long item1) {
        return item > item1 ? item : item1;
    }
}
