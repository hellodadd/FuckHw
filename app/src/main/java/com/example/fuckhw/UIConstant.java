package com.example.fuckhw;

import java.util.Arrays;

public class UIConstant {
    private static float ALPHA = 1.0f;
    private static float[] ANGLEARRAY = new float[]{0.0f, 60.0f, 260.0f};
    private static float[] ANGLESPEED = new float[]{5.0f, 5.0f, 5.0f};
    private static float BOTTOMOFFSETX = 120.0f;
    private static float BOTTOMR = 25.0f;
    private static int CHARGE_ENTRANCE_ANGLE = 0;
    private static int CHARGE_ENTRANCE_LOCATION = 0;
    private static float[][] COLORARRAY = ConstantUtils.getHightBattertColorArrays();
    private static float FAST_CAHRGE = 12.0f;
    private static float FAST_CAHRGE_CREATEBALLSPEED = 17.0f;
    private static float CREATEBALL_SPEED = FAST_CAHRGE_CREATEBALLSPEED;
    private static float FUSE_DISTANCE = 300.0f;
    private static float[][] HIGHBATTERY_COLORARRAY = ConstantUtils.getHightBattertColorArrays();
    private static float[][] LOWBATTERY_COLORARRAY = ConstantUtils.getLowBattertColorArrays();
    private static float[][] MIDDELBATTERY_COLORARRAY = ConstantUtils.getMiddleBattertColorArrays();
    private static int[] MINR = new int[]{24, 40, 35};
    private static float ORGANIC_ROTATE_SPEED = FAST_CAHRGE;
    private static float ORIGIN_TOPBALLR = 300.0f;
    private static float ORIGIN_TOPRECTLENGTH = 355.5f;
    private static float ORIGNAL_BOTTOMOFFSETX = 120.0f;
    private static float ORIGNAL_BOTTOMR = 25.0f;
    private static float ORIGNAL_MAX_TOPBALLR = 310.0f;
    private static float ORIGNAL_MAX_TOPRECTLENGTH = 365.5f;
    private static int[] ORIGNAL_MINR = new int[]{24, 40, 50};
    private static float ORIGNAL_MIN_TOPBALLR = 280.0f;
    private static float ORIGNAL_MIN_TOPRECTLENGTH = 335.5f;
    private static float ORIGNAL_PARTICLEAREASEIZ = 25.0f;
    private static float ORIGNAL_PARTICLEMINR = 12.0f;
    private static float ORIGNAL_PARTICLERANDOMR = 10.0f;
    private static int[] ORIGNAL_RANDOMR = new int[]{15, 15, 20};
    private static int[] RANDOMR = new int[]{15, 15, 30};
    private static float ROTATEANGLE = 0.0f;
    private static float STANDARD_CHARGE = 8.0f;
    private static float STANDARD_CHARGE_CREATEBALLSPEED = 30.0f;
    private static float SURPER_FAST_CHARGE = 18.0f;
    private static float SURPER_FAST_CREATEBALLSPEED = 10.0f;
    private static float TOPBALLR = 300.0f;
    private static float TOPRECTLENGTH = 355.5f;
    private static float UP_SPEED = FAST_CAHRGE;
    private static int[] ZARRAY = new int[]{1, 2, 3};

    public static float getRotateAngle() {
        return ROTATEANGLE;
    }

    public static int getChangeEntranceLocation() {
        return CHARGE_ENTRANCE_LOCATION;
    }

    public static int getChargeEnatranceAngle() {
        return CHARGE_ENTRANCE_ANGLE;
    }

    public static void setChargeEntranceLocation(int chargeEntrance) {
        if (chargeEntrance == 0) {
            CHARGE_ENTRANCE_ANGLE = 0;
        } else if (chargeEntrance == 3) {
            CHARGE_ENTRANCE_ANGLE = -90;
        } else if (chargeEntrance == 2) {
            CHARGE_ENTRANCE_ANGLE = -180;
        } else if (chargeEntrance == 1) {
            CHARGE_ENTRANCE_ANGLE = -270;
        }
        CHARGE_ENTRANCE_LOCATION = chargeEntrance;
    }

    public static float getAlpha() {
        return ALPHA;
    }

    public static void updateAlpha(float alpha) {
        ALPHA = alpha;
    }

    public static void updateChargeScene(int scene) {
        if (scene == 0) {
            UP_SPEED = STANDARD_CHARGE;
            CREATEBALL_SPEED = STANDARD_CHARGE_CREATEBALLSPEED;
        } else if (scene == 1) {
            UP_SPEED = FAST_CAHRGE;
            CREATEBALL_SPEED = FAST_CAHRGE_CREATEBALLSPEED;
        } else if (scene == 2) {
            UP_SPEED = SURPER_FAST_CHARGE;
            CREATEBALL_SPEED = SURPER_FAST_CREATEBALLSPEED;
        } else {
            UP_SPEED = STANDARD_CHARGE;
            CREATEBALL_SPEED = STANDARD_CHARGE_CREATEBALLSPEED;
        }
    }

