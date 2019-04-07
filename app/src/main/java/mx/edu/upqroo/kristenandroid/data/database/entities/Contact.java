package mx.edu.upqroo.kristenandroid.data.database.entities;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "contact")
public class Contact {

    @PrimaryKey()
    @ColumnInfo(name = "contactId")
    @SerializedName("idContacto")
    @NonNull
    private String contactId;

    @ColumnInfo(name = "name")
    @SerializedName("nombre")
    private String name;

    @ColumnInfo(name = "email")
    @SerializedName("correo")
    private String email;

    public Contact(@NonNull String contactId, String name, String email) {
        this.contactId = contactId;
        this.name = name;
        this.email = email;
    }

    public void setContactId(@NotNull String contactId) {
        this.contactId = contactId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotNull
    public String getContactId() {
        return contactId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
