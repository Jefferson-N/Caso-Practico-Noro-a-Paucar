package com.uisrael.trainTravel.utils;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.uisrael.trainTravel.models.entity.buissness.Usuario;

public class Utils {

    public static String getPasswordEncrpt(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    public static void setMessageUI(RedirectAttributes redirectAttrs,
            String message, String property) {
        message = "danger".equals(property) ? message : message.concat(" correctamente");

        redirectAttrs.addFlashAttribute("mensaje", message)
                .addFlashAttribute("class", property);
    }

    public static void setHeaderOptions(Model model, String userRol) {

        Map<String, String> options = new LinkedHashMap<>();

        options.put("Mensajes", "/mensajes/home");
        options.put("Reservar viaje", "/");

        
        switch (userRol) {
            case "ADMIN":
            options.put("Ver clientes", "/cliente/home");
            options.put("Ingresar Trenes", "/tren/home");
            options.put("Ver Boletos", "/Boletos/home");
            options.put("Ver Reservas", "/Reserva/home");
            options.put("Asignar tren", "/detalle-viaje/home");
            options.put("Ingresar Trenes", "/tren/home");



                break;

            case "USER":

                options.put("Reservar viaje", "/");
                options.put("Ver Boletos", "/Boletos/home");
                options.put("Ver Reservas", "/Reserva/home");

                break;

            default:
                options.put("Home", "/");

                break;
        }

        model.addAttribute("menuOptions", options);

        model.addAttribute("appName", Constants.APP);


    }

    public static Usuario getAuthenticationUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (Usuario) authentication.getPrincipal();
    }

    public static void setEndPonitBase(Model model, String value) {
        model.addAttribute("path", value);
        model.addAttribute("btnBack", value.concat(Constants.HOME));
    }

    public static void getAuthEndPonitBase(Model model, String value) {
        model.addAttribute("path", "/".concat(value));
        model.addAttribute("btnBack", value.concat(Constants.HOME));
    }

}
