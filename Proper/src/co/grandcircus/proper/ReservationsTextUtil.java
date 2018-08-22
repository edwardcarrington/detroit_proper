// pair programming project with Darby O'Rear and Shontinique Uqdah

package co.grandcircus.proper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class ReservationsTextUtil {

	public static ArrayList<Reservation> readFromFile(String file) {
		ArrayList<Reservation> allReservations = new ArrayList<>();
		String line;

		/*
		 * Uses new feature called try with resources, used as follows, which closes all
		 * resources automatically at the end Note the use of parenthesis (BEFORE THE
		 * BRACES) where the resources are stored
		 */
		try (FileInputStream inFile = new FileInputStream(file); Scanner input = new Scanner(inFile);) {
			// while there is a line to be read, continues reading, converting to country,
			// then adding to list
			while (input.hasNext()) {
				line = input.nextLine();
				allReservations.add(convertLineToReservation(line));
			}

			// Catches exception if file is not found
		} catch (FileNotFoundException ex) {
			System.out.println("\nSorry, file does not exist");
			// Catches any other exceptions
		} catch (IOException ex) {
			System.out.println("\nSorry, there was an error.");
			ex.printStackTrace();
		}

		return allReservations;

	}

	// Convert line from .txt file into a reservation
	public static Reservation convertLineToReservation(String line) {
		// Split string by separator into array of variable values
		String[] pieces = line.split(",");

		// Assign, and convert as needed, each piece of array to corresponding
		// reservation
		// in reservation constructor
		String fullName = pieces[0];
		LocalDate checkIn = LocalDate.parse(pieces[1], DateTimeFormatter.ofPattern("MM/dd/uuuu"));
		LocalDate checkOut = LocalDate.parse(pieces[2], DateTimeFormatter.ofPattern("MM/dd/uuuu"));
		double price = Double.parseDouble(pieces[3]);
		String propertyName = pieces[4];
		Reservation reservation = new Reservation(fullName, checkIn, checkOut, price, propertyName);

		return reservation;
	}

	public static String convertReservationToLine(Reservation reservation) {
		String line = reservation.toString();
		return line;
	}

	// Creates new file path
	public static void createFilePath(String file) {
		// Converts the string path to an actual path object for uses with Files class
		Path path = Paths.get(file);

		// If the path does not exist, creates the path
		if (Files.notExists(path)) {
			try {
				Files.createFile(path);
				System.out.println("\nFile created at " + path.toAbsolutePath());
			} catch (IOException ex) {
				System.out.println("\nAn error has occurred.");
				ex.getStackTrace();
			}
		}
	}

	public static void writeToFile(ArrayList<Reservation> allReservations, String newPath) {

		// Done the new/easy way with automatic resource closing
		try (
				// the false tells the code to overwrite the program as opposed to adding to it
				FileOutputStream outFile = new FileOutputStream(newPath, false);
				PrintWriter output = new PrintWriter(outFile);) {
			// Converts the countries to strings and writes them to a file
			for (Reservation reservation : allReservations) {
				output.println(convertReservationToLine(reservation));
			}
			// Catches exception thrown when file is not found
		} catch (FileNotFoundException ex) {
			System.out.println("\nSorry, the output file does not exist.");

			// Catches all other exceptions
		} catch (IOException ex) {
			System.out.println("\nSorry, something unexpected occurred.");
			ex.printStackTrace();
		}
	}

	// Appends a single line to the file, instead of overwriting the whole thing
	public static void appendToFile(Reservation reservation, String newPath) {

		// Done the new/easy way with automatic resource closing
		try (
				// the false tells the code to overwrite the program as opposed to adding to it
				FileOutputStream outFile = new FileOutputStream(newPath, true);
				PrintWriter output = new PrintWriter(outFile);) {
			output.println(convertReservationToLine(reservation));
		} catch (FileNotFoundException ex) {
			System.out.println("\nSorry, the output file does not exist.");
		} catch (IOException ex) {
			System.out.println("\nSorry, something unexpected occurred.");
			ex.printStackTrace();
		}
	}

}