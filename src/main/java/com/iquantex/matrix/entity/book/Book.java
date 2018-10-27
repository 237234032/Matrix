package com.iquantex.matrix.entity.book;

import java.io.Serializable;

public class Book implements Serializable {
    /**
     */
    private String uuid;

    /**
     */
    private String bookName;

    /**
     */
    private Double bookPrice;

    /**
     */
    private String createtime;

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 每页展示条数
     */
    private Integer pageSize;

    /**
     * 起始下标
     */
    private Integer startIndex;


    private String createtimeBegin;

    private String createtimeEnd;


    /**
     * [Getter] 
     * @return uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * [Setter] 
     * @param uuid
     */
    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    /**
     * [Getter] 
     * @return bookName
     */
    public String getBookName() {
        return bookName;
    }

    /**
     * [Setter] 
     * @param bookName
     */
    public void setBookName(String bookName) {
        this.bookName = bookName == null ? null : bookName.trim();
    }

    /**
     * [Getter] 
     * @return bookPrice
     */
    public Double getBookPrice() {
        return bookPrice;
    }

    /**
     * [Setter] 
     * @param bookPrice
     */
    public void setBookPrice(Double bookPrice) {
        this.bookPrice = bookPrice;
    }

    /**
     * [Getter] 
     * @return createtime
     */
    public String getCreatetime() {
        return createtime;
    }

    /**
     * [Setter] 
     * @param createtime
     */
    public void setCreatetime(String createtime) {
        this.createtime = createtime == null ? null : createtime.trim();
    }

    /**
     * [Setter]
     * @param pageSize
     */
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * [Getter]
     * @return pageSize
     */
    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * [Setter]
     * @param startIndex
     */
    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    /**
     * [Getter]
     * @return startIndex
     */
    public Integer getStartIndex() {
        return startIndex;
    }

    /**
     * 如果startIndex和pageSize不存在。那么填充默认值，进行limit查询
     * @param defaultStartIndex 默认的startIndex
     * @param defaultPageSize 默认的pageSize
     */
    public void fillDefaultValueIfStartIndexAndPageSizeEmpty(Integer defaultStartIndex, Integer defaultPageSize) {
        if (this.startIndex == null) this.startIndex  = defaultStartIndex;
        if (this.pageSize == null)  this.pageSize = defaultPageSize;
    }

    /**
     * 如果startIndex和pageSize不存在。那么填充默认值，进行limit查询
     */
    public void fillDefaultValueIfStartIndexAndPageSizeEmpty() {
        this.fillDefaultValueIfStartIndexAndPageSizeEmpty(0,10);
    }

    public String getCreatetimeBegin() {
        return createtimeBegin;
    }

    public void setCreatetimeBegin(String createtimeBegin) {
        this.createtimeBegin = createtimeBegin;
    }

    public String getCreatetimeEnd() {
        return createtimeEnd;
    }

    public void setCreatetimeEnd(String createtimeEnd) {
        this.createtimeEnd = createtimeEnd;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", uuid=").append(uuid);
        sb.append(", bookName=").append(bookName);
        sb.append(", bookPrice=").append(bookPrice);
        sb.append(", createtime=").append(createtime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append(", pageSize=").append(pageSize);
        sb.append(", startIndex=").append(startIndex);
        sb.append("]");
        return sb.toString();
    }
}