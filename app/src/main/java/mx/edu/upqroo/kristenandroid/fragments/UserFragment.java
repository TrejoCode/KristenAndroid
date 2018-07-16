package mx.edu.upqroo.kristenandroid.fragments;


import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import me.dkzwm.widget.srl.RefreshingListenerAdapter;
import me.dkzwm.widget.srl.SmoothRefreshLayout;
import me.dkzwm.widget.srl.extra.header.ClassicHeader;
import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.common.SessionHelper;
import mx.edu.upqroo.kristenandroid.models.GeneralInfo;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment {
    private SmoothRefreshLayout mRefreshLayout;

    public UserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        SessionHelper mSession = SessionHelper.getInstance();
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_user, container, false);

        mRefreshLayout = v.findViewById(R.id.refreshLayout_UserInfo);

        mRefreshLayout.setHeaderView(new ClassicHeader(getContext()));
        mRefreshLayout.setOnRefreshListener(new RefreshingListenerAdapter() {
            @Override
            public void onRefreshBegin(boolean isRefresh) {
                Toast.makeText(getContext(), "Refreshing", Toast.LENGTH_LONG).show();
            }
        });

        TextView alumnName = v.findViewById(R.id.text_alumn_name);
        alumnName.setText(mSession.getSession().getName());

        TextView alumnLastName = v.findViewById(R.id.text_alumn_lastname);
        alumnLastName.setText(mSession.getSession().getLastName());

        TextView alumnCareer = v.findViewById(R.id.text_alumn_career);
        alumnCareer.setText(mSession.getSession().getCareer());

        TextView alumnPlanstudy = v.findViewById(R.id.text_alumn_studieplan);
        alumnPlanstudy.setText(mSession.getSession().getPlanStudy());

        TextView alumnModspecial = v.findViewById(R.id.text_alumn_speciality);
        alumnModspecial.setText(mSession.getSession().getModSpecial());

        TextView alumnCredits = v.findViewById(R.id.text_alumn_credits);
        alumnCredits.setText(mSession.getSession().getCreditAcumm());

        TextView alumnValidity = v.findViewById(R.id.text_alumn_status);
        alumnValidity.setText(mSession.getSession().getValidity());

        TextView alumnEntryP = v.findViewById(R.id.text_alumn_starting_period);
        alumnEntryP.setText(mSession.getSession().getEntryPeriod());

        TextView alumnValidp = v.findViewById(R.id.text_alumn_valid_periods);
        alumnValidp.setText(mSession.getSession().getValidPeriods());

        TextView alumnCurrentp = v.findViewById(R.id.text_alumn_actual_period);
        alumnCurrentp.setText(mSession.getSession().getCreditAcumm());

        TextView alumnCurp = v.findViewById(R.id.text_alumn_curp);
        alumnCurp.setText(mSession.getSession().getCurp());

        TextView alumnBirth = v.findViewById(R.id.text_alumn_birth);
        alumnBirth.setText(mSession.getSession().getDob());

        TextView alumnAddress = v.findViewById(R.id.text_alumn_address);
        alumnAddress.setText(mSession.getSession().getAddress());

        TextView alumnCity = v.findViewById(R.id.text_alumn_city);
        alumnCity.setText(mSession.getSession().getCity());

        TextView alumnCP = v.findViewById(R.id.text_alumn_postal_code);
        alumnCP.setText(mSession.getSession().getCp());

        TextView alumnTelDom = v.findViewById(R.id.text_alumn_local_phone);
        alumnTelDom.setText(mSession.getSession().getPhone());

        TextView alumnTelCel = v.findViewById(R.id.text_alumn_mobile_phone);
        alumnTelCel.setText(mSession.getSession().getMobilePhone());

        TextView alumnEmail = v.findViewById(R.id.text_alumn_email);
        alumnEmail.setText(mSession.getSession().getEmail());

        TextView alumnSchool = v.findViewById(R.id.text_alumn_origin_school);
        alumnSchool.setText(mSession.getSession().getPreviousSchool());

        TextView alumnTutor = v.findViewById(R.id.text_alumn_tutor);
        alumnTutor.setText(mSession.getSession().getTutor());
        return v;
    }

}
