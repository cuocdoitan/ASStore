/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SB;

import Models.FeedbackComment;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Tien Phat
 */
@Local
public interface FeedbackCommentFacadeLocal {

    void create(FeedbackComment feedbackComment);

    void edit(FeedbackComment feedbackComment);

    void remove(FeedbackComment feedbackComment);

    FeedbackComment find(Object id);

    List<FeedbackComment> findAll();

    List<FeedbackComment> findRange(int[] range);

    int count();
    
}
