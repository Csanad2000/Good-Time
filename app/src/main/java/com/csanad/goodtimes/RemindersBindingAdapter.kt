package com.csanad.goodtimes

import android.text.Editable
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.csanad.goodtimes.reminders.Reminder
import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat

class RemindersBindingAdapter {
    companion object{
        @BindingAdapter("displayDaysOfWeek")
        @JvmStatic
        fun displayDaysOfWeek(textView: TextView, reminder:Reminder){
            var text:String=""
            if (reminder.repetition!=0){
                for (day in reminder.days){
                    if (text.isNotBlank()){
                        text+=", "
                    }
                    when(day){
                        0 -> text += R.string.mon
                        1 -> text+=R.string.tue
                        2->text+=R.string.wed
                        3->text+=R.string.thu
                        4->text+=R.string.fri
                        5->text+=R.string.sat
                        6->text+=R.string.sun
                    }
                }
            }
            textView.text=text
        }

        @BindingAdapter("setDate")
        @JvmStatic
        fun setDate(textView: TextView,date:Long){
            textView.text=SimpleDateFormat("dd/MM/yyyy").format(date)
        }

        @BindingAdapter("setDescription")
        @JvmStatic
        fun setDescription(layout:TextInputLayout, description: String?){
            if (!description.isNullOrBlank()){
                layout.editText!!.setText(description)
                layout.visibility=View.VISIBLE
            }
        }

        @BindingAdapter("setHour")
        @JvmStatic
        fun setHour(textView:TextView,time:Long){
            textView.text=SimpleDateFormat("hh").format(time)
        }

        @BindingAdapter("setMinute")
        @JvmStatic
        fun setMinute(textView:TextView,time:Long){
            textView.text=SimpleDateFormat("mm").format(time)
        }
    }
}