    public static void updateBatteryScene(int scene) {
        if (scene == 1) {
            COLORARRAY = LOWBATTERY_COLORARRAY;
        } else if (scene == 2) {
            COLORARRAY = MIDDELBATTERY_COLORARRAY;
        } else {
            COLORARRAY = HIGHBATTERY_COLORARRAY;
        }
    }

    public static void updateBallRadiusByBattery(float radio) {
        ORIGIN_TOPRECTLENGTH = ORIGNAL_MIN_TOPRECTLENGTH +
                ((ORIGNAL_MAX_TOPRECTLENGTH - ORIGNAL_MIN_TOPRECTLENGTH) * radio);
        ORIGIN_TOPBALLR = ORIGNAL_MIN_TOPBALLR + ((ORIGNAL_MAX_TOPBALLR - ORIGNAL_MIN_TOPBALLR) * radio);
    }

    public static float getFuseDistance() {
        return FUSE_DISTANCE;
    }

    public static float getUpSpeed() {
        return UP_SPEED;
    }

    public static float getCreateBallSpeed() {
        return CREATEBALL_SPEED;
    }

    public static float getOrganicRotateSpeed() {
        return ORGANIC_ROTATE_SPEED;
    }

    public static float getTopRectLength() {
        return TOPRECTLENGTH;
    }

    public static float getTopBallRadius() {
        return TOPBALLR;
    }

    public static float getBottomRadius() {
        return BOTTOMR;
    }

    public static float getBottomOffsetX() {
        return BOTTOMOFFSETX;
    }

    public static float[] getAngleArray() {
        return Arrays.copyOf(ANGLEARRAY, ANGLEARRAY.length);
    }

    public static float[] getAngleSpeed() {
        return Arrays.copyOf(ANGLESPEED, ANGLESPEED.length);
    }

    public static int[] getMinRadius() {
        return Arrays.copyOf(MINR, MINR.length);
    }

    public static int[] getRandomRadius() {
        return Arrays.copyOf(RANDOMR, RANDOMR.length);
    }

    public static float[][] getColorArray() {
        return (float[][]) Arrays.copyOf(COLORARRAY, COLORARRAY.length);
    }

    public static int[] getZArray() {
        return Arrays.copyOf(ZARRAY, ZARRAY.length);
    }

    public static void updateConstant(float radio) {
        TOPBALLR = ORIGIN_TOPBALLR * radio;
        TOPRECTLENGTH = ORIGIN_TOPRECTLENGTH * radio;
        FUSE_DISTANCE = TOPBALLR;
        BOTTOMR = ORIGNAL_BOTTOMR * radio;
        BOTTOMOFFSETX = ORIGNAL_BOTTOMOFFSETX * radio;
        int i = 0;
        for (int i2 = 0; i2 < MINR.length; i2++) {
            MINR[i2] = (int) (((float) ORIGNAL_MINR[i2]) * radio);
        }
        while (i < RANDOMR.length) {
            RANDOMR[i] = (int) (((float) ORIGNAL_RANDOMR[i]) * radio);
            i++;
        }
        UP_SPEED *= radio;
    }

    public static void initParams() {
        CHARGE_ENTRANCE_ANGLE = 0;
        CHARGE_ENTRANCE_LOCATION = 0;
        ROTATEANGLE = 0.0f;
        ALPHA = 1.0f;
        ORIGIN_TOPRECTLENGTH = 355.5f;
        ORIGIN_TOPBALLR = 300.0f;
        ORIGNAL_MIN_TOPRECTLENGTH = 335.5f;
        ORIGNAL_MIN_TOPBALLR = 280.0f;
        ORIGNAL_MAX_TOPRECTLENGTH = 365.5f;
        ORIGNAL_MAX_TOPBALLR = 310.0f;
        ORIGNAL_BOTTOMR = 25.0f;
        ORIGNAL_BOTTOMOFFSETX = 120.0f;
        ORIGNAL_PARTICLEMINR = 12.0f;
        ORIGNAL_PARTICLERANDOMR = 10.0f;
        ORIGNAL_PARTICLEAREASEIZ = 25.0f;
        ORIGNAL_MINR = new int[]{24, 40, 50};
        ORIGNAL_RANDOMR = new int[]{15, 15, 20};
        FUSE_DISTANCE = 300.0f;
        UP_SPEED = FAST_CAHRGE;
        CREATEBALL_SPEED = FAST_CAHRGE_CREATEBALLSPEED;
        ORGANIC_ROTATE_SPEED = FAST_CAHRGE;
        TOPRECTLENGTH = 355.5f;
        TOPBALLR = 300.0f;
        BOTTOMR = 25.0f;
        BOTTOMOFFSETX = 120.0f;
    }
}
