package com.example.VaccinationBookingSystem.Model;

import com.example.VaccinationBookingSystem.Enum.VaccineType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "dose2_table")
public class Dose2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "dose_id")
    String doseId;

    @Enumerated(EnumType.STRING)
    VaccineType vaccineType;

    @CreationTimestamp
    Date vaccinationDate;

    @OneToOne
    @JoinColumn
    User user;
}
