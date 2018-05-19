/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SB;

import Models.Product;
import java.util.ArrayList;
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
    public List<Models.Product> getListAvailableProduct_User(Models.Users user) {
        TypedQuery query = em.createQuery("SELECT p FROM Product p WHERE p.enabled = ?1 and p.status = ?2 and p.usersId = ?3", Models.Product.class);
        query.setParameter(1, true);
        query.setParameter(2, 1);
        query.setParameter(3, user);
        return query.getResultList();
    }

    @Override
    public List<Models.Product> getListCheckingProduct_User(Models.Users user) {
        TypedQuery query = em.createQuery("SELECT p FROM Product p WHERE p.enabled = ?1 and p.status = ?2 and p.usersId = ?3", Models.Product.class);
        query.setParameter(1, true);
        query.setParameter(2, 0);
        query.setParameter(3, user);
        return query.getResultList();
    }

    @Override
    public List<Models.Product> getListUnavailableProduct_User(Models.Users user) {
        TypedQuery query = em.createQuery("SELECT p FROM Product p WHERE p.enabled = ?1 and p.status = ?2 and p.usersId = ?3", Models.Product.class);
        query.setParameter(1, true);
        query.setParameter(2, 2);
        query.setParameter(3, user);
        return query.getResultList();

    }
}
