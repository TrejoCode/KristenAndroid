package mx.edu.upqroo.kristenandroid.database.entities;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_information")
public class UserInformation {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "userId")
    private String userId;

    @NonNull
    @ColumnInfo(name = "password")
    private String password;

    @NonNull
    @ColumnInfo(name = "name")
    private String name;

    @NonNull
    @ColumnInfo(name = "career")
    private String career;

    @NonNull
    @ColumnInfo(name = "enrollment")
    private String enrollment;

    @NonNull
    @ColumnInfo(name = "creditsAccumulated")
    private String creditsAccumulated;

    @NonNull
    @ColumnInfo(name = "validity")
    private String validity;

    @NonNull
    @ColumnInfo(name = "entryPeriod")
    private String entryPeriod;

    @NonNull
    @ColumnInfo(name = "curp")
    private String curp;

    @NonNull
    @ColumnInfo(name = "dob")
    private String dob;

    @NonNull
    @ColumnInfo(name = "address")
    private String address;

    @NonNull
    @ColumnInfo(name = "phone")
    private String phone;

    @NonNull
    @ColumnInfo(name = "mobilePhone")
    private String mobilePhone;

    @NonNull
    @ColumnInfo(name = "email")
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
