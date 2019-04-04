package mx.edu.upqroo.kristenandroid.ui.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import io.fabric.sdk.android.services.concurrency.AsyncTask;
import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.managers.SessionManager;
import mx.edu.upqroo.kristenandroid.data.database.entities.UserInformation;
import mx.edu.upqroo.kristenandroid.viewModels.UserViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment {

    private UserViewModel mViewModel;

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

    private SwipeRefreshLayout mSwipeContainer;

    public static UserFragment newInstance() {
        return new UserFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // View model instance
        mViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
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

        mSwipeContainer = v.findViewById(R.id.refreshLayout_user);
        // Setup refresh listener which triggers new data loading
        mSwipeContainer.setOnRefreshListener(() -> {
            /*trigger the load of the data to the service*/
            AsyncTask.execute(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mSwipeContainer.setRefreshing(false);
            });
        });
        // Configure the refreshing colors
        mSwipeContainer.setColorSchemeResources(R.color.colorAccent,
                R.color.colorPrimary,
                R.color.colorPrimaryDark,
                R.color.colorPrimaryDarker);

        mViewModel.getUser(SessionManager.getInstance().getSession().getUserId())
                .observe(this, userInformation -> {
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
                });
        return v;
    }
}
