package com.acadgildbatch5.DialogExample;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.acadgildbatch5.R;

/**
 * Created by Divyanshu on 25-12-2016.
 */

public class AlertDFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return  new AlertDialog.Builder(getActivity())

                .setIcon(R.drawable.restart)
                .setTitle("Acadgild Dialog Title  for AlertDFragment")
                .setMessage("This is the message for the AlertDfragment we want to show the user!   d kjfk iedid ufeiuitui rutureiutiureieut iutiuiruirutierweytuiyeryuitiueritireityierhitiuryt iueritiyeiutyeiuey uiyerierytiut")

                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getActivity().finish();
                        Toast.makeText(getActivity(),"AlertDFragment clicked  POSITIVE",Toast.LENGTH_SHORT).show();
                    }
                })

                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Toast.makeText(getActivity(),"AlertDFragment clicked  Negative",Toast.LENGTH_SHORT).show();
                    }
                })
                .create();

    }
}
