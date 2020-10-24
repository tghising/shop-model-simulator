package shopmodelsimulator;

/**
 * Inherited Event ArrivalEvent schedule the OrderEvent to serve the group and schedule the next ArrivalEvent
 *
 * @author 12129239-Tikaraj Ghising
 */
public class ArrivalEvent extends Event {

    // number of lower and upper limit people in group
    private int groupLowerBound = 1;
    private int groupGeneratorBound = 4;

    // time units lower and upper limit for schedule OrderEvent
    private int orderLowerBound = 1;
    private int orderGeneratorBound = 5;

    // ArrivalEvent constructor  initialize the time to super class Event constructor
    public ArrivalEvent(int time) {
        super(time);
     }

    // override process() method to record group of customerGroup arrival at ice-cream shop, schedule Order, and schedule next group of customerGroup arrival
    @Override
    public void process(ShopModel shopModel, IScheduler scheduler) {

        int peopleNumberInGroup = super.getGenerator().nextInt(groupGeneratorBound - groupLowerBound) + groupLowerBound ;
        int nextId = shopModel.getNextId();

        // CustomerGroup object creation and initialization of group information
        CustomerGroup customerGroup = new CustomerGroup(nextId, peopleNumberInGroup,super.getTime());

        // adding new customer group into the history group and groups
        shopModel.logGroup(customerGroup);

        // display the customer groups information
        System.out.println("t =\t" + customerGroup.getArrivalTime() + ": group " + customerGroup.getId() + " <" + customerGroup.getNumberInGroup() + " people> arrived");

        ArrivalEvent arrivalEvent = new ArrivalEvent(super.getTime() + 2);
        scheduler.schedule(arrivalEvent);

        // if there is available seats for arrived group people, they can seat in the shop
        if (shopModel.canSeat(super.getTime(), customerGroup)) {
            shopModel.addGroup(customerGroup);
            int newOrderGapTime = super.getGenerator().nextInt(orderGeneratorBound - orderLowerBound) + orderLowerBound;
            OrderEvent orderEvent = new OrderEvent(super.getTime() + newOrderGapTime, customerGroup);
            scheduler.schedule(orderEvent);
        }
    }
}
