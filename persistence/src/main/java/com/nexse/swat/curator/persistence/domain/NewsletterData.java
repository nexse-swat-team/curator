package com.nexse.swat.curator.persistence.domain;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Configurable
@Entity
public class NewsletterData {
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy HH:mm:ss");

    @Version
    @Column(name = "version")
    private Integer version;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(unique = true)
    private Date createdAt;
    @Column(columnDefinition="MEDIUMTEXT")
    private String body;
    @Column(unique = true)
    private String token;
    @Column(columnDefinition="TEXT")
    private String recipients;


    public NewsletterData() {
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getTime() {
        return getCreatedAt()!=null?simpleDateFormat.format(getCreatedAt()):null;
    }


    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @PersistenceContext
    transient EntityManager entityManager;

    public static final EntityManager entityManager() {
        EntityManager em = new NewsletterData().entityManager;
        if (em == null)
            throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

    public static long countNewsletter() {
        return entityManager().createQuery("SELECT COUNT(o) FROM NewsletterData o", Long.class).getSingleResult();
    }

    public static List<NewsletterData> findNewsletterEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM NewsletterData o", NewsletterData.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    public static NewsletterData findNewsletter(Long id) {
        if (id == null) return null;
        return entityManager().find(NewsletterData.class, id);
    }

    public static List<NewsletterData> findAllNewsletter() {
        return entityManager().createQuery("SELECT o FROM NewsletterData o order by o.createdAt desc", NewsletterData.class).getResultList();
    }

    public static NewsletterData findNewsletterByToken(String token) {
        return entityManager().createQuery("SELECT o FROM NewsletterData o where o.token=:token", NewsletterData.class).setParameter("token",token).getSingleResult();
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getVersion() {
        return this.version;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRecipients() {
        return recipients;
    }

    public void setRecipients(String to) {
        this.recipients = to;
    }

    @Transactional
    public void persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }

    @Transactional
    public void flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }

    @Transactional
    public void remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            NewsletterData attached = NewsletterData.findNewsletter(this.id);
            this.entityManager.remove(attached);
        }
    }

    @Transactional
    public NewsletterData merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        NewsletterData merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

    @Transactional
    public void clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NewsletterData)) return false;

        NewsletterData channelData = (NewsletterData) o;

        if (id != null ? !id.equals(channelData.id) : channelData.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "NewsletterData{" +
                "version=" + version +
                ", id=" + id +
                ", createdAt=" + createdAt +
                ", body='" + body + '\'' +
                ", token='" + token + '\'' +
                ", entityManager=" + entityManager +
                '}';
    }
}
