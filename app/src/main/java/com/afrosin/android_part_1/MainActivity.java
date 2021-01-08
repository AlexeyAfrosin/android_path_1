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

    View inc_task_1;
    View inc_task_2;
    View inc_task_3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RadioButton rb_task_1 = findViewById(R.id.rb_task_1);
        rb_task_1.setOnClickListener(radioButtonClickListener);

        RadioButton rb_task_2 = findViewById(R.id.rb_task_2);
        rb_task_2.setOnClickListener(radioButtonClickListener);

        RadioButton rb_task_3 = findViewById(R.id.rb_task_3);
        rb_task_3.setOnClickListener(radioButtonClickListener);

        TextView textViewSelectedDate = findViewById(R.id.textViewSelectedDate);
        CalendarView calendar_view = findViewById(R.id.calendarView);


        DecimalFormat mFormat = new DecimalFormat("00");

        Date tmp_date = new Date(calendar_view.getDate());
        textViewSelectedDate.setText(String.format(this.getBaseContext().getString(R.string.selected_date), DateFormat.format("dd", tmp_date), DateFormat.format("MM", tmp_date), DateFormat.format("yyyy", tmp_date)));

        calendar_view.setOnDateChangeListener((view, year, month, dayOfMonth) ->
                textViewSelectedDate.setText(String.format(this.getBaseContext().getString(R.string.selected_date), mFormat.format(Double.valueOf(dayOfMonth)), mFormat.format(Double.valueOf(month + 1)), String.valueOf(year))));


        inc_task_1 = findViewById(R.id.inc_task_1);
        inc_task_2 = findViewById(R.id.inc_task_2);
        inc_task_3 = findViewById(R.id.inc_task_3);
    }

    View.OnClickListener radioButtonClickListener = v -> {
        RadioButton rb = (RadioButton) v;
        switch (rb.getId()) {
            case R.id.rb_task_1:
                inc_task_1.setVisibility(View.VISIBLE);
                inc_task_2.setVisibility(View.GONE);
                inc_task_3.setVisibility(View.GONE);
                break;
            case R.id.rb_task_2:
                inc_task_1.setVisibility(View.GONE);
                inc_task_2.setVisibility(View.VISIBLE);
                inc_task_3.setVisibility(View.GONE);
                break;
            case R.id.rb_task_3:
                inc_task_1.setVisibility(View.GONE);
                inc_task_2.setVisibility(View.GONE);
                inc_task_3.setVisibility(View.VISIBLE);
                break;
        }
    };

}