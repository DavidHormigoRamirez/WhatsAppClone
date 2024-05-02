package com.alanturing.cpifp.whatsappclone.chat.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
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
    private fun sendMessage(view:View) {
        // Recupero el RecyclerView
        val rv = binding.messageList
        // Obtengo el texto del input field
        val text = binding.newTextInput.text.toString()

        if (text.isNotBlank()) {
            // Añado al repositorio el nuevo mensaje
            messageRepository.addMessage(text,false,arguments.id)
            // Recupero el adapter asociado al RecyclerView
            val adapter:MessageListAdapter = rv.adapter as MessageListAdapter
            /*adapter.submitList(messageRepository.message.filter {
                it.id == arguments.id
            })*/

            adapter.submitList(messageRepository.getMessage((arguments.id)))

            binding.newTextInput.text?.clear()

            // Cerramos el teclado
            val inputMethodManager = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
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
            sendMessage(it)
        }
    }
}