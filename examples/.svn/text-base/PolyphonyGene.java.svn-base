package examples;

import org.jgap.Gene;
import org.jgap.impl.IntegerGene;


public class PolyphonyGene extends IntegerGene implements Gene, java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1822661153309647462L;
	private int numParts;
	
	public PolyphonyGene(Object noOfParts) throws Exception {
			numParts = (Integer) noOfParts;
	}
	
	public Gene newGene()   {
	try  {	
		return new PolyphonyGene(numParts);
	}
	catch (Exception e)  {return null;}
	}
	
	public void setAllele(int newNumParts)  {
		numParts = newNumParts;
	}
	
	public Object getAllele()   {
		return numParts;
	}

}
