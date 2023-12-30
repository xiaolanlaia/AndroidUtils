package com.wjf.androidutils.origin.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.wjf.androidutils.R
import com.wjf.androidutils.databinding.FragmentDesignBinding
import com.wjf.androidutils.origin.base.MVVMBaseFragment
import com.wjf.androidutils.origin.ui.home.HomeViewModel
import com.wjf.moduledesignpattern.actionType.chain.InterceptorOne
import com.wjf.moduledesignpattern.actionType.chain.InterceptorTwo
import com.wjf.moduledesignpattern.actionType.chain.RealInterceptorChain
import com.wjf.moduledesignpattern.actionType.command.learn.ConcreteCommand
import com.wjf.moduledesignpattern.actionType.command.learn.Invoker
import com.wjf.moduledesignpattern.actionType.command.learn.Receiver
import com.wjf.moduledesignpattern.actionType.interpreter.InterpreterPattern
import com.wjf.moduledesignpattern.actionType.iterator.ConcreteAggregate
import com.wjf.moduledesignpattern.actionType.iterator.entity.Book
import com.wjf.moduledesignpattern.actionType.mediator.ConcreteColleagueA
import com.wjf.moduledesignpattern.actionType.mediator.ConcreteColleagueB
import com.wjf.moduledesignpattern.actionType.memoto.CallOfDuty
import com.wjf.moduledesignpattern.actionType.memoto.Careteker
import com.wjf.moduledesignpattern.actionType.observer.Coder
import com.wjf.moduledesignpattern.actionType.observer.DevTechFrontier
import com.wjf.moduledesignpattern.actionType.state.TvController
import com.wjf.moduledesignpattern.actionType.strategy.learn.BusStrategy
import com.wjf.moduledesignpattern.actionType.strategy.learn.CarStrategy
import com.wjf.moduledesignpattern.actionType.strategy.learn.TrafficCalculator
import com.wjf.moduledesignpattern.actionType.template.AbstractComputer
import com.wjf.moduledesignpattern.actionType.template.CoderComputer
import com.wjf.moduledesignpattern.actionType.template.MilitaryComputer
import com.wjf.moduledesignpattern.actionType.visitor.learn.BusinessReport
import com.wjf.moduledesignpattern.actionType.visitor.learn.CEOVisitor
import com.wjf.moduledesignpattern.actionType.visitor.learn.CTOVisitor
import com.wjf.moduledesignpattern.createType.builder.example.MilkTea
import com.wjf.moduledesignpattern.createType.builder.example.MilkTeaBuilder
import com.wjf.moduledesignpattern.createType.clone.WordDocument
import com.wjf.moduledesignpattern.createType.factory.abs.factory.CompleteLargeShoeFactory
import com.wjf.moduledesignpattern.createType.factory.method.factory.MethodGameAFactory
import com.wjf.moduledesignpattern.createType.factory.simple.GameFactory
import com.wjf.moduledesignpattern.structureType.adapter.example.AudioPlayer
import com.wjf.moduledesignpattern.structureType.adapter.learn.VoltAdapter
import com.wjf.moduledesignpattern.structureType.bridge.example.CircleBridge
import com.wjf.moduledesignpattern.structureType.bridge.example.GreenCircle
import com.wjf.moduledesignpattern.structureType.bridge.example.RedCircle
import com.wjf.moduledesignpattern.structureType.bridge.example.ShapeBridge
import com.wjf.moduledesignpattern.structureType.composite.Composite
import com.wjf.moduledesignpattern.structureType.composite.Leaf
import com.wjf.moduledesignpattern.structureType.decorator.example.Circle
import com.wjf.moduledesignpattern.structureType.decorator.example.Rectangle
import com.wjf.moduledesignpattern.structureType.decorator.example.RedShapedDecorator
import com.wjf.moduledesignpattern.structureType.decorator.learn.ConcreteComponent
import com.wjf.moduledesignpattern.structureType.decorator.learn.ConcreteDecoratorA
import com.wjf.moduledesignpattern.structureType.faced.MobilePhone
import com.wjf.moduledesignpattern.structureType.flyweight.Ticket
import com.wjf.moduledesignpattern.structureType.flyweight.TicketFactory
import com.wjf.moduledesignpattern.structureType.proxy.ProxySubject
import com.wjf.moduledesignpattern.structureType.proxy.RealSubject
import com.wjf.moduleutils.LogUtils


/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/11/29 10:04
 *
 */

class DesignFragment : MVVMBaseFragment<HomeViewModel, FragmentDesignBinding>(), View.OnClickListener {

