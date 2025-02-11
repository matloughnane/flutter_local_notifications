package com.capedkoala.ck_sms_plugin.models.styles;

import androidx.annotation.Keep;

import com.capedkoala.ck_sms_plugin.BitmapSource;

@Keep
public class BigPictureStyleInformation extends DefaultStyleInformation {
    public String contentTitle;
    public Boolean htmlFormatContentTitle;
    public String summaryText;
    public Boolean htmlFormatSummaryText;
    public String largeIcon;
    public BitmapSource largeIconBitmapSource;
    public String bigPicture;
    public BitmapSource bigPictureBitmapSource;
    public Boolean hideExpandedLargeIcon;

    public BigPictureStyleInformation(Boolean htmlFormatTitle, Boolean htmlFormatBody, String contentTitle, Boolean htmlFormatContentTitle, String summaryText, Boolean htmlFormatSummaryText, String largeIcon, BitmapSource largeIconBitmapSource, String bigPicture, BitmapSource bigPictureBitmapSource, Boolean hideExpandedLargeIcon) {
        super(htmlFormatTitle, htmlFormatBody);
        this.contentTitle = contentTitle;
        this.htmlFormatContentTitle = htmlFormatContentTitle;
        this.summaryText = summaryText;
        this.htmlFormatSummaryText = htmlFormatSummaryText;
        this.largeIcon = largeIcon;
        this.largeIconBitmapSource = largeIconBitmapSource;
        this.bigPicture = bigPicture;
        this.bigPictureBitmapSource = bigPictureBitmapSource;
        this.hideExpandedLargeIcon = hideExpandedLargeIcon;
    }
}
