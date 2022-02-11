package com.scnsoft.cocktails.rest;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scnsoft.cocktails.entity.Label;
import com.scnsoft.cocktails.service.LabelService;

@RestController
@RequestMapping("/api")
public class LabelRestController {
	
	@Autowired
	private LabelService labelService;
	
	@GetMapping("/labels")
	public List<Label> getLabels() {
		return labelService.findAll();
	}
	
	@GetMapping("/labels/{labelId}")
	public Label getLabel(@PathVariable UUID labelId) {
		return labelService.getById(labelId);
	}
	
	@PostMapping("/labels")
	public Label addLabel(@RequestBody Label theLabel) {
		theLabel.setId(null);
		
		return labelService.save(theLabel);
	}
	
	@PutMapping("/labels")
	public Label updateEmployee(@RequestBody Label theLabel) {
		return labelService.save(theLabel);
	}
	
	@DeleteMapping("/labels/{labelId}")
	public String deleteEmployee(@PathVariable UUID labelId) {
		labelService.deleteById(labelId);
		
		return "Deleted label id - " + labelId;
	}
}
