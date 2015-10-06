package add_new_contact_using_code.imufun.com.add_new_contact_using_code;

import android.content.ContentProviderOperation;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final EditText name = (EditText) findViewById(R.id.Name);
        final EditText phone = (EditText) findViewById(R.id.phone);
        final EditText work = (EditText) findViewById(R.id.work);
        final EditText home = (EditText) findViewById(R.id.home);

        Button save = (Button) findViewById(R.id.save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList ops = new ArrayList();
                ops.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                        .withValue("account_type", (Object) null)
                        .withValue("account_name", (Object) null).build());

                if (name.getText().toString() != null) {
                    ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                            .withValueBackReference("raw_contact_id", 0)
                            .withValue("mimetype", "vnd.android.cursor.item/name")
                            .withValue("data1", name.getText().toString()).build());
                }

                if (phone.getText().toString() != null) {
                    ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                            .withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/phone_v2")
                            .withValue("data1", phone.getText().toString()).withValue("data2", Integer.valueOf(2)).build());
                }

                if (work.getText().toString() != null) {
                    ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                            .withValueBackReference("raw_contact_id", 0)
                            .withValue("mimetype", "vnd.android.cursor.item/phone_v2")
                            .withValue("data1", work.getText().toString())
                            .withValue("data2", Integer.valueOf(2)).build());
                }

                if (home.getText().toString() != null) {
                    ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                            .withValueBackReference("raw_contact_id", 0)
                            .withValue("mimetype", "vnd.android.cursor.item/phone_v2")
                            .withValue("data1", home.getText().toString())
                            .withValue("data2", Integer.valueOf(2)).build());
                }

                Toast.makeText(getApplication(), "Your Contact Save", Toast.LENGTH_LONG).show();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
