package com.wjf.androidutils.utils

import android.util.Log

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/11/14 9:51
 *
 */

object ArrayUtils {

    /**
     * byte数组合并
     *
     * System.arraycopy
     *      1、native方法，在native层实现，速度更快
     *      2、只能用于复制原始类型数组
     *
     * arraycopy(Object src, int srcPos, Object dest, int destPos, int length)
     *      src：数据源
     *      srcPos：源数组要起始的位置
     *      dest:目的数组
     *      destPos:目的数组放置的起始位置
     *      length:复制的长度
     *
     */
    fun mergeArray(vararg array: ByteArray): ByteArray{

        var lengthTotal = 0
        array.forEach {
            lengthTotal += it.size
        }
        val mergeArray = ByteArray(lengthTotal)
        var lengthCurrent = 0
        array.forEach {
            System.arraycopy(it, 0, mergeArray, lengthCurrent, it.size)
            lengthCurrent += it.size

        }
        return mergeArray
    }

    /**
     * byte数组合并
     *
     * Arrays.copyOf
     *      1、Java方法，在Java层实现，更通用
     *      2、能复制原始类型和对象数组
     */
    fun mergeArrayObject(vararg array: Array<String>): Array<String>{
        var mergedArray =  array[0]

        for (i in 1 until array.size){
            mergedArray = arrayOf(*mergedArray,*array[i])
        }

        return mergedArray
    }

    /**
     * 截取指定位置范围内数组数组
     */
    fun ByteArray.subArray(startIndex: Int, endIndex: Int): ByteArray{
        val newBytes = copyOfRange(startIndex, endIndex)
        return newBytes
    }

    /**
     * 删除指定位置数组
     * 删除 index = [startIndex,endIndex-1]范围内的数组
     */
    fun ByteArray.deleteArray(startIndex: Int, endIndex: Int): ByteArray{
        val newBytes = ByteArray(size - (endIndex - startIndex)) { 0 }
        System.arraycopy(this, 0, newBytes, 0, startIndex)
        System.arraycopy(this, endIndex, newBytes, startIndex, size - endIndex)
        return newBytes
    }

    /**
     * 查找子数组的起始位置
     */
    fun ByteArray.findSubArray(subArray: ByteArray): Int {
        val n = size
        val m = subArray.size
        for (i in 0 until n-m+1) {
            if (subArray contentEquals sliceArray(i until i+m)) {
                return i
            }
        }
        return -1
    }
}