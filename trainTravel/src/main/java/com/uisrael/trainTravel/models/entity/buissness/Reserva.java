package com.uisrael.trainTravel.models.entity.buissness;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import groovy.transform.ToString;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@ToString
@Table(name = "reserva")
public class Reserva implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "re_id")
	private int id;

	@Column(name = "re_codigo", length = 30)
	private String nombre;

	@Column(name = "re_descripcion")
	private String direccion;

	@Basic
    @Column(name = "vt_fecha")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;

	@OneToMany(mappedBy = "fkReserva")
	private List<InfoCalendario> infoCalendarios = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente fkCliente;

	@ManyToOne
	@JoinColumn(name = "id_boleto")
	private Boletos fkBoleto;

	@OneToMany(mappedBy = "fkReserva")
	private List<DetalleViaje> detalleViajes = new ArrayList<>();

}
