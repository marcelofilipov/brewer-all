package com.thefilipov.brewer.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thefilipov.brewer.controller.page.PageWrapper;
import com.thefilipov.brewer.model.Estilo;
import com.thefilipov.brewer.repository.Estilos;
import com.thefilipov.brewer.repository.filter.EstiloFilter;
import com.thefilipov.brewer.service.EstilosService;
import com.thefilipov.brewer.service.exception.ImpossivelExcluirEntidadeException;
import com.thefilipov.brewer.service.exception.NomeEstiloJaCadastradoException;
import com.thefilipov.brewer.util.BrewerConstants;

@Controller
@RequestMapping("/estilos")
public class EstilosController {
	
	@Autowired
	private EstilosService estilosService;
	
	@Autowired
	private Estilos estilos;
	
	private static final Logger LOG = LoggerFactory.getLogger(EstilosController.class);

	@RequestMapping("/novo")
	public ModelAndView novo(Estilo estilo) {
		if(LOG.isDebugEnabled()) {
			LOG.debug("on the line " + Thread.currentThread().getStackTrace()[1].getLineNumber());
		}
		
		return new ModelAndView("estilo/CadastroEstilo");
	}

	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Estilo estilo, BindingResult result, Model model, RedirectAttributes attributes) {
		if(LOG.isDebugEnabled()) {
			LOG.debug("on the line " + Thread.currentThread().getStackTrace()[1].getLineNumber());
		}
		
		if (result.hasErrors()) {
			return novo(estilo);
		}

		try {
			estilosService.salvar(estilo);
		} catch(NomeEstiloJaCadastradoException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novo(estilo);
		}
		
		attributes.addFlashAttribute("mensagem", "Estilo salvo com sucesso!");
				
		return new ModelAndView("redirect:/estilos/novo");
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<?> salvar(@RequestBody @Valid Estilo estilo, BindingResult result) {
		if(LOG.isDebugEnabled()) {
			LOG.debug("on the line " + Thread.currentThread().getStackTrace()[1].getLineNumber());
		}

		if(result.hasErrors()) {
			return ResponseEntity.badRequest().body(result.getFieldError("nome").getDefaultMessage());
		}
		
		estilo = estilosService.salvar(estilo);
		
		return ResponseEntity.ok(estilo	);
	}
	
	@GetMapping
	public ModelAndView pesquisar(EstiloFilter estiloFilter, BindingResult result, 
			@PageableDefault(size = 5) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("estilo/PesquisaEstilos");
		
		PageWrapper<Estilo> paginaWrapper = new PageWrapper<>(estilos.filtrar(estiloFilter, pageable)
				, httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		
		return mv;
	}

	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Estilo estilo) {
		if(LOG.isDebugEnabled()) {
			LOG.debug("on the line " + Thread.currentThread().getStackTrace()[1].getLineNumber());
		}

		try {
			estilosService.excluir(estilo); 
		} catch(ImpossivelExcluirEntidadeException e) {
			LOG.warn(BrewerConstants.MENSAGEM_LOG_ATENCAO + e.getMessage());
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		return ResponseEntity.ok().build();
	}

	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable Long codigo) {		
		if(LOG.isDebugEnabled()) {
			LOG.debug("on the line " + Thread.currentThread().getStackTrace()[1].getLineNumber());
		}

		Estilo estilo = estilos.findByCodigo(codigo);
		ModelAndView mv = novo(estilo);
		mv.addObject(estilo);
		
		return mv;
	}
	
}
