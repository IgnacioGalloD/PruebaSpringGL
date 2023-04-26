package com.nachito.demo;


import com.nachito.demo.domain.User;
import com.nachito.demo.dto.UserDTO;
import com.nachito.demo.exceptions.DemoNotFoundException;
import com.nachito.demo.repository.UserRepository;
import com.nachito.demo.service.impl.DemoServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.MapKey;

import java.util.Optional;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class DemoApplicationTests {

	@Mock
	private UserRepository repo;

	@InjectMocks
	private DemoServiceImpl service;

	@Test
	public void dadoUnIdInexistente_cuandoGetUsuario_thenNotFound() {
		//given
		Integer id = 123456;
		given(repo.findById(anyInt())).willReturn(Optional.empty());

		//when
		UserDTO response = null;
		try {
			response = service.getUser(id);
			fail("El usuario debira de no existir");
		} catch (DemoNotFoundException ignore) {
		}
		//then
		then(repo).should(times(1)).findById(anyInt());
		//assertEquals(Optional.empty(), response);

	}

	

}
