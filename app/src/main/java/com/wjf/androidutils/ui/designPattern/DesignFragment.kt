package com.wjf.androidutils.ui.designPattern

import android.util.Log
import android.view.View
import android.widget.Button
import androidx.lifecycle.ViewModelProviders
import com.wjf.androidutils.R
import com.wjf.androidutils.base.MVVMBaseFragment
import com.wjf.moduledesignpattern.actionType.StragetyPattern.learn.BusStrategy
import com.wjf.moduledesignpattern.actionType.StragetyPattern.learn.CarStrategy
import com.wjf.moduledesignpattern.actionType.StragetyPattern.learn.TrafficCalculator
import com.wjf.moduledesignpattern.actionType.chainPattern.learn.ConcreteHandler1
import com.wjf.moduledesignpattern.actionType.chainPattern.learn.ConcreteHandler2
import com.wjf.moduledesignpattern.actionType.chainPattern.okHttpChain.InterceptorOne
import com.wjf.moduledesignpattern.actionType.chainPattern.okHttpChain.InterceptorTwo
import com.wjf.moduledesignpattern.actionType.chainPattern.okHttpChain.RealInterceptorChain
import com.wjf.moduledesignpattern.actionType.commandPattern.exam.Broker
import com.wjf.moduledesignpattern.actionType.commandPattern.exam.BuyStock
import com.wjf.moduledesignpattern.actionType.commandPattern.exam.ShellStock
import com.wjf.moduledesignpattern.actionType.commandPattern.exam.Stock
import com.wjf.moduledesignpattern.actionType.commandPattern.learn.ConcreteCommand
import com.wjf.moduledesignpattern.actionType.commandPattern.learn.Invoker
import com.wjf.moduledesignpattern.actionType.commandPattern.learn.Receiver
import com.wjf.moduledesignpattern.actionType.interpreterPattern.InterpreterPattern
import com.wjf.moduledesignpattern.actionType.mediatorPattern.exam.User
import com.wjf.moduledesignpattern.actionType.memotoPattern.CallOfDuty
import com.wjf.moduledesignpattern.actionType.memotoPattern.Careteker
import com.wjf.moduledesignpattern.actionType.observerPattern.Coder
import com.wjf.moduledesignpattern.actionType.observerPattern.DevTechFrontier
import com.wjf.moduledesignpattern.actionType.statePattern.TvController
import com.wjf.moduledesignpattern.actionType.templatePattern.AbstractComputer
import com.wjf.moduledesignpattern.actionType.templatePattern.CoderComputer
import com.wjf.moduledesignpattern.actionType.templatePattern.MilitaryComputer
import com.wjf.moduledesignpattern.actionType.visitorPattern.learn.BusinessReport
import com.wjf.moduledesignpattern.actionType.visitorPattern.learn.CEOVisitor
import com.wjf.moduledesignpattern.actionType.visitorPattern.learn.CTOVisitor
import com.wjf.moduledesignpattern.createType.BuilderPattern.example.TodayFool
import com.wjf.moduledesignpattern.createType.BuilderPattern.learn.Director
import com.wjf.moduledesignpattern.createType.BuilderPattern.learn.MacBuilder
import com.wjf.moduledesignpattern.createType.ClonePattern.WordDocument
import com.wjf.moduledesignpattern.createType.FactoryPattern.example.FactoryAnimalConcrete
import com.wjf.moduledesignpattern.createType.FactoryPattern.learn.FactoryConcrete
import com.wjf.moduledesignpattern.createType.FactoryPatternAbstract.FactoryConcrete1
import com.wjf.moduledesignpattern.structureType.adapterPattern.example.AudioPlayer
import com.wjf.moduledesignpattern.structureType.adapterPattern.learn.VoltAdapter
import com.wjf.moduledesignpattern.structureType.bridgePattern.example.CircleBridge
import com.wjf.moduledesignpattern.structureType.bridgePattern.example.GreenCircle
import com.wjf.moduledesignpattern.structureType.bridgePattern.example.RedCircle
import com.wjf.moduledesignpattern.structureType.bridgePattern.example.ShapeBridge
import com.wjf.moduledesignpattern.structureType.compositePattern.Composite
import com.wjf.moduledesignpattern.structureType.compositePattern.Leaf
import com.wjf.moduledesignpattern.structureType.decoratorPattern.example.Circle
import com.wjf.moduledesignpattern.structureType.decoratorPattern.example.Rectangle
import com.wjf.moduledesignpattern.structureType.decoratorPattern.example.RedShapedDecorator
import com.wjf.moduledesignpattern.structureType.decoratorPattern.learn.ConcreteComponent
import com.wjf.moduledesignpattern.structureType.decoratorPattern.learn.ConcreteDecoratorA
import com.wjf.moduledesignpattern.structureType.facedPattern.MobilePhone
import com.wjf.moduledesignpattern.structureType.flyweightPattern.Ticket
import com.wjf.moduledesignpattern.structureType.flyweightPattern.TicketFactory
import com.wjf.moduledesignpattern.structureType.proxyPattern.ProxySubject
import com.wjf.moduledesignpattern.structureType.proxyPattern.RealSubject

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/11/29 10:04
 *
 */

