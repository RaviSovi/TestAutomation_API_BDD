package co.demo.apiautomation.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.json.JsonArray;
import javax.json.JsonException;
import javax.json.JsonObject;

public class TestUtils {
	public static JsonPath jspath;

	/************
	 * Method to get a json data into Map and iterate it to find key values pair -
	 * Please use 'getJsonDataFromBodyAsMap method and pass jsonString as
	 * argument'.*******
	 *****/
//	private static void populateMapFromJsonNode(JsonNode jsonNode, Map<String, Object> map, String parentKey) {
//		if (jsonNode.isObject()) {
//			Iterator<Entry<String, JsonNode>> fields = jsonNode.fields();
//			while (fields.hasNext()) {
//				Entry<String, JsonNode> field = fields.next();
//				String fieldName = field.getKey();
//				JsonNode fieldValue = field.getValue();
//				String fullKey = parentKey.isEmpty() ? fieldName : parentKey + "." + fieldName;
//				populateMapFromJsonNode(fieldValue, map, fullKey);
//			}
//		} else if (jsonNode.isArray()) {
//			for (int i = 0; i < jsonNode.size(); i++) {
//				String arrayKey = parentKey + "[" + i + "]";
//				populateMapFromJsonNode(jsonNode.get(i), map, arrayKey);
//			}
//		} else {
//			map.put(parentKey, jsonNode.asText());
//		}
//	}
//
//	/*** Method to get a json data into Map and iterate it to find key values pair.**/
//	public static Map<String, Object> getJsonDataFromBodyAsMap(String jsonBodyAsString) {
//		// Create ObjectMapper instance
//		ObjectMapper objectMapper = new ObjectMapper();
//		// Convert JSON string to JsonNode
//		JsonNode jsonNode;
//		Map<String, Object> resultMap = new HashMap<>();
//		try {
//			jsonNode = objectMapper.readTree(jsonBodyAsString);
//			// Recursively populate the map with key-value pairs
//			populateMapFromJsonNode(jsonNode, resultMap, "");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return resultMap;
//	}
//
//	/**************************************************************************************************************************/
//
//	/** Method need to use when user want to get value of specific key from jSon **/
//	public static String getJsonPropertyValueFromBodyUsingJSONPath(String jsonBody, String jsonPropertyKey) {
//		jspath = new JsonPath(jsonBody);
//		String jsonProp = jspath.get(jsonPropertyKey).toString();
//		return jsonProp;
//	}
//
//	/**************************************************************************************************************************/
//
//	/**
//	 * Method need to use when user want to compare value of specific key between
//	 * actual jSon response and expected jSon response
//	 **/
//	@SuppressWarnings("unchecked")
//	public static void validateJsonResponseFromExcel(String apiName_ResponseFromExcel, String jsonBodyFromResponse,
//			String jsonPropertyKey) throws Exception {
//		Map<String, String> excelDataMap = ExcelUtils.getData(apiName_ResponseFromExcel);
//		String jsonPropertyValueFromAPIReponse = getJsonPropertyValueFromBodyUsingJSONPath(jsonBodyFromResponse,
//				jsonPropertyKey);
//
//		if (jspath.get(jsonPropertyKey).toString().contains(jsonPropertyValueFromAPIReponse)) {
//			ER.Info("Json Reponse property : " + jsonPropertyKey + " is matching with expected value from excel : "
//					+ jsonPropertyValueFromAPIReponse);
//			System.out.println("Json Reponse property : " + jsonPropertyKey
//					+ " is matching with expected value from excel : " + jsonPropertyValueFromAPIReponse);
//		}
//	}
//
//	/**************************************************************************************************************************/
//
//	/****************This method is to verify if expected Json and actual Json are same or not**********************************/
//	public static boolean areJsonObjectsEqual(String json1, String json2) throws Exception {
//		ObjectMapper objectMapper = new ObjectMapper();
//
//		// Parse the JSON strings into JsonNode objects
//		JsonNode tree1 = objectMapper.readTree(json1);
//		JsonNode tree2 = objectMapper.readTree(json2);
//
//		// Compare the JsonNode objects
//		return compareJsonNodes(tree1, tree2);
//	}
//
//	private static boolean compareJsonNodes(JsonNode node1, JsonNode node2) {
//		// Check if both nodes are objects
//		if (node1.isObject() && node2.isObject()) {
//			Iterator<Map.Entry<String, JsonNode>> fields1 = node1.fields();
//			while (fields1.hasNext()) {
//				Map.Entry<String, JsonNode> entry = fields1.next();
//				String key = entry.getKey();
//				JsonNode value1 = entry.getValue();
//				JsonNode value2 = node2.get(key);
//
//				if (value2 == null || !compareJsonNodes(value1, value2)) {
//					System.out.println("Difference found at key: " + key);
//					System.out.println("Expected :" + value1 + " Actual : " + value2);
//					return false;
//				}
//			}
//
//			// Check if node2 has any extra fields not present in node1
//			Iterator<Map.Entry<String, JsonNode>> fields2 = node2.fields();
//			while (fields2.hasNext()) {
//				Map.Entry<String, JsonNode> entry = fields2.next();
//				String key = entry.getKey();
//				if (!node1.has(key)) {
//					System.out.println("Difference found at key: " + key);
//					return false;
//				}
//			}
//
//			return true;
//		}
//
//		// Check if both nodes are arrays
//		if (node1.isArray() && node2.isArray()) {
//			if (node1.size() != node2.size()) {
//				System.out.println("Array size difference");
//				return false;
//			}
//			for (int i = 0; i < node1.size(); i++) {
//				if (!compareJsonNodes(node1.get(i), node2.get(i))) {
//					System.out.println("Difference found in array at index: " + i);
//					return false;
//				}
//			}
//			return true;
//		}
//
//		// Check if both nodes are values and equal
//		if (node1.isValueNode() && node2.isValueNode()) {
//			return node1.equals(node2);
//		}
//
//		// If none of the above cases match, the nodes are not equal
//		return false;
//	}
//
////	    public static void main(String[] args) {
////	        String json1 = "{ \"name\": \"John\", \"age\": 31, \"address\": { \"city\": \"New York\", \"postalCode\": \"10001\" }, \"phoneNumbers\": [\"123-456-7890\", \"987-654-3210\"] }";
////	        String json2 = "{ \"name\": \"John\", \"age\": 30, \"address\": { \"city\": \"New York\", \"postalCode\": \"10001\" }, \"phoneNumbers\": [\"123-456-7890\", \"987-654-3210\"] }";
//	//
////	        try {
////	            boolean result = areJsonObjectsEqual(json1, json2);
////	            System.out.println("Are JSON objects equal? " + result);
////	        } catch (Exception e) {
////	            e.printStackTrace();
////	        }
////	    }  
//	/**************************************************************************************************************************/
//	/*********************Method to convert Map or List to JSON string based on JSON schema************************************/
//	private static final ObjectMapper mapper = new ObjectMapper();
//	public static String mapToJson(Object data, String jsonSchemaString) {
//		JsonNode schemaNode;
//		try {
//			schemaNode = mapper.readTree(jsonSchemaString);
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//			return null;
//		}
//
//		// Determine schema type
//		String schemaType = schemaNode.get("type").asText();
//		JsonNode jsonObject;
//
//		try {
//			if ("array".equals(schemaType)) {
//				if (!(data instanceof List)) {
//					throw new IllegalArgumentException("Data should be a list for schema type 'array'");
//				}
//				jsonObject = createJsonArrayFromMap((List<?>) data, (ObjectNode) schemaNode);
//			} else if ("object".equals(schemaType)) {
//				if (!(data instanceof Map)) {
//					throw new IllegalArgumentException("Data should be a map for schema type 'object'");
//				}
//				jsonObject = createJsonObjectFromMap((Map<String, Object>) data, (ObjectNode) schemaNode);
//			} else {
//				throw new IllegalArgumentException("Unsupported schema type: " + schemaType);
//			}
//		} catch (ClassCastException | IllegalArgumentException e) {
//			e.printStackTrace();
//			return null;
//		}
//
//		try {
//			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
//
//	private static ArrayNode createJsonArrayFromMap(List<?> list, ObjectNode schemaNode) {
//		ArrayNode arrayNode = mapper.createArrayNode();
//		JsonNode itemSchema = schemaNode.get("items");
//
//		for (Object item : list) {
//			if (item instanceof Map) {
//				arrayNode.add(createJsonObjectFromMap((Map<String, Object>) item, (ObjectNode) itemSchema));
//			} else if (item instanceof List) {
//				arrayNode.add(createJsonArrayFromMap((List<?>) item, (ObjectNode) itemSchema));
//			} else {
//				arrayNode.addPOJO(item);
//			}
//		}
//		return arrayNode;
//	}
//
//	private static ObjectNode createJsonObjectFromMap(Map<String, Object> map, ObjectNode schemaNode) {
//		ObjectNode jsonObject = mapper.createObjectNode();
//		List<String> propertyNames = getPropertyOrder(schemaNode);
//
//		for (String propertyName : propertyNames) {
//			Object value = map.get(propertyName);
//			JsonNode propertySchema = schemaNode.get("properties").get(propertyName);
//
//			if (propertySchema == null) {
//				// Skip if schema does not define this property
//				continue;
//			}
//
//			if (value instanceof Map) {
//				jsonObject.set(propertyName,
//						createJsonObjectFromMap((Map<String, Object>) value, (ObjectNode) propertySchema));
//			} else if (value instanceof List) {
//				jsonObject.set(propertyName,
//						createJsonArrayFromMap((List<?>) value, (ObjectNode) propertySchema.get("items")));
//			} else {
//				jsonObject.putPOJO(propertyName, value);
//			}
//		}
//		return jsonObject;
//	}
//
//	private static List<String> getPropertyOrder(ObjectNode schemaNode) {
//		JsonNode propertiesNode = schemaNode.get("properties");
//		if (propertiesNode == null) {
//			return List.of(); // Empty list if no properties defined
//		}
//
//		List<String> propertyNames = new ArrayList<>();
//		propertiesNode.fieldNames().forEachRemaining(propertyNames::add);
//		return propertyNames;
//	}
//
//	// Method to read JSON schema from file and convert data to JSON
//	public static String readSchemaAndConvertData(Object data, String schemaFilePath) {
//		try {
//			String jsonSchemaString = new String(Files.readAllBytes(Paths.get(schemaFilePath)));
//			return mapToJson(data, jsonSchemaString);
//		} catch (IOException e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
//
////    public static void main(String[] args) {
////        // Example usage for object schema
////        Map<String, Object> dataMapObject = new LinkedHashMap<>();
////        dataMapObject.put("book_name", "John Doe");
////        dataMapObject.put("isbn", "HARMA");
////        dataMapObject.put("aisle", "1012900");
////        dataMapObject.put("author", "PB KI");
////        
////
////        // Example schema file path
////        String schemaFilePath = "src//test//resources//schemas//userSchema.json";
////
////        // Convert data to JSON based on schema
////        String jsonStringObject = readSchemaAndConvertData(dataMapObject, schemaFilePath);
////        System.out.println("Output for object schema:\n" + jsonStringObject);
////    }
//	/**************************************************************************************************************************/
//
//	/********************Convert Json to Java Map Or LIst please use convertJsonToMapOrList method for conversion**************/
//	public static Object convertJson(String jsonString) {
//		Object json = null;
//		if (jsonString.trim().startsWith("{")) {
//			json = new JSONObject(jsonString);
//		} else if (jsonString.trim().startsWith("[")) {
//			json = new JSONArray(jsonString);
//		}
//		return convertJson(json);
//	}
//
//	public static Object convertJson(Object json) {
//		if (json instanceof JSONObject) {
//			return convertJsonObjectToMap((JSONObject) json);
//		} else if (json instanceof JSONArray) {
//			return convertJsonArrayToList((JSONArray) json);
//		} else {
//			return json;
//		}
//	}
//
//	public static Map<String, Object> convertJsonObjectToMap(JSONObject jsonObject) {
//		Map<String, Object> map = new LinkedHashMap<>();
//		Iterator<String> keys = jsonObject.keys();
//
//		while (keys.hasNext()) {
//			String key = keys.next();
//			Object value = jsonObject.get(key);
//
//			if (value instanceof JSONObject) {
//				value = convertJsonObjectToMap((JSONObject) value);
//			} else if (value instanceof JSONArray) {
//				value = convertJsonArrayToList((JSONArray) value);
//			}
//
//			map.put(key, value);
//		}
//
//		return map;
//	}
//	
//	public static List<Object> convertJsonArrayToList(JSONArray jsonArray) {
//		List<Object> list = new ArrayList<>();
//		for (int i = 0; i < jsonArray.length(); i++) {
//			Object value = jsonArray.get(i);
//			if (value instanceof JSONObject) {
//				value = convertJsonObjectToMap((JSONObject) value);
//			} else if (value instanceof JSONArray) {
//				value = convertJsonArrayToList((JSONArray) value);
//			}
//			list.add(value);
//		}
//		return list;
//	}
//
//	public static void printMap(Map<String, Object> map) {
//		for (Map.Entry<String, Object> entry : map.entrySet()) {
//			System.out.println(entry.getKey() + ": " + entry.getValue());
//		}
//	}
//
//	public static void printList(List<Object> list) {
//		for (Object item : list) {
//			System.out.println(item);
//		}
//	}
//
//	@SuppressWarnings("unchecked")
//	public static void convertJsonToMapOrList(String jsonObjectString,String jsonArrayString ) {
////		// Example JSON Object
////		String jsonObjectString = "{\n" + "            \"name\": \"John Doe\",\n" + "            \"age\": 30,\n"
////				+ "            \"address\": {\n" + "                \"street\": \"123 Main St\",\n"
////				+ "                \"city\": \"Anytown\"\n" + "            },\n"
////				+ "            \"phones\": [\"123-456-7890\", \"987-654-3210\"],\n" + "            \"children\": [\n"
////				+ "                {\"name\": \"Jane\", \"age\": 10},\n"
////				+ "                {\"name\": \"Jake\", \"age\": 8}\n" + "            ]\n" + "        }";
////
////		// Example JSON Array
////		String jsonArrayString = " [\n" + "            {\n" + "                \"name\": \"John Doe\",\n"
////				+ "                \"age\": 30\n" + "            },\n" + "            {\n"
////				+ "                \"name\": \"Jane Doe\",\n" + "                \"age\": 25\n" + "            }\n"
////				+ "        ]";
//
//		// Convert JSON Object to Map and print
//		Object resultObject = convertJson(jsonObjectString);
//		if (resultObject instanceof Map) {
//			printMap((Map<String, Object>) resultObject);
//		}
//
//		// Convert JSON Array to List and print
//		Object resultArray = convertJson(jsonArrayString);
//		if (resultArray instanceof List) {
//			printList((List<Object>) resultArray);
//		}
//	}
//	/**************************************************************************************************************************/
//	
//	/*********************************Compare two jason string stored in Map - Please use areMapsEqualMethod************************************/
//	public static Map<String, Object> findDifferences(Map<String, Object> map1, Map<String, Object> map2) {
//		Map<String, Object> differences = new HashMap<>();
//
//		// Check for differences in map1
//		for (Map.Entry<String, Object> entry : map1.entrySet()) {
//			String key = entry.getKey();
//			Object value = entry.getValue();
//			if (!map2.containsKey(key)) {
//				differences.put(key, "Present in map1, missing in map2");
//			} else if (!value.equals(map2.get(key))) {
//				differences.put(key, "Different values: map1=" + value + ", map2=" + map2.get(key));
//			}
//		}
//
//		// Check for keys in map2 that are not in map1
//		for (String key : map2.keySet()) {
//			if (!map1.containsKey(key)) {
//				differences.put(key, "Present in map2, missing in map1");
//			}
//		}
//
//		return differences;
//	}
//
//	public static boolean areMapsEqual(Map<String, Object> map1, Map<String, Object> map2) {
//		Map<String, Object> differences = findDifferences(map1, map2);
//		return differences.isEmpty();
//	}
//	/**************************************************************************************************************************/
//
//	/*********Compare two array jsons are equals or not which stored in a list - Please use compareLists method****************/
//	public static class ComparisonResult {
//		private final boolean areEqual;
//		private final List<Object> differences;
//
//		public ComparisonResult(boolean areEqual, List<Object> differences) {
//			this.areEqual = areEqual;
//			this.differences = differences;
//		}
//
//		public boolean areEqual() {
//			return areEqual;
//		}
//
//		public List<Object> getDifferences() {
//			return differences;
//		}
//
//		@Override
//		public String toString() {
//			return "ComparisonResult{" + "areEqual=" + areEqual + ", differences=" + differences + '}';
//		}
//	}
//
//	public static ComparisonResult compareLists(List<Object> list1, List<Object> list2) {
//		// Create copies of the lists to avoid modifying the original lists
//		List<Object> copyOfList1 = new ArrayList<>(list1);
//		List<Object> copyOfList2 = new ArrayList<>(list2);
//
//		// Sort the copies to ensure sequence doesn't matter
//		Collections.sort(copyOfList1, null);
//		Collections.sort(copyOfList2, null);
//
//		// Compare the sorted lists
//		boolean areEqual = copyOfList1.equals(copyOfList2);
//
//		// Find the differences
//		List<Object> differences = new ArrayList<>();
//		if (!areEqual) {
//			differences.addAll(copyOfList1);
//			differences.removeAll(copyOfList2);
//			differences.addAll(copyOfList2);
//			differences.removeAll(copyOfList1);
//		}
//
//		return new ComparisonResult(areEqual, differences);
//	}
//	
//	public static void compareTwoJsonTypeMap(String expectedJson, String ActualJson)
//	{
//		JSONObject expectedRes = new JSONObject(expectedJson);
//		JSONObject actualRes = new JSONObject(ActualJson); // Please use .asString method to convert json response to string
//
//		Map<String, Object> mapRequest = convertJsonObjectToMap(expectedRes);
//		//Map<String, Object> mapResponse = convertJsonToMap(actualRes);
//	}
	/**************************************************************************************************************************/
	
