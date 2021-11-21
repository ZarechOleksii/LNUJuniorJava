import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(System.in);
		
		Task1.DoTask(in);
		Task2.DoTask(in);
		Task3.DoTask(in);
		in.close();
	}
}
