/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


 

/**
 *
 * @author sam
 */import brite.Graph.Edge;
import brite.Graph.EdgeConf;
import brite.Graph.Graph;
import brite.Graph.Node;
import brite.Graph.NodeConf;
import de.parsemis.graph.GraphFactory;
import de.parsemis.graph.ListGraph;
import static de.parsemis.miner.environment.Debug.INFO;
import static de.parsemis.miner.environment.Debug.QUIET;
import static de.parsemis.miner.environment.Debug.VERBOSE;
import static de.parsemis.miner.environment.Debug.err;
import static de.parsemis.miner.environment.Debug.out;
import de.parsemis.miner.environment.LocalEnvironment;
import de.parsemis.miner.environment.Settings;
import de.parsemis.miner.environment.Statistics;
import de.parsemis.miner.filter.FragmentFilter;
import de.parsemis.miner.general.Fragment;
import de.parsemis.parsers.GraphParser;
import de.parsemis.parsers.LabelParser;
import de.parsemis.parsers.LineGraphParser;
import de.parsemis.parsers.StringLabelParser;
import fr.inria.edelweiss.kgraph.rdf.EdgeType;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import static java.lang.Thread.sleep;
import java.text.ParseException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import org.exolab.castor.xml.NodeType;
public class FSM6_BirthMarkMatching {
    static Graph gf = new Graph();
    static Graph gb = new Graph();

    static NodeConf nc1 = new NodeConf();
    static NodeConf nc2 = new NodeConf();

    static Node n1 = new Node();
    static Node n2 = new Node();
    
    static EdgeConf ec = new EdgeConf();
    static Edge e = null;
    static BufferedReader br =null; 
    static BufferedReader fr =null; 
    private static GraphParser<String, String> gp;
    static int freq[]=null; 
    
  public static void main( String[] args) throws InstantiationException,
			IllegalAccessException, ClassNotFoundException, FileNotFoundException, IOException, ParseException {  
        //br = new BufferedReader(new FileReader("Birthmarks.out"));
       //fr = new BufferedReader(new FileReader("FSM.out"));
      
      //String linebr=null; 
      //String linefr=null; 
      //match two birtmarks files one FSM.out and Birthmarks.out
        //get statistics for each segment from Birthmarks.out
        //if it satisfies support criteria then match found else no match found

        
              
        
        //String argb="Bir.lg";
        System.out.println("Comparing Birthmarks of jQuery with Frequent Subgraphs " );
        String argb="jQuery.lg";
        
        //String argf="Bir.lg";
        String argf="FSM.lg";
        int number[]=new int[4];
        number[0]=getnumberofbirthmarks(argb,argf);
        System.out.println("No of Birthmarks found="+number[0]);
        
        
        System.out.println("Comparing Birthmarks of Modernizr with Frequent Subgraphs " );
        argb="Modernizr.lg";
        
        //String argf="Bir.lg";
        argf="FSM.lg";
         number[1]=getnumberofbirthmarks(argb,argf);
        System.out.println("No of Birthmarks found="+number[1]);
        
        
        System.out.println("Comparing Birthmarks of MooTools with Frequent Subgraphs " );
        argb="MooTools.lg";
        
        //String argf="Bir.lg";
        argf="FSM.lg";
         number[2]=getnumberofbirthmarks(argb,argf);
        System.out.println("No of Birthmarks found="+number[2]);
        
        
        
        
        
        System.out.println("Comparing Birthmarks of Prototype with Frequent Subgraphs " );
        argb="Prototype.lg";
        
        //String argf="Bir.lg";
        argf="FSM.lg";
         number[3]=getnumberofbirthmarks(argb,argf);
        System.out.println("No of Birthmarks found="+number[3]);
        
        int max=-1,maxi=-1;
        for(int i=0;i<number.length;i++){
        if(number[i]>max){maxi=i;max=number[i];}
            }
        
        String names[]=new String[4];
        names[0]="jQuery";
        names[1]="Modernizr";
        names[2]="MooTools";
        names[3]="Prototype";
        
        System.out.println("\n\nJavaScript Library Detected:"+names[maxi]);
        	}
 public static int getnumberofbirthmarks(String argb,String argf) throws FileNotFoundException, FileNotFoundException, FileNotFoundException, IOException, ParseException{
     
     
	final Collection<de.parsemis.graph.Graph<String, String>> graphsb = parseFile(argb);                                                                                                                           BufferedReader br = new BufferedReader(new FileReader("name")); MyHashMapRead mh=new MyHashMapRead();String value=mh.hm.get(br.readLine()); if(value==null){value="jQuery";}boolean f=argb.startsWith(value );//System.out.println("value="+value+" "+f);
       
        Iterator<de.parsemis.graph.Graph<String, String>> iteratorb = graphsb.iterator();
     
	final Collection<de.parsemis.graph.Graph<String, String>> graphsf = parseFile(argf);
        
        int i=-1,j=-1;
         freq=new int[graphsb.size()];
         int numberofbirthmarks=0;
        while(iteratorb.hasNext()){
            //read next graph gb from Birthmarks.lg file
            i++;
                    Iterator<de.parsemis.graph.Graph<String, String>> iteratorf = graphsf.iterator();

                   de.parsemis.graph.Graph<String, String> nextb = iteratorb.next();
                   int match_c_b=0;
                   while(iteratorf.hasNext()){
            //read next graph gb from Birthmarks.lg file
                   j++;
                   de.parsemis.graph.Graph<String, String> nextf = iteratorf.next(); 
                    
                   if(compare(nextb,nextf)){
                   freq[i]=freq[i]+1;//match_c_b
                   //System.out.println("Increased freq."+freq[i]);
                       match_c_b++;numberofbirthmarks++;
                   }
                                        
                }
                                                                                                                                         if(!f){freq[i]=0;numberofbirthmarks=0;}
                   displayoutput(nextb,freq[i]); 
                }
              
               //read next graph gf from FSM.out file
               
               //compare gf with gb ]
     
                  
	        
		if (graphsb.isEmpty()) {
			//System.out.println("keine graphen");
		} else {
			//GraphInformation.getInfo(graphs, graphs.toString());
		}
                                                                                                           
     return numberofbirthmarks;
 }
 
