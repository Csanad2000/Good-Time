package com.csanad.goodtimes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.csanad.goodtimes.databinding.ReminderRowBinding
import com.csanad.goodtimes.reminders.Collection
import com.csanad.goodtimes.reminders.Reminder

class RemindersAdapter:RecyclerView.Adapter<RemindersAdapter.MyViewHolder>() {

    var reminder= Collection()

    class MyViewHolder(private val binding: ReminderRowBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(collection:Collection){
            binding.collection=collection
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
        val currentReminder=reminder[position]
        holder.bind(currentReminder)
    }

    override fun getItemCount(): Int {
        return reminder.size
    }

    fun setData(newData:Collection){
        reminder=newData
        notifyDataSetChanged()
    }
}