package com.example.hospital;

import com.example.hospital.entities.Patient;
import com.example.hospital.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Date;
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

        // Ajouter des patients
        patientRepository.save(new Patient(1, "labrini ouiam", new Date(2002,03,03) , false, 23));
        patientRepository.save(new Patient(2, "toto rajae", new Date(2004,03,03), false, 73));
        patientRepository.save(new Patient(3, "john doe", new Date(1998,03,03), true, 45));

        // Consulter tous les patients
        List<Patient> patients = patientRepository.findAll();
        System.out.println("Liste de tous les patients :");
        patients.forEach(patient -> System.out.println(patient.toString()));

        // Consulter un patient par son ID
        Patient patient = patientRepository.findById(Long.valueOf(1)).get();
        System.out.println("************************************");
        System.out.println("Patient avec l'ID 1 :");
        System.out.println(patient.toString());

        // Chercher des patients par nom
        List<Patient> patientsByName = patientRepository.findByNameContaining("toto");
        System.out.println("************************************");
        System.out.println("Patients dont le nom contient 'toto' :");
        patientsByName.forEach(p -> System.out.println(p.toString()));

        // Mettre à jour un patient
        Patient patientToUpdate = patientRepository.findById(Long.valueOf(2)).get();
        patientToUpdate.setName("toto updated");
        patientToUpdate.setScore(80);
        patientRepository.save(patientToUpdate);
        System.out.println("************************************");
        System.out.println("Patient mis à jour :");
        System.out.println(patientToUpdate.toString());

        // Supprimer un patient par son ID
        patientRepository.deleteById(Long.valueOf(3));
        System.out.println("************************************");
        System.out.println("Patient avec l'ID 3 supprimé.");

        // Consulter tous les patients après suppression
        List<Patient> remainingPatients = patientRepository.findAll();
        System.out.println("************************************");
        System.out.println("Liste des patients après suppression :");
        remainingPatients.forEach(p -> System.out.println(p.toString()));
    }
}