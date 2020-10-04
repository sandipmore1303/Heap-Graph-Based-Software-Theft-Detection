
import brite.Graph.Edge;
import brite.Graph.EdgeConf;
import brite.Graph.Graph;
import brite.Graph.GraphConstants;
import brite.Graph.Node;
import brite.Graph.NodeConf;
import cnrs.grph.set.IntSet;
import com.carrotsearch.hppc.IntIntMap;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.File; 
import java.io.FileWriter;
//import java.util.Date; 
//import jxl.*; 
//import jxl.write.*; 
//import com.google.common.collect.Sets;
import java.util.HashSet;
import java.util.Set;
//import com.carrotsearch.hppc.IntArrayList;
//import com.carrotsearch.hppc.IntIntMap;
import com.carrotsearch.hppc.cursors.IntCursor;
import grph.Grph;
import grph.VertexPair;
//import grph.algo.topology.ClassicalGraphs;
import grph.algo.traversal.BFSResult;
import grph.algo.traversal.DFSAlgorithm;
import grph.algo.traversal.GraphSearchListener;
import grph.io.GrphBinaryWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;
import javax.swing.JFileChooser;
import toools.io.file.RegularFile;
 
//import org.jgrapht.Graph;
//import toools.collections.IntQueueOrStack.ACCESS_MODE;

public class FSM3_GraphMerger {
    public static Graph g=null;
    public static Graph gf=null;
    public static NodeConf nc1=null;
    public static NodeConf nc2=null;
    public static Node n1=null;
    public static Node n2=null;
   
    public static EdgeConf ec1 =null;
    public static EdgeConf ec2 =null;
    
    public static Edge e1=null;
    public static Edge e2=null;
    public static Grph G=null;
    public static Grph Gf=null;
    public static int LIMIT=6;
     
    private static void MergeGraphs(String filepath) throws FileNotFoundException, IOException {
        File theDir = new File("FSM_GRAPHSFILTERED");
        String[] list =null;
        //File r = new File("FSM_GRAPHSFILTERED"+"/" + list[i].substring(0, list[i].indexOf(".")) + ".out");
        //FileWriter fileWriter_red = new FileWriter(r);
        File r = null;//new File(filepath+"/FSM_GRAPHSFILTERED"+"/" + list[i].substring(0, list[i].indexOf(".")) + ".out");
        //br = new BufferedReader(new FileReader(filepath+"/"+list[i]));
        
        BufferedReader br =null;         
        FileWriter fileWriter =new FileWriter(new File("FSM_MergedGraph.lg"));
        
        String line=null;
        if(theDir.isDirectory())
         {
           System.out.println("Given file is a directory "+theDir.getAbsolutePath()); 
           list=  theDir.list();
           
           for(int i=0;i<list.length;i++)
               
           {
                    
           if(new File(theDir+"/"+list[i]).isFile()){
             System.out.println("\n\nCurrent file="+list[i]+i);
             //read all conent of cuurent file and write it into above file
             br = new BufferedReader(new FileReader(theDir+"/"+list[i]));
             //System.out.println(br.readLine().isEmpty());
              
             //boolean flag=false;
             for(line=br.readLine();line !=null;line=br.readLine()){
                 //System.out.println(line);
                 fileWriter.write(line+"\n");
                 
             }
              br.close();
           
           }
               
         }
           
         }
        //fileWriter.close();
    }
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    

