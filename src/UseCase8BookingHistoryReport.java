/**
 * MAIN CLASS UseCase8BookingHistoryReport
 *
 * Use Case 8: Booking History & Reporting
 *
 * Description:
 * This class demonstrates how
 * confirmed bookings are stored
 * and reported.
 * The system maintains an ordered
 * audit trail of reservations.
 *
 * @author sk5006-dev
 * @version 8.0
 */
public class UseCase8BookingHistoryReport {
    /**
     * Application entry point.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        BookingHistory history = new BookingHistory();
        BookingReportService reportService = new BookingReportService();

        history.addReservation(new Reservation("Abhi", "Single"));
        history.addReservation(new Reservation("Subha", "Double"));
        history.addReservation(new Reservation("Vanmathi", "Suite"));

        reportService.generateReport(history);
    }
}
