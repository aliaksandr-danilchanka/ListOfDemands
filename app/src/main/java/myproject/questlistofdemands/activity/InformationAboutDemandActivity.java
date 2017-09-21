package myproject.questlistofdemands.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import myproject.questlistofdemands.R;
import myproject.questlistofdemands.activity.base.BaseActivity;
import myproject.questlistofdemands.fragment.InformationAboutDemandFragment;

public class InformationAboutDemandActivity extends BaseActivity {

    public static final String ID_DEMAND_KEY = "ID_DEMAND_KEY";
    @BindView(R.id.toolbar_actionbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_about_demand);
        ButterKnife.bind(this);
        setUpAppBar();

        if (savedInstanceState == null) {
            Fragment myFragment = InformationAboutDemandFragment.newInstance(getIntent().getIntExtra(ID_DEMAND_KEY, 0));
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container_information_demand, myFragment)
                    .commit();
        }
    }

    private void setUpAppBar() {
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        setTitle(getString(R.string.information_about_demand));
    }
}
