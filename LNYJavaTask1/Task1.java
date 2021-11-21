import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

public class Task1 {
	
	public static void DoTask(Scanner in)
	{
		
		System.out.println("Task 1:");
		int width;
		int height;
		while(true)
		{
			try
			{
				System.out.println("Enter width of matrix:");
				width = in.nextInt();
				if (width < 1)
				{
					throw new Exception("Width should be greater than 0");
				}
				System.out.println("Enter height of matrix:");
				height = in.nextInt();
				if (height < 1)
				{
					throw new Exception("Height should be greater than 0");
				}
				break;
			}
			catch (InputMismatchException e)
			{
				System.out.println("Sorry, but the input is not an integer, try again");
				in.next();
			}
			catch (Exception e)
			{
				System.out.println(e.getMessage());
			}
		}
		
		int[][] matrix = new int[height][width];
		
		for(int i = 0; i < height; i++)
		{
			for (int j = 0; j < width; j++)
			{
				System.out.println(String.format("Enter value for element [%s][%s]:", i, j));
				try {
					int value = in.nextInt();
					matrix[i][j] = value;
				}
				catch (InputMismatchException e)
				{
					System.out.println("Sorry, but the input is not an integer, try again");
					in.next();
					j--;
				}
			}
		}
		System.out.println("Resulting matrix:");
		PrintMatrix(matrix);
		
		ArrayList<Integer> vector = new ArrayList<Integer>();
		
		for(int[] row : matrix)
		{
			int maxSum = 0;
			
			for(int element : row)
			{
				ArrayList<Integer> numbers = new ArrayList<Integer>();
				int current = element;
				
				while (current > 0)
				{
					numbers.add(current % 10);
					current = current / 10;
				}
				
				int sum = 0;
				
				for(int number : numbers)
				{
					sum += number;
				}
				
				if (sum > maxSum)
				{
					maxSum = sum;
				}
			}
			vector.add(maxSum);
		}
		System.out.println("Resulting vector:");
		System.out.println(Arrays.toString(vector.toArray()));
		in.nextLine();
	}
	public static void PrintMatrix(int[][] matrix)
	{
		for(int[] row : matrix)
		{
			System.out.println(Arrays.toString(row));
		}
	}
}
