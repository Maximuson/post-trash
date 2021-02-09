package com.postsgr6.kurs.controller;

import com.postsgr6.kurs.model.Post;
import com.postsgr6.kurs.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


// @RestController — говорит спрингу, что данный класс является REST контроллером.
// Т.е. в данном классе будет реализована логика обработки клиентских запросов
@RestController
public class PostController {
    private final PostService postService;

    /**
     * @Autowired — говорит спрингу, что в этом месте необходимо внедрить зависимость.
     * В конструктор мы передаем интерфейс PostService.
     * Реализацию данного сервиса мы пометили аннотацией @Service ранее,
     * и теперь спринг сможет передать экземпляр этой реализации в конструктор контроллера.
     */
    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    /**
     * @PostMapping(value = "/app/api/posts") — здесь мы обозначаем,
     * что данный метод обрабатывает POST запросы на адрес /app/api/posts
     */
    @PostMapping(value = "/app/api/posts")
    public ResponseEntity<Post> create(@RequestBody Post post) {
        /**
         * Внутри тела метода мы вызываем метод create у ранее созданного сервиса
         * и передаем ему принятого в параметрах контроллера клиента.
         * После чего возвращаем статус 201 Created, создав новый объект ResponseEntity
         * и передав в него нужное значение енума HttpStatus.
         */
        final Post newPost =  postService.create(post);
        return new ResponseEntity<>(newPost, HttpStatus.CREATED);
    }

    @GetMapping(value = "/app/api/posts")
    public ResponseEntity<List<Post>> read() {
        final List<Post> posts = postService.readAll();

        return posts != null && !posts.isEmpty()
                ? new ResponseEntity<>(posts, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/app/api/posts/{id}")
    public ResponseEntity<Post> read(@PathVariable(name = "id") int id) {
        final Post post = postService.read(id);

        return post != null
                ? new ResponseEntity<>(post, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/app/api/posts/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Post post) {
        final boolean updated = postService.update(post, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/app/api/posts/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = postService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
