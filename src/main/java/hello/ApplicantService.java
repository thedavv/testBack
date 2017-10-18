package hello;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicantService {

	@Autowired
	ApplicantRepository applicantRepository;

	// get all applicants
	public List<Applicant> getAppliants() {
		List<Applicant> tempApplicant = new ArrayList<>();
		applicantRepository.findAll().forEach(tempApplicant::add);
		return tempApplicant;
	}

	// get applicant by id
	public Applicant getApplicant(Long id) {
		return applicantRepository.findOne(id);
	}

	// save applicant
	public Applicant saveApplicant(Applicant applicant) {
		applicantRepository.save(applicant);
		return applicant;
	}

	// update applicant
	public Applicant updateApplicant(Applicant applicant) {
		applicantRepository.save(applicant);
		return applicant;
	}

	// delete
	public boolean deleteApplicant(Long id) {
		if (applicantRepository.findOne(id) == null) {
			return false;
		}
		applicantRepository.delete(applicantRepository.findOne(id));
		return true;
	}
	
	
}
