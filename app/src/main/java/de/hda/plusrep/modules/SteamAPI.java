package de.hda.plusrep.modules;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import de.hda.plusrep.Login;

public class SteamAPI {

    Context context;
    WebView webView;

    public SteamAPI(Context context) { this.context = context; }

    public void login(Login login) {

        createWebView(login);
        login.setContentView(webView);
    }

    private void createWebView(Login login) {

        // Set up WebView
        webView = new WebView(login);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);

        String REALM_PARAM = "plusRep";

        // OpenID
        String url = "https://steamcommunity.com/openid/login?" +
                "openid.claimed_id=http://specs.openid.net/auth/2.0/identifier_select&" +
                "openid.identity=http://specs.openid.net/auth/2.0/identifier_select&" +
                "openid.mode=checkid_setup&" +
                "openid.ns=http://specs.openid.net/auth/2.0&" +
                "openid.realm=https://" + REALM_PARAM + "&" +
                "openid.return_to=https://" + REALM_PARAM + "/signin/";

        webView.loadUrl(url);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {

                login.setTitle(url);
                Uri Url = Uri.parse(url);

                if (Url.getAuthority().equals(REALM_PARAM.toLowerCase())) { // Beim erfolgreichen Login

                    webView.stopLoading();

                    Uri userAccountUrl = Uri.parse(Url.getQueryParameter("openid.identity"));
                    String userId = userAccountUrl.getLastPathSegment();

                    // Starte neuen Screen und übergebe die SteamID
                    login.activeIntent.putExtra("Login.steamID", userId);
                    login.activeIntent.putExtra("Profile." + userId + ".loaded", "FALSE");
                    login.activeIntent.setIdentifier("SteamAPI");
                    login.startActivity(login.activeIntent);
                    login.overridePendingTransition(0, 0);
                }
            }
        });
    }
}
