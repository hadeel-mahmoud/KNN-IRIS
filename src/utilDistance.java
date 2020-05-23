
public class utilDistance {

	static double euclideanDistance(Iris iris1, Iris iris2) 
	{

		return Math.sqrt(Math.pow((iris1.getSepalLength() - iris2.getSepalLength()), 2)
				+ Math.pow((iris1.getSepalWidth() - iris2.getSepalWidth()), 2)
				+ Math.pow((iris1.getPetalLength() - iris2.getPetalLength()), 2)
				+ Math.pow((iris1.getPetalWidth() - iris2.getPetalWidth()), 2));

	}

	static double manhattenDistance(Iris iris1, Iris iris2) 
	{
		return Math.abs(iris1.getSepalLength() - iris2.getSepalLength())
				+ Math.abs(iris1.getSepalWidth() - iris2.getSepalWidth())
				+ Math.abs(iris1.getPetalLength() - iris2.getPetalLength())
				+ Math.abs(iris1.getPetalWidth() - iris2.getPetalWidth());

	}

}
