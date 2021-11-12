import java.util.Scanner;

public class ReservationUI{

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
		
		System.out.println("Enter phone number:");
		phone = sc.nextInt();

		System.out.println("ReservationID: " + ReservationManager.createReservation(checkInDateTime, customer, phone, restaurant.getReservationArray()));
		
	}

	public static void reservationViewer(Restaurant restaurant){
		int reservationID;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Reservation ID to check:");
		reservationID = sc.nextInt();

		int result = ReservationManager.checkReservation(reservationID, restaurant.getReservationArray());
		if(result == -1){
			System.out.println("Reservation had been removed");
		}else if(result == -2){
			System.out.println("Reservation not found");
		}else{
			System.out.println("Reservation found. Table number: " + result);
		}
		
	}

	public static void reservationRemover(Restaurant restaurant){
		int reservationID;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Reservation ID to remove:");
		reservationID = sc.nextInt();

		int result = ReservationManager.removeReservation(reservationID, restaurant);
		if(result == -1){
			System.out.println("This reservation had been removed.");
		}else if(result == -2){
			System.out.println("Reservation not found");
		}else{
			System.out.println("Reservation removed. Table number: " + result + " is released.");
		}
		
	}

	public static String promptForCheckInTimeInput(){
		Scanner sc = new Scanner(System.in);
		String input;

		while(true){
			try{
				System.out.println("Enter Check In Time: (YYYY-MM-DDTHH:MM:SS) (-1) to go back to Home Page");
				input = sc.nextLine();
				if(input.equals("-1")) return input;
				if(ReservationManager.validateReservationTime(input) == false){
					System.out.println("Can only make reservation at least 1 hour in advance or within operating hour!");
					continue;
				}
				break;
			}catch (Exception e){
				System.out.println("Invalid format. Please enter again!");
			}
		}
		
		return input;
	}

}