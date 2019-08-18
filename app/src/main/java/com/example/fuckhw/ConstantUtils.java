package com.example.fuckhw;

public class ConstantUtils {
    private static final float[] GREEN_BOTTOMCOLOR = new float[]{0.0f, 0.83921576f, 0.34901962f, 0.9f};
    private static final float[] GREEN_CENTRECOLOR = new float[]{0.0f, 0.83921576f, 0.34901962f, 1.0f};
    private static final float[] GREEN_EJECTOMCOLOR = new float[]{0.0f, 0.83921576f, 0.34901962f, 1.0f};
    private static final float[] GREEN_TOPCOLOR = new float[]{0.0f, 0.67058825f, 0.2784314f, 0.9f};
    private static final float[] ORANGE_BOTTOMCOLOR = new float[]{0.77647066f, 0.3137255f, 0.10588236f, 0.6f};
    private static final float[] ORANGE_CENTRECOLOR = new float[]{0.9843138f, 0.39607847f, 0.13333334f, 1.0f};
    private static final float[] ORANGE_EJECTOMCOLOR = new float[]{0.9843138f, 0.39607847f, 0.13333334f, 1.0f};
    private static final float[] ORANGE_TOPCOLOR = new float[]{0.77647066f, 0.3137255f, 0.10588236f, 0.8f};
    private static final float[] RED_BOTTOMCOLOR = new float[]{0.8000001f, 0.15686275f, 0.0f, 0.6f};
    private static final float[] RED_CENTRECOLOR = new float[]{1.0f, 0.20000002f, 0.1254902f, 1.0f};
    private static final float[] RED_EJECTOMCOLOR = new float[]{1.0f, 0.20000002f, 0.1254902f, 1.0f};
    private static final float[] RED_TOPCOLOR = new float[]{0.8000001f, 0.15686275f, 0.0f, 0.8f};

    public static float[][] getHightBattertColorArrays() {
        return new float[][]{GREEN_BOTTOMCOLOR, GREEN_CENTRECOLOR, GREEN_TOPCOLOR, GREEN_EJECTOMCOLOR};
    }

    public static float[][] getMiddleBattertColorArrays() {
        return new float[][]{ORANGE_BOTTOMCOLOR, ORANGE_CENTRECOLOR, ORANGE_TOPCOLOR, ORANGE_EJECTOMCOLOR};
    }

    public static float[][] getLowBattertColorArrays() {
        return new float[][]{RED_BOTTOMCOLOR, RED_CENTRECOLOR, RED_TOPCOLOR, RED_EJECTOMCOLOR};
    }
}
