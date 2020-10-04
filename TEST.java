 
import org.apache.commons.exec.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

 

/**
 * An example based on the tutorial where the user can can safely play with
 * <ul>
 *  <li>blocking or non-blocking print jobs
 *  <li>with print job timeouts to trigger the {@code ExecuteWatchdog}
 *  <li>with the {@code exitValue} returned from the print script
 * </ul>
 *
 * @version $Id: TutorialTest.java 1636056 2014-11-01 21:12:52Z ggregory $
 */
public class TEST {

    /** the directory to pick up the test scripts */
    private final File testDir = new File("/home/sam/Documents/gaston-1.1/gaston-1.1");
   // private final File testDir = new File("/home/sam/Documents/gSpan6");
    /** simulates a PDF print job */
    //private final File acroRd32Script = TestUtil.resolveScriptForOS(testDir + "/gaston");
       private final File acroRd32Script = new File(testDir + "/gaston");
    //private final File acroRd32Script = new File(testDir + "/gSpan");
    public static void main(String args[]){
       TEST nn= new TEST();
        try {
            nn.testTutorialExample();
        } catch (Exception ex) {
            Logger.getLogger(gaston.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void testTutorialExample() throws Exception {
        final long printJobTimeout = 1500000000;
        final boolean printInBackground = false;
        final File pdfFile = new File(testDir + "/Chemical_340");

        TEST.PrintResultHandler printResult;

        try {
            // printing takes around 10 seconds            
            System.out.println("[main] Preparing print job ...");
            printResult = print(pdfFile, printJobTimeout, printInBackground);
            System.out.println("[main] Successfully sent the print job ...");
        }
        catch (final Exception e) {
            e.printStackTrace();
            //fail("[main] Printing of the following document failed : " + pdfFile.getAbsolutePath());
            throw e;
        }

        // come back to check the print result
        System.out.println("[main] Test is exiting but waiting for the print job to finish...");
        printResult.waitFor();
        System.out.println("[main] The print job has finished ...");
    }

    /**
     * Simulate printing a PDF document.
     *
     * @param file the file to print
     * @param printJobTimeout the printJobTimeout (ms) before the watchdog terminates the print process
     * @param printInBackground printing done in the background or blocking
     * @return a print result handler (implementing a future)
     * @throws IOException the test failed
     */
    public TEST.PrintResultHandler print(final File file, final long printJobTimeout, final boolean printInBackground)
            throws IOException {

        int exitValue;
        ExecuteWatchdog watchdog = null;
        TEST.PrintResultHandler resultHandler;

        // build up the command line to using a 'java.io.File'
        final Map<String, File> map = new HashMap<String, File>();
        map.put("file", file);
        final Map<String, File> map1 = new HashMap<String, File>();
        map.put("file1", new File("myoutputinput.txt"));
        
        final CommandLine commandLine = new CommandLine(acroRd32Script);
        commandLine.addArgument("95");
        commandLine.addArgument("-t");
        
        commandLine.addArgument("${file}");
        commandLine.addArgument("${file1}");
                 
        commandLine.setSubstitutionMap(map);

        // create the executor and consider the exitValue '1' as success
        final Executor executor = new DefaultExecutor();
        executor.setExitValue(1);
        
        // create a watchdog if requested
        if (printJobTimeout > 0) {
            watchdog = new ExecuteWatchdog(printJobTimeout);
            executor.setWatchdog(watchdog);
        }

        // pass a "ExecuteResultHandler" when doing background printing
        if (printInBackground) {
            System.out.println("[print] Executing non-blocking print job  ...");
            resultHandler = new PrintResultHandler(watchdog);
            executor.execute(commandLine, resultHandler);
        }
        else {
            System.out.println("[print] Executing blocking print job  ...");
            exitValue = executor.execute(commandLine);
            resultHandler = new PrintResultHandler(exitValue);
        }

        return resultHandler;
    }

    private class PrintResultHandler extends DefaultExecuteResultHandler {

        private ExecuteWatchdog watchdog;

        public PrintResultHandler(final ExecuteWatchdog watchdog)
        {
            this.watchdog = watchdog;
        }

        public PrintResultHandler(final int exitValue) {
            super.onProcessComplete(exitValue);
        }
        
        @Override
        public void onProcessComplete(final int exitValue) {
            super.onProcessComplete(exitValue);
            System.out.println("[resultHandler] The document was successfully printed ...");
        }

        @Override
        public void onProcessFailed(final ExecuteException e) {
            super.onProcessFailed(e);
            if (watchdog != null && watchdog.killedProcess()) {
                System.err.println("[resultHandler] The print process timed out");
            }
            else {
                System.err.println("[resultHandler] The print process failed to do : " + e.getMessage());
            }
        }
    }
}