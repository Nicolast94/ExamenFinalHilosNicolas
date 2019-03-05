package es.iessaldillo.nicol.nicolas_examenfinal_hilos.services;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import es.iessaldillo.nicol.nicolas_examenfinal_hilos.R;

public class NotificationService extends JobIntentService {

    private static final int JOB_ID = 7;
    private static final int NOTIFICATION_ID = 9001;
    private static String notifCity,notifWeatherDescription;

    public static void start(Context context,String city,String weatherDescription) {
        notifCity = city;
        notifWeatherDescription = weatherDescription;
        Intent intent = new Intent(context, NotificationService.class);
        enqueueWork(context, NotificationService.class, JOB_ID, intent);
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        Notification notification = new NotificationCompat.Builder(this, "Lamlataf")
                .setSmallIcon(R.drawable.ic_wb_sunny_black_24dp)
                .setContentTitle("El tiempo en " + notifCity)
                .setContentText(notifWeatherDescription)
                .build();
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(NOTIFICATION_ID, notification);
    }

}
