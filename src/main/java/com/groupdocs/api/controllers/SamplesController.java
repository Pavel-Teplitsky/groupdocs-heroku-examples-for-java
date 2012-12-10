package com.groupdocs.api.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.groupdocs.sdk.api.MgmtApi;
import com.groupdocs.sdk.common.ApiException;
import com.groupdocs.sdk.common.ApiInvoker;
import com.groupdocs.sdk.common.GroupDocsRequestSigner;
import com.groupdocs.sdk.model.UserInfo;
import com.groupdocs.sdk.model.UserInfoResponse;

@Controller
public class SamplesController extends AbstractController {

    @RequestMapping("/sample1")
    public ModelAndView sample1() {
        ModelAndView modelAndView = new ModelAndView("home/sample1");
        // Specify GroupDocs URL
        @SuppressWarnings("unused")
        String groupdocsUrl = System.getenv("GROUPDOCS_TEST_URL");
        // Specify App Key and App SID
        String appKey = System.getenv("GROUPDOCS_TEST_APPKEY");
        String appSid = System.getenv("GROUPDOCS_TEST_APPSID");
        UserInfo userInfo = null;
        ApiInvoker.getInstance().setRequestSigner(
                new GroupDocsRequestSigner(appKey));
        MgmtApi api = new MgmtApi();
        try {
            UserInfoResponse response = api.GetUserProfile(appSid);
            if(response != null && response.getStatus().trim().equalsIgnoreCase("Ok")){
                userInfo = response.getResult().getUser();
            }
            else {
                throw new ApiException(0, response.getError_message());
            }
            modelAndView.addObject("userInfo", userInfo);
        } catch (ApiException e) {
            log.error(e.getMessage());
            if(e.getCode() == 401){
                modelAndView.addObject("errmsg", "Wrong Credentials. Please make sure to use credentials from");
            } else {
                modelAndView.addObject("errmsg", "Failed to access API: " + e.getMessage());
            }
        }
        return modelAndView;
    }

    @RequestMapping("/sample2")
    public ModelAndView sample2() {
        ModelAndView modelAndView = new ModelAndView("home/sample2");
        // Specify GroupDocs URL
        @SuppressWarnings("unused")
        String groupdocsUrl = System.getenv("GROUPDOCS_TEST_URL");
        // Specify App Key and App SID
        String appKey = System.getenv("GROUPDOCS_TEST_APPKEY");
        String appSid = System.getenv("GROUPDOCS_TEST_APPSID");

        log.info("/sample2.htm ");
        return modelAndView;
    }

    @RequestMapping("/sample3")
    public ModelAndView sample3() {
        ModelAndView modelAndView = new ModelAndView("home/sample3");
        // Specify GroupDocs URL
        @SuppressWarnings("unused")
        String groupdocsUrl = System.getenv("GROUPDOCS_TEST_URL");
        // Specify App Key and App SID
        String appKey = System.getenv("GROUPDOCS_TEST_APPKEY");
        String appSid = System.getenv("GROUPDOCS_TEST_APPSID");

        log.info("/sample3.htm ");
        return modelAndView;
    }

    @RequestMapping("/sample4")
    public ModelAndView sample4() {
        ModelAndView modelAndView = new ModelAndView("home/sample4");
        // Specify GroupDocs URL
        @SuppressWarnings("unused")
        String groupdocsUrl = System.getenv("GROUPDOCS_TEST_URL");
        // Specify App Key and App SID
        String appKey = System.getenv("GROUPDOCS_TEST_APPKEY");
        String appSid = System.getenv("GROUPDOCS_TEST_APPSID");

        log.info("/sample4.htm ");
        return modelAndView;
    }

    @RequestMapping("/sample5")
    public ModelAndView sample5() {
        ModelAndView modelAndView = new ModelAndView("home/sample5");
        // Specify GroupDocs URL
        @SuppressWarnings("unused")
        String groupdocsUrl = System.getenv("GROUPDOCS_TEST_URL");
        // Specify App Key and App SID
        String appKey = System.getenv("GROUPDOCS_TEST_APPKEY");
        String appSid = System.getenv("GROUPDOCS_TEST_APPSID");

        log.info("/sample5.htm ");
        return modelAndView;
    }

    @RequestMapping("/sample6")
    public ModelAndView sample6() {
        ModelAndView modelAndView = new ModelAndView("home/sample6");
        // Specify GroupDocs URL
        @SuppressWarnings("unused")
        String groupdocsUrl = System.getenv("GROUPDOCS_TEST_URL");
        // Specify App Key and App SID
        String appKey = System.getenv("GROUPDOCS_TEST_APPKEY");
        String appSid = System.getenv("GROUPDOCS_TEST_APPSID");

        log.info("/sample6.htm ");
        return modelAndView;
    }

    @RequestMapping("/sample7")
    public ModelAndView sample7() {
        ModelAndView modelAndView = new ModelAndView("home/sample7");
        // Specify GroupDocs URL
        @SuppressWarnings("unused")
        String groupdocsUrl = System.getenv("GROUPDOCS_TEST_URL");
        // Specify App Key and App SID
        String appKey = System.getenv("GROUPDOCS_TEST_APPKEY");
        String appSid = System.getenv("GROUPDOCS_TEST_APPSID");

        log.info("/sample7.htm ");
        return modelAndView;
    }

    @RequestMapping("/sample8")
    public ModelAndView sample8() {
        ModelAndView modelAndView = new ModelAndView("home/sample8");
        // Specify GroupDocs URL
        @SuppressWarnings("unused")
        String groupdocsUrl = System.getenv("GROUPDOCS_TEST_URL");
        // Specify App Key and App SID
        String appKey = System.getenv("GROUPDOCS_TEST_APPKEY");
        String appSid = System.getenv("GROUPDOCS_TEST_APPSID");

        log.info("/sample8.htm ");
        return modelAndView;
    }

    @RequestMapping("/sample9")
    public ModelAndView sample9() {
        ModelAndView modelAndView = new ModelAndView("home/sample9");
        // Specify GroupDocs URL
        @SuppressWarnings("unused")
        String groupdocsUrl = System.getenv("GROUPDOCS_TEST_URL");
        // Specify App Key and App SID
        String appKey = System.getenv("GROUPDOCS_TEST_APPKEY");
        String appSid = System.getenv("GROUPDOCS_TEST_APPSID");

        log.info("/sample9.htm ");
        return modelAndView;
    }

    @RequestMapping("/sample10")
    public ModelAndView sample10() {
        ModelAndView modelAndView = new ModelAndView("home/sample10");
        // Specify GroupDocs URL
        @SuppressWarnings("unused")
        String groupdocsUrl = System.getenv("GROUPDOCS_TEST_URL");
        // Specify App Key and App SID
        String appKey = System.getenv("GROUPDOCS_TEST_APPKEY");
        String appSid = System.getenv("GROUPDOCS_TEST_APPSID");

        log.info("/sample10.htm ");
        return modelAndView;
    }
}
