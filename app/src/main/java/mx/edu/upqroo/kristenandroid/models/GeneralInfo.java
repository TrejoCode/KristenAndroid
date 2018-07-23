package mx.edu.upqroo.kristenandroid.models;

public class GeneralInfo {
    private String userId;
    private String password;
    private String name;
    private String career;
    private String enrollment;
    private String creditAcumm;
    private String validity;
    private String entryPeriod;
    private String curp;
    private String dob;
    private String address;
    private String phone;
    private String mobilePhone;
    private String email;
    private String generalTopic;
    private String userTopic;


    public GeneralInfo() {

    }

    public GeneralInfo(String name, String career, String enrollment, String creditAcumm,
                       String validity, String entryPeriod, String curp, String dob, String address,
                       String phone, String mobilePhone, String email, String generalTopic,
                       String userTopic, String userId, String password) {
        this.name = name;
        this.career = career;
        this.enrollment = enrollment;
        this.creditAcumm = creditAcumm;
        this.validity = validity;
        this.entryPeriod = entryPeriod;
        this.curp = curp;
        this.dob = dob;
        this.address = address;
        this.phone = phone;
        this.mobilePhone = mobilePhone;
        this.email = email;
        this.generalTopic = generalTopic;
        this.userTopic = userTopic;
        this.userId = userId;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public String getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(String enrollment) {
        this.enrollment = enrollment;
    }

    public String getCreditAcumm() {
        return creditAcumm;
    }

    public void setCreditAcumm(String creditAcumm) {
        this.creditAcumm = creditAcumm;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    public String getEntryPeriod() {
        return entryPeriod;
    }

    public void setEntryPeriod(String entryPeriod) {
        this.entryPeriod = entryPeriod;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGeneralTopic() {
        return generalTopic;
    }

    public void setGeneralTopic(String generalTopic) {
        this.generalTopic = generalTopic;
    }

    public String getUserTopic() {
        return userTopic;
    }

    public void setUserTopic(String userTopic) {
        this.userTopic = userTopic;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
