package com.ensa.services;

import com.ensa.entity.DemandeService;
import com.ensa.entity.Student;
import com.ensa.repositories.DemandeServiceRepository;
import com.ensa.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class ServDemService {

    private DemandeServiceRepository demandeServiceRepository;
    private StudentRepository studentRepository;

    @Autowired
    public ServDemService(DemandeServiceRepository demandeServiceRepository,StudentRepository studentRepository) {
        this.demandeServiceRepository = demandeServiceRepository;
        this.studentRepository = studentRepository;
    }

    public DemandeService addDemande(HashMap<String, String> map) throws Exception{
        String cin = map.get("cin");
        Student student = studentRepository.findByCin(cin).orElseThrow(()-> new Exception("the student with the given cin was not found"));

        DemandeService d = new DemandeService();
        d.setStatus("En cours de traitement");
        d.setContent(map.get("content"));
        d.setLibelle(map.get("libelle"));
        d.setStudent(student);
        return demandeServiceRepository.save(d);
    }


    public List<DemandeService> getDemandeServiceByStudent(String email)throws Exception{
        Student student = studentRepository.findByEmail(email).orElseThrow(()-> new Exception("the student with the given email was not found"));

        return demandeServiceRepository.findAllByStudent(student);
    }

    public List<DemandeService> getAllDemande(){
        return demandeServiceRepository.findAll();
    }

    public DemandeService updateDemande(Long id,String etat)throws Exception{
        DemandeService d = demandeServiceRepository.findById(id).orElseThrow(()-> new Exception("Demande with the given id was not found"));
        d.setStatus(etat);
        return demandeServiceRepository.save(d);
    }

}
