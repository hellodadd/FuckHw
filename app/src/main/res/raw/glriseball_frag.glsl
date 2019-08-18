precision highp float;
precision highp int;

varying vec2 pos;//vertex
//uniform vec4 fDimen;// max/radius/width/height
const int count=100;
const float cThreshold=2.0;

uniform float uAlpha;
uniform  vec4 uColor;
uniform  int uCount;
uniform vec2 uCentre[count];
uniform float uR[count];
uniform vec2 ubottomCentre;//At the bottom of the center of the circle
uniform float ubottomR;//At the bottom of the circle radius
uniform vec2 uTopCentre[3];//Above the position of the three circle, circle + 2 small circle
uniform float uTopR[3];//Three round radius



float energy(float r,vec2 xy,vec2 center){//The potential energy function
  float denominator= pow((xy.x-center.x),2.0) + pow((xy.y-center.y),2.0);
  float result=r*r/denominator;
  return result;
}

void main()
{
      vec2 vPos = pos.xy;
      float dis=distance(vPos.xy,uTopCentre[0]);
      gl_FragColor=vec4(0.0,0.0,0.0,0.0);
      if(dis>uTopR[0]){
         float sumEnergy=energy(uTopR[0],vPos.xy,uTopCentre[0]);
         //Top area calculation superposition of two balls and ball impact of potential energy and
         //meet the conditions for direct return;
         for(int i=1;i<3;i++){
            sumEnergy+=energy(1.24*uTopR[i],vPos.xy,uTopCentre[i]);
            if(sumEnergy>cThreshold){//Area above the fill color
               gl_FragColor=mix(vec4(0,0,0,0),uColor*uAlpha,clamp((dis-uTopR[0])/2.0,0.0,1.0));
               return;
            }
         }
         //The rise of the potential impact jet
         float mdis=distance(vPos.xy,ubottomCentre);//Pixel distance ball at the bottom of the center distance
         if(mdis<4.0*ubottomR){//At the bottom of the ball to weaken
            float bottomEng=energy(3.2*ubottomR,vPos.xy,ubottomCentre)*(1.-clamp((mdis-1.0*ubottomR)/(3.0*ubottomR),0.0,1.0));//At the bottom of the potential impact
            sumEnergy+=bottomEng;//The ball at the bottom of the impact on the above
         }
         //Rising ball superposition of potential energy
         for(int i=0;i<uCount;i++){
              float udis=distance(uCentre[i],uTopCentre[0]);
              sumEnergy += energy(0.8*uR[i],vPos.xy,uCentre[i])*(smoothstep(0.0,uTopR[0]*3.0,udis));
              if(sumEnergy>cThreshold){//Below the ball color
                  gl_FragColor=mix(vec4(0,0,0,0),uColor*uAlpha,clamp((dis-uTopR[0])/2.0,0.0,1.0));
                  return;//After more than threshold value assignment directly, and does not need to be calculated in the assignment, reduce the amount of calculation
              }
         }
         //Overall a anti-aliasing ball
         if(dis>uTopR[0]+2.0){
             float cThresholdfactor=0.1*smoothstep(1.7*uTopR[0]-3.0,1.7*uTopR[0],dis)+0.05;
             gl_FragColor=mix(vec4(0,0,0,0),uColor*uAlpha,smoothstep(cThreshold-cThresholdfactor,cThreshold,sumEnergy));
         }
      }
}

