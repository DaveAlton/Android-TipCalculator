package com.davealton.tipcalculator;

import android.os.Bundle;
import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

	private static final String BILL_TOTAL = "BILL_TOTAL";
	private static final String CUSTOM_PERCENT = "CUSTOM_PERCENT";
	private double currentBillTotal; // bill amount entered by the user
	private int currentCustomPercent; // tip % set with the SeekBar
	private EditText tip10EditText; // displays 10% tip
	private EditText total10EditText; // displays total with 10% tip
	private EditText tip15EditText; // displays 15% tip
	private EditText total15EditText; // displays total with 15% tip
	private EditText billEditText; // accepts user input for bill total
	private EditText tip20EditText; // displays 20% tip
	private EditText total20EditText; // displays total with 20% tip
	private TextView customTipTextView; // displays custom tip percentage
	private EditText tipCustomEditText; // displays custom tip amount
	private EditText totalCustomEditText; // displays total with custom tip
	private SeekBar customSeekBar; // used to generate custom tip percentage
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); // call superclass's version
		setContentView(R.layout.activity_main); // inflate the GUI
		// check if app just started or is being restored from memory
		if (savedInstanceState == null) // the app just started running
		{
			currentBillTotal = 0.0; // initialize the bill amount to zero
			currentCustomPercent = 18; // initialize the custom tip to 18%
		} // end if
		else // app is being restored from memory, not executed from scratch
		{
			// initialize the bill amount to saved amount
			currentBillTotal = savedInstanceState.getDouble(BILL_TOTAL);
			// initialize the custom tip to saved tip percent
			currentCustomPercent = savedInstanceState.getInt(CUSTOM_PERCENT);
		} // end else
		// get references to the 10%, 15% and 20% tip and total EditTexts
		tip10EditText = (EditText) findViewById(R.id.tip10EditText);
		total10EditText = (EditText) findViewById(R.id.total10EditText);
		tip15EditText = (EditText) findViewById(R.id.tip15EditText);
		total15EditText = (EditText) findViewById(R.id.total15EditText);
		tip20EditText = (EditText) findViewById(R.id.tip20EditText);
		total20EditText = (EditText) findViewById(R.id.total20EditText);
		// get the TextView displaying the custom tip percentage
		customTipTextView = (TextView) findViewById(R.id.customTipTextView);
		// get the custom tip and total EditTexts
		tipCustomEditText = (EditText) findViewById(R.id.tipCustomEditText);
		totalCustomEditText = (EditText) findViewById(R.id.totalCustomEditText);
		// get the billEditText
		billEditText = (EditText) findViewById(R.id.billEditText);
		
		// billEditTextWatcher handles billEditText's onTextChanged event
		billEditText.addTextChangedListener(new TextWatcher() {
			// called when the user enters a number
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// convert billEditText's text to a double
				try {
					currentBillTotal = Double.parseDouble(s.toString());
				} // end try
				catch (NumberFormatException e) {
					currentBillTotal = 0.0; // default if an exception occurs
				} // end catch
				// update the standard and custom tip EditTexts
				updateStandard(); // update the 10, 15 and 20% EditTexts
				updateCustom(); // update the custom tip EditTexts
			} // end method onTextChanged
			@Override
			public void afterTextChanged(Editable s) {
			} // end method afterTextChanged
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			} // end method beforeTextChanged
		}); // end billEditTextWatcher
		
		// get the SeekBar used to set the custom tip amount
		customSeekBar = (SeekBar) findViewById(R.id.customSeekBar);
		customSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
		// update currentCustomPercent, then call updateCustom
		@Override
		public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
			// sets currentCustomPercent to position of the SeekBar's thumb
			currentCustomPercent = seekBar.getProgress();
			updateCustom(); // update EditTexts for custom tip and total
			} // end method onProgressChanged
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			} // end method onStartTrackingTouch
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			} // end method onStopTrackingTouch
		}); // end OnSeekBarChangeListener
	}
	
	// save values of billEditText and customSeekBar
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putDouble(BILL_TOTAL, currentBillTotal);
		outState.putInt(CUSTOM_PERCENT, currentCustomPercent);
	} // end method onSaveInstanceState
}
