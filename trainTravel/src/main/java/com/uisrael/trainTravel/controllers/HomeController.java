package com.uisrael.trainTravel.controllers;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.uisrael.trainTravel.models.entity.Calendario;
import com.uisrael.trainTravel.models.entity.buissness.Boletos;
import com.uisrael.trainTravel.models.entity.buissness.Cliente;
import com.uisrael.trainTravel.models.entity.buissness.InfoCalendario;
import com.uisrael.trainTravel.models.entity.buissness.Reserva;
import com.uisrael.trainTravel.models.entity.buissness.Usuario;
import com.uisrael.trainTravel.services.IBoletosService;
import com.uisrael.trainTravel.services.IInfoCalendarioService;
import com.uisrael.trainTravel.services.IReservaService;
import com.uisrael.trainTravel.services.IUserService;
import com.uisrael.trainTravel.utils.Constants;
import com.uisrael.trainTravel.utils.Utils;

// @RestController
@Controller
public class HomeController implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private IInfoCalendarioService infoCalendarioService;
    @Autowired
    private IBoletosService iBoletosService;

    @Autowired 
    private IReservaService iReservaService;

    @Autowired private IUserService iUserService;






    @GetMapping("/")
    private String home(Model model) {
        Usuario user = Utils.getAuthenticationUser();

        Utils.setHeaderOptions(model, user.getRol().name());
        Utils.setEndPonitBase(model, "auth");

        List<InfoCalendario> info = infoCalendarioService.getAll();
        List<Calendario> t = new ArrayList<>();

        for (InfoCalendario i : info) {
            String titulo = Objects.isNull(i.getFkReserva() != null?
            i.getFkReserva().getNombre():null)?
            "Viaje": "Viaje - ".concat(i.getFkReserva().getNombre());
            t.add(new Calendario(titulo,
                    LocalDateTime.of(i.getDate().getYear(), i.getDate().getMonth(), i.getDate().getDayOfMonth(),
                            i.getHorario(), 0, 0),
                    LocalDateTime.of(i.getDate().getYear(), i.getDate().getMonth(), i.getDate().getDayOfMonth(),
                            i.getHorario(), 0, 0),
                    false,
                    "green", null, null));
        }
        
        Cliente c = new Cliente();
        model.addAttribute("calendar", t);
        model.addAttribute("object", new InfoCalendario());
        model.addAttribute("user", c);

        return  "calendario/calendario-table";
    }

    @PostMapping("/agendar")
    public String postMethodName(
        RedirectAttributes redirectAttrs, @ModelAttribute("object") InfoCalendario infoCalendario) {

        try {
            Usuario user = iUserService.findById(Utils.getAuthenticationUser().getId()).get();
            Cliente cliente = user.getFkCliente();

            infoCalendarioService.save(infoCalendario);

            int codigoBoleto = (int)iBoletosService.getAll().stream().count()+1;
            
            Boletos boleto = new Boletos();
            boleto.setCodigo("BOLETO-001-0"+ codigoBoleto);
            boleto.setDireccion("ruta");
            boleto.setValor(10.0);

            iBoletosService.save(boleto);


            Reserva reserva = new Reserva();
            reserva.setDireccion("Direccion");
            reserva.setFkBoleto(boleto);
            reserva.setFkCliente(cliente);
            reserva.setFecha(LocalDate.now());
            reserva.setNombre("Reserva viaje - ".concat(cliente.getNombre()));
            
            infoCalendario.setFkReserva(reserva);

            iReservaService.save(reserva);
            
            infoCalendarioService.save(infoCalendario);
            

            Utils.setMessageUI(redirectAttrs, "Guardado Exitosamente", Constants.CLASS_SUCCESS);

        } catch (Exception ex) {


        }

        return "redirect:/";
    }


}
