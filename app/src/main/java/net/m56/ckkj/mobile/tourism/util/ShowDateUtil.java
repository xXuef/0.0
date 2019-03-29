package net.m56.ckkj.mobile.tourism.util;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;

import java.util.Calendar;

/**
 * Created by yue on 2017/08/14.
 */

public class ShowDateUtil {
    private static int mYear, mMonth, mDay;

   public static void showDate(Context c ,DatePickerDialog.OnDateSetListener mdateListener,boolean flag) {
        setDataFormat();
        CustomerDatePickerDialog customerDatePickerDialog = new CustomerDatePickerDialog(c, mdateListener, mYear, mMonth, mDay);
        customerDatePickerDialog.setCancelable(false);
       if (flag) hideDatePickerDay(customerDatePickerDialog);
        customerDatePickerDialog.show();
    }
    private static void setDataFormat() {
        Calendar ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mDay = ca.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 隐藏DatePicker中的日期
     *
     * @param customDialog
     */
    private  static  void hideDatePickerDay(DatePickerDialog customDialog) {
        ViewGroup group = ((ViewGroup) ((ViewGroup) customDialog.getDatePicker().getChildAt(0))
                .getChildAt(0));
        boolean bool = false;
        for (int i = 0; i < group.getChildCount(); i++) {
            View view = group.getChildAt(i);
            String viewName = view.getClass().getName();
            if (bool) {
                view.setVisibility(View.GONE);
                continue;
            }
            if (view instanceof NumberPicker) {
                int maxNum = ((NumberPicker) view).getMaxValue();
                if (maxNum <= 11) bool = true;
            }
        }
    }

}
