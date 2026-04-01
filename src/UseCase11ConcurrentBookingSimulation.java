/**
 * MAIN CLASS UseCase11ConcurrentBookingSimulation
 *
 * Use Case 11: Concurrent Booking Simulation
 *
 * Description:
 * This class simulates multiple users
 * attempting to book rooms at the same time.
 * It highlights race conditions and
 * demonstrates how synchronization
 * prevents inconsistent allocations.
 *
 * @author sk5006-dev
 * @version 11.0
 */
public class UseCase11ConcurrentBookingSimulation {
    /**
     * Application entry point.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        System.out.println("Concurrent Booking Simulation");

        BookingRequestQueue bookingQueue = new BookingRequestQueue();
        RoomInventory inventory = new RoomInventory();
        RoomAllocationService allocationService = new RoomAllocationService();

        bookingQueue.addRequest(new Reservation("Abhi", "Single"));
        bookingQueue.addRequest(new Reservation("Subha", "Double"));
        bookingQueue.addRequest(new Reservation("Vanmathi", "Suite"));
        bookingQueue.addRequest(new Reservation("Arun", "Suite"));
        bookingQueue.addRequest(new Reservation("Divya", "Suite"));

        Thread t1 = new Thread(
                new ConcurrentBookingProcessor(bookingQueue, inventory, allocationService),
                "Booking-Thread-1");
        Thread t2 = new Thread(
                new ConcurrentBookingProcessor(bookingQueue, inventory, allocationService),
                "Booking-Thread-2");

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println("Thread execution interrupted.");
            Thread.currentThread().interrupt();
        }

        System.out.println("Final Inventory");
        System.out.println("Single Room: " + inventory.getRoomAvailability().get("Single Room"));
        System.out.println("Double Room: " + inventory.getRoomAvailability().get("Double Room"));
        System.out.println("Suite Room: " + inventory.getRoomAvailability().get("Suite Room"));
    }
}
