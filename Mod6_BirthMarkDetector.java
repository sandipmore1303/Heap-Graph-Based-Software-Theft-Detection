/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sam
 */
 
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sam
 */

/**
 * created May 2, 2006
 * 
 * @by Marc Woerlein (woerlein@informatik.uni-erlangen.de)
 *
 * Copyright 2006 Marc Woerlein
 * ss
 * This file is part of parsemis.
 *
 * Licence: 
 *  LGPL: http://www.gnu.org/licenses/lgpl.html
 *   EPL: http://www.eclipse.org/org/documents/epl-v10.php
 *   See the LICENSE file in the project's top-level directory for details.
 */
//RUN options : --graphFile=F:\citeseer.lg   --outputFile=F:\out.lg  --minimumFrequency=1 --minimumNodeCount=0 --maximumNodeCount=0 --minimumEdgeCount=0 --maximumEdgeCount=0 --findPathsOnly=true  --singleRooted=true 


import static de.parsemis.miner.environment.Debug.INFO;
import static de.parsemis.miner.environment.Debug.QUIET;
import static de.parsemis.miner.environment.Debug.VERBOSE;
import static de.parsemis.miner.environment.Debug.err;
import static de.parsemis.miner.environment.Debug.out;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.Collection;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import de.parsemis.graph.Graph;
import de.parsemis.graph.Node;
import de.parsemis.miner.environment.LocalEnvironment;
import de.parsemis.miner.environment.Settings;
import de.parsemis.miner.environment.Statistics;
import de.parsemis.miner.filter.FragmentFilter;
import de.parsemis.miner.general.Fragment;
import grph.Grph;
import java.util.Iterator;

/**set
 * This class is the user friendly shell around the ParSeMiS algorithms.
 * 
 * You can read in databases and search for frequent discriminative fragments
 * within it.
 * 
 * @author Marc Woerlein (woerlein@informatik.uni-erlangen.de)
 * --graphFile=file
		The file from which the graphs should be read
	--outputFile=file (optional)
		The file to which the found frequent subgraphs should be written ('-' for stdout)
	--swapFile=file (optional)
		A file to temporarly to swap out temporary unused objects

	--minimumFrequency=freq (integer or percentage)
		The minimLabelParser lp=new LabelParser();um frequency a fragment must have to get reported
	--maximumFrequency=freq (integer or percentage) (optional)
		The maximum frequency a fragment can have to get reported
	--minimumNodeCount=int (optional; default: 0)
		The minimum size in nodes a fragment must have to get reported
	--maximumNodeCount=int (optional; default: 0 = all)
		The maximum size in nodes a fragment can have to get reported
	--minimumEdgeCount=int (optional; default: 0)
		The minimum size in edges a fragment must have to get reported
	--maximumEdgeCount=int (optional; default: 0 = all)
		The maximum size in edges a fragment can have to get reported

	--findPathsOnly=true|false (optional; default: false)
		Specifies that only simple paths should be found (and no trees or arbitrary graphs)
	--findTreesOnly=true|false (optional; default: false)
		Specifies that only (undirected) trees (graphs without cycles) should be found
	--singleRooted=true|false (optional; default: false)
		Specifies for directed graph that only single rooted ones should be found
	--connectedFragments=true|false (optional; default: true)
		Specifies that only connected fragments should be found

	--storeEmbeddings=true|false (optional; default: false)
		Specifies that for each fragment all embeddings should be stored
	--storeHierarchicalEmbeddings=true|false (optional; default: false)
		Specifies that for each fragment all embeddings should be stored as a hierarchical structur
	--embeddingBased=true|false (optional; default: false)
		Specifies that the frequency should be calculated embedding based or graph based

	--algorithm=gspan|gaston|dagma (optional; default: gspan)
		Specifies the mining algorithm to be used
	--closeGraph=true|false (optional; default: false)
		Activates fast closed mining as described for CloseGraph
	--subdue=true|false (optional; default: false)
		Specifies fragment filtering as used in SubDue
	--zaretsky=true|false (optional; default: false)
		Specifies fragment filtering to detect fragments as the algorithm of zaretsky

 */
