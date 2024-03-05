package com.example.VaccinationBookingSystem.Model;

import com.example.VaccinationBookingSystem.Enum.DoseNo;
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
@Table(name = "appointment_table")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "appointment_no")
    String appointmentNo;

    @CreationTimestamp
    Date dateOfAppointment;

    @Enumerated(EnumType.STRING)
    DoseNo doseNo;

    @ManyToOne
    @JoinColumn
    User user;

    @ManyToOne
    @JoinColumn
    Doctor doctor;


}

