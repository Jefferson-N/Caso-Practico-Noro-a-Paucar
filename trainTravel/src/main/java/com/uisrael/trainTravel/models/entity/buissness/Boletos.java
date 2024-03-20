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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "boletos")
public class Boletos implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bo_id")
	private int id;

	@Column(name = "bo_codigo", length = 30)
	private String codigo;

	@Column(name = "bo_descripcion")
	private String direccion;

	@Column(name = "bo_valor")
	private Double valor;

	@ManyToOne
	@JoinColumn(name = "id_reserva")
	private Reserva fkReserva;

}
