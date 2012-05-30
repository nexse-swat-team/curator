package com.nexse.swat.curator.persistence.domain;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Configurable
@Entity
public class ChannelData {
    @Version
    @Column(name = "version")
    private Integer version;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(unique = true)
    private Long originalId;
    private String text;
    private Date createdAt;
    private String fromUser;
    private String profileImageUrl;
    private Long toUserId;
    private Long inReplyToStatusId;
    private Long fromUserId;
    private String languageCode;
    private String source;
    private Integer retweetCount;

    public ChannelData() {
    }

    public Long getOriginalId() {
        return originalId;
    }

    public void setOriginalId(Long originalId) {
        this.originalId = originalId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public Long getToUserId() {
        return toUserId;
    }

    public void setToUserId(Long toUserId) {
        this.toUserId = toUserId;
    }

    public Long getFromUserId() {
        return fromUserId;
    }

    public void setInReplyToStatusId(Long inReplyToStatusId) {
        this.inReplyToStatusId = inReplyToStatusId;
    }

    public Long getInReplyToStatusId() {
        return inReplyToStatusId;
    }

    public void setFromUserId(Long fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setRetweetCount(Integer retweetCount) {
        this.retweetCount = retweetCount;
    }

    /**
     * The number of times this tweet has been retweeted.
     * Only available in timeline results.
     * getRetweetCount() will return null for Tweet objects returned in search results.
     */
    public Integer getRetweetCount() {
        return retweetCount;
    }



    @PersistenceContext
    transient EntityManager entityManager;

    public static final EntityManager entityManager() {
        EntityManager em = new ChannelData().entityManager;
        if (em == null)
            throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

    public static long countChannelData() {
        return entityManager().createQuery("SELECT COUNT(o) FROM ChannelData o", Long.class).getSingleResult();
    }

    public static List<ChannelData> findChannelDatatEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM ChannelData o", ChannelData.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    public static ChannelData findChannelData(Long id) {
        if (id == null) return null;
        return entityManager().find(ChannelData.class, id);
    }

    public static List<ChannelData> findAllChannelData() {
        return entityManager().createQuery("SELECT o FROM ChannelData o", ChannelData.class).getResultList();
    }

    public static List<ChannelData> findAllOrderedChannelData() {
        return entityManager().createNativeQuery("select * from CHANNEL_DATA where from_user='NexseSwatTeam' union select * from CHANNEL_DATA where from_user!='NexseSwatTeam'",ChannelData.class).getResultList();
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
            ChannelData attached = ChannelData.findChannelData(this.id);
            this.entityManager.remove(attached);
        }
    }

    @Transactional
    public ChannelData merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        ChannelData merged = this.entityManager.merge(this);
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
        if (!(o instanceof ChannelData)) return false;

        ChannelData channelData = (ChannelData) o;

        if (id != null ? !id.equals(channelData.id) : channelData.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ChannelData{" +
                "originalId=" + originalId +
                ", text='" + text + '\'' +
                ", createdAt=" + createdAt +
                ", fromUser='" + fromUser + '\'' +
                ", profileImageUrl='" + profileImageUrl + '\'' +
                ", toUserId=" + toUserId +
                ", inReplyToStatusId=" + inReplyToStatusId +
                ", fromUserId=" + fromUserId +
                ", languageCode='" + languageCode + '\'' +
                ", source='" + source + '\'' +
                ", retweetCount=" + retweetCount +
                '}';
    }
}
