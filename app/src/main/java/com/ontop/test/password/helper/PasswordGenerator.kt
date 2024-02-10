package com.ontop.test.password.helper


object PasswordGenerator {


    fun generatePassword(
        length: Int,
        includeUpperCase: Boolean,
        includeNumbers: Boolean,
        includeSymbols: Boolean
    ): String {
        val lowerCaseRange = ('a'..'z')
        var password = "" + lowerCaseRange.random()

        val globalRange = ArrayList<Char>()
        globalRange.addAll(lowerCaseRange)

        val numberRange = ('0'..'9')
        val symbolRange = ('!'..'/')
        val upperCaseRange = ('A'..'Z')

        if (includeNumbers) {
            password += numberRange.random()
            globalRange.addAll(numberRange)
        }
        if (includeSymbols) {
            password += symbolRange.random()
            globalRange.addAll(symbolRange)
        }
        if (includeUpperCase) {
            password += upperCaseRange.random()
            globalRange.addAll(upperCaseRange)
        }
        repeat(length - password.length) {
            password += globalRange.random()
        }
        return password
    }

}
