package com.example.contenproviders;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.text.format.DateFormat;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    TextView salida;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        salida = findViewById(R.id.salida);
        salida.setMovementMethod(new ScrollingMovementMethod());
        if (ActivityCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.READ_CALL_LOG) !=
                PackageManager.PERMISSION_GRANTED )
        {
            final int REQUEST_CODE_ASK_PERMISSIONS = 123;
            ActivityCompat.requestPermissions(MainActivity.this, new
                            String[]{"android.permission.READ_CALL_LOG"},
                    REQUEST_CODE_ASK_PERMISSIONS);
            ActivityCompat.requestPermissions(MainActivity.this, new
                            String[]{"android.permission.WRITE_CALL_LOG"},
                    REQUEST_CODE_ASK_PERMISSIONS);
            ActivityCompat.requestPermissions(MainActivity.this, new
                            String[]{"android.permission.READ_CONTACTS"},
                    REQUEST_CODE_ASK_PERMISSIONS);
            ActivityCompat.requestPermissions(MainActivity.this, new
                            String[]{"android.permission.WRITE_CONTACTS"},
                    REQUEST_CODE_ASK_PERMISSIONS);
        }
        if (ActivityCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.WRITE_CALL_LOG) !=
                PackageManager.PERMISSION_GRANTED)
        {
            final int REQUEST_CODE_ASK_PERMISSIONS = 123;
            ActivityCompat.requestPermissions(MainActivity.this, new
                            String[]{"android.permission.READ_CALL_LOG"},
                    REQUEST_CODE_ASK_PERMISSIONS);
            ActivityCompat.requestPermissions(MainActivity.this, new
                            String[]{"android.permission.WRITE_CALL_LOG"},
                    REQUEST_CODE_ASK_PERMISSIONS);
            ActivityCompat.requestPermissions(MainActivity.this, new
                            String[]{"android.permission.READ_CONTACTS"},
                    REQUEST_CODE_ASK_PERMISSIONS);
            ActivityCompat.requestPermissions(MainActivity.this, new
                            String[]{"android.permission.WRITE_CONTACTS"},
                    REQUEST_CODE_ASK_PERMISSIONS);
        }
        if (ActivityCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.READ_CONTACTS) !=
                PackageManager.PERMISSION_GRANTED)
        {
            final int REQUEST_CODE_ASK_PERMISSIONS = 123;
            ActivityCompat.requestPermissions(MainActivity.this, new
                            String[]{"android.permission.READ_CALL_LOG"},
                    REQUEST_CODE_ASK_PERMISSIONS);
            ActivityCompat.requestPermissions(MainActivity.this, new
                            String[]{"android.permission.WRITE_CALL_LOG"},
                    REQUEST_CODE_ASK_PERMISSIONS);
            ActivityCompat.requestPermissions(MainActivity.this, new
                            String[]{"android.permission.READ_CONTACTS"},
                    REQUEST_CODE_ASK_PERMISSIONS);
            ActivityCompat.requestPermissions(MainActivity.this, new
                            String[]{"android.permission.WRITE_CONTACTS"},
                    REQUEST_CODE_ASK_PERMISSIONS);
        }
        if (ActivityCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.WRITE_CONTACTS) !=
                PackageManager.PERMISSION_GRANTED)
        {
            final int REQUEST_CODE_ASK_PERMISSIONS = 123;
            ActivityCompat.requestPermissions(MainActivity.this, new
                            String[]{"android.permission.READ_CALL_LOG"},
                    REQUEST_CODE_ASK_PERMISSIONS);
            ActivityCompat.requestPermissions(MainActivity.this, new
                            String[]{"android.permission.WRITE_CALL_LOG"},
                    REQUEST_CODE_ASK_PERMISSIONS);
            ActivityCompat.requestPermissions(MainActivity.this, new
                            String[]{"android.permission.READ_CONTACTS"},
                    REQUEST_CODE_ASK_PERMISSIONS);
            ActivityCompat.requestPermissions(MainActivity.this, new
                            String[]{"android.permission.WRITE_CONTACTS"},
                    REQUEST_CODE_ASK_PERMISSIONS);
        }
    }
    public void llamadasSalientes(View view) {
        String[] TIPO_LLAMADA = {"","entrante","saliente","perdida"};
        Uri llamadas= Uri.parse("content://call_log/calls");
//Cursor c = getContentResolver().query(llamadas, null, null,null, null);
        String[] proyeccion = new String[] {
                CallLog.Calls.DATE, CallLog.Calls.DURATION,
                CallLog.Calls.NUMBER, CallLog.Calls.TYPE };
        String[] argsSelecc = new String[] {"2"};
        Cursor c = getContentResolver().query(
                llamadas, // Uri del ContentProvider
                proyeccion, // Columnas que nos interesan
                "type = ?", // consulta WHERE
                argsSelecc, // parámetros de la consulta anterior
                "date DESC"); // Ordenado por fecha, orden ascenciente
        while (c.moveToNext()) {
            salida.append("\n"
                    + DateFormat.format("dd/MM/yy k:mm (",
                    c.getLong(c.getColumnIndex(CallLog.Calls.DATE)))
                    +
                    c.getString(c.getColumnIndex(CallLog.Calls.DURATION)) + ") "
                    + c.getString(c.getColumnIndex(CallLog.Calls.NUMBER))
                    + ", "
                    + TIPO_LLAMADA[Integer.parseInt(c.getString(c
                    .getColumnIndex(CallLog.Calls.TYPE)))]);
}
    }
    public void realizarLLamada(View view) {
        ContentValues valores = new ContentValues();
        valores.put(CallLog.Calls.DATE, new Date().getTime() );
        valores.put(CallLog.Calls.NUMBER, "555555555");
        valores.put(CallLog.Calls.DURATION, "55");
        valores.put(CallLog.Calls.TYPE, CallLog.Calls.INCOMING_TYPE);
        Uri nuevoElemento = getContentResolver().insert(CallLog.Calls.CONTENT_URI, valores);
}
    public void verContactos(View view) {
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,null,
                null, null, null);
        String[] projection = new String[]
                {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                        ContactsContract.CommonDataKinds.Phone.NUMBER };
        Cursor names = getContentResolver().query(uri, projection, null,
                null, null);
        int indexName =
                names.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
        int indexNumber =
                names.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
        names.moveToFirst();
        salida.append("NOMBRE TELEFONO\n");
        do {
            String nombreContacto = names.getString(indexName);
            Log.e("Name new:", nombreContacto);
            String numeroContacto = names.getString(indexNumber);
            Log.e("Number new:","::"+numeroContacto);
            salida.append(nombreContacto +"-----"+numeroContacto+ "\n");
        } while (names.moveToNext());
    }
    public void detalleContacto(View view) {
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,null,
                null, null, null);
        String[] projection = new String[]
                {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                        ContactsContract.CommonDataKinds.Phone.NUMBER ,ContactsContract.CommonDataKinds.Email.DATA };
        Cursor names = getContentResolver().query(uri, projection, null,
                null, null);
        int indexName =
                names.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
        int indexNumber =
                names.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
        names.moveToFirst();
        do {
            String name = names.getString(indexName);
            Log.e("Name new:", name);
            String number = names.getString(indexNumber);
            Log.e("Number new:","::"+number);
            salida.append("Name new:"+ name+ "\n");
            salida.append("Number new ::"+number+ "\n");
        } while (names.moveToNext());
