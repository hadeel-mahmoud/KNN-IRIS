import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class main {

	static ArrayList<Iris> irisData = new ArrayList<>();
	static ArrayList<Iris> trainingData = new ArrayList<>();
	static ArrayList<Iris> testingData = new ArrayList<>();
	static double trainingPercentage = 70.0 / 100;
	static final int K = 10;
	public static void main(String[] args) throws FileNotFoundException {

		loadData();
		System.out.println("We have "+irisData.size()+" data lines");
		System.out.println("Occurence of Setosa in source file: "+count(irisData,IrisType.SETOSA));
		System.out.println("Occurence of Versicolor in source file: "+count(irisData,IrisType.VERSICOLOR));
		System.out.println("Occurence of Virginica in in source file: "+count(irisData,IrisType.VIRGINICA));

		
		
		shuffle(); 
		System.out.println("The percentage that will be using for the training "+trainingPercentage*100+"%");
		//Collections.shuffle(irisData);


		int trainingSize = (int) (irisData.size() * trainingPercentage);
		
		

		for (int i = 0; i < trainingSize; i++) {

			trainingData.add(irisData.get(i));

		}

		for (int i = trainingSize; i < irisData.size(); i++) {

			testingData.add(irisData.get(i));
			

		}
		
		System.out.println("length of trained data set: "+trainingSize);
		System.out.println("length of tested data set: "+(irisData.size()-trainingSize));
		
		System.out.println("Occurence of SETOSA in trained list: "+
		count(trainingData,IrisType.SETOSA)
		+ " and testing is: "+ count(testingData,IrisType.SETOSA));
		
		System.out.println("Occurence of VERSICOLOR in trained list: "+
				count(trainingData,IrisType.VERSICOLOR)
				+ " and testing is: "+ count(testingData,IrisType.VERSICOLOR));
		
		System.out.println("Occurence of VIRGINICA in trained list: "+
				count(trainingData,IrisType.VIRGINICA)
				+ " and testing is: "+ count(testingData,IrisType.VIRGINICA));
		
		
		

		

		System.out.println("Model Accuracy is: "+getAccuracy());
		
		//Iris-setosa
		System.out.println(knn(new Iris(5.1,3.5,1.4,0.2, null)));



	}

	public static double getAccuracy() 
	{

		int positivelyPredicted=0;

		for (Iris sample : testingData) 
		{

			IrisType predictionType=knn(sample);
			if(sample.getType().equals(predictionType)) 
			{
				positivelyPredicted++;
			}
			//System.out.println("Original:"+sample.getType()+"Predicted:"+predictionType);
		}

		return ((double)positivelyPredicted)/testingData.size();

	}


	public static IrisType knn(Iris sample) {

		for (Iris irisData : trainingData) {

			//irisData.setDistance(utilDistance.euclideanDistance(sample, irisData));
			irisData.setDistance(utilDistance.manhattenDistance(sample, irisData));


		}

		Collections.sort(trainingData);
		HashMap<IrisType, Integer> hash = new HashMap<>();
		for (int i = 0; i < K; i++) 
		{
			IrisType type = trainingData.get(i).getType();
			if (!hash.containsKey(type)) 
			{
				hash.put(type, 1);
			} 
			else
			{
				hash.put(type, hash.get(type) + 1);
			}

		}
		return Collections.max(hash.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();		




	}

	public static void shuffle() 
	{

		Random r =new Random();

		for (int i = irisData.size()-1; i >0; i--) 
		{
			int j=r.nextInt(i);
			Iris temp=irisData.get(i);
			irisData.set(i, irisData.get(j));
			irisData.set(j, temp);

		}

	}

	public static void loadData() throws FileNotFoundException {
		File myObj = new File(".\\iris.txt");
		Scanner myReader = new Scanner(myObj);
		while (myReader.hasNextLine()) {
			String data = myReader.nextLine();
			if (data.startsWith("#") || data.trim() == "") {
				continue;
			}
			String[] row = data.split(",");

			IrisType type = null;

			switch (row[4].trim().toLowerCase()) {
			case "iris-setosa":
				type = IrisType.SETOSA;
				break;

			case "iris-versicolor":
				type = IrisType.VERSICOLOR;

				break;

			case "iris-virginica":
				type = IrisType.VIRGINICA;

				break;

			}

			irisData.add(new Iris(Double.parseDouble(row[0].trim()), Double.parseDouble(row[1].trim()),
					Double.parseDouble(row[2].trim()), Double.parseDouble(row[3].trim()), type));
		}
		myReader.close();
	}

	public static int count(ArrayList<Iris> list,IrisType targetType) {
		int sum=0;

		for (Iris iris : list) 
		{

			if(iris.getType().equals(targetType)) 
			{
				sum++;
			}
		}
		return sum;


	}

}
