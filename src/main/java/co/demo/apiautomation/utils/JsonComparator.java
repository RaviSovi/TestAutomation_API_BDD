package co.demo.apiautomation.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class JsonComparator {

    public static List<String> getJsonDifferences(String json1, String json2) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        // Parse the JSON strings into JsonNode objects
        JsonNode tree1 = objectMapper.readTree(json1);
        JsonNode tree2 = objectMapper.readTree(json2);

        List<String> differences = new ArrayList<>();
        compareJsonNodes(tree1, tree2, differences, "");

        return differences;
    }

    private static void compareJsonNodes(JsonNode node1, JsonNode node2, List<String> differences, String path) {
        // Check if both nodes are objects
        if (node1.isObject() && node2.isObject()) {
            Iterator<Map.Entry<String, JsonNode>> fields1 = node1.fields();
            while (fields1.hasNext()) {
                Map.Entry<String, JsonNode> entry = fields1.next();
                String key = entry.getKey();
                JsonNode value1 = entry.getValue();
                JsonNode value2 = node2.get(key);

                if (value2 == null) {
                    differences.add("Missing key in second JSON: " + (path.isEmpty() ? key : path + "." + key));
                } else {
                    compareJsonNodes(value1, value2, differences, path.isEmpty() ? key : path + "." + key);
                }
            }

            // Check if node2 has any extra fields not present in node1
            Iterator<Map.Entry<String, JsonNode>> fields2 = node2.fields();
            while (fields2.hasNext()) {
                Map.Entry<String, JsonNode> entry = fields2.next();
                String key = entry.getKey();
                if (!node1.has(key)) {
                    differences.add("Extra key in second JSON: " + (path.isEmpty() ? key : path + "." + key));
                }
            }

        // Check if both nodes are arrays
        } else if (node1.isArray() && node2.isArray()) {
            if (node1.size() != node2.size()) {
                differences.add("Array size difference at " + path + ": " + node1.size() + " vs " + node2.size());
            } else {
                for (int i = 0; i < node1.size(); i++) {
                    compareJsonNodes(node1.get(i), node2.get(i), differences, path + "[" + i + "]");
                }
            }

        // Check if both nodes are values and equal
        } else if (!node1.equals(node2)) {
            differences.add("Value difference at " + path + ": " + node1 + " vs " + node2);
        }
    }

//    public static void main(String[] args) {
//        String json1 = "{ \"name\": \"John\", \"age\": 30, \"address\": { \"city\": \"New York\", \"postalCode\": \"10001\" }, \"phoneNumbers\": [\"123-456-7890\", \"987-654-3210\"] }";
//        String json2 = "{ \"name\": \"John\", \"age\": 30, \"address\": { \"city\": \"Los Angeles\", \"postalCode\": \"90001\" }, \"phoneNumbers\": [\"123-456-7890\", \"987-654-3211\"] }";
//
//        try {
//            List<String> differences = getJsonDifferences(json1, json2);
//            if (differences.isEmpty()) {
//                System.out.println("The JSON objects are equal.");
//            } else {
//                System.out.println("Differences found:");
//                for (String difference : differences) {
//                    System.out.println(difference);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
