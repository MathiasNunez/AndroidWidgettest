package mnunez.test.appwidgettest;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.widget.RemoteViews;

/**
 * Created by mnunez on 30/08/16.
 */
public class AppWidgetProviderTest extends AppWidgetProvider {

    private int counter = 0;
    private Context mContext;

    @Override
    public void onUpdate(final Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        mContext = context;
        // Perform this loop procedure for each App Widget that belongs to this provider
        for (int appWidgetId : appWidgetIds) {
            // Create an Intent to launch ExampleActivity
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.appwidgettest_layout);
            views.setTextViewText(R.id.test_text, context.getString(R.string.test_wiget_text, counter));
            // Tell the AppWidgetManager to perform an update on the current app widget
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ComponentName thisWidget = new ComponentName(mContext, AppWidgetProviderTest.class);
                RemoteViews views = new RemoteViews(mContext.getPackageName(), R.layout.appwidgettest_layout);
                views.setTextViewText(R.id.test_text, mContext.getString(R.string.test_wiget_text, counter));
                counter++;
                AppWidgetManager.getInstance(mContext).updateAppWidget(thisWidget, views);
                handler.postDelayed(this, 2000);
            }
        }, 1500);
    }

}
