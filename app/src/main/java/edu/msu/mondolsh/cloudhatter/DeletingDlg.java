package edu.msu.mondolsh.cloudhatter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import edu.msu.mondolsh.cloudhatter.Cloud.Cloud;

public class DeletingDlg extends DialogFragment {

    private final static String NAME = "name";
    private final static String ID = "id";
    /**
     * Name of item
     */
    private String catName;

    /**
     * Id of item
     */
    private String catId;

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString(NAME, catName);
        outState.putString(ID, catId);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null)
        {
            catId = savedInstanceState.getString(ID);
            catName = savedInstanceState.getString(NAME);
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Set the title
        builder.setTitle(R.string.deleting_title);
        String message = getString(R.string.delete_sure) + " " + catName;
        builder.setMessage(message);
        builder.setNegativeButton(android.R.string.cancel,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // Exit interface
                    }
                });
        builder.setPositiveButton(android.R.string.ok,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Delete id from catalog
                        delete(catId);
                    }
                });


        // Create the dialog box
        final AlertDialog dlg = builder.create();

        return dlg;
    }

    /**
     * Deleted item associated with id
     * from the cloud
     * @param id
     */
    public void delete(final String id)
    {
        // Get a reference to the view we are going to load into
        final HatterView view = (HatterView)getActivity().findViewById(R.id.hatterView);

        new Thread(new Runnable() {
            @Override
            public void run() {
                Cloud cloud = new Cloud();
                final boolean deleted = cloud.deleteFromCloud(id);
                view.post(new Runnable() {
                    @Override
                    public void run() {
                        if(!deleted) {
                            /*
                             * If we fail to delete, display a toast
                             */
                            Toast.makeText(view.getContext(),
                                    R.string.delete_fail,
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }).start();
    }


    /**
     * Sets the name of item
     * @param catName
     */
    public void setCatName(String catName) {
        this.catName = catName;
    }


    /**
     * Sets id of item
     * @param id
     */
    public void setCatId(String id) {
        catId = id;
    }
}
