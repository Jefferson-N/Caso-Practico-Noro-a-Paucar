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

import com.uisrael.trainTravel.models.entity.buissness.Mensajes;
import com.uisrael.trainTravel.models.entity.buissness.Usuario;
import com.uisrael.trainTravel.services.IMensajeService;
import com.uisrael.trainTravel.utils.Constants;
import com.uisrael.trainTravel.utils.Utils;

@RequestMapping("mensajes")
@Controller
public class MensajesController implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String ENDPOINT_PATH = "mensajes";

    @Autowired
    private IMensajeService iMensajesService;


    @GetMapping("/home")
    private String mainPage(Model model) {

        Usuario user = Utils.getAuthenticationUser();

        Utils.setHeaderOptions(model, user.getRol().name());
        Utils.setEndPonitBase(model, MensajesController.ENDPOINT_PATH);

        model.addAttribute("listObject", iMensajesService.getAll());

        return MensajesController.ENDPOINT_PATH
                .concat("/" + MensajesController.ENDPOINT_PATH.concat("-table"));
    }

    @GetMapping("/register")
    private String register(Model model) {
        Usuario user = Utils.getAuthenticationUser();

        Utils.setHeaderOptions(model, user.getRol().name());
        Utils.setEndPonitBase(model, MensajesController.ENDPOINT_PATH);

        Mensajes obj = new Mensajes();

        model.addAttribute("Object", obj);
        model.addAttribute("required", 0);

        

        return MensajesController.ENDPOINT_PATH
                .concat("/" + MensajesController.ENDPOINT_PATH.concat("-register"));
    }

    @GetMapping("update/{id}")
    private String update(@PathVariable Integer id, Model model) {

        Utils.setEndPonitBase(model, MensajesController.ENDPOINT_PATH);
        Utils.setHeaderOptions(model, MensajesController.ENDPOINT_PATH);

        Mensajes obj;
        try {
            obj = iMensajesService.getByID(id);
            model.addAttribute("Object", obj);
            model.addAttribute("required", 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        

        return MensajesController.ENDPOINT_PATH
                .concat("/" + MensajesController.ENDPOINT_PATH.concat("-register"));
    }

    @PostMapping("register")
    private String register(@ModelAttribute("Mensajes") Mensajes obj, RedirectAttributes redirectAttrs) {

        String mensaje = null;

        Utils.setEndPonitBase(redirectAttrs, MensajesController.ENDPOINT_PATH);

        try {

            if (Objects.equals(obj.getId(), Constants.Zero)) {

                mensaje = "Creado";

                iMensajesService.save(obj);

            } else {
                mensaje = "Editado";
                iMensajesService.update(obj);

            }

            Utils.setMessageUI(redirectAttrs, mensaje, Constants.CLASS_SUCCESS);

        } catch (Exception ex) {

            Utils.setMessageUI(redirectAttrs, Constants.ERROR_OPERACION, Constants.CLASS_DANGER);

        }
        return "redirect:/".concat(MensajesController.ENDPOINT_PATH.concat(Constants.HOME));

    }

    @GetMapping("delete/{id}")
    private String delete(@PathVariable Integer id, RedirectAttributes redirectAttrs) {

        Utils.setEndPonitBase(redirectAttrs, MensajesController.ENDPOINT_PATH);

        try {
            iMensajesService.delete(id);

            Utils.setMessageUI(redirectAttrs, Constants.DELETE_SUCCESS, Constants.CLASS_SUCCESS);

        } catch (Exception e) {

            Utils.setMessageUI(redirectAttrs, Constants.ERROR_OPERACION, Constants.CLASS_DANGER);

        }

        return "redirect:/".concat(MensajesController.ENDPOINT_PATH.concat(Constants.HOME));
    }
}
