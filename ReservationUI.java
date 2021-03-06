import java.util.Scanner;
import java.time.DateTimeException;
import java.util.InputMismatchException;

/**
 * The type Reservation ui.
 */
public class ReservationUI{

	/**
	 * Reservation creator ui.
	 *
	 * @param restaurant the restaurant
	 */
	public static void reservationCreator(Restaurant restaurant){
		Scanner sc = new Scanner(System.in);
		int noOfPax, phone;
		String name, checkInDateTime;

		name = CustomerUI.promptForCustomerNameInput();
		noOfPax = CustomerUI.promptForNoOfPaxInput();

		
		checkInDateTime = promptForCheckInTimeInput();
		if(checkInDateTime.equals("-1")){
			System.out.println("Action cancelled. Going back to Home Page...");
			
			return;
		}
		
		Customer customer = CustomerManager.assignNewCustomerToTable(name, noOfPax, restaurant.getTableArray(), restaurant.getCustomerArray());

		if(customer == null){
			System.out.println("Sorry! No suitable empty tables available!");
			System.out.println("Going back to Home Page...");
			
			return;
		}else{
			System.out.println("Customer " + customer.getName() + " has been assigned to table number " + customer.getTable().getTableID());
		}
		
		phone =promptForPhoneInput();

		System.out.println("ReservationID: " + ReservationManager.createReservation(checkInDateTime, customer, phone, restaurant.getReservationArray()));
		
	}

	/**
	 * Reservation viewer ui.
	 *
	 * @param restaurant the restaurant
	 */
	public static void reservationViewer(Restaurant restaurant){
		int reservationID;
		Scanner sc = new Scanner(System.in);
		System.out.println("Check Reservation");
		reservationID = promptForReservationIDInput();

		int result = ReservationManager.checkReservation(reservationID, restaurant.getReservationArray());
		if(result == -1){
			System.out.println("Reservation had been removed");
		}else if(result == -2){
			System.out.println("Reservation not found");
		}else{
			System.out.println("Reservation found. Table number: " + result);
		}
	}

	/**
	 * Reservation remover ui.
	 *
	 * @param restaurant the restaurant
	 */
	public static void reservationRemover(Restaurant restaurant){
		int reservationID;
		Scanner sc = new Scanner(System.in);
		System.out.println("Remove Reservation");
		reservationID = promptForReservationIDInput();

		int result = ReservationManager.removeReservation(reservationID, restaurant.getReservationArray());
		if(result == -1){
			System.out.println("This reservation had been removed.");
		}else if(result == -2){
			System.out.println("Reservation not found");
		}else{
			System.out.println("Reservation removed. Table number: " + result + " is released.");
		}
		
	}

	/**
	 * Prompt for check in time input string.
	 *
	 * @return the check in time input string
	 */
	public static String promptForCheckInTimeInput(){
		Scanner sc = new Scanner(System.in);
		String input;

		while(true){
			try{
				System.out.println("Enter Check In Time: (YYYY-MM-DDTHH:MM:SS) (-1) to go back to Home Page");
				input = sc.nextLine();
				if(input.equals("-1")) return input;
				if(ReservationManager.validateReservationTime(input) == false){
					System.out.println("Can only make reservation at least 1 hour (2 minutes for demo) in advance, within operating hour and not beyond tomorrow!");
					continue;
				}
				break;
			}catch (DateTimeException e){
				System.out.println("Invalid format. Please enter again!");
			}
		}
		
		return input;
	}

	/**
	 * Prompt for phone input int.
	 *
	 * @return the phone input int
	 */
	public static int promptForPhoneInput() {
		Scanner sc = new Scanner(System.in);
		int input;
		while(true){
			try{
				System.out.println("Enter Phone Number:");
				input = sc.nextInt();sc.nextLine();
				break;
			}catch (InputMismatchException e){
				System.out.println("Invalid integer. Please enter again!");
				sc.nextLine();
			}
		}
		return input;
	}

	/**
	 * Prompt for reservation id input int.
	 *
	 * @return the reservation id input int
	 */
	public static int promptForReservationIDInput() {
		Scanner sc = new Scanner(System.in);
		int input;
		while(true){
			try{
				System.out.println("Enter Reservation ID:");
				input = sc.nextInt();sc.nextLine();
				break;
			}catch (InputMismatchException e){
				System.out.println("Invalid integer. Please enter again!");
				sc.nextLine();
			}
		}
		return input;
	}

}