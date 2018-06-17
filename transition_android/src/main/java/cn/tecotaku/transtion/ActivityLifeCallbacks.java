package cn.tecotaku.transtion;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/**
 * Created by hakurei on 16-10-26.
 */
public class ActivityLifeCallbacks implements Application.ActivityLifecycleCallbacks {
    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {
        if (activity.equals(AnimatorManager.activity)) AnimatorManager.isRegistActivityFront = false;
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}
