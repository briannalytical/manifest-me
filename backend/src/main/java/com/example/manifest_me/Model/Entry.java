package com.example.manifest_me.model;

import com.example.manifest_me.Model.AppUser;
import com.example.manifest_me.Model.Contact;
import com.example.manifest_me.Model.Interview;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "entries")
@Getter
@Setter
@NoArgsConstructor
public class Entry {

    // Enums
    public enum Source { SELF, RECRUITER }

    public enum Status { APPLIED, SCREENING, INTERVIEWING, OFFER, REJECTED, GHOSTED, WITHDRAWN }

    public enum WorkArrangement { REMOTE, HYBRID, ONSITE }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    // Join Conditions
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser user;

    @ManyToMany
    @JoinTable(
            name = "entry_contacts",
            joinColumns = @JoinColumn(name = "entry_id"),
            inverseJoinColumns = @JoinColumn(name = "contact_id")
    )
    private Set<Contact> contacts = new HashSet<>();

    @OneToMany(mappedBy = "entry", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("roundNumber ASC")
    private List<Interview> interviews = new ArrayList<>();


    // Columns
    @Column(name = "company_name", nullable = false, length = 200)
    private String companyName;

    @Column(name = "position_title", nullable = false, length = 200)
    private String positionTitle;

    @Enumerated(EnumType.STRING)
    @Column(name = "source_type", nullable = false, length = 20)
    private Source sourceType = Source.SELF;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Status status = Status.APPLIED;

    @Column(name = "date_applied", nullable = false)
    private LocalDate dateApplied;

    @Column(name = "job_url", length = 500)
    private String jobUrl;

    @ElementCollection
    @CollectionTable(
            name = "entry_work_arrangements",
            joinColumns = @JoinColumn(name = "entry_id")
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "arrangement", nullable = false, length = 20)
    private Set<WorkArrangement> workArrangements = new HashSet<>();

    @ElementCollection
    @CollectionTable(
            name = "entry_locations",
            joinColumns = @JoinColumn(name = "entry_id")
    )
    @Column(name = "location", nullable = false, length = 200)
    private Set<String> locations = new HashSet<>();

    @Column(name = "salary_range", length = 100)
    private String salaryRange;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;
}