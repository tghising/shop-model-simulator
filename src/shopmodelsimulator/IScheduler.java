package shopmodelsimulator;

/**
 * IScheduler interface is implements by simulation to schedule(add new events) on the basis of event occur time
 *
 * @author tghising
 */
public interface IScheduler {

    // method to schedule the event
    public void schedule(Event e);
}