// email
        while (cur.moveToNext()) {
            String id =
                    cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
            Cursor email = cr.query(
                    ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                    null,
                    ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?",
            new String[]{id}, null);
            while (email.moveToNext()) {
//to get the contact names
// if the email addresses were stored in an array
                String emailid =
                        email.getString(email.getColumnIndex(ContactsContract.CommonDataKinds.
                                Email.DATA));
                Log.e("Email id ::", "" +emailid);
                salida.append("Email id ::"+ emailid+ "\n");
                String emailType =
                        email.getString(email.getColumnIndex(ContactsContract.CommonDataKinds.
                                Email.TYPE));
                Log.e("Email Type ::","" + emailType);
                salida.append("Email Type ::"+ emailType+ "\n");
            }
            email.close();
        }
//address
        while (cur.moveToNext()) {
            String id =
                    cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
            Cursor addrCur =
                    cr.query(ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_URI
                            ,null,
                            ContactsContract.CommonDataKinds.StructuredPostal.CONTACT_ID+ " = ?",
                            new String[] { id },null);
            while(addrCur.moveToNext()) {
                String street =
                        addrCur.getString(addrCur.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.STREET));
                Log.e("Street ::","" + street);
                salida.append("Street ::"+ street+ "\n");
                String city =
                        addrCur.getString(addrCur.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.CITY));
                Log.e("City ::", "" +city);
                salida.append("City ::"+ city+ "\n");
                String state =
                        addrCur.getString(addrCur.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.REGION));
                Log.e("State ::", "" +state);
                salida.append("State ::"+ state+ "\n");
                String postalCode =
                        addrCur.getString(addrCur.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.POSTCODE));
                Log.e("Postal Code ::", "" +postalCode);
                salida.append("Postal Code ::"+ postalCode+ "\n");
            }
            addrCur.close();
        }
    }
}