/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Tien Phat
 */
@Entity
@Table(name = "FeedbackComment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FeedbackComment.findAll", query = "SELECT f FROM FeedbackComment f")
    , @NamedQuery(name = "FeedbackComment.findById", query = "SELECT f FROM FeedbackComment f WHERE f.id = :id")
    , @NamedQuery(name = "FeedbackComment.findByContents", query = "SELECT f FROM FeedbackComment f WHERE f.contents = :contents")
    , @NamedQuery(name = "FeedbackComment.findByCreateAt", query = "SELECT f FROM FeedbackComment f WHERE f.createAt = :createAt")
    , @NamedQuery(name = "FeedbackComment.findByUpdateAt", query = "SELECT f FROM FeedbackComment f WHERE f.updateAt = :updateAt")
    , @NamedQuery(name = "FeedbackComment.findByEnabled", query = "SELECT f FROM FeedbackComment f WHERE f.enabled = :enabled")})
public class FeedbackComment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "Contents")
    private String contents;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CreateAt")
    @Temporal(TemporalType.DATE)
    private Date createAt;
    @Column(name = "UpdateAt")
    @Temporal(TemporalType.DATE)
    private Date updateAt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Enabled")
    private boolean enabled;
    @JoinColumn(name = "FeedbackId", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Feedback feedbackId;
    @JoinColumn(name = "UsersId", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Users usersId;

    public FeedbackComment() {
    }

    public FeedbackComment(Integer id) {
        this.id = id;
    }

    public FeedbackComment(Integer id, String contents, Date createAt, boolean enabled) {
        this.id = id;
        this.contents = contents;
        this.createAt = createAt;
        this.enabled = enabled;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Feedback getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Feedback feedbackId) {
        this.feedbackId = feedbackId;
    }

    public Users getUsersId() {
        return usersId;
    }

    public void setUsersId(Users usersId) {
        this.usersId = usersId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FeedbackComment)) {
            return false;
        }
        FeedbackComment other = (FeedbackComment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Models.FeedbackComment[ id=" + id + " ]";
    }
    
}
