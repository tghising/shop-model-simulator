package shopmodelsimulator;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;

/**
 * Assignment1v1 is main method() class for ice-cream shop events simulation
 *
 * @author tghising
 */
public class ShopModelSimulator {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {

        final int SIMULATION_STOP_TIME = 20; // Time units to simulation
        final int NUMBER_OF_SEATS_AVAILABLE = 8; // Number of seats available
        final String SIMULATION_STATISTICS_FILE = "statistics.txt"; // simulation's statistics were written in statistics.txt file

        // create a ShopModel object
        ShopModel shopModel = new ShopModel(NUMBER_OF_SEATS_AVAILABLE);

        // create a Simulator object and pass shopModel in constructor parameter
        Simulator simulator = new Simulator(shopModel);

        // create an ArrayList of events
        ArrayList<Event> eventsInShop = new ArrayList<Event>();

        // create first arrival Event
        ArrivalEvent firstArrivalEvent = new ArrivalEvent(0);
        eventsInShop.add(firstArrivalEvent);

        // simulator initialize() method called
        simulator.initialize(eventsInShop);

        System.out.println("Simulation Trace:\n==============");
        // simulator run() method called to run the shop events simulation
        simulator.run(SIMULATION_STOP_TIME);

        // simulation statistics stored on statistics.txt file using Formatter
        try (Formatter outputToFile = new Formatter(SIMULATION_STATISTICS_FILE)) {
            outputToFile.format("Statistics %n==========%n");
            outputToFile.format("The number of people served = %d %n", shopModel.getNumberServed());
            outputToFile.format("The lost business  = %d people %n", shopModel.getLostBusiness());
            // outputToFile.format("%nThe number of groups still in the shop = %d", shopModel.getNumGroups());

            // Display the groups still in the shop at the end of the simulation
            outputToFile.format("%nThe following groups are in the shop:%n=====================================%n");
            shopModel.showGroups(outputToFile);

            // Display the log/history of customer groups that arrived in the simulation
            outputToFile.format("%nThe following groups are in the history/log:%n============================================%n");
            shopModel.showLog(outputToFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
