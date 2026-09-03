import java.util.HashMap;
import java.util.Map;

class UndergroundSystem {

    // Class to store check-in information for a customer
    private static class CheckInInfo {
        String stationName;
        int checkInTime;

        CheckInInfo(String stationName, int checkInTime) {
            this.stationName = stationName;
            this.checkInTime = checkInTime;
        }
    }

    // Class to store total duration and trip count for a route
    private static class RouteData {
        double totalTime = 0;
        int tripCount = 0;

        void addTrip(int duration) {
            totalTime += duration;
            tripCount++;
        }

        double getAverage() {
            return totalTime / tripCount;
        }
    }

    // Maps customer ID -> check-in details
    private Map<Integer, CheckInInfo> checkInMap;
    // Maps route key ("startStation->endStation") -> route statistics
    private Map<String, RouteData> routeMap;

    public UndergroundSystem() {
        checkInMap = new HashMap<>();
        routeMap = new HashMap<>();
    }
    
    public void checkIn(int id, String stationName, int t) {
        checkInMap.put(id, new CheckInInfo(stationName, t));
    }
    
    public void checkOut(int id, String stationName, int t) {
        CheckInInfo info = checkInMap.remove(id);
        String routeKey = info.stationName + "->" + stationName;
        
        routeMap.putIfAbsent(routeKey, new RouteData());
        routeMap.get(routeKey).addTrip(t - info.checkInTime);
    }
    
    public double getAverageTime(String startStation, String endStation) {
        String routeKey = startStation + "->" + endStation;
        return routeMap.get(routeKey).getAverage();
    }
}