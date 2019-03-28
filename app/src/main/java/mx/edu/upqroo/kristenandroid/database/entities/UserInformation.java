package mx.edu.upqroo.kristenandroid.database.entities;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_information")
public class UserInformation {

    @PrimaryKey
    @NonNull
    private String userId;
    @NonNull
    private String password;
    @NonNull
    private String name;
    @NonNull
    private String career;
    @NonNull
    private String enrollment;
    @NonNull
    private String creditsAccumulated;
    @NonNull
    private String validity;
    @NonNull
    private String entryPeriod;
    @NonNull
    private String curp;
    @NonNull
    private String dob;
    @NonNull
    private String address;
    @NonNull
    private String phone;
    @NonNull
    private String mobilePhone;
    @NonNull
    private String email;

    @NonNull
    @Embedded
    private Config config;

    public UserInformation(@NotNull String name,
                           @NotNull String career,
                           @NotNull String enrollment,
                           @NotNull String creditsAccumulated,
                           @NotNull String validity,
                           @NotNull String entryPeriod,
                           @NotNull String curp,
                           @NotNull String dob,
                           @NotNull String address,
                           @NotNull String phone,
                           @NotNull String mobilePhone,
                           @NotNull String email,
                           @NotNull String userId,
                           @NotNull String password,
                           @NotNull Config config) {
        this.name = name;
        this.career = career;
        this.enrollment = enrollment;
        this.creditsAccumulated = creditsAccumulated;
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

    @NotNull
    public String getName() {
        return name;
    }

    @NotNull
    public String getCareer() {
        return career;
    }

    @NotNull
    public String getEnrollment() {
        return enrollment;
    }

    @NotNull
    public String getCreditsAccumulated() {
        return creditsAccumulated;
    }

    @NotNull
    public String getValidity() {
        return validity;
    }

    @NotNull
    public String getEntryPeriod() {
        return entryPeriod;
    }

    @NotNull
    public String getCurp() {
        return curp;
    }

    @NotNull
    public String getDob() {
        return dob;
    }

    @NotNull
    public String getAddress() {
        return address;
    }

    @NotNull
    public String getPhone() {
        return phone;
    }

    @NotNull
    public String getMobilePhone() {
        return mobilePhone;
    }

    @NotNull
    public String getEmail() {
        return email;
    }

    @NotNull
    public String getUserId() {
        return userId;
    }

    @NotNull
    public String getPassword() {
        return password;
    }

    @NotNull
    public Config getConfig() {
        return config;
    }
}
