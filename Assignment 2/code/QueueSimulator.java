import java.lang.*;

public class QueueSimulator{
  public enum Event { ARRIVAL, DEPARTURE };
  private double currTime;
  private double arrivalRate;
  private double serviceTime;
  private double timeForNextArrival;
  private double timeForNextDeparture;
  private double totalSimTime;
  LinkedListQueue<Data> buffer = new LinkedListQueue<Data>();
  LinkedListQueue<Data> eventQueue = new LinkedListQueue<Data>();
  private Event e;

  public double getRandTime(double arrivalRate){
    double num, time1, max=1, min=0, randNUM;
    randNUM= Math.random();
    time1= (-1/arrivalRate) * (Math.log(1-randNUM));
    //System.out.println(time1);
    return time1;
  }

  public QueueSimulator(double aR, double servT, double simT){
    arrivalRate = aR;
    serviceTime = servT;
    totalSimTime = simT;
    timeForNextDeparture = timeForNextArrival + serviceTime;
  }

  public double calcAverageWaitingTime(){
    double events = eventQueue.size();
    double time = 0;
    Data d;
    while(!eventQueue.isEmpty()){
      d = new Data();
      d = eventQueue.dequeue();
      time += d.getDepartureTime() - d.getArrivalTime();
    }
    return (time/events);
  }

  public double runSimulation(){
    currTime = 0;
    Data data;


    while(currTime < totalSimTime){
      if (buffer.isEmpty()) {
        e = Event.ARRIVAL;
        currTime = timeForNextArrival;
      }
      if (timeForNextArrival < timeForNextDeparture) {
        e = Event.ARRIVAL;
        currTime = timeForNextArrival;
      }
      else {
        e = Event.DEPARTURE;
        currTime = timeForNextDeparture;
      }

      data = new Data();

      switch (e){
        case DEPARTURE:
        data = buffer.dequeue();
        data.setDepartureTime(currTime);
        eventQueue.enqueue(data);
        if (!buffer.isEmpty())
          timeForNextDeparture = currTime + serviceTime;
        else
          timeForNextDeparture = timeForNextArrival + serviceTime;
        break;
        case ARRIVAL:
          data.setArrivalTime(currTime);
          buffer.enqueue(data);
          timeForNextArrival = currTime + getRandTime(arrivalRate);
          break;
      }
    }
    return calcAverageWaitingTime();
  }
}

