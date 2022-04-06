package com.csanad.goodtimes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.csanad.goodtimes.databinding.ReminderRowBinding
import com.csanad.goodtimes.quotes.database.quote.RemindersEntity
import com.csanad.goodtimes.reminders.Collection
import com.csanad.goodtimes.reminders.Reminder

class RemindersAdapter:RecyclerView.Adapter<RemindersAdapter.MyViewHolder>() {

    var reminders= emptyList<RemindersEntity>()

    class MyViewHolder(private val binding: ReminderRowBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(reminder: Reminder){
            binding.reminder=reminder
            binding.executePendingBindings()
        }

        companion object{
            fun from(parent: ViewGroup):MyViewHolder{
                val layoutInflater=LayoutInflater.from(parent.context)
                val binding=ReminderRowBinding.inflate(layoutInflater,parent,false)
                return MyViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentReminder=reminders[position]
        holder.bind(currentReminder.reminder)
    }

    override fun getItemCount(): Int {
        return reminders.size
    }

    fun setData(newData:List<RemindersEntity>){
        val remindersDiffUtil=RemindersDiffUtil(reminders,newData)
        val diffUtilResult=DiffUtil.calculateDiff(remindersDiffUtil)
        reminders=newData
        diffUtilResult.dispatchUpdatesTo(this)
    }
}