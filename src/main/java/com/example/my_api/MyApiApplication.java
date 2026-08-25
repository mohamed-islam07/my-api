// package com.example.my_api;

// import org.springframework.boot.SpringApplication;
// import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication
// public class MyApiApplication {

// 	public static void main(String[] args) {
// 		SpringApplication.run(MyApiApplication.class, args);
// 	}

// }
package com.example.my_api;
 
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
 
@SpringBootApplication
public class MyApiApplication {
 
    public static void main(String[] args) {
        SpringApplication.run(MyApiApplication.class, args);
 
        // --- TEMPORARY DEBUG: remove after checking ---
        String uri = System.getenv("MONGODB_URI");
        System.out.println("MONGODB_URI present: " + (uri != null));
        if (uri != null) {
            System.out.println("URI starts with: " + uri.substring(0, Math.min(20, uri.length())));
        }
        // --- END DEBUG ---
    }
}
 
