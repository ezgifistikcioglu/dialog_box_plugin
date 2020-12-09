package com.huawei.hms.flutter.dialog_box;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;
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
        int backgroundColor = ValueGetter.getInt("backgroundColor", methodCall);
        int setTextColor = ValueGetter.getInt("setTextColor", methodCall);
        int subTextColor = ValueGetter.getInt("subTextColor", methodCall);
        int okButtonColor = ValueGetter.getInt("okButtonColor", methodCall);
        int textSize = ValueGetter.getInt("textSize", methodCall);

        int messageTextSize = ValueGetter.getInt("messageTextSize", methodCall);
        try {
            TextView mTitle = new TextView(this.mActivity);
            mTitle.setText(text);
            mTitle.setGravity(Gravity.CENTER_HORIZONTAL);
            mTitle.setTextSize(textSize);
            mTitle.setTextColor(setTextColor);

            AlertDialog.Builder builder  = new AlertDialog.Builder(this.mActivity);
            builder.setCustomTitle(mTitle);
            builder.setMessage(subText);
            builder.setPositiveButton(okButtonText,  new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int which) {
                    Toast.makeText(mActivity,
                        toastText,
                        Toast.LENGTH_SHORT).show();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.setOnShowListener(dialog -> {
                //The following is to style dialog message
                TextView message = alertDialog.findViewById(android.R.id.message);
                if (message != null) {
                    message.setTextSize(messageTextSize);
                    message.setTextColor(subTextColor);
                }
            });
            alertDialog.show();
            Button positiveButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
            positiveButton.setBackgroundColor(okButtonColor);
            positiveButton.setTextColor(setTextColor);
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
        final String noToastText = ValueGetter.getString("noToastText", methodCall);
        int okButtonColor = ValueGetter.getInt("okButtonColor", methodCall);
        int noButtonColor = ValueGetter.getInt("noButtonColor", methodCall);
        int setTextColor = ValueGetter.getInt("setTextColor", methodCall);
        int setNoTextColor = ValueGetter.getInt("setNoTextColor", methodCall);
        int textSize = ValueGetter.getInt("textSize", methodCall);
        int messageTextSize = ValueGetter.getInt("messageTextSize", methodCall);
        int subTextColor = ValueGetter.getInt("subTextColor", methodCall);

        try {
            TextView mTitle = new TextView(this.mActivity);
            mTitle.setText(text);
            mTitle.setGravity(Gravity.NO_GRAVITY);
            mTitle.setTextSize(textSize);
            mTitle.setTextColor(setTextColor);

            AlertDialog.Builder builder = new AlertDialog.Builder(this.mActivity);
            builder.setCustomTitle(mTitle);
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
                        noToastText, Toast.LENGTH_SHORT).show();
                }
            });
            builder.setCancelable(false);
            AlertDialog alertDialog = builder.create();
            alertDialog.setOnShowListener(dialog -> {
                //The following is to style dialog message
                TextView message = alertDialog.findViewById(android.R.id.message);
                if (message != null) {
                    message.setTextSize(messageTextSize);
                    message.setTextColor(subTextColor);
                }
            });

            alertDialog.show();

            Button positiveButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
            positiveButton.setBackgroundColor(okButtonColor);
            positiveButton.setTextColor(setTextColor);

            Button negativeButton = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
            negativeButton.setBackgroundColor(noButtonColor);
            negativeButton.setTextColor(setNoTextColor);
        }catch (Exception e){
            result.success(e.getMessage());
        }
    }
    public void withItems(MethodCall methodCall, MethodChannel.Result result) {
        //Arguments from call
        String text = ValueGetter.getString("content", methodCall);
        String okButtonText = ValueGetter.getString("okButtonText", methodCall);
        int okButtonColor = ValueGetter.getInt("okButtonColor", methodCall);
        int noButtonColor = ValueGetter.getInt("noButtonColor", methodCall);
        int neutralButtonColor = ValueGetter.getInt("neutralButtonColor", methodCall);
        String noButtonText = ValueGetter.getString("noButtonText", methodCall);
        String neutralButtonText = ValueGetter.getString("neutralButtonText", methodCall);
        final String noToastText = ValueGetter.getString("noToastText", methodCall);
        final String toastText = ValueGetter.getString("toastText", methodCall);
        final String neutralToastText = ValueGetter.getString("neutralToastText", methodCall);
        int backgroundColor = ValueGetter.getInt("backgroundColor", methodCall);
        int setTextColor = ValueGetter.getInt("setTextColor", methodCall);
        int setNoTextColor = ValueGetter.getInt("setNoTextColor", methodCall);
        int setNeutralTextColor = ValueGetter.getInt("setNeutralTextColor", methodCall);
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

            builder.setPositiveButton(okButtonText,   new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int which) {
                    Toast.makeText(mActivity,
                        toastText,
                        Toast.LENGTH_SHORT).show();
                }
            });
            builder.setNegativeButton(noButtonText, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(mActivity, noToastText, Toast.LENGTH_SHORT).show();
            }
            });
            builder.setNeutralButton(neutralButtonText, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int which) {
                    Toast.makeText(mActivity,
                        neutralToastText,
                        Toast.LENGTH_SHORT).show();
                }
            });
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder.setIcon(mActivity.getResources().getDrawable(android.R.drawable.ic_menu_call, mActivity.getTheme()));
                builder.setIcon(mActivity.getResources().getDrawable(R.drawable.list, mActivity.getTheme()));
            }
            AlertDialog alertDialog = builder.create();

            alertDialog.show();

            Button positiveButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
            positiveButton.setBackgroundColor(okButtonColor);
            positiveButton.setTextColor(setTextColor);

           Button negativeButton = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
           negativeButton.setBackgroundColor(noButtonColor);
           negativeButton.setTextColor(setNoTextColor);

           Button neutralButton = alertDialog.getButton(DialogInterface.BUTTON_NEUTRAL);
           neutralButton.setBackgroundColor(neutralButtonColor);
           neutralButton.setTextColor(setNeutralTextColor);
        }catch (Exception e){
            result.success(e.getMessage());
        }
    }

    public void withMultiChoiceItems(MethodCall methodCall, MethodChannel.Result result) {
        //Arguments from call
        String text = ValueGetter.getString("content", methodCall);
        String okButtonText = ValueGetter.getString("okButtonText", methodCall);
        ArrayList<String> itemList = methodCall.argument("itemList");
        int okButtonColor = ValueGetter.getInt("okButtonColor", methodCall);
        int setTextColor = ValueGetter.getInt("setTextColor", methodCall);
        int textSize = ValueGetter.getInt("textSize", methodCall);
        final ArrayList<Integer> selectedList = new ArrayList<>();

        try {
            //List<String> to String[]
            String[] itemListStringArray = null;
            if (itemList != null) {
                itemListStringArray = ValueGetter.itemListToArray(itemList);
            }
            TextView mTitle = new TextView(this.mActivity);
            mTitle.setText(text);
            mTitle.setGravity(Gravity.NO_GRAVITY);
            mTitle.setTextSize(textSize);
            mTitle.setTextColor(setTextColor);

            AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);

            builder.setCustomTitle(mTitle); //This is list choice dialog box
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
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
            Button positiveButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
            positiveButton.setBackgroundColor(okButtonColor);
            positiveButton.setTextColor(setTextColor);
        }catch (Exception e){
            result.success(e.getMessage());
        }
    }

    public void withEditText(MethodCall methodCall, MethodChannel.Result result) {
        //Arguments from call
        String text = ValueGetter.getString("content", methodCall);
        String okButtonText = ValueGetter.getString("okButtonText", methodCall);
        int okButtonColor = ValueGetter.getInt("okButtonColor", methodCall);
        int setTextColor = ValueGetter.getInt("setTextColor", methodCall);
        int textSize = ValueGetter.getInt("textSize", methodCall);
        try {
            TextView mTitle = new TextView(this.mActivity);
            mTitle.setText(text);
            mTitle.setGravity(Gravity.NO_GRAVITY);
            mTitle.setTextSize(textSize);
            mTitle.setTextColor(setTextColor);

            AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
            builder.setCustomTitle(mTitle); //"With Edit Text"

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
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
            Button positiveButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
            positiveButton.setBackgroundColor(okButtonColor);
            positiveButton.setTextColor(setTextColor);
        }catch (Exception e){
            result.success(e.getMessage());
        }
    }

    public void withImageView(MethodCall methodCall, MethodChannel.Result result) {
        //Arguments from call
        String text = ValueGetter.getString("content", methodCall);
        String okButtonText = ValueGetter.getString("okButtonText", methodCall);
        int setTextColor = ValueGetter.getInt("setTextColor", methodCall);
        int textSize = ValueGetter.getInt("textSize", methodCall);
        final String toastText = ValueGetter.getString("toastText", methodCall);
        int okButtonColor = ValueGetter.getInt("okButtonColor", methodCall);
      //  Bitmap bitmap = ValueGetter.bitmapForDecoders(methodCall);

        try {
            TextView mTitle = new TextView(this.mActivity);
            mTitle.setText(text);
            mTitle.setGravity(Gravity.CENTER_HORIZONTAL);
            mTitle.setTextSize(textSize);
            mTitle.setTextColor(setTextColor);

            AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
            builder.setCustomTitle(mTitle);
            LayoutInflater inflater = mActivity.getLayoutInflater();
            View dialogLayout = inflater.inflate(R.layout.alert_dialog_with_image_view, null);


          // Drawable mDrawable = new BitmapDrawable(mActivity.getResources(), bitmap);
          // ImageView image = new ImageView(this.mActivity);
          // image.setImageResource(mDrawable);
            builder.setPositiveButton(okButtonText,  new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int which) {
                    Toast.makeText(mActivity,
                        toastText,
                        Toast.LENGTH_SHORT).show();
                }
            });
            builder.setView(dialogLayout);
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
            Button positiveButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
            positiveButton.setBackgroundColor(okButtonColor);
            positiveButton.setTextColor(setTextColor);

        }catch (Exception e){
            result.success(e.getMessage());
        }
    }


    public void withSeekBar(MethodCall methodCall, MethodChannel.Result result) {
        //Arguments from call
        String text = ValueGetter.getString("content", methodCall);
        int textSize = ValueGetter.getInt("textSize", methodCall);
        String okButtonText = ValueGetter.getString("okButtonText", methodCall);
        int setTextColor = ValueGetter.getInt("setTextColor", methodCall);
        int okButtonColor = ValueGetter.getInt("okButtonColor", methodCall);

        try {
            TextView mTitle = new TextView(this.mActivity);
            mTitle.setText(text);
            mTitle.setGravity(Gravity.CENTER_HORIZONTAL);
            mTitle.setTextSize(textSize);
            mTitle.setTextColor(setTextColor);

            AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
            builder.setCustomTitle(mTitle); //With SeekBar
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
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
            Button positiveButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
            positiveButton.setBackgroundColor(okButtonColor);
            positiveButton.setTextColor(setTextColor);
        }catch (Exception e){
            result.success(e.getMessage());
        }
    }
    public void withRatingBar(MethodCall methodCall, MethodChannel.Result result) {
        //Arguments from call
        String text = ValueGetter.getString("content", methodCall);
        int textSize = ValueGetter.getInt("textSize", methodCall);
        String okButtonText = ValueGetter.getString("okButtonText", methodCall);
        int setTextColor = ValueGetter.getInt("setTextColor", methodCall);
        int okButtonColor = ValueGetter.getInt("okButtonColor", methodCall);

        try {
            TextView mTitle = new TextView(this.mActivity);
            mTitle.setText(text);
            mTitle.setGravity(Gravity.CENTER_HORIZONTAL);
            mTitle.setTextSize(textSize);
            mTitle.setTextColor(setTextColor);

            AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
            LayoutInflater inflater = mActivity.getLayoutInflater();
            builder.setCustomTitle(mTitle); //With RatingBar
            View dialogLayout = inflater.inflate(R.layout.alert_dialog_with_rating_bar, null);
            final RatingBar ratingBar = dialogLayout.findViewById(R.id.ratingBar);
            builder.setView(dialogLayout);
            builder.setPositiveButton(okButtonText, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(mActivity, "Rating is " + ratingBar.getRating(), Toast.LENGTH_SHORT).show();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
            Button positiveButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
            positiveButton.setBackgroundColor(okButtonColor);
            positiveButton.setTextColor(setTextColor);
        }catch (Exception e){
            result.success(e.getMessage());
        }
    }
    @Override
    public void onMethodCall(@NonNull MethodCall call, @NonNull MethodChannel.Result result) {
        switch (call.method) {
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
