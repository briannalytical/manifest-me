package com.example.manifest_me.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(
        name = "interviews",
        uniqueConstraints = @UniqueConstraint(columnNames = {"application_id", "round_number"})
)
@Getter
@Setter
@NoArgsConstructor
public class Interview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Join Conditions
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "entry_id", nullable = false)
    private com.example.manifest_me.model.Entry entry;


    // Columns
    @Column(name = "round_name", nullable = false, length = 100)
    private String roundName;

    @Column(name = "scheduled_at", nullable = false)
    private Instant scheduledAt;

    @Column(length = 200)
    private String interviewer;

    @Column(length = 200)
    private String location;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @Column(nullable = false)
    private boolean completed = false;

    @Column(name = "round_number", nullable = false)
    private int roundNumber = 1;

    @Column(name = "completed_at")
    private Instant completedAt;

    @Column(name = "follow_up_sent_at")
    private Instant followUpSentAt;


    // Accessors
    public int getRoundNumber() {return roundNumber;}

    public void setRoundNumber(int roundNumber) {this.roundNumber = roundNumber;}

    public Instant getCompletedAt() {return completedAt;}

    public void setCompletedAt(Instant completedAt) {this.completedAt = completedAt;}

    public Instant getFollowUpSentAt() {return followUpSentAt;}

    public void setFollowUpSentAt(Instant followUpSentAt) {this.followUpSentAt = followUpSentAt;}
}