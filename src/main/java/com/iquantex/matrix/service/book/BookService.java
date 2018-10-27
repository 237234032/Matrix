package com.iquantex.matrix.service.book;


import com.cabigail.generator.beans.GeneratorPageBean;
import com.iquantex.matrix.entity.book.Book;
import com.iquantex.matrix.entity.book.BookExample;
import com.iquantex.matrix.service.base.BaseService;

/**
 * @Description :
 * @author : zn-Cabigail(1163727353@qq.com)
 * @date :  2018/4/27 上午10:17
 */
public interface BookService extends BaseService<Book,BookExample> {

    GeneratorPageBean selectByLimitCondintion(BookExample example);
}
