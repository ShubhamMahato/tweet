package com.tweetapp.tweet.service.util;

import com.tweetapp.tweet.model.ReasonConstant;
import com.tweetapp.tweet.model.User;
import org.apache.commons.validator.routines.EmailValidator;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class UserValidator {
    /**
     * validate the users details
     * @param user
     * @param date
     * @return
     */
    public static String validateUser(User user, String date){
        //check if firstname doesn't has numerical and characters
        if (!isValidString(user.getFirstName())) {
            return ReasonConstant.firstNameNotValid;
        }//check if lastname is there the validate
        else if (user.getLastName()!=null && user.getLastName().length()>0 && !isValidString(user.getLastName())) {
            return ReasonConstant.lastNameNotValid;
        }// validate the correct email
        else if (!emailValidator(user.getEmail())) {
            return ReasonConstant.emailNotValid;
        }//validate the date
        else if (date!=null && date.length() > 0 && !isValidDate(date)) {
            return ReasonConstant.dateNotValid;
        }// get the gender and validate change to upper case
        else if (user.getGender() == null || user.getGender() != null && !isValidGender(user)) {
            return ReasonConstant.genderNotValid;
        }// get the string and check if length is 8
        else if (user.getPassword() == null || user.getPassword().length() < 8) {
            return ReasonConstant.passwordNotValid;
        } else {
            return ReasonConstant.valid;
        }

    }

    /**
     * Validate the email
     * @param email
     * @return
     */
    public static boolean emailValidator(String email) {
        if (email == null) {
            return false;
        }
        // Get an EmailValidator
        EmailValidator validator = EmailValidator.getInstance();

        // Validate specified String containing an email address
        return validator.isValid(email);
    }

    /**
     * check if date is valid
     * @param inDate
     * @return
     */
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

    /**
     * is valid firstname and lastname
     * @param name
     * @return
     */
    public static boolean isValidString(String name){
        if(name==null){
            return false;
        }
        return !name.matches(".*\\d.*");
    }

    /**
     * validate the gender
     * @param user
     * @return
     */
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
