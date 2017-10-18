package hello;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ApplicantTest {
	@Test
	public void testIfApllicantHasFields() {
		Applicant subject = new Applicant();
		
		assertThat(subject).isNotNull();
		assertThat(subject).hasFieldOrPropertyWithValue("id", null);
		assertThat(subject).hasFieldOrPropertyWithValue("firstName", null);
		assertThat(subject).hasFieldOrPropertyWithValue("lastName", null);
		assertThat(subject).hasFieldOrPropertyWithValue("email", null);
		
		Applicant subject2 = new Applicant("first", "last", "email");
		assertThat(subject2).isNotNull();
		assertThat(subject2).hasFieldOrPropertyWithValue("firstName", "first");
		assertThat(subject2).hasFieldOrPropertyWithValue("lastName", "last");
		assertThat(subject2).hasFieldOrPropertyWithValue("email", "email");
	}
}
