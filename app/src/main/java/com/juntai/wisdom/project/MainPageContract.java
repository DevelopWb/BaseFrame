package com.juntai.wisdom.project;

import com.juntai.disabled.basecomponent.mvp.IPresenter;
import com.juntai.disabled.basecomponent.mvp.BaseIView;

public interface MainPageContract {
    String DELETE_NEWS_DRAFTS = "delete_news_drafts";
    String UPLOAD_HISTORY = "upload_history";


    interface IMainPageView extends BaseIView {
    }

    interface IMainPagePresent extends IPresenter<IMainPageView> {

        /**
         * 轨迹长传
         * @param data
         */
        void uploadHistory(String data, String tag);
    }
}
