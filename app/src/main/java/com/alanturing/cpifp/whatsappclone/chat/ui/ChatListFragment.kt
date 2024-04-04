package com.alanturing.cpifp.whatsappclone.chat.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.alanturing.cpifp.whatsappclone.chat.data.Chat
import com.alanturing.cpifp.whatsappclone.chat.data.ChatRepository
import com.alanturing.cpifp.whatsappclone.databinding.FragmentChatListBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ChatListFragment : Fragment() {
    private lateinit var binding: FragmentChatListBinding
    @Inject lateinit var chatRepository:ChatRepository
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

       binding = FragmentChatListBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val createChatButton = binding.createChatButton
        binding.chatList.layoutManager = LinearLayoutManager(context)

        val listAdapter = ChatListAdapter(::toChat)
        val repo = chatRepository

        listAdapter.submitList(repo.chats)
        binding.chatList.adapter = listAdapter

        createChatButton.setOnClickListener {
            val action = ChatListFragmentDirections.actionChatListFragmentToSelectContactFragment()
            findNavController().navigate(action)
        }

    }

    private fun toChat(view: View,chat: Chat) {
        val action = ChatListFragmentDirections.actionChatListFragmentToChatFragment(chat.to)
        findNavController().navigate(action)
    }

}