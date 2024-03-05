package com.example.VaccinationBookingSystem.Service.ServiceImpl;

import com.example.VaccinationBookingSystem.Dto.RequestDto.DoctorRequestDto;
import com.example.VaccinationBookingSystem.Dto.ResponseDto.DoctorResponseDto;
import com.example.VaccinationBookingSystem.Enum.Gender;
import com.example.VaccinationBookingSystem.Exception.CentreNotFoundException;
import com.example.VaccinationBookingSystem.Exception.DoctorNotFoundException;
import com.example.VaccinationBookingSystem.Model.Doctor;
import com.example.VaccinationBookingSystem.Model.VaccinationCentre;
import com.example.VaccinationBookingSystem.Repository.CentreRepository;
import com.example.VaccinationBookingSystem.Repository.DoctorRepository;
import com.example.VaccinationBookingSystem.Service.DoctorService;
import com.example.VaccinationBookingSystem.Transformer.DoctorTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    CentreRepository centreRepository;

    @Override
    public DoctorResponseDto addDoctor(DoctorRequestDto doctorRequestDto) throws CentreNotFoundException {
        Optional<VaccinationCentre> optionalCentre=centreRepository.findById(doctorRequestDto.getCentreId());
        if(!optionalCentre.isPresent()){
            throw new CentreNotFoundException("No Centre was found with provided centreId");
        }
        VaccinationCentre centre=optionalCentre.get();
        //dto to entity
        Doctor doctor= DoctorTransformer.doctorRequestDtoToDoctor(doctorRequestDto);
        doctor.setVaccinationCentre(centre);

        //add doctors to the doctors list in centre class
        centre.getDoctors().add(doctor);
        //save centre
        VaccinationCentre savedCentre=centreRepository.save(centre); //will save both centre and doctor

        //entity to dto
        DoctorResponseDto doctorResponseDto=DoctorTransformer.doctorToDoctorResponseDto(doctor);
        return doctorResponseDto;

    }

    @Override
    public List<String> getAllDoctorsWhoHaveMoreThan10Appointments() {
        List<String> doctorsWhoHaveMoreThan10Appointments=new ArrayList<>();
        List<Doctor> doctors=doctorRepository.findAll();
        for(Doctor doctor:doctors){
            if(doctor.getAppointments().size()>10){
                doctorsWhoHaveMoreThan10Appointments.add(doctor.getName());
            }
        }
        return doctorsWhoHaveMoreThan10Appointments;
    }

    @Override
    public List<String> getMaleDoctorsAgeAbove40() {
        List<String> maleDoctorsAgeAbove40=new ArrayList<>();
        List<Doctor> doctors=doctorRepository.findAll();
        for(Doctor doctor:doctors){
            if(doctor.getGender().equals(Gender.MALE) && doctor.getAge()>40){
                maleDoctorsAgeAbove40.add(doctor.getName());
            }
        }
        return maleDoctorsAgeAbove40;
    }

    @Override
    public String getRatioOfMaleAndFemaleDoctors() {
        String ratio="";
        int maleCount=0;
        int femaleCount=0;
        List<Doctor> doctors=doctorRepository.findAll();
        for(Doctor doctor:doctors){
            if(doctor.getGender().equals(Gender.MALE)){
                maleCount++;
            }
            if(doctor.getGender().equals(Gender.FEMALE)){
                femaleCount++;
            }
        }

        ratio=String.valueOf(maleCount)+":"+String.valueOf(femaleCount);
return ratio;
}

    @Override
    public DoctorResponseDto updateDoctorNameWithEmailId(String name, String emailId) throws DoctorNotFoundException {
        Doctor doctor = doctorRepository.findByEmailId(emailId);
           if(doctor==null){
            throw new DoctorNotFoundException("No Doctor was Present with provided EmailId!!");}
        doctor.setName(name);
        Doctor savedDoctor = doctorRepository.save(doctor);

        DoctorResponseDto doctorResponseDto = DoctorTransformer.doctorToDoctorResponseDto(savedDoctor);
        return doctorResponseDto;
    }

}
