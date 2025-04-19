package artists.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import artists.ArtistsApplication;

@SpringBootTest(webEnvironment = WebEnvironment.NONE, classes = ArtistsApplication.class)
@ActiveProfiles("test")
@Sql(scripts = {"classpath:schema.sql", "classpath:data.sql"})
@SqlConfig(encoding = "utf-8")
class ArtistsTest extends ArtistsTestSupport {

	@Test
	void test() {
		
		// Given: 
		
		// When: 
		
		// Then: 
		
		// And: 
		
	}

}
