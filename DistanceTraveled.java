import java.util.Scanner;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Laura Davis CIS 260 903
 * This program calculates a distance traveled based on 
 * the values speed and max hours as inputed by the user.
 * This program uses a while loop for validation and
 * a for loop to iterate and calculate distance until 
 * max hours is reached. 
 *
 * Minutes must be a double or the division truncates the value.
 * New sets of minutes and hours are used for conversion and final
 * presentation. 
 * Speed must be calculated using double newHours because minutes is
 * the iteration variable and newMinutes is an integer. 
 * For the same reason, maxHours must also be a double. If maxHours
 * is converted into an integer, it is truncated before the math
 * operation is performed. Because maxMinutes is a conversion factor
 * of maxHours, the for loop does not iterate the correct number of 
 * times. Therefore, both must be doubles and later saved into
 * integer variables (newHours, newMinutes) for display purposes. 
 * This program also uses the LocalTIme and DateTimeFormatter classes.
 * These are objects and, like Scanner keyboard, do not have to be 
 * declared at the beginning of the method. Note that even though
 * maxMinutes is passed into the time1 method, it must be converted
 * into a long in order to work mathematically. Note how LocalTime 
 * objects are created and used within that method, and how the 
 * DateTimeFormatter is used in both the method and the print
 * statement in main. Also note that the format for 12 hour day uses
 * 'hh:mm a', whereas the standard 24 hour format uses 'HH:mm'. 
 * 
 */

public class DistanceTraveled
{
	public static void main(String[] args)
	{
		//declare variables
		double distance, maxHours, maxMinutes;
		double minutes, speed, newHours;
		int newMinutes, hours;
		
		//Creates Scanner object and DateTimeFormatter object (from pattern). 
		Scanner keyboard = new Scanner(System.in);
		DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("hh:mm a");
		
		System.out.print("Please enter the speed in MPH: ");
		speed = keyboard.nextDouble();
		
		System.out.print("Please enter the max number of hours: ");
		maxHours = keyboard.nextDouble();
		maxMinutes = maxHours * 60.0; //converts hours into minutes for easier calculations
		
		//calls time1 method and stores return in LocalTime var
		LocalTime arrive = time1(maxMinutes); //creates LocalTime object and calls time1 method. maxMinutes is passed into time1 method. From this method, arrive is returned.
		
		/**
		* note that these print statements use different formatting and are outside of the
		* for loop because they should not be performed with each iteration.
		**/
		System.out.printf("\nAt %.2f MPH, ", speed);
		System.out.printf("the estimated time of arrival is " + dtFormatter.format(arrive)); //note that + is used in this 'printf' instead of ',' because of custom format.
		System.out.println("\n----------------------------------------------------------");
		
		//validation loop
		while (maxHours < 0)
		{
			maxHours = keyboard.nextDouble();
		}
		
		distance = 1.0;
		
		//user-based iteration loop
		for (minutes = 1; minutes <= maxMinutes; minutes++)
		{
			
			/**
			* conversion and distance calculation. This is in the for loop because 
			* it must be performed and printed with each iteration.
			**/
			newHours = (minutes / 60);
			hours = (int)(newHours);
			newMinutes = ((int)(minutes) % 60); 
			distance = speed * newHours;

			//note formatting in these print statements
				System.out.printf("\n%2d Hours %02d Minutes\t" , hours, newMinutes);  
				System.out.print(" = \t");
				System.out.printf("%.2f miles \t", distance);
		}

		}//end of main
	
	/**
	 * maxMinutes is passed to this method from main. 
	 * Takes time input from user in format "hh:mm a". Converts str, parses value and format
	 * converts maxMinutes to long with Math.round, stores time1 plus the long variable to arrive
	 * and finally returns arrive. 
	 * 
	 * @param maxMinutes user entered hours which was converted into minutes and stored in this variable
	 * @return the time of arrival based on the java.time API. 
	 */
	public static LocalTime time1(double maxMinutes)
	{
		//declare variables
		String str;
		long newMaxMinutes;
		
		//create new scanner object
		Scanner keyboard = new Scanner(System.in);
		
		System.out.print("Please enter your time of departure as hh:mm AM or PM: ");
		str = keyboard.nextLine(); //gets user input
		String t = str.toString(); //converts user input
		DateTimeFormatter x = DateTimeFormatter.ofPattern("hh:mm a"); //provides format for user input
		LocalTime time1 = LocalTime.parse(t, x); //parses converted input and format
		newMaxMinutes = Math.round(maxMinutes); //converts maxMinutes to long with Math.round function
		LocalTime arrive = time1.plusMinutes(newMaxMinutes); //adds minutes of maxMinutes to time1 using time class
		
		return arrive;
		
	}//end of time1 method

}//end of class
