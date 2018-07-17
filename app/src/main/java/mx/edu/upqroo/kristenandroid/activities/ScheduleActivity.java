package mx.edu.upqroo.kristenandroid.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.adapters.ScheduleItemAdapter;

public class ScheduleActivity extends AppCompatActivity {

    ArrayList<String> ListaDatos;
    RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycler = (RecyclerView) findViewById(R.id.recycler_schedule);
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        ListaDatos = new ArrayList<String>();

        for (int i = 0; i <= 50; i++){
            ListaDatos.add("Dato # " +i+ "");
        }
        ScheduleItemAdapter adapter = new ScheduleItemAdapter(ListaDatos);
        recycler.setAdapter(adapter);

    }
}
