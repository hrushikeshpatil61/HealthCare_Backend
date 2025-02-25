package com.Hospital.Management.System.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Hospital.Management.System.doclogin.entity.Appointment;
import com.Hospital.Management.System.entity.Patient;
import com.Hospital.Management.System.repository.PatientRepository;

@CrossOrigin(origins = "http:/localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class PatientController {
	private PatientRepository patientRepository;

	public PatientController(PatientRepository patientRepository) {
		super();
		this.patientRepository = patientRepository;
	}

	@PostMapping("/insert")
	public Patient createPatient(@RequestBody Patient patient) {
		return patientRepository.save(patient);
	}
	
	@GetMapping
	public List<Patient> getAllPatient(){
		return patientRepository.findAll();
	}
	@DeleteMapping("/patients/{id}")
	public ResponseEntity<Map<String, Boolean>>deletePatient(@PathVariable long id){
		Patient patient = patientRepository.findById(id).orElseThrow();
		
		patientRepository.delete(patient);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("Deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
}
