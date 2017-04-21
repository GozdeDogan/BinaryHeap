/**
 * Created by GozdeDogan on 19.04.2017.
 */
public class AirlineCheckinSim {
    // Data Fields
    /** Queue of frequent flyers. */
    private PassengerQueue frequentFlyerQueue = new PassengerQueue("Frequent Flyer");

    /** Queue of regular passengers. */
    private PassengerQueue regularPassengerQueue = new PassengerQueue("Regular Passenger");

    /** Maximum number of frequent flyers to be served
     before a regular passenger gets served. */
    private int frequentFlyerMax = 0;

    /** Maximum time to service a passenger. */
    private int maxProcessingTime = 0;

    /** Total simulated time. */
    private int totalTime = 0;

    /** If set true, print additional output. */
    private boolean showAll = true;

    /** Simulated clock. */
    private int clock = 0;

    /** Time that the agent will be done with the current passenger.*/
    private int timeDone;

    /** Number of frequent flyers served since the
     last regular passenger was served. */
    private int frequentFlyersSinceRP;

    public void runSimulation() {
        for (clock = 0; clock < totalTime; clock++) {
            frequentFlyerQueue.checkNewArrival(clock, showAll);
            regularPassengerQueue.checkNewArrival(clock, showAll);
            if (clock >= timeDone) {
                startServe();
            }
        }
    }

    private void startServe() {
        if (!frequentFlyerQueue.isEmpty()
                && ( (frequentFlyersSinceRP <= frequentFlyerMax)
                || regularPassengerQueue.isEmpty())) {
            // Serve the next frequent flyer.
            frequentFlyersSinceRP++;
            timeDone = frequentFlyerQueue.update(clock, showAll);
        }
        else if (!regularPassengerQueue.isEmpty()) {
            // Serve the next regular passenger.
            frequentFlyersSinceRP = 0;
            timeDone = regularPassengerQueue.update(clock, showAll);
        }
        else if (showAll) {
            System.out.println("Time is " + clock + " server is idle");
        }
    }

    /** Method to show the statistics. */
    public void showStats() {
        System.out.print("\nThe number of regular passengers served was " + regularPassengerQueue.getNumServed());

        double averageWaitingTime = (double) regularPassengerQueue.getTotalWait() / (double) regularPassengerQueue.getNumServed();
        System.out.println(" with an average waiting time of " + averageWaitingTime);

        System.out.print("The number of frequent flyers served was " + frequentFlyerQueue.getNumServed());

        averageWaitingTime = (double) frequentFlyerQueue.getTotalWait() / (double) frequentFlyerQueue.getNumServed();
        System.out.println(" with an average waiting time of " + averageWaitingTime);

        System.out.println("Passengers in frequent flyer queue: " + frequentFlyerQueue.size());
        System.out.println("Passengers in regular passenger queue: " + regularPassengerQueue.size());

        System.out.println("\n\n" + frequentFlyerQueue.toString() + "\n\n");
        System.out.println(regularPassengerQueue.toString());
    }

    /**
     * type 1 ise regularPassengerQueue'ya, 2 ise frequentFlyerQueue'ya eklenir.
     * @param type
     * @param arrivalTime
     */
    public void enterData(int type, int arrivalTime){
        switch (type){
            case '1':
                regularPassengerQueue.add(new Passenger(arrivalTime));
                break;
            case '2':
                frequentFlyerQueue.add(new Passenger(arrivalTime));
                break;
        }
    }
}
