package com.example.VaccinationBookingSystem.Model;

import com.example.VaccinationBookingSystem.Enum.CentreType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "vaccination_centre_table")
public class VaccinationCentre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "name")
    String name;

    @Column(name = "location")
    String location;

    @Enumerated(EnumType.STRING)
    CentreType centreType;

    @OneToMany(mappedBy = "vaccinationCentre",cascade = CascadeType.ALL)
    List<Doctor> doctors=new ArrayList<>();


}
