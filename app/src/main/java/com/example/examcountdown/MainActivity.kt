package com.example.examcountdown
import android.app.Activity
import android.os.Bundle
import android.widget.*
import java.time.LocalDate
import java.time.temporal.ChronoUnit

class MainActivity: Activity() {
 override fun onCreate(b: Bundle?) { super.onCreate(b)
  val box=LinearLayout(this); box.orientation=LinearLayout.VERTICAL; box.setPadding(40,60,40,40)
  val title=TextView(this); title.text="EXAM COUNTDOWN"; title.textSize=28f; title.setTypeface(null,1)
  val name=EditText(this); name.hint="Goal name"
  val date=EditText(this); date.hint="Target date (YYYY-MM-DD)"
  val save=Button(this); save.text="SAVE GOAL"
  box.addView(title); box.addView(name); box.addView(date); box.addView(save); setContentView(box)
  save.setOnClickListener {
   try { getSharedPreferences("app",0).edit().putString("name",name.text.toString()).putString("date",date.text.toString()).apply(); CountdownWidget.update(this) }
   catch(_:Exception) { Toast.makeText(this,"Use YYYY-MM-DD",Toast.LENGTH_SHORT).show() }
  }
 }
}