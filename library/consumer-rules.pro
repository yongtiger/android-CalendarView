###[FIX#Proguard异常#fragment_log.xml#CalendarView is null]
# https://github.com/huanghaibin-dev/CalendarView
-keepclasseswithmembers class * {
    public <init>(android.content.Context);
}

#-keep class your project path.MonthView {
#    public <init>(android.content.Context);
#}
#-keep class your project path.WeekBar {
#    public <init>(android.content.Context);
#}
#-keep class your project path.WeekView {
#    public <init>(android.content.Context);
#}
#-keep class your project path.YearView {
#    public <init>(android.content.Context);
#}