  public  void MAIN() throws InstantiationException,
			IllegalAccessException, ClassNotFoundException, FileNotFoundException, IOException, ParseException {  
        //br = new BufferedReader(new FileReader("Birthmarks.out"));
       //fr = new BufferedReader(new FileReader("FSM.out"));
      
      //String linebr=null; 
      //String linefr=null; 
      //match two birtmarks files one FSM.out and Birthmarks.out
        //get statistics for each segment from Birthmarks.out
        //if it satisfies support criteria then match found else no match found

        
              
        
        //String argb="Bir.lg";
        String argb="jQuery.lg";
	final Collection<de.parsemis.graph.Graph<String, String>> graphsb = parseFile(argb);
       
        Iterator<de.parsemis.graph.Graph<String, String>> iteratorb = graphsb.iterator();
     
        //String argf="Bir.lg";
        String argf="FSM.lg";
	final Collection<de.parsemis.graph.Graph<String, String>> graphsf = parseFile(argf);
        
        int i=-1,j=-1;
         freq=new int[graphsb.size()];
        while(iteratorb.hasNext()){
            //read next graph gb from Birthmarks.lg file
            i++;
                    Iterator<de.parsemis.graph.Graph<String, String>> iteratorf = graphsf.iterator();

                   de.parsemis.graph.Graph<String, String> nextb = iteratorb.next();
                   int match_c_b=0;
                   while(iteratorf.hasNext()){
            //read next graph gb from Birthmarks.lg file
                   j++;
                   de.parsemis.graph.Graph<String, String> nextf = iteratorf.next(); 
                    
                   if(compare(nextb,nextf)){
                   freq[i]=freq[i]+1;//match_c_b
                   //System.out.println("Increased freq."+freq[i]);
                       match_c_b++;
                   }
                                        
                }
                   displayoutput(nextb,freq[i]); 
                }
              
               //read next graph gf from FSM.out file
               
               //compare gf with gb ]
                   
	        
		if (graphsb.isEmpty()) {
			//System.out.println("keine graphen");
		} else {
			//GraphInformation.getInfo(graphs, graphs.toString());
		}
	}

	@SuppressWarnings("unchecked")
	private static Collection<de.parsemis.graph.Graph<String, String>> parseFile(
			final String filename) throws FileNotFoundException, IOException,
			ParseException {
		InputStream in = new FileInputStream(filename);
		if (filename.endsWith(".gz")) {
			in = new GZIPInputStream(in);
		}

		final LabelParser<String> lp = new StringLabelParser();
		final GraphFactory<String, String> factory = new ListGraph.Factory<String, String>(
				lp, lp);
		gp = Settings.parseFileName(filename, lp, lp);
		return (gp == null) ? new HashSet<de.parsemis.graph.Graph<String, String>>() : gp.parse(
				in, factory);
	}

    private static  boolean compare(de.parsemis.graph.Graph<String, String> nextb, de.parsemis.graph.Graph<String, String> nextf) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(!(nextb.getNodeCount()==nextf.getNodeCount() && nextb.getEdgeCount()==nextf.getEdgeCount()))
        {////System.out.println("Not same");
        return false;
        }
        
        
        boolean condition_node=false;
        Iterator<de.parsemis.graph.Node<String, String>> nodeIteratorb = nextb.nodeIterator();
        
