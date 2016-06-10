package com.testes.agenda;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;


import java.util.Calendar;

/**
 * Created by cristian on 03/06/16.
 */
public class DataPickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{

    @Override
    public Dialog onCreateDialog(Bundle savedInstance){
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        // Não sei ainda o que eu vou fazer aqui ...
        //EditText etData = (EditText) fin
        CadastroContatos.date = year +"/"+ (monthOfYear + 1)+"/"+ dayOfMonth;
        Log.v("Data", year +"/"+ monthOfYear+"/"+ dayOfMonth);
    }

}
