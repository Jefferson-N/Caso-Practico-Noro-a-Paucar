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

import com.uisrael.trainTravel.models.entity.buissness.Cliente;
import com.uisrael.trainTravel.models.entity.buissness.Usuario;
import com.uisrael.trainTravel.services.IClienteService;
import com.uisrael.trainTravel.utils.Constants;
import com.uisrael.trainTravel.utils.Utils;

@RequestMapping("cliente")
@Controller
public class ClienteController implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String ENDPOINT_PATH = "cliente";

    @Autowired
    private IClienteService iClienteService;


    @GetMapping("/home")
    private String mainPage(Model model) {

        Usuario user = Utils.getAuthenticationUser();

        Utils.setHeaderOptions(model, user.getRol().name());
        Utils.setEndPonitBase(model, ClienteController.ENDPOINT_PATH);

        model.addAttribute("listObject", iClienteService.getAll());

        return ClienteController.ENDPOINT_PATH
                .concat("/" + ClienteController.ENDPOINT_PATH.concat("-table"));
    }

    @GetMapping("/register")
    private String register(Model model) {
        Usuario user = Utils.getAuthenticationUser();

        Utils.setHeaderOptions(model, user.getRol().name());
        Utils.setEndPonitBase(model, ClienteController.ENDPOINT_PATH);

        Cliente obj = new Cliente();

        model.addAttribute("Object", obj);
        model.addAttribute("required", 0);


        return ClienteController.ENDPOINT_PATH
                .concat("/" + ClienteController.ENDPOINT_PATH.concat("-register"));
    }

    @GetMapping("update/{id}")
    private String update(@PathVariable Integer id, Model model) {

        Utils.setEndPonitBase(model, ClienteController.ENDPOINT_PATH);
        // Utils.setBackButton(model, ClienteController.ENDPOINT_PATH);

        Cliente obj;
        try {
            obj = iClienteService.getByID(id);
            model.addAttribute("Object", obj);
            model.addAttribute("required", 0);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return ClienteController.ENDPOINT_PATH
                .concat("/" + ClienteController.ENDPOINT_PATH.concat("-register"));
    }

    @PostMapping("register")
    private String register(@ModelAttribute("Cliente") Cliente obj, RedirectAttributes redirectAttrs) {

        String mensaje = null;

        Utils.setEndPonitBase(redirectAttrs, ClienteController.ENDPOINT_PATH);

        try {

            if (Objects.equals(obj.getId(), Constants.Zero)) {

                mensaje = "Creado";

                iClienteService.save(obj);

            } else {
                mensaje = "Editado";
                iClienteService.update(obj);

            }

            Utils.setMessageUI(redirectAttrs, mensaje, Constants.CLASS_SUCCESS);

        } catch (Exception ex) {

            Utils.setMessageUI(redirectAttrs, Constants.ERROR_OPERACION, Constants.CLASS_DANGER);

        }
        return "redirect:/".concat(ClienteController.ENDPOINT_PATH.concat(Constants.HOME));

    }

    @GetMapping("delete/{id}")
    private String delete(@PathVariable Integer id, RedirectAttributes redirectAttrs) {

        Utils.setEndPonitBase(redirectAttrs, ClienteController.ENDPOINT_PATH);

        try {
            iClienteService.delete(id);

            Utils.setMessageUI(redirectAttrs, Constants.DELETE_SUCCESS, Constants.CLASS_SUCCESS);

        } catch (Exception e) {

            Utils.setMessageUI(redirectAttrs, Constants.ERROR_OPERACION, Constants.CLASS_DANGER);

        }

        return "redirect:/".concat(ClienteController.ENDPOINT_PATH.concat(Constants.HOME));
    }
}
