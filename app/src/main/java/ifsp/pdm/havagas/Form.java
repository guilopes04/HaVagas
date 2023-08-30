package ifsp.pdm.havagas;

import java.util.Objects;

public class Form {
    private String name;
    private String email;
    private String receiveEmailOption;
    private String phone;
    private String residentialCommercialPhone;
    private String cellOption;
    private String cell;
    private String gender;
    private String birthDate;
    private String education;
    private String graduationYear;
    private String completionYear;
    private String educationInstitution;
    private String thesisTitle;
    private String thesisAdvisor;
    private String interestPositions;

    public Form() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReceiveEmailOption() {
        return receiveEmailOption;
    }

    public void setReceiveEmailOption(String receiveEmailOption) {
        this.receiveEmailOption = receiveEmailOption;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getResidentialCommercialPhone() {
        return residentialCommercialPhone;
    }

    public void setResidentialCommercialPhone(String residentialCommercialPhone) {
        this.residentialCommercialPhone = residentialCommercialPhone;
    }

    public String getCellOption() {
        return cellOption;
    }

    public void setCellOption(String cellOption) {
        this.cellOption = cellOption;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(String graduationYear) {
        this.graduationYear = graduationYear;
    }

    public String getCompletionYear() {
        return completionYear;
    }

    public void setCompletionYear(String completionYear) {
        this.completionYear = completionYear;
    }

    public String getEducationInstitution() {
        return educationInstitution;
    }

    public void setEducationInstitution(String educationInstitution) {
        this.educationInstitution = educationInstitution;
    }

    public String getThesisTitle() {
        return thesisTitle;
    }

    public void setThesisTitle(String thesisTitle) {
        this.thesisTitle = thesisTitle;
    }

    public String getThesisAdvisor() {
        return thesisAdvisor;
    }

    public void setThesisAdvisor(String thesisAdvisor) {
        this.thesisAdvisor = thesisAdvisor;
    }

    public String getInterestPositions() {
        return interestPositions;
    }

    public void setInterestPositions(String interestPositions) {
        this.interestPositions = interestPositions;
    }

    public Form(String name, String email, String receiveEmailOption, String phone, String residentialCommercialPhone, String cellOption, String cell, String gender, String birthDate, String education, String graduationYear, String completionYear, String educationInstitution, String thesisTitle, String thesisAdvisor, String interestPositions) {
        this.name = name;
        this.email = email;
        this.receiveEmailOption = receiveEmailOption;
        this.phone = phone;
        this.residentialCommercialPhone = residentialCommercialPhone;
        this.cellOption = cellOption;
        this.cell = cell;
        this.gender = gender;
        this.birthDate = birthDate;
        this.education = education;
        this.graduationYear = graduationYear;
        this.completionYear = completionYear;
        this.educationInstitution = educationInstitution;
        this.thesisTitle = thesisTitle;
        this.thesisAdvisor = thesisAdvisor;
        this.interestPositions = interestPositions;
    }

    @Override
    public String toString() {
        return "Form{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", receiveEmailOption='" + receiveEmailOption + '\'' +
                ", phone='" + phone + '\'' +
                ", residentialCommercialPhone='" + residentialCommercialPhone + '\'' +
                ", cellOption='" + cellOption + '\'' +
                ", cell='" + cell + '\'' +
                ", gender='" + gender + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", education='" + education + '\'' +
                ", graduationYear='" + graduationYear + '\'' +
                ", completionYear='" + completionYear + '\'' +
                ", educationInstitution='" + educationInstitution + '\'' +
                ", thesisTitle='" + thesisTitle + '\'' +
                ", thesisAdvisor='" + thesisAdvisor + '\'' +
                ", interestPositions='" + interestPositions + '\'' +
                '}';
    }
}
