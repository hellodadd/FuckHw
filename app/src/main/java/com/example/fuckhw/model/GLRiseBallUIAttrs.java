package com.example.fuckhw.model;

import java.util.Arrays;

public class GLRiseBallUIAttrs {
    private float angle;
    private float angleR1;
    private float angleR2;
    private float angleSpeed;
    private float[] bottomBallCentre;
    private float bottomBallR;
    private float bottomOffsetX;
    private float[] color;
    private float dRr1;
    private float dRr2;
    private int[] randomR;
    private float[] topCentre;
    private float[] topCircleCentre;
    private float[] topR;
    private float topTriangleOffsetX;
    private float topTriangleOffsetY;

    public float getAngleR1() {
        return this.angleR1;
    }

    public void setAngleR1(float angleR1) {
        this.angleR1 = angleR1;
    }

    public float getAngleR2() {
        return this.angleR2;
    }

    public void setAngleR2(float angleR2) {
        this.angleR2 = angleR2;
    }

    public float getdRr1() {
        return this.dRr1;
    }

    public void setdRr1(float dRr1) {
        this.dRr1 = dRr1;
    }

    public float getdRr2() {
        return this.dRr2;
    }

    public void setdRr2(float dRr2) {
        this.dRr2 = dRr2;
    }

    public float[] getColor() {
        return Arrays.copyOf(this.color, this.color.length);
    }

    public void setColor(float[] color) {
        this.color = Arrays.copyOf(color, color.length);
    }

    public float getAngle() {
        return this.angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public float getAngleSpeed() {
        return this.angleSpeed;
    }

    public void setAngleSpeed(float angleSpeed) {
        this.angleSpeed = angleSpeed;
    }

    public void setRandomR(int[] randomR) {
        this.randomR = Arrays.copyOf(randomR, randomR.length);
    }

    public float[] getTopCircleCentre() {
        return Arrays.copyOf(this.topCircleCentre, this.topCircleCentre.length);
    }

    public void setTopCircleCentre(float[] topCircleCentre) {
        this.topCircleCentre = Arrays.copyOf(topCircleCentre, topCircleCentre.length);
    }

    public float getTopTriangleOffsetX() {
        return this.topTriangleOffsetX;
    }

    public void setTopTriangleOffsetX(float topTriangleOffsetX) {
        this.topTriangleOffsetX = topTriangleOffsetX;
    }

    public void setTopTriangleOffsetY(float topTriangleOffsetY) {
        this.topTriangleOffsetY = topTriangleOffsetY;
    }

    public void setBottomOffsetX(float bottomOffsetX) {
        this.bottomOffsetX = bottomOffsetX;
    }

    public float[] getBottomBallCentre() {
        return Arrays.copyOf(this.bottomBallCentre, this.bottomBallCentre.length);
    }

    public void setBottomBallCentre(float[] bottomBallCentre) {
        this.bottomBallCentre = Arrays.copyOf(bottomBallCentre, bottomBallCentre.length);
    }

    public float getBottomBallR() {
        return this.bottomBallR;
    }

    public void setBottomBallR(float bottomBallR) {
        this.bottomBallR = bottomBallR;
    }

    public float[] getTopCentre() {
        return Arrays.copyOf(this.topCentre, this.topCentre.length);
    }

    public void setTopCentre(float[] topCentre) {
        this.topCentre = Arrays.copyOf(topCentre, topCentre.length);
    }

    public float[] getTopR() {
        return Arrays.copyOf(this.topR, this.topR.length);
    }

    public void setTopR(float[] topR) {
        this.topR = Arrays.copyOf(topR, topR.length);
    }
}
