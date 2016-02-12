package com.myluco.instagramclient;

import android.content.Context;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by lcc on 2/11/16.
 */
public class InstagramPhotoAdapter extends ArrayAdapter<InstagramPhoto>{
    //what data we need from the activity
    public InstagramPhotoAdapter(Context context,  List<InstagramPhoto> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
    }

    //use template (item_photo.xml) to display photos
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       //get photo to display
        InstagramPhoto photo = getItem(position);
        //check if it is recycled or not. If not, need to inflate
        if (convertView == null) {
            //false indicates to not attach to the list view yet
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_photo,parent,false);
        }
        //get the elements of the conevrtView
        TextView tvCaption = (TextView)convertView.findViewById(R.id.tvCaption);
        TextView tvUser = (TextView)convertView.findViewById(R.id.tvUser);
        TextView tvLikes = (TextView)convertView.findViewById(R.id.tvLikes);
        TextView tvTime = (TextView)convertView.findViewById(R.id.tvTime);

        ImageView ivPhoto = (ImageView)convertView.findViewById(R.id.ivPhoto);
        ImageView ivUser = (ImageView)convertView.findViewById(R.id.ivUser);
        //populate elements with photo data
        tvCaption.setText(photo.caption);
        tvUser.setText(photo.username);
        tvLikes.setText(String.valueOf(photo.likesCount));
        tvTime.setText(DateUtils.getRelativeTimeSpanString(photo.timestamp*1000,System.currentTimeMillis(),DateUtils.MINUTE_IN_MILLIS));
        //need to get the image. Will use Picasso
        //reset the image
        ivPhoto.setImageResource(0);
        ivUser.setImageResource(0);
        //request is asynchronous
        Picasso.with(getContext()).load(photo.imageUrl).into(ivPhoto);
        Picasso.with(getContext()).load(photo.userImageUrl).transform(new CircleTransform()).into(ivUser);
        return convertView;
    }
}
