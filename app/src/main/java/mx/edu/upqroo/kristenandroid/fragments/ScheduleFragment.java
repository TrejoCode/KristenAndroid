package mx.edu.upqroo.kristenandroid.fragments;


import android.annotation.SuppressLint;

import androidx.fragment.app.Fragment;

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

