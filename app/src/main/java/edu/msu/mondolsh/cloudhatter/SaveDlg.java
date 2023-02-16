package edu.msu.mondolsh.cloudhatter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import edu.msu.mondolsh.cloudhatter.Cloud.Cloud;

public class SaveDlg extends DialogFragment {

    private AlertDialog dlg;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Set the title
        builder.setTitle(R.string.save_dlg);

        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.save_dlg, null))
                // Add action buttons
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        EditText editName = (EditText)dlg.findViewById(R.id.editName);
                        save(editName.getText().toString());
                    }
                })

                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // Cancel just closes the dialog box
                    }
                });


        // Create the dialog box
        dlg = builder.create();


        return dlg;
    }

    /**
     * Actually save the hatting
     * @param name name to save it under
     */
    private void save(final String name) {
        if (!(getActivity() instanceof HatterActivity)) {
            return;
        }
        final HatterActivity activity = (HatterActivity) getActivity();
        final HatterView view = (HatterView) activity.findViewById(R.id.hatterView);

        new Thread(new Runnable() {
            @Override
            public void run() {

                Cloud cloud = new Cloud();
                final boolean ok = cloud.saveToCloud(name, view);
                view.post(new Runnable() {
                    @Override
                    public void run() {
                        if(!ok) {
                            /*
                             * If we fail to save, display a toast
                             */
                            // Please fill this in...
                            Toast.makeText(view.getContext(),
                                    R.string.save_fail,
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        }).start();
    }
}
