package com.thefilipov.brewer.mail;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.thefilipov.brewer.model.Cerveja;
import com.thefilipov.brewer.model.ItemVenda;
import com.thefilipov.brewer.model.Venda;
import com.thefilipov.brewer.storage.FotoStorage;

@Component
public class Mailer {
	
	private static final Logger logger = LoggerFactory.getLogger(Mailer.class);

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private TemplateEngine thymeleaf;
	
	@Autowired
	private FotoStorage fotoStorage;
	
	@Async
	public void enviar(Venda venda) {
		Context context = new Context(new Locale("pt","BR"));
		context.setVariable("venda", venda);
		context.setVariable("logo", "logo");
		
		Map<String, String> fotos = new HashMap<>();
		boolean adicionarMockCerveja = false;
		for(ItemVenda item : venda.getItens()) {
			Cerveja cerveja = item.getCerveja();
			if (cerveja.temFoto()) {
				String cid = "foto-" + cerveja.getCodigo();
				context.setVariable(cid, cid);
				
				fotos.put(cid, cerveja.getFoto() + "|" + cerveja.getContentType());
			} else {
				adicionarMockCerveja = true;
				context.setVariable("mockCerveja", "mockCerveja");
			}
			String cid = "foto-" + cerveja.getCodigo();
			context.setVariable(cid, cid);
		}
		
		try {
			String email = thymeleaf.process("mail/ResumoVenda", context);

			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper mmHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			mmHelper.setFrom("noreply.thefilipov@gmail.com");
			mmHelper.setTo(venda.getCliente().getEmail());
			mmHelper.setSubject(String.format("Brewer - Venda nº %d", venda.getCodigo()));
			mmHelper.setText(email, true);
			
			mmHelper.addInline("logo", new ClassPathResource("/static/images/logo-gray.png"));
			
			if (adicionarMockCerveja) {
				mmHelper.addInline("mockCerveja", new ClassPathResource("static/images/cerveja-mock.png"));
			}
			
			for(String cid : fotos.keySet()) {
				String[] fotoContentType = fotos.get(cid).split("\\|");
				String foto = fotoContentType[0];
				String contentType = fotoContentType[1];
				byte[] arrayFoto = fotoStorage.recuperarThumbnail(foto);
				mmHelper.addInline(cid, new ByteArrayResource(arrayFoto), contentType);
			}
					
			mailSender.send(mimeMessage);
		} catch (MessagingException e) {
			logger.error("Erro enviando e-mail", e);
		}
		
	}
}
