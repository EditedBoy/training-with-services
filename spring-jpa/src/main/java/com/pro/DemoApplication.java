package com.pro;

import com.pro.model.Human;
import com.pro.persistence.HumanRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Random;

@SpringBootApplication(scanBasePackages = "com.pro.web")
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(HumanRepository humanRepository) {
        return args -> {
            System.out.println("HumanRepository.save()");
            Arrays.asList("Petro", "Mykola", "Ania")
                    .forEach(name -> humanRepository.save(Human.builder().name(name).age(new Random().nextInt(100)).build()));

            System.out.println("HumanRepository.findAll()");
            humanRepository.findAll().forEach(System.out::println);

            System.out.println("HumanRepository.findAllByName()");
            humanRepository.getAllByName("Taras").forEach(System.out::println);
        };
    }
}
