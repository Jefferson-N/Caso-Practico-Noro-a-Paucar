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
@Table(name = "tren")
public class Tren implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tr_id")
	private int id;

	@Column(name = "tr_codigo", length = 30)
	private String codigo;

	@Column(name = "tr_modelo")
	private String modelo;

	@Column(name = "tr_capacidad")
	private int capacidad;

	@OneToMany(mappedBy = "fkTren")
	private List<DetalleViaje> detalleViajes = new ArrayList<>();
	

}
