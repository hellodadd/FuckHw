uniform mat4 uMVPMatrix;
attribute vec4 aPosition;
varying  vec2 vPosition;

void main()
{
   gl_Position = uMVPMatrix * aPosition;
   vPosition= aPosition.xy;
}