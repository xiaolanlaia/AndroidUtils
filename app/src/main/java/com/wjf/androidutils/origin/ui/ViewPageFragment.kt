package com.wjf.androidutils.origin.ui

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.modulewidget.widget.photo_view.PhotoView
import com.wjf.androidutils.R
import com.wjf.androidutils.databinding.FragmentViewPageBinding
import com.wjf.androidutils.origin.base.MVVMBaseFragment
import com.wjf.androidutils.origin.ui.home.HomeViewModel
import com.wjf.moduleutils.BitmapUtils

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/28 16:30
 *
 */

class ViewPageFragment : MVVMBaseFragment<HomeViewModel,FragmentViewPageBinding>() {

    val bitmapArr = ArrayList<Bitmap>().apply {
        add(BitmapUtils.instance.resource2Bitmap(R.mipmap.ic_launcher))
        add(BitmapUtils.instance.resource2Bitmap(R.mipmap.ic_launcher))
        add(BitmapUtils.instance.resource2Bitmap(R.mipmap.ic_launcher))
        add(BitmapUtils.instance.resource2Bitmap(R.mipmap.ic_launcher))
        add(BitmapUtils.instance.resource2Bitmap(R.mipmap.ic_launcher))
        add(BitmapUtils.instance.resource2Bitmap(R.mipmap.ic_launcher))
    }

    override fun initViewModel(): HomeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)


    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentViewPageBinding {
        return FragmentViewPageBinding.inflate(inflater,container,false)
    }

    override fun initView() {
        binding.vp2Indicator.setIndicatorItemCount(bitmapArr.size)

        binding.pager.pageMargin = (resources.displayMetrics.density * 15).toInt()
        binding.pager.adapter = object : PagerAdapter() {
            override fun getCount(): Int { return bitmapArr.size }

            override fun isViewFromObject(view: View, any: Any): Boolean { return view === any }


            override fun instantiateItem(container: ViewGroup, position: Int): Any {
                val view = PhotoView(mView.context)
                view.enable()
                view.scaleType = ImageView.ScaleType.FIT_CENTER
                view.setImageBitmap(bitmapArr[position])
                container.addView(view)
                return view
            }

            override fun destroyItem(container: ViewGroup, position: Int, any: Any) {
                container.removeView(any as View)
            }
        }
        binding.pager.setOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {

                // 显示在哪个页面就重绘对应的指示器
                binding.vp2Indicator.setCurrentSelectedPosition(position)
                binding.vp2Indicator.postInvalidate()
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
        binding.pager.currentItem = 0

    }
}