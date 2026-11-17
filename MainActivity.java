package vn.sotro.app;

import android.os.Bundle;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import com.getcapacitor.BridgeActivity;

public class MainActivity extends BridgeActivity {
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    final WebView w = getBridge().getWebView();
    w.addJavascriptInterface(new Object() {
      @JavascriptInterface
      public void print() {
        runOnUiThread(new Runnable() {
          public void run() {
            PrintManager pm = (PrintManager) getSystemService(PRINT_SERVICE);
            PrintDocumentAdapter a = w.createPrintDocumentAdapter("Hoa don");
            pm.print("Hoa don", a, new PrintAttributes.Builder()
                .setMediaSize(PrintAttributes.MediaSize.ISO_A5).build());
          }
        });
      }
    }, "AndroidPrint");
  }
}
