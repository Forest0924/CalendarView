package com.haibin.calendarviewproject.self;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarLayout;
import com.haibin.calendarview.CalendarView;
import com.haibin.calendarviewproject.R;
import com.haibin.calendarviewproject.base.activity.BaseActivity;
import com.haibin.calendarviewproject.simple.SimpleActivity;

import java.util.HashMap;
import java.util.Map;

public class SelfActivity extends BaseActivity implements CalendarView.OnMonthChangeListener,
        CalendarView.OnCalendarSelectListener, CalendarView.OnYearChangeListener, View
                .OnClickListener {

    CalendarView mCalendarView;

    CalendarLayout mCalendarLayout;

    Button last, next;
    TextView dateshow,tv_current_day;
    FrameLayout fl_current;

    public static void show(Context context) {
        context.startActivity(new Intent(context, SelfActivity.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_self;
    }

    @Override
    protected void initView() {
        setStatusBarDarkMode();
        mCalendarView = (CalendarView) findViewById(R.id.calendarView);
        mCalendarLayout = (CalendarLayout) findViewById(R.id.calendarLayout);
        fl_current = (FrameLayout) findViewById(R.id.fl_current);
        tv_current_day = (TextView) findViewById(R.id.tv_current_day);
        last = (Button) findViewById(R.id.last);
        next = (Button) findViewById(R.id.next);
        dateshow = (TextView) findViewById(R.id.tv_date);

        mCalendarView.setOnYearChangeListener(this);
        mCalendarView.setOnMonthChangeListener(this);
        mCalendarView.setOnCalendarSelectListener(this);
//        mCalendarLayout.expand();

        fl_current.setOnClickListener(this);
        last.setOnClickListener(this);
        next.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        int year = mCalendarView.getCurYear();
        int month = mCalendarView.getCurMonth();
        int day=mCalendarView.getCurDay();
        String str = year + "年" + month + "月";
        dateshow.setText(str);
        tv_current_day.setText(String.valueOf(day));

        Map<String, Calendar> map = new HashMap<>();
        map.put(getSchemeCalendar(year, month, 3, 0xFFFF7700, "假").toString(), getSchemeCalendar
                (year, month, 3, 0xFFFF7700, "假"));
        map.put(getSchemeCalendar(year, month, 6, 0xFFFF7700, "事").toString(), getSchemeCalendar
                (year, month, 6, 0xFFFF7700, "事"));
        map.put(getSchemeCalendar(year, month, 9, 0xFFFF7700, "议").toString(), getSchemeCalendar
                (year, month, 9, 0xFFFF7700, "议"));
        map.put(getSchemeCalendar(year, month, 13, 0xFFFF7700, "记").toString(), getSchemeCalendar
                (year, month, 13, 0xFFFF7700, "记"));
        map.put(getSchemeCalendar(year, month, 14, 0xFFFF7700, "记").toString(), getSchemeCalendar
                (year, month, 14, 0xFFFF7700, "记"));
        map.put(getSchemeCalendar(year, month, 15, 0xFFFF7700, "假").toString(), getSchemeCalendar
                (year, month, 15, 0xFFFF7700, "假"));
        map.put(getSchemeCalendar(year, month, 18, 0xFFFF7700, "记").toString(), getSchemeCalendar
                (year, month, 18, 0xFFFF7700, "记"));
        map.put(getSchemeCalendar(year, month, 25, 0xFFFF7700, "假").toString(), getSchemeCalendar
                (year, month, 25, 0xFFFF7700, "假"));
        map.put(getSchemeCalendar(year, month, 27, 0xFFFF7700, "多").toString(), getSchemeCalendar
                (year, month, 27, 0xFFFF7700, "多"));
        //此方法在巨大的数据量上不影响遍历性能，推荐使用
        mCalendarView.setSchemeDate(map);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fl_current:
                mCalendarView.scrollToCurrent(true);
                break;
            case R.id.last:
                mCalendarView.scrollToPre(true);
                break;
            case R.id.next:
                mCalendarView.scrollToNext(true);
                break;
        }
    }

    @Override
    public void onCalendarOutOfRange(Calendar calendar) {

    }

    @Override
    public void onCalendarSelect(Calendar calendar, boolean isClick) {

    }

    @Override
    public void onYearChange(int year) {

    }

    private Calendar getSchemeCalendar(int year, int month, int day, int color, String text) {
        Calendar calendar = new Calendar();
        calendar.setYear(year);
        calendar.setMonth(month);
        calendar.setDay(day);
//        calendar.setSchemeColor(color);//如果单独标记颜色、则会使用这个颜色
//        calendar.setScheme(text);
        return calendar;
    }

    @Override
    public void onMonthChange(int year, int month) {
        String str = year + "年" + month + "月";
        dateshow.setText(str);
    }
}
