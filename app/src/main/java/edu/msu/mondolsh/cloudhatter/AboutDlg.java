package edu.msu.mondolsh.cloudhatter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import android.view.LayoutInflater;

public class AboutDlg  extends DialogFragment {

	public AboutDlg() {
	}

	@NonNull
	@SuppressLint("InflateParams")
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
	    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	    
	    builder.setTitle(R.string.about_dlg_title);
	    
	    // Get the layout inflater
	    LayoutInflater inflater = requireActivity().getLayoutInflater(); //Objects.requireNonNull(getActivity()).getLayoutInflater();

	    // Inflate and set the layout for the dialog
	    // Pass null as the parent view because its going in the dialog layout
	    builder.setView(inflater.inflate(R.layout.about_dlg, null))
	    // Add action buttons
	           .setPositiveButton(android.R.string.ok, (dialog, id) -> {
			   });
	    
	    final Dialog dlg = builder.create();
        
        dlg.setOnShowListener(dialog -> {
		});
	    
	    return dlg;
	}
}
