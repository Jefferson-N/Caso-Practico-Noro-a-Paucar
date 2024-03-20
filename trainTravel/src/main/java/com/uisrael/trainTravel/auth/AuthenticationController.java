package com.uisrael.trainTravel.auth;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.uisrael.trainTravel.models.entity.Rol;
import com.uisrael.trainTravel.models.entity.buissness.Usuario;
import com.uisrael.trainTravel.services.IClienteService;
import com.uisrael.trainTravel.services.IUserService;
import com.uisrael.trainTravel.utils.Constants;
import com.uisrael.trainTravel.utils.Utils;

@RequestMapping("auth")
@Controller
public class AuthenticationController implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String ENDPOINT_PATH = "user";

    @Autowired
    private IUserService iUserService;

    @Autowired
    private IClienteService iClienteService;

    @GetMapping("/home")
    private String mainPage(Model model) {

        Usuario user = Utils.getAuthenticationUser();

        Utils.setHeaderOptions(model, user.getRol().name());
        Utils.setEndPonitBase(model, "auth");

        model.addAttribute("listObject", iUserService.getAll());

        return "auth"
                .concat("/" + AuthenticationController.ENDPOINT_PATH.concat("-table"));
    }

    @GetMapping("login")
    private String login() {

        return "auth/login";
    }

    @GetMapping("register")
    private String register(Model model) {

        Utils.setEndPonitBase(model, "auth");

        Usuario obj = new Usuario();

        model.addAttribute("Object", obj);
        model.addAttribute("required", 0);

        return "auth"
                .concat("/" + AuthenticationController.ENDPOINT_PATH.concat("-register"));
    }

    @GetMapping("update/{id}")
    private String update(@PathVariable Integer id, Model model) {

        Optional<Usuario> obj = iUserService.findById(id);
        Utils.setEndPonitBase(model, "auth");

        model.addAttribute("Object", obj.get());
        model.addAttribute("required", 0);

        return "auth"
                .concat("/" + AuthenticationController.ENDPOINT_PATH.concat("-register"));
    }

    @GetMapping("delete/{id}")
    private String delete(@PathVariable Integer id, RedirectAttributes redirectAttrs) {

        try {
            iUserService.deleteById(id);

            redirectAttrs.addFlashAttribute("mensaje", "Eliminado correctamente")
                    .addFlashAttribute("class", "success");

        } catch (Exception e) {
            redirectAttrs.addFlashAttribute("mensaje", "[Error]: " + e.getMessage())
                    .addFlashAttribute("class", "danger");
        }

        return "redirect:/";
    }

    @PostMapping("register")
    private String register(@ModelAttribute("User") Usuario obj,
            RedirectAttributes redirectAttrs) {

        String mensaje = null;
        try {

            if (Objects.equals(obj.getId(), Constants.Zero)) {

                obj.getFkCliente().setRol(Rol.USER.name());

                iClienteService.save(obj.getFkCliente());
                obj.setRol(Rol.USER);
                mensaje = "Creado";

                obj.setUsername(obj.getFkCliente().getEmail());
                obj.setEstado("V");
                iUserService.save(obj);

            } else {
                mensaje = "Editado";
                iUserService.update(obj);

            }

            redirectAttrs.addFlashAttribute("mensaje", mensaje + " correctamente")
                    .addFlashAttribute("class", "success");
        } catch (Exception ex) {

            redirectAttrs.addFlashAttribute("mensaje", "Error al procesar la operación")
                    .addFlashAttribute("class", "danger");
        }
        return "redirect:/";

    }

}
