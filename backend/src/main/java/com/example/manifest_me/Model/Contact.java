package com.example.manifest_me.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;



@Entity
@Table(name = "contacts")
@Getter
@Setter
@NoArgsConstructor
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Join Conditions
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser user;


    // Columns
    @Column(nullable = false, length = 200)
    private String name;

    @Column(length = 200)
    private String role;

    @Column(name = "company_name", length = 200)
    private String companyName;

    @Column(length = 254, nullable = true)
    private String email;

    @Column(length = 50, nullable = true)
    private String phone;

    @Column(name = "is_recruiter", nullable = false)
    private boolean recruiter = false;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @ManyToMany(mappedBy = "contacts")
    private Set<com.example.manifest_me.model.Entry> applications = new HashSet<>();

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;
}