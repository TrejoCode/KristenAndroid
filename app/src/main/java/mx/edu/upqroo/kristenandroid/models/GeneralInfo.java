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
    private Config config;

    public GeneralInfo() {

    }

    public GeneralInfo(String name, String career, String enrollment, String creditAcumm,
                       String validity, String entryPeriod, String curp, String dob, String address,
                       String phone, String mobilePhone, String email, String userId,
                       String password, Config config) {
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
        this.userId = userId;
        this.password = password;
        this.config = config;
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

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }
}
