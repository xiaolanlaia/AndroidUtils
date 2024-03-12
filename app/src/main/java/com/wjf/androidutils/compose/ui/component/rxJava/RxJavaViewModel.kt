package com.wjf.androidutils.compose.ui.component.rxJava

import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2024/3/12 9:57
 *
 */

class RxJavaViewModel : ViewModel() {

    private var observable: Observable<Int>? = null
    private var observer: Observer<Int>? = null
    var observerValue = MutableStateFlow("")
    var chainValue = MutableStateFlow("")

    var disposable: Disposable? = null
    var disposableChain: Disposable? = null

    /**
     * 分步骤实现：创建被观察者
     */
    fun createObservable() {

        //创建被观察者对象
        observable = Observable.create(object : ObservableOnSubscribe<Int>{
            //在复写的subscribe（）里定义需要发送的事件
            override fun subscribe(emitter: ObservableEmitter<Int>) {
                //通过 ObservableEmitter类对象产生事件并通知观察者
                //ObservableEmitter类介绍
                    // a. 定义：事件发射器
                    // b. 作用：定义需要发送的事件 & 向观察者发送事件
                emitter.onNext(1)
                emitter.onNext(2)
                emitter.onNext(3)
                emitter.onComplete()

            }

        })



    }
    /**
     * 分步骤实现：创建观察者
     */
    fun createObserver(){

        //创建观察者，定义响应事件行为
        observer = object : Observer<Int>{
            //开始采用subscribe连接
            override fun onSubscribe(d: Disposable) {
                disposable = d
                observerValue.value = "onSubscribe"

            }

            //对onNext事件做出响应
            override fun onNext(t: Int) {
                observerValue.value = "${observerValue.value} → $t"
            }

            //对onError事件做出响应
            override fun onError(e: Throwable) {
                observerValue.value = "${observerValue.value} \n ${e.message}"

            }

            //对onComplete事件做出响应
            override fun onComplete() {
                observerValue.value = "${observerValue.value} → onComplete"

            }

        }
    }




    /**
     * 分步骤实现：订阅
     */
    fun observer(){
        if (observer != null){
            observable?.subscribe(observer!!)
        }
    }

    /**
     * 分步骤实现：取消订阅
     */
    fun disposable(){
        disposable?.dispose()
    }

    /**
     * 基于事件流的链式调用
     */
    fun chainCall(){

        Observable.create(object : ObservableOnSubscribe<Int>{
            override fun subscribe(emitter: ObservableEmitter<Int>) {
                emitter.onNext(1)
                emitter.onNext(2)
                emitter.onNext(3)
                emitter.onComplete()
            }

        }).subscribe(object : Observer<Int>{
            //开始采用subscribe连接
            override fun onSubscribe(d: Disposable) {
                disposableChain = d
                chainValue.value = "onSubscribe"

            }

            //对onNext事件做出响应
            override fun onNext(t: Int) {
                chainValue.value = "${chainValue.value} → $t"
            }

            //对onError事件做出响应
            override fun onError(e: Throwable) {
                chainValue.value = "${chainValue.value} \n ${e.message}"

            }

            //对onComplete事件做出响应
            override fun onComplete() {
                chainValue.value = "${chainValue.value} → onComplete"

            }
        })

    }

    fun disposableChain(){
        disposableChain?.dispose()
    }
}