import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import com.gargoylesoftware.htmlunit.util.Cookie;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.w3c.dom.NamedNodeMap;

import java.io.*;
import java.net.URL;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring-mvc.xml","classpath*:spring-mybatis.xml"})
public class JunitTest {

    @Before
    public void before(){

    }

    public void test(){
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setCssEnabled(false);
        webClient.getCookieManager().setCookiesEnabled(true);
        webClient.waitForBackgroundJavaScript(600*1000);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setUseInsecureSSL(true);

        try {
            File file = new File("cookies.out");
            Set<Cookie> cookies;
            if(!file.exists()) {
                file.createNewFile();
                cookies = loign(webClient,file);
            }else{
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                cookies = (Set<Cookie>)ois.readObject();
                if(cookies==null||cookies.size()==0) {
                    cookies = loign(webClient,file);
                }
            }

            if(cookies != null) {
                for (Cookie cookie : cookies){
                    webClient.getCookieManager().addCookie(cookie);
                }
                main(webClient);

            }else{
                System.out.println("登录失败");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private Set<Cookie> loign(WebClient webClient,File file) throws Exception{
        URL url = new URL("https://tieba.baidu.com/");
        HtmlPage htmlPage = webClient.getPage(url);

        HtmlAnchor ha = htmlPage.getAnchorByText("登录");
        htmlPage = ha.click();

        HtmlForm hf = (HtmlForm)htmlPage.getElementById("TANGRAM__PSP_8__form");

        HtmlInput userName = hf.getInputByName("userName");
        HtmlInput password = hf.getInputByName("password");
        HtmlInput loginBt = hf.getInputByValue("登录");
        userName.setValueAttribute("emptils");
        password.setValueAttribute("13982106347ls");
        loginBt.click();


        Set<Cookie> cookies = webClient.getCookieManager().getCookies();

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        oos.writeObject(cookies);
        oos.close();

        return cookies;
    }

    private void main(WebClient webClient) throws Exception{
        HtmlPage htmlPage = webClient.getPage("https://tieba.baidu.com/f?ie=utf-8&kw=%E4%BD%8E%E8%B0%83%E7%9A%84%E6%88%91%E4%B8%8D%E4%BC%9A%E5%88%B0%E5%A4%84%E8%AF%B4%E6%88%91%E6%98%AF%E4%B8%8A%E5%B8%9D%E6%9B%B4%E4%B8%8D%E5%8F%AF%E8%83%BD%E8%AF%B4%E6%88%91%E8%B7%9F%E4%BD%9B%E4%B8%BB%E5%85%B6%E5%AE%9E%E6%98%AF%E5%90%8C%E9%97%A8%E5%B8%88%E5%85%84%E5%BC%9F");
        System.out.println(htmlPage.asXml());
        DomNodeList<DomNode> aList = htmlPage.querySelectorAll("a .j_th_tit ");

        for(DomNode dn : aList){
            System.out.println(dn.getTextContent());
        }



    }

    @Test
    public void test1(){
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setCssEnabled(false);
        webClient.getCookieManager().setCookiesEnabled(true);
        webClient.waitForBackgroundJavaScript(600*1000);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setUseInsecureSSL(true);

        try {
            HtmlPage page = webClient.getPage("https://cd.lianjia.com/ershoufang/");
//            DomNode dn = page.querySelector(".sellListContent");
//            if(dn!=null){
//                DomNodeList<DomNode> dns = dn.getChildNodes();
//                if(dns!=null&&dns.size()>0) {
//                    for(DomNode cdn : dns){
//                        HtmlAnchor a = cdn.querySelector("a.img");
//                        System.out.println(a.getHrefAttribute());
//                    }
//                }
//            }

            DomNode pageBox = page.querySelector(".page-box.house-lst-page-box");
            NamedNodeMap nnm = pageBox.getAttributes();

            System.out.println(pageBox.asXml());

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}