package com.uisrael.trainTravel.models.entity.buissness;

import jakarta.persistence.Basic;
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
@Table(name = "detalle_viaje", schema = "train_travel", catalog = "")
public class DetalleViaje {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "dv_id")
    private int id;
    @Basic
    @Column(name = "dv_ruta")
    private String ruta;
    @Basic
    @Column(name = "dv_posicion")
    private String vtPosicion;

    @ManyToOne
    @JoinColumn(name = "id_tren")
    private Tren fkTren;

    @ManyToOne
    @JoinColumn(name = "id_reserva")
    private Reserva fkReserva;
    


    }
