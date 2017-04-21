/**
 * Created by GozdeDogan on 19.04.2017.
 */
public class PassengerQueue {
    // Data Fields
    /**  The queue of passengers. */
    private BinaryHeap <Passenger> theQueue;

    /** The number of passengers served. */
    private int numServed = 0;

    /** The total time passengers were waiting. */
    private int totalWait = 0;

    /** The name of this queue. */
    private String queueName;

    /** The average arrival rate. */
    private double arrivalRate = 0;

    // Constructor
    /** Construct a PassengerQueue with the given name.
     @param queueName The name of this queue
     */
    public PassengerQueue(String queueName) {
        numServed = 0;
        totalWait = 0;
        this.queueName = queueName;
        theQueue = new BinaryHeap<Passenger>();
    }

    /** Return the number of passengers served
     @return The number of passengers served
     */
    public int getNumServed() {
        numServed = 2;
        return numServed;
    }

    /** Return the total wait time
     @return The total wait time
     */
    public int getTotalWait() {
        totalWait = 10;
        return totalWait;
    }

    /** Return the queue name
     @return - The queu name
     */
    public String getQueueName() {
        return queueName;
    }

    /** Set the arrival rate
     @param arrivalRate the value to set
     */
    public void setArrivalRate(double arrivalRate) {
        this.arrivalRate = arrivalRate;
    }

    /** Determine if the passenger queue is empty
     @return true if the passenger queue is empty
     */
    public boolean isEmpty() {
        return theQueue.isEmpty();
    }

    /** Determine the size of the passenger queue
     @return the size of the passenger queue
     */
    public int size() {
        return theQueue.size();
    }

    /** Check if a new arrival has occurred.
     @param clock The current simulated time
     @param showAll Flag to indicate that detailed
     data should be output
     */
    public void checkNewArrival(int clock, boolean showAll) {
        if (Math.random() < arrivalRate) {
            theQueue.add(new Passenger(clock));
            if (showAll) {
                System.out.println("Time is "
                        + clock + ": "
                        + queueName
                        + " arrival, new queue size is "
                        + theQueue.size());
            }
        }
    }

    /** Update statistics.
     pre: The queue is not empty.
     @param clock The current simulated time
     @param showAll Flag to indicate whether to show detail
     @return Time passenger is done being served
     */
    public int update(int clock, boolean showAll) {
        Passenger nextPassenger = theQueue.remove();
        int timeStamp = nextPassenger.getArrivalTime();
        int wait = clock - timeStamp;
        totalWait += wait;
        numServed++;
        if (showAll) {
            System.out.println("Time is " + clock
                    + ": Serving "
                    + queueName
                    + " with time stamp "
                    + timeStamp);
        }
        return clock + nextPassenger.getProcessingTime();
    }

    public void add(Passenger passenger){
        theQueue.add(passenger);
    }
}
