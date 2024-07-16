package com.octl3.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
//public static void main(String[] args) {
//        ApplicationContext context = SpringApplication.run(DemoApplication.class, args);
//
//        // Lấy PasswordEncoder bean từ ApplicationContext
//        PasswordEncoder passwordEncoder = context.getBean(PasswordEncoder.class);
//
//        // Mã hóa mật khẩu
//        String rawPassword = "admin";
//        String encodedPassword = passwordEncoder.encode(rawPassword);
//
//        // In ra mật khẩu đã mã hóa
//        System.out.println("Mật khẩu đã mã hóa: " + encodedPassword);
//
//
//    }

}
