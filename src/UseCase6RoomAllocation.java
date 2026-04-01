/**
 * MAIN CLASS UseCase6RoomAllocation
 *
 * Use Case 6: Reservation Confirmation & Room Allocation
 *
 * Description:
 * This class demonstrates how booking
 * requests are confirmed and rooms
 * are allocated safely.
 * It consumes booking requests in FIFO
 * order and updates inventory immediately.
 *
 * @author sk5006-dev
 * @version 6.0
 */
public class UseCase6RoomAllocation {
    /**
     * Application entry point.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        System.out.println("Reservation Confirmation & Room Allocation");

        RoomInventory inventory = new RoomInventory();
        BookingRequestQueue bookingQueue = new BookingRequestQueue();
        RoomAllocationService allocationService = new RoomAllocationService();

        bookingQueue.addRequest(new Reservation("Abhi", "Single"));
        bookingQueue.addRequest(new Reservation("Subha", "Double"));
        bookingQueue.addRequest(new Reservation("Vanmathi", "Suite"));
        bookingQueue.addRequest(new Reservation("Arun", "Suite"));
        bookingQueue.addRequest(new Reservation("Divya", "Suite"));

        while (bookingQueue.hasPendingRequests()) {
            Reservation reservation = bookingQueue.processNextRequest();
            allocationService.allocateRoom(reservation, inventory);
        }

        System.out.println("Remaining Inventory");
        System.out.println("Single Room: " + inventory.getRoomAvailability().get("Single Room"));
        System.out.println("Double Room: " + inventory.getRoomAvailability().get("Double Room"));
        System.out.println("Suite Room: " + inventory.getRoomAvailability().get("Suite Room"));
    }
}
