package hello;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@DataJpaTest
public class ApplicantRepositoryTests {
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private ApplicantRepository applicants;

	@Test
	public void testFindByLastName() {
		Applicant applicant = new Applicant("first", "last", "first@Last.com");
		entityManager.persist(applicant);

		//holds last name
		List<Applicant> findByLastName = applicants.findByLastName(applicant.getLastName());

		assertThat(findByLastName).extracting(Applicant::getLastName).containsOnly(applicant.getLastName());
		assertTrue(!findByLastName.isEmpty());
	}

	@Test
	public void testSaveApplicant() {
		Applicant applicant = new Applicant("first", "last", "first@Last.com");
		entityManager.persist(applicant);

		//save to list
		Applicant savedApplicants = applicants.save(applicant);

		assertThat(savedApplicants).isNotNull();
		assertThat(savedApplicants).isEqualToComparingFieldByField(applicant);

		//save to repository
		applicants.save(applicant);
		
		assertNotNull(applicant.getId());
		assertTrue(applicant.getId() > 0);
	}

	@Test
	public void testUpdateApplicant() {
		Applicant applicant = new Applicant("first", "last", "first@Last.com");
		Applicant applicant2 = new Applicant("second", "second", "second@second.com");
		applicant2.setId(1L);
		
		applicants.save(applicant);
		applicants.save(applicant2);
		assertThat(applicants.findOne(1L)).isNotNull();
		
		assertThat(applicants.findOne(applicant2.getId()).getId()).isEqualTo(applicant2.getId());
		assertThat(applicants.findOne(applicant2.getId()).getFirstName()).isEqualTo(applicant2.getFirstName());
		assertThat(applicants.findOne(applicant2.getId()).getLastName()).isEqualTo(applicant2.getLastName());
		assertThat(applicants.findOne(applicant2.getId()).getEmail()).isEqualTo(applicant2.getEmail());
		
		assertThat(applicants.exists(applicant2.getId()));
	}

	@Test
	public void testDeleteApplicant() {
		Applicant applicant = new Applicant("first", "last", "first@Last.com");
		
		applicants.save(applicant);
		assertNotNull(applicants.findOne(applicant.getId()));
		
		applicants.delete(applicant);
		assertNull(applicants.findOne(applicant.getId()));
		assertFalse(applicants.exists(applicant.getId()));
	}
	
	@Test
	public void testFindOne(){
		Applicant applicant = new Applicant("first", "last", "first@Last.com");

		applicants.save(applicant);
		assertNotNull(applicants.findOne(applicant.getId()));
	}
	
	@Test
	public void testFindAll(){
		assertNotNull(applicants);
		assertFalse(applicants.count() <=0);
		assertThat(applicants.findAll()).isNotEmpty();
		assertThat(applicants.findAll()).containsAll(applicants.findByLastName("last"));
	}
}