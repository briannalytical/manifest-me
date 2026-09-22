package com.example.manifest_me.Model;

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

    public enum Source { SELF, RECRUITER }

    public enum Status { APPLIED, SCREENING, INTERVIEWING, OFFER, REJECTED }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser user;


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

    public enum WorkArrangement { REMOTE, HYBRID, ONSITE }

    @ElementCollection
    @CollectionTable(
            name = "application_work_arrangements",
            joinColumns = @JoinColumn(name = "application_id")
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "arrangement", nullable = false, length = 20)
    private Set<WorkArrangement> workArrangements = new HashSet<>();

    @ElementCollection
    @CollectionTable(
            name = "application_locations",
            joinColumns = @JoinColumn(name = "application_id")
    )
    @Column(name = "location", nullable = false, length = 200)
    private Set<String> locations = new HashSet<>();

    @Column(name = "salary_range", length = 100)
    private String salaryRange;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @ManyToMany
    @JoinTable(
            name = "application_contacts",
            joinColumns = @JoinColumn(name = "application_id"),
            inverseJoinColumns = @JoinColumn(name = "contact_id")
    )
    private Set<Contact> contacts = new HashSet<>();

    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("scheduledAt ASC")
    private List<Interview> interviews = new ArrayList<>();

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;


    // Accessors
    public Set<WorkArrangement> getWorkArrangements() { return workArrangements; }

    public void setWorkArrangements(Set<WorkArrangement> workArrangements) {this.workArrangements = workArrangements;}

    public Set<String> getLocations() {return locations;}

    public void setLocations(Set<String> locations) {this.locations = locations;}
}