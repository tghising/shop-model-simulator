package shopmodelsimulator;

import java.util.Random;

/**
 * Event: used to driven simulation for ice-cream shop that occur at specified times (arrival, order and leave)
 *
 * @author tghising
 */
public abstract class Event {

    private int time;

    // Random number generator
    private static Random generator = new Random(1);//use a seed of 1

    // Event constructor  initialize the time
    public Event(int time) {
        this.time = time;
    }

    // method to process shop event's
    public abstract void process(ShopModel shopModel , IScheduler scheduler);

    // getters method to return time
    public int getTime(){
        return this.time;
    }

    // getters method to Random generator
    public Random getGenerator() {
        return generator;
    }

}
