public class Planet {
    public double xxPos ;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;


    /* constructor method so it could be valid without return value  */
    public Planet(double xP, double yP, double xV,
              double yV, double m, String img){
                  xxPos = xP;  // left is the property of class  right one is the actual value which is passed via instance
                  yyPos = yP;
                  xxVel = xV;
                  yyVel = yV;
                  mass = m;
                  imgFileName = img;
              }
    // 2nd way to build a constructor method for the class Planet

    public Planet(Planet b){  // input an object as variable
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;

    }

    public double calcDistance (Planet a){
        double dx2 = Math.pow(this.xxPos - a.xxPos, 2);
        double dy2 = Math.pow(this.yyPos - a.yyPos, 2);
        return Math.sqrt(dx2+dy2);
    }



    public double calcForceExertedBy (Planet a){
        double m1 = this.mass;
        double m2 = a.mass;
        double someNumber = 6.67e-11;
        double distance = this.calcDistance(a);
        double force = (someNumber * m1 * m2) / (distance * distance);
        return force;
    }

    public double calcForceExertedByX (Planet a){
        double force = this.calcForceExertedBy(a);
        double dx = a.xxPos - this.xxPos;
        double forceByX = force * dx /this.calcDistance(a);
        return forceByX;
    }

    public double calcForceExertedByY (Planet a){
        double force = this.calcForceExertedBy(a);
        double dy = a.yyPos - this.yyPos;
        double forceByY = force * dy /this.calcDistance(a);
        return forceByY;
    }

    public double calcNetForceExertedByX(Planet[] allPlaneties){
        double netForceByX = 0.0;
            for(Planet a : allPlaneties){
                if (this.equals(a)){
                    netForceByX = netForceByX; // this step is important,if replace this by "break", then you will get nothing when input include this Planet,because break means break entire the loop
                }
                else{
                netForceByX = calcForceExertedByX(a) + netForceByX;
                }
            }   
            return netForceByX;
        }
        
            
    public double calcNetForceExertedByY(Planet[] allPlaneties){
        double netForceByY = 0.0;
            for(Planet a : allPlaneties){
                if (this.equals(a)){
                    netForceByY = netForceByY;
                }
                else{
                netForceByY = calcForceExertedByY(a) + netForceByY;
                }
            }
            return netForceByY;
        }   
       
    public void update(double dt,double fX,double fY){
        double accX = fX / this.mass;
        double accY = fY / this.mass;
        this.xxVel = this.xxVel + accX * dt; // iteration use old value, we can use this.xxx instead of creating a new variable
        this.yyVel = this.yyVel + accY * dt;
        this.xxPos = this.xxPos + this.xxVel * dt;
        this.yyPos = this.yyPos + this.yyVel * dt;
    }

    
    public void draw(){
        StdDraw.picture(xxPos, yyPos,"images/" + imgFileName);// we shoule separate the directory and the planet name otherwise it cannot find an image called images/imgFilename
    }
        
        
}

