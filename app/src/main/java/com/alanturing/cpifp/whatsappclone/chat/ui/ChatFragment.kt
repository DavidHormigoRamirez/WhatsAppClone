package com.alanturing.cpifp.whatsappclone.chat.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.alanturing.cpifp.whatsappclone.chat.data.Contact
import com.alanturing.cpifp.whatsappclone.chat.data.ContactRepository
import com.alanturing.cpifp.whatsappclone.chat.data.MessageRepository
import com.alanturing.cpifp.whatsappclone.databinding.FragmentChatBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ChatFragment : Fragment() {

    private lateinit var binding: FragmentChatBinding
    private val arguments: ChatFragmentArgs by navArgs()
    @Inject
    lateinit var contactRepository:ContactRepository

    @Inject
    lateinit var  messageRepository: MessageRepository
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChatBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }
    private fun sendMessage() {
        val rv = binding.messageList
        val text = binding.newTextInput.text.toString()

        if (text.isNotBlank()) {
            messageRepository.addMessage(text,false,arguments.id)
            val adapter:MessageListAdapter = rv.adapter as MessageListAdapter
            adapter.submitList(messageRepository.message.filter {
                it.id == arguments.id
            })
            binding.newTextInput.text?.clear()
        }

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = arguments.id

        val contact: Contact? = contactRepository.contacts.find {
            it.id == id
        }

        contact?.let { binding.contactName.text = "${it.name} ${it.surname}" }

        val adapter = MessageListAdapter()
        val rv = binding.messageList
        rv.layoutManager = LinearLayoutManager(context)
        adapter.submitList(messageRepository.message.filter {
            it.id == id
        })
        rv.adapter = adapter

        binding.sendMesage.setOnClickListener {
            sendMessage()
        }
    }
}