package com.example.VaccinationBookingSystem.Exception;

public class NotEligibleForDoseException extends Exception{
    public NotEligibleForDoseException(String message){
        super(message);
    }
}
