package com.example.hospital;

import com.example.hospital.entities.Patient;
import com.example.hospital.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.List;

@SpringBootApplication
public class HospitalApplication implements CommandLineRunner {

    @Autowired
    private PatientRepository patientRepository;

    public static void main(String[] args) {
        SpringApplication.run(HospitalApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        patientRepository.save(new Patient(1, "labrini ouiam", sdf.parse("2002-03-23"), false, 23));
        patientRepository.save(new Patient(2, "toto rajae", sdf.parse("2004-03-13"), false, 73));

        List<Patient> patients = patientRepository.findAll();
        patients.forEach(
                patient -> {
                    System.out.println(patient.toString());
                }
        );

        Patient patient=patientRepository.findById(Long.valueOf(1)).get();
        System.out.println("************************************");
        System.out.println(patient.toString());
        
    }
}