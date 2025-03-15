package co.feip.fefu2025.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.compose.ui.graphics.Color
import co.feip.fefu2025.R

/**
 *  JvmOverloads constructor -  используется для генерации перегруженных конструкторов в байт-коде Java;
 *
 * context - информация об окружении, в котором работает View;
 *
 * attrs - Атрибуты из XML;
 *
 * defStyleAttr - ID стиля по умолчанию, который применяется к View;
 * */
class CustomProgLanguageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): FrameLayout(context, attrs, defStyleAttr) {
    private val tvLanguageName: TextView
    private val tvPercent: TextView
    private val circleView: View

    init {
        /** inflate принимает 3 параметра:
         * 1) resource - ID XML-файла с разметкой который хотим загрузить(раздуть);
         * 2) root (this) - Родительский ViewGroup, в который будет добавлена этв разметка
         * 3) attachToRoot (true) - Определяет, добавлять ли разметку сразу в root. В Recycler View если
         * сделать true будет краш :) Эта штука в положении false полезна, если нужно сначала настроить View,
         * а потом добавить вручную.
         * */
        LayoutInflater.from(context).inflate(R.layout.programming_language_item, this, true)
        tvLanguageName = findViewById(R.id.programmingLanguageName)
        tvPercent= findViewById(R.id.percent)
        circleView = findViewById(R.id.circleView)
    }

    fun setItem(name: String, color: Int, percentage: Float) {
        tvLanguageName.text = name
        tvPercent.text = "${percentage.toInt()}%"
        circleView.background.setTint(color)
    }
}