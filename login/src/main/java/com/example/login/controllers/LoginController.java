package com.example.login.controllers;

import com.example.login.dao.IUsuarioDAO;
import com.example.login.entity.Usuario;
import com.example.login.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping("/auth/login")
    public String login(Model model){
        model.addAttribute("usuario", new Usuario());
        return "/layouts/login";
    }

    @RequestMapping(value = "/auth/registro")
    public String registroForm(Map<String, Object> model){
        //model.addAttribute("usuario", new Usuario());

        Usuario usuario = new Usuario();
        model.put("usuario", usuario);
        model.put("titulo", "Formulario de cliente");
        return "/layouts/registro";
    }
/*
    @RequestMapping(value = "/auth/registro", method = RequestMethod.POST)
    public String registro(@Valid @ModelAttribute Usuario usuario, BindingResult result, RedirectAttributes flash
            , Model model){

        if ( result.hasErrors()){
            return "/layouts/registro";
        }
        else{
            System.out.println("si funciono");
        }
        return "redirect:/index";
    }
    */
/*
    @GetMapping("/auth/listar")
    public String listarForm(Model model){
        model.addAttribute("usuario", new Usuario());
        return "/auth/listar";
    }

 */

    @RequestMapping(value = "/auth/listar", method = RequestMethod.GET)
    public String listar(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("usuarios", usuarioService.findAll());
        return "/layouts/listar";
    }

    @RequestMapping(value = "/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
        if (id > 0) {
            usuarioService.delete(id);
            flash.addFlashAttribute("success", "Eliminado Con éxito");
        }
        return "redirect:/auth/listar";
    }
/*
    @PostMapping("/auth/registro")
    public String registro(@Valid @ModelAttribute Usuario usuario, BindingResult result, RedirectAttributes flash
    , Model model){
        if ( result.hasErrors()){
            return "redirect:/auth/registro?error";
        }
        else{
            model.addAttribute("usuario", usuarioService.registrar(usuario));
        }
        return "redirect:/auth/login";
    }
*/
/*
    @RequestMapping(value = "/registro/{id}")
    public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {
        Usuario usuario = null;

        if (id > 0) {
            usuario = usuarioService.finOne(id);
            if (usuario == null) {
                flash.addFlashAttribute("error", "El id no existe en la base de datos");
                return "redirect:/auth/listar";
            }
        } else {
            flash.addFlashAttribute("error", "El Id del cliente no puede ser 0");
            return "redirect:/listar";
        }
        model.put("usuario", usuario);
        model.put("titulo", "editar cliente");
        return "redirect:/auth/registro";
    }

*/
/*
    @PostMapping("/auth/registro")
    public String registro(@Valid Usuario usuario,BindingResult result, RedirectAttributes flash
            , Model model,@RequestParam(name = "file", required = true) MultipartFile foto) {

        Date fechaActual = new Date();
        Date fechaFormulario = usuario.getCreateAt();

        if (!foto.isEmpty() || foto.isEmpty() ) {
                String ruta = "E://Temp//uploads";
                try {
                    byte[] bytes = foto.getBytes();
                    Path rutaAbsoluta = Paths.get(ruta + "//" + foto.getOriginalFilename());
                    Files.write(rutaAbsoluta, bytes);
                    usuario.setFoto(foto.getOriginalFilename());

                    if (usuario.getPassword().isEmpty() || result.hasErrors() || usuario.getUsername().isEmpty() || fechaFormulario.after(fechaActual)){
                        return "/layouts/registro";
                    }
                } catch (Exception e) {
                    return "redirect:/auth/registro?error";
                }
            }


            model.addAttribute("usuario", usuarioService.registrar(usuario));
        return "redirect:/auth/login";
    }


*/

    @RequestMapping(value = "/auth/registro", method = RequestMethod.POST)
    public String registro(@Valid @ModelAttribute Usuario usuario, BindingResult result, RedirectAttributes flash
            , Model model,@RequestParam(name = "file", required = true) MultipartFile foto){

        Date fechaActual = new Date();
        Date fechaFormulario = usuario.getCreateAt();

        if ( result.hasErrors() ){
            return "/layouts/registro";
        }

        else{
            if (!foto.isEmpty() || foto.isEmpty()) {
                String ruta = "C://Temp//uploads";
                try {
                    byte[] bytes = foto.getBytes();
                    Path rutaAbsoluta = Paths.get(ruta + "//" + foto.getOriginalFilename());
                    Files.write(rutaAbsoluta, bytes);
                    usuario.setFoto(foto.getOriginalFilename());
                } catch (Exception e) {
                    return "redirect:/auth/registro?error";
                }
            }


            model.addAttribute("usuario", usuarioService.registrar(usuario));

        }
        return "redirect:/auth/login";
    }



}