public final class Mod6_BirthMarkDetector {

	/**
	 * to start a search from console
	 * 
	 * @param args
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
      
	public static void main(String[] args) throws InstantiationException,
			IllegalAccessException, ClassNotFoundException {
            
                 final String[] options = new String[] { "--findPathsOnly=false",
			"--findTreesOnly=false" };
                 
                 boolean  isClosed = true;
                 int minimumFrequency=1,typeIndex=1,minimumEdgeCount=5;
                 
                 String selectedFile_getAbsolutePath=null;
                 String outputfilename=null;
                 selectedFile_getAbsolutePath="FSM_MergedGraph1.lg";
                 outputfilename="Birthmarks.lg";
                  String[] set = new String[] {
					"--graphFile=" + selectedFile_getAbsolutePath,"--outputFile="+outputfilename,
					"--minimumFrequency=" + minimumFrequency,"--storeEmbeddings=false","--minimumEdgeCount="+minimumEdgeCount,
					options[typeIndex], "--connectedFragments=" + isClosed,
					"--distribution=visualisation" };
                  
		run(set);
                 
	}
public  void MAIN() throws InstantiationException,
			IllegalAccessException, ClassNotFoundException {
            
                 final String[] options = new String[] { "--findPathsOnly=false",
			"--findTreesOnly=true", "--findPathsOnly=true" };
                 boolean  isClosed = true;
                 int minimumFrequency=1,typeIndex=1,minimumEdgeCount=5;
                 
                 String selectedFile_getAbsolutePath=null;
                 String outputfilename=null;
                 selectedFile_getAbsolutePath="FSM_MergedGraph.lg";
                 outputfilename="Birthmarks.lg";
                  String[] set = new String[] {
					"--graphFile=" + selectedFile_getAbsolutePath,"--outputFile="+outputfilename,
					"--minimumFrequency=" + minimumFrequency,"--storeEmbeddings=false","--minimumEdgeCount="+minimumEdgeCount,
					options[typeIndex], "--connectedFragments=" + isClosed,
					"--distribution=visualisation" };
                  
		run(set);
                 
	}

	/**
	 * starts a separate thread that regularly checks the memory consumption of
	 * the whole program
	 * 
	 * @param stats
	 * @return the thread
	 */
	private static final Thread memoryCheck(final Statistics stats) {
		return new Thread() {
			{
				this.setDaemon(true);
			}

			@Override
			public void run() {
				while (!isInterrupted()) {
					if (!QUIET) {
						out.print("Getting maximal heap size...");
					}
					System.gc();
					stats.maximumHeapSize = Math
							.max(stats.maximumHeapSize, (int) ((Runtime
									.getRuntime().totalMemory() - Runtime
									.getRuntime().freeMemory()) >> 10));
					if (!QUIET) {
						out.println(stats.maximumHeapSize + "kB");
					}

					try {
						sleep(10);
					} catch (final java.lang.InterruptedException e) {
						err.print(e);
					}
				}
			}
		};
	}

