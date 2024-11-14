package HackerRank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Itinerary {
        private static Map<String, List<String>> tripMap = new HashMap<>();
        private static List<String> result = new ArrayList<>();
        private static  int totalTrips;

        private static boolean dfs(String currentCity, int tripCount){
            if(tripCount == totalTrips+ 1){
                result.add(currentCity);
                return true;
            }

            if(!tripMap.containsKey(currentCity)){
                return false;
            }

            List<String> destinations = tripMap.get(currentCity);

            Collections.sort(destinations);

            for(int i =0; i< destinations.size(); i++){
                String nextCity = destinations.get(i);
                destinations.remove(i);
                result.add(currentCity);

                if(dfs(nextCity, tripCount+ 1)){
                    return true;
                }

                destinations.add(i, nextCity);
                result.remove(result.size()-1);
            }
            return false;
        }

        public static List<String> findItinerary (List<List<String>> trips, String start){

            for(List<String> trip : trips){
                String origin = trip.get(0);
                String destination = trip.get(1);

                tripMap.putIfAbsent(origin, new ArrayList<>());
                tripMap.get(origin).add(destination);
            }
            totalTrips = trips.size();
            result.clear();

            if(!dfs(start, 1)){
                return null;
            }
            return result;
        }


        public static void main(String args[] ) throws Exception {
            List<List<String>> trips1 = Arrays.asList(Arrays.asList("Amsterdam", "London"),
                    Arrays.asList("Berlin", "Amsterdam"), Arrays.asList("Barcelona", "Berlin"), Arrays.asList("London", "Milan"));
            System.out.println(findItinerary(trips1, "Barcelona"));
        }
}
