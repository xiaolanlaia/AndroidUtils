package com.wjf.androidutils.ui.reflect

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.ViewModelProviders
import com.wjf.androidutils.base.MVVMBaseFragment
import com.wjf.androidutils.databinding.FragmentReflectBinding
import com.wjf.androidutils.ui.home.HomeViewModel


/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/6 14:02
 *
 */

class ReflectFragment : MVVMBaseFragment<HomeViewModel, FragmentReflectBinding>() {

    override fun initViewModel() = ViewModelProviders.of(this).get(HomeViewModel::class.java)

    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentReflectBinding {
        return FragmentReflectBinding.inflate(inflater, container, false)
    }

    override fun initView() {

        val tabTitle = arrayOf("tab 1", "tab 2", "tab 3")
        val tabFragments = ArrayList<ReflectItemFragment>()

        tabTitle.forEach {
            binding.tabLayoutReflect.addTab(binding.tabLayoutReflect.newTab().setText(it))
            tabFragments.add(ReflectItemFragment.newInstance(it))
        }
        setTabWidth()
        binding.viewPageReflect.adapter =
            object : FragmentPagerAdapter(childFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
                override fun getItem(position: Int): Fragment {
                    return tabFragments[position]
                }

                override fun getCount(): Int {
                    return tabFragments.size
                }

                override fun getPageTitle(position: Int): CharSequence {
                    return tabTitle.get(position)
                }
            }

        binding.tabLayoutReflect.setupWithViewPager(binding.viewPageReflect,false)

    }


    fun setTabWidth() {
        binding.tabLayoutReflect.post {
            try {
                //拿到tabLayout的mTabStrip（低版本）/slidingTabIndicator（高版本）属性
                val mTabStrip = binding.tabLayoutReflect.getChildAt(0) as LinearLayout
                for (i in 0 until mTabStrip.childCount) {
                    val tabView = mTabStrip.getChildAt(i)

                    //这里需要根据源码版本做一个判断
                    val text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        "textView"
                    } else {
                        "mTextView"
                    }
                    //拿到tabView的mTextView属性 tab的字数不固定一定用反射取mTextView
                    val mTextViewField = tabView.javaClass.getDeclaredField(text)
                    mTextViewField.isAccessible = true
                    val mTextView = mTextViewField[tabView] as TextView
                    tabView.setPadding(0, 0, 0, 0)

                    //字多宽线就多宽
                    var width = 0
                    width = mTextView.width
                    if (width == 0) {
                        mTextView.measure(0, 0)
                        width = mTextView.measuredWidth
                    }

                    //设置tab左右间距 注意这里不能使用Padding 因为源码中线的宽度是根据 tabView的宽度来设置的
                    val params = tabView.layoutParams as LinearLayout.LayoutParams
                    params.width = width
                    params.leftMargin = 50
                    params.rightMargin = 50
                    tabView.layoutParams = params
                    tabView.invalidate()
                }
            } catch (e: NoSuchFieldException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            }
        }
    }
}












