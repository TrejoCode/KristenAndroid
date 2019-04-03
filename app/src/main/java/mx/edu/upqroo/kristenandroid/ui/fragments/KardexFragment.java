package mx.edu.upqroo.kristenandroid.ui.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.adapters.KardexItemAdapter;
import mx.edu.upqroo.kristenandroid.managers.SessionManager;
import mx.edu.upqroo.kristenandroid.viewModels.KardexViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class KardexFragment extends Fragment {

    private KardexViewModel mViewModel;

    private RecyclerView mRecyclerKardex;
    private KardexItemAdapter mKardexAdapter;
    private ProgressBar mProgress;

    public static KardexFragment newInstance() {
        return new KardexFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // View model instance
        mViewModel = ViewModelProviders.of(this).get(KardexViewModel.class);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_kardex, container, false);
        mRecyclerKardex =  v.findViewById(R.id.recycler_kardex);
        mRecyclerKardex.setHasFixedSize(true);
        mRecyclerKardex.setLayoutManager(new LinearLayoutManager(v.getContext()));
        mRecyclerKardex.setVisibility(View.GONE);
        mProgress = v.findViewById(R.id.progress_kardex);
        mProgress.setVisibility(View.VISIBLE);
        mKardexAdapter = new KardexItemAdapter(v.getContext(), new ArrayList<>());
        mRecyclerKardex.setAdapter(mKardexAdapter);

        mViewModel.getKardex(SessionManager.getInstance().getSession().getUserId())
                .observe(this, kardexList -> {
                    mRecyclerKardex.setVisibility(View.VISIBLE);
                    mProgress.setVisibility(View.GONE);
                    mKardexAdapter.setData(kardexList);
        });

        return v;
    }
}
