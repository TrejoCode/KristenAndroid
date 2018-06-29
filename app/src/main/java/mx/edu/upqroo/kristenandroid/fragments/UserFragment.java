package mx.edu.upqroo.kristenandroid.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.models.GeneralInfo;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment {
    GeneralInfo mGeneralInfo;


    public UserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_user, container, false);
        mGeneralInfo = new GeneralInfo("Rodrigo", "Pech", "Ing. Software", "201500100", "ISOF-2013 DE 375 CREDITOS", "( )",
                "295.0", "VIGENTE", "(3153) SEP DIC 1", "00", "(09) (3182) MAY-AGO 18", "pepr970313hqrccd01",
                "1997-03-13", "SANTA CECILIA", "CANCUN", "77500", "991564564", "9981158454", "rodrigoupech13@gmail.com",
                "MANO AMIGA", "DIEGO LOVERA");

        TextView alumnName = v.findViewById(R.id.text_alumn_name);
        alumnName.setText(mGeneralInfo.getName());

        TextView alumnLastName = v.findViewById(R.id.text_alumn_lastname);
        alumnLastName.setText(mGeneralInfo.getLastName());

        TextView alumnCareer = v.findViewById(R.id.text_alumn_career);
        alumnCareer.setText(mGeneralInfo.getCareer());

        TextView alumnPlanstudy = v.findViewById(R.id.text_alumn_studieplan);
        alumnPlanstudy.setText(mGeneralInfo.getPlanStudy());

        TextView alumnModspecial = v.findViewById(R.id.text_alumn_speciality);
        alumnModspecial.setText(mGeneralInfo.getModSpecial());

        TextView alumnCredits = v.findViewById(R.id.text_alumn_credits);
        alumnCredits.setText(mGeneralInfo.getCreditAcumm());

        TextView alumnValidity = v.findViewById(R.id.text_alumn_status);
        alumnValidity.setText(mGeneralInfo.getValidity());

        TextView alumnEntryP = v.findViewById(R.id.text_alumn_starting_period);
        alumnEntryP.setText(mGeneralInfo.getEntryPeriod());

        TextView alumnValidp = v.findViewById(R.id.text_alumn_valid_periods);
        alumnValidp.setText(mGeneralInfo.getValidPeriods());

        TextView alumnCurrentp = v.findViewById(R.id.text_alumn_actual_period);
        alumnCurrentp.setText(mGeneralInfo.getCreditAcumm());

        TextView alumnCurp = v.findViewById(R.id.text_alumn_curp);
        alumnCurp.setText(mGeneralInfo.getCurp());

        TextView alumnBirth = v.findViewById(R.id.text_alumn_birth);
        alumnBirth.setText(mGeneralInfo.getDob());

        TextView alumnAddress = v.findViewById(R.id.text_alumn_address);
        alumnAddress.setText(mGeneralInfo.getAddress());

        TextView alumnCity = v.findViewById(R.id.text_alumn_city);
        alumnCity.setText(mGeneralInfo.getCity());

        TextView alumnCP = v.findViewById(R.id.text_alumn_postal_code);
        alumnCP.setText(mGeneralInfo.getCp());

        TextView alumnTelDom = v.findViewById(R.id.text_alumn_local_phone);
        alumnTelDom.setText(mGeneralInfo.getPhone());

        TextView alumnTelCel = v.findViewById(R.id.text_alumn_mobile_phone);
        alumnTelCel.setText(mGeneralInfo.getMobilePhone());

        TextView alumnEmail = v.findViewById(R.id.text_alumn_email);
        alumnEmail.setText(mGeneralInfo.getEmail());

        TextView alumnSchool = v.findViewById(R.id.text_alumn_origin_school);
        alumnSchool.setText(mGeneralInfo.getPreviousSchool());

        TextView alumnTutor = v.findViewById(R.id.text_alumn_tutor);
        alumnTutor.setText(mGeneralInfo.getTutor());
        return v;
    }

}
