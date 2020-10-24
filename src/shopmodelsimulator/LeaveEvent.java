package shopmodelsimulator;

/**
 * Inherited Event LeaveEvent that process leave the shop by the group of customers after finished
 *
 * @author 12129239-Tikaraj Ghising
 */
public class LeaveEvent extends Event {

    // object creation of CustomerGroup that is about to leave
    private CustomerGroup group;

    // LeaveEvent constructor  initialize the time to super class Event constructor and instance customer group
    public LeaveEvent(int time, CustomerGroup g) {
        super(time);
        this.group = g;
    }

    // override process() method to leave the ice-cream shop by group of customers
    @Override
    public void process(ShopModel shopModel, IScheduler scheduler) {
        shopModel.leave(super.getTime(), group);
    }
}
