package com.example.manifest_me.Model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Table(name = "resources")
public class Resource {

    public enum Category {
        RESUME, COVER_LETTER, JOB_BOARD, INTERVIEW_PREP, NETWORKING, SALARY, LEARNING
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    // Columns
    @Column(nullable = false, length = 200)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, length = 500)
    private String url;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private Category category;

    @Column(name = "display_order", nullable = false)
    private int displayOrder = 0;

    @Column(nullable = false)
    private boolean published = false;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;


    public Resource() {}


    // Accessors
    public Long getId() {return id;}

    public String getTitle() {return title;}

    public void setTitle(String title) {this.title = title;}

    public String getDescription() {return description;}

    public void setDescription(String description) {this.description = description;}

    public String getUrl() {return url;}

    public void setUrl(String url) {this.url = url;}

    public Category getCategory() {return category;}

    public void setCategory(Category category) {this.category = category;}

    public int getDisplayOrder() {return displayOrder;}

    public void setDisplayOrder(int displayOrder) {this.displayOrder = displayOrder;}

    public boolean isPublished() {return published;}

    public void setPublished(boolean published) {this.published = published;}

    public Instant getCreatedAt() {return createdAt;}
}