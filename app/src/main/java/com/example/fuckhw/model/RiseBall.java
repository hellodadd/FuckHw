package com.example.fuckhw.model;

import android.util.Log;

import java.util.Random;

public class RiseBall {
    private static int mNum = 0;
    public float f2099R;
    public boolean canFuse;
    public float centreX;
    public float centreY;
    public float velocityX;
    public float velocityY;

    public RiseBall(float centreX, float centreY, float r, float velocityY, float velocityX, boolean canFuse) {
        this.centreX = centreX;
        this.centreY = centreY;
        this.f2099R = r;
        this.velocityY = velocityY;
        this.velocityX = velocityX;
        this.canFuse = canFuse;
    }

    public float getCentreX() {
        return this.centreX;
    }

    public float getCentreY() {
        return this.centreY;
    }

    public float getR() {
        return this.f2099R;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("RiseBall{, centreX=");
        stringBuilder.append(this.centreX);
        stringBuilder.append(", centreY=");
        stringBuilder.append(this.centreY);
        stringBuilder.append(", R=");
        stringBuilder.append(this.f2099R);
        stringBuilder.append(", velocityY=");
        stringBuilder.append(this.velocityY);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }

    public static RiseBall createRiseBall(float halfscreenX, float halfscreenY, int minR, int randomR) {
        float mCentreX;
        float velocityY2;
        float mCentreX2 = 0.0f;
        float velocityY3 = 0.0f;
        float angle;
        float mCentreX3;
        float velocityY4;
        float f = halfscreenX;
        float mR = (float) (minR + new Random().nextInt(randomR));
        float mCentreX4 = ((float) (((Math.random() - 0.5d) * 2.0d * ((double) (120.0f - 50.0f))) + ((double) f))) * 0.8f;
        float mCentreY = halfscreenY + 50.0f;
        float angle2 = 0.0f;
        mNum = (mNum + 1) % 4;
        if (mNum == 0) {
            float f2 = mCentreX4;
            mCentreX3 = ((float) ((-1.0d * ((double) (120.0f - 50.0f))) + ((double) f))) * 0.8f;
            velocityY4 = (float) (0.44999998807907104d + (Math.random() * 0.5d));
            angle2 = -((float) ((Math.random() + 1.0d) * 7.0d * 0.5d));
        } else {
            mCentreX = mCentreX4;
            if (mNum == 1) {
                mCentreX2 = ((float) ((0.0d * ((double) (120.0f - 50.0f))) + ((double) f))) * 0.8f;
                velocityY3 = (float) (0.3499999940395355d + (Math.random() * 0.5d));
                angle = ((float) (Math.random() - 0.5d)) * 7.0f;
            } else if (mNum == 2) {
                mCentreX3 = ((float) ((((double) (120.0f - 50.0f)) * 1.0d) + ((double) f))) * 0.8f;
                velocityY4 = (float) (0.44999998807907104d + (Math.random() * 0.5d));
                angle2 = (float) ((Math.random() + 1.0d) * 7.0d * 0.5d);
            } else if (mNum == 3) {
                mCentreX2 = ((float) ((0.0d * ((double) (120.0f - 50.0f))) + ((double) f))) * 0.8f;
                velocityY3 = (float) (0.6000000238418579d + (Math.random() * 0.25d));
                angle = ((float) (Math.random() - 0.5d)) * 7.0f * 2.0f;
            } else {
                velocityY2 = 0.0f;
                float angle3 = angle2;
                boolean canFuse2 = false;
                if (angle3 >= -3.5f && ((double) angle3) <= 3.5d) {
                    canFuse2 = true;
                }
                float x = (float) Math.sin(Math.toRadians((double) angle3));
                float y = (float) Math.cos(Math.toRadians((double) angle3));
                float f3 = y;
                float f4 = x;
                RiseBall riseBall = new RiseBall(mCentreX, mCentreY, mR, velocityY2, (x / y) * velocityY2, canFuse2);
                return riseBall;
            }
            mCentreX = mCentreX2;
            velocityY2 = velocityY3;
            float angle32 = angle2;
            boolean canFuse22 = false;
            canFuse22 = true;
            float x2 = (float) Math.sin(Math.toRadians((double) angle32));
            float y2 = (float) Math.cos(Math.toRadians((double) angle32));
            float f32 = y2;
            float f42 = x2;
            RiseBall riseBall2 = new RiseBall(mCentreX, mCentreY, mR, velocityY2, (x2 / y2) * velocityY2, canFuse22);
            return riseBall2;
        }
        mCentreX = mCentreX3;
        velocityY2 = velocityY4;
        float angle322 = angle2;
        boolean canFuse222 = false;
        canFuse222 = true;
        float x22 = (float) Math.sin(Math.toRadians((double) angle322));
        float y22 = (float) Math.cos(Math.toRadians((double) angle322));
        float f322 = y22;
        float f422 = x22;
        RiseBall riseBall22 = new RiseBall(mCentreX, mCentreY, mR, velocityY2, (x22 / y22) * velocityY2, canFuse222);
        return riseBall22;
    }
}
