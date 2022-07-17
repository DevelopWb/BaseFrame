package a3phone.of.com.main.multi;

import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.Date;
import java.util.List;

import a3phone.of.com.main.R;
import a3phone.of.com.main.base.selectPics.SelectPhotosFragment;
import a3phone.of.com.main.bean.multiBean.BaseNormalRecyclerviewBean;
import a3phone.of.com.main.bean.multiBean.ItemFragmentBean;
import a3phone.of.com.main.bean.multiBean.MultipleItem;
import a3phone.of.com.main.bean.multiBean.TextKeyValueBean;
import a3phone.of.disabled.basecomponent.utils.CalendarUtil;
import a3phone.of.disabled.basecomponent.utils.DisplayUtil;
import a3phone.of.disabled.basecomponent.utils.PickerManager;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @UpdateUser: 更新者
 */
public class MultiAdapter extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {

    private boolean isDetail = false;//是否是详情模式
    private FragmentManager mFragmentManager;

    private MultiContact.BaseNormalRvItemClick baseNormalRvItemClick;


    public void setBaseNormalRvItemClick(MultiContact.BaseNormalRvItemClick baseNormalRvItemClick) {
        this.baseNormalRvItemClick = baseNormalRvItemClick;
    }

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public MultiAdapter(List<MultipleItem> data, boolean isDetail, FragmentManager mFragmentManager) {
        super(data);

        addItemType(MultipleItem.ITEM_DIVIDER, R.layout.memu_divider);
        addItemType(MultipleItem.ITEM_EDIT_HOR, R.layout.item_layout_type_edit_hor);
        addItemType(MultipleItem.ITEM_EDIT_VER, R.layout.item_layout_type_edit_ver);
        addItemType(MultipleItem.ITEM_SELECT, R.layout.item_layout_type_select);
        addItemType(MultipleItem.ITEM_TIME_START_END, R.layout.item_layout_type_time_start_end);
        addItemType(MultipleItem.ITEM_NORMAL_RECYCLEVIEW, R.layout.item_layout_type_recyclerview);
        addItemType(MultipleItem.ITEM_FRAGMENT, R.layout.item_layout_type_fragment);
        this.isDetail = isDetail;
        this.mFragmentManager = mFragmentManager;
    }

