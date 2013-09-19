package com.stf.persistence.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.stf.exception.NotDeleteException;
import com.stf.exception.NotFoundException;
import com.stf.exception.NotSaveException;

/**
 * @author xavier
 * 
 * @param <T>
 * @param <PK>
 */
@SuppressWarnings("unchecked")
public abstract class GenericDao<T, PK extends Serializable> {

	private Class<T> type;

	@PersistenceContext(unitName = "stfPU")
	protected EntityManager entityManager;

	/**
	 * Contructor de GenericDaoImpl
	 * 
	 * @param type
	 */
	public GenericDao(final Class<T> type) {
		this.type = type;
	}

	/*
	 * Crea una entidad
	 * 
	 * @see
	 * ec.com.webcreativa.escolar.persistencia.dao.GenericDao#create(java.lang
	 * .Object)
	 */
	public T create(final T o) throws NotSaveException {
		try {
			getEntityManager().persist(o);
			getEntityManager().flush();
			getEntityManager().refresh(o);
			return o;
		} catch (Exception e) {
			throw new NotSaveException("Sorry, 'Save' was not succesfull", e);
		}
	}

	/*
	 * Actualiza una entidad
	 * 
	 * @see
	 * ec.com.webcreativa.escolar.persistencia.dao.GenericDao#update(java.lang
	 * .Object)
	 */
	public T update(final T o) throws NotSaveException {
		try {
			getEntityManager().merge(o);
			return o;
		} catch (Exception e) {
			throw new NotSaveException("Sorry, 'Save' was not succesfull", e);
		}
	}

	/*
	 * Elimina una entidad
	 * 
	 * @see
	 * ec.com.webcreativa.escolar.persistencia.dao.GenericDao#delete(java.lang
	 * .Object)
	 */
	public void delete(T o) throws NotDeleteException {
		try {
			o = getEntityManager().merge(o);
			getEntityManager().remove(o);

		} catch (Exception e) {
			throw new NotDeleteException("Sorry, 'Delete' was not succesfull"
					+ e);
		}
	}

	/*
	 * Elimina una entidad por PK
	 * 
	 * @see
	 * ec.com.webcreativa.escolar.persistencia.dao.GenericDao#deleteByPK(java
	 * .io.Serializable)
	 */
	public void deleteByPK(final PK pk) throws NotDeleteException {
		try {
			// getEntityManager().merge(o);
			Object ref = getEntityManager().getReference(type, pk);
			getEntityManager().remove(ref);

		} catch (Exception e) {
			throw new NotDeleteException("Sorry, 'Delete' was not succesfull"
					+ e);
		}
	}

	/*
	 * Actualiza la entidad
	 * 
	 * @see
	 * ec.com.webcreativa.escolar.persistencia.dao.GenericDao#refresh(java.lang
	 * .Object)
	 */
	public void refresh(final T o) {
		getEntityManager().refresh(o);
	}

	/*
	 * Busca una entidad por PK
	 * 
	 * @see
	 * ec.com.webcreativa.escolar.persistencia.dao.GenericDao#findById(java.
	 * io.Serializable)
	 */
	public T findById(final PK id) throws NotFoundException {

		try {
			return getEntityManager().find(type, id);
		} catch (Exception e) {
			throw new NotFoundException();
		}
	}

	/*
	 * Busca todas las entidades, permite ordenar la busqueda
	 * 
	 * @see
	 * ec.com.webcreativa.escolar.persistencia.dao.GenericDao#findAll(java.lang
	 * .String[])
	 */
	public List<T> findAll(final String... order) throws NotFoundException {
		try {
			StringBuffer ordered = new StringBuffer("");
			ordered.append("from ").append(type.getSimpleName());
			if (order != null && order.length != 0) {
				ordered.append(" ORDER BY ");
				for (int i = 0; i < order.length; i++) {
					if (i < order.length - 1) {
						ordered.append(order[i]).append(", ");
					} else {
						ordered.append(order[i]);
					}
				}
			}

			return getEntityManager().createQuery(ordered.toString())
					.getResultList();
		} catch (Exception e) {
			throw new NotFoundException("No results found" + e);
		}
	}

	/*
	 * Busca por NamedQuery
	 * 
	 * @see
	 * ec.com.webcreativa.escolar.persistencia.dao.GenericDao#findByNamedQuery
	 * (java.lang.String)
	 */
	public List<T> findByNamedQuery(String namedQueryName)
			throws NotFoundException {
		try {
			return getEntityManager().createNamedQuery(namedQueryName)
					.getResultList();
		} catch (Exception e) {
			throw new NotFoundException("No results found" + e);
		}
	}

