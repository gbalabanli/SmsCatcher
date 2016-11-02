package com.proje.smsCatcher;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SMSReceiverActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		final EditText email = (EditText) findViewById(R.id.mailText);
		final EditText password = (EditText) findViewById(R.id.passwordText);
		Button saveButton = (Button) this.findViewById(R.id.saveButton);

		saveButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				SMSReceiver.username = email.getText().toString();
				SMSReceiver.password = password.getText().toString();
				
			}
		});

	}
}