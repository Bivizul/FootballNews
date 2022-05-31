package com.bivizul.footballnews.presentation.teamselect

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import coil.load
import com.bivizul.footballnews.databinding.ItemTeamSelectBinding
import com.bivizul.footballnews.domain.models.TeamInfo

class TeamSelectionAdapter() : ListAdapter<TeamInfo, TeamSelectViewHolder>(TeamSelectDiffCallback) {

    var onTeamItemClickListener: ((TeamInfo) -> Unit)? = null

    // рисуем элемент по которому создается ViewHolder
    // сколько элементов будет,столько раз и нарисуется
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): TeamSelectViewHolder {
        val binding = ItemTeamSelectBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TeamSelectViewHolder(binding)
    }

    // подключаем к элементу текст и т.д.
    override fun onBindViewHolder(holder: TeamSelectViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding) {
            with(item) {
                tvSearchItem.text = name
                imgSearchItem.load(icon)
                root.setOnClickListener {
                    onTeamItemClickListener?.invoke(item)
                }
            }
        }
    }
}