package hello;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ApplicantRepository extends CrudRepository<Applicant, Long> {

    List<Applicant> findByLastName(String lastName);
}
