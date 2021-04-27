package com.capedkoala.ck_sms_plugin.models.styles;

import androidx.annotation.Keep;

import com.capedkoala.ck_sms_plugin.models.MessageDetails;
import com.capedkoala.ck_sms_plugin.models.PersonDetails;

import java.util.ArrayList;

@Keep
public class MessagingStyleInformation extends DefaultStyleInformation {
    public PersonDetails person;
    public String conversationTitle;
    public Boolean groupConversation;
    public ArrayList<MessageDetails> messages;

    public MessagingStyleInformation(PersonDetails person, String conversationTitle, Boolean groupConversation, ArrayList<MessageDetails> messages, Boolean htmlFormatTitle, Boolean htmlFormatBody) {
        super(htmlFormatTitle, htmlFormatBody);
        this.person = person;
        this.conversationTitle = conversationTitle;
        this.groupConversation = groupConversation;
        this.messages = messages;
    }
}