    @Override
    protected void convert(BaseViewHolder helper, MultipleItem item) {
        switch (item.getItemType()) {

            case MultipleItem.ITEM_FRAGMENT:
                ItemFragmentBean fragmentBean = (ItemFragmentBean) item.getObject();
                SelectPhotosFragment fragment = (SelectPhotosFragment) mFragmentManager.findFragmentById(R.id.photo_fg);
                fragment.setObject(fragmentBean);
                fragment.setSpanCount(fragmentBean.getmSpanCount())
                        .setPhotoDelateable(!isDetail)
                        .setType(fragmentBean.getType())
                        .setPhotoTitle(fragmentBean.getTitle())
                        .setMaxCount(fragmentBean.getmMaxCount())
                        .setOnPicLoadSuccessCallBack(new SelectPhotosFragment.OnPicLoadSuccessCallBack() {
                            @Override
                            public void loadSuccess(List<String> icons) {
                                ItemFragmentBean picBean = (ItemFragmentBean) fragment.getObject();
                                picBean.setFragmentPics(icons);
                            }
                        });
                if (fragmentBean.getFragmentPics().size() > 0) {
                    fragment.setIcons(fragmentBean.getFragmentPics());
                }

                break;
            case MultipleItem.ITEM_EDIT_HOR:
            case MultipleItem.ITEM_EDIT_VER:
                TextKeyValueBean textValueEditBean = (TextKeyValueBean) item.getObject();
                EditText editText = helper.getView(R.id.edit_value_et);
                helper.setGone(R.id.important_tag_tv, textValueEditBean.isImportant());
                initEditTextData(textValueEditBean, editText);
                String editKey = ((TextKeyValueBean) editText.getTag()).getKey();
                helper.setText(R.id.edit_key_tv, editKey);
                switch (editKey) {
                    case MultiContact.REMARK:
                        helper.setBackgroundRes(R.id.edit_root_cl, R.drawable.sp_filled_white_6dp);
                        break;
                    default:
                        helper.setBackgroundRes(R.id.edit_root_cl, 0);
                        break;
                }
                break;
            case MultipleItem.ITEM_SELECT:
                TextKeyValueBean textValueSelectBean = (TextKeyValueBean) item.getObject();
                helper.setGone(R.id.important_tag_tv, textValueSelectBean.isImportant());
                TextView textViewTv = helper.getView(R.id.select_value_tv);
                String selectKey = textValueSelectBean.getKey();
                helper.setText(R.id.select_key_tv, selectKey);
                String selectTextValue = textValueSelectBean.getValue();
                if (!isDetail) {
                    helper.addOnClickListener(R.id.select_value_tv);
                    switch (selectKey) {
                        case MultiContact.PAY_TYPE:
                            helper.setBackgroundRes(R.id.select_root_cl, R.drawable.sp_filled_white_6dp);
                            break;
                        default:
                            helper.setBackgroundRes(R.id.select_root_cl, 0);

                            break;
                    }
                    helper.setGone(R.id.select_arrow_right_iv, true);
                } else {
                    helper.setGone(R.id.select_arrow_right_iv, false);
                    helper.setBackgroundRes(R.id.select_root_cl, 0);
                }
                textViewTv.setTag(textValueSelectBean);
                if (selectTextValue.contains("\\n")) {
                    selectTextValue = selectTextValue.replace("\\n", "\n");
                }
                textViewTv.setText(selectTextValue.trim());
                break;
            case MultipleItem.ITEM_NORMAL_RECYCLEVIEW:
                //recycleview

                BaseNormalRecyclerviewBean baseNormalRecyclerviewBean = (BaseNormalRecyclerviewBean) item.getObject();
                RecyclerView recyclerView = helper.getView(R.id.item_normal_rv);
                LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL
                        , false);
                BaseQuickAdapter adapter = baseNormalRecyclerviewBean.getAdapter();
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(manager);
                switch (baseNormalRecyclerviewBean.getType()) {
                    case MultiContact.BASE_RECYCLERVIEW_TYPE_MULTI:
                        recyclerView.setBackgroundResource(R.drawable.sp_filled_white_6dp);
                        List<MultipleItem> arrays = (List<MultipleItem>) baseNormalRecyclerviewBean.getObject();
                        adapter.setNewData(arrays);
                        adapter.setOnItemChildClickListener(new OnItemChildClickListener() {
                            @Override
                            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                                if (baseNormalRvItemClick != null) {
                                    baseNormalRvItemClick.onItemChildClick(adapter,recyclerView,position);
                                }

                            }
                        });
                        break;
//                    case BaseInspectContract.BASE_RECYCLERVIEW_TYPE_RESPONSIBILITY_CONTENT:
//                        List<String> pics = (List<String>) baseNormalRecyclerviewBean.getObject();
//                        adapter.setNewData(pics);
//                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
    }


    private void initEditTextData(TextKeyValueBean textValueEditBean, EditText editText) {
        if (isDetail) {
            editText.setClickable(false);
            editText.setFocusable(false);
        } else {
            editText.setClickable(true);
            editText.setFocusable(true);
        }
        int editType = textValueEditBean.getType();
        ConstraintLayout.LayoutParams lp = (ConstraintLayout.LayoutParams) editText.getLayoutParams();
        if (0 == editType) {
            lp.height = DisplayUtil.dp2px(mContext, 32);
            editText.setGravity(Gravity.CENTER_VERTICAL);
            editText.setSingleLine(true);
        } else {
            lp.height = LinearLayout.LayoutParams.WRAP_CONTENT;
            editText.setMinimumHeight(DisplayUtil.dp2px(mContext, 100));
            editText.setGravity(Gravity.TOP);
            editText.setSingleLine(false);
        }
        editText.setLayoutParams(lp);
        editText.setTag(textValueEditBean);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                TextKeyValueBean editBean = (TextKeyValueBean) editText.getTag();
                String str = s.toString().trim();
                editBean.setValue(str);
            }
        });
        editText.setHint(textValueEditBean.getHint());
        editText.setText(textValueEditBean.getValue());
    }
}
