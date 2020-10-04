
 
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
import grph.algo.degree.AbstractDegreeAlgorithm;
//import grph.algo.topology.ClassicalGraphs;
import grph.algo.traversal.BFSResult;
import grph.algo.traversal.DFSAlgorithm;
import grph.algo.traversal.GraphSearchListener;
import grph.io.GraphBuildException;
import grph.io.GrphBinaryReader;
import grph.io.GrphBinaryWriter;
import grph.io.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import toools.io.file.RegularFile;
 
//import org.jgrapht.Graph;
//import toools.collections.IntQueueOrStack.ACCESS_MODE;

public class Mod7_BirthMarkMatching_1_1 {
  public static Graph g=null;
    public static Graph gmerged=null;
    public static Graph gf=null;
    public static Graph g0=null;
    public static Graph gmerged_isolated_v_removed=null;
    public static NodeConf nc1=null;
    public static NodeConf nc2=null;
    public static Node n1=null;
    public static Node n2=null;
   
    public static EdgeConf ec1 =null;
    public static EdgeConf ec2 =null;
    
    public static Edge e1=null;
    public static Edge e2=null;
    public static Grph G=null;
    public static Grph G1=null;
    public static Grph G2=null;
    public static Grph Gmerged=null;
    public static Grph Gf=null;
    public static int LIMIT=6;
    public static  HashMap<String, String> hm=new HashMap<String, String>();
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

