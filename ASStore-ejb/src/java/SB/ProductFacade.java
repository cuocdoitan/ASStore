/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SB;

import Models.Product;
import Models.Users;
import java.util.ArrayList;
import java.util.Date;
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
public class ProductFacade extends AbstractFacade<Product> implements ProductFacadeLocal {

    @PersistenceContext(unitName = "ASStore-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductFacade() {
        super(Product.class);
    }

    @Override
    public List<Product> getListApprovingProduct() {
        List<Product> listApprovinProduct = new ArrayList<>();
        for (Product product : this.findAll()) {
            if (product.getStatus() == 0) {
                listApprovinProduct.add(product);
            }
        }
        return listApprovinProduct;
    }

    @Override
    public List<Product> getProductbyCategory(int proCateId) {
        TypedQuery query = em.createQuery("SELECT p FROM Product p WHERE p.categoryId.id = ?1 and p.enabled = ?2 and p.status = ?3", Product.class);
        query.setParameter(1, proCateId);
        query.setParameter(2, true);
        query.setParameter(3, 1);
        List<Product> list = query.getResultList();
        return list;
    }

    @Override
    public List<Product> getProductbyCate(int proCateId) {
        TypedQuery query = em.createQuery("SELECT p FROM Product p WHERE p.categoryId.id = ?1 and p.enabled = ?2", Product.class);
        query.setParameter(1, proCateId);
        query.setParameter(2, true);
        List<Product> list = query.getResultList();
        if(list.isEmpty()){
            return null;
        }
        else{
            return list;
        }
        
    }

}
