package com.bivizul.footballnews.presentation.players

import androidx.recyclerview.widget.DiffUtil
import com.bivizul.footballnews.domain.models.Player

object PlayerDiffCallback : DiffUtil.ItemCallback<Player>() {

    // сравниваем, один и тот же объект?
    override fun areItemsTheSame(oldItem: Player, newItem: Player): Boolean {
        return oldItem.name == newItem.name
    }

    // смотрим на изменение содержимого
    override fun areContentsTheSame(oldItem: Player, newItem: Player): Boolean {
        return oldItem == newItem
    }
}