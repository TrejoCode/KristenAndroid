package mx.edu.upqroo.kristenandroid.ui.fragments;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.adapters.ContactItemAdapter;
import mx.edu.upqroo.kristenandroid.viewModels.ContactViewModel;

public class ContactFragment extends Fragment {

    private ContactViewModel mViewModel;

    private RecyclerView mRecyclerContact;
    private ContactItemAdapter mContactAdapter;
    private ProgressBar mProgress;

    private SwipeRefreshLayout mSwipeContainer;

    public static ContactFragment newInstance() {
        return new ContactFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // View model instance
        mViewModel = ViewModelProviders.of(this).get(ContactViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_contact, container, false);
        mRecyclerContact =  v.findViewById(R.id.recycler_contacts);
        mRecyclerContact.setHasFixedSize(true);
        mRecyclerContact.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerContact.setVisibility(View.GONE);
        mProgress = v.findViewById(R.id.progress_contacts);
        mProgress.setVisibility(View.VISIBLE);

        mContactAdapter = new ContactItemAdapter(getContext(), new ArrayList<>());
        mRecyclerContact.setAdapter(mContactAdapter);

        mSwipeContainer = v.findViewById(R.id.refreshLayout_contacts);
        // Setup refresh listener which triggers new data loading
        mSwipeContainer.setOnRefreshListener(() -> mViewModel.updateFromService());
        // Configure the refreshing colors
        mSwipeContainer.setColorSchemeResources(R.color.colorAccent,
                R.color.colorPrimary,
                R.color.colorPrimaryDark,
                R.color.colorPrimaryDarker);

        mViewModel.getContacts()
                .observe(this, contacts -> {
                    mRecyclerContact.setVisibility(View.VISIBLE);
                    mContactAdapter.setData(contacts);
                    mSwipeContainer.setRefreshing(false);
                    mProgress.setVisibility(View.GONE);
                });
        return v;
    }

}
