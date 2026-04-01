/**
 * MAIN CLASS UseCase4RoomSearch
 *
 * Use Case 4: Room Search & Availability Check
 *
 * Description:
 * This class demonstrates how guests
 * can view available rooms without
 * modifying inventory data.
 * The system enforces read-only access
 * by design and usage discipline.
 *
 * @author sk5006-dev
 * @version 4.0
 */
public class UseCase4RoomSearch {
    /**
     * Application entry point.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        RoomInventory inventory = new RoomInventory();
        Room singleRoom = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suiteRoom = new SuiteRoom();
        RoomSearchService searchService = new RoomSearchService();

        System.out.println("Available Rooms for Booking");
        searchService.searchAvailableRooms(inventory, singleRoom, doubleRoom, suiteRoom);
    }
}
