package shopmodelsimulator;

import java.util.ArrayList;
import java.util.Formatter;

/**
 * ShopModel: used to represents the ice-cream shop where people or group of people come(Arrive), order ice-cream and leave the shop
 *
 * @author 12129239-Tikaraj Ghising
 */
public class ShopModel {

    // declarations and initializations of private member variables
    private ArrayList<CustomerGroup> groups = new ArrayList<CustomerGroup>();
    private ArrayList<CustomerGroup> history = new ArrayList<CustomerGroup>();
    
    // private member variable declarations

    private int nextid = 0;
    private int numGroups = 0;
    private int numSeats;
    private int lostBusiness;
    private int numServed;

    public ShopModel(int numSeats) {
        this.numSeats = numSeats;
    }

    // method to record served group of people in the shop
    public void addGroup(CustomerGroup g) {
        groups.add(g);
        // numGroups++;
    }

    // method to record all group of people in the shop to history/log
    public void logGroup(CustomerGroup g) {
        history.add(g);
    }

    // method to generate id (Next id) for new arrival customers in the shop
    public int getNextId() {
        return nextid++;
    }

    // method display the groups still in the shop at the end of the simulation
    public void showGroups(Formatter output) {
        for (int i = 0; i < groups.size(); i++) {
            output.format("%s%n", groups.get(i));
        }
    }

    // method display the log/history of customer groups that arrived in the simulation
    public void showLog(Formatter output) {
        for (int i = 0; i < history.size(); i++) {
            output.format("%s%n", history.get(i));
        }
    }

    // method to serve order for the arrived group of customers in the shop
    // and calculate number of people served in the shop by adding new served customers
    public void serveOrder(int time, CustomerGroup g) {
        System.out.println("t =\t" + time + ": Order served for Group " + g.getId());
        numServed = numServed + g.getNumberInGroup();
    }

    // method to leave for order served finish group of customers, manage groups still in the shop,
    // calculate groups in the shop by decreasing, and available number of seats after leaved by the group of customers in the shop
    public void leave(int time, CustomerGroup group) {
        System.out.println("t =\t" + time + ": Group "+ group.getId() + " leaves");
        groups.remove(group);
        numSeats = numSeats + group.getNumberInGroup();
        // numGroups--; // commented because not used properly in our program specification
    }

    // method to determine group of customers can seat if seats available
    // otherwise, group of people can not seats, and calculate number of lost business
    public boolean canSeat(int time, CustomerGroup group) {
        boolean canSeatGroup = false;
        if(group.getNumberInGroup() <= numSeats) {
            canSeatGroup = true;
            System.out.println("t =\t" + time + ": Group "+ group.getId() + " (" + group.getNumberInGroup() + " people) seated");
            numSeats = numSeats - group.getNumberInGroup();
            numGroups++;
        } else {
            System.out.println("t =\t" + time + ": Group " + group.getId() + " leaves as there are insufficient seats for the group");
            lostBusiness = lostBusiness + group.getNumberInGroup();
        }
        return canSeatGroup;
    }

    // getter methods

    // method to return number of people served
    public int getNumberServed() {
        return numServed;
    }

    // method to return number of people lost by shop in business
    public int getLostBusiness() {
        return lostBusiness;
    }

    /*
    // method to return number of groups still in the shop
    public int getNumGroups() {
        return numGroups;
    }
    */
}

