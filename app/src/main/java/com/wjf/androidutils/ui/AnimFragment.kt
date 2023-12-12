package com.wjf.androidutils.ui

import android.animation.AnimatorSet
import android.animation.Keyframe
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.graphics.Point
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.wjf.androidutils.base.MVVMBaseFragment
import com.wjf.androidutils.databinding.FragmentAnimBinding
import com.wjf.androidutils.ui.home.HomeViewModel
import com.wjf.moduleutils.ScreenUtils
import com.wjf.moduleutils.ToastUtils
import com.wjf.moduleutils.anim.PointEvaluator
import com.wjf.moduleutils.singleClick

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/9 15:20
 *
 */

class AnimFragment : MVVMBaseFragment<HomeViewModel, FragmentAnimBinding>() {
    override fun initViewModel() = ViewModelProviders.of(this).get(HomeViewModel::class.java)

    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAnimBinding {
        return FragmentAnimBinding.inflate(inflater,container,false)
    }

    override fun initView() {

        binding.tvAnim1.setOnClickListener {
            val a = ObjectAnimator.ofFloat(binding.tvAnim1, "translationX", 0f, -70f)
            val b = ObjectAnimator.ofFloat(binding.tvAnim1, "translationY", 0f, -70f)
            val c = ObjectAnimator.ofFloat(binding.tvAnim1, "rotation", 0f, -70f)
            val d = ObjectAnimator.ofFloat(binding.tvAnim1, "scaleX", 0f, 3f)
            AnimatorSet().apply {
                //a 在b之前播放
                play(a).before(b)
                //b和c同时播放动画效果
                play(a).with(c)
                //d 在c播放结束之后播放
                play(a).after(d)


                //顺序播放
                playSequentially(a,b,c,d)
                //同时播放
                playTogether(a,b,c,d)
                //1秒后播放a动画
                play(a).after(1000)

                start()
            }
        }

        binding.tvAnim2.singleClick {
            //点击第一次有效
            binding.tvAnim2.animate().translationX(100f).start()
            //每次点击都有效
            binding.tvAnim2.animate().translationXBy(100f).start()
        }

        binding.tvAnim3.singleClick {

            val animator = ValueAnimator.ofObject(
                PointEvaluator(),
                Point(0,0),
                Point(ScreenUtils.instance.getDeviceWidth(),ScreenUtils.instance.getDeviceHeight())
            )

            animator.addUpdateListener {
                val point = it.animatedValue as Point
                binding.tvAnim3.x = point.x.toFloat()
                binding.tvAnim3.y = point.y.toFloat()
            }

            animator.duration = 5000
            animator.start()
        }

        binding.tvKeyframe.singleClick {
            val start = Keyframe.ofFloat(0f, 0f)
            val middle = Keyframe.ofFloat(0.3f, 400f)
            val end = Keyframe.ofFloat(1f, 700f)
            val holder = PropertyValuesHolder.ofKeyframe("translationX",start,middle,end)
            ObjectAnimator.ofPropertyValuesHolder(binding.tvKeyframe,holder).apply {
                duration=2000
                start()
            }

        }

        binding.ivFeixing.singleClick {
            ToastUtils.instance.show("ivFeixing")

        }
    }
}