	/*
	 * Busca por NamedQuery
	 * 
	 * @see
	 * ec.com.webcreativa.escolar.persistencia.dao.GenericDao#findByNamedQuery
	 * (java.lang.String, java.util.Map)
	 */
	public List<T> findByNamedQuery(String namedQueryName,
			Map<String, Object> parameters) throws NotFoundException {
		try {
			return findByNamedQuery(namedQueryName, parameters, 0);
		} catch (Exception e) {
			throw new NotFoundException("No results found" + e);
		}
	}

	/**
	 * @param namedQueryName
	 * @param parameters
	 * @return
	 * @throws NotFoundException
	 */
	public List<Object[]> findByNamedQueryObjectArray(String namedQueryName,
			Map<String, Object> parameters) throws NotFoundException {
		try {
			return findByNamedQueryObjectArray(namedQueryName, parameters, 0);
		} catch (Exception e) {
			throw new NotFoundException("No results found" + e);
		}
	}

	/**
	 * @param namedQueryName
	 * @param parameters
	 * @return
	 * @throws NotFoundException
	 */
	public List<Object> findByNamedQueryObject(String namedQueryName,
			Map<String, Object> parameters) throws NotFoundException {
		try {
			return findByNamedQueryObject(namedQueryName, parameters, 0);
		} catch (Exception e) {
			throw new NotFoundException("No results found" + e);
		}
	}

	/*
	 * Busca por NamedQuery
	 * 
	 * @see
	 * ec.com.webcreativa.escolar.persistencia.dao.GenericDao#findByNamedQuery
	 * (java.lang.String, int)
	 */
	public List<T> findByNamedQuery(String queryName, int resultLimit)
			throws NotFoundException {
		try {
			return getEntityManager().createNamedQuery(queryName)
					.setMaxResults(resultLimit).getResultList();
		} catch (Exception e) {
			throw new NotFoundException("No results found" + e);
		}
	}

	/*
	 * (Busca por NamedQuery
	 * 
	 * @see
	 * ec.com.webcreativa.escolar.persistencia.dao.GenericDao#findByNamedQuery
	 * (java.lang.String, java.util.Map, int)
	 */
	public List<T> findByNamedQuery(String namedQueryName,
			Map<String, Object> parameters, int resultLimit)
			throws NotFoundException {
		try {
			Set<Entry<String, Object>> rawParameters = parameters.entrySet();
			Query query = getEntityManager().createNamedQuery(namedQueryName);
			if (resultLimit > 0)
				query.setMaxResults(resultLimit);
			for (Entry<String, Object> entry : rawParameters) {
				query.setParameter(entry.getKey(), entry.getValue());
			}
			return query.getResultList();
		} catch (Exception e) {
			throw new NotFoundException("No results found" + e);
		}
	}

	/**
	 * @param namedQueryName
	 * @param parameters
	 * @param resultLimit
	 * @return
	 * @throws NotFoundException
	 */
	public List<Object[]> findByNamedQueryObjectArray(String namedQueryName,
			Map<String, Object> parameters, int resultLimit)
			throws NotFoundException {
		try {
			Set<Entry<String, Object>> rawParameters = parameters.entrySet();
			Query query = getEntityManager().createNamedQuery(namedQueryName);
			if (resultLimit > 0)
				query.setMaxResults(resultLimit);
			for (Entry<String, Object> entry : rawParameters) {
				query.setParameter(entry.getKey(), entry.getValue());
			}
			return query.getResultList();
		} catch (Exception e) {
			throw new NotFoundException("No results found" + e);
		}
	}

	/**
	 * @param namedQueryName
	 * @param parameters
	 * @param resultLimit
	 * @return
	 * @throws NotFoundException
	 */
	public List<Object> findByNamedQueryObject(String namedQueryName,
			Map<String, Object> parameters, int resultLimit)
			throws NotFoundException {
		try {
			Set<Entry<String, Object>> rawParameters = parameters.entrySet();
			Query query = getEntityManager().createNamedQuery(namedQueryName);
			if (resultLimit > 0)
				query.setMaxResults(resultLimit);
			for (Entry<String, Object> entry : rawParameters) {
				query.setParameter(entry.getKey(), entry.getValue());
			}
			return query.getResultList();
		} catch (Exception e) {
			throw new NotFoundException("No results found" + e);
		}
	}

	/**
	 * @return
	 */
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	/*
	 * Busca por NativeQuery
	 * 
	 * @see
	 * ec.com.webcreativa.escolar.persistencia.dao.GenericDao#findByNativeQuery
	 * (java.lang.String)
	 */
	public List<T> findByNativeQuery(String sql) {
		return getEntityManager().createNativeQuery(sql, type).getResultList();
	}
}