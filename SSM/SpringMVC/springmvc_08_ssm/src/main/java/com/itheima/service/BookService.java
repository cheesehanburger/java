package com.itheima.service;

import com.itheima.domain.Book;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface BookService {

    /**
     * 保存
     * @param book
     */
    public boolean save(Book book);

    /**
     * 更新
     * @param book
     */
    public boolean update(Book book);

    /**
     * 根据id删除
     * @param id
     */

    public boolean delete(Integer id);

    /**
     * 根据id查询
     * @param id
     * @return
     */

    public Book selectById(Integer id);

    /**
     * 查询所有
     * @return
     */

    public List<Book> selectAll();
}