	/**************************************************************************************************************************/
	
//	public static Map<String, Object> jsonToMap(JSONObject json) {
//        Map<String, Object> retMap = new HashMap<String, Object>();
//
//        if(json != JsonObject.NULL) {
//            retMap = toMap(json);
//        }
//        return retMap;
//    }
//
//    public static Map<String, Object> toMap(JSONObject object) throws JsonException {
//        Map<String, Object> map = new HashMap<String, Object>();
//
//        Iterator<String> keysItr = object.keySet().iterator();
//        while(keysItr.hasNext()) {
//            String key = keysItr.next();
//            Object value = object.get(key);
//
//            if(value instanceof JsonArray) {
//                value = toList((JSONArray) value);
//            }
//
//            else if(value instanceof JSONObject) {
//                value = toMap((JSONObject) value);
//            }
//            map.put(key, value);
//        }
//        return map;
//    }
//
//    public static List<Object> toList(JSONArray array) {
//        List<Object> list = new ArrayList<Object>();
//        for(int i = 0; i < array.length(); i++) {
//            Object value = array.get(i);
//            if(value instanceof JSONArray) {
//                value = toList((JSONArray) value);
//            }
//
//            else if(value instanceof JsonObject) {
//                value = toMap((JSONObject) value);
//            }
//            list.add(value);
//        }
//        return list;
//    }
	
	
	
//	public static Map<String, Object> jsonObjectToMap(JSONObject jsonObject) {
//        Map<String, Object> map = new HashMap<>();
//
//        for (String key : jsonObject.keySet()) {
//            Object value = jsonObject.get(key);
//
//            if (value instanceof JSONObject) {
//                // Recursively convert JSONObject to Map
//                map.put(key, jsonObjectToMap((JSONObject) value));
//            } else if (value instanceof JSONArray) {
//                // Convert JSONArray to an array of Maps/Objects
//                map.put(key, jsonArrayToList((JSONArray) value));
//            } else {
//                // For other types, just put them directly
//                map.put(key, value);
//            }
//        }
//
//        return map;
//    }
//
//    private static Object jsonArrayToList(JSONArray jsonArray) {
//        // Check if the array contains JSONObjects or primitive types
//        boolean isJsonObjectArray = true;
//        for (int i = 0; i < jsonArray.length(); i++) {
//            if (!(jsonArray.get(i) instanceof JSONObject)) {
//                isJsonObjectArray = false;
//                break;
//            }
//        }
//
//        if (isJsonObjectArray) {
//            // Convert JSONArray of JSONObjects to an array of Maps
//            Map<String, Object>[] mapArray = new HashMap[jsonArray.length()];
//            for (int i = 0; i < jsonArray.length(); i++) {
//                mapArray[i] = jsonObjectToMap(jsonArray.getJSONObject(i));
//            }
//            return mapArray;
//        } else {
//            // Convert JSONArray to an array of Objects
//            Object[] objectArray = new Object[jsonArray.length()];
//            for (int i = 0; i < jsonArray.length(); i++) {
//                objectArray[i] = jsonArray.get(i);
//            }
//            return objectArray;
//        }
//    }
//	
//	
//	
//	
//	
//    
//    public static void main(String arg[]) throws ParseException
//    {
//    	// Example JSON Object
//    			String jsonObjectString = "{\n" + "            \"name\": \"John Doe\",\n" + "            \"age\": 30,\n"
//    					+ "            \"address\": {\n" + "                \"street\": \"123 Main St\",\n"
//    					+ "                \"city\": \"Anytown\"\n" + "            },\n"
//    					+ "            \"phones\": [\"123-456-7890\", \"987-654-3210\"],\n" + "            \"children\": [\n"
//    					+ "                {\"name\": \"Jane\", \"age\": 10},\n"
//    					+ "                {\"name\": \"Jake\", \"age\": 8}\n" + "            ]\n" + "        }";
//
//    			// Example JSON Array
//    			String jsonArrayString = " [\n" + "            {\n" + "                \"name\": \"John Doe\",\n"
//    					+ "                \"age\": 30\n" + "            },\n" + "            {\n"
//    					+ "                \"name\": \"Jane Doe\",\n" + "                \"age\": 25\n" + "            }\n"
//    					+ "        ]";
//    			
//    			JSONObject response = new JSONObject(jsonObjectString);
//    			
//    			//jsonToMap((JsonObject) response);
//    			
//    			for (Map.Entry<String, Object> entry : jsonObjectToMap(response).entrySet()) {
//    				System.out.println(entry.getKey() + ": " + entry.getValue());
//    			}
//    }
	
	
	public static byte[] convertJsonToByteArray(Object json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsBytes(json);
    }

