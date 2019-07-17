package com.kitcut.helloworld.springboot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SpringbootApplication implements CommandLineRunner {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(SpringbootApplication.class, args);

//        MessageSource messageSource = applicationContext.getBean(MessageSource.class);
//        String msg = messageSource.getMessage("welcome.text", null, Locale.FRENCH);

//        EmployeeService employeeService = applicationContext.getBean(EmployeeServiceImpl.class);
//        List<Employee> employeeList = employeeService.findAll();
//        int i = 0;
    }

    @Override
    public void run(String... args) throws Exception {
//        System.out.println("Hello CommandLineRunner");
    }
}
