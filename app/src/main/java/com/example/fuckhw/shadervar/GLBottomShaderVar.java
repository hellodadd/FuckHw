package com.example.fuckhw.shadervar;

import org.rajawali3d.materials.shaders.AShaderBase;

public enum GLBottomShaderVar implements AShaderBase.IGlobalShaderVar {
    U_COLOR("uColor", AShaderBase.DataType.VEC4),
    U_ALPHA("uAlpha", AShaderBase.DataType.FLOAT),
    U_FDIMENS("fDimen", AShaderBase.DataType.VEC4);

    private AShaderBase.DataType mDataType;
    private String mVarString;

    private GLBottomShaderVar(String varString, AShaderBase.DataType dataType) {
        this.mVarString = varString;
        this.mDataType = dataType;
    }

    public String getVarString() {
        return this.mVarString;
    }

    public AShaderBase.DataType getDataType() {
        return this.mDataType;
    }
}