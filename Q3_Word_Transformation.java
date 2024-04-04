package April_03_Assignment;

import java.util.*;

public class Q3_Word_Transformation {
    // Method to find all transformation sequences from start to end
    public static List<List<String>> findLadders(String start, String end, List<String> dict) {
        List<List<String>> result = new ArrayList<>(); // List to store all transformation sequences
        Set<String> dictionary = new HashSet<>(dict); // Set to store words from the dictionary for faster lookup
        Map<String, List<String>> neighborsMap = new HashMap<>(); // Map to store neighbors of each word
        Map<String, Integer> distanceMap = new HashMap<>(); // Map to store distances from start to each word

        // Perform BFS to build neighborsMap and distanceMap
        bfs(start, end, dictionary, neighborsMap, distanceMap);

        // Perform DFS to find all transformation sequences
        List<String> path = new ArrayList<>();
        path.add(start);
        dfs(start, end, neighborsMap, distanceMap, path, result);

        return result;
    }

    // Breadth-First Search to build neighborsMap and distanceMap
    private static void bfs(String start, String end, Set<String> dictionary,
                            Map<String, List<String>> neighborsMap, Map<String, Integer> distanceMap) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        distanceMap.put(start, 0);

        while (!queue.isEmpty()) {
            String word = queue.poll();
            int distance = distanceMap.get(word);
            List<String> neighbors = getNeighbors(word, dictionary);

            for (String neighbor : neighbors) {
                // Add neighbor to the neighborsMap
                neighborsMap.computeIfAbsent(word, k -> new ArrayList<>()).add(neighbor);

                if (!distanceMap.containsKey(neighbor)) {
                    // Update distanceMap and add neighbor to the queue for further exploration
                    distanceMap.put(neighbor, distance + 1);
                    queue.offer(neighbor);
                }
            }
        }
    }

    // Helper method to get neighbors of a word
    private static List<String> getNeighbors(String word, Set<String> dictionary) {
        List<String> neighbors = new ArrayList<>();
        char[] chars = word.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            char originalChar = chars[i];
            for (char c = 'a'; c <= 'z'; c++) {
                if (c != originalChar) {
                    chars[i] = c;
                    String newWord = String.valueOf(chars);
                    // Add neighbor to the list if it exists in the dictionary
                    if (dictionary.contains(newWord)) {
                        neighbors.add(newWord);
                    }
                }
            }
            chars[i] = originalChar;
        }

        return neighbors;
    }

    // Depth-First Search to find all transformation sequences
    private static void dfs(String current, String end, Map<String, List<String>> neighborsMap,
                            Map<String, Integer> distanceMap, List<String> path, List<List<String>> result) {
        // If we reach the end word, add the current path to result
        if (current.equals(end)) {
            result.add(new ArrayList<>(path));
            return;
        }

        // Explore neighbors recursively
        for (String neighbor : neighborsMap.getOrDefault(current, new ArrayList<>())) {
            if (distanceMap.getOrDefault(neighbor, Integer.MAX_VALUE) == distanceMap.get(current) + 1) {
                path.add(neighbor);
                dfs(neighbor, end, neighborsMap, distanceMap, path, result);
                path.remove(path.size() - 1); // Backtrack
            }
        }
    }

    // Main method to take user input and demonstrate the functionality
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Take input for start word
        System.out.println("Enter the start word:");
        String start = scanner.next();

        // Take input for end word
        System.out.println("Enter the end word:");
        String end = scanner.next();

        // Take input for number of words in the dictionary
        System.out.println("Enter the number of words in the dictionary:");
        int n = scanner.nextInt();

        // Consume newline character
        scanner.nextLine();

        List<String> dict = new ArrayList<>();
        // Take input for words in the dictionary
        System.out.println("Enter the words in the dictionary:");
        for (int i = 0; i < n; i++) {
            dict.add(scanner.next());
        }

        // Find all transformation sequences
        List<List<String>> sequences = findLadders(start, end, dict);

        // Display the transformation sequences
        System.out.println("Transformation sequences:");
        for (List<String> sequence : sequences) {
            System.out.println(sequence);
        }
    }
}
