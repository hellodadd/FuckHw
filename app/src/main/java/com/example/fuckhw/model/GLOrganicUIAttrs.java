package com.example.fuckhw.model;

import java.util.Arrays;

public class GLOrganicUIAttrs {
    private float angle;
    private float angleSpeed;
    private float[] color;
    private float dRr;
    private int layer;
    private int[] randomR;
    private float[] topCentre;
    private float[] topCircleCentre;
    private float[] topR;
    private float topTriangleOffsetX;
    private float topTriangleOffsetY;

    public int getLayer() {
        return this.layer;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    public float getdRr() {
        return this.dRr;
    }

    public void setdRr(float dR) {
        this.dRr = dR;
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
