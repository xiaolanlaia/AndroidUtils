package com.wjf.moduleutils

import android.os.Environment
import android.util.Xml
import com.wjf.moduleutils.UtilsConstant.utilsContext
import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.NodeList
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult

object XmlUtils {

    /**
     * 读取xml
     */
    fun getXmlStr(readType: Int): String? {
        val xml = utilsContext.javaClass.classLoader.getResourceAsStream("assets/hatemanconfigFile.xml")
        var contentStr = ""
        val pullParser = Xml.newPullParser()
        try {

            //为PULL解析器设置要解析的XML数据
            pullParser.setInput(xml, "UTF-8")
            var event = pullParser.eventType
            while (event != XmlPullParser.END_DOCUMENT) {
                when (event) {
                    XmlPullParser.START_DOCUMENT -> {}
                    XmlPullParser.START_TAG -> {
                        when(readType){
                            0 -> {
                                if ("rows_split" == pullParser.name) {
                                    contentStr = pullParser.nextText()
                                }
                            }
                            1 -> {
                                if ("cols_split" == pullParser.name) {
                                    contentStr = pullParser.nextText()
                                }
                            }
                        }

                    }

                    XmlPullParser.END_TAG -> {}
                }
                event = pullParser.next()
            }
        } catch (e: XmlPullParserException) {
            e.printStackTrace()
        }
        return contentStr
    }

    /**
     * 修改
     */
    fun modifyXmlInAssets(type: Int, content: String) {
        val filePath = "${Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)}/local/hatemanconfigFile.xml"
        // 加载 XML 文件
        val xmlFile = File(filePath)
        if (!xmlFile.exists()){
            LogUtils.dw("__xml-err-0","文件不存在")
            return
        }
        val dbFactory = DocumentBuilderFactory.newInstance()
        val dBuilder = dbFactory.newDocumentBuilder()
        val doc: Document = dBuilder.parse(xmlFile)

        // 可选：如果 XML 文件有声明，可能需要以下代码
        doc.documentElement.normalize()

        // 定位 opencv_storage 节点
        val opencvStorageNodeList: NodeList = doc.getElementsByTagName("opencv_storage")
        if (opencvStorageNodeList.length > 0) {
            val opencvStorageNode = opencvStorageNodeList.item(0) as Element

            // 定位 rows_split 子节点
            val rowsSplitNodeList = if (type == 0)
                opencvStorageNode.getElementsByTagName("rows_split") else
                opencvStorageNode.getElementsByTagName("cols_split")

            if (rowsSplitNodeList.length > 0) {
                val rowsSplitNode = rowsSplitNodeList.item(0) as Element

                // 修改节点的文本内容
                rowsSplitNode.textContent = content

                // 保存修改后的 XML 文件
                try {
                    val transformer = TransformerFactory.newInstance().newTransformer()
                    val source = DOMSource(doc)
                    val result = StreamResult(FileOutputStream(xmlFile))
                    transformer.transform(source, result)
                    LogUtils.dw("__xml-save","XML 文件修改并保存成功")
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            } else {
                LogUtils.dw("__xml-err-1","rows_split 节点未找到")
            }
        } else {
            LogUtils.dw("__xml-err-2","opencv_storage 节点未找到")
        }
    }
}