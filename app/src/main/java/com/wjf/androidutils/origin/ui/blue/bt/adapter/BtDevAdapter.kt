package com.wjf.androidutils.origin.ui.blue.bt.adapter

import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wjf.androidutils.R
import com.wjf.modulebluetooth.bt.BlueUtils
import com.wjf.moduleutils.ToastUtils

@SuppressLint("MissingPermission")
class BtDevAdapter : RecyclerView.Adapter<BtDevAdapter.VH>() {
    private val mDevices: MutableList<BluetoothDevice> = ArrayList()

    init {
        addBound()
    }

    private fun addBound() {
        val bondedDevices = BlueUtils.instance.getBondDevice()
        if (bondedDevices != null) {
            mDevices.addAll(bondedDevices)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_dev, parent, false)
        return VH(view)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val dev = mDevices[position]
        holder.tvName.text = dev.name ?: ""
        holder.tvAddress.text = String.format("%s (%s)", dev.address, if (dev.bondState == 10) "未配对" else "配对")
    }

    override fun getItemCount(): Int {
        return mDevices.size
    }

    fun add(dev: BluetoothDevice) {
        if (mDevices.contains(dev)) {
            return
        }
        mDevices.add(dev)
        notifyDataSetChanged()
    }

    fun reScan() {
        mDevices.clear()
        addBound()
        BlueUtils.instance.scan()
        notifyDataSetChanged()
    }

    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val tvName: TextView
        val tvAddress: TextView

        init {
            itemView.setOnClickListener(this)
            tvName = itemView.findViewById(R.id.tv_name)
            tvAddress = itemView.findViewById(R.id.tv_address)
        }

        override fun onClick(v: View) {
            val pos = adapterPosition
            if (pos >= 0 && pos < mDevices.size) {
                ToastUtils.instance.show("正在连接...")
                BlueUtils.instance.bluetoothSocket(mDevices[pos])
            }
        }
    }
}