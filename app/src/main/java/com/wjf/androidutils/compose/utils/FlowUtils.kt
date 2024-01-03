package com.wjf.androidutils.compose.utils

import com.wjf.moduleutils.CoroutineUtils
import com.wjf.moduleutils.LogUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.takeWhile
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.flow.zip

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2024/1/2 19:22
 *
 */

object FlowUtils {


    /**
     * 冷流
     * 通过 flow 创建
     */
    fun coldFlow(flowValue: (Int) -> Unit): Job {
        LogUtils.d("__FlowUtils-coldFlow-1", "1")
        return CoroutineUtils.instance.launch {
            flow {
                for (i in 1..3) {
                    delay(500)
                    emit(i)
                }
            }.collect { value -> flowValue(value) }
        }
    }

    /**
     * 冷流
     * 通过 asFlow 创建
     */
    fun asFlow(flowValue: (Int) -> Unit): Job {
        return CoroutineUtils.instance.launch {
            (1..3).asFlow()
                .collect { value ->
                    delay(500)
                    flowValue(value)
                }
        }
    }

    /**
     * flowOf:只支持单个值或可变值
     */
    fun coldFlowOf(flowValue: (Int) -> Unit): Job {
        return CoroutineUtils.instance.launch {
            flowOf(1, 2, 2, 3).collect { value ->
                delay(500)
                flowValue(value)
            }
        }
    }

    /**
     * 流程操作符
     * 执行流程: onStart->flow{ ...}->onEach->collect->onCompletion
     */
    fun processOperator(flowValue: (String) -> Unit): Job {
        return CoroutineUtils.instance.launch {
            flow {
                flowValue("emit ->")
                LogUtils.d("__processOperator", "flow")
                emit(1)
            }.onStart {
                flowValue("onStart ->")
                LogUtils.d("__processOperator", "onStart ")
            }.onEach {
                flowValue("onEach${it} ->")
                LogUtils.d("__processOperator", "onEach :${it}")
            }.onCompletion {
                flowValue("onCompletion ->")
                LogUtils.d("__processOperator", "onCompletion")
            }.collect { value ->
                flowValue("collect :${value}")
                LogUtils.d("__processOperator", "collect :${value}")
            }
        }
    }

    /**
     * 异常操作符
     */
    fun catchOperator(flowValue: (String) -> Unit): Job {
        return CoroutineUtils.instance.launch {
            flow {
                LogUtils.d("__catchOperator", "flow")
                flowValue("emit ->")
                emit(1)
                throw NullPointerException("空指针")
            }.onStart {
                flowValue("onStart ->")
                LogUtils.d("__catchOperator", "onStart ")
            }.onEach {
                flowValue("onEach${it} ->")
                LogUtils.d("__catchOperator", "onEach :${it}")
            }.catch { cause ->
                flowValue("catch$cause ->")
                LogUtils.d("__catchOperator", "catch $cause")
                emit(2)
            }.onCompletion {
                flowValue("onCompletion ->")
                LogUtils.d("__catchOperator", "onCompletion")
            }.collect { value ->
                flowValue("collect:${value} ->")
                LogUtils.d("__catchOperator", "collect :${value}")
            }
        }
    }

    /**
     * 转换操作符：map
     * 将要发射的原始数据转换为所需的格式
     */
    fun mapOperator(flowValue: (String) -> Unit): Job {
        return CoroutineUtils.instance.launch {
            (1..3).asFlow()
                .map {
                    "map一下:${it} "
                }
                .collect {
                    flowValue(it)
                }
        }
    }

    /**
     * 转换操作符：transform
     * 跟map相似，
     *  map操作的是发射出去的数据
     *  transform操作的是flow本身
     *
     */
    fun transformOperator(flowValue: (Int) -> Unit): Job {
        return CoroutineUtils.instance.launch {
            (1..3).asFlow()
                .transform {
                    emit(it + 1)
                }
                .collect {
                    flowValue(it)
                }
        }
    }

    /**
     * 转换操作符：take
     * 控制发射数量
     */
    fun takeOperator(flowValue: (Int) -> Unit): Job {
        return CoroutineUtils.instance.launch {
            (1..3).asFlow()
                .take(2)
                .collect {
                    flowValue(it)
                }
        }
    }

    /**
     * 转换操作符：combine
     * 将两个流发射出来的数据结合在一起
     * 以多的数据流为主，超出的数据与少的最后一个数据组合
     */
    fun combineOperator(flowValue: (String) -> Unit): Job {
        return CoroutineUtils.instance.launch {
            val stringFlow = listOf("a", "b").asFlow()
            (1..3).asFlow()
                .combine(stringFlow) { value1, value2 ->
                    "$value1:$value2"
                }
                .collect {
                    flowValue(it)
                }
        }
    }

    /**
     * 转换操作符：zip
     * 将两个流发射出来的数据结合在一起
     * 以少的数据为主
     */
    fun zipOperator(flowValue: (String) -> Unit): Job {
        return CoroutineUtils.instance.launch {
            val stringFlow = listOf("a", "b").asFlow()
            (1..3).asFlow()
                .zip(stringFlow) { value1, value2 ->
                    "$value1:$value2"
                }
                .collect {
                    flowValue(it)
                }
        }
    }

