package com.wjf.androidutils.utils


import org.junit.Before
import org.junit.Test
import com.google.common.truth.Truth.assertThat
import com.wjf.androidutils.utils.ArrayUtils.deleteArray
import com.wjf.androidutils.utils.ArrayUtils.findSubArray
import com.wjf.androidutils.utils.ArrayUtils.subArray

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/3 13:08
 */

class ArrayUtilsTest {
    lateinit var byteArray: ByteArray
    lateinit var byteArray1: ByteArray
    lateinit var byteArray2: ByteArray
    lateinit var byteArray3: ByteArray

    lateinit var strArr : Array<String>
    lateinit var strArr1 : Array<String>
    lateinit var strArr2 : Array<String>

    @Before
    fun setUp() {

        byteArray =  byteArrayOf(1,2,3,2,3,2,3,4,5,4,5,2,3,3)
        byteArray1 = byteArrayOf(1,2,3,2,3,2)
        byteArray2 = byteArrayOf(3,4,5,4,5,2,3,3)
        byteArray3 = byteArrayOf(2,3)

        strArr =  arrayOf("1","2","3","2","3","2","3","4","5","4","5","2","3","3")
        strArr1 = arrayOf("1","2","3","2","3","2")
        strArr2 = arrayOf("3","4","5","4","5","2","3","3")
    }


    @Test
    fun `test mergeArray`(){
        assertThat(ArrayUtils.mergeArray(byteArray1,byteArray2)).isEqualTo(byteArray)
    }

    @Test
    fun `test mergeArrayObject`() {
        assertThat(ArrayUtils.mergeArrayObject(strArr1,strArr2)).isEqualTo(strArr)
    }

    @Test
    fun `test splitByteArray`() {
        val split = ArrayUtils.splitByteArray(byteArray,3)
        split.forEach {
            print(it.contentToString())
        }
    }

    @Test
    fun `test splitByteArray2`() {
        val split = ArrayUtils.splitByteArray2(byteArray,3)
        split.forEach {
            print(it.contentToString())
        }
    }

    @Test
    fun `test subArray`() {
        assertThat(byteArray.subArray(0,6)).isEqualTo(byteArray1)
    }

    @Test
    fun `test deleteArray`() {
        assertThat(byteArray.deleteArray(0,6)).isEqualTo(byteArray2)
    }

    @Test
    fun `test findSubArray`() {
        assertThat(byteArray.findSubArray(byteArray3)).isEqualTo(1)
        assertThat(byteArray.findSubArray(byteArray3,true)).isEqualTo(11)
    }

}