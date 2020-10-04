
 
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
import java.util.Collection;
import java.util.Iterator;
 
//import org.jgrapht.Graph;
//import toools.collections.IntQueueOrStack.ACCESS_MODE;

public class C {
    public static Grph G=null;
    
     private static void GenerateGraph(String filepath) throws FileNotFoundException, IOException {
         // "type","name","id","self_size","edge_count"
         //
         //step 1 Find number of fils in a folder ....
         ///for every file in a folder .....  generate graph G[i]
         File file = new File(filepath);
         
         String list[];
         String sCurrentLine;     
         BufferedReader br = null;
         
         if(file.isDirectory())
         {
           System.out.println("Given file is a directory "); 
           list=file.list();
           
           for(int i=0;i<list.length;i++)
               
           {System.out.println("Current file="+list[i]); 
               
           G=new Grph();
            br = new BufferedReader(new FileReader(filepath+"\\"+list[i]));
            
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
          G.addVertexIfNecessary(Integer.parseInt(id));
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
          G.addVertexIfNecessary(Integer.parseInt(id));
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
             G.addDirectedSimpleEdge(Integer.parseInt(node1), Integer.parseInt(node2));
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
             G.addDirectedSimpleEdge(Integer.parseInt(node1), Integer.parseInt(node2));
   
                
       }
      }
        
            br.close();
            //System.out.println(""+G.getDescription());
            //G.display();
           }
         }
        
         
         
         
        
        System.out.println(""+G.getDescription());
        IntSet isolatedVertices = G.getIsolatedVertices();
        Iterator<IntCursor> iterator = isolatedVertices.iterator();
        while( iterator.hasNext())
        {
             IntCursor next = iterator.next();
             G.removeVertex(next.value);
            //System.out.println (next.value);
        }
         System.out.println(""+G.getDescription());  
         
          G.display();
     }        
 
     public static void main(String args[]) throws FileNotFoundException, IOException{
            String path1="C:\\Users\\sam\\Desktop\\TEST1\\";
             
            GenerateGraph(path1);                   
      
      
        }

   
}
        
