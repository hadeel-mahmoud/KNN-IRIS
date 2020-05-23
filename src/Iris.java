
public class Iris implements Comparable{

	private double sepalLength;
	private double sepalWidth;
	private double petalLength;
	private double petalWidth;
	private IrisType type;
	private double distance;
	
	public Iris(double sepalLength, double sepalWidth, double petalLength, double petalWidth, IrisType type) {
		super();
		this.sepalLength = sepalLength;
		this.sepalWidth = sepalWidth;
		this.petalLength = petalLength;
		this.petalWidth = petalWidth;
		this.type = type;
	}

	public double getSepalLength() {
		return sepalLength;
	}

	public double getSepalWidth() {
		return sepalWidth;
	}

	public double getPetalLength() {
		return petalLength;
	}

	public double getPetalWidth() {
		return petalWidth;
	}

	public IrisType getType() {
		return type;
	}

	
	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	@Override
	public String toString() {
		return "Iris [sepalLength=" + sepalLength + ", sepalWidth=" + sepalWidth + ", petalLength=" + petalLength
				+ ", petalWidth=" + petalWidth + ", type=" + type + "]";
	}

	@Override

	public int compareTo(Object o) {

		if(this.distance>((Iris)o).distance) {
			return 1;
		}
		else if(this.distance<((Iris)o).distance)
		{
			return -1;
		}
		
		return 0;
	}

	
	
	
	

}