	/**
	 * 
	 * @param <NodeType>
	 *            the type of the node labels (will be hashed and checked with
	 *            .equals(..))
	 * @param <EdgeType>
	 *            the type of the edge labels (will be hashed and checked with
	 *            .equals(..))
	 * @param graphs
	 * @param settings
	 * @return a collection of the found frequent fragments
	 */
	public static <NodeType, EdgeType> Collection<Fragment<NodeType, EdgeType>> mine(
			final Collection<Graph<NodeType, EdgeType>> graphs,
			final Settings<NodeType, EdgeType> settings) {


		final Statistics stats = settings.stats;

		// start memoryCheck, if necessary
		Thread t = null;
		if (settings.memoryStatistics) {
			t = memoryCheck(stats);
			t.start();
		}
		// search fragments
		if (INFO) {
			stats.searchTime -= System.currentTimeMillis();
			stats.searchTime2 -= LocalEnvironment.currentCPUMillis();
		}

		final Collection<Fragment<NodeType, EdgeType>> expectedFragments = settings.algorithm
				.initialize(graphs, settings.factory, settings);

		Collection<Fragment<NodeType, EdgeType>> ret = settings.strategy
				.search(settings.algorithm);

		ret.addAll(expectedFragments);

		if (INFO) {
			stats.searchTime += System.currentTimeMillis();
			stats.searchTime2 += LocalEnvironment.currentCPUMillis();
		}
		// filter fragments, if necessary
		final FragmentFilter<NodeType, EdgeType> filter = LocalEnvironment
				.env(settings.strategy).filter;
		if (filter != null) {
			if (INFO) {
				stats.filteringTime -= System.currentTimeMillis();
			}
			ret = filter.filter(ret);
			if (INFO) {
				stats.filteringTime += System.currentTimeMillis();
			}
		}

		// stop memoryCheck, if necessary
		if (t != null) {
			t.interrupt();
		}

		return ret;
	}

	public static final <NodeType, EdgeType> Collection<Graph<NodeType, EdgeType>> parseInput(
			final Settings<NodeType, EdgeType> settings) {
		InputStream in = null;
		if (settings.inputFileName != null) {
			if (settings.inputFileName.equals("-")) {
				in = System.in;
			} else {
				try {
					in = new FileInputStream(settings.inputFileName);
					if (settings.inputFileName.endsWith(".gz")) {
						in = new GZIPInputStream(in);
					}
				} catch (final FileNotFoundException ex) {
					err.println(ex);
				} catch (final IOException ex) {
					err.println(ex);
				}
			}
		}
		if (in == null) {
			err.println("No database is given!");
			System.exit(-1);
		}
		try {
			final Collection<Graph<NodeType, EdgeType>> ret = settings.parser
					.parse(in, settings.factory);
			if (settings.minFreq == null) {
				for (final Graph<NodeType, EdgeType> graph : ret) {
					if (settings.minFreq == null) {
						settings.minFreq = settings.getFrequency(graph);
					} else {
						settings.minFreq.add(settings.getFrequency(graph));
					}
				}
				settings.minFreq.smul(settings.minProzent);
			}
			if (settings.maxFreq == null && settings.maxProzent >= 0) {
				for (final Graph<NodeType, EdgeType> graph : ret) {
					if (settings.maxFreq == null) {
						settings.maxFreq = settings.getFrequency(graph);
					} else {
						settings.maxFreq.add(settings.getFrequency(graph));
					}
				}
				settings.maxFreq.smul(settings.maxProzent);
			}
			if (VERBOSE) {
				out.println("searching on "
						+ ret.size()
						+ " graphs with frequency "
						+ settings.minFreq
						+ (settings.maxFreq != null ? " (maximum "
								+ settings.maxFreq + ")" : ""));
			}
			return ret;
		} catch (final ParseException pe) {
			pe.printStackTrace();
			err.println(pe);
			System.exit(-1);
		} catch (final IOException io) {
			err.println(io);
			System.exit(-1);
		}
		return null; // shall never be reached
	}
 private static <NodeType, EdgeType> void displayOutput(Collection<Fragment<NodeType, EdgeType>> fragments, Settings<NodeType, EdgeType> settings) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    System.out.println("Result of Frequent subgraph Mining and birthmarks detection with given parameters:");
        OutputStream out = System.out;
		 
