package pe.edu.tecsup.sugarormapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.List;

import pe.edu.tecsup.sugarormapp.R;
import pe.edu.tecsup.sugarormapp.adapter.UserAdapter;
import pe.edu.tecsup.sugarormapp.repository.UserRepository;
import pe.edu.tecsup.sugarormapp.model.User;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private static final int REGISTER_FORM_REQUEST = 100;

    private RecyclerView usersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configure ReciclerView
        usersList = findViewById(R.id.user_list);
        usersList.setLayoutManager(new LinearLayoutManager(this));

        // Set Data Adapter to ReciclerView
        List<User> users = UserRepository.list();
        usersList.setAdapter(new UserAdapter(users));

    }

    public void callRegisterForm(View view){
        startActivityForResult(new Intent(this, RegisterActivity.class), REGISTER_FORM_REQUEST);
    }

    // return from RegisterActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // refresh data
        UserAdapter adapter = (UserAdapter)usersList.getAdapter();

        List<User> users = UserRepository.list();
        adapter.setUsers(users);
        adapter.notifyDataSetChanged();

    }

}
