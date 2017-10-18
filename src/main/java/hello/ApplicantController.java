package hello;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/users")
@CrossOrigin(origins = "http://localhost:4200")
public class ApplicantController {

	@Autowired
	ApplicantService applicantService;
	
	@GetMapping
	public List<Applicant> getApplicants(){
		return applicantService.getAppliants();
	}
	
	@GetMapping(value = "/{id}")
	public Applicant getApplicant(@PathVariable("id") Long id) {
		return applicantService.getApplicant(id);
	}
	
	@PostMapping
	public Applicant saveApplicant(@RequestBody Applicant applicant) {
		return applicantService.saveApplicant(applicant);
	}
	
	@PutMapping
	public Applicant updateApplicant(@RequestBody Applicant applicant) {
		return applicantService.updateApplicant(applicant);
	}
	
	@DeleteMapping(value = "/{id}")
	public boolean deleteApplicant(@PathVariable("id") Long id) {
		return applicantService.deleteApplicant(id);
	}
}
