package mx.edu.upqroo.kristenandroid.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.models.GeneralInfo;

public class GeneralInfoActivity extends AppCompatActivity {
    GeneralInfo mGeneralInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_info);
        mGeneralInfo = new GeneralInfo("Rodrigo", "Pech", "Ing. Software", "201500100", "ISOF-2013 DE 375 CREDITOS", "( )",
                                       "295.0", "VIGENTE", "(3153) SEP DIC 1", "00", "(09) (3182) MAY-AGO 18", "pepr970313hqrccd01",
                                        "1997-03-13", "SANTA CECILIA", "CANCUN", "77500", "991564564", "9981158454", "rodrigoupech13@gmail.com",
                                       "MANO AMIGA", "DIEGO LOVERA");

        TextView alumnName = findViewById(R.id.text_alumn_name);
        alumnName.setText(mGeneralInfo.getName());

        TextView alumnLastName = findViewById(R.id.text_alumn_lastname);
        alumnLastName.setText(mGeneralInfo.getLastName());

        TextView alumnCareer = findViewById(R.id.text_alumn_career);
        alumnCareer.setText(mGeneralInfo.getCareer());

        TextView alumnPlanstudy = findViewById(R.id.text_alumn_studieplan);
        alumnPlanstudy.setText(mGeneralInfo.getPlanStudy());

        TextView alumnModspecial = findViewById(R.id.text_alumn_speciality);
        alumnModspecial.setText(mGeneralInfo.getModSpecial());

        TextView alumnCredits = findViewById(R.id.text_alumn_credits);
        alumnCredits.setText(mGeneralInfo.getCreditAcumm());

        TextView alumnValidity = findViewById(R.id.text_alumn_status);
        alumnValidity.setText(mGeneralInfo.getValidity());

        TextView alumnEntryP = findViewById(R.id.text_alumn_starting_period);
        alumnEntryP.setText(mGeneralInfo.getEntryPeriiod());

        TextView alumnValidp = findViewById(R.id.text_alumn_valid_periods);
        alumnValidp.setText(mGeneralInfo.getValidPeriods());

        TextView alumnCurrentp = findViewById(R.id.text_alumn_actual_period);
        alumnCurrentp.setText(mGeneralInfo.getCreditAcumm());

        TextView alumnCurp = findViewById(R.id.text_alumn_curp);
        alumnCurp.setText(mGeneralInfo.getCurp());

        TextView alumnBirth = findViewById(R.id.text_alumn_birth);
        alumnBirth.setText(mGeneralInfo.getBirthdate());

        TextView alumnAddress = findViewById(R.id.text_alumn_address);
        alumnAddress.setText(mGeneralInfo.getAddress());

        TextView alumnCity = findViewById(R.id.text_alumn_city);
        alumnCity.setText(mGeneralInfo.getCity());

        TextView alumnCP = findViewById(R.id.text_alumn_postal_code);
        alumnCP.setText(mGeneralInfo.getCp());

        TextView alumnTelDom = findViewById(R.id.text_alumn_local_phone);
        alumnTelDom.setText(mGeneralInfo.getTelDomicilio());

        TextView alumnTelCel = findViewById(R.id.text_alumn_mobile_phone);
        alumnTelCel.setText(mGeneralInfo.getTelCelular());

        TextView alumnEmail = findViewById(R.id.text_alumn_email);
        alumnEmail.setText(mGeneralInfo.getEmail());

        TextView alumnSchool = findViewById(R.id.text_alumn_origin_school);
        alumnSchool.setText(mGeneralInfo.getOriginSchool());

        TextView alumnTutor = findViewById(R.id.text_alumn_tutor);
        alumnTutor.setText(mGeneralInfo.getTutor());
    }
}
