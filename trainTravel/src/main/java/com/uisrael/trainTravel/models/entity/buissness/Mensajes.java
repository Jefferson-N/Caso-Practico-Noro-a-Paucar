package com.uisrael.trainTravel.models.entity.buissness;

import java.io.Serializable;

import groovy.transform.ToString;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@ToString
@Table(name = "mensaje")
public class Mensajes implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "me_id")
	private int id;

	@Column(name = "me_tipo")
	private String tipo;

	@Column(name = "me_mensaje")
	private String nombre;

	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente fkCliente;



}
