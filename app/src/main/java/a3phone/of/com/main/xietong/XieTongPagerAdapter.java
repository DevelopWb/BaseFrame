package a3phone.of.com.main.xietong;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import a3phone.of.com.main.R;



public class XieTongPagerAdapter extends FragmentPagerAdapter {
    private Context mContext;
   private List<String> titles;


    public void setTitles(List<String> titles) {
        this.titles = titles;
        notifyDataSetChanged();
    }

    public XieTongPagerAdapter(FragmentManager fm, Context mContext, List<String> titles) {
        super(fm);
        this.mContext = mContext;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return new AllThingsFragment();
    }

    @Override
    public int getCount() {
        return titles == null ? 0 : titles.size();
    }

    /**
     * 自定义底部消息tab
     * @param position
     * @return
     */
    public View getTabView(int position) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.xietong_tabitem, null);
        TextView title = v.findViewById(R.id.tabitem_text);
        title.setText(titles.get(position));
        return v;
    }




}
