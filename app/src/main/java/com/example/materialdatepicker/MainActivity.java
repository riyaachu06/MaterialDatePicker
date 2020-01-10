package com.example.materialdatepicker;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.util.Calendar;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {
    private Calendar calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        datePicker();
        dateRangePicker();
    }

    private void datePicker(){
        Button datePickerBtn = findViewById(R.id.datePicker);

        long today = MaterialDatePicker.todayInUtcMilliseconds();

        final MaterialDatePicker materialDatePicker =
                MaterialDatePicker.Builder.datePicker() //date picker -> to select a particular date
                        .setTitleText("Choose a date")
                        .setSelection(today) // Makes today as the default selection
                        .setCalendarConstraints(getCalenderConstraints()) // calender constraints
                        .build();

        datePickerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                materialDatePicker.show(getSupportFragmentManager(),"date picker");
            }
        });

        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) { // Selection return date value in milli seconds
                Toast.makeText(MainActivity.this, materialDatePicker.getHeaderText(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void dateRangePicker() {
        Button rangePickerBtn = findViewById(R.id.dateRangerPicker);
        final MaterialDatePicker materialDatePicker =
                MaterialDatePicker.Builder.dateRangePicker() //date range picker -> select sequence of dates
                        .setTitleText("Choose a date")
                        .build();

        rangePickerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                materialDatePicker.show(getSupportFragmentManager(),"date picker");
            }
        });

        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) { // Selection return date value in milli seconds
                Toast.makeText(MainActivity.this, materialDatePicker.getHeaderText(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private CalendarConstraints getCalenderConstraints () {
        calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.clear();

        calendar.setTimeInMillis(MaterialDatePicker.todayInUtcMilliseconds()); // Sets the year, else it will start from 1970

        calendar.set(Calendar.MONTH, Calendar.MARCH);
        long calenderStarting = calendar.getTimeInMillis();

        calendar.set(Calendar.MONTH, Calendar.DECEMBER);
        long calenderEnd = calendar.getTimeInMillis();

//        calendar.set(Calendar.MONTH, Calendar.FEBRUARY);
//        long calenderOpen = calendar.getTimeInMillis();

        return new CalendarConstraints.Builder()
                .setStart(calenderStarting)
                .setEnd(calenderEnd)
//                .setOpenAt(calenderOpen)
//                .setValidator(DateValidatorPointForward.now())  // Prevents picking older dates , NOTE : we can create custom date validators
                .build();

    }
}
