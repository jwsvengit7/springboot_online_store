package com.example.stores.Config;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CloudinaryConfig {

    public String insert(MultipartFile file, String name) throws IOException {
            HashMap<String,String> config = new HashMap<>();
            config.put("cloud_name", "jwsven");
            config.put("api_key", "597977144288479");
            config.put("api_secret", "dohCGwMiFy0-Wm9faIX5Bqiua8M");
            Cloudinary cloudinary = new Cloudinary(config);
            Map<String, String> options = new HashMap<>();
            options.put("public_id", name);
            cloudinary.uploader().upload(file.getBytes(), options);

            String url = cloudinary.url().transformation(new Transformation().width(150).height(200).crop("fill")).generate(name);
            System.out.println(url);
            return url;

        }

    }



