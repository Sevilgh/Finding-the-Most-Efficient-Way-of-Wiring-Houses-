package com.example.java;


import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import org.apache.commons.collections15.Transformer;


import java.awt.Dimension;
import java.io.File;
import java.lang.reflect.Array;
import java.util.Hashtable;
import java.util.LinkedList;
import javax.swing.JFrame;
import org.apache.commons.collections15.Transformer;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

class Vertex implements Comparable<Vertex> {
        public final String name;
        public Edge[] adjacencies;
        public double minDistance = Double.POSITIVE_INFINITY;
        public Vertex previous;

        public Vertex(String argName) {
            name = argName;
        }

        public String toString() {
            return name;
        }

        public int compareTo(Vertex other) {
            return Double.compare(minDistance, other.minDistance);
        }

    }

    class Edge {
        public final Vertex target;
        public final double weight;

        public Edge(Vertex argTarget, double argWeight) {
            target = argTarget;
            weight = argWeight;
        }
    }

    public class dijkstra {
        public static void computePaths(Vertex source) {
            source.minDistance = 0.;
            PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
            vertexQueue.add(source);

            while (!vertexQueue.isEmpty()) {
                Vertex u = vertexQueue.poll();

                for (Edge e : u.adjacencies) {
                    Vertex v = e.target;
                    double weight = e.weight;
                    double distanceThroughU = u.minDistance + weight;
                    if (distanceThroughU < v.minDistance) {
                        vertexQueue.remove(v);
                        v.minDistance = distanceThroughU;
                        v.previous = u;
                        vertexQueue.add(v);
                    }
                }
            }
        }


        public static List<Vertex> getShortestPathTo(Vertex target) {

            List<Vertex> path = new ArrayList<Vertex>();
            for (Vertex vertex = target; vertex != null; vertex = vertex.previous)
                path.add(vertex);

            Collections.reverse(path);
            return path;
        }

        public static void main(String[] args,ArrayList<Node> inputList,String adress) {
            ArrayList<Vertex> listOfNodes=new ArrayList<>();

            Vertex A = new Vertex("A");
            Vertex B = new Vertex("B");
            Vertex D = new Vertex("D");
            Vertex C = new Vertex("C");
            Vertex E = new Vertex("E");
            Vertex F = new Vertex("F");
            Vertex G = new Vertex("G");
            Vertex H = new Vertex("H");
            Vertex I = new Vertex("I");

            listOfNodes.add(A);
            listOfNodes.add(B);
            listOfNodes.add(D);
            listOfNodes.add(C);
            listOfNodes.add(E);
            listOfNodes.add(F);
            listOfNodes.add(G);
            listOfNodes.add(H);
            listOfNodes.add(I);

            int counter=0;
            ArrayList<String> ForDisplay=new ArrayList<>();

            try {

                int adjCounter=0;
                File names = new File("TheEdgesPoints.txt");
                Scanner input = new Scanner(names);
                while (input.hasNext()) {
                    String[] tokens=input.nextLine().split("@");
                    Edge []adj=new Edge[tokens.length];

                    for (int i = 0; i < tokens.length; i++) {
                        String[] edge = tokens[i].split(":");

                        for(int j=0;j<listOfNodes.size();j++) {
                            if(edge[0].compareTo(listOfNodes.get(j).name)==0) {
                           adj[adjCounter]= new Edge(listOfNodes.get(j), Double.parseDouble(edge[1]));
                           adjCounter++;
                           break;
                            }
                        }

                    }
                    listOfNodes.get(counter).adjacencies=adj;
                    adjCounter=0;
                    counter++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }



                Vertex []keys={B,E,I};
                int total=0;

                computePaths(A);

            List<Vertex> path = getShortestPathTo(keys[0]);
                List<Vertex> path1 = getShortestPathTo(keys[0]);
                total+=keys[0].minDistance;
                int min=Integer.MAX_VALUE;
                List<Vertex> path2 = null ;
                for(int i =0;i<path.size();i++) {
                    computePaths(path.get(i));
                    if(keys[1].minDistance<min) {
                        min=(int) keys[1].minDistance;
                        path2= getShortestPathTo(keys[1]);
                    }
                }
                total+=min;
                System.out.println(path1);
                System.out.println(path2);


                for(int i=0;i<path2.size();i++) {
                    path.add(path2.get(i));
                }
                min=Integer.MAX_VALUE;
                List<Vertex> path3 = null ;
                for(int i =0;i<path.size();i++) {
                    computePaths(path.get(i));
                    if(keys[2].minDistance<min) {
                        min=(int) keys[2].minDistance;
                        path3= getShortestPathTo(keys[2]);
                    }
                }
                total+=min;
                System.out.println(path3);
                System.out.println("total "+total);


                Graph_Viz GA1 = new Graph_Viz();
                LinkedList<String> Distinct_Vertex = new LinkedList<String>();
                LinkedList<String> Source_Vertex = new LinkedList<String>();
                LinkedList<String> Target_Vertex = new LinkedList<String>();
                LinkedList<Double> Edge_Weight = new LinkedList<Double>();
                LinkedList<String> Edge_Label = new LinkedList<String>();

                for(int i=0;i<listOfNodes.size();i++){
                    Distinct_Vertex.add(listOfNodes.get(i).toString());
                }
                for(int i=0;i<path2.size()-1;i++){
                    Source_Vertex.add(path2.get(i).toString());
                    Target_Vertex.add(path2.get(i+1).toString());
                                                                                                                                                                                                     Edge_Weight.add(1.0);
                    Edge_Label.add("");
                }
                for(int i=0;i<path3.size()-1;i++){
                    Source_Vertex.add(path3.get(i).toString());
                    Target_Vertex.add(path3.get(i+1).toString());
                                                                                                                                             Edge_Weight.add(1.0);
                    Edge_Label.add("");
                }
                for(int i=0;i<path1.size()-1;i++){
                    Source_Vertex.add(path1.get(i).toString());
                    Target_Vertex.add(path1.get(i+1).toString());
                                                                                                                                             Edge_Weight.add(1.0);
                    Edge_Label.add("");
                }


                GA1.Visualize_Directed_Graph(Distinct_Vertex, Source_Vertex, Target_Vertex, Edge_Weight, Edge_Label);




        }

    }
class Graph_Viz {

    static int edgeCount_Directed = 0;
    class   MyNode {
        String id;
        public MyNode(String id) {
            this.id = id;
        }

        public String toString() {
            return "V"+id;
        }

        public String Node_Property() {
            String node_prop = id;
            return(node_prop);
        }

    }

    class MyLink {

        double weight;
        String Label;
        int id;
        public MyLink(double weight, String Label) {

            this.id = edgeCount_Directed++;
            this.weight = weight;
            this.Label = Label;
        }
        public String toString() {
            return "E"+id;
        }

        public String Link_Property() {
            String Link_prop = Label;
            return(Link_prop);
        }

        public String Link_Property_wt() {
            String Link_prop_wt = "";
            return(Link_prop_wt);
        }
    }


    public void Visualize_Directed_Graph(LinkedList<String> Distinct_nodes, LinkedList<String> source_vertex, LinkedList<String> target_vertex, LinkedList<Double> Edge_Weight, LinkedList<String> Edge_Label) {


        Graph<MyNode, MyLink> g = new DirectedSparseGraph<MyNode, MyLink>();
        Hashtable<String, MyNode> Graph_Nodes = new Hashtable<String, MyNode>();
        LinkedList<MyNode> Source_Node = new LinkedList<MyNode>();
        LinkedList<MyNode> Target_Node = new LinkedList<MyNode>();
        LinkedList<MyNode> Graph_Nodes_Only = new LinkedList<MyNode>();

        for(int i=0;i<Distinct_nodes.size();i++) {
            String node_name = Distinct_nodes.get(i);
            MyNode data = new MyNode(node_name);
            Graph_Nodes.put(node_name, data);
            Graph_Nodes_Only.add(data);

        }


        for(int t=0;t<source_vertex.size();t++) {
            Source_Node.add(Graph_Nodes.get(source_vertex.get(t)));
            Target_Node.add(Graph_Nodes.get(target_vertex.get(t)));

        }


        for(int i=0;i<Edge_Weight.size();i++){
            g.addEdge(new MyLink(Edge_Weight.get(i),Edge_Label.get(i)),Source_Node.get(i), Target_Node.get(i), EdgeType.DIRECTED);
        }
        //-------------
        CircleLayout<MyNode, MyLink> layout1 = new CircleLayout<MyNode,MyLink>(g);
        layout1.setSize(new Dimension(600, 600));
        BasicVisualizationServer<MyNode, MyLink> viz = new BasicVisualizationServer<MyNode,MyLink>(layout1);
        viz.setPreferredSize(new Dimension(600, 600));
        Transformer<MyNode, String> vertexLabelTransformer = new Transformer<MyNode, String>() {

            public String transform(MyNode vertex) {
                return (String) vertex.Node_Property();
            }

        };

        Transformer<MyLink, String> edgeLabelTransformer = new Transformer<MyLink, String>() {

            public String transform(MyLink edge) {
                return ""+edge.Link_Property()+""+edge.Link_Property_wt();
            }

        };
        viz.getRenderContext().setEdgeLabelTransformer(edgeLabelTransformer);
        viz.getRenderContext().setVertexLabelTransformer(vertexLabelTransformer);
        JFrame frame = new JFrame("");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(viz);
        frame.pack();
        frame.setVisible(true);
    }}