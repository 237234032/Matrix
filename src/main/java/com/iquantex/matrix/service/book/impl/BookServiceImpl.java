package com.iquantex.matrix.service.book.impl;

import com.cabigail.generator.beans.GeneratorPageBean;
import com.iquantex.matrix.entity.book.Book;
import com.iquantex.matrix.entity.book.BookExample;
import com.iquantex.matrix.mapper.book.BookMapper;
import com.iquantex.matrix.mapper.base.BaseMapper;
import com.iquantex.matrix.service.book.BookService;
import com.iquantex.matrix.service.base.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BookServiceImpl extends BaseServiceImpl<Book,BookExample> implements BookService
{
    /**
     * 必须
     */
    @Resource
    private BookMapper bookMapper;

    /**
     * 必须要设置
     * @return
     */
    @Override
    public BaseMapper getMapper() {
        return bookMapper;
    }

    @Override
    public GeneratorPageBean selectByLimitCondintion(BookExample example)
    {
        /**获取数据**/
        List<Book> tests = this.selectByExample(example);
        /**获取总条数**/
        long count = this.countByExample(example);
       return  GeneratorPageBean.getInstance(tests,example.getStartIndex(),example.getPageSize(),count);
    }


}