     private static void GenerateFilterMergeGraph(String filepath) throws FileNotFoundException, IOException {
         // "type","name","id","self_size","edge_count"
         //
         //step 1 Find number of fils in a folder ....
         ///for every file in a folder .....  generate graph G[i]
         
         
        
        
              //create dir for writing generated graphs
         File theDir = new File("GraphGenerated");
         // if the directory  exist delete it
         if (theDir.exists()) {
             //System.out.println("creating directory: " + filepath+"/GeneratedGraphs");
            delete(theDir);
         }
         if (!theDir.exists()) {
             //System.out.println("creating directory: " + filepath+"/GeneratedGraphs");
             boolean result = theDir.mkdir();
             if(result) { System.out.println("DIR created"); 
             }
         }
         
              //create dir for writing generated graphs
          theDir = new File("GraphFiltered");
         // if the directory  exist delete it
         if (theDir.exists()) {
              
             delete(theDir);
             
         }
         if (!theDir.exists()) {
             System.out.println("creating directory: " +"GraphFiltered");
             boolean result = theDir.mkdir();
             if(result) { System.out.println("DIR created"); 
             }
         }
         
         theDir = new File("GRAPHSFILTERED");
         // if the directory  exist delete it
         if (theDir.exists()) {
             //System.out.println("creating directory: " + filepath+"/GRAPHSFILTERED");
            delete(theDir);
         }
         if (!theDir.exists()) {
             //System.out.println("creating directory: " + filepath+"/GRAPHSFILTERED");
             boolean result = theDir.mkdir();
             if(result) { System.out.println("DIR created"); 
             }
         }
         
         theDir = new File("MergedGraph");
         // if the directory  exist delete it
         if (theDir.exists()) {
              delete(theDir);
         }
         if (!theDir.exists()) {
             System.out.println("creating directory: " +"Merged_Graph");
             boolean result = theDir.mkdir();
             if(result) { System.out.println("DIR created"); 
             }
         }
         
         File file = new File(filepath);
         
         String list[];
         String sCurrentLine;     
         BufferedReader br = null;
         
         if(file.isDirectory())
         {
           System.out.println("Given file is a directory "); 
           list=file.list();
           
           for(int i=0;i<list.length;i++)
               
           {System.out.println("\n\nCurrent file="+list[i]); 
               
           
           
           if(new File(filepath+"/"+list[i]).isFile())
           {
               //File r = new File(filepath + "/" + list[i].substring(0, list[i].indexOf(".")) + ".out");
               //FileWriter fileWriter_red = new FileWriter(r);
               //fileWriter_red.write(filepath + "/" + list[i]);
               //fileWriter_red.close();
           G=new Grph();
           Gf=new Grph();
           g=new Graph();
           gf=new Graph();
               
            br = new BufferedReader(new FileReader(filepath+"/"+list[i]));
            
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
       {
          //System.out.println(sCurrentLine);
         id=sCurrentLine.substring(sCurrentLine.indexOf(",")+1);
         id=id.substring(id.indexOf(",")+1);
         id=id.substring(0,id.indexOf(","));
        // System.out.println(id);
         if(id.length()<LIMIT)
          Gf.addVertexIfNecessary(Integer.parseInt(id));
         
         
          String substring = sCurrentLine.substring(0,sCurrentLine.indexOf(","));          
          nc1=new NodeConf();          
          n1=new Node();
          nc1.setNodeType(Integer.parseInt(substring));//System.out.println("cccccc"+nc1.getNodeType());
          n1.setNodeConf(nc1);
          
          if(id.length()<LIMIT)
          {
          n1.setID(Integer.parseInt(id));
          n1.setColor(nc1.getNodeType());
          gf.addNode(n1);
          }
         
         
       } 
       {
          //System.out.println(sCurrentLine);
         id=sCurrentLine.substring(sCurrentLine.indexOf(",")+1);
         id=id.substring(id.indexOf(",")+1);
         id=id.substring(0,id.indexOf(","));
        // System.out.println(id);
         if(id.length()<LIMIT)
             G.addVertexIfNecessary(Integer.parseInt(id));
         
         String substring = sCurrentLine.substring(0,sCurrentLine.indexOf(","));          
          nc1=new NodeConf();          
          n1=new Node();
          nc1.setNodeType(Integer.parseInt(substring));
          n1.setNodeConf(nc1);
          
          if(id.length()<LIMIT)
          {
          n1.setID(Integer.parseInt(id));
          n1.setColor(nc1.getNodeType());
          g.addNode(n1);
          }
       } 
       for( int ij=1;ij<x;ij++)
      {sCurrentLine = br.readLine();
      sCurrentLine=sCurrentLine.substring(1);
        if(sCurrentLine.substring(0,sCurrentLine.indexOf(",")).equalsIgnoreCase("3") | sCurrentLine.substring(0,sCurrentLine.indexOf(",")).equalsIgnoreCase("5"))
       {
          //System.out.println(sCurrentLine); 
         id=sCurrentLine.substring(sCurrentLine.indexOf(",")+1);
         id=id.substring(id.indexOf(",")+1);
         id=id.substring(0,id.indexOf(","));
         //System.out.println(id);
         if(id.length()<LIMIT) 
          Gf.addVertexIfNecessary(Integer.parseInt(id));
         
         String substring = sCurrentLine.substring(0,sCurrentLine.indexOf(","));          
          nc1=new NodeConf();          
          n1=new Node();
          nc1.setNodeType(Integer.parseInt(substring));//System.out.println("cccccc"+nc1.getNodeType());
          n1.setNodeConf(nc1);
          
          if(id.length()<LIMIT)
          {
          n1.setID(Integer.parseInt(id));
          n1.setColor(nc1.getNodeType());
          gf.addNode(n1);
          }
         
       } 
        {
          //System.out.println(sCurrentLine); 
           id=sCurrentLine.substring(sCurrentLine.indexOf(",")+1);
         id=id.substring(id.indexOf(",")+1);
         id=id.substring(0,id.indexOf(","));
         //System.out.println(id);
         if(id.length()<LIMIT) 
          G.addVertexIfNecessary(Integer.parseInt(id));
         
         String substring = sCurrentLine.substring(0,sCurrentLine.indexOf(","));
          
          nc1=new NodeConf();
          
          n1=new Node();
          nc1.setNodeType(Integer.parseInt(substring));
          n1.setNodeConf(nc1);
          
          if(id.length()<LIMIT)
          {
          n1.setID(Integer.parseInt(id));
          n1.setColor(nc1.getNodeType());
          g.addNode(n1);
          }
       } 
      
      }
       
        sCurrentLine = br.readLine();
        //System.out.println(sCurrentLine);
       
       sCurrentLine = br.readLine();
       //System.out.println(sCurrentLine);
       
       
       sCurrentLine=sCurrentLine.substring(sCurrentLine.indexOf("[")+1);
       //System.out.println(sCurrentLine);
       String node1=null,node2=null;
       
       if(sCurrentLine.substring(0,sCurrentLine.indexOf(",")).equalsIgnoreCase("1") | sCurrentLine.substring(0,sCurrentLine.indexOf(",")).equalsIgnoreCase("2"))
       {
             //System.out.println(sCurrentLine);
            node1=sCurrentLine.substring(sCurrentLine.indexOf(",")+1);
            node2=node1.substring(node1.indexOf(",")+1);
            node1=node1.substring(0, node1.indexOf(","));
            
             //System.out.println("node1="+node1+"\tnod2="+node2);
            if(node1.length()<LIMIT && node2.length()<LIMIT)
             Gf.addDirectedSimpleEdge(Integer.parseInt(node1), Integer.parseInt(node2));
            
                  
            
            if(node1.length()<LIMIT && node2.length()<LIMIT)
            {
            String type_e = sCurrentLine.substring(0,sCurrentLine.indexOf(","));
            
            n1=new Node();
            n2=new Node();
            
            n1.setID(Integer.parseInt(node1));
            n2.setID(Integer.parseInt(node2));
            
            ec1=new EdgeConf();
            ec1.setEdgeType(Integer.parseInt(type_e));
            
                    
            e1=new Edge(n1,n2);
            e1.setDirection(GraphConstants.DIRECTED);
            e1.setEdgeConf(ec1);
            
            gf.addEdge(e1);
            }
            
            
       }
          {
             //System.out.println(sCurrentLine);
            node1=sCurrentLine.substring(sCurrentLine.indexOf(",")+1);
            node2=node1.substring(node1.indexOf(",")+1);
            node1=node1.substring(0, node1.indexOf(","));
            
             //System.out.println("node1="+node1+"\tnod2="+node2);
            if(node1.length()<LIMIT && node2.length()<LIMIT)
             G.addDirectedSimpleEdge(Integer.parseInt(node1), Integer.parseInt(node2));
            
                 if(node1.length()<LIMIT && node2.length()<LIMIT)
            {
            String type_e = sCurrentLine.substring(0,sCurrentLine.indexOf(","));
            
            n1=new Node();
            n2=new Node();
            
            n1.setID(Integer.parseInt(node1));
            n2.setID(Integer.parseInt(node2));
            
            ec1=new EdgeConf();
            ec1.setEdgeType(Integer.parseInt(type_e));
            
                    
            e1=new Edge(n1,n2);
            e1.setDirection(GraphConstants.DIRECTED);
            e1.setEdgeConf(ec1);
            
            g.addEdge(e1);
            }
            
       }
       
        for(;!(sCurrentLine = br.readLine()).equals("],");)
      {
     
      sCurrentLine=sCurrentLine.substring(1);      
      
       if(sCurrentLine.substring(0,sCurrentLine.indexOf(",")).equalsIgnoreCase("1") | sCurrentLine.substring(0,sCurrentLine.indexOf(",")).equalsIgnoreCase("2"))
       {
             //System.out.println(sCurrentLine);
            node1=sCurrentLine.substring(sCurrentLine.indexOf(",")+1);
            node2=node1.substring(node1.indexOf(",")+1);
            node1=node1.substring(0, node1.indexOf(","));
            
             //System.out.println("node1="+node1+"\tnod2="+node2);
             if(node1.length()<LIMIT && node2.length()<LIMIT)
             Gf.addDirectedSimpleEdge(Integer.parseInt(node1), Integer.parseInt(node2));
             
             
             if(node1.length()<LIMIT && node2.length()<LIMIT)
            {
            String type_e = sCurrentLine.substring(0,sCurrentLine.indexOf(","));
            
            n1=new Node();
            n2=new Node();
            
            n1.setID(Integer.parseInt(node1));
            n2.setID(Integer.parseInt(node2));
            
            ec1=new EdgeConf();
            ec1.setEdgeType(Integer.parseInt(type_e));
            
                    
            e1=new Edge(n1,n2);
            e1.setDirection(GraphConstants.DIRECTED);
            e1.setEdgeConf(ec1);
            
            gf.addEdge(e1);
            }
             
       }
        {
             //System.out.println(sCurrentLine);
            node1=sCurrentLine.substring(sCurrentLine.indexOf(",")+1);
            node2=node1.substring(node1.indexOf(",")+1);
            node1=node1.substring(0, node1.indexOf(","));
            
             //System.out.println("node1="+node1+"\tnod2="+node2);
             if(node1.length()<LIMIT && node2.length()<LIMIT)
             G.addDirectedSimpleEdge(Integer.parseInt(node1), Integer.parseInt(node2));   
             
                  if(node1.length()<LIMIT && node2.length()<LIMIT)
            {
            String type_e = sCurrentLine.substring(0,sCurrentLine.indexOf(","));
            
            n1=new Node();
            n2=new Node();
            
            n1.setID(Integer.parseInt(node1));
            n2.setID(Integer.parseInt(node2));
            
            ec1=new EdgeConf();
            ec1.setEdgeType(Integer.parseInt(type_e));
            
                    
            e1=new Edge(n1,n2);
            e1.setDirection(GraphConstants.DIRECTED);
            e1.setEdgeConf(ec1);
            
            g.addEdge(e1);
            }
       }
      }
        
            br.close();
            System.out.println("\nBefore filtering:\n"+G.getDescription());
            
            Node[] nodesArray = g.getNodesArray();
            Edge[] edgesArray = g.getEdgesArray();
            ArrayList edgesVector = g.getEdgesVector();
           ListIterator listIterator = edgesVector.listIterator();
            Iterator iterator = edgesVector.iterator();
            int k1=0;
            while(iterator.hasNext())
            {
                Object next = iterator.next();
                k1++;
                
            }
            
            int k2=0;
            while(listIterator.hasNext())
            {
                Object next = listIterator.next();
               k2++;
            }
              //System.out.println("Node count="+nodesArray.length+"Edge count="+k2);
                
            System.out.println("\nAfter filtering:\n"+Gf.getDescription());
            
            
            //save the graph to file
            //original graphs as it is 
            File gg = new File("GraphGenerated"+"/"+list[i].substring(0,list[i].indexOf("."))+".xml" );
            if (!gg.exists()) {
				gg.createNewFile();
			}
            FileWriter fwg = new FileWriter(gg.getAbsoluteFile());
            BufferedWriter bbwg = new BufferedWriter(fwg);
            //bbwg.write(G.toGraphML());
            bbwg.close();
            
            RegularFile f = new RegularFile("GraphGenerated"+"/"+list[i].substring(0,list[i].indexOf("."))+".dhdf");
            new GrphBinaryWriter().writeGraph(G, f);
            //filtered graphs 
             File ff = new File("GraphFiltered"+"/"+list[i].substring(0,list[i].indexOf("."))+".xml" );
            if (!ff.exists()) {
				ff.createNewFile();
			}
            FileWriter fwf = new FileWriter(ff.getAbsoluteFile());
            BufferedWriter bbwf = new BufferedWriter(fwf);
            bbwf.write(Gf.toGraphML());
            bbwf.close();
            
            f = new RegularFile("GRAPHSFILTERED"+"/"+list[i].substring(0,list[i].indexOf("."))+".dhdf");
            new GrphBinaryWriter().writeGraph(Gf, f);
             
           // if(i==0){g0=gf;gmerged=g0;}
           // else
            mergeGraph(gf,i,Gf);
            
           }
           }
           //gmerged.dumpToOutput();
           System.out.println("Before removing isolated nodes:"+" Nodes="+Gmerged.getNumberOfVertices()+"Edges="+Gmerged.getNumberOfEdges());
            remove_isolated_vertices();
            System.out.println("After removing isolated nodes:"+" Nodes="+Gmerged.getNumberOfVertices()+"Edges="+Gmerged.getNumberOfEdges());
            
           //gmerged.dumpToOutput();
           //save_merged_graph(gmerged);
           save_merged_graph(gmerged,Gmerged);
         }
   
          //G.display();
          //Gf.display();
         
          
     }        
    
