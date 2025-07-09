package algo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeightedRoundRobin {
    static class Server {
        String name;
        int weight;
        int currentWeight;

        Server(String name, int weight) {
            this.name = name;
            this.weight = weight;
            this.currentWeight = 0;
        }
    }

    private List<Server> servers;
    private int totalWeight;

    public WeightedRoundRobin(Map<String, Integer> weights) {
        servers = new ArrayList<>();
        totalWeight = 0;
        for (Map.Entry<String, Integer> entry : weights.entrySet()) {
            servers.add(new Server(entry.getKey(), entry.getValue()));
            totalWeight += entry.getValue();
        }
    }

    public String next() {
        Server best = null;
        for (Server server : servers) {
            server.currentWeight += server.weight;
            if (best == null || server.currentWeight > best.currentWeight) {
                best = server;
            }
        }
        best.currentWeight -= totalWeight;
        return best.name;
    }

    public static void main(String[] args) {
        Map<String, Integer> weights = new HashMap<>();
        weights.put("a", 1);
        weights.put("b", 2);
        weights.put("c", 5);

        WeightedRoundRobin wrr = new WeightedRoundRobin(weights);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            result.append(wrr.next());
        }
        System.out.println(result.toString()); // 结果可能是：ccbcacbc
    }
}
