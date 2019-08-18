package com.example.fuckhw;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import org.rajawali3d.util.OnFPSUpdateListener;
import org.rajawali3d.view.SurfaceView;

public class FastChargeSurfaceView extends SurfaceView {
    private static final String TAG = FastChargeSurfaceView.class.getSimpleName();
    private float fps;
    private int mBatteryScene;
    private int mCloseViewRunTime;
    public MainRenderer mRenderer;
    public SurfaceView mRenderview;

    class C35571 implements OnFPSUpdateListener {
        C35571() {
        }

        public void onFPSUpdate(double v) {
            FastChargeSurfaceView.this.fps =
                    (float) ((((double) FastChargeSurfaceView.this.fps) + v) / 2.0d);
            String access$100 = FastChargeSurfaceView.TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("fps:");
            stringBuilder.append(FastChargeSurfaceView.this.fps);
            Log.i(access$100, stringBuilder.toString());
        }
    }

    public FastChargeSurfaceView(Context context) {
        this(context, null);
    }

    public FastChargeSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mCloseViewRunTime = -1;
        this.fps = 60.0f;
        setRenderMode(1);
        this.mRenderview = this;
        this.mRenderer = new MainRenderer(context);
        openMultisampling();
        initParams();
        initLocation();
        statisticalFps();
    }

    private void openMultisampling() {
        //mRenderview.setAntiAliasingMode(ANTI_ALIASING_CONFIG.MULTISAMPLING);
        this.mRenderview.setSampleCount(4);
        this.mRenderview.setTransparent(true);
        this.mRenderview.setSurfaceRenderer(this.mRenderer);
    }

    private void statisticalFps() {
        this.mRenderer.setFPSUpdateListener(new C35571());
    }

    public void setChargeType(int type) {
        UIConstant.updateChargeScene(type);
    }

    public void setEntranceLocation(int location) {
        UIConstant.setChargeEntranceLocation(location);
    }

    public void setBatteryLevel(float batteryLevel) {
        int tmpBatteryScene;
        String str;
        StringBuilder stringBuilder;
        if (batteryLevel >= 0.0f && batteryLevel < 11.0f) {
            tmpBatteryScene = 1;
        } else if (batteryLevel < 11.0f || batteryLevel >= 21.0f) {
            tmpBatteryScene = 3;
        } else {
            tmpBatteryScene = 2;
        }
        if (tmpBatteryScene != this.mBatteryScene) {
            this.mBatteryScene = tmpBatteryScene;
            UIConstant.updateBatteryScene(this.mBatteryScene);
            this.mRenderer.updateColor();
            str = TAG;
            stringBuilder = new StringBuilder();
            stringBuilder.append("mRenderer.updateColor() ");
            stringBuilder.append(this.mBatteryScene);
            Log.i("zwb", stringBuilder.toString());
        }
        UIConstant.updateBallRadiusByBattery((1.0f * batteryLevel) / 100.0f);
        str = TAG;
        stringBuilder = new StringBuilder();
        stringBuilder.append("setBatteryLevel to ");
        stringBuilder.append(batteryLevel);
        Log.i(str, stringBuilder.toString());
    }

    public void removeAllView() {
        this.mRenderer.removeAllView();
    }

    public void onDestroy() {
        removeAllView();
    }

    public void setAlpha(float alpha) {
        UIConstant.updateAlpha(alpha);
    }

    private void initParams() {
        UIConstant.initParams();
    }

    private void initLocation() {

    }
}
