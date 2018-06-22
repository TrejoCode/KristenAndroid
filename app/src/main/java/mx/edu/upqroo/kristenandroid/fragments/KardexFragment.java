package mx.edu.upqroo.kristenandroid.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mx.edu.upqroo.kristenandroid.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class KardexFragment extends Fragment {


    public KardexFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kardex, container, false);
    }

}
