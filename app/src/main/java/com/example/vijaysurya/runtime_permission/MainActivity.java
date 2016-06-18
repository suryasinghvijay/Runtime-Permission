package com.example.vijaysurya.runtime_permission;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback {

  private static final int REQUEST_CONTACTS = 1;
  private View mLayout;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mLayout = findViewById(R.id.layout);
    Button button = (Button) findViewById(R.id.contacts);

    if (Build.VERSION.SDK_INT < 23) {
      button.setVisibility(View.GONE);
    }


  }

  public void ShowContacts(View view) {
    Log.i("TAG", "Show contacts button pressed. Checking permissions.");

    if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_CONTACTS) != (PackageManager.PERMISSION_DENIED)) {
      Log.i("TAG", "Contact permissions has NOT been granted. Requesting permissions.");
      requestContactsPermissions();
    } else {
      Log.i("TAG", "Contact permissions have already been granted. Displaying contact details.");

    }
  }

  private void requestContactsPermissions() {

    if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.WRITE_CONTACTS)) {
      Log.i("TAG", "Displaying contacts permission rationale to provide additional context.");

      Snackbar.make(mLayout, R.string.permission_contacts, Snackbar.LENGTH_INDEFINITE)
          .setAction(R.string.ok, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.WRITE_CONTACTS},
                  REQUEST_CONTACTS);
            }
          }).show();
    } else {
      // Camera permission has not been granted yet. Request it directly.
      ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.WRITE_CONTACTS}, REQUEST_CONTACTS);
    }

  }
}
