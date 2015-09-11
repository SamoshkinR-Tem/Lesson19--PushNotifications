package com.l19_pushnotification;

/**
 * Created by R-Tem on 10.09.2015.
 */
public class ReceivedMessage {

    private int mMessageId;
    private String mMessage;
    private String mTitle;
    private String mSubtitle;
    private String mTickerText;
    private int mVibrate;
    private int mSound;

    public ReceivedMessage(int _MessageId, String _Message, String _Title, String _Subtitle,
                           String _TickerText, int _Vibrate, int _Sound) {
        this.mMessageId = _MessageId;
        this.mMessage = _Message;
        this.mTitle = _Title;
        this.mSubtitle = _Subtitle;
        this.mTickerText = _TickerText;
        this.mVibrate = _Vibrate;
        this.mSound = _Sound;
    }

    public int getMessageId() {
        return mMessageId;
    }
    public void setMessageId(int _MessageId) {
        this.mMessageId = _MessageId;
    }

    public String getMessage() {
        return mMessage;
    }
    public void setMessage(String _Message) {
        this.mMessage = _Message;
    }

    public String getTitle() {
        return mTitle;
    }
    public void setTitle(String _Title) {
        this.mTitle = _Title;
    }

    public String getSubtitle() {
        return mSubtitle;
    }
    public void setSubtitle(String _Subtitle) {
        this.mSubtitle = _Subtitle;
    }

    public String getTickerText() {
        return mTickerText;
    }
    public void setTickerText (String _TickerText) {
        this.mTickerText = _TickerText;
    }

    public int getVibrate() {
        return mVibrate;
    }
    public void setVibrate(int _Vibrate) {
        this.mVibrate = _Vibrate;
    }

    public int getSound() {
        return mSound;
    }
    public void setSound(int _Sound) {
        this.mSound = _Sound;
    }


}
