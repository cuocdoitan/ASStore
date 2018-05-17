/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SB;

import Models.Feedback;
import Models.FeedbackComment;
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
public class FeedbackCommentFacade extends AbstractFacade<FeedbackComment> implements FeedbackCommentFacadeLocal {

    @PersistenceContext(unitName = "ASStore-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FeedbackCommentFacade() {
        super(FeedbackComment.class);
    }

    @Override
    public List<FeedbackComment> getList() {
        TypedQuery query = em.createNamedQuery("FeedbackComment.findByEnabled", Feedback.class);
        query.setParameter("enabled", true);
        return query.getResultList();
    }
    
}
