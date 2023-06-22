package com.ww.mvp_pattern.Contract;

public interface OnItemClick {
    // UserAdapterContract의 setOnClickListener() 파라미터인 OnItemClick 인터페이스 정의
    // RecyclerView의 Item 클릭 이벤트 처리를 Presenter에게 위임하기 위해 사용

    void onItemClick(int position); //단순히 클릭한 User의 번호를 전달
}
