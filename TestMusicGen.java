import org.jgap.*;
import org.jgap.impl.*;

public class TestMusicGen {

	
	private static final int MAX_ALLOWED_EVOLUTIONS = 200;

	private static int maxPolyphony = 30;  // 30 arbitrarily chosen
	private static int maxNumNotes = 200;  // 200 arbitrarily chosen.
	
	/**
	 * @return the maxPolyphony
	 */
	static int getMaxPolyphony() {
		return maxPolyphony;
	}

	/**
	 * @return the maxNumNotes
	 */
	static int getMaxNumNotes() {
		return maxNumNotes;
	}

	/**
	 * @return
	 * @throws InvalidConfigurationException
	 */
	private static Configuration setUpGAParameters()
			throws InvalidConfigurationException {
		// Set up GA parameters: Default configuration, fitness function, population Size and sample Chromosome.
		Configuration conf = new DefaultConfiguration();
		
		FitnessFunction myFunc = new TestMusicGenFitnessFunction();
		conf.setFitnessFunction(myFunc);

		conf.setPopulationSize(10);
		
		Gene[] sampleGenes = new Gene[5];
		// sampleGenes[0] = new PolphonyGene(conf, 1, maxPolyphony);  // polyphony - no. of parts  
		sampleGenes[0] = new IntegerGene(conf, 1, maxPolyphony);  // polyphony - no. of parts  
		sampleGenes[1] = new IntegerGene(conf, 1, maxNumNotes); // no. of Notes 
		sampleGenes[2] = new IntegerGene(conf, 0, 11);  // key - pitch class // 0 = C, 1 = C#, up to 11 = B
		sampleGenes[3] = new IntegerGene(conf, 1, 128); // note range in MIDI // max possible is 128 i.e. 0-127 // NB gonna have to be careful not to exceed MIDI 127 when implemented
		sampleGenes[4] = new IntegerGene(conf, 0, 127); // bottom note in MIDI nos // max possible is 127 (highest MIDI note)	
	    IChromosome sampleChromosome = new Chromosome(conf, sampleGenes); // not sure why its IChromosome rather than Chromosome
	    conf.setSampleChromosome(sampleChromosome);
		return conf;
	}


	
	/**
	 * @param args
	 * @throws InvalidConfigurationException 
	 */
	public static void main(String[] args) throws Exception {
		
		Configuration conf = setUpGAParameters();
	    // Finished setting up GA parameters
	    
	    // Initialise new population at random
	    Genotype population = Genotype.randomInitialGenotype(conf);
	    
	    // Evolve population over the specified number of evolutions allowed
	    for (int i=0; i<MAX_ALLOWED_EVOLUTIONS; i++)  {      
	    	population.evolve();
	    }

	    System.out.println(population.toString());
	}


}
