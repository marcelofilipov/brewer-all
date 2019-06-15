package com.thefilipov.brewer.repository.helper.cerveja;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.thefilipov.brewer.dto.CervejaDTO;
import com.thefilipov.brewer.dto.ValorItensEstoqueDTO;
import com.thefilipov.brewer.model.Cerveja;
import com.thefilipov.brewer.repository.filter.CervejaFilter;
import com.thefilipov.brewer.repository.paginacao.PaginacaoUtil;
import com.thefilipov.brewer.storage.FotoStorage;

public class CervejasImpl implements CervejasQueries {

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginacaoUtil paginacaoUtil;
	
	@Autowired
	private FotoStorage fotoStorage;
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<Cerveja> filtrar(CervejaFilter filtro, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Cerveja.class);
		
		paginacaoUtil.preparar(criteria, pageable);
		adicionarFiltro(filtro, criteria);
		
		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}
	
	@Override
	public ValorItensEstoqueDTO valorItensEstoque() {
		String query = "select new com.thefilipov.brewer.dto.ValorItensEstoqueDTO(sum(preco * qtdeEstoque), sum(qtdeEstoque)) from Cerveja";
		return manager.createQuery(query, ValorItensEstoqueDTO.class).getSingleResult();
	}

	private Long total(CervejaFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Cerveja.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}
	
	private void adicionarFiltro(CervejaFilter filtro, Criteria criteria) {
		if (filtro != null) {
			if (!StringUtils.isEmpty(filtro.getSku())) {
				criteria.add(Restrictions.eq("sku", filtro.getSku()));
			}
			
			if (!StringUtils.isEmpty(filtro.getNome())) {
				criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
			}

			if (isEstiloPresente(filtro)) {
				criteria.add(Restrictions.eq("estilo", filtro.getEstilo()));
			}

			if (filtro.getSabor() != null) {
				criteria.add(Restrictions.eq("sabor", filtro.getSabor()));
			}

			if (filtro.getOrigem() != null) {
				criteria.add(Restrictions.eq("origem", filtro.getOrigem()));
			}

			if (filtro.getPrecoDe() != null) {
				criteria.add(Restrictions.ge("preco", filtro.getPrecoDe()));
			}

			if (filtro.getPrecoAte() != null) {
				criteria.add(Restrictions.le("preco", filtro.getPrecoAte()));
			}
		}
	}
		
	private boolean isEstiloPresente(CervejaFilter filtro) {
		return filtro.getEstilo() != null && filtro.getEstilo().getCodigo() != null;
	}

	@Override
	public List<CervejaDTO> porSkuOuNome(String skuOuNome) {
		String jpql = "select new com.thefilipov.brewer.dto.CervejaDTO(codigo, sku, nome, origem, preco, foto) "
				+ "from Cerveja where lower(sku) like lower(:skuOuNome) or lower(nome) like lower(:skuOuNome)";
		List<CervejaDTO> cervejasFiltradas = manager.createQuery(jpql, CervejaDTO.class)
					.setParameter("skuOuNome", skuOuNome + "%")
					.getResultList();
		cervejasFiltradas.forEach(c -> c.setUrlThumbnailFoto(fotoStorage.getUrl(FotoStorage.THUMBNAIL_PREFIX + c.getFoto())));
		return cervejasFiltradas;
	}
}
