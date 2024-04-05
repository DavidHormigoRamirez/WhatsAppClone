package com.alanturing.cpifp.whatsappclone.chat.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.alanturing.cpifp.whatsappclone.chat.data.Message
import com.alanturing.cpifp.whatsappclone.databinding.MessageCardBinding

class MessageListAdapter():ListAdapter<Message, MessageListAdapter.MessageListItemViewHolder>(MessageDiff) {

    inner class MessageListItemViewHolder(private val binding: MessageCardBinding): ViewHolder(binding.root) {

        fun bindTo(message:Message) {
            binding.messageText.text = message.text
            binding.messageTime.text = message.instant.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageListItemViewHolder {
        val binding = MessageCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MessageListItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MessageListItemViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    object MessageDiff: DiffUtil.ItemCallback<Message>() {
        override fun areItemsTheSame(oldItem: Message, newItem: Message):Boolean {
            return ((oldItem.id==newItem.id)&&(oldItem.instant.epochSeconds==newItem.instant.epochSeconds))
        }

        override fun areContentsTheSame(oldItem: Message, newItem: Message) = oldItem == newItem

    }


}