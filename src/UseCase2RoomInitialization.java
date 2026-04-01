/**
 * MAIN CLASS UseCase2RoomInitialization
 *
 * Use Case 2: Basic Room Types & Static Availability
 *
 * Description:
 * This class demonstrates room initialization
 * using domain models before introducing
 * centralized inventory management.
 * Availability is represented using
 * simple variables to highlight limitations.
 *
 * @author sk5006-dev
 * @version 2.0
 */
public class UseCase2RoomInitialization {
    /**
     * Application entry point.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        Room singleRoom = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suiteRoom = new SuiteRoom();

        int singleRoomAvailability = 5;
        int doubleRoomAvailability = 3;
        int suiteRoomAvailability = 2;

        System.out.println("Hotel Room Initialization");

        System.out.println("Single Room:");
        singleRoom.displayRoomDetails();
        System.out.println("Available: " + singleRoomAvailability);

        System.out.println("Double Room:");
        doubleRoom.displayRoomDetails();
        System.out.println("Available: " + doubleRoomAvailability);

        System.out.println("Suite Room:");
        suiteRoom.displayRoomDetails();
        System.out.println("Available: " + suiteRoomAvailability);
    }
}
