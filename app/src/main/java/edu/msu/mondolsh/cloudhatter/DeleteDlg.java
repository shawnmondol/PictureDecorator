package edu.msu.mondolsh.cloudhatter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import edu.msu.mondolsh.cloudhatter.Cloud.Cloud;

public class DeleteDlg extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Set the title
        builder.setTitle(R.string.delete_title);

        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Pass null as the parent view because its going in the dialog layout
        @SuppressLint("InflateParams")
        View view = inflater.inflate(R.layout.catalog_dlg, null);
        builder.setView(view);

        // Add a cancel button
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                // Cancel just closes the dialog box
            }
        });

        final AlertDialog dlg = builder.create();

        // Find the list view
        ListView list = (ListView)view.findViewById(R.id.listHattings);

        // Create an adapter
        final Cloud.CatalogAdapter adapter = new Cloud.CatalogAdapter(list);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new ListView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // Get the id of the one we want to delete
                String catName = adapter.getName(position);
                // Dismiss the dialog box
                dlg.dismiss();

                //The deleting dialog popup
                DeletingDlg delDlg = new DeletingDlg();
                delDlg.setCatName(catName);
                delDlg.setCatId(adapter.getId(position));
                delDlg.show(getActivity().getSupportFragmentManager(), "deleting");
            }

        });

        return dlg;
    }
}
