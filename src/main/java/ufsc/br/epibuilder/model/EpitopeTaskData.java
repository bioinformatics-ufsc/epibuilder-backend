package ufsc.br.epibuilder.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

/**
 * Represents a single epitope prediction task containing all configuration parameters
 * and results. This entity serves as the parent container for generated epitopes.
 */
@Entity
@Table(name = "epitope_task_data")
@Getter
@Setter
public class EpitopeTaskData {
    
    /**
     * Unique identifier for the task
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * User-defined name for the task
     */
    @Column(name = "task_name")
    private String taskName;

    /**
     * Timestamp when the task was executed
     */
    @Column(name = "execution_date")
    private java.util.Date executionDate;

    /**
     * User who created this task (LAZY-loaded for performance)
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * List of epitopes generated by this task
     * Cascades all operations and removes orphaned epitopes
     */
    @OneToMany(mappedBy = "epitopeTaskData", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Epitope> epitopes = new ArrayList<>();

    /**
     * Identifier for this specific run
     */
    @Column(nullable = false)
    private String runName;

    /**
     * FASTA file containing protein sequence(s) for analysis
     * Marked as Transient as it's not persisted in the database
     */
    @Transient
    private MultipartFile fasta;

    /**
     * Type of analysis to perform (PREDICT, ANALYZE, or PREDICT_AND_ANALYZE)
     */
    @Column(nullable = false)
    private ActionType action;

    /**
     * Minimum threshold value for BepiPred prediction scores
     */
    @Column(nullable = false)
    private Double bepipredMin;

    /**
     * Maximum threshold value for BepiPred prediction scores
     */
    @Column(nullable = false)
    private Double bepipredMax;

    /**
     * Minimum allowed length for predicted epitopes (in amino acids)
     */
    @Column(nullable = false)
    private Integer minEpitopeLength;

    /**
     * Maximum allowed length for predicted epitopes (in amino acids)
     */
    @Column(nullable = false)
    private Integer maxEpitopeLength;

    /**
     * Subcellular localization filter (e.g., "Secreted", "Membrane")
     */
    @Column(nullable = false)
    private String subcell;

    /**
     * InterPro domain filter for targeted epitope prediction
     */
    @Column(nullable = false)
    private String interpro;

    /**
     * Specific epitope sequence to search for within proteins
     */
    @Column(nullable = false)
    private String epitopeSearch;
}