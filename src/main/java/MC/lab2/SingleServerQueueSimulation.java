package MC.lab2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SingleServerQueueSimulation {

  public static String solve(int X0, int minArrivalTime, int maxArrivalTime, int minServiceTime, int maxServiceTime,
      int numRequests) {

    Random random = new Random(X0);

    double totalArrivalTime = 0;
    double totalWaitingTime = 0;
    double totalServiceTime = 0;
    double busyTime = 0;
    List<Double> completionTimes = new ArrayList<>();

    for (int i = 0; i < numRequests; i++) {
      double interarrivalTime = random.nextDouble() * (maxArrivalTime - minArrivalTime) + minArrivalTime;
      totalArrivalTime += interarrivalTime;

      double serviceTime = random.nextDouble() * (maxServiceTime - minServiceTime) + minServiceTime;
      totalServiceTime += serviceTime;

      double waitingTime = Math.max(0, busyTime - totalArrivalTime);
      totalWaitingTime += waitingTime;

      double completionTime = totalArrivalTime + waitingTime + serviceTime;
      completionTimes.add(completionTime);
      busyTime = Math.max(busyTime, completionTime);
    }

    double averageArrivalRate = numRequests / totalArrivalTime;
    double averageWaitingTime = totalWaitingTime / numRequests;
    double averageTimeInSystem = (totalWaitingTime + totalServiceTime) / numRequests;
    double channelUtilization = totalServiceTime / busyTime;

    return "Інтенсивність вхідного потоку заявок: " +
        String.format("%.2f", averageArrivalRate) +
        " заявок/хв\nСередній час очікування заявки перед обслуговуванням: " +
        String.format("%.2f", averageWaitingTime) +
        " хв\nСередній час перебування заявки в системі: " +
        String.format("%.2f", averageTimeInSystem) +
        " хв\nКоефіцієнт завантаження каналу системи: " +
        String.format("%.2f", channelUtilization);
  }
}
