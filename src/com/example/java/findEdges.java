package com.example.java;




import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import org.opencv.core.*;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class findEdges {
        static   ArrayList<Point> points=new ArrayList<>();
        static   ArrayList<String> lineArrayList=new ArrayList<>();


    public static void start(String path) throws IOException {
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

            Mat src = Imgcodecs.imread(path);
            Mat gray = new Mat();
            Imgproc.cvtColor(src, gray, Imgproc.COLOR_RGBA2GRAY);
            Mat edges = new Mat();
            Imgproc.Canny(gray, edges, 60, 60*1, 3, false);
            Mat cannyColor = new Mat();
            Imgproc.cvtColor(edges, cannyColor, Imgproc.COLOR_GRAY2BGR);
            Mat lines = new Mat();
           ArrayList<Node> list=new ArrayList<>();

        Imgproc.HoughLinesP(edges, lines, 1, Math.PI/180, 15, 3, 65);
            for (int i = 0; i < lines.rows(); i++) {
                double[] data = lines.get(i, 0);
                Point pt1 = new Point(data[0], data[1]);
                Point pt2 = new Point(data[2], data[3]);
                points.add(pt1);
                points.add(pt2);
                lineArrayList.add(pt1.x+":"+pt1.y+":"+pt2.x+":"+pt2.y);
               // System.out.println("x: "+pt1.x+" y "+pt1.y);
               // System.out.println("x: "+pt2.x+" y "+pt2.y);

                Imgproc.line(cannyColor, pt1, pt2, new Scalar(0, 0, 255), 3);

            }
            Image img = HighGui.toBufferedImage(cannyColor);
            WritableImage writableImage= SwingFXUtils.toFXImage((BufferedImage) img, null);
           // String []split=path.split("\\\\");
            writeToFile(lineArrayList);
            String newPath="5.jpg";
            Imgcodecs.imwrite(newPath,edges);




        }

        public static void main(String[] args) throws IOException {
            Node n = new Node(1,3);
            start("4.jpg");
            String adress="4.jpg";
            ArrayList<Node> list=new ArrayList<>();

            dijkstra dij=new dijkstra();
            dij.main(null,list,adress);
        }


        public  static void writeToFile(ArrayList<String> list) throws IOException {
            File file=new File("TheEdgePoints.txt");
            FileWriter fileWriter=new FileWriter(file);
            for (String str:list){
                fileWriter.write(str+"\n");
            }
            fileWriter.flush();
            fileWriter.close();

        }

    }
class Node {
    ArrayList<Double> array = new ArrayList();
    double x;
    double y;
    public Node(double x, double y){
        x=this.x;
        y=this.y;
    }

}
