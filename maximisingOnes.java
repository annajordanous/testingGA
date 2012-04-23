import java.util.Random;

/**
 * @author akj20
 *
 */
public class maximisingOnes {

	static int popSize = 30;
	static int genotype = 10;
	static int[][] popn = new int[popSize][genotype];
	static double[] prob = new double[popSize];
	static Random generator = new Random();

	
	static void initialise_popn()  {
		int i, j;
		for (i=0; i<popSize; i++)  {
			for (j=0; j<genotype; j++)  {
				popn[i][j] = flip_a_bit();
			}
		}
	}
	
	static int flip_a_bit()  {
		return generator.nextInt(2);
	}
	
	static int evaluate(int[] g)  {
		int i, r = 0;
		
		for (i=0; i<genotype; i++)   {
			if (g[i] == 1) 
				r++;
		}
		return r;
	}
	
	static void recombination()  {
		int i;
		int[][] new_popn = new int[popSize][genotype];
		int[] parent1;
		int[] parent2;
		for (i=0; i<popSize; i++)  {
			parent1 = pickParentFromPopn();
			parent2 = pickParentFromPopn();
			new_popn[i] = uniformRecombination(parent1, parent2);
		}
		popn = new_popn;
	}
	
	static int[] pickParentFromPopn() {
		boolean chosen = false;
		int[] parent = new int[genotype];
		double probability;
		int index;
		while(!chosen)  {
			index = generator.nextInt(popSize);
			parent = popn[index];
			probability = prob[index]*generator.nextDouble();
			if (probability>=0.3) chosen=true;
		}
		return parent;
	}

	static int[] uniformRecombination(int[] parent1, int[] parent2)  {
		int[] child = new int[genotype];
		for (int pos = 0; pos<genotype; pos++)  {
			if (pos<(genotype/2))  {
				child[pos] = parent1[pos];
			}
			else child[pos] = parent2[pos];	
		}
		return child;
	}
	
	static void mutate()  {
		int noOfMutations = (int)(generator.nextDouble()*genotype);
		int index, bitIndex;
		for (int i=0; i<noOfMutations; i++)  {
			index = generator.nextInt(popSize);
			bitIndex = generator.nextInt(genotype);
			if (popn[index][bitIndex]==0) 
				popn[index][bitIndex] = 1;
			else popn[index][bitIndex] = 0;
		}
	}
			
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		initialise_popn();
		int i;
		double average_fitness;
		for (int keepGoing=0; keepGoing < 500; keepGoing++)  { 
			average_fitness = 0;
			for (i=0; i<popSize; i++)   {
				//rank selection: fitness = max 10 min 0
				//slope 2.0 - 0.0
				// 10=2.0, 5 = 1.0, 0=0.0
				//so divide scores[i] by 5 for rank selection on 2.0-0.0 slope
				prob[i] = evaluate(popn[i])/5.0;
				average_fitness += evaluate(popn[i]);
			}		
			average_fitness = average_fitness/popSize;
			recombination();
			mutate();
			System.out.println(keepGoing+" - Average Fitness = "+average_fitness);
		}
	}

}
