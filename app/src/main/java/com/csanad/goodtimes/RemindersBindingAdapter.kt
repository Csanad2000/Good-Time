package com.csanad.goodtimes

import android.text.Editable
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.csanad.goodtimes.reminders.Reminder
import com.google.android.material.textfield.TextInputLayout

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
        fun setDate(textView: TextView,date:String){
            textView.text=date
        }

        @BindingAdapter("setDescription")
        @JvmStatic
        fun setDescription(layout:TextInputLayout, description: Editable?){
            if (!description.isNullOrBlank()){
                layout.editText!!.text=description
                layout.visibility=View.VISIBLE
            }
        }

        @BindingAdapter("setHour")
        @JvmStatic
        fun setHour(textView:TextView,time:String){
            textView.text=time.replaceAfter('/',"").dropLast(1)
        }

        @BindingAdapter("setMinute")
        @JvmStatic
        fun setMinute(textView:TextView,time:String){
            textView.text=time.replaceBefore('/',"").drop(1)
        }
    }
}