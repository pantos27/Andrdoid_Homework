package com.example.veierovioum.lesson13registration;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<User> users;
    private ArrayAdapter<User> adapter;
    private final int RQ_UserDetails=442;
    private final int RQ_AddUSer=224;
    private final int RQ_EditUser=654;

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
                this.users = (ArrayList<User>) savedInstanceState.get(getString(R.string.KEY_USERS));
            }
            else{
                //create and fill countires list
                this.users = new ArrayList<>();
                setUsers(this.users);
            }
        }

        adapter=new ArrayAdapter<User>(this,android.R.layout.simple_list_item_1, users);

        ListView lView=(ListView)(findViewById(R.id.listViewUsers));
        lView.setAdapter(adapter);
        //set the listener for an item on the list long click event
        lView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, UserDetails.class);
                //add all user details

                intent.putExtra(getString(R.string.KEY_USERDETAILS),adapter.getItem(position));
                intent.putExtra(getString(R.string.KEY_POSITION), position);

                startActivityForResult(intent, RQ_UserDetails);
                return true;
            }

        });



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabAddUser);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(MainActivity.this,NewUser.class);
                startActivityForResult(intent,RQ_AddUSer);
            }
        });
    }

    private void setUsers(ArrayList<User> _users) {
        _users.add(new User("Mark","Mathews","mark@pmail.com","555-4832","5 Bottle St.","06504823","drcd212"));
        _users.add(new User("Arthur", "Brown", "art@fire.org", "555-4050", "27 Rock St.", "32322500", "rbb2y541c"));
        _users.add(new User("Mark", "Mathews", "mark@pmail.com", "555-4832", "5 Bottle St.", "06504823", "drcd212"));
        _users.add(new User("Lisa", "Stanfield", "east@north.west", "555-5223", "300 Park Ave.", "0213152", "qwerty123"));

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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //sate countries list
        outState.putSerializable(getString(R.string.KEY_USERS),users);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //do nothing if no action taken
        if (resultCode==RESULT_CANCELED) return;

        int position=data.getIntExtra(getString(R.string.KEY_POSITION),-1);
        // TODO: 29/01/2016 check not -1
        switch (resultCode) {
            case R.id.fabDelete:
                users.remove(position);
                adapter.notifyDataSetChanged();
                break;

            case R.id.fabEdit: {
                Intent editIntent = new Intent(this,EditUser.class);
                editIntent.putExtra(getString(R.string.KEY_USERDETAILS),users.get(position));
                editIntent.putExtra(getString(R.string.KEY_POSITION), position);

                startActivityForResult(editIntent,RQ_EditUser);
                break;
            }

            case R.id.fabSave:{
                //get user data from edit activity
                User user= (User) data.getSerializableExtra(getString(R.string.KEY_USERDETAILS));
                //update array and list view
                users.set(position,user);
                adapter.notifyDataSetChanged();
            }
            case R.id.fabNewUser:{
                //get user data from edit activity
                User user= (User) data.getSerializableExtra(getString(R.string.KEY_USERDETAILS));
                //add user to arrat
                users.add(user);
                adapter.notifyDataSetChanged();
            }

        }

    }
}
