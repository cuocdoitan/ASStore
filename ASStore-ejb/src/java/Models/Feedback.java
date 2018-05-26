/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.io.Serializable;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Tien Phat
 */
@Entity
@Table(name = "Feedback")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Feedback.findAll", query = "SELECT f FROM Feedback f")
    , @NamedQuery(name = "Feedback.findById", query = "SELECT f FROM Feedback f WHERE f.id = :id")
    , @NamedQuery(name = "Feedback.findByTitle", query = "SELECT f FROM Feedback f WHERE f.title = :title")
    , @NamedQuery(name = "Feedback.findByContents", query = "SELECT f FROM Feedback f WHERE f.contents = :contents")
    , @NamedQuery(name = "Feedback.findByCreateAt", query = "SELECT f FROM Feedback f WHERE f.createAt = :createAt")
    , @NamedQuery(name = "Feedback.findByUpdateAt", query = "SELECT f FROM Feedback f WHERE f.updateAt = :updateAt")
    , @NamedQuery(name = "Feedback.findByEnabled", query = "SELECT f FROM Feedback f WHERE f.enabled = :enabled ORDER BY f.createAt DESC")})
public class Feedback implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Title")
    private String title;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "feedbackId")
    private Collection<FeedbackComment> feedbackCommentCollection;
    @JoinColumn(name = "UsersId", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Users usersId;

    public Feedback() {
    }

    public Feedback(Integer id) {
        this.id = id;
    }

    public Feedback(Integer id, String title, String contents, Date createAt, boolean enabled) {
        this.id = id;
        this.title = title;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getCreateAt() {
        Format formatter = new SimpleDateFormat("dd / MM / yyyy");
        return formatter.format(createAt);
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

    @XmlTransient
    public Collection<FeedbackComment> getFeedbackCommentCollection() {
        return feedbackCommentCollection;
    }

    public void setFeedbackCommentCollection(Collection<FeedbackComment> feedbackCommentCollection) {
        this.feedbackCommentCollection = feedbackCommentCollection;
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
        if (!(object instanceof Feedback)) {
            return false;
        }
        Feedback other = (Feedback) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Models.Feedback[ id=" + id + " ]";
    }

}
