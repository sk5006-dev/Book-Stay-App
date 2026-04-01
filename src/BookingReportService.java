/**
 * CLASS BookingReportService
 *
 * Use Case 8: Booking History & Reporting
 *
 * Description:
 * This class generates reports
 * from booking history data.
 * Reporting logic is separated
 * from data storage.
 *
 * @author sk5006-dev
 * @version 8.0
 */
public class BookingReportService {
    /**
     * Displays a summary report
     * of all confirmed bookings.
     *
     * @param history booking history
     */
    public void generateReport(BookingHistory history) {
        System.out.println("Booking History Report");
        System.out.println("Total Confirmed Reservations: " + history.getConfirmedReservations().size());

        for (Reservation reservation : history.getConfirmedReservations()) {
            System.out.println(
                    reservation.getGuestName() + " - " + reservation.getRoomType() + " Room");
        }
    }
}
