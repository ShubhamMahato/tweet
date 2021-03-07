package com.tweetapp.tweet.service.util;

import com.tweetapp.tweet.model.ReasonConstant;
import com.tweetapp.tweet.model.User;
import org.apache.commons.validator.routines.EmailValidator;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class UserValidator {
    public static String validateUser(User user, String date){
        if (!isValidString(user.getFirstName())) {
            return ReasonConstant.firstNameNotValid;
        } else if (user.getLastName()!=null && user.getLastName().length()>0 && !isValidString(user.getLastName())) {
            return ReasonConstant.lastNameNotValid;
        } else if (!emailValidator(user.getEmail())) {
            return ReasonConstant.emailNotValid;
        } else if (date.length() > 0 && !isValidDate(date)) {
            return ReasonConstant.dateNotValid;
        } else if (user.getGender() == null || user.getGender() != null && !isValidGender(user)) {
            return ReasonConstant.genderNotValid;
        } else if (user.getPassword() == null || user.getPassword().length() < 8) {
            return ReasonConstant.passwordNotValid;
        } else {
            return ReasonConstant.valid;
        }

    }

    public static boolean emailValidator(String email) {
        if (email == null) {
            return false;
        }
        // Get an EmailValidator
        EmailValidator validator = EmailValidator.getInstance();

        // Validate specified String containing an email address
        return validator.isValid(email);
    }

    public static boolean isValidDate(String inDate) {
        if(inDate==null){
            return false;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate);
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }

    public static boolean isValidString(String name){
        if(name==null){
            return false;
        }
        return !name.matches(".*\\d.*");
    }

    public static boolean isValidGender(User user) {
        if(user.getGender()==null){
            return false;
        }
        if (user.getGender().equalsIgnoreCase("MALE")) {
            user.setGender("MALE");
            return true;
        } else if (user.getGender().equalsIgnoreCase("FEMALE")){
            user.setGender("FEMALE");
            return true;
        }
       return false;
    }

    private UserValidator(){

    }
}
