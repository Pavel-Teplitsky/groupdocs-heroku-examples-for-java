package com.groupdocs.api.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.groupdocs.api.forms.Sample10Form;
import com.groupdocs.api.forms.Sample3Form;
import com.groupdocs.api.forms.Sample4Form;
import com.groupdocs.api.forms.Sample5Form;
import com.groupdocs.api.forms.Sample8Form;
import com.groupdocs.api.forms.Sample9Form;
import com.groupdocs.sdk.api.DocApi;
import com.groupdocs.sdk.api.MgmtApi;
import com.groupdocs.sdk.api.SignatureApi;
import com.groupdocs.sdk.api.StorageApi;
import com.groupdocs.sdk.common.ApiException;
import com.groupdocs.sdk.common.ApiInvoker;
import com.groupdocs.sdk.common.FileStream;
import com.groupdocs.sdk.common.GroupDocsRequestSigner;
import com.groupdocs.sdk.model.FileMoveResponse;
import com.groupdocs.sdk.model.FileMoveResult;
import com.groupdocs.sdk.model.FileSystemDocument;
import com.groupdocs.sdk.model.GetDocumentInfoResponse;
import com.groupdocs.sdk.model.GetDocumentInfoResult;
import com.groupdocs.sdk.model.ListEntitiesResponse;
import com.groupdocs.sdk.model.SharedUsersResponse;
import com.groupdocs.sdk.model.SharedUsersResult;
import com.groupdocs.sdk.model.SignatureSignDocumentDocumentSettings;
import com.groupdocs.sdk.model.SignatureSignDocumentResponse;
import com.groupdocs.sdk.model.SignatureSignDocumentSettings;
import com.groupdocs.sdk.model.SignatureSignDocumentSignerSettings;
import com.groupdocs.sdk.model.UploadRequestResult;
import com.groupdocs.sdk.model.UploadResponse;
import com.groupdocs.sdk.model.UserInfo;
import com.groupdocs.sdk.model.UserInfoResponse;
import com.sun.jersey.core.util.Base64;

@Controller
public class SamplesController extends AbstractController {

    @RequestMapping("/sample1")
    public ModelAndView sample1() {
        ModelAndView modelAndView = new ModelAndView("home/sample1");
        // Specify GroupDocs URL
        @SuppressWarnings("unused")
        String groupdocsUrl = System.getenv("GROUPDOCS_URL");
        // Specify App Key and App SID
        String clientId = System.getenv("GROUPDOCS_CID");
        String privateKey = System.getenv("GROUPDOCS_PKEY");
        UserInfo userInfo = null;
        ApiInvoker.getInstance().setRequestSigner(
                new GroupDocsRequestSigner(privateKey));
        MgmtApi api = new MgmtApi();
        try {
            UserInfoResponse response = api.GetUserProfile(clientId);
            if (response != null
                    && response.getStatus().trim().equalsIgnoreCase("Ok")) {
                userInfo = response.getResult().getUser();
            }
            else {
                throw new ApiException(0, response.getError_message());
            }
            modelAndView.addObject("userInfo", userInfo);
        }
        catch (ApiException e) {
            log.error(e.getMessage());
            if (e.getCode() == 401) {
                modelAndView
                        .addObject("errmsg",
                                "Wrong Credentials. Please make sure to use credentials from Production Server");
            }
            else {
                modelAndView.addObject("errmsg",
                        "Failed to access API: " + e.getMessage());
            }
        }
        return modelAndView;
    }

