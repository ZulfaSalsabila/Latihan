/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package latihan.cobaUas;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import latihan.cobaUas.exceptions.NonexistentEntityException;
import latihan.cobaUas.exceptions.PreexistingEntityException;

/**
 *
 * @author ROG
 */
public class KontrakanJpaController implements Serializable {

    public KontrakanJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("latihan_cobaUas_jar_0.0.1-SNAPSHOTPU");

    public KontrakanJpaController() {
    }
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Kontrakan kontrakan) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(kontrakan);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findKontrakan(kontrakan.getId()) != null) {
                throw new PreexistingEntityException("Kontrakan " + kontrakan + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Kontrakan kontrakan) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            kontrakan = em.merge(kontrakan);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = kontrakan.getId();
                if (findKontrakan(id) == null) {
                    throw new NonexistentEntityException("The kontrakan with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Kontrakan kontrakan;
            try {
                kontrakan = em.getReference(Kontrakan.class, id);
                kontrakan.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The kontrakan with id " + id + " no longer exists.", enfe);
            }
            em.remove(kontrakan);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Kontrakan> findKontrakanEntities() {
        return findKontrakanEntities(true, -1, -1);
    }

    public List<Kontrakan> findKontrakanEntities(int maxResults, int firstResult) {
        return findKontrakanEntities(false, maxResults, firstResult);
    }

    private List<Kontrakan> findKontrakanEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Kontrakan.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Kontrakan findKontrakan(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Kontrakan.class, id);
        } finally {
            em.close();
        }
    }

    public int getKontrakanCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Kontrakan> rt = cq.from(Kontrakan.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
