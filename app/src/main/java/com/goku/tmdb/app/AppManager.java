package com.goku.tmdb.app;

import android.app.Activity;

import androidx.fragment.app.Fragment;

import java.util.Stack;

/**
 * @author: Ciel
 * @date: 2019/12/4
 * activity堆栈式管理
 */

public class AppManager {

    private static Stack<Activity> sActivitystack;
    private static Stack<Fragment> sFragmentStack;
    private static AppManager sInstance;

    private AppManager() {
    }

    /**
     * 单例模式
     *
     * @return AppManager
     */
    public static AppManager getAppManager() {
        if (sInstance == null) {
            sInstance = new AppManager();
        }
        return sInstance;
    }

    public static Stack<Activity> getActivitystack() {
        return sActivitystack;
    }

    public static Stack<Fragment> getFragmentStack() {
        return sFragmentStack;
    }


    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        if (sActivitystack == null) {
            sActivitystack = new Stack<Activity>();
        }
        sActivitystack.add(activity);
    }

    /**
     * 移除指定的Activity
     */
    public void removeActivity(Activity activity) {
        if (activity != null) {
            sActivitystack.remove(activity);
        }
    }


    /**
     * 是否有activity
     */
    public boolean isActivity() {
        if (sActivitystack != null) {
            return !sActivitystack.isEmpty();
        }
        return false;
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity currentActivity() {
        Activity activity = sActivitystack.lastElement();
        return activity;
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity() {
        Activity activity = sActivitystack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : sActivitystack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
                break;
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = sActivitystack.size(); i < size; i++) {
            if (null != sActivitystack.get(i)) {
                finishActivity(sActivitystack.get(i));
            }
        }
        sActivitystack.clear();
    }

    /**
     * 获取指定的Activity
     *
     * @author kymjs
     */
    public Activity getActivity(Class<?> cls) {
        if (sActivitystack != null) {
            for (Activity activity : sActivitystack) {
                if (activity.getClass().equals(cls)) {
                    return activity;
                }
            }
        }
        return null;
    }


    /**
     * 添加Fragment到堆栈
     */
    public void addFragment(Fragment fragment) {
        if (sFragmentStack == null) {
            sFragmentStack = new Stack<Fragment>();
        }
        sFragmentStack.add(fragment);
    }

    /**
     * 移除指定的Fragment
     */
    public void removeFragment(Fragment fragment) {
        if (fragment != null) {
            sFragmentStack.remove(fragment);
        }
    }


    /**
     * 是否有Fragment
     */
    public boolean isFragment() {
        if (sFragmentStack != null) {
            return !sFragmentStack.isEmpty();
        }
        return false;
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Fragment currentFragment() {
        if (sFragmentStack != null) {
            Fragment fragment = sFragmentStack.lastElement();
            return fragment;
        }
        return null;
    }


    /**
     * 退出应用程序
     */
    public void AppExit() {
        try {
            finishAllActivity();
            // 杀死该应用进程
//          android.os.Process.killProcess(android.os.Process.myPid());
//            调用 System.exit(n) 实际上等效于调用：
//            Runtime.getRuntime().exit(n)
//            finish()是Activity的类方法，仅仅针对Activity，当调用finish()时，只是将活动推向后台，并没有立即释放内存，活动的资源并没有被清理；当调用System.exit(0)时，退出当前Activity并释放资源（内存），但是该方法不可以结束整个App如有多个Activty或者有其他组件service等不会结束。
//            其实android的机制决定了用户无法完全退出应用，当你的application最长时间没有被用过的时候，android自身会决定将application关闭了。
            //System.exit(0);
        } catch (Exception e) {
            sActivitystack.clear();
            e.printStackTrace();
        }
    }
}