package com.example.baitap9;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<Contact> {
    private Context context;
    private int resource;
    private List<Contact> arrContact;
    private int lastPosition = -1;

    public CustomAdapter(Context context, int resource, ArrayList<Contact> arrContact) {
        super(context, resource, arrContact);
        this.context = context;
        this.resource = resource;
        this.arrContact = arrContact;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.row_listview, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvName = convertView.findViewById(R.id.tvName);
            viewHolder.tvNumberPhone = convertView.findViewById(R.id.tvMaSinhVien);
            viewHolder.tvAvatar = convertView.findViewById(R.id.tvAvatar);
            viewHolder.cardView = (CardView) convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Contact contact = arrContact.get(position);

        // Set avatar background với gradient colors
        viewHolder.tvAvatar.setBackgroundResource(getAvatarBackground(contact.getColor()));
        viewHolder.tvAvatar.setText(String.valueOf(position + 1));
        viewHolder.tvName.setText(contact.getName());
        viewHolder.tvNumberPhone.setText(contact.getIdStudent());

        // Animation khi scroll
        animateItem(convertView, position);

        return convertView;
    }

    /**
     * Animation cho item khi scroll
     */
    private void animateItem(View view, int position) {
        if (position > lastPosition) {
            // Slide from right animation
            view.setTranslationX(200f);
            view.setAlpha(0f);

            ObjectAnimator animatorX = ObjectAnimator.ofFloat(view, "translationX", 200f, 0f);
            animatorX.setDuration(400);
            animatorX.setInterpolator(new DecelerateInterpolator());

            ObjectAnimator animatorAlpha = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
            animatorAlpha.setDuration(400);

            animatorX.start();
            animatorAlpha.start();

            lastPosition = position;
        }
    }

    /**
     * Lấy background tương ứng với màu
     */
    private int getAvatarBackground(int color) {
        // Map màu sang các drawable gradient đã tạo
        if (color == android.graphics.Color.RED) {
            return R.drawable.avatar_gradient_red;
        } else if (color == android.graphics.Color.GREEN) {
            return R.drawable.avatar_gradient_green;
        } else if (color == android.graphics.Color.GRAY) {
            return R.drawable.avatar_gradient_gray;
        } else if (color == android.graphics.Color.YELLOW) {
            return R.drawable.avatar_gradient_yellow;
        } else if (color == android.graphics.Color.BLACK) {
            return R.drawable.avatar_gradient_black;
        } else if (color == android.graphics.Color.BLUE) {
            return R.drawable.avatar_gradient_blue;
        } else if (color == android.graphics.Color.CYAN) {
            return R.drawable.avatar_gradient_cyan;
        }
        return R.drawable.avatar_gradient_blue; // Default
    }

    public class ViewHolder {
        TextView tvName, tvNumberPhone, tvAvatar;
        CardView cardView;
    }
}