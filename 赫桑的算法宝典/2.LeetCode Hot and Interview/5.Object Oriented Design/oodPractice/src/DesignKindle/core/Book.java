package DesignKindle.core;

import DesignKindle.enums.BookFormat;

/**
 * @Author He Zhu
 * @Date 2022-05-26
 * @Version 0.1
 */
public class Book {
    private BookFormat bookFormat;

    public Book(BookFormat bookFormat) {
        this.bookFormat = bookFormat;
    }

    public BookFormat getBookFormat() {
        return bookFormat;
    }

    public void setBookFormat(BookFormat bookFormat) {
        this.bookFormat = bookFormat;
    }
}
