package com.hashmap.RestApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class RestApiServerApplication {
	private final Map<String, String> hashMap = new HashMap<>();

	public static void main(String[] args) {
		SpringApplication.run(RestApiServerApplication.class, args);
	}
	public RestApiServerApplication() {
		hashMap.put("1", "Jagadeesh");
		hashMap.put("2", "kakunuri");
		hashMap.put("3", "joel");
	}

	@GetMapping("/hello")
	public String getHello(){
		return "Hello Jagadesh !";
	}
	@GetMapping("/get")
	public String getValue(@RequestParam String key) {
		String name = hashMap.get(key);
		if (name != null) {
			return "Value for key " + key + " is: " + name;
		} else {
			return "Key not found: " + key;
		}
	}

	@GetMapping("/add")
	public String addValue(@RequestParam String key, @RequestParam String value) {
		hashMap.put(key, value);
		return "Added key-value pair: " + key + " -> " + value;
	}

	@GetMapping("/remove")
	public String removeValue(@RequestParam String key) {
		String removedValue = hashMap.remove(key);
		if (removedValue != null) {
			return "Removed key-value pair: " + key + " -> " + removedValue;
		} else {
			return "Key not found: " + key;
		}
	}
}