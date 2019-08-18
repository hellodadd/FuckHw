precision highp float;

varying vec2 pos;//vertex
const float cThreshold=2.0;
uniform  vec4 uColor;
uniform vec2 uTopCentre[3];//Above the position of the three circle, circle + 2 small circle
uniform float uTopR[3];//Three round radius
uniform float uAlpha;

float energy(float r,vec2 xy,vec2 center){//The potential energy function
  float denominator= pow((xy.x-center.x),2.0) + pow((xy.y-center.y),2.0);
  float result=r*r/denominator;
  return result;
}

void main()
{
      vec2 vPos = pos.xy ;
      float dis=distance(vPos.xy,uTopCentre[0]);
      gl_FragColor=vec4(0.0,0.0,0.0,0.0);
      if(dis>uTopR[0]){
         float sumEnergy=energy(uTopR[0],vPos.xy,uTopCentre[0]);
         for(int i=1;i<3;i++){
            sumEnergy+=energy(1.24*uTopR[i],vPos.xy,uTopCentre[i]);
            if(sumEnergy>cThreshold){//Area above the fill color
               //Central area organic shape circle radius uTopR to anti-aliasing uTopR + 2.0 area
               gl_FragColor=mix(vec4(0.0,0.0,0.0,0.0),uColor*uAlpha,clamp((dis-uTopR[0])/2.0,0.0,1.0));
               return;
            }
         }
         //Anti-aliasing uTopR + 2.0 area
         if(dis>uTopR[0]+2.0){
             gl_FragColor=mix(vec4(0.0,0.0,0.0,0.0),uColor*uAlpha,smoothstep(cThreshold-0.03,cThreshold,sumEnergy));
         }
      }
}

