package com.dekra.Inicio;

import com.dekra.Inicio.logs.application.LogService;
import com.dekra.Inicio.team.application.TeamService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InicioApplication {

	public static void main(String[] args) {
		SpringApplication.run(InicioApplication.class, args);
	}

	@Bean
	public CommandLineRunner setup(TeamService teamService, LogService logService) {
		return args -> {
			teamService.addObserver(logService); // Registro inicial
		};

	}
}
