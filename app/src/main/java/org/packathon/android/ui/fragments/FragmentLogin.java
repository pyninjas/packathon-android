package org.packathon.android.ui.fragments;

import android.widget.EditText;

import com.squareup.otto.Subscribe;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.packathon.android.R;
import org.packathon.android.enums.FragmentType;
import org.packathon.android.events.LoginEvent;
import org.packathon.android.helpers.BusHelper;
import org.packathon.android.rest.RestClient;
import org.packathon.android.services.UserService;
import org.packathon.android.ui.events.SwitchFragment;
import org.packathon.android.utils.AlertUtil;

@EFragment(R.layout.fragment_login)
public class FragmentLogin extends BaseFragment {

    @ViewById(R.id.text_email)
    protected EditText textEmail;

    @ViewById(R.id.text_password)
    protected EditText textPassword;

    @Bean UserService userService;
    @Bean AlertUtil alertUtil;
    @Bean RestClient api;

    private String email;
    private String password;

    @AfterViews
    protected void setupViews() {
        setFragmentType(FragmentType.LOGIN);
        setTitle(getString(R.string.title_login));

        if (userService.isLoggedIn()) {
            BusHelper.BUS.post(new SwitchFragment(getActivity().getSupportFragmentManager(), new FragmentVote_(), false));
        }
    }

    @Click(R.id.button_login)
    protected void buttonLoginClicked() {
        email = textEmail.getText().toString();
        password = textPassword.getText().toString();

        if (!email.equals("") && !password.equals("")) {
            alertUtil.showLoadingDialog();
            userService.login(email, password);
        } else {
            alertUtil.showToast(getString(R.string.err_required_fields));
        }
    }

    @Subscribe
    public void onLogin(LoginEvent result) {
        alertUtil.dissmissDialog();

        if (result.hasError()) {
            alertUtil.showToast(getString(R.string.err_invalid_credentials));
            return;
        }

        alertUtil.showToast(getString(R.string.msg_login_success));
        BusHelper.BUS.post(new SwitchFragment(getActivity().getSupportFragmentManager(), new FragmentVote_(), true));
    }

}
