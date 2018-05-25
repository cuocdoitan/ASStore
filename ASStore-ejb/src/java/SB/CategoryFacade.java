/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SB;

import Models.Category;
import Models.Feedback;
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
public class CategoryFacade extends AbstractFacade<Category> implements CategoryFacadeLocal {

    @PersistenceContext(unitName = "ASStore-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategoryFacade() {
        super(Category.class);
    }
    

    @Override
    public List<Category> getList() {
        TypedQuery query = em.createNamedQuery("Category.findByEnabled", Feedback.class);
        query.setParameter("enabled", true);
        return query.getResultList();
    }

    @Override
    public Category getCateByName(String cateName) {
        TypedQuery query = em.createQuery("SELECT c FROM Category c WHERE c.name like ?1 and c.enabled = ?2", Category.class);
        query.setParameter(1, "%"+cateName+"%");
        query.setParameter(2, true);
        List<Category> list = query.getResultList();
        System.out.println("=====================================");
        System.out.println("list = "+list.toString());
        if(list.isEmpty()){
            return null;
        }else{
            return list.get(0);
        }
    }

 

}
