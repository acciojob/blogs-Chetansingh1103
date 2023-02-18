package com.driver.services;

import com.driver.models.Blog;
import com.driver.models.User;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository1;

    @Autowired
    UserRepository userRepository1;

    public Blog createAndReturnBlog(Integer userId, String title, String content) {
        //create a blog at the current time
        try {
            Blog blog = new Blog();
            blog.setTitle(title);
            blog.setContent(content);
            User user = userRepository1.findById(userId).get();

            user.getBlogList().add(blog);

            userRepository1.save(user);
            return blog;
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void deleteBlog(int blogId){
        //delete blog and corresponding images
        blogRepository1.deleteById(blogId);
    }
}
