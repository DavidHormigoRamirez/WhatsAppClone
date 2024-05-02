package com.alanturing.cpifp.whatsappclone.register.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alanturing.cpifp.whatsappclone.register.data.RegisterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class UiState {
    object started: UiState()
    class success(val User:UserUiState):UiState()
    class error(val error:String):UiState()
    object loading :UiState()
}
@HiltViewModel
class RegisterViewModel @Inject constructor(private val repository: RegisterRepository): ViewModel() {
    private val _user: MutableStateFlow<UiState> = MutableStateFlow(UiState.started)
    val user: StateFlow<UiState>
        get() = _user.asStateFlow()

    fun register(phone:String) {

        // LLamo al metodo del repositorio para registrar un usuario
        viewModelScope.launch {
            _user.value = UiState.loading
            val newUser = repository.register(phone)

            if (newUser != null) {
                _user.value = UiState.success(UserUiState(newUser.phone))
            }
            else {
                _user.value = UiState.error("El usuario ya existe")
            }
        }
    }
}