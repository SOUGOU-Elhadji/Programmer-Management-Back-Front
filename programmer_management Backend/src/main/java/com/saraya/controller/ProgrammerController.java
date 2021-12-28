package com.saraya.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saraya.exception.ResourceNotFoundException;
import com.saraya.model.Programmer;
import com.saraya.repository.ProgrammerRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/programmers-api/")
public class ProgrammerController {
	
	@Autowired
	ProgrammerRepository programmerRepository;
	
	// get all programmer
	@GetMapping("/programmers")
	public List<Programmer> getAllProgrammers(){
	return programmerRepository.findAll();
	}
	// create programmer
	@PostMapping("/programmers")
	public Programmer createProgrammer(@RequestBody Programmer programmer) {
	return programmerRepository.save(programmer);
	}
	
	// get programmer by id 
	@GetMapping("/programmers/{id}")public ResponseEntity<Programmer> getProgrammerById(@PathVariable Long id)
	{
	Programmer programmer = programmerRepository.findById(id)
	.orElseThrow(() -> new ResourceNotFoundException("Programmer not exist with id :" + id));
	return ResponseEntity.ok(programmer);
	}
	// update programmer
	@PutMapping("/programmers/{id}")
	public ResponseEntity<Programmer> updateProgrammer(@PathVariable Long id, @RequestBody Programmer programmerDetails){
	Programmer programmer = programmerRepository.findById(id)
	.orElseThrow(() -> new ResourceNotFoundException("Programmer not exist with id :" + id));
	programmer.setFirstName(programmerDetails.getFirstName());
	programmer.setLastName(programmerDetails.getLastName());
	programmer.setEmail(programmerDetails.getEmail());
	Programmer updatedProgrammer = programmerRepository.save(programmer);
	return ResponseEntity.ok(updatedProgrammer);
	}
	
	
	// delete programmer
	@DeleteMapping("/programmers/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteProgrammer(@PathVariable
	Long id){
	Programmer programmer = programmerRepository.findById(id)
	.orElseThrow(() -> new ResourceNotFoundException("Programmer not exist with id :" + id));
	programmerRepository.delete(programmer);
	Map<String, Boolean> response = new HashMap<>();
	response.put("deleted", Boolean.TRUE);
	return ResponseEntity.ok(response);
	}
}
