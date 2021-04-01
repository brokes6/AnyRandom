package com.laboratory.anyrandom.override

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewScrollHelper {
    companion object {
        fun scrollToPosition(recyclerView: RecyclerView, position: Int) {
            val manager1 = recyclerView.layoutManager
            if (manager1 is LinearLayoutManager) {
                val manager = manager1
                val mScroller = TopSmoothScroller(recyclerView.context)
                mScroller.targetPosition = position
                manager.startSmoothScroll(mScroller)
            }
        }

        class TopSmoothScroller(context: Context) : LinearSmoothScroller(context) {
            override fun getHorizontalSnapPreference(): Int {
                return SNAP_TO_START
            }

            override fun getVerticalSnapPreference(): Int {
                return SNAP_TO_START
            }
        }
    }
}