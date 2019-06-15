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
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thefilipov.brewer.controller.page.PageWrapper;
import com.thefilipov.brewer.model.Cliente;
import com.thefilipov.brewer.model.TipoPessoaValues;
import com.thefilipov.brewer.repository.Clientes;
import com.thefilipov.brewer.repository.Estados;
import com.thefilipov.brewer.repository.filter.ClienteFilter;
import com.thefilipov.brewer.service.ClienteService;
import com.thefilipov.brewer.service.exception.CpfCnpjClienteJaCadastradoException;
import com.thefilipov.brewer.service.exception.ImpossivelExcluirEntidadeException;
import com.thefilipov.brewer.util.BrewerConstants;

@Controller
@RequestMapping("/clientes")
public class ClientesController {

	@Autowired
	private Estados estados;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private Clientes clientes;

	private static final Logger logger = LoggerFactory.getLogger(ClientesController.class);
	
	@RequestMapping("/novo")
	public ModelAndView novo(Cliente cliente) {
		ModelAndView mv = new ModelAndView("cliente/CadastroCliente");
		mv.addObject("tiposPessoa", TipoPessoaValues.values());
		mv.addObject("estados", estados.findAll());
		
		return mv;
	}
	
	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Cliente cliente, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(cliente);
		}
		
		try {
			clienteService.salvar(cliente);
		} catch (CpfCnpjClienteJaCadastradoException e) {
			result.rejectValue("cpfOuCnpj", e.getMessage(), e.getMessage());
			return novo(cliente);
		}
		
		attributes.addFlashAttribute("mensagem", "Cliente salvo com sucesso!");
		
		return new ModelAndView("redirect:/clientes/novo");

	}
	
	@GetMapping
	public ModelAndView pesquisar(ClienteFilter clienteFilter, BindingResult result, 
			@PageableDefault(size = 3) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("cliente/PesquisaClientes");
		mv.addObject("tipoPessoa", TipoPessoaValues.values());
		
		PageWrapper<Cliente> paginaWrapper = new PageWrapper<>(clientes.filtrar(clienteFilter, pageable), 
				httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		
		return mv;
	}
	
	@RequestMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<Cliente> pesquisar(String nome) {
		validarTamanhoNome(nome);
		return clientes.findByNomeStartingWithIgnoreCase(nome);
	}

	private void validarTamanhoNome(String nome) {
		if (StringUtils.isEmpty(nome) || nome.length() < 3) {
			throw new IllegalArgumentException();
		}
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Void> tratarIllegalArgumentException(IllegalArgumentException e) {
		return ResponseEntity.badRequest().build();
	}
	
	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Cliente cliente) {
		try {
			clienteService.excluir(cliente); 
		} catch(ImpossivelExcluirEntidadeException e) {
			logger.warn(BrewerConstants.MENSAGEM_LOG_ATENCAO + e.getMessage());
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable Long codigo) {		
		Cliente cliente = clientes.findByCodigo(codigo);
		ModelAndView mv = novo(cliente);
		mv.addObject(cliente);
		
		return mv;
	}

}
