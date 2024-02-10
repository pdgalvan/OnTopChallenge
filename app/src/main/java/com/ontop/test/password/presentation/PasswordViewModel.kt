package com.ontop.test.password.presentation

import androidx.lifecycle.ViewModel
import com.ontop.test.password.helper.Constants.PASSWORD_MIN_LENGTH
import com.ontop.test.password.helper.PasswordGenerator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class PasswordViewModel @Inject constructor() : ViewModel() {

    private val _viewState = MutableStateFlow(PasswordViewState())
    val viewState = _viewState.asStateFlow()

    fun generatePassword() {
        _viewState.update {
            it.copy(
                password = PasswordGenerator.generatePassword(
                    length = it.length,
                    includeUpperCase = it.includeUpperCase,
                    includeNumbers = it.includeNumbers,
                    includeSymbols = it.includeSymbols,
                )
            )
        }
    }

    fun changeLength(length: Int) {
        _viewState.update {
            it.copy(
                length = length,
            )
        }
    }

    fun changeUpperCaseStatus(include: Boolean) {
        _viewState.update {
            it.copy(
                includeUpperCase = include,
            )
        }
    }

    fun changeNumbersStatus(include: Boolean) {
        _viewState.update {
            it.copy(
                includeNumbers = include,
            )
        }
    }

    fun changeSymbolsStatus(include: Boolean) {
        _viewState.update {
            it.copy(
                includeSymbols = include,
            )
        }
    }

}

data class PasswordViewState(
    val password: String = "",
    val length: Int = PASSWORD_MIN_LENGTH,
    val includeUpperCase: Boolean = false,
    val includeNumbers: Boolean = false,
    val includeSymbols: Boolean = false,
)