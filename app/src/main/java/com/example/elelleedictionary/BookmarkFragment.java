package com.example.elelleedictionary;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class BookmarkFragment extends Fragment {

    private FragmentListener listener;
    private DBHelper mDBHelper;

    public BookmarkFragment() {
        // Required empty public constructor
    }
    public static BookmarkFragment getNewInstance(DBHelper dbHelper){
        BookmarkFragment fragment = new BookmarkFragment();
        fragment.mDBHelper=dbHelper;
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bookmark, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        setHasOptionsMenu(true);
        ListView bookmarkList = view.findViewById(R.id.bookmarkList);
        BookmarkAdapter adapter = new BookmarkAdapter(getActivity(),mDBHelper.getAllWordFromBookmark());
        bookmarkList.setAdapter(adapter);
        adapter.setOnItemClick(new ListItemListener() {

            @Override
            public void onItemClick(int position) {
                if (listener!=null)
                    listener.onItemClick(String.valueOf(adapter.getItem(position)));

            }
        });
        adapter.setOnItemDeleteClick(new ListItemListener() {

            @Override
            public void onItemClick(int position) {
                    adapter.removeItem(position);
                    adapter.notifyDataSetChanged();
        }
        });
    }
    public void setOnFragmentListener(FragmentListener listener){
        this.listener= listener;

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_clear,menu);
    }
}
