precision highp float;
uniform vec4 uColor;
uniform vec4 fDimen;//centreX,centreY,offsetX,offsetY
uniform float uAlpha;
const float pi=3.1415926;
varying  vec2 vPosition;
void main()
{
      float offX=vPosition.x-fDimen.x;
      float offY=fDimen.y-vPosition.y;
      float angle=offX*pi/fDimen.z;
      float m=(fDimen.w)*cos(angle)-offY;
      gl_FragColor=mix(vec4(0,0,0,0),uColor*uAlpha,smoothstep(-1.,1.,m));
}
