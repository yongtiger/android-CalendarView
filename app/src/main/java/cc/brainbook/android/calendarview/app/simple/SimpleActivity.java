package cc.brainbook.android.calendarview.app.simple;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cc.brainbook.android.calendarview.Calendar;
import cc.brainbook.android.calendarview.CalendarLayout;
import cc.brainbook.android.calendarview.CalendarView;
import cc.brainbook.android.calendarview.app.Article;
import cc.brainbook.android.calendarview.app.ArticleAdapter;
import cc.brainbook.android.calendarview.app.R;
import cc.brainbook.android.calendarview.app.base.activity.BaseActivity;
import cc.brainbook.android.calendarview.app.colorful.ColorfulActivity;
import cc.brainbook.android.calendarview.app.group.GroupItemDecoration;
import cc.brainbook.android.calendarview.app.group.GroupRecyclerView;
import cc.brainbook.android.calendarview.app.index.IndexActivity;
import cc.brainbook.android.calendarview.app.meizu.MeiZuActivity;

import java.util.HashMap;
import java.util.Map;

public class SimpleActivity extends BaseActivity implements
        CalendarView.OnCalendarSelectListener,
        CalendarView.OnYearChangeListener,
        View.OnClickListener {

    TextView mTextMonthDay;

    TextView mTextYear;

    TextView mTextLunar;

    TextView mTextCurrentDay;

    CalendarView mCalendarView;

    RelativeLayout mRelativeTool;

    GroupRecyclerView mRecyclerView;
    private int mYear;
    CalendarLayout mCalendarLayout;

    public static void show(Context context) {
        context.startActivity(new Intent(context, SimpleActivity.class));
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_simple;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void initView() {
        setStatusBarDarkMode();
        mTextMonthDay = findViewById(R.id.tv_month_day);
        mTextYear = findViewById(R.id.tv_year);
        mTextLunar = findViewById(R.id.tv_lunar);
        mRelativeTool = findViewById(R.id.rl_tool);
        mCalendarView = findViewById(R.id.calendarView);
        mTextCurrentDay = findViewById(R.id.tv_current_day);
        mTextMonthDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mCalendarLayout.isExpand()) {
                    mCalendarLayout.expand();
                    return;
                }
                mCalendarView.showYearSelectLayout(mYear);
                mTextLunar.setVisibility(View.GONE);
                mTextYear.setVisibility(View.GONE);
                mTextMonthDay.setText(String.valueOf(mYear));
            }
        });
        findViewById(R.id.fl_current).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCalendarView.scrollToCurrent();
            }
        });

        mCalendarLayout = findViewById(R.id.calendarLayout);
        mCalendarView.setOnYearChangeListener(this);
        mCalendarView.setOnCalendarSelectListener(this);
        mTextYear.setText(String.valueOf(mCalendarView.getCurYear()));
        mYear = mCalendarView.getCurYear();
        mTextMonthDay.setText(mCalendarView.getCurMonth() + "???" + mCalendarView.getCurDay() + "???");
        mTextLunar.setText("??????");
        mTextCurrentDay.setText(String.valueOf(mCalendarView.getCurDay()));
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new GroupItemDecoration<String,Article>());
        mRecyclerView.setAdapter(new ArticleAdapter(this));
        mRecyclerView.notifyDataSetChanged();
    }

    @Override
    protected void initData() {

        int year = mCalendarView.getCurYear();
        int month = mCalendarView.getCurMonth();

        Map<String, Calendar> map = new HashMap<>();
        map.put(getSchemeCalendar(year, month, 3, 0xFF40db25, "???").toString(),
                getSchemeCalendar(year, month, 3, 0xFF40db25, "???"));
        map.put(getSchemeCalendar(year, month, 6, 0xFFe69138, "???").toString(),
                getSchemeCalendar(year, month, 6, 0xFFe69138, "???"));
        map.put(getSchemeCalendar(year, month, 9, 0xFFdf1356, "???").toString(),
                getSchemeCalendar(year, month, 9, 0xFFdf1356, "???"));
        map.put(getSchemeCalendar(year, month, 13, 0xFFedc56d, "???").toString(),
                getSchemeCalendar(year, month, 13, 0xFFedc56d, "???"));
        map.put(getSchemeCalendar(year, month, 14, 0xFFedc56d, "???").toString(),
                getSchemeCalendar(year, month, 14, 0xFFedc56d, "???"));
        map.put(getSchemeCalendar(year, month, 15, 0xFFaacc44, "???").toString(),
                getSchemeCalendar(year, month, 15, 0xFFaacc44, "???"));
        map.put(getSchemeCalendar(year, month, 18, 0xFFbc13f0, "???").toString(),
                getSchemeCalendar(year, month, 18, 0xFFbc13f0, "???"));
        map.put(getSchemeCalendar(year, month, 25, 0xFF13acf0, "???").toString(),
                getSchemeCalendar(year, month, 25, 0xFF13acf0, "???"));
        map.put(getSchemeCalendar(year, month, 27, 0xFF13acf0, "???").toString(),
                getSchemeCalendar(year, month, 27, 0xFF13acf0, "???"));
        //?????????????????????????????????????????????????????????????????????
        mCalendarView.setSchemeDate(map);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_flyme:
                MeiZuActivity.show(this);
                break;
            case R.id.ll_simple:
                SimpleActivity.show(this);
                break;
            case R.id.ll_colorful:
                ColorfulActivity.show(this);
                break;
            case R.id.ll_index:
                IndexActivity.show(this);
                break;
        }
    }

    private Calendar getSchemeCalendar(int year, int month, int day, int color, String text) {
        Calendar calendar = new Calendar();
        calendar.setYear(year);
        calendar.setMonth(month);
        calendar.setDay(day);
        calendar.setSchemeColor(color);//???????????????????????????????????????????????????
        calendar.setScheme(text);
        return calendar;
    }

    @Override
    public void onCalendarOutOfRange(Calendar calendar) {

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onCalendarSelect(Calendar calendar, boolean isClick) {
        mTextLunar.setVisibility(View.VISIBLE);
        mTextYear.setVisibility(View.VISIBLE);
        mTextMonthDay.setText(calendar.getMonth() + "???" + calendar.getDay() + "???");
        mTextYear.setText(String.valueOf(calendar.getYear()));
        mTextLunar.setText(calendar.getLunar());
        mYear = calendar.getYear();
    }

    @Override
    public void onYearChange(int year) {
        mTextMonthDay.setText(String.valueOf(year));
    }


}
