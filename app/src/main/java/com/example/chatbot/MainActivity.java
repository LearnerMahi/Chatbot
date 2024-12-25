package com.example.chatbot;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.chatbot.chat.ChatAdapter;
import com.example.chatbot.chat.ChatMessage;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView chatListView;
    private EditText messageInput;
    private ImageButton sendButton;
    private ChatAdapter chatAdapter;
    private ArrayList<ChatMessage> chatMessages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chatListView = findViewById(R.id.chatListView);
        messageInput = findViewById(R.id.messageInput);
        sendButton = findViewById(R.id.sendButton);

        chatMessages = new ArrayList<>();
        chatAdapter = new ChatAdapter(this, chatMessages);
        chatListView.setAdapter(chatAdapter);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = messageInput.getText().toString().trim();
                if (!message.isEmpty()) {
                    sendMessage(message);
                    messageInput.setText("");
                }
            }
        });
    }

    private void sendMessage(String message) {
        ChatMessage userMessage = new ChatMessage(message, true);
        chatMessages.add(userMessage);

        // Generate bot response
        String botResponse = getBotResponse(message);
        ChatMessage botMessage = new ChatMessage(botResponse, false);
        chatMessages.add(botMessage);

        chatAdapter.notifyDataSetChanged();
        chatListView.smoothScrollToPosition(chatMessages.size() - 1);
    }

    private String getBotResponse(String message) {
        // Add your chatbot logic here
        message = message.toLowerCase();

        if (message.contains("hello") || message.contains("hi")) {
            return "Hello! How can I help you today?";
        } else if (message.contains("how are you")) {
            return "I'm doing great! Thanks for asking.";
        } else if (message.contains("bye")) {
            return "Goodbye! Have a great day!";
        } else {
            return "I'm still learning. Could you please rephrase that?";
        }
    }
}
