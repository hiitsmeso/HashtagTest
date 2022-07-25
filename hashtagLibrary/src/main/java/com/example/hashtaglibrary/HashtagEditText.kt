package com.example.hashtaglibrary

import android.content.Context
import android.graphics.Color
import android.text.Editable
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import java.util.regex.Pattern

class HashtagEditText(context: Context, attrs: AttributeSet?) : AppCompatEditText(context, attrs) {
    init {
        HashTagEditText()
    }

    fun HashTagEditText (){
        //val content = findViewById<EditText>(R.id.editTextTextMultiLine)
        this@HashtagEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val wordlist= this@HashtagEditText.text.toString().split(" ")

                for ( i in wordlist) {
                    val hashtagValidation = "#[\\S]*"
                    val p = Pattern.matches(hashtagValidation, i)
                    if (p) {
                        //Log.d(ContentValues.TAG, "It's hashtag " + i)
                        this@HashtagEditText.text?.indexOf(i)?.let {
                            this@HashtagEditText.text?.setSpan(
                                ForegroundColorSpan(Color.BLUE),
                                it,
                                it+ i.length,
                                SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE

                            )
                        }
                        //content.text.setSpan(UnderlineSpan(),content.text.indexOf(i),content.text.indexOf(i)+i.length,SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE)
                    } else {
                        //Log.d(ContentValues.TAG, "It's not hashtag " + i)
                        this@HashtagEditText.text?.indexOf(i)?.let {
                            this@HashtagEditText.text?.setSpan(
                                ForegroundColorSpan(Color.BLACK),
                                it,
                                it + i.length,
                                SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE
                            )
                        }
                    }
                }
            }
        })
    }

}