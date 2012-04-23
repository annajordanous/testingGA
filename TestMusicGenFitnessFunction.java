import org.jgap.*;

/**
 * 
 */

/**
 * @author akj20
 *
 */
public class TestMusicGenFitnessFunction extends FitnessFunction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TestMusicGenFitnessFunction()  {
		
	}

	/*
	 * See org.jgap.FitnessFunction#evaluate(org.jgap.IChromosome)
	 */
	@Override
	protected double evaluate(IChromosome chromosome) {
		
		// NB all fitness measures for individual genes are normalised to be between 0 and 1 (inclusive)
		double polyphonyFitness  = getPolyphony(chromosome, 0);
		double numNotesFitness   = getNumNotes(chromosome, 1);
		double keyFitness        = getKey(chromosome, 2);
		double noteRangeFitness  = getNoteRange(chromosome, 3);
		double lowestNoteFitness = getLowestNote(chromosome, 4);
		
		double overallFitness = (polyphonyFitness + numNotesFitness + keyFitness + noteRangeFitness + lowestNoteFitness) / 5.0;
		return overallFitness;
	}

	private double getLowestNote(IChromosome chromosome, int i) {
		return 0.5;
	}

	private double getNoteRange(IChromosome chromosome, int i) {
		return 0.5;
	}

	private double getKey(IChromosome chromosome, int i) {
		return 0.5;
	}

	private double getNumNotes(IChromosome chromosome, int geneNo) {
		// for now maximise numNotes
		double maxNumNotes = (double) TestMusicGen.getMaxNumNotes();
		double fitness;
		Integer chromosomeNumNotes = (Integer) chromosome.getGene(geneNo).getAllele();
		
		fitness = chromosomeNumNotes.doubleValue() / maxNumNotes;
		return fitness;
	}

	private double getPolyphony(IChromosome chromosome, int geneNo) {
		// for now just try to minimise no. of voices
		double maxPolyphony = (double) TestMusicGen.getMaxPolyphony();
		double fitness;
		Integer chromosomePolyphony = (Integer) chromosome.getGene(geneNo).getAllele();
		
		fitness = (maxPolyphony - chromosomePolyphony.doubleValue()) / maxPolyphony;
		return fitness;
	}

}
