import java.util.LinkedList;
import java.util.Queue;

/**
 * CLASS BookingRequestQueue
 *
 * Use Case 5: Booking Request (FIFO)
 *
 * Description:
 * This class manages booking requests
 * using a queue to ensure fair allocation.
 * Requests are processed strictly
 * in the order they are received.
 *
 * @author sk5006-dev
 * @version 5.0
 */
public class BookingRequestQueue {
    private Queue<Reservation> bookingRequests;

    /** Initializes an empty FIFO booking queue. */
    public BookingRequestQueue() {
        bookingRequests = new LinkedList<>();
    }

    /**
     * Adds a new booking request to the queue.
     *
     * @param reservation guest booking request
     */
    public void addRequest(Reservation reservation) {
        bookingRequests.offer(reservation);
    }

    /**
     * Retrieves and removes the next booking request.
     *
     * @return next queued reservation
     */
    public Reservation processNextRequest() {
        return bookingRequests.poll();
    }

    /**
     * Checks whether the queue still contains requests.
     *
     * @return true when requests are waiting
     */
    public boolean hasPendingRequests() {
        return !bookingRequests.isEmpty();
    }
}
