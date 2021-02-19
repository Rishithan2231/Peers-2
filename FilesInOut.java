import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FilesInOut {
	public static void main(String[] args)
	{

		//declaring and initialising variables. 
		File input = null; //Gets the file path for the initial input of values. 
		File output = null;//Where the initial values are going to be processed and displayed.
		boolean upperCaseOrTitleCase = false; //All of the letters to be upper case or title case. 



		//if statement for if length of the argument is non-existent. 
		if (args.length == 0) {

			//Scanning for the file path for input
			Scanner in = new Scanner(System.in);
			System.out.println("Supply a file path for input of values. ");
			input = new File(in.nextLine());

			//Scanning for file path of output
			System.out.println("Supply a file path for output of values. ");
			output = new File(in.nextLine());

			//Asking for whether the user wants everything upper case or title case
			upperCaseOrTitleCase = uOrT("For upper case press U. For title case press T. ", in); 
			in.close();

			//For loop for what happens to the file based on the conditions above. 
			for (String arg : args) 
			{
				if (arg.equals("-u")) 
				{
					upperCaseOrTitleCase = true;
				} 

				else if (input == null) 
				{
					input = new File(arg);
				} 

				else if (output == null) 
				{
					output = new File(arg);
				}
			}
		}

		else 
		{
			System.out.println("Something is wrong.");

		}



		try (Scanner in = new Scanner(input); PrintWriter out = new PrintWriter(output)) 
		{

			while (in.hasNextLine()) 
			{
				// Separates the list of words into individual components.
				String line = in.nextLine();
				Scanner a = new Scanner(line);
				ArrayList<String> list = new ArrayList<String>();     

				while (a.hasNext()) 
				{
					list.add(a.next());
				}
				a.close();

				//Generates a string builder based on the decision of Upper case or title case.
				StringBuilder name = new StringBuilder();              

				for (int i = 0; i < list.size() - 1; i++) 
				{
					if (upperCaseOrTitleCase) 
					{
						name.append(list.get(i).toUpperCase());
					}
					else 
					{
						name.append(list.get(i).substring(0, 1).toUpperCase()).append(list.get(i).substring(1));
					}

					if (list.get(i).length() == 1) 
					{
						name.append(".");
					}

					name.append(" ");
				}

				//Date Conversion.
				String conversion = list.get(list.size()-1);
				LocalDate date = LocalDate.parse(conversion, DateTimeFormatter.ofPattern("ddMMuuuu"));

				//Output format
				if (upperCaseOrTitleCase)

					out.printf("%1$-20s\t%2$td/%2$tm/%2$tY", name, date);

				if (upperCaseOrTitleCase)
					out.printf("\n");
			}

		} catch (Exception e) 
		{
			System.out.println("Something is wrong");
		}
	} 

	//uOrT scanner
	public static boolean uOrT(String prompt, Scanner in)
	{
		boolean uOrT = false;
		boolean uOrT2 = false;
		String checker;

		do {

				System.out.println(prompt);
				checker = in.nextLine();
				if (checker.length() == 1) 
				{
					switch (checker.toUpperCase()) 
					{
					case "U":
						uOrT2 = true;
						uOrT = true;
						break;
					case "T":
						uOrT = true;
						break;
					default:
						break;
					}
				}
			
		} while(!uOrT);

		return uOrT2;
	}
} 