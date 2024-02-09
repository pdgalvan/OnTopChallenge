package com.ontop.test.phrase.presentation

import androidx.lifecycle.ViewModel
import com.ontop.test.phrase.helper.IPhraseParser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class PhraseViewModel @Inject constructor(
    private val parser: IPhraseParser,
) : ViewModel() {
    private val _phrase = MutableStateFlow("")
    val phrase = _phrase.asStateFlow()

    private val _parsedPhrase = MutableStateFlow("")
    val parsedPhrase = _parsedPhrase.asStateFlow()

    fun inputPhrase(phrase: String) {
        _phrase.update { phrase }
    }

    fun parse() {
        _parsedPhrase.update { parser.parse(_phrase.value) }
    }
}