    @RequestMapping("/sample2")
    public ModelAndView sample2() {
        ModelAndView modelAndView = new ModelAndView("home/sample2");
        // Specify GroupDocs URL
        @SuppressWarnings("unused")
        String groupdocsUrl = System.getenv("GROUPDOCS_URL");
        // Specify App Key and App SID
        String clientId = System.getenv("GROUPDOCS_CID");
        String privateKey = System.getenv("GROUPDOCS_PKEY");
        List<FileSystemDocument> files = null;
        try {
            ApiInvoker.getInstance().setRequestSigner(
                    new GroupDocsRequestSigner(privateKey));
            StorageApi api = new StorageApi();
            ListEntitiesResponse response = api.ListEntities(clientId, "",
                    null, null, null, null, null, null, null);
            if (response != null
                    && response.getStatus().trim().equalsIgnoreCase("Ok")) {
                files = response.getResult().getFiles();
            }
            else {
                throw new ApiException(0, response.getError_message());
            }
            modelAndView.addObject("files", files);
        }
        catch (ApiException e) {
            if (e.getCode() == 401) {
                modelAndView
                        .addObject("errmsg",
                                "Wrong Credentials. Please make sure to use credentials from Production Server");
            }
            else {
                modelAndView.addObject("errmsg",
                        "Failed to access API: " + e.getMessage());
            }
        }

        log.info("/sample2.htm ");
        return modelAndView;
    }

    @RequestMapping(value = "/sample3", method = RequestMethod.GET)
    public String sample3(Model model) {
        model.addAttribute(new Sample3Form());
        return "home/sample3";
    }

