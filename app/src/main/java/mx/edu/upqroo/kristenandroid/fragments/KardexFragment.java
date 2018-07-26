package mx.edu.upqroo.kristenandroid.fragments;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import me.dkzwm.widget.srl.RefreshingListenerAdapter;
import me.dkzwm.widget.srl.SmoothRefreshLayout;
import me.dkzwm.widget.srl.extra.header.ClassicHeader;
import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.adapters.GradesItemAdapter;
import mx.edu.upqroo.kristenandroid.adapters.KardexItemAdapter;
import mx.edu.upqroo.kristenandroid.common.SessionHelper;
import mx.edu.upqroo.kristenandroid.models.Grades;
import mx.edu.upqroo.kristenandroid.models.Kardex;
import mx.edu.upqroo.kristenandroid.service.ApiServices;
import mx.edu.upqroo.kristenandroid.service.messages.GradesListMessage;
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
        mRecyclerKardex.setVisibility(View.VISIBLE);
        mProgress.setVisibility(View.GONE);
        mKardexList.addAll(event.getKardexList());
        mKardexAdapter.notifyDataSetChanged();
    }
}
