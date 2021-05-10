package com.geo.controller;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;

@RestController
public class FileUploadController {

    @Value("${filepath}")
    private String filepath;


    @GetMapping("/upload")
    public @ResponseBody
    String provideUploadInfo() {
        return "You can upload the file using the same url\n";
    }


    @PostMapping("/upload") //
    public @ResponseBody String handleFileUpload(@RequestParam("name") String name,
                                                 @RequestParam("file") MultipartFile file){
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(name + "-uploaded")));
                stream.write(bytes);
                stream.close();

                System.out.println(name);
                System.out.println(file);

                RandomAccessFile writer = new RandomAccessFile( filepath + "file", "rw");
                writer.write(bytes);
                writer.close();

                return "File " + name + " uploaded success";
            } catch (Exception e) {
                return "the attempt to download the file  " + name + " failed=> " + e.getMessage();
            }
        } else {
            return "File " + name + " is empty";
        }
    }


}
