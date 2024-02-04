package com.example.java;

import java.awt.Dimension;
import java.util.Hashtable;
import java.util.LinkedList;
import javax.swing.JFrame;
import org.apache.commons.collections15.Transformer;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;

public class faz1 {

    static int edgeCount_Directed = 0;
    class MyNode {
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
            String Link_prop_wt = ""+weight;
            return(Link_prop_wt);
        }
    }


    public void Visualize_Directed_Graph(LinkedList<String> Distinct_nodes, LinkedList<String> source_vertex, LinkedList<String> target_vertex, LinkedList<Double> Edge_Weight, LinkedList<String> Edge_Label) {


        Graph<MyNode, MyLink> g = new DirectedSparseGraph<faz1.MyNode, faz1.MyLink>();
        //create node objects
        Hashtable<String, MyNode> Graph_Nodes = new Hashtable<String, faz1.MyNode>();
        LinkedList<MyNode> Source_Node = new LinkedList<faz1.MyNode>();
        LinkedList<MyNode> Target_Node = new LinkedList<faz1.MyNode>();
        LinkedList<MyNode> Graph_Nodes_Only = new LinkedList<faz1.MyNode>();
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
                return "[ "+edge.Link_Property()+" ]: Wt = "+edge.Link_Property_wt();
            }

        };
        viz.getRenderContext().setEdgeLabelTransformer(edgeLabelTransformer);
        viz.getRenderContext().setVertexLabelTransformer(vertexLabelTransformer);
        JFrame frame = new JFrame("A Directed Graph Visualization... demo code by Niraj Kumar");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(viz);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {

        String a[]={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

        // TODO Auto-generated method stub
        Graph_Viz GA1 = new Graph_Viz();
        LinkedList<String> Distinct_Vertex = new LinkedList<String>();
        LinkedList<String> Source_Vertex = new LinkedList<String>();
        LinkedList<String> Target_Vertex = new LinkedList<String>();
        LinkedList<Double> Edge_Weight = new LinkedList<Double>();
        LinkedList<String> Edge_Label = new LinkedList<String>();
        Distinct_Vertex.add("A");
        Distinct_Vertex.add("B");
        Distinct_Vertex.add("C");
        Distinct_Vertex.add("D");
        Distinct_Vertex.add("E");
        Distinct_Vertex.add("F");
        Distinct_Vertex.add("G");

        Source_Vertex.add("A"); Target_Vertex.add("B"); Edge_Weight.add(0.7);Edge_Label.add("AB");
        Source_Vertex.add("B"); Target_Vertex.add("C"); Edge_Weight.add(0.9);Edge_Label.add("BC");
        Source_Vertex.add("C"); Target_Vertex.add("D"); Edge_Weight.add(4.57);Edge_Label.add("CD");
        Source_Vertex.add("D"); Target_Vertex.add("B"); Edge_Weight.add(1.0);Edge_Label.add("DB");
        Source_Vertex.add("C"); Target_Vertex.add("A"); Edge_Weight.add(1.3);Edge_Label.add("CA");
        Source_Vertex.add("A"); Target_Vertex.add("D"); Edge_Weight.add(0.3);Edge_Label.add("AD");
        Source_Vertex.add("D"); Target_Vertex.add("F"); Edge_Weight.add(0.2);Edge_Label.add("DF");
        Source_Vertex.add("E"); Target_Vertex.add("G"); Edge_Weight.add(0.4);Edge_Label.add("EG");
        Source_Vertex.add("G"); Target_Vertex.add("F"); Edge_Weight.add(2.2);Edge_Label.add("GF");
        GA1.Visualize_Directed_Graph(Distinct_Vertex, Source_Vertex, Target_Vertex, Edge_Weight, Edge_Label);
    }

}
