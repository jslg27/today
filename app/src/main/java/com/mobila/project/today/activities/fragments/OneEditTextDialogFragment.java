
package com.mobila.project.today.activities.fragments;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.mobila.project.today.R;

import java.util.Objects;

public class OneEditTextDialogFragment extends GeneralConfirmationDialogFragment {
    public static final String CONFIRMED_STRING = "CONFIRMATION_STRING";

    private OneEditTextDialogListener callback;
    private EditText editText;

    @Override
    void getOptionalDialogAddendum(Bundle bundle, AlertDialog.Builder builder) {
        LayoutInflater inflater = Objects.requireNonNull(getActivity()).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_course, null);
        builder.setView(view);
        this.editText = view.findViewById(R.id.course_name_edit_text);
    }

    @Override
    void onConfirmation(Bundle resultBundle, GeneralConfirmationDialogFragment dialog) {
        resultBundle.putString(CONFIRMED_STRING, editText.getText().toString());
        callback.onAddCourseConfirmation(resultBundle, dialog);
    }

    @Override
    void onCancellation(Bundle resultBundle, GeneralConfirmationDialogFragment dialog) {
        callback.onAddCourseConfirmation(resultBundle, dialog);
    }

    public void setOneEditTextDialogListener(OneEditTextDialogListener listener){
        this.callback=listener;
    }

    public interface OneEditTextDialogListener {
        void onAddCourseConfirmation(Bundle resultBundle, GeneralConfirmationDialogFragment dialog);
    }
}