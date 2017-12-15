package com.glsid;

import com.glsid.Dao.SocieteRepository;
import com.glsid.Entities.Societe;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class OrdreApplication {

	public  void main(String[] args) {
		SpringApplication.run(OrdreApplication.class, args);
	}
	public void run(String... arg0) throws Exception {

		Societe S1= SocieteRepository.save(new Societe("A123","BCP"));
		Societe S2=SocieteRepository.save(new Societe("B123","HEM");
		Societe S3=SocieteRepository.save(new Societe("C123","OCP");
	}
}