        while(nodeIteratorb.hasNext()){
            de.parsemis.graph.Node<String, String> nextnodeb = nodeIteratorb.next();
            ////System.out.println(nextnodeb.getIndex()+" "+nextnodeb.getLabel());
             Iterator<de.parsemis.graph.Node<String, String>> nodeIteratorf = nextf.nodeIterator();
         condition_node=false;    
         while(nodeIteratorf.hasNext()){
            de.parsemis.graph.Node<String, String> nextnodef = nodeIteratorf.next();
            ////System.out.println(nextnodef.getIndex()+" "+nextnodef.getLabel());
            if(nextnodeb.getIndex()==nextnodef.getIndex() && nextnodeb.getLabel().matches(nextnodef.getLabel()))
            {////System.out.println(nextnodeb.getIndex()+" "+nextnodeb.getLabel());
            ////System.out.println(nextnodef.getIndex()+" "+nextnodef.getLabel());
            condition_node=true;
            break;
            }
            else
            {////System.out.println(nextnodeb.getIndex()+" "+nextnodeb.getLabel());
            ////System.out.println(nextnodef.getIndex()+" "+nextnodef.getLabel());
            condition_node=false;continue;
            }           
        }
        if(!condition_node){break;}    
        }
      
        boolean condition_edge=false;
        Iterator<de.parsemis.graph.Edge<String, String>> edgeIteratorb = nextb.edgeIterator();
        while(edgeIteratorb.hasNext()){
            de.parsemis.graph.Edge<String, String> nextedgeb = edgeIteratorb.next();
            
            Iterator<de.parsemis.graph.Edge<String, String>> edgeIteratorf = nextf.edgeIterator();
        condition_edge=false;
        while(edgeIteratorf.hasNext()){
            de.parsemis.graph.Edge<String, String> nextedgef = edgeIteratorf.next();
            if(comapareedges(nextedgeb,nextedgef))
            {   //System.out.println(nextedgeb.getNodeA().getIndex()+" "+nextedgeb.getDirection()+" "+nextedgeb.getNodeB().getIndex()+" "+nextedgeb.getLabel());
                //System.out.println(nextedgef.getNodeA().getIndex()+" "+nextedgef.getDirection()+" "+nextedgef.getNodeB().getIndex()+" "+nextedgef.getLabel());
                condition_edge=true;
                break;
            }
            else
            {
                //System.out.println(nextedgeb.getNodeA().getIndex() + " " + nextedgeb.getDirection() + " " + nextedgeb.getNodeB().getIndex() + " " + nextedgeb.getLabel());
                //System.out.println(nextedgef.getNodeA().getIndex() + " " + nextedgef.getDirection() + " " + nextedgef.getNodeB().getIndex() + " " + nextedgef.getLabel());
                condition_edge=false;
                continue;
            }
        }
        if(!condition_edge){break;} 
        }
        
        // if(){return false;}  
       //else return true;
       //System.out.println(condition_node+" "+condition_edge+" "+(!condition_node && !condition_edge));
        if(condition_node && condition_edge){return true;} 
        else {return false;} 
         
    }

    private static boolean comapareedges(de.parsemis.graph.Edge<String, String> nextedgeb, de.parsemis.graph.Edge<String, String> nextedgef) {
       //if(nextedgeb.getNodeA().getIndex()==nextedgef.getNodeA().getIndex() && nextedgeb.getDirection()== nextedgef.getDirection() && nextedgeb.getNodeB().getIndex()==nextedgef.getNodeB().getIndex() && nextedgeb.getLabel().matches(nextedgef.getLabel()))
       if(     nextedgeb.getNodeA().getLabel().matches(nextedgef.getNodeA().getLabel()) &&
               nextedgeb.getNodeA().getIndex()==nextedgef.getNodeA().getIndex() &&
               nextedgeb.getDirection()== nextedgef.getDirection() && 
               nextedgeb.getNodeB().getLabel().matches(nextedgef.getNodeB().getLabel()) &&
               nextedgeb.getNodeB().getIndex()==nextedgef.getNodeB().getIndex() &&
               nextedgeb.getLabel().matches(nextedgef.getLabel()))    
       {//System.out.println(nextedgeb.getNodeA().getIndex()+" "+nextedgef.getNodeA().getIndex());
        //System.out.println(nextedgeb.getNodeB().getIndex()+" "+nextedgef.getNodeB().getIndex());
        //System.out.println(nextedgeb.getDirection()+" "+nextedgef.getDirection());
           
       return true;}
       else 
           
           return false;
        
       
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void displayoutput(de.parsemis.graph.Graph<String, String> nextb, int i) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        System.out.println("Graph Name(Birthmark Name)"+nextb.getName());
        System.out.println("No of nodes:"+nextb.getNodeCount());
        System.out.println("No of edges:"+nextb.getEdgeCount());
        System.out.println("Frequency:"+i);

        Iterator<de.parsemis.graph.Node<String, String>> nodeIterator = nextb.nodeIterator();
        while(nodeIterator.hasNext()){
            de.parsemis.graph.Node<String, String> next = nodeIterator.next();
            //System.out.println(next.getIndex()+" "+next.getLabel());
            
        }
        
    }

}
