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
        mGeneralInfo = new GeneralInfo("Rodrigo", "Pech", "Ing. Software", "201500100", "ISOF-2013 DE 375 CREDITOS", "( )",
                                       "295.0", "VIGENTE", "(3153) SEP DIC 1", "00", "(09) (3182) MAY-AGO 18", "pepr970313hqrccd01",
                                        "1997-03-13", "SANTA CECILIA", "CANCUN", "77500", "991564564", "9981158454", "rodrigoupech13@gmail.com",
                                       "MANO AMIGA", "DIEGO LOVERA");
    }
}
