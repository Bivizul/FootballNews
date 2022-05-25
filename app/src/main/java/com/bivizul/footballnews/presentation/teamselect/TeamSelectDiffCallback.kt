package com.bivizul.footballnews.presentation.teamselect

import androidx.recyclerview.widget.DiffUtil
import com.bivizul.footballnews.domain.models.TeamInfo

object TeamSelectDiffCallback : DiffUtil.ItemCallback<TeamInfo>() {

    // сравниваем, один и тот же объект?
    override fun areItemsTheSame(oldItem: TeamInfo, newItem: TeamInfo): Boolean {
        return oldItem.name == newItem.name
    }

    // смотрим на изменение содержимого
    override fun areContentsTheSame(oldItem: TeamInfo, newItem: TeamInfo): Boolean {
        return oldItem == newItem
    }
}