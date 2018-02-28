package com.cn.fragmentdialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.cn.github.R;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ld on 2017/12/27.
 *
 * @author ld
 * @date 2017/12/27
 */

public class FragmentDialogActivity extends AppCompatActivity {
    @BindView(R.id.btn_1)
    Button btn1;
    @BindView(R.id.btn_2)
    Button btn2;
    @BindView(R.id.btn_3)
    Button btn3;
    @BindView(R.id.btn_4)
    Button btn4;
    @BindView(R.id.btn_5)
    Button btn5;
    @BindView(R.id.btn_6)
    Button btn6;
    @BindView(R.id.btn_7)
    Button btn7;
    @BindView(R.id.btn_8)
    Button btn8;
    @BindView(R.id.btn_9)
    Button btn9;
    private DialogFragment dialogFragment;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_fragment);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4, R.id.btn_5, R.id.btn_6, R.id.btn_7, R.id.btn_8, R.id.btn_9})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_1:
                showConfirmDialog();
                break;
            case R.id.btn_2:
                showDateDialog();
                break;
            case R.id.btn_3:
                showInsertDialog();
                break;
            case R.id.btn_4:
                showIntervalInsertDialog();
                break;
            case R.id.btn_5:
                showListDialog();
                break;
            case R.id.btn_6:
                showPasswordInsertDialog();
                break;
            case R.id.btn_7:
                dialogFragment = DialogFragmentHelper.showProgress(getSupportFragmentManager(), "正在加载中");
                break;
            case R.id.btn_8:
                showTimeDialog();
                break;
            case R.id.btn_9:
                DialogFragmentHelper.showTips(getSupportFragmentManager(), "你进入了无网的异次元中");
                break;
        }
    }
    /**
     * 选择时间的弹出窗
     */
    private void showTimeDialog() {
        String titleTime = "请选择时间";
        Calendar calendarTime = Calendar.getInstance();
        DialogFragmentHelper.showTimeDialog(getSupportFragmentManager(), titleTime, calendarTime, new IDialogResultListener<Calendar>() {
            @Override
            public void onDataResult(Calendar result) {
                showToast(String.valueOf(result.getTime().getDate()));
            }
        }, true);
    }

    /**
     * 输入密码的弹出窗
     */
    private void showPasswordInsertDialog() {
        String titlePassword = "请输入密码";
        DialogFragmentHelper.showPasswordInsertDialog(getSupportFragmentManager(), titlePassword, new IDialogResultListener<String>() {
            @Override
            public void onDataResult(String result) {
                showToast("密码为：" + result);
            }
        }, true);
    }

    /**
     * 显示列表的弹出窗
     */
    private void showListDialog() {
        String titleList = "选择哪种方向？";
        final String [] languanges = new String[]{"Android", "iOS", "web 前端", "Web 后端", "老子不打码了"};

        DialogFragmentHelper.showListDialog(getSupportFragmentManager(), titleList, languanges, new IDialogResultListener<Integer>() {
            @Override
            public void onDataResult(Integer result) {
                showToast(languanges[result]);
            }
        }, true);
    }

    /**
     * 两个输入框的弹出窗
     */
    private void showIntervalInsertDialog() {
        String title = "请输入想输入的内容";
        DialogFragmentHelper.showIntervalInsertDialog(getSupportFragmentManager(), title, new IDialogResultListener<String[]>() {
            @Override
            public void onDataResult(String[] result) {
                showToast(result[0] + result[1]);
            }
        }, true);
    }

    private void showInsertDialog() {
        String titleInsert  = "请输入想输入的内容";
        DialogFragmentHelper.showInsertDialog(getSupportFragmentManager(), titleInsert, new IDialogResultListener<String>() {
            @Override
            public void onDataResult(String result) {
                showToast(result);
            }
        }, true);
    }

    /**
     * 选择日期的弹出窗
     */
    private void showDateDialog() {
        String titleDate = "请选择日期";
        Calendar calendar = Calendar.getInstance();
        dialogFragment = DialogFragmentHelper.showDateDialog(getSupportFragmentManager(), titleDate, calendar, new IDialogResultListener<Calendar>() {
            @Override
            public void onDataResult(Calendar result) {
                showToast(String.valueOf(result.getTime().getDate()));
            }
        }, true);
    }

    /**
     * 确认和取消的弹出窗
     */
    private void showConfirmDialog() {
        DialogFragmentHelper.showConfirmDialog(getSupportFragmentManager(), "是否选择 Android？", new IDialogResultListener<Integer>() {
            @Override
            public void onDataResult(Integer result) {
                showToast("You Click Ok");
            }
        }, true, new CustomDialog.OnDialogCancelListener() {
            @Override
            public void onCancel() {
                showToast("You Click Cancel");
            }
        });
    }


    /**
     * 对 Toast 进行封藏，减少代码量
     *
     * @param message 想要显示的信息
     */
    private void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
