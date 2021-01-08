package com.afrosin.android_part_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.CalendarView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    View incTask1;
    View incTask2;
    View incTask3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RadioButton rbTask1 = findViewById(R.id.rb_task_1);
        rbTask1.setOnClickListener(radioButtonClickListener);

        RadioButton rbTask2 = findViewById(R.id.rb_task_2);
        rbTask2.setOnClickListener(radioButtonClickListener);

        RadioButton rbTask3 = findViewById(R.id.rb_task_3);
        rbTask3.setOnClickListener(radioButtonClickListener);

        TextView textViewSelectedDate = findViewById(R.id.textViewSelectedDate);
        CalendarView calendar_view = findViewById(R.id.calendarView);


        DecimalFormat mFormat = new DecimalFormat("00");

        Date tmpDate = new Date(calendar_view.getDate());
        textViewSelectedDate.setText(String.format(this.getBaseContext().getString(R.string.selected_date), DateFormat.format("dd", tmpDate), DateFormat.format("MM", tmpDate), DateFormat.format("yyyy", tmpDate)));

        calendar_view.setOnDateChangeListener((view, year, month, dayOfMonth) ->
                textViewSelectedDate.setText(String.format(this.getBaseContext().getString(R.string.selected_date), mFormat.format(Double.valueOf(dayOfMonth)), mFormat.format(Double.valueOf(month + 1)), String.valueOf(year))));


        incTask1 = findViewById(R.id.inc_task_1);
        incTask2 = findViewById(R.id.inc_task_2);
        incTask3 = findViewById(R.id.inc_task_3);
    }

    View.OnClickListener radioButtonClickListener = v -> {
        RadioButton rb = (RadioButton) v;
        switch (rb.getId()) {
            case R.id.rb_task_1:
                incTask1.setVisibility(View.VISIBLE);
                incTask2.setVisibility(View.GONE);
                incTask3.setVisibility(View.GONE);
                break;
            case R.id.rb_task_2:
                incTask1.setVisibility(View.GONE);
                incTask2.setVisibility(View.VISIBLE);
                incTask3.setVisibility(View.GONE);
                break;
            case R.id.rb_task_3:
                incTask1.setVisibility(View.GONE);
                incTask2.setVisibility(View.GONE);
                incTask3.setVisibility(View.VISIBLE);
                break;
        }
    };

}