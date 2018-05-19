/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SB;

import Models.ProductRating;
import java.text.DecimalFormat;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Tien Phat
 */
@Stateless
public class ProductRatingFacade extends AbstractFacade<ProductRating> implements ProductRatingFacadeLocal {

    @PersistenceContext(unitName = "ASStore-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductRatingFacade() {
        super(ProductRating.class);
    }
}
