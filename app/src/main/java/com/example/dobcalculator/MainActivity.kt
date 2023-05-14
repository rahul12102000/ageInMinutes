package com.example.dobcalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.time.Year
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {
   private var selectedDate: TextView?=null
   private var ageInMinutes: TextView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnDatePicker=findViewById<Button>(R.id.btnDatePicker)
        selectedDate=findViewById(R.id.textView2)
        ageInMinutes=findViewById(R.id.textView4)
        btnDatePicker.setOnClickListener {
            setDate()
        }
    }
    private fun setDate(){
        val myCalendar=Calendar.getInstance()
        val year=myCalendar.get(Calendar.YEAR)
        val month=myCalendar.get(Calendar.MONTH)
        val day=myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd=DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{_,years,months,dayOfMonth->
                Toast.makeText(this ,"Selected date is: $dayOfMonth/${months+1}/$years ",Toast.LENGTH_LONG).show()
                val dateTextSelected="$dayOfMonth/${months+1}/$years"
                selectedDate?.text=dateTextSelected.toString()
                val sdf=SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                val date=sdf.parse(dateTextSelected)
                date?.let { val selectedDateToMinutes=date.time/60000
                    val currentDate=sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let {
                        val currentDateToMinutes=currentDate.time/60000
                        val differenceInDates=currentDateToMinutes-selectedDateToMinutes
                        ageInMinutes?.text=differenceInDates.toString()
                    }
                }
            },
            year,
            month,
            day
        )
        dpd.datePicker.maxDate=System.currentTimeMillis()-86400000
        dpd.show()
    }
}