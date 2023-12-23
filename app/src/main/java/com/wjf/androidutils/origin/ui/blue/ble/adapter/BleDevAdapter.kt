package com.wjf.androidutils.origin.ui.blue.ble.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wjf.androidutils.R
import com.wjf.modulebluetooth.ble.BleDev
import com.wjf.modulebluetooth.ble.BleUtils
import com.wjf.moduleutils.ToastUtils

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/15 9:07
 *
 */


@SuppressLint("MissingPermission")
class BleDevAdapter internal constructor() : RecyclerView.Adapter<BleDevAdapter.VH>() {
 val mDevices: MutableList<BleDev> = ArrayList()

 init {
  BleUtils.instance.scanBle()
 }

 fun updateList(bleDev: BleDev){
  if (!mDevices.contains(bleDev)) {
   Log.d("__device-name", "" + bleDev.dev.name)
   mDevices.add(bleDev)
   notifyDataSetChanged()
  }
 }

 // 重新扫描
 fun reScan() {
  mDevices.clear()
  notifyDataSetChanged()
  BleUtils.instance.scanBle()
 }

 override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
  val view = LayoutInflater.from(parent.context).inflate(R.layout.item_dev, parent, false)
  return VH(view)
 }

 override fun onBindViewHolder(holder: VH, position: Int) {
  val dev = mDevices[position]
  val name = dev.dev.name
  val address = dev.dev.address
  holder.name.text = String.format("%s, %s, Rssi=%s", name, address, dev.scanResult.rssi)
  holder.address.text = String.format("广播数据{%s}", dev.scanResult.scanRecord)
 }

 override fun getItemCount(): Int {
  return mDevices.size
 }

 inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
  val name: TextView
  val address: TextView

  init {
   itemView.setOnClickListener(this)
   name = itemView.findViewById(R.id.name)
   address = itemView.findViewById(R.id.address)
  }

  override fun onClick(v: View) {
   val pos = adapterPosition
   if (pos >= 0 && pos < mDevices.size) {

    BleUtils.instance.connect(v.context,mDevices[pos].dev)
    ToastUtils.instance.show(String.format("与[%s]开始连接............", mDevices[pos].dev))
   }
  }
 }

}