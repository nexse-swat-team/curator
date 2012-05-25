package com.nexse.swat.curator.persistence.domain;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Configurable
@Entity
public class EnrichedData {
    @Version
    @Column(name = "version")
    private Integer version;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @OneToOne
    private ChannelData channelData;

    @PersistenceContext
    transient EntityManager entityManager;

    public static final EntityManager entityManager() {
        EntityManager em = new EnrichedData().entityManager;
        if (em == null)
            throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

    public static long countEnrichedData() {
        return entityManager().createQuery("SELECT COUNT(o) FROM EnrichedData o", Long.class).getSingleResult();
    }

    public static List<EnrichedData> findEnrichedDatatEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM EnrichedData o", EnrichedData.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    public static EnrichedData findEnrichedData(Long id) {
        if (id == null) return null;
        return entityManager().find(EnrichedData.class, id);
    }

    public static List<EnrichedData> findAllEnrichedData() {
        return entityManager().createQuery("SELECT o FROM EnrichedData o", EnrichedData.class).getResultList();
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getVersion() {
        return this.version;
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
            EnrichedData attached = EnrichedData.findEnrichedData(this.id);
            this.entityManager.remove(attached);
        }
    }

    @Transactional
    public EnrichedData merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        EnrichedData merged = this.entityManager.merge(this);
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
        if (!(o instanceof EnrichedData)) return false;

        EnrichedData EnrichedData = (EnrichedData) o;

        if (id != null ? !id.equals(EnrichedData.id) : EnrichedData.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
