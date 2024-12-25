package com.example.chatbot.chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.chatbot.R;

import java.util.ArrayList;

public class ChatAdapter extends ArrayAdapter<ChatMessage> {
    public ChatAdapter(Context context, ArrayList<ChatMessage> messages) {
        super(context, 0, messages);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ChatMessage message = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    message.isUser() ? R.layout.user_message : R.layout.bot_message,
                    parent,
                    false
            );
        }

        TextView messageText = convertView.findViewById(R.id.messageText);
        messageText.setText(message.getMessage());

        return convertView;
    }
}

