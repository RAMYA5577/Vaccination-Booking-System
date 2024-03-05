package com.example.VaccinationBookingSystem.Service.ServiceImpl;

import com.example.VaccinationBookingSystem.Dto.RequestDto.AppointmentRequestDto;
import com.example.VaccinationBookingSystem.Dto.ResponseDto.AppointmentResponseDto;
import com.example.VaccinationBookingSystem.Dto.ResponseDto.CentreResponseDto;
import com.example.VaccinationBookingSystem.Enum.DoseNo;
import com.example.VaccinationBookingSystem.Exception.DoctorNotFoundException;
import com.example.VaccinationBookingSystem.Exception.NotEligibleForDoseException;
import com.example.VaccinationBookingSystem.Exception.UserNotFoundException;
import com.example.VaccinationBookingSystem.Model.*;
import com.example.VaccinationBookingSystem.Repository.*;
import com.example.VaccinationBookingSystem.Service.AppointmentService;
import com.example.VaccinationBookingSystem.Service.Dose1Service;
import com.example.VaccinationBookingSystem.Service.Dose2Service;
import com.example.VaccinationBookingSystem.Transformer.CentreTansformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    
    @Autowired
    UserRepository userRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    Dose1Service dose1Service;

    @Autowired
    Dose2Service dose2Service;

    @Autowired
    private JavaMailSender emailSender;

    @Override
    public AppointmentResponseDto bookAppointment(AppointmentRequestDto appointmentRequestDto) throws UserNotFoundException,DoctorNotFoundException,NotEligibleForDoseException{

        Optional<User> optionalUser=userRepository.findById((appointmentRequestDto.getUserId()));
        if(!optionalUser.isPresent()){
            throw new UserNotFoundException("User doesn't exist!");
        }

        Optional<Doctor> optionalDoctor=doctorRepository.findById(appointmentRequestDto.getDoctorId());
        if(!optionalDoctor.isPresent()){
            throw new DoctorNotFoundException("Doctor doesn't exist!");
        }

        User user=optionalUser.get();
        Doctor doctor=optionalDoctor.get();

       if(appointmentRequestDto.getDoseNo() == DoseNo.DOSE1){
           Dose1 dose1=dose1Service.createDose1(user,appointmentRequestDto.getVaccineType());
           user.setDose1Taken(true);
           user.setDose1(dose1);
       }
       else{
           //DOSE_2
           if(!user.isDose1Taken()){
               throw new NotEligibleForDoseException("Sorry! You are not yet eligible for Dose2");
           }
           Dose2 dose2=dose2Service.createDose2(user,appointmentRequestDto.getVaccineType());
           user.setDose2Taken(true);
           user.setDose2(dose2);
       }
        Appointment appointment=Appointment.builder()
                .appointmentNo(String.valueOf(UUID.randomUUID()))
                .doseNo(appointmentRequestDto.getDoseNo())
                .user(user)
                .doctor(doctor)
                .build();

       user.getAppointmentList().add(appointment);
       User savedUser=userRepository.save(user);  //save dose1,dose2 and appointment

        Appointment savedAppointment=savedUser.getAppointmentList().get(savedUser.getAppointmentList().size()-1);
        doctor.getAppointments().add(savedAppointment);
        doctorRepository.save(doctor);

        // send email
        String text = "Congrats!! " + user.getName() + " Your appointment for "+ appointmentRequestDto.getDoseNo() + " has been booked!!";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("testifydosify@gmail.com");
        message.setTo(user.getEmailId());
        message.setSubject("Appointment Booked !!!");
        message.setText(text);
        emailSender.send(message);

        //prepare responsedto
        CentreResponseDto centreResponseDto= CentreTansformer.centreToCentreResponseDto(doctor.getVaccinationCentre());

        return AppointmentResponseDto.builder()
                .userName(user.getName())
                .appointmentNo(appointment.getAppointmentNo())
                .dateOfAppointment(savedAppointment.getDateOfAppointment())
                .doseNo(appointment.getDoseNo())
                .centerResponseDto(centreResponseDto)
                .doctorName(doctor.getName())
                .vaccineType(appointmentRequestDto.getVaccineType())
                .build();
    }
}
