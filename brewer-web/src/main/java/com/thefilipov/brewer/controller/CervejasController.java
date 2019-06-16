package com.thefilipov.brewer.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thefilipov.brewer.controller.page.PageWrapper;
import com.thefilipov.brewer.dto.CervejaDTO;
import com.thefilipov.brewer.model.Cerveja;
import com.thefilipov.brewer.model.OrigemValues;
import com.thefilipov.brewer.model.SaborValues;
import com.thefilipov.brewer.repository.Cervejas;
import com.thefilipov.brewer.repository.Estilos;
import com.thefilipov.brewer.repository.filter.CervejaFilter;
import com.thefilipov.brewer.service.CervejasService;
import com.thefilipov.brewer.service.exception.ImpossivelExcluirEntidadeException;
import com.thefilipov.brewer.util.BrewerConstants;

@Controller
@RequestMapping("/cervejas")
public class CervejasController {

	@Autowired
	private Estilos estilos;
	
	@Autowired
	private CervejasService cervejasService;
	
	@Autowired
	private Cervejas cervejas;
	
	private static final Logger LOG = LoggerFactory.getLogger(CervejasController.class); 
	
	@RequestMapping("/nova")
	public ModelAndView nova(Cerveja cerveja) {
		if(LOG.isDebugEnabled()) {
			LOG.debug("on the line " + Thread.currentThread().getStackTrace()[1].getLineNumber());
		}
		
		ModelAndView mv = new ModelAndView("cerveja/CadastroCerveja");
		mv.addObject("sabores",SaborValues.values());
		mv.addObject("estilos", estilos.findAll());
		mv.addObject("origens", OrigemValues.values());
				
		return mv;
	}	
	
	@RequestMapping(value = { "/nova", "{\\d+}" }, method = RequestMethod.POST)
	public ModelAndView salvar(@Valid Cerveja cerveja, BindingResult result, Model model, RedirectAttributes attributes) {
		if(LOG.isDebugEnabled()) {
			LOG.debug("on the line " + Thread.currentThread().getStackTrace()[1].getLineNumber());
		}
		
		if (result.hasErrors()) {
// Para simular um erro 500
//			throw new RuntimeException();
			return nova(cerveja);
		}
		cervejasService.salvar(cerveja);
		attributes.addFlashAttribute("mensagem", "Cerveja salva com sucesso!");
				
		return new ModelAndView("redirect:/cervejas/nova");
	}
	
	@GetMapping
	public ModelAndView pesquisar(CervejaFilter cervejaFilter, BindingResult result, 
			@PageableDefault(size = 2) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("cerveja/PesquisaCervejas");
		mv.addObject("estilos", estilos.findAll());
		mv.addObject("sabores", SaborValues.values());
		mv.addObject("origens", OrigemValues.values());
		
		PageWrapper<Cerveja> paginaWrapper = new PageWrapper<>(cervejas.filtrar(cervejaFilter, pageable), 
				httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		
		return mv;
	}
	
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<CervejaDTO> pesquisar(String skuOuNome) {
		return cervejas.porSkuOuNome(skuOuNome);
	}

	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Cerveja cerveja) {
		if(LOG.isDebugEnabled()) {
			LOG.debug("on the line " + Thread.currentThread().getStackTrace()[1].getLineNumber());
		}
		
		try {
			cervejasService.excluir(cerveja); 
		} catch(ImpossivelExcluirEntidadeException e) {
			LOG.warn(BrewerConstants.MENSAGEM_LOG_ATENCAO + e.getMessage());
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable("codigo") Cerveja cerveja) {
		if(LOG.isDebugEnabled()) {
			LOG.debug("on the line " + Thread.currentThread().getStackTrace()[1].getLineNumber());
		}
		
		ModelAndView mv = nova(cerveja);
		mv.addObject(cerveja);
		
		return mv;
	}
	
}
