package com.players.app;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.players.app.models.service.IUploadFileService;

@SpringBootApplication

@EntityScan(basePackages = "com.players.app.models.entity")
@ComponentScan(basePackages = {
	    "com.players.app.controllers",
	    "com.players.app.models.dao",
	    "com.players.app.models.service",
	    "com.players.app.models.entity"
	})
@EnableJpaRepositories("com.players.app.models.dao")
public class PlayersApplication implements CommandLineRunner {
	@Autowired(required = true)
	IUploadFileService uploadFileService;

	public static void main(String[] args) {
		SpringApplication.run(PlayersApplication.class, args);
	}

	@Override
	public void run(String... args) {
		// TODO Auto-generated method stub
		uploadFileService.deleteAll();
		try {
			uploadFileService.init();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
