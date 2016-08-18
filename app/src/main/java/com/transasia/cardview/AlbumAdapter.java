package com.transasia.cardview;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by babu.c on 8/18/2016.
 */
public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.MyViewHolder> {
    private Context context;
    private List<Album> list;

    public class MyViewHolder extends RecyclerView.ViewHolder{
       private TextView title,count;
       private ImageView thumbnail,overflow;
        public MyViewHolder(View itemView) {
            super(itemView);
            title=(TextView)itemView.findViewById(R.id.title);
            count=(TextView)itemView.findViewById(R.id.count);
            thumbnail=(ImageView)itemView.findViewById(R.id.thumbnail);
            overflow=(ImageView)itemView.findViewById(R.id.overflow);

        }
    }
    public AlbumAdapter(Context con,List<Album> List){
            this.context=con;
            this.list=List;
    }
    @Override
    public AlbumAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.album_card,parent,false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final AlbumAdapter.MyViewHolder holder, int position) {
        Album album=list.get(position);
        holder.title.setText(album.getName());
        holder.count.setText(album.getNumOfSong()+"songs");
        Glide.with(context).load(album.getThumbnail()).into(holder.thumbnail);
        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupMenu(holder.overflow);

            }
        });


    }
    private void showPopupMenu(View v){
        PopupMenu menu=new PopupMenu(context,v);
        MenuInflater inflater=menu.getMenuInflater();
        inflater.inflate(R.menu.menu_album,menu.getMenu());
        menu.setOnMenuItemClickListener(new MyMenuItemClickListener());
        menu.show();
    }
   class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener{
        public MyMenuItemClickListener()
        {

        }
       @Override
       public boolean onMenuItemClick(MenuItem item) {
           switch (item.getItemId()){
               case R.id.action_add_favourite:
                   Toast.makeText(context,"Add fovraite",Toast.LENGTH_SHORT).show();
                   return true;
               case R.id.action_play_next:
                   Toast.makeText(context,"Play",Toast.LENGTH_SHORT).show();
                   return true;
               default:
           }
           return false;
       }
   }
    @Override
    public int getItemCount() {
        return list.size();
    }
}
