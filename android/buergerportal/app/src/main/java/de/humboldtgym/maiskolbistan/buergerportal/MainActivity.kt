package de.humboldtgym.maiskolbistan.buergerportal

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    private lateinit var progress: View

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView = findViewById(R.id.webview)
        progress = findViewById(R.id.progress)

        webView.settings.apply {
            javaScriptEnabled = true
            domStorageEnabled = true
            databaseEnabled = true
            loadWithOverviewMode = true
            useWideViewPort = true
            setSupportZoom(true)
            builtInZoomControls = true
            displayZoomControls = false
        }

        webView.webChromeClient = WebChromeClient()
        webView.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                progress.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                progress.visibility = View.GONE
            }

            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                progress.visibility = View.GONE
                if (request?.isForMainFrame == true) {
                    view?.loadUrl("about:blank")
                    view?.loadData(
                        "<html><body style='font-family:sans-serif;padding:24px;text-align:center;'>" +
                        "<h2>Verbindungsfehler</h2>" +
                        "<p>Bitte prüfe deine Internetverbindung und tippe, um es erneut zu versuchen.</p>" +
                        "</body></html>",
                        "text/html",
                        "UTF-8"
                    )
                    view?.setOnClickListener { loadPortal() }
                }
            }
        }

        onBackPressedDispatcher.addCallback(this) {
            if (webView.canGoBack()) {
                webView.goBack()
            } else {
                finish()
            }
        }

        loadPortal()
    }

    private fun loadPortal() {
        webView.loadUrl(PORTAL_URL)
    }

    companion object {
        private const val PORTAL_URL = "https://maiskolbistan-buergerportal.netlify.app"
    }
}
