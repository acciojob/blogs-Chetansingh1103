package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions) throws Exception{
        //add an image to the blog
        try {
            Image image = new Image();
            image.setDescription(description);
            image.setDimensions(dimensions);

            Blog blog = blogRepository2.findById(blogId).get();

            blog.getImageList().add(image);

            blogRepository2.save(blog);

            return image;
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void deleteImage(Integer id){
        imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) throws Exception{
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        Image image = imageRepository2.findById(id).get();

        // getting the given image dimension
        String dimensions = image.getDimensions();

        try {
            int a = Integer.parseInt(String.valueOf(dimensions.charAt(0)));
            int b = Integer.parseInt(String.valueOf(dimensions.charAt(2)));

            int c = a * b;

            // getting the total screen dimension
            int x = Integer.parseInt(String.valueOf(screenDimensions.charAt(0)));
            int y = Integer.parseInt(String.valueOf(screenDimensions.charAt(2)));

            int z = x * y;

            // returning number of total images which can fit inside the screen
            return z / c;
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }

    }
}
