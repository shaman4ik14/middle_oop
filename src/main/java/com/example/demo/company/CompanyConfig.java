package com.example.demo.company;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CompanyConfig {
    @Bean
    CommandLineRunner commandLineRunner(CompanyRepository repository) {
        return args -> {
            Company ucu = new Company("ucu.edu.ua","ukrainian catholic university",
                    "doesn`t exist",
                    "https://facebook.com/UkrainianCatholicUniversity",
                    "https://asset.brandfetch.io/idkq5Jhe1A/idHPW1qDOU.png",
                    "https://asset.brandfetch.io/idkq5Jhe1A/idY4QLg_4A.jpeg",
                    227, "Ilariona Svjentsits'koho St, 17, Lviv, Lviv Oblast, Ukraine, 79000");
            repository.save(ucu);
        };
    }

}
