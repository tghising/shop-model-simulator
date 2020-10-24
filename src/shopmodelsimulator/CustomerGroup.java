package shopmodelsimulator;

/**
 * CustomerGroup: used to store information about customer group arrive at the shop
 *
 * @author 12129239-Tikaraj Ghising
 */
public class CustomerGroup {

    // declarations and initializations of private member variables
    private int id = 0;
    private int numberInGroup = 0;
    private int arrivalTime = 0;

    public CustomerGroup(int id, int number, int time) {
        this.id = id;
        this.numberInGroup = number;
        this.arrivalTime = time;
    }
    // getter methods
    public int getId() {
        return this.id;
    }

    public int getNumberInGroup() {
        return this.numberInGroup;
    }

    public int getArrivalTime() {
        return this.arrivalTime;
    }

    public String toString() {
        return String.format("Group %d (%d people) arrived at t = %d",this.id,this.numberInGroup,this.arrivalTime);
    }
}
