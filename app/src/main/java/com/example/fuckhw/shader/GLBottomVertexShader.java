package com.example.fuckhw.shader;

import com.example.fuckhw.R;

import org.rajawali3d.materials.shaders.VertexShader;
import org.rajawali3d.util.RawShaderLoader;

public class GLBottomVertexShader extends VertexShader {
    public GLBottomVertexShader() {
        setNeedsBuild(false);
        privateInitialize();
    }

    private void privateInitialize() {
        super.initialize();
        this.mShaderString = RawShaderLoader.fetch(R.raw.glbottom_vertex);
    }

    public void initialize() {
        privateInitialize();
    }

    public void main() {
    }

    public void setLocations(int programHandle) {
        super.setLocations(programHandle);
    }

    public void applyParams() {
        super.applyParams();
    }
}
