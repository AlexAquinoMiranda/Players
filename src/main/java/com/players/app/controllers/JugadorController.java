package com.players.app.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.players.app.models.entity.Jugador;
import com.players.app.models.service.IJugadorService;
import com.players.app.models.service.IUploadFileService;

@Controller
@SessionAttributes("jugador")
public class JugadorController {

	@Autowired
	private IJugadorService jugadorService;

	@Autowired(required=false)
	private IUploadFileService uploadFileService;

	/**
	 * metodo para ver la foto
	 * 
	 * @param filename
	 * @return
	 */
	@GetMapping(value = "/uploads/{filename:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String filename) {

		Resource recurso = null;

		try {
			recurso = uploadFileService.load(filename);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
				.body(recurso);
	}

	/**
	 * método para ver al usuario de acuerdo a su id
	 * 
	 * @param id
	 * @param model
	 * @param flash
	 * @return
	 */
	@GetMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {


		
		Jugador jugador = jugadorService.findOne(id);
		if (jugador == null) {
			flash.addFlashAttribute("error", "El cliente no existe en la base de datos");
			return "redirect:/listar";
		}

		model.put("jugador", jugador);
		model.put("titulo", "Nombre de Usuario: " + jugador.getNombre());
		return "ver";
	}

	/**
	 * listado de jugadores
	 * 
	 * @param page
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {

		List<Jugador> jugadores = jugadorService.findAllJugador();
		
		/**
		Jugador ale = new Jugador();
		ale.setApellido("aquino");
		ale.setDni("08120107A");
		ale.setDorsal(7);
		ale.setNombre("alecis");
		ale.setId((long) 9);
		jugadores.add(ale);
		*/
		//
		
		 

		// PageRender<Cliente> pageRender = new PageRender<Cliente>("/listar",
		// clientes);
		model.addAttribute("titulo", "Listado de jugadores");
		model.addAttribute("jugadores", jugadores);
		// model.addAttribute("page", pageRender);
		return "listar";
	}

	/**
	 * CREAR JUGADOR
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/form")
	public String crear(Map<String, Object> model) {

		Jugador jugador = new Jugador();
		model.put("jugador", jugador);
		model.put("titulo", "FORMULARIO DE JUGADOR");
		return "form";
	}

	/**
	 * EDITAR JUGADOR
	 * 
	 * @param id
	 * @param model
	 * @param flash
	 * @return
	 */
	@RequestMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		Jugador jugador = null;

		if (id > 0) {
			jugador = jugadorService.findOne(id);
			if (jugador == null) {
				flash.addFlashAttribute("error", "El ID del jugador no existe en la BBDD!");
				return "redirect:/listar";
			}
		} else {
			flash.addFlashAttribute("error", "El ID del jugador no puede ser cero!");
			return "redirect:/listar";
		}
		model.put("jugador", jugador);
		model.put("titulo", "Editar jugador");
		return "form";
	}

	/**
	 * método para autenticar usuario, iniciando con una cuenta actual o registrando
	 * una nueva
	 * 
	 * @param model model para las variables.
	 * @return home home view.
	 */
	@RequestMapping(value = "/home")
	public String home(Map<String, Object> model) {
		model.put("inicio", "Sing in");
		model.put("crear", "Sing up");
		model.put("titulo", "Autenticación de usuario");
		return "home";
	}

	/**
	 * guardar datos
	 * 
	 * @param jugador
	 * @param result
	 * @param model
	 * @param foto
	 * @param flash
	 * @param status
	 * @return
	 */
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid Jugador jugador, BindingResult result, Model model,
			@RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Jugador");
			return "form";
		}

		if (!foto.isEmpty()) {

			if (jugador.getId() != null && jugador.getId() > 0 && jugador.getFoto() != null
					&& jugador.getFoto().length() > 0) {

				uploadFileService.delete(jugador.getFoto());
			}

			String uniqueFilename = null;
			try {
				uniqueFilename = uploadFileService.copy(foto);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename + "'");

			jugador.setFoto(uniqueFilename);
		}

		String mensajeFlash = (jugador.getId() != null) ? "Jugador editado con éxito!" : "Jugador creado con éxito!";

		jugadorService.saveJugador(jugador);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:listar";
	}

	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		if (id > 0) {
			Jugador jugador = jugadorService.findOne(id);

			jugadorService.deleteJugador(id);
			flash.addFlashAttribute("success", "Cliente " + jugador.getNombre() + " eliminado con éxito!");

			/**
			 * if (uploadFileService.delete(cliente.getFoto())) {
			 * flash.addFlashAttribute("info", "Foto " + jugador.getFoto() + " eliminada con
			 * exito!"); }
			 **/

		}
		return "redirect:/listar";
	}

}
