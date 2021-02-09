package com.postsgr6.kurs.service;

import com.postsgr6.kurs.model.Post;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PostServiceImpl implements PostService {

    // Хранилище постов
    private static final Map<Integer, Post> POST_REPOSITORY_MAP = new HashMap<>();

    // Переменная для генерации ID поста
    private static final AtomicInteger POST_ID_HOLDER = new AtomicInteger();

    @Override
    public Post create(Post post) {
        final int postId = POST_ID_HOLDER.incrementAndGet();
        post.setId(postId);
        POST_REPOSITORY_MAP.put(postId, post);
        return POST_REPOSITORY_MAP.get(postId);
    }

    @Override
    public List<Post> readAll() {
        return new ArrayList<>(POST_REPOSITORY_MAP.values());
    }

    @Override
    public Post read(int id) {
        return POST_REPOSITORY_MAP.get(id);
    }

    @Override
    public boolean update(Post post, int id) {
        if (POST_REPOSITORY_MAP.containsKey(id)) {
            post.setId(id);
            POST_REPOSITORY_MAP.put(id, post);
            return true;
        }

        return false;
    }

    @Override
    public boolean delete(int id) {
        return POST_REPOSITORY_MAP.remove(id) != null;
    }
}
