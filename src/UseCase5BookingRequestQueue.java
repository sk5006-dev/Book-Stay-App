/**
 * MAIN CLASS UseCase5BookingRequestQueue
 *
 * Use Case 5: Booking Request (First-Come-First-Served)
 *
 * Description:
 * This class demonstrates how booking
 * requests are accepted and queued
 * in a fair and predictable order.
 * No room allocation or inventory
 * update is performed here.
 *
 * @author sk5006-dev
 * @version 5.0
 */
public class UseCase5BookingRequestQueue {
    /**
     * Application entry point.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        System.out.println("Booking Request Queue");

        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        Reservation r1 = new Reservation("Abhi", "Single");
        Reservation r2 = new Reservation("Subha", "Double");
        Reservation r3 = new Reservation("Vanmathi", "Suite");

        bookingQueue.addRequest(r1);
        bookingQueue.addRequest(r2);
        bookingQueue.addRequest(r3);

        while (bookingQueue.hasPendingRequests()) {
            Reservation reservation = bookingQueue.processNextRequest();
            System.out.println(
                    reservation.getGuestName() + " requested " + reservation.getRoomType() + " Room");
        }
    }
}