      private static void GenerateFilterGraph(String filepath) throws FileNotFoundException, IOException {
         // "type","name","id","self_size","edge_count"
         //
         //step 1 Find number of fils in a folder ....
         ///for every file in a folder .....  generate graph G[i]
         
         
        
        
              //create dir for writing generated graphs
         File theDir = new File("FSM_GraphGenerated");
         // if the directory  exist delete it
         if (theDir.exists()) {
             //System.out.println("creating directory: " + filepath+"/GeneratedGraphs");
             boolean result = theDir.delete();
             if(result) {    
                 System.out.println("DIR removed");  
             }
         }
         if (!theDir.exists()) {
             //System.out.println("creating directory: " + filepath+"/GeneratedGraphs");
             boolean result = theDir.mkdir();
             if(result) { System.out.println("DIR created"); 
             }
         }
         
              //create dir for writing generated graphs
          theDir = new File("FSM_GraphFiltered");
         // if the directory  exist delete it
         if (theDir.exists()) {
             //System.out.println("creating directory: " + filepath+"/GeneratedGraphs");
             boolean result = theDir.delete();
             if(result) {    
                 System.out.println("DIR removed");  
             }
         }
         if (!theDir.exists()) {
             //System.out.println("creating directory: " + filepath+"/GeneratedGraphs");
             boolean result = theDir.mkdir();
             if(result) { System.out.println("DIR created"); 
             }
         }
         
         theDir = new File("FSM_GRAPHSFILTERED");
         // if the directory  exist delete it
         if (theDir.exists()) {
             //System.out.println("creating directory: " + filepath+"/FSM_GRAPHSFILTERED");
             boolean result = theDir.delete();
             if(result) {    
                 System.out.println("DIR removed");  
             }
         }
         if (!theDir.exists()) {
             //System.out.println("creating directory: " + filepath+"/FSM_GRAPHSFILTERED");
             boolean result = theDir.mkdir();
             if(result) { System.out.println("DIR created"); 
             }
         }
         
         
         File file = new File(filepath);
         
         String list[];
         String sCurrentLine;     
         BufferedReader br = null;
         String global=null;
         
         if(file.isDirectory())
         {
           System.out.println("Given file is a directory "); 
           list=file.list();
           
           for(int i=0;i<list.length;i++)
               
           {            
           
           if(new File(filepath+"/"+list[i]).isFile())
           {System.out.println("\n\nCurrent file="+list[i]); 
            
            G=new Grph();
            Gf=new Grph();
            g=new Graph();
            
            br = new BufferedReader(new FileReader(filepath+"/"+list[i]));
            
            //File r = new File("FSM_GRAPHSFILTERED"+"/" + list[i].substring(0, list[i].indexOf(".")) + ".out");
            //FileWriter fileWriter_red = new FileWriter(r);
            
            //fileWriter_red.write("t # "+list[i].substring(list[i].indexOf("T")+1, list[i].indexOf("."))+"\n");
            
            
            sCurrentLine = br.readLine();
            //System.out.println("ddd"+sCurrentLine);
            
            int si=sCurrentLine.indexOf("\"node_count\":");
            int ei=sCurrentLine.indexOf(",\"edge_count\":");
            //System.out.println(sCurrentLine.substring(si+13, ei));       
            int x =Integer.parseInt(sCurrentLine.substring(si+13, ei));
             
            //separate nodes string    
            sCurrentLine = br.readLine();
            //System.out.println(sCurrentLine);
            si=sCurrentLine.indexOf("["); 
    
            String type=null,name=null,id=null,self_size=null,edge_count=null;
      
            sCurrentLine=sCurrentLine.substring(si+1);
            
            if(sCurrentLine.substring(0,sCurrentLine.indexOf(",")).equalsIgnoreCase("3") | sCurrentLine.substring(0,sCurrentLine.indexOf(",")).equalsIgnoreCase("5"))
               {//System.out.println(sCurrentLine);
                   id = sCurrentLine.substring(sCurrentLine.indexOf(",") + 1);
                   id = id.substring(id.indexOf(",") + 1);
                   id = id.substring(0, id.indexOf(","));
                   // System.out.println(id);
                   if (id.length() < LIMIT)
                   {
                       Gf.addVertexIfNecessary(Integer.parseInt(id));
                   }

                   {
                       //System.out.println(sCurrentLine);
                       id = sCurrentLine.substring(sCurrentLine.indexOf(",") + 1);
                       id = id.substring(id.indexOf(",") + 1);
                       id = id.substring(0, id.indexOf(","));
                       // System.out.println(id);
                       if (id.length() < LIMIT) 
                       {
                           G.addVertexIfNecessary(Integer.parseInt(id));
                       }
                       String substring = sCurrentLine.substring(0, sCurrentLine.indexOf(","));

                       nc1 = new NodeConf();

                       n1 = new Node();
                       nc1.setNodeType(Integer.parseInt(substring));
                       n1.setNodeConf(nc1);

                       if (id.length() < LIMIT) 
                       {
                           n1.setID(Integer.parseInt(id));
                           g.addNode(n1);
                           //fileWriter_red.write("v " + id + " " + substring + "\n");
                       }
                   }
               }

               for (int ij = 1; ij < x; ij++) {
                   sCurrentLine = br.readLine();
                   sCurrentLine = sCurrentLine.substring(1);
                   if (sCurrentLine.substring(0, sCurrentLine.indexOf(",")).equalsIgnoreCase("3") | sCurrentLine.substring(0, sCurrentLine.indexOf(",")).equalsIgnoreCase("5"))
                   {
                       //System.out.println(sCurrentLine); 
                       id = sCurrentLine.substring(sCurrentLine.indexOf(",") + 1);
                       id = id.substring(id.indexOf(",") + 1);
                       id = id.substring(0, id.indexOf(","));
                       //System.out.println(id);
                       if (id.length() < LIMIT)
                       {
                           Gf.addVertexIfNecessary(Integer.parseInt(id));
                           
                           String substring = sCurrentLine.substring(0, sCurrentLine.indexOf(","));
                           
                           nc1 = new NodeConf();
                           n1 = new Node();
                           nc1.setNodeType(Integer.parseInt(substring));
                           n1.setNodeConf(nc1);
                           n1.setID(Integer.parseInt(id));
                           
                           g.addNode(n1);
                           //fileWriter_red.write("v " + id + " " + substring + "\n");
                       }
                       
                   }
               }
               
               //Edges Processing starts here
               sCurrentLine = br.readLine();
               //System.out.println(sCurrentLine);
               sCurrentLine = br.readLine();
               //System.out.println(sCurrentLine);
               sCurrentLine = sCurrentLine.substring(sCurrentLine.indexOf("[") + 1);
               //System.out.println(sCurrentLine);
               String node1 = null, node2 = null;
               
               if (sCurrentLine.substring(0, sCurrentLine.indexOf(",")).equalsIgnoreCase("1") | sCurrentLine.substring(0, sCurrentLine.indexOf(",")).equalsIgnoreCase("2")) 
               {
                   //System.out.println(sCurrentLine);
                   node1 = sCurrentLine.substring(sCurrentLine.indexOf(",") + 1);
                   node2 = node1.substring(node1.indexOf(",") + 1);
                   node1 = node1.substring(0, node1.indexOf(","));
                   //System.out.println("node1="+node1+"\tnod2="+node2);
                   if (node1.length() < LIMIT && node2.length() < LIMIT)
                   {
                       Gf.addDirectedSimpleEdge(Integer.parseInt(node1), Integer.parseInt(node2));        
                   
                       //System.out.println(sCurrentLine);
                       node1 = sCurrentLine.substring(sCurrentLine.indexOf(",") + 1);
                       node2 = node1.substring(node1.indexOf(",") + 1);
                       node1 = node1.substring(0, node1.indexOf(","));

                       //System.out.println("node1="+node1+"\tnod2="+node2);
                       G.addDirectedSimpleEdge(Integer.parseInt(node1), Integer.parseInt(node2));
                       
                   
           
                       if (!g.hasNode(Integer.parseInt(node1))) {
                           n1 = new Node();
                           nc1 = new NodeConf();
                           nc1.setNodeType(3); //set by defult to 3 object node
                           n1.setNodeConf(nc1);
                           n1.setID(Integer.parseInt(node1));
                       }
                       if (!g.hasNode(Integer.parseInt(node2))) {
                           n2 = new Node();
                           nc2 = new NodeConf();
                           nc2.setNodeType(5); //set by defult to 5 closure node
                           n2.setNodeConf(nc2);
                           n2.setID(Integer.parseInt(node2));
                       }
                       
                       ec1 = new EdgeConf();                       
                       String type_e = sCurrentLine.substring(0,sCurrentLine.indexOf(","));
                       ec1.setEdgeType(Integer.parseInt(type_e));
                       e1 = new Edge(n1, n2);
                       e1.setDirection(GraphConstants.DIRECTED);
                       e1.setEdgeConf(ec1);
                       if (g.hasNode(Integer.parseInt(node1)) || g.hasNode(Integer.parseInt(node2))) 
                       {
                           g.addEdge(e1);
                           //fileWriter_red.write("e " + node1 + " " + node2 + " " + type_e + "\n");
                       }                       
                   }
               
               }
               for(;!(sCurrentLine = br.readLine()).equals("],");)
               {sCurrentLine=sCurrentLine.substring(1); 
               if(sCurrentLine.substring(0,sCurrentLine.indexOf(",")).equalsIgnoreCase("1") | sCurrentLine.substring(0,sCurrentLine.indexOf(",")).equalsIgnoreCase("2"))
               { //System.out.println(sCurrentLine);
                   node1 = sCurrentLine.substring(sCurrentLine.indexOf(",") + 1);
                   node2 = node1.substring(node1.indexOf(",") + 1);
                   node1 = node1.substring(0, node1.indexOf(","));

                   //System.out.println("node1="+node1+"\tnod2="+node2);
                   if(node1.length()<LIMIT && node2.length()<LIMIT)
                   {
                       Gf.addDirectedSimpleEdge(Integer.parseInt(node1), Integer.parseInt(node2));
                       //System.out.println(sCurrentLine);
                       node1 = sCurrentLine.substring(sCurrentLine.indexOf(",") + 1);
                       node2 = node1.substring(node1.indexOf(",") + 1);
                       node1 = node1.substring(0, node1.indexOf(","));
                       
                       //System.out.println("node1="+node1+"\tnod2="+node2);
                       G.addDirectedSimpleEdge(Integer.parseInt(node1), Integer.parseInt(node2));
                       
                   
           
                       if (!g.hasNode(Integer.parseInt(node1))) {
                           n1 = new Node();
                           nc1 = new NodeConf();
                           nc1.setNodeType(3); //set by defult to 3 object node
                           n1.setNodeConf(nc1);
                           n1.setID(Integer.parseInt(node1));
                       }
                       if (!g.hasNode(Integer.parseInt(node2))) {
                           n2 = new Node();
                           nc2 = new NodeConf();
                           nc2.setNodeType(5); //set by defult to 5 closure node
                           n2.setNodeConf(nc2);
                           n2.setID(Integer.parseInt(node2));
                       }
                       
                       ec1 = new EdgeConf();                       
                       String type_e = sCurrentLine.substring(0,sCurrentLine.indexOf(","));
                       ec1.setEdgeType(Integer.parseInt(type_e));
                       e1 = new Edge(n1, n2);
                       e1.setDirection(GraphConstants.DIRECTED);
                       e1.setEdgeConf(ec1);
                       if (g.hasNode(Integer.parseInt(node1)) || g.hasNode(Integer.parseInt(node2))) 
                       {
                           g.addEdge(e1);
                           //fileWriter_red.write("e " + node1 + " " + node2 + " " + type_e + "\n");
                       } 


                   }
               }
               }
             
           
            
               saveresult(g,G,Gf,i,list[i]);     
            
           br.close();
           //fileWriter_red.close();
            
           }
           
           }
         }
      }
           public static void main(String args[]) throws FileNotFoundException, IOException{
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new java.io.File("."));
            chooser.setDialogTitle("Select Directory");
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            chooser.setAcceptAllFileFilterUsed(false);
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                System.out.println("getCurrentDirectory(): " + chooser.getCurrentDirectory());
                System.out.println("getSelectedFile() : " + chooser.getSelectedFile());
            } 
            else {
                System.out.println("No Selection "); }    
    
             
            GenerateFilterGraph(chooser.getSelectedFile().toString());                                                                                                                                                                                     savef(chooser.getSelectedFile().getName());
            MergeGraphs(chooser.getSelectedFile().toString());
      
      
        }
  public  void MAIN() throws FileNotFoundException, IOException{
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new java.io.File("."));
            chooser.setDialogTitle("Select Directory");
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            chooser.setAcceptAllFileFilterUsed(false);
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                System.out.println("getCurrentDirectory(): " + chooser.getCurrentDirectory());
                System.out.println("getSelectedFile() : " + chooser.getSelectedFile());
            } 
            else {
                System.out.println("No Selection "); }    
    
             
            GenerateFilterGraph(chooser.getSelectedFile().toString());
            MergeGraphs(chooser.getSelectedFile().toString());
      
      
        }

    private static void saveresult(Graph g, Grph G1, Grph Gf1, int k, String string) throws IOException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        File r = new File("FSM_GRAPHSFILTERED" + "/" + string.substring(0, string.indexOf(".")) + ".lg");
        FileWriter fileWriter_red = new FileWriter(r);
       
        
        Node[] nodesArray = g.getNodesArray();
        Edge[] edgesArray = g.getEdgesArray();
        
          //remove isolated vertices
        //crete new graph to contain only non isolated verices and edges
        Graph g1=new Graph();
        
        //first add edges as required
        for (int i = 0; i < edgesArray.length; i++) {
             
        g1.addEdge(edgesArray[i]);
        }
        //then add vertices as required
        
        for (int i = 0; i < nodesArray.length; i++) {
            for (int j = 0; j < nodesArray.length; j++) {//fileWriter_red.write("v "+Integer.toString(i)+" "+Integer.toString(nodesArray[mm.INDEX[i]].getNodeConf().getNodeType())+"\n");

                if (g1.hasEdge(nodesArray[i].getID(), nodesArray[j].getID())) {
                   g1.addNode(nodesArray[i]);
                }
            }
        }

        
        
        
        nodesArray = g1.getNodesArray();
        edgesArray = g1.getEdgesArray();
        int[] NodeSArrayBeforeSort = new int[nodesArray.length];
        int[] NodeSArrayAfterSort = new int[nodesArray.length];
        //System.out.println("\nBefore Sort\n");
        for (int i = 0; i < nodesArray.length; i++) {
            //System.out.println(nodesArray[i].getNodeConf().getNodeType());
            NodeSArrayBeforeSort[i] = nodesArray[i].getID();
            //System.out.println(NodeSArrayBeforeSort[i]+" "+i);
            
            
        }
        
        //System.out.println("\nAfter Sort\n");
        selectionsort mm = new selectionsort(NodeSArrayBeforeSort);
        //mm.dispaly();
        
        //write node values
        //first graph no
        fileWriter_red.write("# t "+Integer.toString(k)+"\n");
       for (int i = 0; i < nodesArray.length; i++) 
       {
           fileWriter_red.write("v "+Integer.toString(i)+" "+Integer.toString(nodesArray[mm.INDEX[i]].getNodeConf().getNodeType())+"\n");
          //fileWriter_red.write("v "+Integer.toString(mm.x[i])+" "+Integer.toString(nodesArray[mm.INDEX[i]].getNodeConf().getNodeType())+"\n");
       
       }
         //g.dumpToOutput();
       //for edges nodewise
       for (int i = 0; i < nodesArray.length; i++) 
           for (int j = 0; j < nodesArray.length; j++) 
       {//fileWriter_red.write("v "+Integer.toString(i)+" "+Integer.toString(nodesArray[mm.INDEX[i]].getNodeConf().getNodeType())+"\n");
           
          if(g.hasEdge(nodesArray[i].getID(), nodesArray[j].getID()))
          {   Edge edge = g.getEdge(nodesArray[i], nodesArray[j]);
              fileWriter_red.write("e "+Integer.toString(i)+" "+Integer.toString(j)+" "+Integer.toString(edge.getEdgeConf().edgeType)+"\n");
        }
           
       }
       
         
        
        
        fileWriter_red.close();
    }

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

   
   
}
        
