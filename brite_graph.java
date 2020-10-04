
import brite.Graph.Edge;
import brite.Graph.EdgeConf;
import brite.Graph.Graph;
import brite.Graph.Node;
import brite.Graph.NodeConf;

public class brite_graph {

    public static void main(String args[]) {


        Graph g = new Graph();
        NodeConf nc1 = new NodeConf();
        NodeConf nc2 = new NodeConf();
        Node n1 = new Node();
        Node n2 = new Node();
        nc1.setNodeType(100);
        nc2.setNodeType(200);
        n1.setNodeConf(nc1);
        n1.setColor(1000);
        n1.setID(1);
        n2.setNodeConf(nc2);
        n2.setColor(2000);
        n2.setID(2);

        EdgeConf ec = new EdgeConf();
        ec.setEdgeType(10);
        Edge e = new Edge(n1, n2);
        e.setEdgeConf(ec);
        g.addNode(n1);
        g.addNode(n2);
        g.addEdge(e);

        Graph g1 = new Graph();
        nc1 = new NodeConf();
        nc2 = new NodeConf();
        n1 = new Node();
        n2 = new Node();
        nc1.setNodeType(100);
        nc2.setNodeType(200);
        n1.setNodeConf(nc1);
        n1.setColor(1000);
        n1.setID(1);
        n2.setNodeConf(nc2);
        n2.setColor(2000);
        n2.setID(2);

        ec = new EdgeConf();
        ec.setEdgeType(10);
        e = new Edge(n1, n2);
        e.setEdgeConf(ec);
        g1.addNode(n1);
        g1.addNode(n2);
        g1.addEdge(e);

        if (g.equals(g1)) {
            System.out.println("YES");
        }





        Node[] nodesArray = g.getNodesArray();
        Edge[] edgesArray = g.getEdgesArray();
        for (int i = 0; i < nodesArray.length; i++) {
            //System.out.println(nodesArray[i].getNodeConf().getNodeType());
        }
        for (int i = 0; i < edgesArray.length; i++) {//System.out.println(edgesArray[i].getEdgeConf().getEdgeType());
        }
        //System.out.println(g.dfs(n1));


    }
}
