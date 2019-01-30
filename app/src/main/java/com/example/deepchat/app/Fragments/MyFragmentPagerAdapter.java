package com.example.deepchat.app.Fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.Selection;

import com.example.deepchat.app.ChatActivity;

/**
 * Created by Kshitiz on 3/17/2018.
 * //---THIS CLASS FOR MANAGING FRAGMENTS---
 *
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter{

    public MyFragmentPagerAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 2:
                RequestFragment requestFragment=new RequestFragment();
                return requestFragment;
            case 0:
                ChatFragment chatFragment=new ChatFragment();
                return chatFragment;
            case 1:
                FriendFragment friendFragment=new FriendFragment();
                return friendFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch(position){
            case 2:
                return "SOLICITAÇÕES DE AMIZADE";
            case 0:
                return "CHATS";
            case 1:
                return "AMIGOS";
            default:
                return null;
        }
    }

}
