package com.gabrielbatista.userregapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.gabrielbatista.userregapi.domain.User;
import com.gabrielbatista.userregapi.domain.enums.Genre;
import com.gabrielbatista.userregapi.repositories.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // for restTemplate
class UserregapiApplicationTests {

	private static final ObjectMapper om = new ObjectMapper();

	@Autowired
	private TestRestTemplate restTemplate;

	@MockBean
	private UserRepository mockRepository;

	@Before
	public void init() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		User user = new User(2, "Mariana Silva", Genre.FEMININE, "teste@gmail.com", sdf.parse("08/10/1993"), "Pernambucana",
				"Brasileira", "48633820064", "Av. Carlos de Lima Cavalcanti");
		when(mockRepository.findById(2)).thenReturn(Optional.of(user));
	}

	@Test
	public void findUserIdOK() throws JSONException {

		String expected = "{id:2,name:\"Mariana Silva\",genre:\"2\",email:\"teste@gmail.com\",dateBirth:\"1993-10-08\",naturalness:\"Pernambucana\",nationality:\"Brasileira\",cpf:\"48633820064\",address:\"Av. Carlos de Lima Cavalcanti}";

		ResponseEntity<String> response = restTemplate.getForEntity("/v2/users/1", String.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(MediaType.APPLICATION_JSON_UTF8, response.getHeaders().getContentType());

		JSONAssert.assertEquals(expected, response.getBody(), false);

		verify(mockRepository, times(1)).findById(2);

	}
}
