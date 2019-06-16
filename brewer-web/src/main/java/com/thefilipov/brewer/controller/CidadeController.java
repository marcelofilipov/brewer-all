package com.thefilipov.brewer.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thefilipov.brewer.controller.page.PageWrapper;
import com.thefilipov.brewer.model.Cidade;
import com.thefilipov.brewer.repository.Cidades;
import com.thefilipov.brewer.repository.Estados;
import com.thefilipov.brewer.repository.filter.CidadeFilter;
import com.thefilipov.brewer.service.CidadeService;
import com.thefilipov.brewer.service.exception.ImpossivelExcluirEntidadeException;
import com.thefilipov.brewer.service.exception.NomeCidadeJaCadastradaException;
import com.thefilipov.brewer.util.BrewerConstants;

@Controller
@RequestMapping("/cidades")
public class CidadeController {

	@Autowired
	private Estados estados;
	
	@Autowired
	private Cidades cidades;
	
	@Autowired
	private CidadeService cidadeService;
	
	private static final Logger LOG = LoggerFactory.getLogger(CidadeController.class); 
	
	@RequestMapping("/nova")
	public ModelAndView nova(Cidade cidade) {
		if(LOG.isDebugEnabled()) {
			LOG.debug("on the line " + Thread.currentThread().getStackTrace()[1].getLineNumber());
		}

		ModelAndView mv = new ModelAndView("cidade/CadastroCidade");
		mv.addObject("estados", estados.findAll());
				
		return mv;
	}	
	
	/* Cache para qualquer Estado */
	// @Cacheable("cidades")
	@Cacheable(value = "cidades", key = "#codigoEstado")
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Cidade> pesquisarPorCodigoEstado(
			@RequestParam(name = "estado", defaultValue = "-1") Long codigoEstado) {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {	}
		return cidades.findByEstadoCodigo(codigoEstado);
	}
	
	@PostMapping({ "/nova", "{\\+d}" })
	/* Retirar todo o cache */
	// @CacheEvict(value = "cidades", allEntries = true)
	@CacheEvict(value = "cidades", key = "#cidade.estado.codigo", condition = "#cidade.temEstado()")
	public ModelAndView salvar(@Valid Cidade cidade, BindingResult result, RedirectAttributes attributes) {
		if(LOG.isDebugEnabled()) {
			LOG.debug("on the line " + Thread.currentThread().getStackTrace()[1].getLineNumber());
		}

		if (result.hasErrors()) {
			return nova(cidade);
		}
		
		try {
			cidadeService.salvar(cidade);
		} catch (NomeCidadeJaCadastradaException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return nova(cidade);
		}
		
		attributes.addFlashAttribute("mensagem", "Cidade salva com sucesso!");
		return new ModelAndView("redirect:/cidades/nova");
	}
	
	@GetMapping
	public ModelAndView pesquisar(CidadeFilter cidadeFilter, BindingResult result
			, @PageableDefault(size = 10) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("cidade/PesquisaCidades");
		mv.addObject("estados", estados.findAll());
		
		PageWrapper<Cidade> paginaWrapper = new PageWrapper<>(cidades.filtrar(cidadeFilter, pageable)
				, httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
	
	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Cidade cidade) {
		if(LOG.isDebugEnabled()) {
			LOG.debug("on the line " + Thread.currentThread().getStackTrace()[1].getLineNumber());
		}

		try {
			cidadeService.excluir(cidade); 
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

		Cidade cidade = cidades.buscarComEstado(codigo);
		ModelAndView mv = nova(cidade);
		mv.addObject(cidade);
		
		return mv;
	}
	
}
