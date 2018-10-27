package com.iquantex.matrix.manage;

import com.iquantex.matrix.entity.book.Book;
import com.iquantex.matrix.entity.book.BookExample;
import com.iquantex.matrix.service.book.BookService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class Bookmanage {

    @Resource
    private BookService bookService;


    /**
     * Manage调用不同的service进行业务处理
     */
    public void testManage()
    {
        List<Book> books = bookService.selectByExample(new BookExample());
        long l = bookService.countByExample(new BookExample());
        System.out.println(new Object[]{books,l});
    }
}
