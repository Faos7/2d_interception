package main.misc;

import main.primitives2d.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by faos7 on 17.03.17.
 */
public class PointGetter {

    public static Shape createShape(String s){
        Shape shape1 = new Shape();
        shape1.setCreated(false);
        while (shape1.isCreated() == false){
            System.out.println("please choose " + s + " 2D primitive \n\'s\' - square\n" +
                    "\'t\' - triangle\n\'c\' - circle");
            String res = readLine();

            switch (res.charAt(0)){
                case 'c':{
                    System.out.println("please enter center (x,y)");
                    String center = readLine();
                    System.out.println("please enter radius");

                    try {
                        double r = toDouble(readLine());
                        shape1 = new Circle(toP2D(center), r);
                    }catch (Exception e){
                        System.err.println("wrong entry");
                        break;
                    }

                    break;
                }
                case 't':{
                    System.out.println("please enter first vertice (x,y)");
                    String a = readLine();
                    System.out.println("please enter second vertice (x,y)");
                    String b = readLine();
                    System.out.println("please enter third vertice (x,y)");
                    String c = readLine();
                    try {
                        shape1 = new Triangle(toP2D(a), toP2D(b), toP2D(c));
                    }catch (Exception e){
                        System.err.println("wrong entry");
                        break;
                    }
                    break;
                }
                case 's':{
                    System.out.println("please enter first vertice (x,y)");
                    String a = readLine();
                    System.out.println("please enter second vertice (x,y)");
                    String b = readLine();
                    try {
                        shape1 = new Square(toP2D(a), toP2D(b));
                    }catch (Exception e){
                        System.err.println("wrong entry");
                        break;
                    }

                    break;
                }
                default: System.exit(0);
            }
        }
        return shape1;
    }

    public static Point_2D toP2D(String string) throws Exception{

        String[] jn = string.split(",");
        double x = Double.parseDouble(jn[0]);
        double y = Double.parseDouble(jn[1]);
        return new Point_2D(x,y);
    }

    public static String readLine(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String res;
        try {
            res = reader.readLine();
        } catch (IOException e){
            System.err.println(e.toString());
            res = "";
        }
        return res;
    }

    public static double toDouble(String string)throws Exception{

        double res = Double.parseDouble(string);
        return res;
    }


}
