package myproject.questlistofdemands.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import myproject.questlistofdemands.R;
import myproject.questlistofdemands.activity.base.BaseActivity;
import myproject.questlistofdemands.fragment.ListOfDemandsFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        toolbar.setTitle(R.string.app_name);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, new ListOfDemandsFragment())
                    .commit();
        }
    }
}
