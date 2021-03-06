package edu.gatech.cs2340.gtrational.rational.controller;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;

import java.text.ParseException;

import edu.gatech.cs2340.gtrational.rational.R;

/**
 * A fragment for the dashboard screen.
 */
public class GraphFilterDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Filter")
                .setPositiveButton("Apply", (DialogInterface dialog, int id) -> {
                    //Parse date fields
                    EditText startDateField = getDialog().findViewById((R.id.startDate));
                    EditText endDateField = getDialog().findViewById((R.id.endDate));
                    String startDate = startDateField.getText().toString();
                    String endDate = endDateField.getText().toString();

                    RadioButton month_button = getDialog().findViewById(R.id.month);
                    RadioButton year_button = getDialog().findViewById(R.id.year);

                    try {
                        long startLong = MapFilterDialogFragment.dateToSeconds(startDate);
                        long endLong = MapFilterDialogFragment.dateToSeconds(endDate);
                        ((MainDashboardActivity) getActivity()).setGraphData(startLong, endLong,
                                year_button.isChecked());
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }

                })
                .setNegativeButton("Cancel", (DialogInterface dialog, int id) -> {
                })
                .setView(R.layout.fragment_graph_filter);
        return builder.create();
    }

}
