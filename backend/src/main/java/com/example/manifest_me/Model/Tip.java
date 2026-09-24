package com.example.manifest_me.Model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Table(name = "tips")
public class Tip {

    public enum Category {
        APPLYING, INTERVIEWING, NEGOTIATING, NETWORKING, MINDSET, FOLLOW_UP
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    // Columns
    @Column(nullable = false, length = 200)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String body;

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

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;


    // Initialize object
    public Tip() {}


    // Accessors
    public Long getId() {return id;}

    public String getTitle() {return title;}

    public void setTitle(String title) {this.title = title;}

    public String getBody() {return body;}

    public void setBody(String body) {this.body = body;}

    public Category getCategory() {return category;}

    public void setCategory(Category category) {this.category = category;}

    public int getDisplayOrder() {return displayOrder;}

    public void setDisplayOrder(int displayOrder) {this.displayOrder = displayOrder;}

    public boolean isPublished() {return published;}

    public void setPublished(boolean published) {this.published = published;}

    public Instant getCreatedAt() {return createdAt;}

    public Instant getUpdatedAt() {return updatedAt;}
}