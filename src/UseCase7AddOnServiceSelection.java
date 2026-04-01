/**
 * MAIN CLASS UseCase7AddOnServiceSelection
 *
 * Use Case 7: Add-On Service Selection
 *
 * Description:
 * This class demonstrates how optional
 * services can be attached to a confirmed
 * booking.
 * Services are added after room allocation
 * and do not affect inventory.
 *
 * @author sk5006-dev
 * @version 7.0
 */
public class UseCase7AddOnServiceSelection {
    /**
     * Application entry point.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        System.out.println("Add-On Service Selection");

        String reservationId = "SUI-1";
        AddOnServiceManager serviceManager = new AddOnServiceManager();

        serviceManager.addService(reservationId, new AddOnService("Breakfast", 500.0));
        serviceManager.addService(reservationId, new AddOnService("Spa", 1500.0));
        serviceManager.addService(reservationId, new AddOnService("Airport Pickup", 800.0));

        System.out.println("Reservation ID: " + reservationId);
        System.out.println("Selected Services:");

        for (AddOnService service : serviceManager.getServicesForReservation(reservationId)) {
            System.out.println(service.getServiceName() + " - " + service.getCost());
        }

        System.out.println("Total Add-On Cost: " + serviceManager.calculateTotalServiceCost(reservationId));
    }
}