    override fun initViewModel() = ViewModelProviders.of(this).get(HomeViewModel::class.java)

    override fun initViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentDesignBinding {
        return FragmentDesignBinding.inflate(inflater,container,false)
    }


    override fun initView() {
        binding.builderPattern.setOnClickListener(this)
        binding.clonePattern.setOnClickListener(this)
        binding.factorySimple.setOnClickListener(this)
        binding.methodFactory.setOnClickListener(this)
        binding.abstractFactory.setOnClickListener(this)
        binding.strategyPattern.setOnClickListener(this)
        binding.statePattern.setOnClickListener(this)
        binding.chainPattern.setOnClickListener(this)
        binding.interpreterPattern.setOnClickListener(this)
        binding.commandPattern.setOnClickListener(this)
        binding.observerPattern.setOnClickListener(this)
        binding.memotoPattern.setOnClickListener(this)
        binding.templatePattern.setOnClickListener(this)
        binding.visitorPattern.setOnClickListener(this)
        binding.mediatorPattern.setOnClickListener(this)
        binding.proxyPattern.setOnClickListener(this)
        binding.compositePattern.setOnClickListener(this)
        binding.adapterPattern.setOnClickListener(this)
        binding.adapterPatternExample.setOnClickListener(this)
        binding.chainIterator.setOnClickListener(this)
        binding.decoratePattern.setOnClickListener(this)
        binding.decoratePatternExample.setOnClickListener(this)
        binding.flyweightPattern.setOnClickListener(this)
        binding.facedPattern.setOnClickListener(this)
        binding.facedPatternExample.setOnClickListener(this)
        binding.bridgePattern.setOnClickListener(this)
        binding.bridgePatternExample.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.builder_pattern -> {
                val milkTea: MilkTea = MilkTeaBuilder().type("").size("").build()
            }

            R.id.clone_pattern -> {
                val originDocument = WordDocument()
                originDocument.setmText("originDocument 文档")
                originDocument.addImage("图片 1")
                originDocument.addImage("图片 2")
                originDocument.addImage("图片 3")
                originDocument.showDocument()

                //拷贝一份文档
                val doc2: WordDocument = originDocument.clone() as WordDocument
                doc2.showDocument()
                doc2.setmText("doc 2 文档")
                doc2.addIndex(100)
                doc2.addIndex(200)
                doc2.showDocument()
                originDocument.showDocument()
                val arrayList = ArrayList<String>()
                val newStr = arrayList.clone() as ArrayList<String>
            }

            R.id.factory_simple -> {
                val factory = GameFactory()
                //不应该直接传入参数，使用配置文件能够适应变化
                val game = factory.createGame(GameFactory.gameType)
                binding.factorySimple.text = game.play()
            }

            R.id.method_factory -> {

                val methodGameFactory = MethodGameAFactory()
                val methodGameA = methodGameFactory.createGame()
                binding.methodFactory.text = methodGameA.play()
            }

            R.id.abstract_factory -> {
                val factory = CompleteLargeShoeFactory()
                //大号的鞋子工厂所以创建的鞋子和鞋垫都是大号的
                factory.createShoe()
                factory.createInsole()
            }

            R.id.strategy_pattern -> {
                val calculator = TrafficCalculator()
                Thread {

                    Log.d("__price-bus", "" + calculator.setCalculateStrategy(BusStrategy()).calculatePrice(16))
                }.start()
                Thread {

                    Log.d("__price-car", "" + calculator.setCalculateStrategy(CarStrategy()).calculatePrice(16))
                }.start()
            }

            R.id.state_pattern -> {
                val tvController = TvController()
                tvController.powerOn()
                tvController.nextChannel()
                tvController.powerOff()
                tvController.nextChannel()
            }

            R.id.chain_pattern -> {
                val leaderManager =
                    RealInterceptorChain() //传递下发任务可执行等级
                leaderManager.addInterceptor(InterceptorOne())
                leaderManager.addInterceptor(InterceptorTwo())
                val result: String = leaderManager.proceed("开始请求了") //开始执行
                Log.d("最后的结果是：", result)
            }

            R.id.interpreter_pattern -> {
                val isMale = InterpreterPattern.getMaleExpression()
                val isMarriedWoman = InterpreterPattern.getMarriedWomanExpression()
                Log.d("__expression", "John is male? " + isMale.interpret("John"))
                Log.d(
                    "__expression",
                    "Julie is a married women?  " + isMarriedWoman.interpret("Married Julie")
                )
            }

            R.id.command_pattern -> {
                val receiver = Receiver()
                val command = ConcreteCommand(receiver)
                val invoker = Invoker(command)
                invoker.action()
                invoker.undo()
            }

            R.id.observer_pattern -> {
                val devTechFrontier = DevTechFrontier()
                val coder1 = Coder("coder1")
                val coder2 = Coder("coder2")
                val coder3 = Coder("coder3")
                devTechFrontier.addObserver(coder1)
                devTechFrontier.addObserver(coder2)
                devTechFrontier.addObserver(coder3)
                devTechFrontier.postNewPublication("发布了新消息")
            }

            R.id.memoto_pattern -> {
                val game = CallOfDuty()
                game.play()
                val careteker = Careteker()
                careteker.archive(game.createMemoto())
                game.quit()
                val newGame = CallOfDuty()
                newGame.restore(careteker.getMemoto())
            }

            R.id.template_pattern -> {
                var abstractComputer: AbstractComputer = CoderComputer()
                abstractComputer.startUp()
                abstractComputer = MilitaryComputer()
                abstractComputer.startUp()
            }

            R.id.visitor_pattern -> {
                val businessReport = BusinessReport()
                Log.d("__businessReport-CEO", "-----------------")
                businessReport.showReport(CEOVisitor())
                Log.d("__businessReport-CTO", "-----------------")
                businessReport.showReport(CTOVisitor())
            }

            R.id.mediator_pattern -> {
                val c1 = ConcreteColleagueA()
                val c2 = ConcreteColleagueB()
                c1.send()
                println("-------------")
                c2.send()
            }

            R.id.chain_iterator -> {
                val concreteAggregate = ConcreteAggregate<Book>()
                concreteAggregate.add(Book(bookName = "bookName"))
                concreteAggregate.add(Book(bookName = "bookName", price = "price"))
                concreteAggregate.add(Book(bookName = "bookName", price = "price", author = "author"))

                val iterator = concreteAggregate.iterator()
                while (iterator.hasNext()){
                    val book = iterator.next()
                    LogUtils.d("__Book",book.toString())
                }

            }
            R.id.proxy_pattern -> {
                val realSubject = RealSubject()
                val proxySubject = ProxySubject(realSubject)
                proxySubject.visit()
            }

            R.id.composite_pattern -> {
                val root = Composite("Root")
                val branch1 = Composite("Branch1")
                val branch2 = Composite("branch2")
                val leaf1 = Leaf("Leaf1")
                val leaf2 = Leaf("leaf2")
                branch1.addChild(leaf1)
                branch2.addChild(leaf2)
                root.addChild(branch1)
                root.addChild(branch2)
                root.doSomething()
            }

            R.id.adapter_pattern -> {
                val adapter = VoltAdapter()
                Log.d("__adapter", "" + adapter.getVolt5())
            }

            R.id.adapter_pattern_example -> {
                val audioPlayer = AudioPlayer()
                audioPlayer.play("mp3", "beyond the horizon.mp3")
                audioPlayer.play("mp4", "alone.mp4")
                audioPlayer.play("vlc", "far far away.vlc")
                audioPlayer.play("avi", "mind me.avi")
            }

            R.id.decorate_pattern -> {
                val component = ConcreteComponent()
                val decorator = ConcreteDecoratorA(component)
                decorator.operate()
            }

            R.id.decorate_pattern_example -> {
                val circle = Circle()
                val redCircle = RedShapedDecorator(Circle())
                val redRectangle = RedShapedDecorator(Rectangle())
                println("Circle with normal border")
                circle.draw()
                println("\nCircle of red border")
                redCircle.draw()
                println("\nRectangle of red border")
                redRectangle.draw()
            }

            R.id.flyweight_pattern -> {
                val ticket: Ticket = TicketFactory.getTicket("北京", "青岛")
                ticket.showTicketInfo("上铺")
                val ticket1: Ticket = TicketFactory.getTicket("北京", "青岛")
                ticket1.showTicketInfo("下铺")
                val ticket2: Ticket = TicketFactory.getTicket("北京", "青岛")
                ticket2.showTicketInfo("坐票")
            }

            R.id.faced_pattern -> {
                val mobilePhone = MobilePhone()
                mobilePhone.takePicture()
                mobilePhone.videoChat()
            }

            R.id.faced_pattern_example -> {}
            R.id.bridge_pattern -> {}
            R.id.bridge_pattern_example -> {
                val redCircle: ShapeBridge = CircleBridge(100, 100, 10, RedCircle())
                val greenCircle: ShapeBridge = CircleBridge(100, 100, 10, GreenCircle())
                redCircle.draw()
                greenCircle.draw()
            }
        }
    }
}