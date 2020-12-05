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

public class DialogBoxMethodCallHandler implements MethodChannel.MethodCallHandler, PluginRegistry.ActivityResultListener {
    private Activity mActivity;

    public DialogBoxMethodCallHandler(final Activity activity){
        this.mActivity = activity;
    }

    private  void alertDialog(MethodCall methodCall, MethodChannel.Result result){
        //Arguments from call
        String text = ValueGetter.getString("content", methodCall);
        String subText = ValueGetter.getString("subText", methodCall);
        try {
            AlertDialog dialog = new AlertDialog.Builder( mActivity).create();
            dialog.setTitle(text);
            dialog.setMessage(subText);
            dialog.setButton("OK", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    // Write your code here to execute after dialog
                    // closed
                    Toast.makeText(mActivity,
                        "You clicked  OK", Toast.LENGTH_SHORT).show();
                }
            });
            dialog.show();
        }catch (Exception e){
            result.success(e.getMessage());
        }
    }

    public void simpleAlert(MethodCall methodCall, MethodChannel.Result result) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.mActivity);
        builder.setTitle("Simple Alert");
        builder.setMessage("We have a message");
        builder.setPositiveButton("OK",
            new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(mActivity,
                        "OK was clicked",
                        Toast.LENGTH_SHORT).show();
                }
            });
        builder.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(mActivity,
                    android.R.string.no, Toast.LENGTH_SHORT).show();
            }
        });
        builder.setCancelable(false);
        builder.show();
    }
    public void withItems(MethodCall methodCall, MethodChannel.Result result) {

        final String[] items = {"Apple", "Banana", "Orange", "Grapes"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this.mActivity);
        builder.setTitle("List of Items")

            .setItems(items, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(mActivity, items[which] + " is clicked", Toast.LENGTH_SHORT).show();
                }
            });

        builder.setPositiveButton("OK", null);
        builder.setNegativeButton("CANCEL", null);
        builder.setNeutralButton("NEUTRAL", null);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder.setIcon(mActivity.getResources().getDrawable(android.R.drawable.ic_menu_call, mActivity.getTheme()));
            builder.setIcon(mActivity.getResources().getDrawable(R.drawable.list, mActivity.getTheme()));
        }
        AlertDialog alertDialog = builder.create();

        alertDialog.show();

        Button button = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        button.setBackgroundColor(Color.BLACK);
        button.setPadding(0, 0, 20, 0);
        button.setTextColor(Color.WHITE);
    }

    public void withMultiChoiceItems(MethodCall methodCall, MethodChannel.Result result) {
        final String[] items = {"Apple", "Banana", "Orange", "Grapes"};
        final ArrayList<Integer> selectedList = new ArrayList<>();
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);

        builder.setTitle("This is list choice dialog box");
        builder.setMultiChoiceItems(items, null,
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

        builder.setPositiveButton("DONE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ArrayList<String> selectedStrings = new ArrayList<>();

                for (int j = 0; j < selectedList.size(); j++) {
                    selectedStrings.add(items[selectedList.get(j)]);
                }

                Toast.makeText(mActivity, "Items selected are: " + Arrays.toString(selectedStrings.toArray()), Toast.LENGTH_SHORT).show();
            }
        });

        builder.show();

    }
    public void withEditText(MethodCall methodCall, MethodChannel.Result result) {

        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setTitle("With Edit Text");

        final EditText input = new EditText(this.mActivity);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        builder.setView(input);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(mActivity, "Text entered is " + input.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }
    public void withImageView(MethodCall methodCall, MethodChannel.Result result) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        LayoutInflater inflater = mActivity.getLayoutInflater();
        View dialogLayout = inflater.inflate(R.layout.alert_dialog_with_image_view, null);
        builder.setPositiveButton("OK", null);
        builder.setView(dialogLayout);
        builder.show();
    }

    public void withSeekBar(MethodCall methodCall, MethodChannel.Result result) {

        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setTitle("With SeekBar");
        final SeekBar seekBar = new SeekBar(this.mActivity);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT);
        seekBar.setLayoutParams(lp);
        builder.setView(seekBar);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(mActivity, "Progress is " + seekBar.getProgress(), Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();

    }
    public void withRatingBar(MethodCall methodCall, MethodChannel.Result result) {

        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        LayoutInflater inflater = mActivity.getLayoutInflater();
        builder.setTitle("With RatingBar");
        View dialogLayout = inflater.inflate(R.layout.alert_dialog_with_rating_bar, null);
        final RatingBar ratingBar = dialogLayout.findViewById(R.id.ratingBar);
        builder.setView(dialogLayout);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(mActivity, "Rating is " + ratingBar.getRating(), Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
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
            case "simpleAlert":
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
