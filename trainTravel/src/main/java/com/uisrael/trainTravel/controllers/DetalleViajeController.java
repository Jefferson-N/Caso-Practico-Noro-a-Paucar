package com.uisrael.trainTravel.controllers;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.uisrael.trainTravel.models.entity.buissness.DetalleViaje;
import com.uisrael.trainTravel.models.entity.buissness.Usuario;
import com.uisrael.trainTravel.services.IDetalleViajeService;
import com.uisrael.trainTravel.services.IReservaService;
import com.uisrael.trainTravel.services.ITrenService;
import com.uisrael.trainTravel.utils.Constants;
import com.uisrael.trainTravel.utils.Utils;

@RequestMapping("detalle-viaje")
@Controller
public class DetalleViajeController implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String ENDPOINT_PATH = "detalle-viaje";

    @Autowired
    private IDetalleViajeService iDetalleViajeService;
    @Autowired 
    private IReservaService iReservaService;

    @Autowired 
    private ITrenService iTrenService;




    @GetMapping("/home")
    private String mainPage(Model model) {

        Usuario user = Utils.getAuthenticationUser();

        Utils.setHeaderOptions(model, user.getRol().name());
        Utils.setEndPonitBase(model, DetalleViajeController.ENDPOINT_PATH);


        model.addAttribute("listObject", iDetalleViajeService.getAll());

        return DetalleViajeController.ENDPOINT_PATH
                .concat("/" + DetalleViajeController.ENDPOINT_PATH.concat("-table"));
    }

    @GetMapping("/register")
    private String register(Model model) {
        Usuario user = Utils.getAuthenticationUser();

        Utils.setHeaderOptions(model, user.getRol().name());
        Utils.setEndPonitBase(model, DetalleViajeController.ENDPOINT_PATH);

        DetalleViaje obj = new DetalleViaje();

        model.addAttribute("Object", obj);
        model.addAttribute("fkReservaList", iReservaService.getAll());
        model.addAttribute("fkTrenList", iTrenService.getAll());

        model.addAttribute("required", 0);

        

        return DetalleViajeController.ENDPOINT_PATH
                .concat("/" + DetalleViajeController.ENDPOINT_PATH.concat("-register"));
    }

    @GetMapping("update/{id}")
    private String update(@PathVariable Integer id, Model model) {

        Utils.setEndPonitBase(model, DetalleViajeController.ENDPOINT_PATH);
        // Utils.setBackButton(model, DetalleViajeController.ENDPOINT_PATH);

        DetalleViaje obj;
        try {
            obj = iDetalleViajeService.getByID(id);
            model.addAttribute("Object", obj);
            model.addAttribute("required", 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        

        return DetalleViajeController.ENDPOINT_PATH
                .concat("/" + DetalleViajeController.ENDPOINT_PATH.concat("-register"));
    }

    @PostMapping("register")
    private String register(@ModelAttribute("DetalleViaje") DetalleViaje obj, RedirectAttributes redirectAttrs) {

        String mensaje = null;

        Utils.setEndPonitBase(redirectAttrs, DetalleViajeController.ENDPOINT_PATH);

        try {

            if (Objects.equals(obj.getId(), Constants.Zero)) {

                mensaje = "Creado";

                iDetalleViajeService.save(obj);

            } else {
                mensaje = "Editado";
                iDetalleViajeService.update(obj);

            }

            Utils.setMessageUI(redirectAttrs, mensaje, Constants.CLASS_SUCCESS);

        } catch (Exception ex) {

            Utils.setMessageUI(redirectAttrs, Constants.ERROR_OPERACION, Constants.CLASS_DANGER);

        }
        return "redirect:/".concat(DetalleViajeController.ENDPOINT_PATH.concat(Constants.HOME));

    }

    @GetMapping("delete/{id}")
    private String delete(@PathVariable Integer id, RedirectAttributes redirectAttrs) {

        Utils.setEndPonitBase(redirectAttrs, DetalleViajeController.ENDPOINT_PATH);

        try {
            iDetalleViajeService.delete(id);

            Utils.setMessageUI(redirectAttrs, Constants.DELETE_SUCCESS, Constants.CLASS_SUCCESS);

        } catch (Exception e) {

            Utils.setMessageUI(redirectAttrs, Constants.ERROR_OPERACION, Constants.CLASS_DANGER);

        }

        return "redirect:/".concat(DetalleViajeController.ENDPOINT_PATH.concat(Constants.HOME));
    }
}