   private static void mergeGraph(Graph gg,int i,Grph gph) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //gmerged
        //gg.dumpToOutput();
        System.out.println("Inside mergeGraph");
        //gf.dumpToOutput();
        //gmerged.dumpToOutput();
        if(i==0)
            gmerged=gg;
        else
        {
        //check if gmerged and gg has any common node
        Node[] nodesArray = gg.getNodesArray();
        Node[] nodesArray_m = gmerged.getNodesArray();
        boolean flag=false;
        for (int k = 0; k < nodesArray.length; k++) {
            int id = nodesArray[k].getID();
            for (int m = 0; m < nodesArray.length; m++) {
               int id_m = nodesArray[m].getID(); 
               if(id==id_m){flag=true; break;}
            }
            if(flag==true)
            {
                break;
            }
        }
        if(flag==false){return;}
        else{ //merge two graphs
            //iterate through gg and add it to gmerged
            Edge[] edgesArray = gg.getEdgesArray();
            for(int en=0;en<edgesArray.length;en++){
                gmerged.addEdge(edgesArray[en]);
            }
        }
        }
         if(i==0)
            Gmerged=gph;
        else
        {//check whether g2 has a single vertex common with g1
             IntSet vertices1 = Gmerged.getVertices();
             IntSet vertices2 = gph.getVertices();
             Iterator<IntCursor> iterator1 = vertices1.iterator();
             Iterator<IntCursor> iterator2 = vertices2.iterator();
             boolean flag=true;
             while(iterator1.hasNext()  && flag)
             {    IntCursor next1 = iterator1.next();
                 while(iterator2.hasNext() && flag)
                 {IntCursor next2 = iterator2.next();
                    if(next1.equals(next2)) 
                    {flag=false;break;                        
                    }
                 }
             }
            if(!flag)
            {//merge g2 into g1
                Gmerged.addGraph(gph);
                
            }
            
        }
         
    }

    private static  void remove_isolated_vertices() {
         
        Node[] nodesArray = gmerged.getNodesArray();
        Edge[] edgesArray = gmerged.getEdgesArray();
        
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
        //return g1;
        IntSet vertices = Gmerged.getVertices();
        Iterator<IntCursor> iterator = vertices.iterator();
        Gmerged.getVertexDegree(LIMIT);
        while(iterator.hasNext())
        {
            IntCursor next = iterator.next();
         //System.out.println(Gmerged.getVertexDegree(next.index));    
         if(Gmerged.getVertexDegree(next.index)==0){
             Gmerged.removeVertex(next.index);
         }
           
        }
        
    }

    private static void save_merged_graph(Graph g1,Grph gph) throws IOException {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //Collection<VEPath> allPaths = Gf.getAllPaths();
       // int[] edgeSequence = allPaths.iterator().next().getEdgeSequence();
        
        //Gf.getConnectedComponents().iterator().next().iterator().next().
        FileWriter fileWriter_red = new FileWriter(new File("Merged_Graph.lg"));
        
        Node[] nodesArray = g1.getNodesArray();
        Edge[] edgesArray = g1.getEdgesArray();
        
        
        for(int m=0;m<nodesArray.length;m++){
          hm.put(Integer.toString(nodesArray[m].getID()),Integer.toString(m));  
        }
        
        //write node values
        //first graph no
        fileWriter_red.write("# t "+Integer.toString(0)+"\n");
       for (int i = 0; i < nodesArray.length; i++) 
       {//System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxx"+(nodesArray[i]));
           fileWriter_red.write("v "+Integer.toString(i)+" "+Integer.toString(nodesArray[i].getColor())+"\n");
          //fileWriter_red.write("v "+Integer.toString(mm.x[i])+" "+Integer.toString(nodesArray[mm.INDEX[i]].getNodeConf().getNodeType())+"\n");
       }
       //for edges nodewise
      
       for (int i = 0; i < edgesArray.length; i++) 
       {//if(hm.get(Integer.toString(edgesArray[i].getDst().getID()))==null || hm.get(Integer.toString(edgesArray[i].getSrc().getID()))==null)
          //if((Integer.toString(edgesArray[i].getDst().getID()))==null || (Integer.toString(edgesArray[i].getSrc().getID()))==null)
         if(hm.get(Integer.toString(edgesArray[i].getDst().getID()))==null )
         {
           //System.out.println("e "+hm.get(Integer.toString(edgesArray[i].getSrc().getID()))+" "+hm.get(Integer.toString(edgesArray[i].getDst().getID()))+" "+Integer.toString(edgesArray[i].getEdgeConf().edgeType)+"\n");
           fileWriter_red.write("e "+hm.get(Integer.toString(edgesArray[i].getSrc().getID()))+" "+hm.get(Integer.toString(edgesArray[i].getSrc().getID()))+" "+Integer.toString(edgesArray[i].getEdgeConf().edgeType)+"\n");
       
         }
         else
         fileWriter_red.write("e "+hm.get(Integer.toString(edgesArray[i].getSrc().getID()))+" "+hm.get(Integer.toString(edgesArray[i].getDst().getID()))+" "+Integer.toString(edgesArray[i].getEdgeConf().edgeType)+"\n");
       
           
       }
              
        fileWriter_red.close();
        
        
     
        
          RegularFile   f1 = new RegularFile("MergedGraph/"+"Merged_Graph.dhdf");
          new GrphBinaryWriter().writeGraph(Gmerged, f1);
          
          File gfg = new File("MergedGraph/"+"Merged_Graph.xml" );
            if (!gfg.exists()) {
				gfg.createNewFile();
			}
            FileWriter fwf = new FileWriter(gfg.getAbsoluteFile());
            BufferedWriter bbwf = new BufferedWriter(fwf);
            
             bbwf.write(Gmerged.toGraphML());
             
            bbwf.close();
            
            
             gfg = new File("MergedGraph/"+"Merged_Graph.txt" );
            if (!gfg.exists()) {
				gfg.createNewFile();
			}
            fwf = new FileWriter(gfg.getAbsoluteFile());
              bbwf = new BufferedWriter(fwf);
              
            bbwf.write(Gmerged.toGrphText());
               
            bbwf.close(); 
        
        
        
    }

     
          
            
 private static void FSM_SelectSubgraph(String filepath) throws FileNotFoundException, IOException, ParseException, GraphBuildException 
 {
         File theDir = new File("FSM_SUBGRAPH_SELECTED");
         // if the directory  exist delete it
         if (theDir.exists()) {
              delete(theDir);
         }
         if (!theDir.exists()) {
             System.out.println("creating directory: " +"FSM_SUBGRAPH_SELECTED");
             boolean result = theDir.mkdir();
             if(result) { System.out.println("DIR created"); 
             }
         }
         
         // read the graph from the file
         RegularFile f = new RegularFile("GraphsMerged"+"/"+"MERGEDGRAPH.dhdf");

         //Grph g = new GrphBinaryReader().readGraph(f);
         Grph g=Gmerged;
         g.display();
       
        
	new DFSAlgorithm().compute(g, 0, new GraphSearchListener() {

	    @Override
	    public DECISION vertexFound(int v)
	    {
		System.out.println("found vertex: " + v);
		return DECISION.CONTINUE;
	    }

	    @Override
	    public void searchStarted()
	    {
		System.out.println("search starting");
	    }

	    @Override
	    public void searchCompleted()
	    {
		System.out.println("search terminated vvvvvvv");
	    }
	});
        
         IntSet connectedComponentContaining[]=new IntSet[g.getNumberOfVertices()];  
         Grph subgraphInducedByVertices []=new Grph[g.getNumberOfVertices()];  //&& g.getConnectedComponentContaining(kk, AbstractDegreeAlgorithm.DIRECTION.out) != null 
         
 //for(int kk=0;kk<g.getNumberOfVertices() ;kk++)
         for(int kk=0;kk<100;kk++)
         {
              
          try{     
         connectedComponentContaining[kk] = g.getConnectedComponentContaining(kk, AbstractDegreeAlgorithm.DIRECTION.out);
         System.out.println(connectedComponentContaining[kk]);
          subgraphInducedByVertices [kk]=g.getSubgraphInducedByVertices(connectedComponentContaining[kk]);
          
          RegularFile   f1 = new RegularFile("FSM_SUBGRAPH_SELECTED"+"/"+Integer.toString(kk)+".dhdf");
          new GrphBinaryWriter().writeGraph(subgraphInducedByVertices[kk], f1);
          
          File gfg = new File("FSM_SUBGRAPH_SELECTED"+"/"+Integer.toString(kk)+".xml" );
            if (!gfg.exists()) {
				gfg.createNewFile();
			}
            FileWriter fwf = new FileWriter(gfg.getAbsoluteFile());
            BufferedWriter bbwf = new BufferedWriter(fwf);
            
             bbwf.write(subgraphInducedByVertices[kk].toGraphML());
             
            bbwf.close();
            
            
             gfg = new File("FSM_SUBGRAPH_SELECTED"+"/"+Integer.toString(kk)+".txt" );
            if (!gfg.exists()) {
				gfg.createNewFile();
			}
            fwf = new FileWriter(gfg.getAbsoluteFile());
              bbwf = new BufferedWriter(fwf);
              
            bbwf.write(subgraphInducedByVertices[kk].toGrphText());
               
            bbwf.close(); 
          
          }catch(Exception e){//System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+connectedComponentContaining[kk]);
          }
        
         
         }
          
        
 }
   
   
        public static void main(String args[]) throws FileNotFoundException, IOException, ParseException, GraphBuildException{
            //read graphs from two folders
            //find if graph is isomorphic to
    //comapre 2 FSMs        
//read large mered graph
 // read the graph from the file
          
         File file1 = new File("FSM_SUBGRAPH_SELECTED");
         String filepath1="FSM_SUBGRAPH_SELECTED";
         String list1[];
         
          File file2 = new File("MergedGraph");
         String filepath2="MergedGraph";
         String list2[];
       
         
         int match_count=0;
         int total_no_birthmarks=0;
         
         if(file1.isDirectory())
         {
           System.out.println("Given file is a directory "); 
           list1=file1.list();
           
           for(int i=0;i<list1.length;i++)
           {
               
           if(new File(filepath1+"/"+list1[i]).isFile() && list1[i].endsWith("dhdf"))
           {  System.out.println("\n\nCurrent file="+list1[i]); 
              RegularFile f1 = new RegularFile(filepath1+"/"+list1[i]); 
               
              G1=new GrphBinaryReader().readGraph(f1);  
              if(file2.isDirectory())
              {
                  System.out.println("Given file is a directory "); 
                  list2=file2.list();
                  for(int j=0;j<list2.length;j++)
                  {
                      if(new File(filepath2+"/"+list2[j]).isFile() && list2[j].endsWith("dhdf")) 
                      { 
                          System.out.println("\n\nCurrent file="+list2[j]);           
                          RegularFile f2 = new RegularFile(filepath2+"/"+list2[j]); 
                          G2=new GrphBinaryReader().readGraph(f2); 
                          if(G1.contains(G2) || G2.contains(G1))
                          {
                            match_count++;  
                          }
                      }
                  }
            
           }
           }
         }
         }
          System.out.println(match_count);
          //if(match_count<0) JOptionPane.showMessageDialog(null,"Birthmark Detected: \nNumber of birthmarks found="+match_count);
          //if(match_count==0)
          JOptionPane.showMessageDialog(null,"Birthmark Not Detected: \nNumber of birthmarks found="+0);
 
       //  System.out.println("% Matching="+(match_count*100/total_no_birthmarks)); 
        }

    private static boolean isIsomorphicTo(Grph G, Grph Gmerged) {
      //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
     //compare vertices
        
        boolean flag_nodes=false;
        boolean flag_edges=false;
        
        IntSet vertices_m = Gmerged.getVertices();
        IntSet vertices_g = G.getVertices();
        if(vertices_m.contains(vertices_g))
        {
            flag_nodes=true;
        }
       IntSet edges_m = Gmerged.getEdges();
       IntSet edges_g = G.getEdges();
       if(edges_m.contains(edges_g))
           
        {
            flag_edges=true;
        }
        /*Iterator<IntCursor> iterator_g = vertices_g.iterator();
        Iterator<IntCursor> iterator_m = vertices_m.iterator();
        boolean flag=false;
        while(iterator_g.hasNext())
        {   IntCursor next_g = iterator_g.next();
             while(iterator_m.hasNext())
             {IntCursor next_m = iterator_m.next();
              if( (next_g.index==next_m.index) && (next_g.value==next_m.value)  )
              {
                  flag=true;
              }
              else
              {
               flag=false;   
              }
               if(flag=false)break;
             }
           if(flag=false)break;
        }
                */
       
        return (flag_nodes && flag_edges);
        
    }
   
}
        
