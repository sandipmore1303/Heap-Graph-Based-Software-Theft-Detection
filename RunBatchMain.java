public class RunBatchMain {
    //This is Example that display how to run batch using java
    public static void main(String args[])
    {
        //Run batch file using java
        String filePath = "/home/sam/Documents/gaston-1.1/gaston-1.1/gaston.sh";
        try {
             
            Process p = Runtime.getRuntime().exec(filePath);
             
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}