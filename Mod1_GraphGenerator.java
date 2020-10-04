import brite.Graph.Edge;
import brite.Graph.EdgeConf;
import brite.Graph.Graph;
import brite.Graph.GraphConstants;
import brite.Graph.Node;
import brite.Graph.NodeConf;
import cnrs.grph.set.IntSet;
import com.carrotsearch.hppc.IntIntMap;
import grph.Grph;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import ij.io.OpenDialog;
import ij.io.Opener;
import java.util.HashMap;
import javax.swing.JFileChooser;
//import org.jgrapht.Graph;
//import toools.collections.IntQueueOrStack.ACCESS_MODE;

public class Mod1_GraphGenerator {

    public static Graph g = null;
    public static Graph gf = null;
    public static NodeConf nc1 = null;
    public static NodeConf nc2 = null;
    public static Node n1 = null;
    public static Node n2 = null;
    public static EdgeConf ec1 = null;
    public static EdgeConf ec2 = null;
    public static Edge e1 = null;
    public static Edge e2 = null;
    public static Grph G = null;
    public static int LIMIT = 6;
    public static void savef(String s){
               //System.out.println(s);
               File r=new File("name");
               FileWriter fw=null;
        try {
            fw = new FileWriter(r);
        } catch (IOException ex) {
            //Logger.getLogger(FSM1_2_GenerateFilterGraph.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            fw.write(s);
        } catch (IOException ex) {
            //Logger.getLogger(FSM1_2_GenerateFilterGraph.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            fw.close();
        } catch (IOException ex) {
            //Logger.getLogger(FSM1_2_GenerateFilterGraph.class.getName()).log(Level.SEVERE, null, ex);
        }
           }

public static void delete(File file) throws IOException{
 
    	if(file.isDirectory()){
 
    		//directory is empty, then delete it
    		if(file.list().length==0){
 
    		   file.delete();
    		   System.out.println("Directory is deleted : " 
                                                 + file.getAbsolutePath());
 
    		}else{
 
    		   //list all the directory contents
        	   String files[] = file.list();
 
        	   for (String temp : files) {
        	      //construct the file structure
        	      File fileDelete = new File(file, temp);
 
        	      //recursive delete
        	     delete(fileDelete);
        	   }
 
        	   //check the directory again, if empty then delete it
        	   if(file.list().length==0){
           	     file.delete();
        	     System.out.println("Directory is deleted : " 
                                                  + file.getAbsolutePath());
        	   }
    		}
 
    	}else{
    		//if file, then delete it
    		file.delete();
    		System.out.println("File is deleted : " + file.getAbsolutePath());
    	}
    }
    private static void GenerateGraph(String filepath) throws FileNotFoundException, IOException {
        // "type","name","id","self_size","edge_count"
        //
        //step 1 Find number of fils in a folder ....
        ///for every file in a folder .....  generate graph G[i]
        brite.Graph.Graph bb = new brite.Graph.Graph();

    
        
              //create dir for writing generated graphs
         File theDir = new File("GraphGenerated");
         // if the directory  exist delete it
         if (theDir.exists()) {
             //System.out.println("creating directory: " + filepath+"/GeneratedGraphs");
             delete(theDir);
         }
         
             System.out.println("creating director:"+"GraphGenerated");
             boolean result = theDir.mkdir();
             if(result) { System.out.println("DIR created"); 
             }
         
         
              //create dir for writing generated graphs
          theDir = new File("GraphFiltered");
         // if the directory  exist delete it
         if (theDir.exists()) {
             //System.out.println("creating directory: " +"GeneratedGraphs");
            delete(theDir);
         }
          
             System.out.println("creating directory: " +"GraphFiltered");
               result = theDir.mkdir();
             if(result) { System.out.println("DIR created"); 
             }
         
         
     
        File file = new File(filepath);

        String list[];
        String sCurrentLine;
        BufferedReader br = null;

        if (file.isDirectory()) {
            System.out.println("Given file is a directory ");
            list = file.list();

            for (int i = 0; i < list.length; i++) {
                System.out.println("Current file=" + list[i]);

                G = new Grph();
                g = new Graph();


                if (new File(filepath + "/" + list[i]).isFile()) {
                    br = new BufferedReader(new FileReader(filepath + "/" + list[i]));

                    sCurrentLine = br.readLine();

                    //System.out.println("ddd"+sCurrentLine);

                    int si = sCurrentLine.indexOf("\"node_count\":");
                    int ei = sCurrentLine.indexOf(",\"edge_count\":");
                    //System.out.println(sCurrentLine.substring(si+13, ei));
                    int x = Integer.parseInt(sCurrentLine.substring(si + 13, ei));
                    //separate nodes string

                    sCurrentLine = br.readLine();
                    //System.out.println(sCurrentLine);

                    si = sCurrentLine.indexOf("[");


                    String type = null, name = null, id = null, self_size = null, edge_count = null;

                    sCurrentLine = sCurrentLine.substring(si + 1);


                    //if(sCurrentLine.substring(0,sCurrentLine.indexOf(",")).equalsIgnoreCase("3") | sCurrentLine.substring(0,sCurrentLine.indexOf(",")).equalsIgnoreCase("5"))
                    {
                        //System.out.println(sCurrentLine);
                        id = sCurrentLine.substring(sCurrentLine.indexOf(",") + 1);
                        id = id.substring(id.indexOf(",") + 1);
                        id = id.substring(0, id.indexOf(","));
                        // System.out.println(id);
                        if (id.length() < LIMIT) {
                            G.addVertexIfNecessary(Integer.parseInt(id));
                        }

                        String substring = sCurrentLine.substring(0, sCurrentLine.indexOf(","));

                        nc1 = new NodeConf();

                        n1 = new Node();
                        nc1.setNodeType(Integer.parseInt(substring));
                        n1.setNodeConf(nc1);

                        if (id.length() < LIMIT) {
                            n1.setID(Integer.parseInt(id));
                            g.addNode(n1);
                        }
                        //System.out.println(Integer.parseInt(substring));

                    }
                    for (int ij = 1; ij < x; ij++) {
                        sCurrentLine = br.readLine();
                        sCurrentLine = sCurrentLine.substring(1);
                        //if(sCurrentLine.substring(0,sCurrentLine.indexOf(",")).equalsIgnoreCase("3") | sCurrentLine.substring(0,sCurrentLine.indexOf(",")).equalsIgnoreCase("5"))
                        {
                            //System.out.println(sCurrentLine); 
                            id = sCurrentLine.substring(sCurrentLine.indexOf(",") + 1);
                            id = id.substring(id.indexOf(",") + 1);
                            id = id.substring(0, id.indexOf(","));
                            //System.out.println(id);
                            if (id.length() < LIMIT) {
                                G.addVertexIfNecessary(Integer.parseInt(id));
                            }

                            String substring = sCurrentLine.substring(0, sCurrentLine.indexOf(","));

                            nc1 = new NodeConf();

                            n1 = new Node();
                            nc1.setNodeType(Integer.parseInt(substring));
                            n1.setNodeConf(nc1);

                            if (id.length() < LIMIT) {
                                n1.setID(Integer.parseInt(id));
                                g.addNode(n1);
                            }
                        }

                    }

                    sCurrentLine = br.readLine();
                    //System.out.println(sCurrentLine);

                    sCurrentLine = br.readLine();
                    //System.out.println(sCurrentLine);


                    sCurrentLine = sCurrentLine.substring(sCurrentLine.indexOf("[") + 1);
                    //System.out.println(sCurrentLine);
                    String node1 = null, node2 = null;

                    //if(sCurrentLine.substring(0,sCurrentLine.indexOf(",")).equalsIgnoreCase("1") | sCurrentLine.substring(0,sCurrentLine.indexOf(",")).equalsIgnoreCase("2"))
                    {
                        //System.out.println(sCurrentLine);
                        node1 = sCurrentLine.substring(sCurrentLine.indexOf(",") + 1);
                        node2 = node1.substring(node1.indexOf(",") + 1);
                        node1 = node1.substring(0, node1.indexOf(","));

                        //System.out.println("node1="+node1+"\tnod2="+node2);
                        if (node1.length() < LIMIT && node2.length() < LIMIT) {
                            G.addDirectedSimpleEdge(Integer.parseInt(node1), Integer.parseInt(node2));
                        }


                        if (node1.length() < LIMIT && node2.length() < LIMIT) {
                            String type_e = sCurrentLine.substring(0, sCurrentLine.indexOf(","));

                            n1 = new Node();
                            n2 = new Node();

                            n1.setID(Integer.parseInt(node1));
                            n2.setID(Integer.parseInt(node2));

                            ec1 = new EdgeConf();
                            ec1.setEdgeType(Integer.parseInt(type_e));


                            e1 = new Edge(n1, n2);
                            e1.setDirection(GraphConstants.DIRECTED);
                            e1.setEdgeConf(ec1);

                            g.addEdge(e1);
                        }

                    }

                    for (; !(sCurrentLine = br.readLine()).equals("],");) {

                        sCurrentLine = sCurrentLine.substring(1);

                        // if(sCurrentLine.substring(0,sCurrentLine.indexOf(",")).equalsIgnoreCase("1") | sCurrentLine.substring(0,sCurrentLine.indexOf(",")).equalsIgnoreCase("2"))
                        {
                            //System.out.println(sCurrentLine);
                            node1 = sCurrentLine.substring(sCurrentLine.indexOf(",") + 1);
                            node2 = node1.substring(node1.indexOf(",") + 1);
                            node1 = node1.substring(0, node1.indexOf(","));

                            //System.out.println("node1="+node1+"\tnod2="+node2);
                            if (node1.length() < LIMIT && node2.length() < LIMIT) {
                                G.addDirectedSimpleEdge(Integer.parseInt(node1), Integer.parseInt(node2));
                            }

                            if (node1.length() < LIMIT && node2.length() < LIMIT) {
                                String type_e = sCurrentLine.substring(0, sCurrentLine.indexOf(","));

                                n1 = new Node();
                                n2 = new Node();

                                n1.setID(Integer.parseInt(node1));
                                n2.setID(Integer.parseInt(node2));

                                ec1 = new EdgeConf();
                                ec1.setEdgeType(Integer.parseInt(type_e));


                                e1 = new Edge(n1, n2);
                                e1.setDirection(GraphConstants.DIRECTED);
                                e1.setEdgeConf(ec1);

                                g.addEdge(e1);
                            }




                        }
                    }

                    br.close();
                    System.out.println("" + G.getDescription());
                    //G.display();

                    //save the graph to file
                    File gf = new File("GraphGenerated" + "/" + list[i].substring(0, list[i].indexOf(".")) + ".xml");
                    if (!gf.exists()) {
                        gf.createNewFile();
                    }
                    FileWriter fw = new FileWriter(gf.getAbsoluteFile());
                    BufferedWriter bbw = new BufferedWriter(fw);
                    bbw.write(G.toGraphML());
                    bbw.close();


                   
                    Node[] nodesArray = new Node[g.getNodesArray().length];
                    
                    Edge[] edgesArray = new Edge [g.getEdgesArray().length];
                    
                    nodesArray=g.getNodesArray();
                    edgesArray =g.getEdgesArray();
                    System.out.println("Node count=" + nodesArray.length);
                    System.out.println("Edge count=" + edgesArray.length);
 
                     

                }
            }
            //g.dumpToOutput();

            G.display();
        }

    }

    public static void main(String args[]) throws FileNotFoundException, IOException {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("Select Directory");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            System.out.println("getCurrentDirectory(): " + chooser.getCurrentDirectory());
            System.out.println("getSelectedFile() : " + chooser.getSelectedFile());
             GenerateGraph(chooser.getSelectedFile().toString());    
        } else {
            System.out.println("No Selection ");
        }


                                                                                                                                                    savef(chooser.getSelectedFile().getName());


    }
}
