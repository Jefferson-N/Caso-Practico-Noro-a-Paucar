package com.uisrael.trainTravel.models.entity.buissness;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import groovy.transform.ToString;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@ToString
@Table(name = "cliente")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cl_id")
	private int id;

	@Column(name = "cl_cedula")
	private String cedula;

	@Column(name = "cl_nombre", length = 30)
	private String nombre;

	@Column(name = "cl_direccion")
	private String direccion;

	@Column(name = "cl_apellido", length = 30)
	private String apellido;

	@Column(name = "cl_telefono", length = 10)
	private String telefono;
	
	@Column(name = "cl_rol")
	private String rol;

	@Column(name = "cl_email")
    private String email;

	@OneToMany(mappedBy = "fkCliente")
	private List<Usuario> user = new ArrayList<>();

	@OneToMany(mappedBy = "fkCliente")
	private List<Reserva> reservas = new ArrayList<>();

	@OneToMany(mappedBy = "fkCliente")
	private List<Mensajes> mensajes = new ArrayList<>();


}