    /**
     * 转换操作符：collectLatest
     * 当存在背压时，只接收最新的数据
     */
    fun collectLatestOperator(flowValue: (String) -> Unit): Job {
        return CoroutineUtils.instance.launch {
            flow {
                var i = 0
                while (i < 3) {
                    emit(i++)
                    // 发射完数据之后延时100毫秒
                    delay(100)
                }
            }
                .collectLatest {
                    LogUtils.d("__FlowUtils-collectLatest-start", "$it \n")
                    flowValue("start collectLatest: $it \n")

                    // 模拟耗时操作500毫秒
                    delay(500)

                    LogUtils.d("__FlowUtils-collectLatest-end", "$it \n")
                    flowValue("end collectLatest: $it \n")
                }

        }
    }

    /**
     * 转换操作符：buffer
     * 当存在背压时，接收所有数据
     */
    fun bufferOperator(flowValue: (String) -> Unit): Job {
        return CoroutineUtils.instance.launch {
            flow {
                var i = 0
                while (i < 3) {
                    emit(i++)
                    // 发射完数据之后延时100毫秒
                    delay(100)
                }
            }
                .onEach {
                    LogUtils.d("__FlowUtils-buffer-onEach", "$it \n")
                }
                .buffer()
                .collect {
                    flowValue("start buffer: $it \n")

                    // 模拟耗时操作500毫秒
                    delay(500)

                    LogUtils.d("__FlowUtils-buffer-end", "$it \n")
                    flowValue("end buffer: $it \n")
                }

        }
    }

    /**
     * 转换操作符：flatMapConcat
     * 转换原始数据流，保证时序
     * 将接收到的每一个数据转换成一个新的流发送出去
     */
    fun flatMapConcatOperator(flowValue: (String) -> Unit): Job {
        return CoroutineUtils.instance.launch {
            flow {
                (3 downTo 1).forEach {
                    delay(it * 200L)
                    emit(it)
                }
            }
                .flatMapConcat {
                    flow {
                        //将原始数据转换成流再次发送
                        emit(it)
                    }
                }
                .collect {
                    flowValue("flatMapConcat $it \n")
                }

        }
    }

    /**
     * 转换操作符：flatMapMerge
     * 转换原始数据流，不保证时序
     * 将接收到的每一个数据转换成一个新的流发送出去
     */
    fun flatMapMergeOperator(flowValue: (String) -> Unit): Job {
        return CoroutineUtils.instance.launch {
            flow {
                (3 downTo 1).forEach {
                    //flatMapMerge不保证时许，延时少的被优先发送
                    delay(it * 300L)
                    emit(it)
                }
            }
                .flatMapMerge {
                    flow {
                        //将原始数据转换成流再次发送
                        emit(it)
                    }
                }
                .collect {
                    flowValue("flatMapConcat $it \n")
                }
        }
    }

    /**
     * 转换操作符：flatMapLatest
     * 只接收原始数据流最新的数据，并将数据转换成另外一个流发送出去
     */
    fun flatMapLatestOperator(flowValue: (String) -> Unit): Job {
        return CoroutineUtils.instance.launch {
            flow {
                (0..3).forEach {
                    delay(100L)
                    emit(it)
                }
            }
                .flatMapLatest  {
                    flow {
                        flowValue("start:$it")
                        delay(300L)
                        flowValue("end:$it")
                        emit(it)
                    }
                }
                .collect {
                    flowValue("collect $it \n")
                }
        }
    }


    /**
     * 转换操作符：flowOn
     * 切换线程
     * flow切换到IO线程
     * collect依旧在default线程
     */
    fun flowOnOperator(flowValue: (Int) -> Unit): Job {
        return CoroutineUtils.instance.launch {
            flow {
                for (i in 1..3) {
                    delay(100)
                    emit(i)
                }
            }.flowOn(Dispatchers.IO)
                .collect { value ->
                    flowValue(value)
                }
        }
    }

    /**
     * 转换操作符：filter
     * 过滤数据
     */
    fun filterOperator(flowValue: (Int) -> Unit): Job {
        return CoroutineUtils.instance.launch {
            (1..3).asFlow()
                .filter {
                    it <= 2
                }
                .collect {
                    flowValue(it)
                }
        }
    }

    /**
     * 转换操作符：takeWhile
     * 过滤数据
     */
    fun takeWhileOperator(flowValue: (Int) -> Unit): Job {
        return CoroutineUtils.instance.launch {
            (1..3).asFlow()
                .takeWhile {
                    it < 2
                }
                .collect {
                    flowValue(it)
                }
        }
    }

    /**
     * 转换操作符：drop
     * 丢弃掉指定的count数量后执行后续的流
     */
    fun dropOperator(flowValue: (Int) -> Unit): Job {
        return CoroutineUtils.instance.launch {
            (1..3).asFlow()
                .drop(2)
                .collect {
                    flowValue(it)
                }
        }
    }


}