import brite.Graph.Graph;
import java.io.File;
import java.io.FileWriter;

public class Main {

  public static void main(final String[] args) throws Exception {
   FileWriter fileWriter =new FileWriter(new File("TEST_GRAPH.lg"));
   String line=null; 
   String line1=null; 
   final int CONSTANT=50; 
   fileWriter.write("t # 0"+"\n");
   for(int i=0;i<CONSTANT;i++)
             {
                 //System.out.println(line);
                 line=Integer.toString(i);
                 fileWriter.write("v "+line+" "+line+"\n");
                 
             }
   for(int i=0;i<CONSTANT-1;i++)
             {
                 //System.out.println(line);
                 line=Integer.toString(i);
                 line1=Integer.toString(i+1);
                 
                 fileWriter.write("e "+line+" "+(line1)+" "+line+"\n");
                 
             }
   
   fileWriter.close();
  }
  
      Graph g=null;
      
}