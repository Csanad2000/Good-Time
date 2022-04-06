package com.csanad.goodtimes

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.csanad.goodtimes.databinding.ReminderBottomSheetBinding
import com.csanad.goodtimes.quotes.database.quote.RemindersEntity
import com.csanad.goodtimes.reminders.Reminder
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class ReminderBottomSheet : BottomSheetDialogFragment() {
    lateinit var mainViewModel: MainViewModel
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

        mainViewModel= ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        val todayDate=SimpleDateFormat("dd/MM/yyyy").format(System.currentTimeMillis())
        binding!!.dateEditText.setText(todayDate)
        binding!!.dateEditText.setOnClickListener {
            makeDatePickerDialog()
        }

        binding!!.hourPicker.minValue=0
        binding!!.hourPicker.maxValue=23
        binding!!.hourPicker.wrapSelectorWheel=true
        binding!!.hourPicker.value=6

        binding!!.minutePicker.minValue=0
        binding!!.minutePicker.maxValue=59
        binding!!.minutePicker.wrapSelectorWheel=true
        binding!!.minutePicker.value=30

        binding!!.noChip.setOnCheckedChangeListener { button, b ->
            when(button.isChecked){
                false->{
                    binding!!.daysScrollView.visibility=View.VISIBLE
                    binding!!.daysTextView.visibility=View.VISIBLE
                }
                true->{
                    binding!!.daysScrollView.visibility=View.GONE
                    binding!!.daysTextView.visibility=View.GONE
                }
            }
        }

        binding!!.cancelButton.setOnClickListener {
            dismiss()
        }

        binding!!.applyButton.setOnClickListener {
            if (binding!!.descriptionEditText.text.isNullOrBlank()){
                Toast.makeText(context,"Please add a description.",Toast.LENGTH_SHORT).show()
            }else {
                val reminder= Reminder(binding!!.repeatingChipGroup.checkedChipId,binding!!.daysChipGroup.checkedChipIds,
                binding!!.descriptionEditText.text!!.toString(),binding!!.dateEditText.text.toString(),
                (binding!!.hourPicker.value.toString()+"/"+binding!!.minutePicker.value.toString()))

                mainViewModel.insertReminders(RemindersEntity(reminder))
                dismiss()
            }
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