    public static void main(String[] args) {
        try {
            // Example JSON object
            String jsonString = "{\n" + "            \"name\": \"John Doe\",\n" + "            \"age\": 30,\n"
					+ "            \"address\": {\n" + "                \"street\": \"123 Main St\",\n"
					+ "                \"city\": \"Anytown\"\n" + "            },\n"
					+ "            \"phones\": [\"123-456-7890\", \"987-654-3210\"],\n" + "            \"children\": [\n"
					+ "                {\"name\": \"Jane\", \"age\": 10},\n"
					+ "                {\"name\": \"Jake\", \"age\": 8}\n" + "            ]\n" + "        }";
            
            String jsonArrayString = " [\n" + "            {\n" + "                \"name\": \"John Doe\",\n"
					+ "                \"age\": 30\n" + "            },\n" + "            {\n"
					+ "                \"name\": \"Jane Doe\",\n" + "                \"age\": 25\n" + "            }\n"
					+ "        ]";


            ObjectMapper objectMapper = new ObjectMapper();
            Object jsonObject = objectMapper.readValue(jsonArrayString, Object.class);

            // Convert to byte array
            byte[] byteArray = convertJsonToByteArray(jsonObject);
            String s = new String(byteArray);
            // Print the byte array
            System.out.println(s);
            
            
           List<String> value = getValuesForKey(s, "age");
           System.out.println(value);
            
            //comapareExpectedJsonResponse(s, "street");
            

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void comapareExpectedJsonResponse(String jsonResponse, String expectedValue)
    {
    	Object obj = JsonPath.read(jsonResponse, "$.*.['"+expectedValue+"']");
    	System.out.println(obj.toString());
    }
    
    
    public static List<String> getValuesForKey(String jsonString, String expectedKey) {
        try {
            // Create a generic JsonPath expression to match any key with the expectedKey name
            String jsonPathExpression = "$..['" + expectedKey + "']";
            
            // Use JsonPath to extract the values associated with the expected key
            List<Object> values = JsonPath.read(jsonString, jsonPathExpression);
            
            // Convert the values to a list of strings
            List<String> stringValues = new ArrayList<>();
            for (Object value : values) {
                stringValues.add(value.toString());
            }
            
            // Return the list of values
            return stringValues;
        } catch (PathNotFoundException e) {
            // Handle the case where the key does not exist in the JSON
            return new ArrayList<>();
        }
    }
    
    ////////////////////////
    
    public static List<String> getValuesInObject(JSONObject jsonObject, String key) {
        List<String> accumulatedValues = new ArrayList<>();
        for (String currentKey : jsonObject.keySet()) {
            Object value = jsonObject.get(currentKey);
            if (currentKey.equals(key)) {
                accumulatedValues.add(value.toString());
            }

            if (value instanceof JSONObject) {
                accumulatedValues.addAll(getValuesInObject((JSONObject) value, key));
            } else if (value instanceof JSONArray) {
                accumulatedValues.addAll(getValuesInArray((JSONArray) value, key));
            }
        }

        return accumulatedValues;
    }

    public static List<String> getValuesInArray(JSONArray jsonArray, String key) {
        List<String> accumulatedValues = new ArrayList<>();
        for (Object obj : jsonArray) {
            if (obj instanceof JSONArray) {
                accumulatedValues.addAll(getValuesInArray((JSONArray) obj, key));
            } else if (obj instanceof JSONObject) {
                accumulatedValues.addAll(getValuesInObject((JSONObject) obj, key));
            }
        }

        return accumulatedValues;
    }
    
}
