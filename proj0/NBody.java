
public class NBody{
    public static double readRadius(String filename){
        In file = new In(filename);// remove" ", just use string
        int planetsNum = file.readInt();// must keep this line otherwise you will give the first line value as radius
        return  file.readDouble(); 
    }

    public static Planet[] readBodies(String filename){
        In file = new In(filename);
        int planetsNum = file.readInt();// must keep this line otherwise you will give the first line value as radius
        double radius = file.readDouble();//if miss this line then you would not get next value
        Planet[] planetPlanet = new Planet[planetsNum]; // only create 5 pointers which point to null or Planet and its subclass 
          //  planetPlanet[i].xxPos = file.readDouble(); this syntax is wrong, because left is  null  so we can not put value in it 
          for(int i = 0;i < planetsNum;i++){
           double xxPos = file.readDouble();
           double yyPos = file.readDouble();
           double xxVel = file.readDouble();
           double yyVel = file.readDouble();
           double mass = file.readDouble();
           String imgFileName = file.readString();
           planetPlanet[i] = new Planet(xxPos,yyPos,xxVel,yyVel,mass,imgFileName);//new object; assign
          }
           return planetPlanet;
    }

    public static void main(String[] args){
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);

        String filename = args[2];
        Planet[] Bodies = NBody.readBodies(filename);
        double radius = NBody.readRadius(filename);

        StdDraw.setScale(-radius, radius);
        StdDraw.picture(0,0,"images/starfield.jpg");
        
        for(Planet b:Bodies){
            b.draw();
        }
        StdDraw.enableDoubleBuffering(); // should put doublebuffering after draw otherwise we wouldnot see the planets
        double[] xForces = new double[Bodies.length];
        double[] yForces = new double[Bodies.length];

        for(double time = 0.0;time <= T; time = time + dt){
            for(int j = 0; j < Bodies.length;j++){
                xForces[j] = Bodies[j].calcNetForceExertedByX(Bodies);
                yForces[j] = Bodies[j].calcNetForceExertedByY(Bodies);
            }

                
                StdDraw.setScale(-radius, radius);
                StdDraw.picture(0,0,"images/starfield.jpg");

            for(int k = 0; k < Bodies.length;k++){
                    Bodies[k].update(dt,xForces[k],yForces[k]);
                    Bodies[k].draw();
                }
                StdDraw.show();
                StdDraw.pause(10);
        }
        StdOut.printf("%d\n", Bodies.length);

        StdOut.printf("%.2e\n", radius);
    
        for(int m = 0; m < Bodies.length; m++) {
        System.out.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                      Bodies[m].xxPos, Bodies[m].yyPos, Bodies[m].xxVel,
                      Bodies[m].yyVel, Bodies[m].mass, Bodies[m].imgFileName);   
         }
    }

    
   
}