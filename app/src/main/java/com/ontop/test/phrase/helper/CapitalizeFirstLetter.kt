package com.ontop.test.phrase.helper

class CapitalizeFirstLetter : IPhraseParser {
    override fun parse(phrase: String): String {
        if (phrase.isEmpty()) return phrase
        var parsedPhrase = ""
        val phraseLength = phrase.length
        var alreadyCapitalized = false
        for (index in 0 until phraseLength) {
            val char = phrase[index]
            when {
                isEmptySpace(char) -> {
                    alreadyCapitalized = false
                    parsedPhrase += char
                }

                isANumberOrSymbol(char) -> {
                    parsedPhrase += char
                }

                isALetter(char) -> {
                    if (alreadyCapitalized) {
                        parsedPhrase += char
                    } else {
                        parsedPhrase += capitalize(char)
                        alreadyCapitalized = true
                    }
                }
            }
        }
        return parsedPhrase

    }


    private fun capitalize(char: Char): Char {
        if (isUpperCase(char)) return char
        return char - 32
    }

    private fun isANumberOrSymbol(char: Char): Boolean {
        return !isEmptySpace(char) && !isALetter(char)
    }

    private fun isEmptySpace(char: Char) = char == ' ' || char == '\n'

    private fun isALetter(char: Char): Boolean {
        return isLowerCase(char) || isUpperCase(char)
    }

    private fun isUpperCase(char: Char) = (char in 'A'..'Z')
    private fun isLowerCase(char: Char) = (char in 'a'..'z')

}