package com.ww.mvp_pattern.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ww.mvp_pattern.R;
import com.ww.mvp_pattern.Contract.OnItemClick;
import com.ww.mvp_pattern.Contract.UserAdapterContract;
import com.ww.mvp_pattern.Model.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder>
        implements UserAdapterContract.View, UserAdapterContract.Model {
    // RecyclerView를 관리한 Adapter 정의
    // 일반적으로 View는 Adapter를 관리(Data변경, UI Update)하지만, 여기선 Adapter도 View에서 분리하여 Presenter에게 관리를 위임해서
    // Presenter가 Adapter를 통해 UI Update & Data 변경을 하도록 Presenter와 연결되게 정의

    private final String TAG = "UserAdapter";
    private Context context;
    private OnItemClick onItemClick;
    private List<User> users;

    // UserAdapter 생성자
    public UserAdapter(Context context) { this.context = context; }

    // onCreateViewHolder 구현
    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);

        return new UserAdapter.ViewHolder(view);
    }

    // onBindViewHolder 구현 - RecyclerView Item 세팅 부분
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        User user = users.get(position);

        // Glide 이미지 라이브러리 사용
        Glide.with(context)
                .load(user.getImage())
                .apply(new RequestOptions().circleCrop())
                .into(holder.image);

        // Item 세팅 (이름 + 지역 + 블로그URL + Follower & Following)
        holder.name.setText(user.getLogin());
        holder.location.setText(user.getLocation());
        holder.blog.setText(user.getBlog());
        holder.follower.setText(String.valueOf(user.getFollowers()));
        holder.following.setText(String.valueOf(user.getFollowing()));
    }

    @Override
    public int getItemCount() {
        return users != null ? users.size() : 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    // ViewHolder 클래스 정의
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name, location, blog, follower, following;
        ImageView image;

        // ViewHolder 생성자
        public ViewHolder(@NonNull View view) {
            super(view);

            name = view.findViewById(R.id.user_name);
            location = view.findViewById(R.id.user_location);
            blog = view.findViewById(R.id.user_blog);
            follower = view.findViewById(R.id.user_follower);
            following = view.findViewById(R.id.user_following);

            image = view.findViewById(R.id.user_image);

            // RecyclerView Item 클릭리스너 등록
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // OnItemClick 인터페이스 메서드 사용 (Presenter에게 이벤트 처리 위임)
                    onItemClick.onItemClick(getAdapterPosition());
                }
            });
        }
    }

    // Adapter.View Contract 추상메서드 정의 (Presenter에서 사용)
    @Override
    public void notifyAdapter() {
        notifyDataSetChanged();
    }

    // Adapter.View Contract 추상메서드 정의 (Presenter에서 사용)
    @Override
    public void setOnClickListener(OnItemClick clickListener) {
        this.onItemClick = clickListener;
    }

    // Adapter.Model Contract 추상메서드 정의 (Presenter에서 사용)
    @Override
    public void setData(List<User> users) {
        this.users = users;
    }

    // Adapter.View Contract 추상메서드 정의 (Presenter에서 사용)
    @Override
    public User user(int position) {
        return users.get(position);
    }
}