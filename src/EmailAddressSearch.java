import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class EmailAddressSearch {

	private static final int ADD = 1;
	private static final int SEARCH = 2;
	private static final int QUIT = 3;

	public static void main(String[] args) throws IndexOutOfBoundsException
	{
		ArrayList<String> emailAddresses = new ArrayList<String>();

		Scanner input = new Scanner(System.in);

		// This is the priming read.
		int menu = menuChoice(input);
		while(menu != QUIT)
		{
			if (menu == ADD)
			{
				System.out.println("Enter the email address");
				String email = input.nextLine();
				addNewEmail(emailAddresses, email);
			}
			else if (menu == SEARCH)
			{
				String email = autoComplete(emailAddresses, input);
				if (email != null)
					System.out.println("Found: " + email);
				else
					System.out.println("No matching email was found");
			}
			else
			{
				System.out.println("Unanticipated case");
			}

			// Priming read.
			menu = menuChoice(input);

		} // End of while loop.

	}

	public static String autoComplete(ArrayList<String> emailAddresses, Scanner input)
	{
		System.out.println("Enter the first letters, one at a time");
		String start = "";

		while (true)
		{
			String read = input.nextLine();
			start += read;

			System.out.println(start);

			int count = 0;
			String result = "";

			for (int i = 0; i < emailAddresses.size(); ++i)
			{
				if (emailAddresses.get(i).startsWith(start))
				{
					result = emailAddresses.get(i); // Keep just the last one.
					System.out.println(emailAddresses.get(i));
					++count;
				}
			}

			if (count == 1)
				return result;

			if (count == 0)
				return null;

		}
	}

	public static int menuChoice(Scanner keyboard)
	{
		System.out.println("Please choose from the following menu of choices:");
		System.out.println("1. Enter a new email address");
		System.out.println("2. Find an existing email address");
		System.out.println("3. Quit.");
		System.out.println("What is your choice?");

		int choice = keyboard.nextInt();
		keyboard.nextLine();

		// Allow user to try again.
		while (choice < ADD || choice > QUIT)
		{
			System.out.println("You must choose a value between 1 and 3");
			System.out.println("Please re-enter your choice");
			choice = keyboard.nextInt();
			keyboard.nextLine();
		}

		return choice;
	}

	public static void sortArray(ArrayList<String> emailAddresses)
	{
		Collections.sort(emailAddresses);
	}

	public static void addNewEmail(ArrayList<String> emailAddresses, String insertMe)
	{
		if (Collections.frequency(emailAddresses, insertMe) == 0)
		{
			emailAddresses.add(insertMe);
		}

		else
		{
			System.out.println("That email address has already been inserted");
		}

		sortArray(emailAddresses);

	}

}
