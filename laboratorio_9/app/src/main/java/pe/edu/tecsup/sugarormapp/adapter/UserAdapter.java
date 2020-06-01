package pe.edu.tecsup.sugarormapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.List;

import pe.edu.tecsup.sugarormapp.R;
import pe.edu.tecsup.sugarormapp.repository.UserRepository;
import pe.edu.tecsup.sugarormapp.model.User;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder>  {
    private Context context;
    private List<User> users;
    private List<File> imagesFiles;
    public UserAdapter(List<User> users){
        this.users = users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView picture;
        public TextView fullname;
        public TextView email;
        public ImageButton button;
        public ViewHolder(View itemView) {
            super(itemView);
            picture = itemView.findViewById(R.id.picture_image);
            fullname = itemView.findViewById(R.id.fullname_text);
            email = itemView.findViewById(R.id.email_text);
            button = itemView.findViewById(R.id.delete_button);
        }
    }

    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UserAdapter.ViewHolder viewHolder,final int position) {
        final User user = this.users.get(position);
        viewHolder.fullname.setText(user.getFullname());
        viewHolder.email.setText(user.getEmail());


        viewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserRepository.delete(user.getId());
                users.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, getItemCount());

            }
        });

    }

    @Override
    public int getItemCount() {
        return this.users.size();
    }


}
