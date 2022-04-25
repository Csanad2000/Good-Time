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
                        1 -> text += "Mon"
                        2 -> text+="Tue"
                        3->text+="Wed"
                        4->text+="Thu"
                        5->text+="Fri"
                        6->text+="Sat"
                        7->text+="Sun"
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