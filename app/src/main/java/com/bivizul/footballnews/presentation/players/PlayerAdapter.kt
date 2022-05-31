package com.bivizul.footballnews.presentation.players

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import coil.load
import com.bivizul.footballnews.databinding.ItemPlayerBinding
import com.bivizul.footballnews.domain.models.Player
import com.bivizul.footballnews.utils.Constants.DEFAULT_PLAYER

class PlayerAdapter() : ListAdapter<Player, PlayerViewHolder>(PlayerDiffCallback) {

    // рисуем элемент по которому создается ViewHolder
    // сколько элементов будет,столько раз и нарисуется
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): PlayerViewHolder {
        val binding = ItemPlayerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PlayerViewHolder(binding)
    }

    // подключаем к элементу текст и т.д.
    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding) {
            with(item) {
                tvName.text = name
                tvType.text = type
                tvCountry.text = country
                imgFlag.load(flag)
                if (photo.isNotEmpty()) {
                    imgPlayer.load(photo)
                } else {
                    imgPlayer.load(DEFAULT_PLAYER)
                }
            }
        }
    }
}