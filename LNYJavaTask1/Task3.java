import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

public class Task3 {
	
	public static class Education{
		public String Name;
		public String Address;
		public int Year;
		
		public Education(String name, String address, int year) {
			Name = name;
			Address = address;
			Year = year;
		}
		
		@Override
	    public String toString()
	    {
	        return this.Name + ", " + this.Address + ", " + this.Year;
	    }
	}
	
	public static class School extends Education{
		public int Students;
		public int Number;
		
		public School(String name, String address, int year, int students, int number) {
			super(name, address, year);
			Students = students;
			Number = number;
		}
		@Override
	    public String toString()
	    {
	        return super.toString() + ", Students: " +this.Students + ", Number: " + this.Number;
	    }
	}
	
	public static class University extends Education{
		public int Level;
		public int Faculties;
		
		public University(String name, String address, int year, int level, int faculties) {
			super(name, address, year);
			Level = level;
			Faculties = faculties;
		}
		
		@Override
	    public String toString()
	    {
	        return super.toString() + ", Level: " +this.Level + ", Faculties: " + this.Faculties;
	    }
	}
	
	public static void DoTask(Scanner in) throws FileNotFoundException
	{
		Gson gson = new Gson();
		JsonReader reader = new JsonReader(new FileReader("src/task3.json"));
		JsonObject objects = gson.fromJson(reader, JsonObject.class);

		ArrayList<Education> educations = new ArrayList<Education>();
		
		School[] schools = gson.fromJson(objects.get("Schools"), School[].class);
		University[] universities = gson.fromJson(objects.get("Universities"), University[].class);
		
		for(School school: schools)
		{
			educations.add(school);
		}
		
		for(University university: universities)
		{
			educations.add(university);
		}
		
		System.out.println("From file:");
		educations.forEach((edu) -> System.out.println(edu));
		
		educations.sort((first, second) -> first.Year-second.Year);
		System.out.println("Sorted by year: ");
		educations.forEach((edu) -> System.out.println(edu));
		
		System.out.println("Least students: ");
		System.out.println(educations.stream()
				.filter((edu) -> edu.getClass() == School.class)
				.min((first, second) -> ((School)first).Students-((School)second).Students).get());
		
		while (true)
		{
			System.out.println("Enter university level (1-4):");
			try
			{
				final int level = in.nextInt();
				
				System.out.println("Universities with " + level + " level:");
				educations.stream()
				.filter((edu) -> edu.getClass() == University.class)
				.filter((edu) -> ((University)edu).Level == level)
				.forEach((edu) -> System.out.println(edu));
				
				break;
			}
			catch (InputMismatchException e)
			{
				System.out.println("Sorry, but the input is not an integer, try again");
				in.next();
			}
		}
	}
}
