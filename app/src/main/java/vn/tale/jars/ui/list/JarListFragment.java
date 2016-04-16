package vn.tale.jars.ui.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.tale.jars.R;
import vn.tale.jars.base.BaseFragment;
import vn.tale.jars.ui.binding.ErrorTextView;
import vn.tale.jars.ui.binding.ToggleVisibleGone;
import vn.tale.jars.ui.list.adapter.JarListAdapter;
import vn.tale.lcebinding.LceBinding;
import vn.tale.lcebinding.LoadingContentError;

/**
 * Created by Giang Nguyen at Tiki on 4/11/16.
 */
public class JarListFragment extends BaseFragment {

  @Bind(R.id.vProgress)
  View vLoading;
  @Bind(R.id.vError)
  View vError;
  @Bind(R.id.tvErrorMessage)
  TextView tvErrorMessage;
  @Bind(R.id.list)
  RecyclerView recyclerView;

  @Inject
  JarListViewModel viewModel;

  @Inject
  JarListAdapter adapter;

  @Inject
  LceBinding lceBinding;

  @Inject LoadingContentError lce;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setupDependencies();
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_list, container, false);
    ButterKnife.bind(this, view);
    return view;
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    setupBinding();

    setupListView();

    loadData();
  }

  @Override
  public void onDestroyView() {
    ButterKnife.unbind(this);
    lceBinding.unbind();
    super.onDestroyView();
  }

  @OnClick(R.id.vError)
  public void loadData() {
    viewModel.load(lce);
  }

  private void setupBinding() {
    lceBinding.bind(lce,
        new ToggleVisibleGone(vLoading),
        new ToggleVisibleGone(recyclerView),
        new ErrorTextView(vError, tvErrorMessage));
  }

  private void setupListView() {
    if (recyclerView != null) {
      recyclerView.setLayoutManager(
          new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
      recyclerView.setHasFixedSize(true);
      recyclerView.setAdapter(adapter);
    }
    viewModel.dataStream().subscribe(adapter::setItems);
  }

  private void setupDependencies() {
    ((JarListActivity) getActivity()).getComponent().inject(this);
  }
}
