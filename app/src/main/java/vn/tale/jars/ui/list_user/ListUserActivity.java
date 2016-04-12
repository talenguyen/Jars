package vn.tale.jars.ui.list_user;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.tale.jars.App;
import vn.tale.jars.R;
import vn.tale.jars.ui.binding.ToggleVisibleGone;
import vn.tale.lcebinding.ErrorView;
import vn.tale.lcebinding.LceBinding;
import vn.tale.lcebinding.ShowHideView;

public class ListUserActivity extends AppCompatActivity {
  @Inject UserListAdapter adapter;
  @Inject UserListVM viewModel;
  @Inject LceBinding lceBinding;

  @Bind(R.id.list) RecyclerView recyclerView;
  @Bind(R.id.vProgress) View vLoading;
  @Bind(R.id.tvErrorMessage) TextView tvError;

  private ShowHideView loadingView;
  private ErrorView errorView;
  private ShowHideView contentView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);

    setupDependencies();

    setupListView();
  }

  private void setupDependencies() {
    App.get(this).getComponent().plus(new UserListModule()).inject(this);
    loadingView = new ToggleVisibleGone(vLoading);
//    errorView = new ErrorTextView(tvError);
    contentView = new ToggleVisibleGone(recyclerView);
  }

  @Override protected void onResume() {
    super.onResume();
    lceBinding.bind(viewModel.getLce(), loadingView, contentView, errorView);
    loadData();
  }

  @OnClick(R.id.tvErrorMessage)
  public void loadData() {
    viewModel.load();
  }

  @Override protected void onPause() {
    viewModel.unsubscribe();
    lceBinding.unbind();
    super.onPause();
  }

  private void setupListView() {
    if (recyclerView != null) {
      recyclerView.setLayoutManager(
          new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
      recyclerView.setHasFixedSize(true);
      recyclerView.setAdapter(adapter);
    }
    viewModel.getUsersStream().subscribe(adapter::setItems);
  }
}
