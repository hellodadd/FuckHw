precision highp float;
uniform mat4 uMVPMatrix;
attribute vec4 aPosition;
varying  vec2 pos;

void main()
{
   gl_Position = uMVPMatrix * aPosition;
   pos= aPosition.xy;
}