		if (out != null) {
			try {
				settings.serializer.serializeFragments(out, fragments);
				out.close();
			} catch (final IOException io) {
				err.println(io);
			}
		}
             
            
    }
        
	/**
	 * prints the given fragments to the configured output
	 * 
	 * @param <NodeType>
	 *            the type of the node labels (will be hashed and checked with
	 *            .equals(..))
	 * @param <EdgeType>
	 *            the type of the edge labels (will be hashed and checked with
	 *            .equals(..))
	 * @param fragments
	 * @param settings
	 */
	
        public static final <NodeType, EdgeType> void printOutput(
			final Collection<Fragment<NodeType, EdgeType>> fragments,
			final Settings<NodeType, EdgeType> settings) {
		OutputStream out = null;
		if (settings.outputFileName != null) {
			if (settings.outputFileName.equals("-")) {
				out = System.out;
			} else {
				try {
					out = new FileOutputStream(settings.outputFileName);
					if (settings.outputFileName.endsWith(".gz")) {
						out = new GZIPOutputStream(out);
					}
				} catch (final FileNotFoundException ex) {
					err.println(ex);
				} catch (final IOException ex) {
					err.println(ex);
				}
			}
		}
		if (out != null) {
			try {
				settings.serializer.serializeFragments(out, fragments);
				out.close();
			} catch (final IOException io) {
				err.println(io);
			}
		}
	}

	private static <NodeType, EdgeType> void run(
			final Settings<NodeType, EdgeType> settings) {

		final Statistics stats = settings.stats;
		if (!QUIET) {
			stats.completeTime -= System.currentTimeMillis();
			stats.completeTime2 -= LocalEnvironment.currentCPUMillis();
		}
		// parse graphs
		if (INFO) {
			stats.parseTime -= System.currentTimeMillis();
		}

		final Collection<Graph<NodeType, EdgeType>> graphs = parseInput(settings);
		if (INFO) {
			stats.parseTime += System.currentTimeMillis();
		}
		// mine them
		final Collection<Fragment<NodeType, EdgeType>> fragments = mine(graphs,
				settings);
               
		// write results
		if (INFO) {
			stats.serializeTime -= System.currentTimeMillis();
		}
		printOutput(fragments, settings);
                displayOutput(fragments, settings);
		if (INFO) {
			stats.serializeTime += System.currentTimeMillis();
		}
		if (!QUIET) {
			stats.completeTime += System.currentTimeMillis();
			stats.completeTime2 += LocalEnvironment.currentCPUMillis();
		}

		if (INFO) {
			stats.printTo(out);
		}

		if (!QUIET) {
			if (settings.storeEmbeddings) {
				int numEmbeddings = 0;
				int maxNodes = 0;
				int maxEdges = 0;
				int cyclic = 0;
				for (final Fragment<NodeType, EdgeType> actFrag : fragments) {
					numEmbeddings += actFrag.size();
					final Graph<NodeType, EdgeType> g = actFrag.toGraph();
					if (g.getNodeCount() > maxNodes) {
						maxNodes = actFrag.toGraph().getNodeCount();
					}
					if (g.getEdgeCount() > maxEdges) {
						maxEdges = actFrag.toGraph().getEdgeCount();
					}
					if (g.getNodeCount() - 1 < g.getEdgeCount()) {
						cyclic++;
					}
				}
				out.println("Complete run took "
						+ (settings.stats.completeTime / 1000.0) + " seconds; "
						+ "found " + fragments.size() + " fragments and "
						+ numEmbeddings + " embeddings.");
				out.println("The biggest Fragment has " + maxNodes
						+ " nodes and " + maxEdges + " edge.");
				out.println(cyclic + " cyclic Fragments are found.");
			} else {
				out.println("Complete run took "
						+ (settings.stats.completeTime / 1000.0) + " seconds; "
						+ "found " + fragments.size() + " fragments");
			}
		}

	}

	/**
	 * starts a search with the given parameters
	 * 
	 * @param args
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public final static void run(final String[] args)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException {
		final Settings settings = Settings.parse(args);
		if (settings == null) {
			Settings.printUsage(err);
		} else {
			if (INFO) {
				out.println("running: ");
                                System.out.println("running: ");
				for (int i = 0; i < args.length; i++) {
					System.err.println("args[" + i + "]=" + args[i]);
                                        System.out.println("args[" + i + "]=" + args[i]);
				}
			}
			run(settings);
		}
	}
}
