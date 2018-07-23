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
    public UserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        SessionHelper mSession = SessionHelper.getInstance();
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_user, container, false);

        TextView alumnName = v.findViewById(R.id.text_alumn_name);
        alumnName.setText(mSession.getSession().getName());

        TextView alumnCareer = v.findViewById(R.id.text_alumn_career);
        alumnCareer.setText(mSession.getSession().getCareer());

        TextView alumnCredits = v.findViewById(R.id.text_alumn_credits);
        alumnCredits.setText(mSession.getSession().getCreditAcumm());

        TextView alumnValidity = v.findViewById(R.id.text_alumn_status);
        alumnValidity.setText(mSession.getSession().getValidity());

        TextView alumnEntryP = v.findViewById(R.id.text_alumn_starting_period);
        alumnEntryP.setText(mSession.getSession().getEntryPeriod());

        TextView alumnCurp = v.findViewById(R.id.text_alumn_curp);
        alumnCurp.setText(mSession.getSession().getCurp());

        TextView alumnBirth = v.findViewById(R.id.text_alumn_birth);
        alumnBirth.setText(mSession.getSession().getDob());

        TextView alumnAddress = v.findViewById(R.id.text_alumn_address);
        alumnAddress.setText(mSession.getSession().getAddress());

        TextView alumnTelDom = v.findViewById(R.id.text_alumn_local_phone);
        alumnTelDom.setText(mSession.getSession().getPhone());

        TextView alumnTelCel = v.findViewById(R.id.text_alumn_mobile_phone);
        alumnTelCel.setText(mSession.getSession().getMobilePhone());

        TextView alumnEmail = v.findViewById(R.id.text_alumn_email);
        alumnEmail.setText(mSession.getSession().getEmail());
        return v;
    }

}
