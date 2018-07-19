package mx.edu.upqroo.kristenandroid.fragments;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.adapters.ScheduleItemAdapter;
import mx.edu.upqroo.kristenandroid.common.EndlessRecyclerViewScrollListener;
import mx.edu.upqroo.kristenandroid.models.News;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScheduleFragment extends Fragment {

    private String _dayOfWeek, _message;
    private int _imgSchedule;

    public ScheduleFragment() {
    }

    @SuppressLint("ValidFragment")
    public ScheduleFragment(String _dayOfWeek, String _message, int _imgSchedule) {
        this._dayOfWeek = _dayOfWeek;
        this._message = _message;
        this._imgSchedule = _imgSchedule;
    }

    public String get_dayOfWeek() {
        return _dayOfWeek;
    }

    public void set_dayOfWeek(String _dayOfWeek) {
        this._dayOfWeek = _dayOfWeek;
    }

    public String get_message() {
        return _message;
    }

    public void set_message(String _message) {
        this._message = _message;
    }

    public int get_imgSchedule() {
        return _imgSchedule;
    }

    public void set_imgSchedule(int _imgSchedule) {
        this._imgSchedule = _imgSchedule;
    }
}

