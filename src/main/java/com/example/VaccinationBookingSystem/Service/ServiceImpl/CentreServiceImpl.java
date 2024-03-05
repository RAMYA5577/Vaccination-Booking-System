package com.example.VaccinationBookingSystem.Service.ServiceImpl;

import com.example.VaccinationBookingSystem.Dto.RequestDto.CentreRequestDto;
import com.example.VaccinationBookingSystem.Dto.ResponseDto.CentreResponseDto;
import com.example.VaccinationBookingSystem.Enum.CentreType;
import com.example.VaccinationBookingSystem.Enum.Gender;
import com.example.VaccinationBookingSystem.Exception.CentreNotFoundException;
import com.example.VaccinationBookingSystem.Model.Doctor;
import com.example.VaccinationBookingSystem.Model.VaccinationCentre;
import com.example.VaccinationBookingSystem.Repository.CentreRepository;
import com.example.VaccinationBookingSystem.Service.CentreService;
import com.example.VaccinationBookingSystem.Transformer.CentreTansformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CentreServiceImpl implements CentreService {
    @Autowired
    CentreRepository centreRepository;

    @Override
    public CentreResponseDto addCentre(CentreRequestDto centreRequestDto) {
        //Dto to entity
        VaccinationCentre centre= CentreTansformer.centreRequestDtoToCentre(centreRequestDto);

        //save in repo
        VaccinationCentre savedCentre=centreRepository.save(centre);

        //entity to dto
        CentreResponseDto centreResponseDto=CentreTansformer.centreToCentreResponseDto(savedCentre);
        return centreResponseDto;
    }

    @Override
    public List<String> getListOfDoctorsAtAParticularCentre(int id) throws CentreNotFoundException {
        List<String> doctors=new ArrayList<>();
        Optional<VaccinationCentre> optionalCentre=centreRepository.findById(id);
        if(!optionalCentre.isPresent()){
            throw new  CentreNotFoundException("No centre found with provided centre Id");
        }
        VaccinationCentre centre=optionalCentre.get();
        List<Doctor> doctorList=centre.getDoctors();
        for(Doctor doctor:doctorList){
            doctors.add(doctor.getName());
        }
        return doctors;
    }

    @Override
    public List<String> getListOfAllMaleDoctorsAtAParticularCentre(int id) throws CentreNotFoundException {
        List<String> maleDoctors=new ArrayList<>();
        Optional<VaccinationCentre> optionalCentre=centreRepository.findById(id);
        if(!optionalCentre.isPresent()){
            throw new CentreNotFoundException("No centre was found with provided centre Id");
        }
        VaccinationCentre centre=optionalCentre.get();
        List<Doctor> doctors=centre.getDoctors();
        for(Doctor doctor:doctors){
            if(doctor.getGender().equals(Gender.MALE)){
                maleDoctors.add(doctor.getName());
            }
        }
        return maleDoctors;
    }

    @Override
    public List<String> getListOfAllFemaleDoctorsAtAParticularCentre(int id) throws CentreNotFoundException {
        List<String> femaleDoctors=new ArrayList<>();
        Optional<VaccinationCentre> optionalCentre=centreRepository.findById(id);
        if(!optionalCentre.isPresent()){
            throw new CentreNotFoundException("No centre was found with provided centre Id");
        }
        VaccinationCentre centre=optionalCentre.get();
        List<Doctor> doctors=centre.getDoctors();
        for(Doctor doctor:doctors){
            if(doctor.getGender().equals(Gender.FEMALE)){
                femaleDoctors.add(doctor.getName());
            }
        }
        return femaleDoctors;
    }

    @Override
    public List<String> getListOfMaleDoctorsAgeAbove40AtAParticularCentre(int id) throws CentreNotFoundException {
        List<String> maleDoctorsAgeAbove40=new ArrayList<>();
        Optional<VaccinationCentre> optionalCentre=centreRepository.findById(id);
        if(!optionalCentre.isPresent()){
            throw new CentreNotFoundException("Centre was not present with provided CentreId");
        }
        VaccinationCentre centre=optionalCentre.get();
        List<Doctor> doctors=centre.getDoctors();
        for(Doctor doctor:doctors){
            if(doctor.getGender().equals(Gender.MALE) && doctor.getAge()>40){
                maleDoctorsAgeAbove40.add(doctor.getName());
            }
        }
        return maleDoctorsAgeAbove40;
    }

    @Override
    public List<String> getAllCentresWithParticularCentreType(CentreType centreType) throws CentreNotFoundException {
      List<String> centresWithSameCentreType=new ArrayList<>();
      List<VaccinationCentre>centres=centreRepository.findAll();
      for(VaccinationCentre centre:centres){
          if(centre.getCentreType().equals(centreType)){
              centresWithSameCentreType.add(centre.getName());
          }
      }

        return centresWithSameCentreType;
    }

}
