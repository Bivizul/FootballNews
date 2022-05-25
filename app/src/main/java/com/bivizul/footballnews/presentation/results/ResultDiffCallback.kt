package com.bivizul.footballnews.presentation.results

import androidx.recyclerview.widget.DiffUtil
import com.bivizul.footballnews.domain.models.Result

object ResultDiffCallback : DiffUtil.ItemCallback<Result>() {

    // сравниваем, один и тот же объект?
    override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem.name_team == newItem.name_team
    }

    // смотрим на изменение содержимого
    override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem == newItem
    }
}