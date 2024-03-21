package com.alanturing.cpifp.whatsappclone.chat.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alanturing.cpifp.whatsappclone.R
import com.alanturing.cpifp.whatsappclone.databinding.FragmentSelectContactBinding


class SelectContactFragment : Fragment() {
    private lateinit var binding: FragmentSelectContactBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSelectContactBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }
}