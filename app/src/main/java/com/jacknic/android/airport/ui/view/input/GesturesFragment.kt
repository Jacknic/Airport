package com.jacknic.android.airport.ui.view.input

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.catalog.framework.annotations.Sample

@Sample(
    name = "触摸手势检测",
    description = "输入处理",
    tags = ["touch", "gestures"]
)
class GesturesFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return GesturesView(requireContext())
    }
}