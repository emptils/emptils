package com.emptils.util;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.Cookie;

import java.io.IOException;
import java.util.Set;

/**
 * Created by Administrator on 2017/6/8.
 */
public class MyWebClient extends WebClient{

    private Set<Cookie> cookies;

    public MyWebClient() {
        super(BrowserVersion.CHROME);
        this.getOptions().setCssEnabled(false);
        this.getOptions().setTimeout(50000);
        this.getOptions().setJavaScriptEnabled(true);
        this.getCookieManager().setCookiesEnabled(true);
    }

    @Override
    public HtmlPage getPage(String url)throws IOException{
        if(cookies != null){
            for(Cookie cookie : cookies) {
                this.getCookieManager().addCookie(cookie);
            }
        }
        HtmlPage htmlPage = super.getPage(url);
        return null;
    }


}
