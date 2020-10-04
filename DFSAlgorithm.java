/**
 *	This file is part of Grph.
 *	
 *  Grph is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Grph is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Grph.  If not, see <http://www.gnu.org/licenses/>. *
 */

/**
 * Grph
 * Initial Software by Luc HOGIE, Issam TAHIRI, Aurélien LANCIN, Nathann COHEN, David COUDERT.
 * Copyright © INRIA/CNRS/UNS, All Rights Reserved, 2011, v0.9
 *
 * The Grph license grants any use or destribution of both binaries and source code, if
 * a prior notification was made to the Grph development team.
 * Modification of the source code is not permitted. 
 * 
 *
 */
 

import grph.Grph;
import grph.algo.topology.ClassicalGraphs;
import grph.algo.traversal.GraphSearchListener;
import grph.algo.traversal.TreeBasedTraversal;
import grph.io.GrphBinaryWriter;
import java.io.IOException;
import toools.collections.IntQueueOrStack.ACCESS_MODE;
import toools.io.file.RegularFile;

public class DFSAlgorithm extends TreeBasedTraversal
{
    private static String filepath;
 
    @Override
    protected ACCESS_MODE getAccessMode()
    {
	return ACCESS_MODE.STACK;
    }
    
    public static void main(String[] args) throws IOException
    {
	Grph g1 = new Grph();
        RegularFile   f1 = new RegularFile(filepath+"\\GraphsMerged"+"\\"+"MERGEDGRAPH.dhdf");
            new GrphBinaryWriter().writeGraph(g1, f1);
        
	new DFSAlgorithm().compute(g1, 0, new GraphSearchListener() {

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
    }

}
