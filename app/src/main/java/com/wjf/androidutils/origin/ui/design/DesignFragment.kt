package com.wjf.androidutils.origin.ui.design

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.wjf.androidutils.databinding.LayoutRecyclerBinding
import com.wjf.androidutils.origin.base.MVVMBaseFragment
import com.wjf.androidutils.origin.ui.design.adapter.DesignAdapter
import com.wjf.androidutils.origin.ui.home.HomeViewModel
import java.util.LinkedList


/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/11/29 10:04
 *
 */

const val DESIGN_BUILDER            = "建造者模式"
const val DESIGN_CLONE              = "原型模式"
const val DESIGN_FACTORY_SIMPLE     = "简单工厂"
const val DESIGN_FACTORY_METHOD     = "工厂方法"
const val DESIGN_FACTORY_ABSTRACT   = "抽象工厂"
const val DESIGN_OBSERVER           = "观察者模式"
const val DESIGN_MEMO               = "备忘录模式"
const val DESIGN_INTERPRETER        = "解释器模式"
const val DESIGN_COMMAND            = "命令模式"
const val DESIGN_STRATEGY           = "策略模式"
const val DESIGN_VISITOR            = "访问者模式"
const val DESIGN_STATE              = "状态模式"
const val DESIGN_MEDIATOR           = "中介者模式"
const val DESIGN_TEMPLATE           = "模板方法模式"
const val DESIGN_ITERATOR           = "迭代器模式"
const val DESIGN_CHAIN              = "责任链模式"
const val DESIGN_BRIDGE             = "桥接模式"
const val DESIGN_FACED              = "外观模式"
const val DESIGN_COMBINE            = "组合模式"
const val DESIGN_DECORATE           = "装饰者模式"
const val DESIGN_DECORATE_2         = "装饰者模式2"
const val DESIGN_ADAPTER            = "适配器模式"
const val DESIGN_ADAPTER_2          = "适配器模式2"
const val DESIGN_PROXY              = "代理模式"
const val DESIGN_PROXY_DYNAMIC      = "动态代理模式"
const val DESIGN_FLYWEIGHT          = "享元模式"

val designList = LinkedList<String>().apply {
    add(DESIGN_BUILDER)
    add(DESIGN_CLONE)
    add(DESIGN_FACTORY_SIMPLE)
    add(DESIGN_FACTORY_METHOD)
    add(DESIGN_FACTORY_ABSTRACT)
    add(DESIGN_OBSERVER)
    add(DESIGN_MEMO)
    add(DESIGN_INTERPRETER)
    add(DESIGN_COMMAND)
    add(DESIGN_STRATEGY)
    add(DESIGN_VISITOR)
    add(DESIGN_STATE)
    add(DESIGN_MEDIATOR)
    add(DESIGN_TEMPLATE)
    add(DESIGN_ITERATOR)
    add(DESIGN_CHAIN)
    add(DESIGN_BRIDGE)
    add(DESIGN_FACED)
    add(DESIGN_COMBINE)
    add(DESIGN_DECORATE)
    add(DESIGN_DECORATE_2)
    add(DESIGN_ADAPTER)
    add(DESIGN_ADAPTER_2)
    add(DESIGN_PROXY)
    add(DESIGN_PROXY_DYNAMIC)
    add(DESIGN_FLYWEIGHT)
}

class DesignFragment : MVVMBaseFragment<HomeViewModel, LayoutRecyclerBinding>() {

    override fun initViewModel() = ViewModelProviders.of(this).get(HomeViewModel::class.java)

    override fun initViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        LayoutRecyclerBinding.inflate(inflater,container,false)


    override fun initView() {
        binding.rvWidget.layoutManager = GridLayoutManager(this.context,4)
        binding.rvWidget.adapter = DesignAdapter(designList)
    }
}