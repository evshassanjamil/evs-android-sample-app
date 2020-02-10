package com.evs.android.mysampleapp.week12.auth;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class GoogleSignInUtils {

    private Context mContext;
    private GoogleSignInClient mGoogleSignInClient;
    private final int RC_SIGN_IN = 1221;
    private static final String TAG = GoogleSignInUtils.class.getSimpleName();

    GoogleSignInUtils(Context context) {
        this.mContext = context;
    }

    public void onCreate(SignInButton signInButton) {
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(mContext, gso);

        // Set the dimensions of the sign-in button.
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInIntent();
            }
        });
    }

    public GoogleSignInAccount onStart() {
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        return GoogleSignIn.getLastSignedInAccount(mContext);
    }

    public GoogleSignInAccount onActivityResult(int requestCode, int resultCode, Intent data) {
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Signed in successfully, show authenticated UI, consume the object returned.
                return task.getResult(ApiException.class);
            } catch (ApiException e) {
                // The ApiException status code indicates the detailed failure reason.
                // Please refer to the GoogleSignInStatusCodes class reference for more information.
                Log.e(TAG, "signInResult:failed code=" + e.getStatusCode());
                return null;
            }
        }
        return null;
    }

    private void signInIntent() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        ((Activity) mContext).startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    public void signout(OnCompleteListener<Void> listener) {
        mGoogleSignInClient
                .signOut()
                .addOnCompleteListener(listener);
    }
}
