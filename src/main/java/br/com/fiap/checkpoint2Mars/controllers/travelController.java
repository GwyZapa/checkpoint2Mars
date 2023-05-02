package br.com.fiap.checkpoint2Mars.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.fiap.checkpoint2Mars.models.Travels;
import br.com.fiap.checkpoint2Mars.repositories.travelRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/viagem")
public class travelController {
	@Autowired
	private travelRepository travelRepository;
	
	@GetMapping("")
	public ModelAndView get() {
		ModelAndView model = new ModelAndView("viagem/index");
		List<Travels> listaViagem = travelRepository.findAll();
		model.addObject("viagens", listaViagem);
		return model;
	}
	
	@GetMapping("/create")
	public ModelAndView create() {
		ModelAndView model = new ModelAndView("viagem/create");
		return model;
	}
	
	@PostMapping("/create")
	public ResponseEntity<Travels> createForm(@Valid @RequestBody Travels objViagem ){ 
	
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");	
		LocalDate dataPartida = LocalDate.parse(objViagem.getDataDecolagem(),fmt);
	    LocalDate dataRetorno = dataPartida.plusDays(520 + Integer.parseInt((objViagem.getDiasEstadia())));
	    objViagem.setDataDecolagem(dataPartida.format(fmt));
	    objViagem.setDataRetorno(dataRetorno.format(fmt));
	    travelRepository.save(objViagem);
		return new ResponseEntity<Travels>(objViagem, HttpStatus.CREATED);
	}
	
	@GetMapping("/edit/{id}")
	public String getById(Model model, @PathVariable("id")Long idViagem) {
		Optional<Travels> optionalViagem = travelRepository.findById(idViagem);
		if(optionalViagem.isPresent()) {
			Travels viagem = optionalViagem.get();
			model.addAttribute("viagem", viagem);
			return "viagem/edit";
		} else {
			return "/viagem";
		}
	}
	
	@PutMapping("/edit")
	public ResponseEntity<Travels> updateForm(@Valid @RequestBody Travels objViagem ){
	
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");	
		LocalDate dataPartida = LocalDate.parse(objViagem.getDataDecolagem(),fmt);
		LocalDate dataRetorno = dataPartida.plusDays(520 + Integer.parseInt((objViagem.getDiasEstadia())));
	    objViagem.setDataDecolagem(dataPartida.format(fmt));
	    objViagem.setDataRetorno(dataRetorno.format(fmt));
		travelRepository.save(objViagem);
		return new ResponseEntity<Travels>(objViagem, HttpStatus.OK);
	}
		
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id")Long id) {
		travelRepository.deleteById(id);
		return "redirect:/viagem";
	}

}