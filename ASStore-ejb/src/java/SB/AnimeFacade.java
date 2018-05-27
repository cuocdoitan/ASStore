/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SB;

import Models.Anime;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Tien Phat
 */
@Stateless
public class AnimeFacade extends AbstractFacade<Anime> implements AnimeFacadeLocal {

    @PersistenceContext(unitName = "ASStore-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AnimeFacade() {
        super(Anime.class);
    }
    
    @Override
    public List<Anime> getAll() {
        return findAll();
    }
    
    @Override
    public Anime findAnimeByName(String name){
        TypedQuery query = em.createQuery("SELECT a FROM Anime a WHERE a.name = ?1 and a.enabled = ?2", Anime.class);
        query.setParameter(1, name.trim());
        query.setParameter(2, true);
        List<Anime> list = query.getResultList();
        if(list.isEmpty()){
            return null;
        }else{
            return list.get(0);
        }
    }
    
    public List<Anime> findAllAvailableAnime(){
        TypedQuery query = em.createQuery("SELECT a FROM Anime a WHERE a.enabled = ?1", Anime.class);
        query.setParameter(1, true);
        return query.getResultList();
    }
}
