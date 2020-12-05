package com.huawei.hms.flutter.dialog_box;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.annotation.NonNull;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DialogBoxMethodCallHandler implements MethodChannel.MethodCallHandler, PluginRegistry.ActivityResultListener {
    private Activity mActivity;

    public DialogBoxMethodCallHandler(final Activity activity){
        this.mActivity = activity;
    }

    private  void alertDialog(MethodCall methodCall, MethodChannel.Result result){
        //Arguments from call
        String text = ValueGetter.getString("content", methodCall);
        String subText = ValueGetter.getString("subText", methodCall);
        String okButtonText = ValueGetter.getString("okButtonText", methodCall);
        final String toastText = ValueGetter.getString("toastText", methodCall);
        try {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this.mActivity);
            dialog.setTitle(text);
            dialog.setMessage(subText);
            dialog.setPositiveButton(okButtonText,  new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int which) {
                    Toast.makeText(mActivity,
                        toastText,
                        Toast.LENGTH_SHORT).show();
                }
            });
            dialog.show();
        }catch (Exception e){
            result.success(e.getMessage());
        }
    }

    public void simpleAlert(MethodCall methodCall, MethodChannel.Result result) {
        //Arguments from call
        String text = ValueGetter.getString("content", methodCall);
        String subText = ValueGetter.getString("subText", methodCall);
        String okButtonText = ValueGetter.getString("okButtonText", methodCall);
        String noButtonText = ValueGetter.getString("noButtonText", methodCall);
        final String toastText = ValueGetter.getString("toastText", methodCall);

        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(this.mActivity);
            builder.setTitle(text);
            builder.setMessage(subText);
            builder.setPositiveButton(okButtonText,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(mActivity,
                            toastText,
                            Toast.LENGTH_SHORT).show();
                    }
                });
            builder.setNegativeButton(noButtonText, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(mActivity,
                        android.R.string.no, Toast.LENGTH_SHORT).show();
                }
            });
            builder.setCancelable(false);
            builder.show();
        }catch (Exception e){
            result.success(e.getMessage());
        }
    }
    public void withItems(MethodCall methodCall, MethodChannel.Result result) {
        //Arguments from call
        String text = ValueGetter.getString("content", methodCall);
        String okButtonText = ValueGetter.getString("okButtonText", methodCall);
        String noButtonText = ValueGetter.getString("noButtonText", methodCall);
        int backgroundColor = ValueGetter.getInt("backgroundColor", methodCall);
        int setTextColor = ValueGetter.getInt("setTextColor", methodCall);
        ArrayList<String> itemList = methodCall.argument("itemList");

        try {
            //List<String> to String[]
          String[] itemListStringArray = null;
            if (itemList != null) {
                itemListStringArray = ValueGetter.itemListToArray(itemList);
            }

            AlertDialog.Builder builder = new AlertDialog.Builder(this.mActivity);
            final String[] finalItemListStringArray = itemListStringArray;
            builder.setTitle(text) //"List of Items"

                .setItems(itemListStringArray, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        assert finalItemListStringArray != null;
                        Toast.makeText(mActivity, Arrays.toString(finalItemListStringArray[which].toCharArray()) + " is clicked", Toast.LENGTH_SHORT).show();
                    }
                });

            builder.setPositiveButton(okButtonText, null);
            builder.setNegativeButton(noButtonText, null);
            builder.setNeutralButton("NEUTRAL", null);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder.setIcon(mActivity.getResources().getDrawable(android.R.drawable.ic_menu_call, mActivity.getTheme()));
                builder.setIcon(mActivity.getResources().getDrawable(R.drawable.list, mActivity.getTheme()));
            }
            AlertDialog alertDialog = builder.create();

            alertDialog.show();

            Button button = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
            button.setBackgroundColor(backgroundColor);
            button.setTextColor(setTextColor);
        }catch (Exception e){
            result.success(e.getMessage());
        }
    }

    public void withMultiChoiceItems(MethodCall methodCall, MethodChannel.Result result) {
        //Arguments from call
        String text = ValueGetter.getString("content", methodCall);
        String okButtonText = ValueGetter.getString("okButtonText", methodCall);
        ArrayList<String> itemList = methodCall.argument("itemList");
        final ArrayList<Integer> selectedList = new ArrayList<>();

        try {
            //List<String> to String[]
            String[] itemListStringArray = null;
            if (itemList != null) {
                itemListStringArray = ValueGetter.itemListToArray(itemList);
            }

            AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);

            builder.setTitle(text); //This is list choice dialog box
            builder.setMultiChoiceItems(itemListStringArray, null,
                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if (isChecked) {
                            selectedList.add(which);
                        } else if (selectedList.contains(which)) {
                            selectedList.remove(Integer.valueOf(which));
                        }
                    }
                });

            final String[] finalItemListStringArray = itemListStringArray;
            builder.setPositiveButton(okButtonText, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    ArrayList<String> selectedStrings = new ArrayList<>();

                    for (int j = 0; j < selectedList.size(); j++) {
                        assert finalItemListStringArray != null;
                        selectedStrings.add(finalItemListStringArray[selectedList.get(j)]);
                    }

                    Toast.makeText(mActivity, "Items selected are: " + Arrays.toString(selectedStrings.toArray()), Toast.LENGTH_SHORT).show();
                }
            });

            builder.show();
        }catch (Exception e){
            result.success(e.getMessage());
        }
    }

    public void withEditText(MethodCall methodCall, MethodChannel.Result result) {
        //Arguments from call
        String text = ValueGetter.getString("content", methodCall);
        String okButtonText = ValueGetter.getString("okButtonText", methodCall);
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
            builder.setTitle(text); //"With Edit Text"

            final EditText input = new EditText(this.mActivity);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
            input.setLayoutParams(lp);
            builder.setView(input);
            builder.setPositiveButton(okButtonText, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(mActivity, "Text entered is " + input.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            });
            builder.show();
        }catch (Exception e){
            result.success(e.getMessage());
        }
    }

    public void withImageView(MethodCall methodCall, MethodChannel.Result result) {
        //Arguments from call
        String okButtonText = ValueGetter.getString("okButtonText", methodCall);

        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
            LayoutInflater inflater = mActivity.getLayoutInflater();
            View dialogLayout = inflater.inflate(R.layout.alert_dialog_with_image_view, null);
            builder.setPositiveButton(okButtonText, null);
            builder.setView(dialogLayout);
            builder.show();
        }catch (Exception e){
            result.success(e.getMessage());
        }
    }


    public void withSeekBar(MethodCall methodCall, MethodChannel.Result result) {
        //Arguments from call
        String text = ValueGetter.getString("content", methodCall);
        String okButtonText = ValueGetter.getString("okButtonText", methodCall);

        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
            builder.setTitle(text); //With SeekBar
            final SeekBar seekBar = new SeekBar(this.mActivity);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
            seekBar.setLayoutParams(lp);
            builder.setView(seekBar);
            builder.setPositiveButton(okButtonText, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(mActivity, "Progress is " + seekBar.getProgress(), Toast.LENGTH_SHORT).show();
                }
            });
            builder.show();
        }catch (Exception e){
            result.success(e.getMessage());
        }
    }
    public void withRatingBar(MethodCall methodCall, MethodChannel.Result result) {
        //Arguments from call
        String text = ValueGetter.getString("content", methodCall);
        String okButtonText = ValueGetter.getString("okButtonText", methodCall);

        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
            LayoutInflater inflater = mActivity.getLayoutInflater();
            builder.setTitle(text); //With RatingBar
            View dialogLayout = inflater.inflate(R.layout.alert_dialog_with_rating_bar, null);
            final RatingBar ratingBar = dialogLayout.findViewById(R.id.ratingBar);
            builder.setView(dialogLayout);
            builder.setPositiveButton(okButtonText, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(mActivity, "Rating is " + ratingBar.getRating(), Toast.LENGTH_SHORT).show();
                }
            });
            builder.show();
        }catch (Exception e){
            result.success(e.getMessage());
        }
    }
    @Override
    public void onMethodCall(@NonNull MethodCall call, @NonNull MethodChannel.Result result) {
        switch (call.method) {
            case "getPlatformVersion":
                result.success("Android " + android.os.Build.VERSION.RELEASE);
                break;
            case "showAlertDialog":
                alertDialog(call,result);
                break;
            case "simpleAlertWithCancel":
                simpleAlert(call,result);
                break;
            case "withItems":
                withItems(call,result);
                break;
            case "withMultiChoiceItems":
                withMultiChoiceItems(call,result);
                break;
            case "withEditText":
                withEditText(call,result);
                break;
            case "withImageView":
                withImageView(call,result);
                break;
            case "withSeekBar":
                withSeekBar(call,result);
                break;
            case "withRatingBar":
                withRatingBar(call,result);
                break;
            default:
                result.notImplemented();
                break;
        }
    }

    @Override
    public boolean onActivityResult(int requestCode, int resultCode, Intent data) {
        return false;
    }

}
