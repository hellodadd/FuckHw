package com.example.fuckhw.shadervar;

import org.rajawali3d.materials.shaders.AShaderBase;
import org.rajawali3d.materials.shaders.AShaderBase.DataType;

public enum GLRiseBallShaderVar implements AShaderBase.IGlobalShaderVar {
    U_ALPHA("uAlpha", DataType.FLOAT),
    U_COLOR("uColor", DataType.VEC4),
    U_FDIMENS("fDimen", DataType.VEC4),
    U_COUNT("uCount", DataType.INT),
    U_CENTRE("uCentre", DataType.VEC2),
    U_R("uR", DataType.FLOAT),
    U_BOTTOMCENTRE("ubottomCentre", DataType.VEC2),
    U_BOTTOMR("ubottomR", DataType.FLOAT),
    U_TOPR("uTopR", DataType.FLOAT),
    U_TOPCENTRE("uTopCentre", DataType.VEC2);

    private DataType mDataType;
    private String mVarString;

    private GLRiseBallShaderVar(String varString, DataType dataType) {
        this.mVarString = varString;
        this.mDataType = dataType;
    }

    public String getVarString() {
        return this.mVarString;
    }

    public DataType getDataType() {
        return this.mDataType;
    }
}
