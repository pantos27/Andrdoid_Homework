package com.example.veierovioum.lesson13registration;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //check if users list exists
        if (users ==null){
            if (savedInstanceState!=null){
                //get users list from saved state
                this.users = (ArrayList<Country>) savedInstanceState.get(getString(R.string.KEY_USERS));
            }
            else{
                //create and fill countires list
                this.users = new ArrayList<>();
                setUsers(this.users);
            }
        }

        adapter=new ArrayAdapter<Country>(this,android.R.layout.simple_list_item_1, users);

        ListView lView=(ListView)(findViewById(R.id.listViewCountries));
        lView.setAdapter(adapter);
        //set the listener for an item on the list click
        lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);

                intent.putExtra(getString(R.string.KEY_CITIES), adapter.getItem(position).getCities());

                startActivity(intent);
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

    private void setUsers(ArrayList<User> _users) {
        _users.add(new User("Mark","Mathews","mark@pmail.com","555-4832","5 Bottle St.","06504823","drcd212"));
        _users.add(new User("Mark","Mathews","mark@pmail.com","555-4832","5 Bottle St.","06504823","drcd212"));

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
