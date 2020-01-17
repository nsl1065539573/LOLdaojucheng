package com.LOLdaojucheng.controller.back;

import com.LOLdaojucheng.common.ServerResponse;
import com.LOLdaojucheng.service.IproductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/manage/product")
public class UploadController {
    @Autowired
    IproductService iproductService;
    @RequestMapping(value = "/upload",method = RequestMethod.GET)
    public String upload(){
        return "upload";
    }
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse upload2(@RequestParam(value = "upload_file",required = false)MultipartFile file){
        String path = "E:\\Java_idea_workspace\\LOLdaojucheg\\src\\main\\webapp\\img";
        return iproductService.upload(file,path);
    }

    @RequestMapping(value = "/uploadPicture",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse uploadPicture(@RequestParam(value = "file",required = false)MultipartFile file){
        String path = "E:\\picture";
        return iproductService.upload(file,path);
    }
}
