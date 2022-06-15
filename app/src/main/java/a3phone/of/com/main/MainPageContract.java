package a3phone.of.com.main;

import a3phone.of.disabled.basecomponent.mvp.IPresenter;
import a3phone.of.disabled.basecomponent.mvp.BaseIView;

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
