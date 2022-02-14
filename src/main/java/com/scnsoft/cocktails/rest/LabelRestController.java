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

import com.scnsoft.cocktails.entity.LabelDTO;
import com.scnsoft.cocktails.facade.LabelFacade;

@RestController
@RequestMapping("/api/labels")
public class LabelRestController {
	
	@Autowired
	private LabelFacade labelFacade;
	
	@GetMapping
	public List<LabelDTO> getLabels() {
		return labelFacade.findAll();
	}
	
	@GetMapping("/{labelId}")
	public LabelDTO getLabel(@PathVariable UUID labelId) {
		return labelFacade.getById(labelId);
	}
	
	@PostMapping
	public LabelDTO addLabel(@RequestBody LabelDTO theLabel) {
		theLabel.setId(null);
		
		return labelFacade.save(theLabel);
	}
	
	@PutMapping
	public LabelDTO updateLabel(@RequestBody LabelDTO theLabel) {
		return labelFacade.save(theLabel);
	}
	
	@DeleteMapping("/{labelId}")
	public String deleteLabel(@PathVariable UUID labelId) {
		labelFacade.deleteById(labelId);
		
		return "Deleted label id - " + labelId;
	}
}
