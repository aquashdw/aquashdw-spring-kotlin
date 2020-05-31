package me.aquashdw.api.calc

// try calculating bitwise strings without calling to int
class Calculator {
    fun bitwiseAdder(x: String, y: String): String? {
        for(a: Char in x)
            if(!(a == '0' || a == '1'))
                throw UnsupportedOperationException("char other than 0 or 1, left operand")

        for(b: Char in y)
            if(!(b == '0' || b == '1'))
                throw UnsupportedOperationException("char other than 0 or 1, right operand")

        var leftArray: CharArray = x.toCharArray()
        var rightArray: CharArray = y.toCharArray()

        leftArray = leftArray.reversedArray()
        rightArray = leftArray.reversedArray()

        return when(leftArray.size > rightArray.size){
            true -> this.adder(leftArray, rightArray)
            false -> this.adder(rightArray, leftArray)
        }.toString()
    }

    private fun adder(long: CharArray, short: CharArray): CharArray{
        val resultReversed: CharArray = charArrayOf()
        var overflowFlag: Boolean = false
        var index: Int = 0

        while(index < short.size){
            val containsLong: Boolean = long[index] == '1'
            val containsShort: Boolean = short[index] == '1'
            // 1, 1, no overflow
            if(containsLong && containsShort && !overflowFlag){
                resultReversed[index] = '0'
                overflowFlag = true
            }
            // 1, 1, overflow
            else if (containsLong && containsShort && overflowFlag){
                resultReversed[index] = '1'
                overflowFlag = true
            }
            // 1, 0, no overflow
            else if ((containsLong || containsShort) && !overflowFlag){
                resultReversed[index] = '1'
                overflowFlag = false
            }
            // 1, 0, overflow
            else if ((containsLong || containsShort) && overflowFlag){
                resultReversed[index] = '0'
                overflowFlag = true
            }
            // 0, 0, no overflow
            else if (!(containsLong || containsShort) && !overflowFlag){
                resultReversed[index] = '0'
                overflowFlag = false
            }
            // o, o, overflow
            else{
                resultReversed[index] = '1'
                overflowFlag = false
            }

            index++
        }

        while (index < long.size && overflowFlag) {
            if(long[index] == '1'){
                resultReversed[index] = '0'
            } else {
                resultReversed[index] = '1'
                overflowFlag = false
                break
            }
        }

        when (index < long.size){
            true -> {
                while (index < long.size){
                    resultReversed[index] = long[index]
                    index++
                }
            }
            false -> {
                if(overflowFlag) resultReversed[index + 1] = '1'
            }
        }

        return resultReversed.reversedArray()
    }
}
