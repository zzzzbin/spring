package com.example.demo.controller;

import com.example.demo.application.service.UserApplicationService;
import com.example.demo.domain.user.model.MUser;
import com.example.demo.domain.user.service.UserService;
import com.example.demo.form.UserListForm;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserListController {

    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserApplicationService userApplicationService;

    /**
     * Display user list screen
     */
    @GetMapping("/list")
    public String getUserList(@ModelAttribute UserListForm form, Model model) {
        //convert form to MUser class
        MUser user = modelMapper.map(form, MUser.class);
        List<MUser> userList = userService.getUsers(user);
        model.addAttribute("userList", userList);
        return "user/list";
    }

    //    User search process
    @PostMapping("/list")
    public String postUserList(@ModelAttribute UserListForm form, Model model) {
        //convert form to MUser class
        MUser user = modelMapper.map(form, MUser.class);
        List<MUser> userList = userService.getUsers(user);
        model.addAttribute("userList", userList);
        return "user/list";
    }

    /**
     * User list download process
     */
    @PostMapping("/list/download")
    public ResponseEntity<byte[]> downloadUserList(@ModelAttribute UserListForm form)
            throws IOException {
        // Convert form to MUser class
        MUser user = modelMapper.map(form, MUser.class);
        // User search
        List<MUser> userList = userService.getUsers(user);
        // Save CSV file
        String fileName = "user.csv";
        userApplicationService.saveUserCsv(userList, fileName);
        // Get CSV file
        byte[] bytes = userApplicationService.getCsv(fileName);
        HttpHeaders header = new HttpHeaders();
        // HTTP header settings
        header.add("Content-Type", MediaType.ALL_VALUE + "; charset=utf-8");
        header.setContentDispositionFormData("filename", fileName);
        return new ResponseEntity<>(bytes, header, HttpStatus.OK);
    }


    /**
     * Zip file download process
     */
    @PostMapping("/list/download/zip")
    public void downloadZip(@ModelAttribute UserListForm form,
                            HttpServletResponse response) throws IOException {
        // Convert form to MUser class
        MUser user = modelMapper.map(form, MUser.class);
        // User search
        List<MUser> userList = userService.getUsers(user);
        List<String> fileNameList = new ArrayList<>();
        // Save user CSV file
        String userFileName = "user.csv";
        userApplicationService.saveUserCsv(userList, userFileName);
        fileNameList.add(userFileName);
        // Save departments CSV file
        String departmentFileName = "department.csv";
        userApplicationService.saveDepartmentCsv(userList, departmentFileName);
        fileNameList.add(departmentFileName);
        // HTTP header settings
        String zipFileName = "sample.zip";
        response.setHeader(HttpHeaders.CONTENT_TYPE,
                MediaType.APPLICATION_OCTET_STREAM_VALUE);
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=" + zipFileName);
        // Zip file download
        try (ZipOutputStream zos = new ZipOutputStream(response.getOutputStream())) {
            for (String fileName : fileNameList) {
                try (InputStream is = userApplicationService.getInputStream(fileName)) { // Store in zip file
                    zos.putNextEntry(new ZipEntry(fileName));
                    StreamUtils.copy(is, zos);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
