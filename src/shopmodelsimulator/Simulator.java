package shopmodelsimulator;

import java.util.ArrayList;

/**
 * Simulator implements IScheduler interface and used to run events, schedule events on the basis of time
 *
 * @author 12129239-Tikaraj Ghising
 */
public class Simulator implements IScheduler {

    // declare an event ArrayList, clock, and a ShopModel objects
    private ArrayList<Event> events;
    private int clock = 0;
    private ShopModel model;

    // Simulator constructor
    public Simulator(ShopModel model) {
        this.model = model;
    }

    // method to initialize ArrayList of events to Simulator
    public void initialize(ArrayList<Event> events) {
        this.events = events;
    }

    // method runs the simulation until stop time, Event queue process according to its time of travel
    public void run(int stopTime) {
        if ((events == null)|| events.isEmpty() )
            return;

        Event e = events.remove(0);
        clock = e.getTime();
        // events queue will never become empty as after the first event is
        // added, every arrival event will generate a new arrival event
        // (which may be greater than the stop time)
        while (clock <= stopTime) {
            e.process(model, this);
            e = events.remove(0);
            clock = e.getTime();
        }
    }

    // method to schedule events by adding events in events queue in order to time of event occur
    public void schedule(Event event) {
        for (int i = 0; i < events.size(); i++) {
            if (event.getTime() < events.get(i).getTime()) {
                events.add(i, event);
                return;
            }
        }
        events.add(event);
    }
}
