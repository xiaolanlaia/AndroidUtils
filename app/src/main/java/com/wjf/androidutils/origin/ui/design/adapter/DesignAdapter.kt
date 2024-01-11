package com.wjf.androidutils.origin.ui.design.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wjf.androidutils.R
import com.wjf.androidutils.origin.ui.design.DESIGN_ADAPTER
import com.wjf.androidutils.origin.ui.design.DESIGN_ADAPTER_2
import com.wjf.androidutils.origin.ui.design.DESIGN_BRIDGE
import com.wjf.androidutils.origin.ui.design.DESIGN_BUILDER
import com.wjf.androidutils.origin.ui.design.DESIGN_CHAIN
import com.wjf.androidutils.origin.ui.design.DESIGN_CLONE
import com.wjf.androidutils.origin.ui.design.DESIGN_COMBINE
import com.wjf.androidutils.origin.ui.design.DESIGN_COMMAND
import com.wjf.androidutils.origin.ui.design.DESIGN_DECORATE
import com.wjf.androidutils.origin.ui.design.DESIGN_DECORATE_2
import com.wjf.androidutils.origin.ui.design.DESIGN_FACED
import com.wjf.androidutils.origin.ui.design.DESIGN_FACTORY_ABSTRACT
import com.wjf.androidutils.origin.ui.design.DESIGN_FACTORY_METHOD
import com.wjf.androidutils.origin.ui.design.DESIGN_FACTORY_SIMPLE
import com.wjf.androidutils.origin.ui.design.DESIGN_FLYWEIGHT
import com.wjf.androidutils.origin.ui.design.DESIGN_INTERPRETER
import com.wjf.androidutils.origin.ui.design.DESIGN_ITERATOR
import com.wjf.androidutils.origin.ui.design.DESIGN_MEDIATOR
import com.wjf.androidutils.origin.ui.design.DESIGN_MEMO
import com.wjf.androidutils.origin.ui.design.DESIGN_OBSERVER
import com.wjf.androidutils.origin.ui.design.DESIGN_PROXY
import com.wjf.androidutils.origin.ui.design.DESIGN_PROXY_DYNAMIC
import com.wjf.androidutils.origin.ui.design.DESIGN_STATE
import com.wjf.androidutils.origin.ui.design.DESIGN_STRATEGY
import com.wjf.androidutils.origin.ui.design.DESIGN_TEMPLATE
import com.wjf.androidutils.origin.ui.design.DESIGN_VISITOR
import com.wjf.androidutils.origin.utils.JumpPageKey
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
import com.wjf.moduledesignpattern.structureType.proxyDynamic.Buyer1
import com.wjf.moduledesignpattern.structureType.proxyDynamic.Buyer2
import com.wjf.moduledesignpattern.structureType.proxyDynamic.DynamicProxy
import com.wjf.moduledesignpattern.structureType.proxyDynamic.Subject
import com.wjf.moduleutils.LogUtils
import com.wjf.moduleutils.singleClick
import java.util.LinkedList

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2024/1/11 8:20
 *
 */

