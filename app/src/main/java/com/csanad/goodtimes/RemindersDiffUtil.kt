package com.csanad.goodtimes

import androidx.recyclerview.widget.DiffUtil
import com.csanad.goodtimes.reminders.Reminder

class RemindersDiffUtil(val oldList: List<Reminder>, val newList:List<Reminder>):DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition]===newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition]==newList[newItemPosition]
    }
}