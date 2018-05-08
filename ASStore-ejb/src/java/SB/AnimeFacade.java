/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SB;

import Models.Anime;
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
    
    public Anime findAnimeByName(String name){
        TypedQuery query = em.createNamedQuery("Anime.findByName", Anime.class);
        query.setParameter("name", name);
        return (Anime) query.getSingleResult();
    }
}