class DesignAdapter(private val dataList: LinkedList<String> = LinkedList(JumpPageKey.keys)) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    lateinit var mView: View
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        mView =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_text_view, parent, false)
        return DesignViewHolder(mView)
    }

    override fun getItemCount() = dataList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is DesignViewHolder -> {
                holder.tvItem.text = dataList[position]
            }
        }
    }

    inner class DesignViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvItem: TextView = itemView.findViewById(R.id.tv_item)

        init {
            itemView.singleClick {
                when (dataList[adapterPosition]) {
                    DESIGN_BUILDER          -> {
                        val milkTea: MilkTea = MilkTeaBuilder().type("1").size("2").build()
                        tvItem.text = "type:${milkTea.type} size:${milkTea.size}"
                    }
                    DESIGN_CLONE            -> {
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
                    DESIGN_FACTORY_SIMPLE   -> {
                        val factory = GameFactory()
                        //不应该直接传入参数，使用配置文件能够适应变化
                        val game = factory.createGame(GameFactory.gameType)
                        tvItem.text = game.play()
                    }
                    DESIGN_FACTORY_METHOD   -> {
                        val methodGameFactory = MethodGameAFactory()
                        val methodGameA = methodGameFactory.createGame()
                        tvItem.text = methodGameA.play()
                    }
                    DESIGN_FACTORY_ABSTRACT -> {
                        val factory = CompleteLargeShoeFactory()
                        //大号的鞋子工厂所以创建的鞋子和鞋垫都是大号的
                        factory.createShoe()
                        factory.createInsole()
                    }
                    DESIGN_OBSERVER         -> {
                        val devTechFrontier = DevTechFrontier()
                        val coder1 = Coder("coder1")
                        val coder2 = Coder("coder2")
                        val coder3 = Coder("coder3")
                        devTechFrontier.addObserver(coder1)
                        devTechFrontier.addObserver(coder2)
                        devTechFrontier.addObserver(coder3)
                        devTechFrontier.postNewPublication("发布了新消息")
                    }
                    DESIGN_MEMO             -> {
                        val game = CallOfDuty()
                        game.play()
                        val careteker = Careteker()
                        careteker.archive(game.createMemoto())
                        game.quit()
                        val newGame = CallOfDuty()
                        newGame.restore(careteker.memoto)
                    }
                    DESIGN_INTERPRETER      -> {
                        val isMale = InterpreterPattern.getMaleExpression()
                        val isMarriedWoman = InterpreterPattern.getMarriedWomanExpression()
                        Log.d("__expression", "John is male? " + isMale.interpret("John"))
                        Log.d("__expression", "Julie is a married women?  " + isMarriedWoman.interpret("Married Julie"))
                    }
                    DESIGN_COMMAND          -> {
                        val receiver = Receiver()
                        val command = ConcreteCommand(receiver)
                        val invoker = Invoker(command)
                        invoker.action()
                        invoker.undo()
                    }
                    DESIGN_STRATEGY         -> {
                        val calculator = TrafficCalculator()
                        tvItem.text = "${tvItem.text} → ${calculator.setCalculateStrategy(BusStrategy()).calculatePrice(16)}"
                        tvItem.text = "${tvItem.text} → ${calculator.setCalculateStrategy(CarStrategy()).calculatePrice(16)}"
                    }
                    DESIGN_VISITOR          -> {
                        val businessReport = BusinessReport()
                        Log.d("__businessReport-CEO", "-----------------")
                        businessReport.showReport(CEOVisitor())
                        Log.d("__businessReport-CTO", "-----------------")
                        businessReport.showReport(CTOVisitor())
                    }
                    DESIGN_STATE            -> {
                        val tvController = TvController()
                        tvController.powerOn()
                        tvController.nextChannel()
                        tvController.powerOff()
                        tvController.nextChannel()
                    }
                    DESIGN_MEDIATOR         -> {
                        val c1 = ConcreteColleagueA()
                        val c2 = ConcreteColleagueB()
                        c1.send()
                        println("-------------")
                        c2.send()
                    }
                    DESIGN_TEMPLATE         -> {
                        var abstractComputer: AbstractComputer = CoderComputer()
                        abstractComputer.startUp()
                        abstractComputer = MilitaryComputer()
                        abstractComputer.startUp()
                    }
                    DESIGN_ITERATOR         -> {
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
                    DESIGN_CHAIN            -> {
                        val leaderManager = RealInterceptorChain() //传递下发任务可执行等级
                        leaderManager.addInterceptor(InterceptorOne())
                        leaderManager.addInterceptor(InterceptorTwo())
                        val result: String = leaderManager.proceed("开始请求了") //开始执行
                        tvItem.text = result
                    }
                    DESIGN_BRIDGE           -> {
                        val redCircle: ShapeBridge = CircleBridge(100, 100, 10, RedCircle())
                        val greenCircle: ShapeBridge = CircleBridge(100, 100, 10, GreenCircle())
                        redCircle.draw()
                        greenCircle.draw()
                    }
                    DESIGN_FACED            -> {
                        val mobilePhone = MobilePhone()
                        mobilePhone.takePicture()
                        mobilePhone.videoChat()
                    }
                    DESIGN_COMBINE          -> {
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
                    DESIGN_DECORATE         -> {
                        val component = ConcreteComponent()
                        val decorator = ConcreteDecoratorA(component)
                        decorator.operate()
                    }
                    DESIGN_DECORATE_2       -> {
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
                    DESIGN_ADAPTER          -> {
                        val adapter = VoltAdapter()
                        Log.d("__adapter", "" + adapter.getVolt5())
                    }
                    DESIGN_ADAPTER_2        -> {
                        val audioPlayer = AudioPlayer()
                        audioPlayer.play("mp3", "beyond the horizon.mp3")
                        audioPlayer.play("mp4", "alone.mp4")
                        audioPlayer.play("vlc", "far far away.vlc")
                        audioPlayer.play("avi", "mind me.avi")
                    }
                    DESIGN_PROXY            -> {
                        val realSubject = RealSubject()
                        val proxySubject = ProxySubject(realSubject)
                        proxySubject.visit()
                    }
                    DESIGN_PROXY_DYNAMIC    -> {

                        // 1. 创建调用处理器类对象
                        val dynamicProxy = DynamicProxy()

                        // 2. 创建目标对象对象
                        val mBuyer1 = Buyer1()

                        // 3. 创建动态代理类 & 对象：通过调用处理器类对象newProxyInstance（）
                        // 传入上述目标对象对象
                        val buyer1DynamicProxy = dynamicProxy.newProxyInstance(mBuyer1) as Subject

                        // 4. 通过调用动态代理对象方法从而调用目标对象方法
                        // 实际上是调用了invoke（），再通过invoke（）里的反射机制调用目标对象的方法
                        buyer1DynamicProxy.buybuybuy()
                        // 以上代购为小成代购Mac

                        // 以下是代购为小何代购iPhone
                        val mBuyer2 = Buyer2()
                        val buyer2DynamicProxy = dynamicProxy.newProxyInstance(mBuyer2) as Subject
                        buyer2DynamicProxy.buybuybuy()

                    }
                    DESIGN_FLYWEIGHT        -> {
                        val ticket: Ticket = TicketFactory.getTicket("北京", "青岛")
                        ticket.showTicketInfo("上铺")
                        val ticket1: Ticket = TicketFactory.getTicket("北京", "青岛")
                        ticket1.showTicketInfo("下铺")
                        val ticket2: Ticket = TicketFactory.getTicket("北京", "青岛")
                        ticket2.showTicketInfo("坐票")
                    }
                }
            }
        }
    }


}