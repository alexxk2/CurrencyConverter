package com.example.currencyconverter.utils



class EditTextUtils {

    companion object {
        fun decimalLimiter(string: String, MAX_DECIMAL: Int): String {
            var str = string
            if (str[0] == '.') str = "0$str"
            val max = str.length
            var rFinal = ""
            var after = false
            var index = 0
            var up = 0
            var decimal = 0
            var t: Char
            val decimalCount = str.count { ".".contains(it) }
            if (decimalCount > 1)
                return str.dropLast(1)
            while (index < max) {
                t = str[index]
                if (t != '.' && !after) {
                    up++
                } else if (t == '.') {
                    after = true
                } else {
                    decimal++
                    if (decimal > MAX_DECIMAL)
                        return rFinal
                }
                rFinal += t
                index++
            }
            return rFinal
        }
    }
}