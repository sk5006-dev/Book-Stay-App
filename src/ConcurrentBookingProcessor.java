/**
 * CLASS ConcurrentBookingProcessor
 *
 * Use Case 11: Concurrent Booking Simulation
 *
 * Description:
 * This class represents a booking processor
 * that can be executed by multiple threads.
 * It demonstrates how shared resources
 * such as booking queues and inventory
 * must be accessed in a thread-safe manner.
 *
 * @author sk5006-dev
 * @version 11.0
 */
public class ConcurrentBookingProcessor implements Runnable {
    /** Shared booking request queue. */
    private BookingRequestQueue bookingQueue;

    /** Shared room inventory. */
    private RoomInventory inventory;

    /** Shared room allocation service. */
    private RoomAllocationService allocationService;

    /**
     * Creates a new booking processor.
     *
     * @param bookingQueue shared booking queue
     * @param inventory shared inventory
     * @param allocationService shared allocation service
     */
    public ConcurrentBookingProcessor(
            BookingRequestQueue bookingQueue,
            RoomInventory inventory,
            RoomAllocationService allocationService
    ) {
        this.bookingQueue = bookingQueue;
        this.inventory = inventory;
        this.allocationService = allocationService;
    }

    /**
     * Executes booking processing logic.
     *
     * This method is called when the thread starts.
     */
    @Override
    public void run() {
        while (true) {
            Reservation reservation;

            synchronized (bookingQueue) {
                if (!bookingQueue.hasPendingRequests()) {
                    return;
                }
                reservation = bookingQueue.processNextRequest();
            }

            synchronized (inventory) {
                allocationService.allocateRoom(reservation, inventory);
            }
        }
    }
}
