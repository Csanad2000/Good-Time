package com.csanad.goodtimes

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.csanad.goodtimes.databinding.ReminderBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.*

class ReminderBottomSheet : BottomSheetDialogFragment() {
    private var binding:ReminderBottomSheetBinding?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=ReminderBottomSheetBinding.inflate(layoutInflater,container,false)

        val todayDate=SimpleDateFormat("dd/MM/yyyy").format(System.currentTimeMillis())
        binding!!.dateEditText.setText(todayDate)
        binding!!.dateEditText.setOnClickListener {
            makeDatePickerDialog()
        }

        binding!!.cancelButton.setOnClickListener {
            dismiss()
        }

        binding!!.applyButton.setOnClickListener {
            Toast.makeText(context,"Works",Toast.LENGTH_SHORT).show()
        }

        return binding!!.root
    }

    fun makeDatePickerDialog(){
        val calendar=Calendar.getInstance()
        val currentYear=calendar.get(Calendar.YEAR)
        val currentMonth=calendar.get(Calendar.MONTH)
        val currentDay=calendar.get(Calendar.DAY_OF_MONTH)

        val dpd=DatePickerDialog(this.requireContext(),{view,year,month,day ->
            var date=SimpleDateFormat("dd/MM/yyyy").format(GregorianCalendar(year,month,day).time.time)
            binding!!.dateEditText.setText(date)
        },currentYear,currentMonth,currentDay)

        dpd.datePicker.minDate=System.currentTimeMillis()
        dpd.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding=null
    }
}