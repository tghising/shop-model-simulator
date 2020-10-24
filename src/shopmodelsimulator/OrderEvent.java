package shopmodelsimulator;

/**
 * Inherited Event OrderEvent serve the group of customers order and schedule LeaveEvent for the group
 *
 * @author tghising
 */
public class OrderEvent extends Event {

    // object creation of CustomerGroup that is about to serve order
    private CustomerGroup group;

    // time units lower and upper limit for leave the shop by group of people
    private int leaveLowerBound = 5;
    private int leaveGeneratorBound = 12;

    // OrderEvent constructor  initialize the time to super class Event constructor and instance customer group
    public OrderEvent(int time, CustomerGroup g) {
        super(time);
        this.group = g;
    }

    // override process() method to serve the order of group of customers arrival at ice-cream shop, schedule to leave the ice-cream shop by the group of customers
    @Override
    public void process(ShopModel shopModel, IScheduler scheduler) {
        shopModel.serveOrder(super.getTime(), group);
        int leaveShopGapTime = super.getGenerator().nextInt(leaveGeneratorBound - leaveLowerBound) + leaveLowerBound;
        LeaveEvent leaveEvent = new LeaveEvent(super.getTime() + leaveShopGapTime, group);
        scheduler.schedule(leaveEvent);
    }
}