    @RequestMapping(value = "/sample3", method = RequestMethod.POST)
    public ModelAndView sample3(Sample3Form sample3Form, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView("home/sample3");
        // Specify GroupDocs URL
        @SuppressWarnings("unused")
        String groupdocsUrl = System.getenv("GROUPDOCS_URL");
        // Specify App Key and App SID
        String clientId = System.getenv("GROUPDOCS_CID");
        String privateKey = System.getenv("GROUPDOCS_PKEY");

        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                log.error("Error: " + error.getCode() + " - "
                        + error.getDefaultMessage());
                modelAndView.addObject("errmsg", error.getDefaultMessage());
            }
        }
        else {
            CommonsMultipartFile multipartFile = sample3Form.getFileData();
            UploadRequestResult file = null;
            try {
                ApiInvoker.getInstance().setRequestSigner(
                        new GroupDocsRequestSigner(privateKey));
                StorageApi api = new StorageApi();
                UploadResponse response = api.Upload(clientId,
                        multipartFile.getOriginalFilename(), null,
                        new FileStream(multipartFile.getInputStream()));
                if (response != null
                        && response.getStatus().trim().equalsIgnoreCase("Ok")) {
                    file = response.getResult();
                }
                else {
                    throw new ApiException(0, response.getError_message());
                }
                modelAndView.addObject("file", file);
            }
            catch (ApiException e) {
                if (e.getCode() == 401) {
                    modelAndView
                            .addObject("errmsg",
                                    "Wrong Credentials. Please make sure to use credentials from Production Server");
                }
                else {
                    modelAndView.addObject("errmsg", "Failed to access API: "
                            + e.getMessage());
                }
            }
            catch (IOException e) {
                modelAndView.addObject("errmsg",
                        "Failed to access API: " + e.getMessage());
            }
        }
        log.info("/sample3.htm ");
        return modelAndView;
    }

    @RequestMapping(value = "/sample4", method = RequestMethod.GET)
    public ModelAndView sample4(Model model) {
        ModelAndView modelAndView = new ModelAndView("home/sample4");
        model.addAttribute(new Sample4Form());
        log.info("/sample4.htm ");
        return modelAndView;
    }

    @RequestMapping(value = "/sample4", method = RequestMethod.POST)
    public void sample4(Sample4Form sample4Form, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView("home/sample4");
        // Specify GroupDocs URL
        @SuppressWarnings("unused")
        String groupdocsUrl = System.getenv("GROUPDOCS_URL");
        // Specify App Key and App SID
        String clientId = System.getenv("GROUPDOCS_CID");
        String privateKey = System.getenv("GROUPDOCS_PKEY");
        String fileGuid = sample4Form.getFileId();
        if (fileGuid != null && !"".equalsIgnoreCase(fileGuid)) {
            FileStream file = null;

            try {
                ApiInvoker.getInstance().setRequestSigner(
                        new GroupDocsRequestSigner(privateKey));
                StorageApi api = new StorageApi();
                FileStream resp = api.GetFile(clientId, fileGuid);
                if (resp != null && resp.getInputStream() != null) {
                    file = resp;
                }
                else {
                    throw new Exception("Not Found");
                }
                if (file.getFileName() == null) {
                    file.setFileName(fileGuid);
                }
                response.setContentType(file.getContentType());
                response.addHeader("Content-Disposition",
                        "attachment; filename=\"" + file.getFileName() + "\"");
                IOUtils.copy(file.getInputStream(), response.getOutputStream());
                response.flushBuffer();
            }
            catch (ApiException e) {
                if (e.getCode() == 401) {
                    modelAndView
                            .addObject("errmsg",
                                    "Wrong Credentials. Please make sure to use credentials from Production Server");
                }
                else {
                    modelAndView.addObject("errmsg", "Failed to access API: "
                            + e.getMessage());
                }
                log.error(e.getMessage());
            }
            catch (Exception e) {
                modelAndView.addObject("errmsg",
                        "Something wrong with your file: " + e.getMessage());
                log.error(e.getMessage());
            }
            finally {
                if (file != null) {
                    IOUtils.closeQuietly(file.getInputStream());
                }
            }
        }
    }

    @RequestMapping(value = "/sample5", method = RequestMethod.GET)
    public String sample5() {
        log.info("/sample5.htm ");
        return "home/sample5";
    }

    @RequestMapping(value = "/sample5", method = RequestMethod.POST)
    public ModelAndView sample5(Sample5Form sample5Form) {
        ModelAndView modelAndView = new ModelAndView("home/sample5");
        // Specify GroupDocs URL
        @SuppressWarnings("unused")
        String groupdocsUrl = System.getenv("GROUPDOCS_URL");
        // Specify App Key and App SID
        String clientId = System.getenv("GROUPDOCS_CID");
        String privateKey = System.getenv("GROUPDOCS_PKEY");
        if (sample5Form != null && sample5Form.getAction() != null) {
            String srcPath = sample5Form.getSrcPath();
            String dstPath = sample5Form.getDstPath();
            boolean isCopy = ("COPY".equalsIgnoreCase(sample5Form.getAction()) ? true
                    : false);
            FileMoveResult moveResult = null;

            try {

                ApiInvoker.getInstance().setRequestSigner(
                        new GroupDocsRequestSigner(privateKey));

                GetDocumentInfoResponse metadata = new DocApi()
                        .GetDocumentMetadataByPath(clientId, srcPath);
                Long fileId = null;
                if (metadata != null
                        && metadata.getStatus().trim().equalsIgnoreCase("Ok")) {
                    fileId = metadata.getResult().getId().longValue();
                }
                else {
                    throw new Exception("Not Found");
                }

                StorageApi api = new StorageApi();
                FileMoveResponse response;
                if (isCopy) {
                    response = api.MoveFile(clientId, dstPath, null,
                            fileId.toString(), null);
                }
                else {
                    response = api.MoveFile(clientId, dstPath, null, null,
                            fileId.toString());
                }
                if (response != null
                        && response.getStatus().trim().equalsIgnoreCase("Ok")) {
                    moveResult = response.getResult();
                }
                else {
                    throw new ApiException(0, response.getError_message());
                }
                modelAndView.addObject("moveResult", moveResult.getDst_file());
            }
            catch (ApiException e) {
                if (e.getCode() == 401) {
                    modelAndView
                            .addObject("errmsg",
                                    "Wrong Credentials. Please make sure to use credentials from Production Server");
                }
                else {
                    modelAndView.addObject("errmsg", "Failed to access API: "
                            + e.getMessage());
                }
                log.error(e.getMessage());
            }
            catch (Exception e) {
                if (dstPath == null) {
                    modelAndView.addObject("errmsg", "This field is required: "
                            + e.getMessage());
                }
                else {
                    modelAndView
                            .addObject(
                                    "errmsg",
                                    "Something wrong with your file: "
                                            + e.getMessage());
                }
                log.error(e.getMessage());
            }
        }
        return modelAndView;
    }

    @SuppressWarnings("unused")
    @RequestMapping("/sample6")
    public ModelAndView sample6(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("home/sample6");
        // Specify GroupDocs URL
        String groupdocsUrl = System.getenv("GROUPDOCS_URL");
        // Specify App Key and App SID
        String clientId = System.getenv("GROUPDOCS_CID");
        String privateKey = System.getenv("GROUPDOCS_PKEY");
        String fileId = null;

        if (ServletFileUpload.isMultipartContent(request)) {

            if (!(request instanceof DefaultMultipartHttpServletRequest)) {
                throw new IllegalArgumentException(
                        "Request is not multipart, please 'multipart/form-data' enctype for your form.");
            }

            DefaultMultipartHttpServletRequest dmhsRequest = (DefaultMultipartHttpServletRequest) request;
            MultipartFile document = dmhsRequest.getFile("document");
            MultipartFile signature = dmhsRequest.getFile("signature");
            try {
                if (document == null || signature == null) {
                    throw new Exception("Please, fill all fields!");
                }
                String base64file = ApiInvoker.readAsDataURL(
                        document.getInputStream(), document.getContentType());
                String base64signature = ApiInvoker.readAsDataURL(
                        signature.getInputStream(), signature.getContentType());

                ApiInvoker.getInstance().setRequestSigner(
                        new GroupDocsRequestSigner(privateKey));

                SignatureSignDocumentDocumentSettings doc = new SignatureSignDocumentDocumentSettings();
                doc.setName(document.getName());
                doc.setData(base64file);

                SignatureSignDocumentSignerSettings signer = new SignatureSignDocumentSignerSettings();
                signer.setPlaceSingatureOn("");
                signer.setName(signature.getName());
                signer.setData(base64signature);
                signer.setHeight(40d);
                signer.setWidth(100d);
                signer.setTop(0.03319);
                signer.setLeft(0.52171);

                SignatureSignDocumentSettings requestBody = new SignatureSignDocumentSettings();
                List<SignatureSignDocumentSignerSettings> signers = new ArrayList<SignatureSignDocumentSignerSettings>();
                signers.add(signer);
                requestBody.setSigners(signers);
                List<SignatureSignDocumentDocumentSettings> documents = new ArrayList<SignatureSignDocumentDocumentSettings>();
                documents.add(doc);
                requestBody.setDocuments(documents);

                SignatureSignDocumentResponse response = new SignatureApi()
                        .SignDocument(clientId, requestBody);
                if (response != null
                        && response.getStatus().trim().equalsIgnoreCase("Ok")) {
                    fileId = response.getResult().getDocumentId();
                }
                else {
                    throw new ApiException(400, response.getError_message());
                }
                modelAndView.addObject("fileId", fileId);
            }
            catch (ApiException e) {
                if (e.getCode() == 401) {
                    modelAndView
                            .addObject("errmsg",
                                    "Wrong Credentials. Please make sure to use credentials from Production Server");
                }
                else {
                    modelAndView.addObject("errmsg", "Failed to access API: "
                            + e.getMessage());
                }
                log.error(e.getMessage());
            }
            catch (Exception e) {
                modelAndView.addObject("errmsg",
                        "Something wrong with your file: " + e.getMessage());
                log.error(e.getMessage());
            }
        }

        log.info("/sample6.htm ");
        return modelAndView;
    }

    @RequestMapping("/sample7")
    public ModelAndView sample7(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("home/sample7");
        // Specify GroupDocs URL
        @SuppressWarnings("unused")
        String groupdocsUrl = System.getenv("GROUPDOCS_URL");
        // Specify App Key and App SID
        String clientId = System.getenv("GROUPDOCS_CID");
        String privateKey = System.getenv("GROUPDOCS_PKEY");

        ApiInvoker.getInstance().setRequestSigner(
                new GroupDocsRequestSigner(privateKey));
        StorageApi storageApi = new StorageApi();
        ListEntitiesResponse listEntitiesResponse = null;

        try {
            listEntitiesResponse = storageApi.ListEntities(clientId, "", 0,
                    null, null, null, null, null, true);
            if (listEntitiesResponse != null
                    && listEntitiesResponse.getResult() != null
                    && listEntitiesResponse.getResult().getFiles() != null) {
                List<FileSystemDocument> documents = listEntitiesResponse
                        .getResult().getFiles();
                File appPathFile = new File(request.getSession()
                        .getServletContext().getRealPath("/")
                        + "/public-resources/img/");
                FileUtils.deleteDirectory(appPathFile);
                appPathFile.mkdirs();
                Map<String, String> files = new HashMap<String, String>();
                for (int index = 0; index < documents.size(); index++) {
                    FileSystemDocument document = documents.get(index);
                    String base64 = document.getThumbnail();
                    if (base64 != null) {
                        String thumbName = "thumbnail"
                                + Integer.toString(index) + ".png";
                        String thumbPath = appPathFile.getAbsolutePath() + "/"
                                + thumbName;
                        FileOutputStream fileOutputStream = new FileOutputStream(
                                thumbPath);
                        fileOutputStream.write(Base64.decode(base64));
                        fileOutputStream.close();
                        files.put(document.getName(), thumbName);
                    }
                }
                modelAndView.addObject("documents", files);
            }
            else {
                throw new Exception("Result error!");
            }
        }
        catch (ApiException e) {
            if (e.getCode() == 401) {
                modelAndView
                        .addObject("errmsg",
                                "Wrong Credentials. Please make sure to use credentials from Production Server");
            }
            else {
                modelAndView.addObject("errmsg",
                        "Failed to access API: " + e.getMessage());
            }
            log.error(e.getMessage());
        }
        catch (Exception e) {
            modelAndView.addObject("errmsg", e.getMessage());
            log.error(e.getMessage());
        }

        log.info("/sample7.htm ");
        return modelAndView;
    }

    @RequestMapping(value = "/sample8", method = RequestMethod.GET)
    public String sample8() {
        return "home/sample8";
    }

    @SuppressWarnings("unused")
    @RequestMapping(value = "/sample8", method = RequestMethod.POST)
    public ModelAndView sample8(Sample8Form sample8Form) {
        ModelAndView modelAndView = new ModelAndView("home/sample8");
        // Specify GroupDocs URL
        String groupdocsUrl = System.getenv("GROUPDOCS_URL");
        // Specify App Key and App SID
        String clientId = System.getenv("GROUPDOCS_CID");
        String privateKey = System.getenv("GROUPDOCS_PKEY");
        if (sample8Form != null && sample8Form.getFileId() != null) {
            String fileGuid = sample8Form.getFileId();
            String pageNumber = sample8Form.getPageNumber();
            String dimension = sample8Form.getDimension();
            List<String> thumbnailUrls = null;
            try {
                if (fileGuid == null || dimension == null || pageNumber == null) {
                    throw new Exception("Please, fill all fields.");
                }

                ApiInvoker.getInstance().setRequestSigner(
                        new GroupDocsRequestSigner(privateKey));

                DocApi api = new DocApi();
                GetDocumentInfoResponse response = api.GetDocumentMetadata(
                        clientId, fileGuid);
                Integer pageCount = null;
                if (response != null
                        && response.getStatus().trim().equalsIgnoreCase("Ok")) {
                    pageCount = response.getResult().getPage_count();
                }
                else {
                    throw new Exception("Not Found");
                }
                thumbnailUrls = api
                        .GetDocumentPagesImageUrls(clientId, fileGuid, 0,
                                pageCount, dimension, null, null, null)
                        .getResult().getUrl();
                modelAndView.addObject("thumbnailUrls", thumbnailUrls);
            }
            catch (ApiException e) {
                if (e.getCode() == 401) {
                    modelAndView
                            .addObject("errmsg",
                                    "Wrong Credentials. Please make sure to use credentials from Production Server");
                }
                else {
                    modelAndView.addObject("errmsg", "Failed to access API: "
                            + e.getMessage());
                }
                log.error(e.getMessage());
            }
            catch (Exception e) {
                modelAndView.addObject("errmsg", e.getMessage());
                log.error(e.getMessage());
            }
        }

        log.info("/sample8.htm ");
        return modelAndView;
    }

    @RequestMapping(value = "/sample9", method = RequestMethod.GET)
    public String sample9() {
        return "home/sample9";
    }

    @SuppressWarnings("unused")
    @RequestMapping(value = "/sample9", method = RequestMethod.POST)
    public ModelAndView sample9(Sample9Form sample9Form) {
        ModelAndView modelAndView = new ModelAndView("home/sample9");
        // Specify GroupDocs URL
        String groupdocsUrl = System.getenv("GROUPDOCS_URL");
        // Specify App Key and App SID
        String clientId = System.getenv("GROUPDOCS_CID");
        String privateKey = System.getenv("GROUPDOCS_PKEY");
        String fileId = sample9Form.getFileId();
        String width = sample9Form.getWidth();
        String height = sample9Form.getHeight();
        HashMap<String, String> data = null;
        try {
            if (fileId == null || width == null || height == null) {
                throw new Exception();
            }

            ApiInvoker.getInstance().setRequestSigner(
                    new GroupDocsRequestSigner(privateKey));
            DocApi api = new DocApi();
            GetDocumentInfoResponse response = api.GetDocumentMetadata(
                    clientId, fileId);
            if (response != null
                    && response.getStatus().trim().equalsIgnoreCase("Ok")) {
                GetDocumentInfoResult result = response.getResult();
                data = new HashMap<String, String>();
                data.put("fileId", fileId);
                data.put("id", result.getId().toString());
                data.put("width", width);
                data.put("height", height);
                data.put("pages", result.getPage_count().toString());
                data.put("views", result.getViews_count().toString() + " times");
            }
            else {
                throw new Exception("Not Found");
            }
            modelAndView.addObject("data", data);
        }
        catch (ApiException e) {
            if (e.getCode() == 401) {
                modelAndView
                        .addObject("errmsg",
                                "Wrong Credentials. Please make sure to use credentials from Production Server");
            }
            else {
                modelAndView.addObject("errmsg",
                        "Failed to access API: " + e.getMessage());
            }
            log.error(e.getMessage());
        }
        catch (Exception e) {
            modelAndView.addObject("errmsg", e.getMessage());
            log.error(e.getMessage());
        }
        log.info("/sample9.htm ");
        return modelAndView;
    }

    @RequestMapping(value = "/sample10", method = RequestMethod.GET)
    public String sample10() {
        return "home/sample10";
    }

    @SuppressWarnings("unused")
    @RequestMapping(value = "/sample10", method = RequestMethod.POST)
    public ModelAndView sample10(Sample10Form sample10Form) {
        ModelAndView modelAndView = new ModelAndView("home/sample10");
        // Specify GroupDocs URL
        String groupdocsUrl = System.getenv("GROUPDOCS_URL");
        // Specify App Key and App SID
        String clientId = System.getenv("GROUPDOCS_CID");
        String privateKey = System.getenv("GROUPDOCS_PKEY");
        String fileGuid = sample10Form.getFileId();
        String email = sample10Form.getEmail();
        SharedUsersResult result = null;
        try {
            if (fileGuid == null || email == null) {
                throw new Exception();
            }
            ApiInvoker.getInstance().setRequestSigner(
                    new GroupDocsRequestSigner(privateKey));

            DocApi api = new DocApi();
            GetDocumentInfoResponse metadata = new DocApi()
                    .GetDocumentMetadata(clientId, fileGuid);
            String fileId = null;
            if (metadata != null
                    && metadata.getStatus().trim().equalsIgnoreCase("Ok")) {
                fileId = metadata.getResult().getId().toString();
            }
            else {
                throw new Exception("Not Found");
            }

            SharedUsersResponse response = api.ShareDocument(clientId, fileId,
                    Arrays.asList(new String[] { email }));
            if (response != null
                    && metadata.getStatus().trim().equalsIgnoreCase("Ok")) {
                result = response.getResult();
            }
            else {
                throw new Exception("User identified by " + " not Found");
            }
            modelAndView.addObject("result", result);
        }
        catch (ApiException e) {
            if (e.getCode() == 401) {
                modelAndView
                        .addObject("errmsg",
                                "Wrong Credentials. Please make sure to use credentials from Production Server");
            }
            else {
                modelAndView.addObject("errmsg",
                        "Failed to access API: " + e.getMessage());
            }
            log.error(e.getMessage());
        }
        catch (Exception e) {
            modelAndView.addObject("errmsg", e.getMessage());
            log.error(e.getMessage());
        }
        log.info("/sample10.htm ");
        return modelAndView;
    }
}
