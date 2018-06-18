package mx.edu.upqroo.kristenandroid.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.models.GeneralInfo;

public class GeneralInfoActivity extends AppCompatActivity {
    GeneralInfo mGeneralInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_info);
        mGeneralInfo = new GeneralInfo("Rodrigo", "Pech", "20", "Teibol dance", "201500100");
    }
}
