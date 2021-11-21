import java.util.Scanner;

public class Task2 {
	public static void DoTask(Scanner in)
	{	
		System.out.println("Task 2:");
		System.out.println("Enter task string:");
		String data = in.nextLine();
		
		StringBuilder str = new StringBuilder();
		
		for(char letter : data.toCharArray())
		{
			str.append(letter);
			if (letter == 't')
			{
				str.append('h');
			}
		}
		
		String result = str.toString();
		String[] results = result.split(", ");
		
		System.out.println("Task 2 result:");
		for (String value : results)
		{
			System.out.println(value);
		}
	}
}
