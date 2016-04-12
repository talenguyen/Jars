package vn.tale.jars.ui.list;

import android.os.Bundle;
import android.support.annotation.Nullable;

import vn.tale.jars.App;
import vn.tale.jars.R;
import vn.tale.jars.base.BaseActivity;

/**
 * Created by Giang Nguyen at Tiki on 4/9/16.
 */
public class JarListActivity extends BaseActivity {

    private JarListComponent component;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupDependencies();
        setContentView(R.layout.activity_single_toolbar);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentContainer, new JarListFragment())
                    .commit();
        }
    }

    public JarListComponent getComponent() {
        return component;
    }

    private void setupDependencies() {
        component = App.get(this).getComponent().plus(new JarListModule());
    }
}
