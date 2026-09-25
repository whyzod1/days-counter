package com.example.examcountdown
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import java.time.LocalDate
import java.time.temporal.ChronoUnit

class CountdownWidget: AppWidgetProvider() {
 override fun onUpdate(c: Context,m: AppWidgetManager,ids:IntArray){ ids.forEach{ updateOne(c,m,it) } }
 companion object {
  fun update(c:Context){ val m=AppWidgetManager.getInstance(c); update(c,m,m.getAppWidgetIds(android.content.ComponentName(c,CountdownWidget::class.java))) }
  fun update(c:Context,m:AppWidgetManager,ids:IntArray){ ids.forEach{updateOne(c,m,it)} }
  private fun updateOne(c:Context,m:AppWidgetManager,id:Int){
   val p=c.getSharedPreferences("app",0)
   val name=p.getString("name","EXAM COUNTDOWN") ?: "EXAM COUNTDOWN"
   val date=p.getString("date","2026-11-29") ?: "2026-11-29"
   val days=try{ChronoUnit.DAYS.between(LocalDate.now(),LocalDate.parse(date))}catch(_:Exception){0}
   val v=RemoteViews(c.packageName,R.layout.widget_countdown)
   v.setTextViewText(R.id.goal,name.uppercase()); v.setTextViewText(R.id.days,days.toString()); v.setTextViewText(R.id.label,"DAYS LEFT  •  $date")
   m.updateAppWidget(id,v)
  }
 }
}