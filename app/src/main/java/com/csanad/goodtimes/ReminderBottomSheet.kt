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
import kotlin.collections.ArrayList

@AndroidEntryPoint
class ReminderBottomSheet : BottomSheetDialogFragment() {
    lateinit var mainViewModel: MainViewModel
    private var binding: ReminderBottomSheetBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = ReminderBottomSheetBinding.inflate(layoutInflater, container, false)

        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        val todayDate = SimpleDateFormat("dd/MM/yyyy").format(System.currentTimeMillis())
        binding!!.dateEditText.setText(todayDate)
        binding!!.dateEditText.setOnClickListener {
            makeDatePickerDialog()
        }

        binding!!.hourPicker.minValue = 0
        binding!!.hourPicker.maxValue = 23
        binding!!.hourPicker.wrapSelectorWheel = true
        binding!!.hourPicker.value = 6

        binding!!.minutePicker.minValue = 0
        binding!!.minutePicker.maxValue = 59
        binding!!.minutePicker.wrapSelectorWheel = true
        binding!!.minutePicker.value = 30

        binding!!.noChip.setOnCheckedChangeListener { button, b ->
            when (button.isChecked) {
                false -> {
                    binding!!.daysScrollView.visibility = View.VISIBLE
                    binding!!.daysTextView.visibility = View.VISIBLE
                }
                true -> {
                    binding!!.daysScrollView.visibility = View.GONE
                    binding!!.daysTextView.visibility = View.GONE
                }
            }
        }

        binding!!.cancelButton.setOnClickListener {
            dismiss()
        }

        binding!!.applyButton.setOnClickListener {
            if (binding!!.descriptionEditText.text.isNullOrBlank()) {
                Toast.makeText(context, "Please add a description.", Toast.LENGTH_SHORT).show()
            } else {
                val time =
                    SimpleDateFormat("hh").parse(binding!!.hourPicker.value.toString()).time +
                            SimpleDateFormat("mm").parse(binding!!.minutePicker.value.toString()).time

                val date =
                    SimpleDateFormat("dd/MM/yyyy").parse(binding!!.dateEditText.text.toString()).time

                val days=ArrayList<Int>()
                if (!binding!!.noChip.isChecked) {
                    for (chipId in binding!!.daysChipGroup.checkedChipIds) {
                        when (chipId) {
                            binding!!.mondayChip.id -> days.add(1)
                            binding!!.tuesdayChip.id -> days.add(2)
                            binding!!.wednesdayChip.id -> days.add(3)
                            binding!!.thursdayChip.id -> days.add(4)
                            binding!!.fridayChip.id -> days.add(5)
                            binding!!.saturdayChip.id -> days.add(6)
                            binding!!.sundayChip.id -> days.add(7)
                        }
                    }
                }

                var same = false

                for (entity in mainViewModel.readReminders.value!!) {
                    var canConflict=false
                    for (day in days){
                        if (entity.reminder.days.contains(day)){
                            canConflict=true
                            break
                        }
                    }
                    if(days.isEmpty()){
                        canConflict=true
                    }
                    if (canConflict) {
                        var tempFrom = entity.reminder.from
                        var tempDate = date
                        if (tempFrom <= date) {
                            while (tempFrom < date) {
                                tempFrom = getNextDate(tempFrom, days, entity)
                                if (tempFrom == entity.reminder.from) {
                                    break
                                }
                            }
                        } else {
                            while (tempDate < entity.reminder.from) {
                                tempDate = getNextDate(tempDate, days)
                                if (tempDate == date) {
                                    break
                                }
                            }
                        }
                        if (tempFrom == tempDate && entity.reminder.time == time) {
                            same = true
                            break
                        }
                    }
                }

                if (same) {
                    Toast.makeText(
                        context,
                        "You cannot set two alerts for the same point in time.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    val reminder = Reminder(
                        binding!!.repeatingChipGroup.checkedChipId,
                        days,
                        binding!!.descriptionEditText.text!!.toString(),
                        date,
                        time
                    )

                    mainViewModel.insertReminders(RemindersEntity(reminder))
                    dismiss()
                }
            }
        }

        return binding!!.root
    }

    private fun getNextDate(date: Long,days:ArrayList<Int>):Long {
        if (binding!!.noChip.isChecked){
            return date
        }else{
            val oldDate=Date(date)
            val c=Calendar.getInstance()
            c.time=oldDate
            val oldDayOfWeek=c.get(Calendar.DAY_OF_WEEK)

            var newDayOfWeek=0
            for (weekday in days){
                if (weekday>oldDayOfWeek){
                    newDayOfWeek=weekday
                    break
                }
            }
            if (newDayOfWeek==0){
                newDayOfWeek=days.first()
            }

            if(newDayOfWeek<=oldDayOfWeek){
                c.add(Calendar.DATE,7-(oldDayOfWeek-newDayOfWeek))
            }else{
                c.add(Calendar.DATE,(newDayOfWeek-oldDayOfWeek))
            }
            return c.time.time
        }
    }

    private fun getNextDate(date:Long,days: ArrayList<Int>, entity: RemindersEntity):Long{
        if (entity.reminder.repetition==binding!!.noChip.id){
            return date
        }else{
            val oldDate=Date(date)
            val c=Calendar.getInstance()
            c.time=oldDate
            val oldDayOfWeek=c.get(Calendar.DAY_OF_WEEK)

            var newDayOfWeek=0
            for (weekday in entity.reminder.days){
                if (weekday>oldDayOfWeek){
                    newDayOfWeek=weekday
                    break
                }
            }
            if (newDayOfWeek==0){
                newDayOfWeek=entity.reminder.days.first()
            }

            if(newDayOfWeek<=oldDayOfWeek){
                c.add(Calendar.DATE,7-(oldDayOfWeek-newDayOfWeek))
            }else{
                c.add(Calendar.DATE,(newDayOfWeek-oldDayOfWeek))
            }
            return c.time.time
        }
    }

    fun makeDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val currentYear = calendar.get(Calendar.YEAR)
        val currentMonth = calendar.get(Calendar.MONTH)
        val currentDay = calendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this.requireContext(), { view, year, month, day ->
            var date =
                SimpleDateFormat("dd/MM/yyyy").format(GregorianCalendar(year, month, day).time.time)
            binding!!.dateEditText.setText(date)
        }, currentYear, currentMonth, currentDay)

        dpd.datePicker.minDate = System.currentTimeMillis()
        dpd.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}