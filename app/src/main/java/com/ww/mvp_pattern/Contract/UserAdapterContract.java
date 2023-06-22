package com.ww.mvp_pattern.Contract;

import com.ww.mvp_pattern.Model.User;

import java.util.List;

public interface UserAdapterContract {
    // View에서 RecyclerView의 관리를 위한 Adapter도 분리하기 위해 Presenter에게 위임하도록 Contract 정의

    // 1. Adapter UI 이벤트를 위한 interface
    interface View {
        void notifyAdapter();	// UI Update

        void setOnClickListener(OnItemClick clickListener);	// Click 이벤트 처리위한 리스너
    }

    // 2. Adapter 데이터 관리를 위한 Interface
    interface Model {
        void setData(List<User> users);	// Adapter 데이터 갱신 메서드

        User user(int position);	// 클릭한 user의 정보를 반환하는 메서드
    }
}
