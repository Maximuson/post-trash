package com.postsgr6.kurs.service;

import com.postsgr6.kurs.model.Post;

import java.util.List;

public interface PostService {
    /**
     * Создает нового поста
     * @param post - пост для создания
     */
    Post create(Post post);

    /**
     * Возвращает список всех имеющихся постов
     * @return список клиентов
     */
    List<Post> readAll();

    /**
     * Возвращает пост по его ID
     * @param id - ID клиента
     * @return - объект клиента с заданным ID
     */
    Post read(int id);

    /**
     * Обновляет посто с заданным ID,
     * в соответствии с переданным постом
     * @param post - пост в соответсвии с которым нужно обновить данные
     * @param id - id поста которого нужно обновить
     * @return - true если данные были обновлены, иначе false
     */
    boolean update(Post post, int id);

    /**
     * Удаляет пост с заданным ID
     * @param id - id пост, которого нужно удалить
     * @return - true если пост был удален, иначе false
     */
    boolean delete(int id);
}
