package mx.edu.upqroo.kristenandroid.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.crashlytics.android.Crashlytics;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.adapters.KardexItemAdapter;
import mx.edu.upqroo.kristenandroid.common.SessionHelper;
import mx.edu.upqroo.kristenandroid.models.Kardex;
import mx.edu.upqroo.kristenandroid.service.ApiServices;
import mx.edu.upqroo.kristenandroid.service.messages.KardexListMessage;

/**
 * A simple {@link Fragment} subclass.
 */
public class KardexFragment extends Fragment {

    private ArrayList<Kardex> mKardexList;
    private RecyclerView mRecyclerKardex;
    private KardexItemAdapter mKardexAdapter;
    private ProgressBar mProgress;

    public KardexFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_kardex, container, false);

        mKardexList = new ArrayList<>();

        mRecyclerKardex =  v.findViewById(R.id.recycler_kardex);
        mRecyclerKardex.setHasFixedSize(true);
        mRecyclerKardex.setLayoutManager(new LinearLayoutManager(v.getContext()));
        mRecyclerKardex.setVisibility(View.GONE);

        mProgress = v.findViewById(R.id.progress_kardex);
        mProgress.setVisibility(View.VISIBLE);

        mKardexAdapter = new KardexItemAdapter(v.getContext(), mKardexList);
        mRecyclerKardex.setAdapter(mKardexAdapter);
        ApiServices.getKardexList(SessionHelper.getInstance().getSession().getUserId());
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(KardexListMessage event) {
        if (event.isSuccessful()) {
            mRecyclerKardex.setVisibility(View.VISIBLE);
            mKardexList.addAll(event.getKardexList());
            mKardexAdapter.notifyDataSetChanged();
        } else {
            Crashlytics.log("Llamada de kardex fallida");
            //todo set visible a text view saying that there was an error
        }
        mProgress.setVisibility(View.GONE);
    }
}
