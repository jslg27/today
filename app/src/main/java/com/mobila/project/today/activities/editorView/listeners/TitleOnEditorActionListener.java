package com.mobila.project.today.activities.editorView.listeners;

import android.app.Activity;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.mobila.project.today.R;

public class TitleOnEditorActionListener implements TextView.OnEditorActionListener {
    private Activity activity;

    public TitleOnEditorActionListener(Activity activity) {
        this.activity=activity;
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_NEXT) {
            EditText editTextNote = activity.findViewById(R.id.editor_content);
            editTextNote.requestFocus();
            return true;
        }
        return false;
    }
}
