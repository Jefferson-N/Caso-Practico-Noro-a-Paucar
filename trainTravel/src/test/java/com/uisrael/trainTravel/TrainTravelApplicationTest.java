package com.uisrael.trainTravel;

import com.uisrael.trainTravel.services.IUserService;
import com.uisrael.trainTravel.models.entity.Rol;
import com.uisrael.trainTravel.models.entity.buissness.Cliente;
import com.uisrael.trainTravel.services.IClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.uisrael.trainTravel.models.entity.buissness.Usuario;

@SpringBootTest
class TrainTravelApplicationTest {

	@Autowired
	private IUserService iUserService;




	@Autowired
	private IClienteService iClienteService;



	private Cliente cliente;

	@BeforeEach
	void beforeEach() {
		cliente = new Cliente();

		cliente.setNombre("prueba");
		cliente.setApellido("prueba");
		cliente.setTelefono("098321456");
		cliente.setEmail("prueba@mail.com");

		

	}

	@Test
	@Order(1)
	void contextLoads() {
	}

	@Test
	@Order(2)
	void insertUser() {

		try {

			iClienteService.save(cliente);

			Usuario user = new Usuario();

			user.setFkCliente(cliente);
			user.setEstado("V");
			user.setUsername(cliente.getEmail());
			user.setPassword("1234");
			user.setRol(Rol.ADMIN);
			iUserService.save(user);



		} catch (Exception e) {
			System.err.print(e.getMessage());
		}

	}

}
