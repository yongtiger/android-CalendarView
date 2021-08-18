package cc.brainbook.android.calendarview.app.pager;

import androidx.recyclerview.widget.LinearLayoutManager;

import cc.brainbook.android.calendarview.app.Article;
import cc.brainbook.android.calendarview.app.ArticleAdapter;
import cc.brainbook.android.calendarview.app.R;
import cc.brainbook.android.calendarview.app.base.fragment.BaseFragment;
import cc.brainbook.android.calendarview.app.group.GroupItemDecoration;
import cc.brainbook.android.calendarview.app.group.GroupRecyclerView;

public class PagerFragment extends BaseFragment {

    private GroupRecyclerView mRecyclerView;


    static PagerFragment newInstance() {
        return new PagerFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_pager;
    }

    @Override
    protected void initView() {
        mRecyclerView = mRootView.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.addItemDecoration(new GroupItemDecoration<String, Article>());
        mRecyclerView.setAdapter(new ArticleAdapter(mContext));
        mRecyclerView.notifyDataSetChanged();
    }

    @Override
    protected void initData() {

    }

    boolean isScrollTop() {
        return mRecyclerView != null && mRecyclerView.computeVerticalScrollOffset() == 0;
    }
}
