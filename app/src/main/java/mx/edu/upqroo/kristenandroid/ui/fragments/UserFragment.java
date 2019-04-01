package mx.edu.upqroo.kristenandroid.ui.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.managers.SessionManager;
import mx.edu.upqroo.kristenandroid.data.database.entities.UserInformation;
import mx.edu.upqroo.kristenandroid.viewModels.UserViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment {

    private UserViewModel mUserViewModel;
    private Observer<UserInformation> userObserver;

    private TextView mUserNameText;
    private TextView mUserCareerText;
    private TextView mUserCreditsText;
    private TextView mUserValidityText;
    private TextView mUserEntryPText;
    private TextView mUserCurp;
    private TextView mUserDoBText;
    private TextView mUserAddressText;
    private TextView mUserLocalPhoneText;
    private TextView mUserMobilePhoneText;
    private TextView mUserEmailText;

    public static UserFragment newInstance() {
        return new UserFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // View model instance
        mUserViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        // Observer declaration
        userObserver = userInformation -> {
            mUserNameText.setText(userInformation.getName());
            mUserCareerText.setText(userInformation.getEnrollment());
            mUserCreditsText.setText(userInformation.getCreditsAccumulated());
            mUserValidityText.setText(userInformation.getValidity());
            mUserEntryPText.setText(userInformation.getEntryPeriod());
            mUserCurp.setText(userInformation.getCurp());
            mUserDoBText.setText(userInformation.getDob());
            mUserAddressText.setText(userInformation.getAddress());
            mUserLocalPhoneText.setText(userInformation.getPhone());
            mUserMobilePhoneText.setText(userInformation.getMobilePhone());
            mUserEmailText.setText(userInformation.getEmail());
        };

        mUserViewModel.getUser(SessionManager.getInstance().getSession().getUserId())
                .observe(this, userObserver);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_user, container, false);
        mUserNameText = v.findViewById(R.id.text_alumn_name);
        mUserCareerText = v.findViewById(R.id.text_alumn_career);
        mUserCreditsText = v.findViewById(R.id.text_alumn_credits);
        mUserValidityText = v.findViewById(R.id.text_alumn_status);
        mUserEntryPText = v.findViewById(R.id.text_alumn_starting_period);
        mUserCurp = v.findViewById(R.id.text_alumn_curp);
        mUserDoBText = v.findViewById(R.id.text_alumn_birth);
        mUserAddressText = v.findViewById(R.id.text_alumn_address);
        mUserLocalPhoneText = v.findViewById(R.id.text_alumn_local_phone);
        mUserMobilePhoneText = v.findViewById(R.id.text_alumn_mobile_phone);
        mUserEmailText = v.findViewById(R.id.text_alumn_email);
        return v;
    }
}