class DesignFragment : MVVMBaseFragment<DesignViewModel>(), View.OnClickListener {
    override fun initViewModel() = ViewModelProviders.of(this).get(DesignViewModel::class.java)

    override fun initContentViewID() = R.layout.fragment_design

    override fun initView() {
        val builder_pattern = mView.findViewById<Button>(R.id.builder_pattern)
        val builder_pattern_example = mView.findViewById<Button>(R.id.builder_pattern_example)
        val clone_pattern = mView.findViewById<Button>(R.id.clone_pattern)
        val clone_pattern_example = mView.findViewById<Button>(R.id.clone_pattern_example)
        val factory_pattern = mView.findViewById<Button>(R.id.factory_pattern)
        val factory_pattern_example = mView.findViewById<Button>(R.id.factory_pattern_example)
        val factory_abstract_pattern = mView.findViewById<Button>(R.id.factory_abstract_pattern)
        val factory_abstract_pattern_example = mView.findViewById<Button>(R.id.factory_abstract_pattern_example)
        val strategy_pattern = mView.findViewById<Button>(R.id.strategy_pattern)
        val strategy_pattern_example = mView.findViewById<Button>(R.id.strategy_pattern_example)
        val state_pattern = mView.findViewById<Button>(R.id.state_pattern)
        val state_pattern_example = mView.findViewById<Button>(R.id.state_pattern_example)
        val chain_pattern = mView.findViewById<Button>(R.id.chain_pattern)
        val chain_pattern_example = mView.findViewById<Button>(R.id.chain_pattern_example)
        val interpreter_pattern = mView.findViewById<Button>(R.id.interpreter_pattern)
        val interpreter_pattern_example = mView.findViewById<Button>(R.id.interpreter_pattern_example)
        val command_pattern = mView.findViewById<Button>(R.id.command_pattern)
        val command_pattern_example = mView.findViewById<Button>(R.id.command_pattern_example)
        val observer_pattern = mView.findViewById<Button>(R.id.observer_pattern)
        val observer_pattern_example = mView.findViewById<Button>(R.id.observer_pattern_example)
        val memoto_pattern = mView.findViewById<Button>(R.id.memoto_pattern)
        val memoto_pattern_example = mView.findViewById<Button>(R.id.memoto_pattern_example)
        val template_pattern = mView.findViewById<Button>(R.id.template_pattern)
        val template_pattern_example = mView.findViewById<Button>(R.id.template_pattern_example)
        val visitor_pattern = mView.findViewById<Button>(R.id.visitor_pattern)
        val visitor_pattern_example = mView.findViewById<Button>(R.id.visitor_pattern_example)
        val mediator_pattern = mView.findViewById<Button>(R.id.mediator_pattern)
        val mediator_pattern_example = mView.findViewById<Button>(R.id.mediator_pattern_example)
        val proxy_pattern = mView.findViewById<Button>(R.id.proxy_pattern)
        val proxy_pattern_example = mView.findViewById<Button>(R.id.proxy_pattern_example)
        val composite_pattern = mView.findViewById<Button>(R.id.composite_pattern)
        val composite_pattern_example = mView.findViewById<Button>(R.id.composite_pattern_example)
        val adapter_pattern = mView.findViewById<Button>(R.id.adapter_pattern)
        val adapter_pattern_example = mView.findViewById<Button>(R.id.adapter_pattern_example)
        val decorate_pattern = mView.findViewById<Button>(R.id.decorate_pattern)
        val decorate_pattern_example = mView.findViewById<Button>(R.id.decorate_pattern_example)
        val flyweight_pattern = mView.findViewById<Button>(R.id.flyweight_pattern)
        val flyweight_pattern_example = mView.findViewById<Button>(R.id.flyweight_pattern_example)
        val faced_pattern = mView.findViewById<Button>(R.id.faced_pattern)
        val faced_pattern_example = mView.findViewById<Button>(R.id.faced_pattern_example)
        val bridge_pattern = mView.findViewById<Button>(R.id.bridge_pattern)
        val bridge_pattern_example = mView.findViewById<Button>(R.id.bridge_pattern_example)

        builder_pattern.setOnClickListener(this)
        builder_pattern_example.setOnClickListener(this)
        clone_pattern.setOnClickListener(this)
        clone_pattern_example.setOnClickListener(this)
        factory_pattern.setOnClickListener(this)
        factory_pattern_example.setOnClickListener(this)
        factory_abstract_pattern.setOnClickListener(this)
        factory_abstract_pattern_example.setOnClickListener(this)
        strategy_pattern.setOnClickListener(this)
        strategy_pattern_example.setOnClickListener(this)
        state_pattern.setOnClickListener(this)
        state_pattern_example.setOnClickListener(this)
        chain_pattern.setOnClickListener(this)
        chain_pattern_example.setOnClickListener(this)
        interpreter_pattern.setOnClickListener(this)
        interpreter_pattern_example.setOnClickListener(this)
        command_pattern.setOnClickListener(this)
        command_pattern_example.setOnClickListener(this)
        observer_pattern.setOnClickListener(this)
        observer_pattern_example.setOnClickListener(this)
        memoto_pattern.setOnClickListener(this)
        memoto_pattern_example.setOnClickListener(this)
        template_pattern.setOnClickListener(this)
        template_pattern_example.setOnClickListener(this)
        visitor_pattern.setOnClickListener(this)
        visitor_pattern_example.setOnClickListener(this)
        mediator_pattern.setOnClickListener(this)
        mediator_pattern_example.setOnClickListener(this)
        proxy_pattern.setOnClickListener(this)
        proxy_pattern_example.setOnClickListener(this)
        composite_pattern.setOnClickListener(this)
        composite_pattern_example.setOnClickListener(this)
        adapter_pattern.setOnClickListener(this)
        adapter_pattern_example.setOnClickListener(this)
        decorate_pattern.setOnClickListener(this)
        decorate_pattern_example.setOnClickListener(this)
        flyweight_pattern.setOnClickListener(this)
        flyweight_pattern_example.setOnClickListener(this)
        faced_pattern.setOnClickListener(this)
        faced_pattern_example.setOnClickListener(this)
        bridge_pattern.setOnClickListener(this)
        bridge_pattern_example.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.builder_pattern -> {
                val builder = MacBuilder()
                val pcDirector = Director(builder)
                pcDirector.construct("英特尔主板", "Retina显示器")
                Log.d("__ComputerInfo", builder.create().toString())
            }

            R.id.builder_pattern_example -> {
                TodayFool()
                    .setEgg("egg")
                    .setMeat("meat")
                    .whatDayEat()
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

            R.id.clone_pattern_example -> {}
            R.id.factory_pattern -> {
                val factory = FactoryConcrete()
                val product = factory.createProduct()
                product.method()
            }

            R.id.factory_pattern_example -> {
                val factoryAnimal = FactoryAnimalConcrete()
                val animal = factoryAnimal.createAnimal()
                animal.animalType()
            }

            R.id.factory_abstract_pattern -> {
                val factoryAbstract = FactoryConcrete1()
                val productAAbstract = factoryAbstract.createProductA()
                val productBAbstract = factoryAbstract.createProductB()
                productAAbstract.method()
                productBAbstract.method()
            }

            R.id.factory_abstract_pattern_example -> {}
            R.id.strategy_pattern -> {
                val calculator = TrafficCalculator()
                Thread {
                    calculator.setCalculateStrategy(BusStrategy())
                    Log.d("__price-bus", "" + calculator.calculatePrice(16))
                }.start()
                Thread {
                    calculator.setCalculateStrategy(CarStrategy())
                    Log.d("__price-car", "" + calculator.calculatePrice(16))
                }.start()
            }

            R.id.strategy_pattern_example -> {}
            R.id.state_pattern -> {
                val tvController = TvController()
                tvController.powerOn()
                tvController.nextChannel()
                tvController.powerOff()
                tvController.nextChannel()
            }

            R.id.state_pattern_example -> {}
            R.id.chain_pattern -> {
                val handler1 = ConcreteHandler1()
                val handler2 = ConcreteHandler2()
                handler1.successor = handler2
                handler2.successor = handler1
                handler1.handleRequest("ConcreteHandler2")
            }

            R.id.chain_pattern_example -> {
                val leaderManager = RealInterceptorChain() //传递下发任务可执行等级
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

            R.id.interpreter_pattern_example -> {}
            R.id.command_pattern -> {
                val receiver = Receiver()
                val command = ConcreteCommand(receiver)
                val invoker = Invoker(command)
                invoker.action()
            }

            R.id.command_pattern_example -> {
                val abcStock = Stock()
                val buyStock = BuyStock(abcStock)
                val shellStock = ShellStock(abcStock)
                val broker = Broker()
                broker.takeOrder(buyStock)
                broker.takeOrder(shellStock)
                broker.placeOrders()
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

            R.id.observer_pattern_example -> {}
            R.id.memoto_pattern -> {
                val game = CallOfDuty()
                game.play()
                val careteker = Careteker()
                careteker.archive(game.createMemoto())
                game.quit()
                val newGame = CallOfDuty()
                newGame.restore(careteker.getMemoto())
            }

            R.id.memoto_pattern_example -> {}
            R.id.template_pattern -> {
                var abstractComputer: AbstractComputer = CoderComputer()
                abstractComputer.startUp()
                abstractComputer = MilitaryComputer()
                abstractComputer.startUp()
            }

            R.id.template_pattern_example -> {}
            R.id.visitor_pattern -> {
                val businessReport = BusinessReport()
                Log.d("__businessReport-CEO", "-----------------")
                businessReport.showReport(CEOVisitor())
                Log.d("__businessReport-CTO", "-----------------")
                businessReport.showReport(CTOVisitor())
            }

            R.id.visitor_pattern_example -> {}
            R.id.mediator_pattern -> {
                val robert = User("Robert")
                val john = User("John")
                robert.sendMessage("Hi John")
                john.sendMessage("Hello Robert")
            }

            R.id.mediator_pattern_example -> {}
            R.id.proxy_pattern -> {
                val realSubject = RealSubject()
                val proxySubject = ProxySubject(realSubject)
                proxySubject.visit()
            }

            R.id.proxy_pattern_example -> {}
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

            R.id.composite_pattern_example -> {}
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

            R.id.flyweight_pattern_example -> {}
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