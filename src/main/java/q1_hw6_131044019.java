/**
 * Created by GozdeDogan on 20.04.2017.
 */
public class q1_hw6_131044019 {
    public static void main(String[] args){
        AirlineCheckinSim sim = new AirlineCheckinSim();
        sim.enterData(1, 10);
        sim.enterData(2, 13);
        sim.enterData(1, 14);
        sim.enterData(1, 15);
        sim.enterData(2, 15);
        sim.enterData(2, 17);
        sim.enterData(1, 20);
        sim.enterData(2, 14);
        sim.enterData(2, 23);
        sim.enterData(1, 25);
        sim.runSimulation();
        sim.showStats();
    }
}
